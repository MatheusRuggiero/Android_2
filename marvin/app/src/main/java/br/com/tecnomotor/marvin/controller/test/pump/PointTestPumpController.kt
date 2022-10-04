package br.com.tecnomotor.marvin.controller.test.pump

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.CommandHelper
import br.com.tecnomotor.commonrail.device.commands.CommandToDevice
import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestPumpResult
import br.com.tecnomotor.commonrail.device.commands.result.MeasurementResult
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.commonrail.device.controller.DeviceControlController
import br.com.tecnomotor.commonrail.device.controller.DeviceMeasurementController
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestKey
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestProcessForControl
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumDefinesTestTypeMeasurement.*
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumTypeOfMeasurement
import br.com.tecnomotor.marvin.config.PointTestPumpConfig
import br.com.tecnomotor.marvin.config.RotationTestConfig
import br.com.tecnomotor.marvin.controller.test.IControllerTest
import br.com.tecnomotor.marvin.controller.test.flags.TestFlags
import br.com.tecnomotor.marvin.controller.test.flags.TestFlagsControl
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.utils.CountUpTimer
import br.com.tecnomotor.marvin.utils.TimeTest
import kotlinx.coroutines.*
import java.util.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
class PointTestPumpController(
    private var pointTestPump: PointTestPump,
    private val pump: Pump,
) : IControllerTest, Observer, Observable() {
    private var tagLog = this::class.java.simpleName
    private val repository = CommonRailRepository.getInstance()
    private var pointTestResult = PointTestPumpResult()
    val pointTestPumpResultValues: MutableLiveData<PointTestPumpResult> = MutableLiveData()
    private var rotationResult = RotationResult()
    val rotationResultValues: MutableLiveData<RotationResult> = MutableLiveData()
    private var measurementResult = MeasurementResult()
    private var controlController: DeviceControlController
    private var measurementController: DeviceMeasurementController
    private var measuringNow: EnumTypeOfMeasurement = EnumTypeOfMeasurement.None
    private var stopJobs = false

    // Tempo de teste do condicionamento
    private val conditioningTimeTest: Long = 20000
    private var timerJob: CountUpTimer? = null

    private val testFlags = TestFlagsControl()

    init {
        Log.w(tagLog, "Init class")
        //Flags para o teste
        testFlags
            .addValidFlags(TestFlags.flgSkipRotation)
        Log.i(tagLog, "Start control controller")
        repository.controlDevice.let {
            controlController = repository.controlController
        }
        Log.i(tagLog, "Start measurement controller")
        repository.measurementDevice.let {
            measurementController = repository.measurementController
        }
    }

    /**
     * Atualiza o tempo em pointTestResult
     */
    private fun timeTestOnTick(millisFinished: Long) {
        val timeInSeconds = (millisFinished / 1000)
        if (timeInSeconds != pointTestResult.testTime) {
            pointTestResult.testTime = timeInSeconds
            Log.w(tagLog, "Tempo: ${pointTestResult.testTime}")
        }
    }

    /**
     * Finaliza o tempo do ponto de teste
     */
    private fun timeTestOnFinish() {
        Log.w(tagLog, "Tempo do teste: Finalizado!")
        timerJob = null
    }

    /**
     * Finaliza o tempo do condicionamento
     */
    private fun timeConditioningOnFinish() {
        Log.w(tagLog, "Tempo do condicionamento: Finalizado!")
        pointTestResult.testTime = 0
        timerJob = null
        //INICIA A MEDIÇÃO
        pointTestResult.status = EnumTestStatus.TEST_RUNNING
        //Verificando qual será a medição VAZÃO PRINCIPAL OU VAZAO RETORNO
        if ((measuringNow == EnumTypeOfMeasurement.None) && (pointTestPump.measureMain))
            measuringNow = EnumTypeOfMeasurement.FlowMeasurement
        else if (((measuringNow == EnumTypeOfMeasurement.None) ||
                    (measuringNow == EnumTypeOfMeasurement.FlowMeasurement)) &&
            (pointTestPump.measureReturn)
        )
            measuringNow = EnumTypeOfMeasurement.ReturnMeasurement

        measurementParameterization(REMOTE_PUMP_PROCESS.getLocalizedCommandByte(), measuringNow) // parametriza
        measurementController.addCommand(CommandHelper.CMD_MEASURE_SET_PUMP_PROGRESS) // executa
    }

    /**
     * Define o ponto de teste a ser testado
     */
    fun setPointTestPump(value: PointTestPump) {
        //Log.i(tagLog, "setPointTestPump() - $value")
        this.pointTestPump = value
        this.rotationResult = RotationResult()
        this.pointTestResult = PointTestPumpResult()
        this.measurementResult = MeasurementResult()
    }

    /**
     * Reseta as placas para o estado inicial
     */
    private fun resetBoardsParam() {
        resetControlParam()
        resetMeasureParam()
    }

    private fun resetControlParam() {
        controlController.addCommand(CommandHelper.CMD_PARAMETER_RESET)
    }

    private fun resetMeasureParam() {
        measurementController.addCommand(CommandHelper.CMD_PARAMETER_RESET)
    }

    /**
     * Parametriza a placa de controle utilizando timeout personalizado
     */
    private fun getControlParameters(timeout: Int? = null): ByteArray {
        return if (timeout == null) CommandHelper.CMD_SET_PARAMETER + pointTestPump.getControlParameters(pump = pump)
        else CommandHelper.CMD_SET_PARAMETER + pointTestPump.getControlParameters(timeout, pump = pump)
    }

    /**
     * Executa o teste de rotação
     */
    @Synchronized
    private fun execRotationTest() {
        measuringNow = EnumTypeOfMeasurement.None
        rotationResult.status = EnumTestStatus.TEST_PARAMETERIZING
        controlController.addCommand(getControlParameters(RotationTestConfig.getInstance().getTimeoutRotation()))
        controlController.addCommand(CommandHelper.CMD_SET_TEST + EnumControlTypeTest.RPM_TEST.value)
        flush() // inicializa medição com flush
    }


    /**
     * Repete teste de rotação
     */
    fun repeatRotation() {
        resetControlParam()
        execRotationTest()
    }

    /**
     * Parametriza a placa de medição
     * @param device indica qual dispositivo será usado na parametrização
     * @param typeOfMeasurement indica qual vazão será usada na medição: principal ou retorno. Caso deixado em branco nenhuma será selecionada.
     */
    private fun measurementParameterization(
        device: Byte,
        typeOfMeasurement: EnumTypeOfMeasurement = EnumTypeOfMeasurement.None,
    ) {
        resetMeasureParam()
        val parameters = pointTestPump.getMeasurementParameters(device, typeOfMeasurement)
        measurementController.addCommand(CommandHelper.CMD_SET_PARAMETER + parameters)
    }

    /**
     * Parametriza e depois inicia o processo de flush
     */
    private fun flush() {
        measurementParameterization(REMOTE_FLUSH_PROCESS.getLocalizedCommandByte())
        measurementController.addCommand(CommandHelper.CMD_MEASURE_SET_FLUSH_PROCESS)
    }

    /**
     * Executa o condicionamento do teste
     */
    private fun execConditioning() {
        pointTestResult.status = EnumTestStatus.TEST_CONDITIONING
        pointTestPumpResultValues.postValue(pointTestResult)

        measurementParameterization(REMOTE_CONDITIONING_PROCESS.getLocalizedCommandByte())
        measurementController.addCommand(CommandHelper.CMD_MEASURE_SET_CONDITIONING_PROCESS)

        timerJob = TimeTest.getTimeTest(
            conditioningTimeTest,
            this::timeTestOnTick,
            this::timeConditioningOnFinish
        )
        timerJob!!.start()
    }

    /**
     * Inicia a execução do ponto de teste
     */
    @Synchronized
    private fun runTest() {
        pointTestResult.testTime = 0
        rotationResult = RotationResult()
        testFlags.clearFlag()
        val timeout = PointTestPumpConfig.getInstance().getTimeoutControlWhenMeasuring()
        controlController.addCommand(getControlParameters(if (pointTestPump.isMeasure()) timeout else null))
        controlController.addCommand(
            CommandHelper.CMD_SET_TEST + EnumTestProcessForControl.TestPumps.value)
        if (pointTestPump.isMeasure())
            execConditioning()
        else {
            timerJob = TimeTest.getTimeTest(pointTestPump.getTimeout()*1000L, this::timeTestOnTick, this::timeTestOnFinish)
            timerJob!!.start()
        }
    }

    /**
     * Envia teclas de controle
     */
    private fun sendKeyYes() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.YES.value)
    private fun sendKeyF2() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.F2.value)
    private fun sendKeyF3() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.F3.value)
    private fun sendKeyF4() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.F4.value)
    private fun sendKeyReturn() { controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.RETURN.value) }

    /**
     * Inicia o teste
     */
    override fun startTest() {
        stopJobs = false
        testFlags.clearFlag()
        rotationResult.status = EnumTestStatus.TEST_STARTING
        rotationResult.error = ECommonRailCommandException(0)
        rotationResultValues.postValue(rotationResult)

        controlController.deleteObserver(this@PointTestPumpController)
        measurementController.deleteObserver(this@PointTestPumpController)
        resetBoardsParam()
        if (controlController.isFinished())
            controlController.start()
        if (measurementController.isFinished())
            measurementController.start()

        controlController.addObserver(this)
        measurementController.addObserver(this)
    }

    /**
     * Cancela o teste (tanto rotação como ponto de teste
     */
    override fun cancelTest() {
        if (rotationResult.status in arrayOf(EnumTestStatus.TEST_FAIL, EnumTestStatus.TEST_STOPPED) && (pointTestResult.status != EnumTestStatus.TEST_RUNNING))
            finishBoards()
        else
            sendKeyReturn()
    }

    /**
     * Pula o teste
     */
    override fun skipTest() {
        when {
            // se deu erro de rotação então não envia F4
            // apenas finaliza placas e atualiza observer com status skipped para viewModel tomar decisão
            rotationResult.status in arrayOf(EnumTestStatus.TEST_FAIL, EnumTestStatus.TEST_STOPPED) -> {
                rotationResult.status = EnumTestStatus.TEST_SKIPPED
                rotationResultValues.postValue(rotationResult)
                finishBoards()
            }
            // teste está pausado e clicou em pular então notifica view model para tomar decisão
            pointTestResult.status == EnumTestStatus.TEST_PAUSED -> {
                pointTestResult.status = EnumTestStatus.TEST_SKIPPED
                pointTestPumpResultValues.postValue(pointTestResult)
            }
            // teste rodando normalmente então envia F4
            else -> sendKeyF4()
        }
    }

    /**
     * Pula verificação de rotação
     */
    fun skipRotation() {
        if (rotationResult.status == EnumTestStatus.TEST_RUNNING) {
            testFlags.flag = TestFlags.flgSkipRotation
            sendKeyF4()
        } else if (rotationResult.status in arrayOf(EnumTestStatus.TEST_FAIL, EnumTestStatus.TEST_STOPPED))
            continuarTest()
    }

    fun continuarTest() {
        resetControlParam()
        runTest()
    }

    /**
     * Pausa o teste
     */
    override fun pauseTest() {
        sendKeyF3()
    }

    override fun isFinished(): Boolean = (controlController.isFinished() && measurementController.isFinished())

    /**
     * Finaliza as placas
     */
    private fun finishBoards() {
        testFlags.clearFlag()
        resetBoardsParam()
        flush()
        GlobalScope.launch {
            delay(100) // não gostei disso, só para dar tempo de dar o resetBoard antes de parar o stopJobs
            stopJobs = true
            timerJob?.stop()
        }
    }

    /**
     * Para o controlador da placa de controle
     */
    private fun stopJobController() {
        controlController.stop()
        controlController.deleteObserver(this@PointTestPumpController)

    }

    /**
     * Para o controlador da placa de medição
     */
    private fun stopJobMeasurement() {
        measurementController.stop()
        measurementController.deleteObserver(this@PointTestPumpController)
    }

    /**
     * Monitora a execução do teste
     */
    @Synchronized
    override fun update(observable: Observable?, value: Any?) {
        when (observable) {
            is DeviceControlController -> {
                Log.d(tagLog, value.toString())
                if (stopJobs) {
                    stopJobController()
                    Log.i(tagLog, "controlController.stop()")
                    return
                }

                when (value) {
                    is RotationResult -> {
                        rotationResult.deepCopy(value)
                        when (value.status) {
                            EnumTestStatus.TEST_WAIT_KEY -> {
                                sendKeyYes()
                            }
                            EnumTestStatus.TEST_SKIPPED -> {
                                if (testFlags.isValid()) {
                                    if (testFlags.isValid()) testFlags.setStatus(rotationResult)
                                    rotationResultValues.postValue(rotationResult)
                                    runTest()
                                    return
                                } else
                                    finishBoards()
                            }
                            EnumTestStatus.TEST_FAIL -> {
                                //resetControlParam()
                            }
                            EnumTestStatus.TEST_PAUSED,
                            EnumTestStatus.TEST_CANCELLED -> {
                                finishBoards()
                            }
                            EnumTestStatus.TEST_FINISHED -> {
                                runTest()
                            }
                            else -> {}
                        }
                        if (testFlags.isValid()) testFlags.setStatus(rotationResult)
                        if (rotationResult.status != EnumTestStatus.NONE) // https://tecnomotor.easyredmine.com/issues/17099
                            rotationResultValues.postValue(rotationResult)
                    }
                    is PointTestPumpResult -> {
                        when (pointTestResult.status) { // status anterior por causa das coisas medição, está aqui pelo observer da placa de controle
                            EnumTestStatus.TEST_CONDITIONING -> {
                                val pointTestReceived = PointTestPumpResult()
                                pointTestReceived.deepCopy(value)
                                pointTestReceived.status = EnumTestStatus.TEST_CONDITIONING
                                pointTestReceived.testTime = pointTestResult.testTime
                                pointTestReceived.mainFlow = pointTestResult.mainFlow
                                pointTestReceived.returnFlow = pointTestResult.returnFlow
                                pointTestReceived.errorMeasurement = pointTestResult.errorMeasurement
                                pointTestResult.deepCopy(pointTestReceived)
                            }
                            EnumTestStatus.TEST_FAIL -> {
                                finishBoards()
                            }
                            else -> {
                                val pointTestReceived = PointTestPumpResult()
                                pointTestReceived.deepCopy(value)
                                pointTestReceived.testTime = pointTestResult.testTime
                                pointTestReceived.errorMeasurement = pointTestResult.errorMeasurement
                                if (pointTestPump.measureMain || pointTestPump.measureReturn)
                                    when (measuringNow) {
                                        EnumTypeOfMeasurement.FlowMeasurement -> {
                                            pointTestReceived.mainFlow = measurementResult.flowLHCH3
                                        }
                                        EnumTypeOfMeasurement.ReturnMeasurement -> {
                                            pointTestReceived.mainFlow = pointTestResult.mainFlow
                                            pointTestReceived.returnFlow = measurementResult.flowLHCH3
                                        }
                                        else -> {
                                        }
                                    }
                                pointTestResult.deepCopy(pointTestReceived)
                            }
                        }
                        when (value.status) {
                            EnumTestStatus.TEST_RUNNING -> {
                                //Executa o teste com medição de retorno
                                if ((measurementResult.status == EnumTestStatus.TEST_FINISHED) &&
                                    (measuringNow == EnumTypeOfMeasurement.FlowMeasurement) && (pointTestPump.measureReturn))
                                        execConditioning()
                                else if (measurementResult.status == EnumTestStatus.TEST_FINISHED) {
                                    sendKeyF2()
                                } else
                                    pointTestPumpResultValues.postValue(pointTestResult)
                            }
                            EnumTestStatus.TEST_PAUSED,
                            EnumTestStatus.TEST_SKIPPED,
                            EnumTestStatus.TEST_CANCELLED -> {
                                if (testFlags.isValid()) testFlags.setStatus(pointTestResult)
                                else pointTestResult.status = value.status
                                pointTestPumpResultValues.postValue(pointTestResult)
                                finishBoards()
                            }
                            EnumTestStatus.TEST_FAIL -> {
                                pointTestResult.status = value.status
                                pointTestResult.error = value.error
                                pointTestPumpResultValues.postValue(pointTestResult)
                                //resetControlParam()
                                finishBoards()
                            }
                            EnumTestStatus.TEST_FINISHED -> {
                                pointTestPumpResultValues.postValue(pointTestResult)
                                finishBoards()
                            }
                            else -> {}
                        }
                    }
                    is CommandToDevice -> {
                        if (CommandHelper.commandIsValid(value.read) && (!testFlags.isValid())) {
                            if (rotationResult.status == EnumTestStatus.TEST_STARTING) {
                                Log.d(tagLog, "execRotationTest in CommandToDevice")
                                execRotationTest()
                            }
                        }
                    }
                    is Exception -> {//Printa no log se recebeu alguma mensagem
                        value.message?.let { Log.e(tagLog, it) }
                    }
                    else -> {}
                }
            }
            is DeviceMeasurementController -> {
//                Log.v(tagLog, value.toString())
                if (stopJobs) {
                    stopJobMeasurement()
                    Log.i(tagLog, "measurementController.stop()")
                    return
                }
                when (value) {
                    is MeasurementResult -> {
                        measurementResult.deepCopy(value)
                        when (value.status) {
                            EnumTestStatus.TEST_FAIL -> {
                                Log.w(tagLog, "Falha no teste. PLACA DE MEDIÇÃO")
                                pointTestResult.status = value.status
                                pointTestResult.error = value.error
                            }
                            else -> {}
                        }
                    }
                    is CommandToDevice -> {}
                    is Exception -> {
                        value.message?.let { Log.e(tagLog, it) }
                    }
                    else -> {}
                }
            }
            else -> {
                Log.d(
                    tagLog,
                    "observable type not compatible: ${observable?.javaClass.toString()}: ${value.toString()}"
                )
            }
        }
    }

}