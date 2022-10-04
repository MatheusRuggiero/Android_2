package br.com.tecnomotor.marvin.view.unit_test.pump

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.CommandHelper
import br.com.tecnomotor.commonrail.device.commands.CommandSendRead
import br.com.tecnomotor.commonrail.device.commands.CommandToDevice
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestPumpResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestSynchronizedPumpResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.SingleElectricalTestResult
import br.com.tecnomotor.commonrail.device.commands.result.MeasurementResult
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.commonrail.device.controller.DeviceControlController
import br.com.tecnomotor.commonrail.device.controller.DeviceMeasurementController
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestKey
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestProcessForControl
import br.com.tecnomotor.marvin.utils.ExtentionFunctions.toByteArray16
import br.com.tecnomotor.marvin.utils.ExtentionFunctions.toByteArray32
import br.com.tecnomotor.marvin.utils.ExtentionFunctions.toByteArray8
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
class PumpTestUnitController(var repository: CommonRailRepository) : Observer {
    private var initController = GlobalScope.launch { }
    private var tagLog = this::class.java.simpleName

    private val controlController: DeviceControlController by lazy { repository.controlController }

    private val measurementController: DeviceMeasurementController by lazy { repository.measurementController }

    val commandToDeviceControlValues: MutableLiveData<CommandToDevice> = MutableLiveData()
    val commandToDeviceMeasurementValues: MutableLiveData<CommandToDevice> = MutableLiveData()
    val measurementResultValues: MutableLiveData<MeasurementResult> = MutableLiveData()
    val rotationResultValues: MutableLiveData<RotationResult> = MutableLiveData()

    //lateinit var testPointsResult: TestPointsResult
    val pointTestPumpPumpResultValues: MutableLiveData<PointTestPumpResult> = MutableLiveData()
    val pointTestSynchronizedPumpResultValues: MutableLiveData<PointTestSynchronizedPumpResult> = MutableLiveData()
    val resultTestElectric: MutableLiveData<SingleElectricalTestResult> = MutableLiveData()
    val commandControlSendRead: MutableLiveData<CommandSendRead> = MutableLiveData()
    val commandMeasurementSendRead: MutableLiveData<CommandSendRead> = MutableLiveData()


    init {
        //initController = GlobalScope.launch {
        //repository.waitInit()//espera o repository conversar com as placas e criar os dispositivos
        controlController.addObserver(this@PumpTestUnitController)
        controlController.start()
        measurementController.addObserver(this@PumpTestUnitController)
        measurementController.start()
        resetControlParam()
        resetMeasureParam()
        //}
    }

    /**
     * Comandos gerais
     */
    fun resetControlParam() = controlController.addCommand(CommandHelper.CMD_PARAMETER_RESET)

    /**
     * Função ainda não disponível para uso
     */
    fun resetMeasureParam() = measurementController.addCommand(CommandHelper.CMD_PARAMETER_RESET)


    /**
     * #####################################################
     * ##### FUNÇÕES TESTES UNITÁRIOS - PLACA CONTROLE #####
     * #####################################################
     */

    /**
     * Teclas especiais
     */
    fun sendKeyFinished() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.F2.value)
    fun sendKeyPaused() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.F3.value)
    fun sendKeyRet() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.RETURN.value)
    fun sendKeyError() = controlController.addCommand(CommandHelper.CMD_PARAMETER_RESET)
    fun sendKeyF4() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.F4.value)
    fun sendKeyStart() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.START.value)
    fun sendKeyStop() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.STOP.value)
    fun sendKeySkip() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.SKIP.value)
    fun sendKeyCancel() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.CANCEL.value)


    /**
     * Testes Digitais
     */
    fun onBuzzer() = controlController.addCommand(CommandHelper.buzzerON())
    fun offBuzzer() = controlController.addCommand(CommandHelper.buzzerOFF())

    fun onLed01() = controlController.addCommand(CommandHelper.cmdLedON(CommandHelper.CMD_LED_01))
    fun offLed01() = controlController.addCommand(CommandHelper.cmdLedOFF(CommandHelper.CMD_LED_01))

    fun onLed02() = controlController.addCommand(CommandHelper.cmdLedON(CommandHelper.CMD_LED_02))
    fun offLed02() = controlController.addCommand(CommandHelper.cmdLedOFF(CommandHelper.CMD_LED_02))

    fun onLed03() = controlController.addCommand(CommandHelper.cmdLedON(CommandHelper.CMD_LED_03))
    fun offLed03() = controlController.addCommand(CommandHelper.cmdLedOFF(CommandHelper.CMD_LED_03))

    fun onLed04() = controlController.addCommand(CommandHelper.cmdLedON(CommandHelper.CMD_LED_04))
    fun offLed04() = controlController.addCommand(CommandHelper.cmdLedOFF(CommandHelper.CMD_LED_04))

    fun onLed05() = controlController.addCommand(CommandHelper.cmdLedON(CommandHelper.CMD_LED_05))
    fun offLed05() = controlController.addCommand(CommandHelper.cmdLedOFF(CommandHelper.CMD_LED_05))

    fun onLed06() = controlController.addCommand(CommandHelper.cmdLedON(CommandHelper.CMD_LED_06))
    fun offLed06() = controlController.addCommand(CommandHelper.cmdLedOFF(CommandHelper.CMD_LED_06))

    /**
     * Testes Analógicos
     */
    fun paramAnalogTests(channel: Int, pwm: Int, duty: Int) = controlController.addCommand(CommandHelper.CMD_HW_ANALOGIC_PARAM + channel.toByteArray8() + pwm.toByteArray16() + duty.toByteArray32())
    fun startAnalogTests() = controlController.addCommand(CommandHelper.CMD_START_ANALOG_TEST)

    /**
     * Teste elétrico
     */
    fun drv1() = controlController.addCommand(byteArrayOf(0x26.toByte(), 0x17.toByte()))
    fun drv2() = controlController.addCommand(byteArrayOf(0x26.toByte(), 0x18.toByte()))
    fun ext1() = controlController.addCommand(byteArrayOf(0x26.toByte(), 0x19.toByte()))
    fun ext2() = controlController.addCommand(byteArrayOf(0x26.toByte(), 0x1A.toByte()))

    /**
     * Verificação rotação antes do teste de bomba
     */
    fun checkRotationParam(desiredRotation: String, timeout: String) {
        val rotacaoByteArray: ByteArray = desiredRotation.toInt().toByteArray16() //Retorna a rotação informada em ByteArray
        val timeoutByteArray: ByteArray = timeout.toInt().toByteArray16() //Retorna o timeout informado em ByteArray
        val param = byteArrayOf(
            0x06.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),
            0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte()) + rotacaoByteArray + timeoutByteArray
        controlController.addCommand(CommandHelper.CMD_SET_PARAMETER + param)
    }
    fun sendKeyCheckStart() = controlController.addCommand(CommandHelper.CMD_SET_TEST + EnumTestProcessForControl.CheckTestRotation.value)

    /**
     * Teste bomba
     */
    fun paramPumpTest(listOpPointTestPump: ArrayList<String>) {
        val controlByteArray = listOpPointTestPump[0].toInt().toByteArray8()
        val ext1Corrente = ((listOpPointTestPump[1].toFloat() * 100).toInt()).toByteArray16()
        val ext2Corrente = ((listOpPointTestPump[2].toFloat() * 100).toInt()).toByteArray16()
        val pressure = listOpPointTestPump[3].toInt().toByteArray16()
        val rotation = listOpPointTestPump[4].toInt().toByteArray16()
        val timeout = listOpPointTestPump[5].toInt().toByteArray16()
        val freqExt1 = listOpPointTestPump[6].toInt().toByteArray16()
        val freqExt2 = listOpPointTestPump[7].toInt().toByteArray16()

        val param = byteArrayOf(0x06.toByte()) + controlByteArray + ext1Corrente + ext2Corrente + freqExt1 + freqExt2 + pressure + rotation + timeout

        resetControlParam()
        controlController.addCommand(CommandHelper.CMD_SET_PARAMETER + param)
    }
    fun sendKeyPointTestStart() = controlController.addCommand(CommandHelper.CMD_SET_TEST + EnumTestProcessForControl.TestPumps.value)

    /**
     * Teste Bombas Sincronizadas
     */
    fun btnTestSynchronizedPumpParam(listTestPumpSynchronized: ArrayList<String>) {
        val testTime = listTestPumpSynchronized[0].toInt().toByteArray16()
        val param1 = listTestPumpSynchronized[1].toInt().toByteArray16()
        val param2 = listTestPumpSynchronized[2].toInt().toByteArray16()
        val param3 = listTestPumpSynchronized[3].toInt().toByteArray16()
        val param4 = listTestPumpSynchronized[4].toInt().toByteArray16()
        val param5 = listTestPumpSynchronized[5].toInt().toByteArray16()
        val param6 = listTestPumpSynchronized[6].toInt().toByteArray16()
        val param7 = listTestPumpSynchronized[7].toInt().toByteArray16()
        val param8 = listTestPumpSynchronized[8].toInt().toByteArray16()
        val param9 = listTestPumpSynchronized[9].toInt().toByteArray16()
        val param10 = listTestPumpSynchronized[10].toInt().toByteArray16()
        val param11 = listTestPumpSynchronized[11].toInt().toByteArray16()
        val param = byteArrayOf(EnumControlTypeTest.SYNC_PUMP.value) + testTime + param1 + param2 + param3 + param4 + param5 + param6 + param7 + param8 + param9 + param10 + param11

        resetControlParam()
        controlController.addCommand(CommandHelper.CMD_SET_PARAMETER + param)
    }

    /**
     * ####################################################
     * ##### FUNÇÕES TESTES UNITÁRIOS - PLACA MEDIÇÃO #####
     * ####################################################
     */

    /**
     * Testes Analógicos e Digital
     */
    fun startAnalogTestsMed() = measurementController.addCommand(CommandHelper.CMD_START_ANALOG_TEST_MED)

    fun onBuzzerMeasurement() = measurementController.addCommand(CommandHelper.buzzerONMed())
    fun offBuzzerMeasurement() = measurementController.addCommand(CommandHelper.buzzerOFFMed())

    fun onValvFlushRet() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_VALV_FLUSH_RET))
    fun offValvFlushRet() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_VALV_FLUSH_RET))

    fun onValvFlushInj() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_VALV_FLUSH_INJ))
    fun offValvFlushInj() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_VALV_FLUSH_INJ))

    fun onValvCond() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_VALV_COND))
    fun offValvCond() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_VALV_COND))

    fun onComut01() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_VALV_COMUT_1))
    fun offComut01() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_VALV_COMUT_1))

    fun onComut02() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_VALV_COMUT_2))
    fun offComut02() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_VALV_COMUT_2))

    fun onComut03() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_VALV_COMUT_3))
    fun offComut03() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_VALV_COMUT_3))

    fun onDreno01() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_VALV_DRENO_M1))
    fun offDreno01() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_VALV_DRENO_M1))

    fun onDreno02() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_VALV_DRENO_M2))
    fun offDreno02() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_VALV_DRENO_M2))

    fun onExtra01() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_EXTRA_01))
    fun offExtra01() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_EXTRA_01))

    fun onExtra02() = measurementController.addCommand(CommandHelper.cmdONMed(CommandHelper.CMD_EXTRA_02))
    fun offExtra02() = measurementController.addCommand(CommandHelper.cmdOFFMed(CommandHelper.CMD_EXTRA_02))

    /**
     * Parametrização Medição
     */

    fun paramPumpTestMeasurement(dispositivo:Int, cmCH1:Int, cmCH2:Int, freq:Int, timeout:Int, canal:Int, flagPrincipal:Int,  flagRetorno:Int){
        // resetMeasureParam()
        val param =
            dispositivo.toByteArray8() +
                    cmCH1.toByteArray16() +
                    cmCH2.toByteArray16() +
                    freq.toByteArray16() +
                    timeout.toByteArray16() +
                    canal.toByteArray8() +
                    flagPrincipal.toByteArray8() +
                    flagRetorno.toByteArray8()
        measurementController.addCommand(CommandHelper.CMD_SET_PARAMETER + param)
    }
    private fun sendKeyMeasurement(key: EnumTestKey) = measurementController.addCommand(CommandHelper.CMD_SEND_KEY + key.value)
    private fun setTestMeasurement(cmd: ByteArray) = measurementController.addCommand(cmd)

    fun sendKeyParamMedSim() = sendKeyMeasurement(EnumTestKey.YES)
    fun sendKeyParamMedNao() = sendKeyMeasurement(EnumTestKey.NO)
    fun sendKeyParamMedRet() = sendKeyMeasurement(EnumTestKey.RETURN)
    fun sendKeyParamMedError() = setTestMeasurement(CommandHelper.CMD_PARAMETER_RESET)
    fun sendKeyParamMedFlush() = setTestMeasurement(CommandHelper.CMD_MEASURE_SET_FLUSH_PROCESS)
    fun sendKeyParamMedCondic() = setTestMeasurement(CommandHelper.CMD_MEASURE_SET_CONDITIONING_PROCESS)
    fun sendKeyParamMedEstanq() = setTestMeasurement(CommandHelper.CMD_MEASURE_SET_ESTANQ_PROCESS)
    fun sendKeyParamMedInjector() = setTestMeasurement(CommandHelper.CMD_MEASURE_SET_INJET_PROCESS)
    fun sendKeyParamMedValve() = setTestMeasurement(CommandHelper.CMD_MEASURE_SET_VALVULAS_PROCESS)
    fun sendKeyParamMedPump() = setTestMeasurement(CommandHelper.CMD_MEASURE_SET_PUMP_PROGRESS)


    override fun update(observable: Observable?, value: Any?) {
//        Log.d(tagLog, "Observable: ${observable?.javaClass?.simpleName}")
//        Log.d(tagLog, "Value: ${value?.javaClass?.simpleName}")
        when (observable) {
            is DeviceControlController -> {
                when (value) {
                    is RotationResult -> {
                        rotationResultValues.postValue(value)
                    }
                    is PointTestPumpResult -> {
                        pointTestPumpPumpResultValues.postValue(value)
                    }
                    is PointTestSynchronizedPumpResult -> {
                        pointTestSynchronizedPumpResultValues.postValue(value)
                    }
                    is CommandToDevice -> {
                        commandToDeviceControlValues.postValue(value)
                    }
                    is SingleElectricalTestResult -> {
                        resultTestElectric.postValue(value)
                    }
                    is CommandSendRead -> {
                        commandControlSendRead.postValue(value)
                    }
                    is Exception -> {
                        value.message?.let { Log.e(tagLog, it) }
                    }
                    else -> {
                        Log.w(tagLog, "Valor não tratado")
                    }
                }
            }
            is DeviceMeasurementController -> {
                when (value) {
                    is MeasurementResult -> {
                        measurementResultValues.postValue(value)
                    }
                    is CommandToDevice -> {
                        commandToDeviceMeasurementValues.postValue(value)
                    }
                    is CommandSendRead -> {
                        commandMeasurementSendRead.postValue(value)
                    }
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

    fun stopCommunication() {
        controlController.stop()
        measurementController.stop()
    }

}