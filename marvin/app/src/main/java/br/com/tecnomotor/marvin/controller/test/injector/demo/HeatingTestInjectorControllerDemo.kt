package br.com.tecnomotor.marvin.controller.test.injector.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.injector.EnumInjectorType
import br.com.tecnomotor.commonrail.device.commands.injector.result.PointTestInjectorResultDemo
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import br.com.tecnomotor.marvin.controller.test.demo.PressureDemo
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import br.com.tecnomotor.marvin.utils.CountUpTimer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class HeatingTestInjectorControllerDemo(
    private val injector: Injector,
    private val pointTestInjector: PointTestInjector
): ControllerTest(), Observer {

    private var tagLog = this::class.java.simpleName
    private var stepIndex: Int = 0
    private companion object {
        class HeatingStep(
            var step: Double,
            var pressure: Long
        )
        val heatingStepNormal: ArrayList<HeatingStep> = arrayListOf(
            HeatingStep(0.0, 200),
            HeatingStep(0.15, 600),
            HeatingStep(0.30, 900),
            HeatingStep(0.50, 1100),
            HeatingStep(0.70, 1400)
        )
        val heatingStepDS: ArrayList<HeatingStep> = arrayListOf(
            HeatingStep(0.0, 200),
            HeatingStep(0.15, 400),
            HeatingStep(0.30, 500),
            HeatingStep(0.50, 600),
            HeatingStep(0.70, 750)
        )
    }
    private var heatingStep: ArrayList<HeatingStep> =
        if (injector.typeId == EnumInjectorType.INDUTIVO_DS.id)
            heatingStepDS else heatingStepNormal
    private var timeStep: Long = 0

    private var testeEmExecucao = false

    private var resultadoDeTeste = PointTestInjectorResultDemo(EnumTestStatus.NONE)
    val resultadoTesteInjetor: MutableLiveData<PointTestInjectorResultDemo> = MutableLiveData(resultadoDeTeste)
    private var countTest: CountUpTimer

    val pressureDemo = PressureDemo(200)

    init {
        pressureDemo.addObserver(this)

        /**
         * Contador do tempo de teste para aquecimento
         * - campo timeout
         */
        @Synchronized
        countTest = object: CountUpTimer(1000, pointTestInjector.timeTest.toLong() * 1000) {
            override fun onTick(millisFinished: Long) {
                if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING) {
                    resultadoDeTeste.testTime = millisFinished
                    resultadoTesteInjetor.postValue(resultadoDeTeste)
                    setChanged()
                    notifyObservers(resultadoDeTeste)
                }
            }

            override fun onFinish() {
                if (resultadoDeTeste.status !in
                    arrayOf(
                        EnumTestStatus.TEST_SKIPPED,
                        EnumTestStatus.TEST_CANCELLED,
                        EnumTestStatus.TEST_FAIL,
                        EnumTestStatus.TEST_PAUSED)) {
                    Log.w(tagLog, "CountUpTimer: onFinish")
                    pressureDemo.stop()
                    resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
                    resultadoTesteInjetor.postValue(resultadoDeTeste)
                    setChanged()
                    notifyObservers(resultadoDeTeste)
                }
            }
        }
    }

    /**
     *  ParameterGroup,         [01]  => 01 - Injector;
     *  CurrInjector,       [02]  => 0 - inj1 .. 3 - inj4
     *  InjectionFlag,      [03]  => 0 - disable measurement; 1 - enable measurement
     *  InjectionMsrFlag,   [04]  => 0 - disable measurement; 1 - enable measurement
     *  ReturnMsrFlag,      [05]  => 0 - disable measurement; 1 - enable measurement
     *  Pressure,        [06 07]
     *  TempoTeste,      [08 09]
     *  Frequency,       [10 11]
     *  TimeInj,         [12 13]
     *  TimeOn,             [14]
     *  TimeOff,            [15]
     *  TimeHigh,           [16]
     *  TimeIHigh,       [17 18]
     *  InjFillCtl,        [19]
     *  lTimeHoldOff      [20]
     *  lVLow             [21]
     *  lVHigh            [22]
     */
    @Synchronized
    private suspend fun parametrizaTeste(pressure: Long):Boolean {
        var res = true //false
//        if (pointTestInjector.parameters.isNotEmpty()) {
//            if (repository.commandControle()?.podeParametrizar()!!) {
//                val parametros = byteArrayOf(0x01.toByte(), 0x00.toByte()) +
//                        testPointData.parameters["injecaoAtiva"]!! +
//                        testPointData.parameters["medirInjecao"]!! +
//                        testPointData.parameters["medirRetorno"]!! +
//                        byteArrayOf(
//                            (pressure shr (8)).toByte(),
//                            pressure.toByte()
//                        ) + //testPointData.parameters["pressaoStep"]!! +
//                        byteArrayOf(0x07.toByte(), 0x08.toByte()) +
//                        testPointData.parameters["frequencia"]!! +
//                        testPointData.parameters["tempoInjecao"]!! +
//                        testPointData.parameters["tempoLigado"]!! +
//                        testPointData.parameters["tempoDesligado"]!! +
//                        testPointData.parameters["tempoDoSinalEmAlta"]!! +
//                        testPointData.parameters["tempo12Volts"]!! +
//                        testPointData.parameters["preEncherMedidoInjecao"]!! +
//                        testPointData.parameters["tempoDoSinalDesligado"]!! +
//                        testPointData.parameters["tensaoBaixa"]!! +
//                        testPointData.parameters["tensaoAlta"]!!
//                setLogInfo("Parametros ($pressure): ${parametros.toHex()}")
//                res = repository.commandControle()?.setParametros(parametros, 1) ?: false
//            }
//        }
        return res
    }

    @Synchronized
    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while(!finished) {
            if (started) {
                if (!paused) {
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_STARTING) {
                        Log.w(tagLog, "Ligar bombas")
//                        commands.repository.commandControle()?.ligarBombas()
                        Log.w(tagLog, "Flush")
//                        commands.repository.commandMedicao()?.flush()
                        resultadoDeTeste.status = EnumTestStatus.TEST_PARAMETERIZING
                        resultadoTesteInjetor.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        delay(500)
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_PARAMETERIZING) {
                        if (parametrizaTeste(heatingStep[stepIndex].pressure)) {
                            resultadoDeTeste.desiredPressure = heatingStep[stepIndex].pressure
                            pressureDemo.targetPressure = heatingStep[stepIndex].pressure
                            resultadoDeTeste.status = EnumTestStatus.TEST_RUNNING
                            resultadoTesteInjetor.postValue(resultadoDeTeste)
                            setChanged()
                            notifyObservers(resultadoDeTeste)
                            stepIndex++
                            if (!testeEmExecucao) {
                                Log.w(tagLog, "Controle: Inicia Teste")
//                                    commands.repository.commandControle()?.iniciaTeste()
                                testeEmExecucao = true
                            }
                        }
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING) {
                        if (stepIndex < heatingStep.size) {
                            timeStep =
                                ((pointTestInjector.timeTest * heatingStep[stepIndex].step) * 1000).toLong()
                            if (resultadoDeTeste.testTime > timeStep) {
                                resultadoDeTeste.status = EnumTestStatus.TEST_PARAMETERIZING
                                resultadoTesteInjetor.postValue(resultadoDeTeste)
                                setChanged()
                                notifyObservers(resultadoDeTeste)
                            }
                        }
//                        respostaControle =
//                            RespostaTesteControle(commands.repository.commandControle()?.getTesteData(1))
//                        setLogInfo(respostaControle.toString())
                        //resultadoDeTeste.pressao = respostaControle.getPressao().toLong()
                        println("Pressao: ${resultadoDeTeste.pressure} - Index($stepIndex) - " +
                                "Tempo: ${"%.1f".format(resultadoDeTeste.testTime.toDouble() / 1000.0)} /" +
                                " ${"%.1f".format(timeStep.toDouble() / 1000.0)}")

//                        if (resultadoDeTeste.statusDoTeste == StatusTeste.TESTE_CANCELADO) {
//                            commands.cancelarTeste()
//                        }
//                        if (respostaControle.statusDoTeste in arrayOf(StatusTeste.TESTE_FINALIZADO,
//                                StatusTeste.TESTE_ERROR, StatusTeste.TESTE_CANCELADO)) {
//                            finalizarTeste()
//                        }
                        delay(500)
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_PAUSED) {
                        pressureDemo.stop()
                        countTest.stop()
                        pause()
                        resultadoDeTeste.pressure = 0
                        resultadoTesteInjetor.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                    }
                    if (resultadoDeTeste.status in
                        arrayOf(
                            EnumTestStatus.TEST_CANCELLED,
                            EnumTestStatus.TEST_FAIL,
                            EnumTestStatus.TEST_SKIPPED,
                            EnumTestStatus.TEST_FINISHED)) {
                        finished = true
                        pressureDemo.stop()
                        countTest.stop()
                        resultadoDeTeste.pressure = 0
                        resultadoTesteInjetor.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        delay(2000)
                        finish()
                    }
                } //else setLogInfo("Paused")
            } //else setLogInfo("Not started")
        }
        Log.i(tagLog, "Finished job")
    }

    @Synchronized
    override fun startTest() {
        Log.i(tagLog,"startTest")
        testeEmExecucao = false
        resultadoDeTeste.testTime = 0
        resultadoDeTeste.status = EnumTestStatus.TEST_STARTING
        stepIndex = 0
        timeStep = 0
        resultadoDeTeste.desiredPressure = heatingStep[stepIndex].pressure
        resultadoDeTeste.pressure = pressureDemo.pressure.value ?: 0
        resultadoTesteInjetor.postValue(resultadoDeTeste)
        setChanged()
        notifyObservers(resultadoDeTeste)
    }

    override fun start() {
        countTest.start()
        super.start()
        pressureDemo.start(heatingStep[stepIndex].pressure)
        start()
    }

    @Synchronized
    override fun cancelTest() {
        Log.i(tagLog,"cancelTest")
        resultadoDeTeste.status = EnumTestStatus.TEST_CANCELLED
    }

    @Synchronized
    override fun skipTest() {
        Log.i(tagLog,"skipTest")
        resultadoDeTeste.status = EnumTestStatus.TEST_SKIPPED
    }

    @Synchronized
    override fun pauseTest() {
        Log.i(tagLog,"pauseTest")
        resultadoDeTeste.status = EnumTestStatus.TEST_PAUSED
    }

    override fun update(observable: Observable?, value: Any?) {
        if ((observable is PressureDemo) && (value is Long)) {
            if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING) {
                resultadoDeTeste.pressure = value
                resultadoTesteInjetor.postValue(resultadoDeTeste)
                setChanged()
                notifyObservers(resultadoDeTeste)
            }
        }
    }
}