package br.com.tecnomotor.marvin.controller.test.injector.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.injector.result.ConditioningResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import br.com.tecnomotor.marvin.controller.test.demo.PressureDemo
import br.com.tecnomotor.marvin.utils.CountUpTimer
import kotlinx.coroutines.*
import java.util.*

@DelicateCoroutinesApi
class MeterConditioningControllerDemo(): ControllerTest(), Observer {

    private var tagLog = this::class.java.simpleName
    private var resultadoDeTeste = ConditioningResult(EnumTestStatus.TEST_STARTING)
    val conditioningResult: MutableLiveData<ConditioningResult> = MutableLiveData(resultadoDeTeste)
    private var countTest: CountUpTimer
    private var pressure: PressureDemo

    init {
        pressure = PressureDemo(0)
        pressure.addObserver(this)

        @Synchronized
        countTest = object: CountUpTimer(1000, resultadoDeTeste.timeTest) {
            override fun onTick(millisFinished: Long) {
                if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING) {
                    Log.w(tagLog, "Tempo de condicionamento: ${(millisFinished / 1000).toInt()}")
                    resultadoDeTeste.currentTestTime = millisFinished
                }
            }

            override fun onFinish() {
                Log.w(tagLog, "Condicionamento finalizado: $resultadoDeTeste")
                if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING)
                    resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
            }
        }
    }

    /**
     *   PontoDeTeste: HashMap<String, ByteArray>
     *   timeOn: Int,
     *   timeOff: Int,
     *   timeHigh: Int,
     *   timeIHigh: Int,
     *   timeHoldOff: Int,
     *   lowVoltage: Int,
     *   highVoltage: Int
     */
    @Synchronized
    private suspend fun parametrizaCondicionamento():Boolean {
        var res: Boolean = true
        /*
        val parametros = byteArrayOf(
            0x01.toByte(),                                      //Injetor
            0x00.toByte(),                                      //Canal
            0x01.toByte(),                                      //InjectionFlag: Injecao ativa
            0x00.toByte(),                                      //InjectionMsrFlag: Nao medindo
            0x00.toByte(),                                      //ReturnMsrFlag: Nao medindo
            (resultadoDeTeste.pressaoDesejada shr(8)).toByte(),   //Pressao
            resultadoDeTeste.pressaoDesejada.toByte(),
            (resultadoDeTeste.timeout shr(8)).toByte(),           //Timeout
            resultadoDeTeste.timeout.toByte(),
            (resultadoDeTeste.frequencia shr(8)).toByte(),        //Frequencia
            resultadoDeTeste.frequencia.toByte(),
            (resultadoDeTeste.tempoDeInjecao shr(8)).toByte(),     //Tempo de injeção
            resultadoDeTeste.tempoDeInjecao.toByte()) +
            pontoDeTeste["tempoLigado"]!! +
            pontoDeTeste["tempoDesligado"]!! +
            pontoDeTeste["tempoDoSinalEmAlta"]!! +
            pontoDeTeste["tempo12Volts"]!! +
            pontoDeTeste["preEncherMedidoInjecao"]!! +
            pontoDeTeste["tempoDoSinalDesligado"]!! +
            pontoDeTeste["tensaoBaixa"]!! +
            pontoDeTeste["tensaoAlta"]!!
        res = (res && (commands.repository.commandControle()?.setParametros(parametros) ?: false))
        if (res) {
            commands.repository.commandControle()
                ?.sendCommand(byteArrayOf(0x26.toByte(), 0x0D.toByte()))
            res = (res && (commands.repository.commandMedicao()?.condicionamento() ?: false))
        }
         */
        return res
    }

    @Synchronized
    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while(!finished) {
            if (started) {
                if (!paused) {
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_STARTING) {
                        resultadoDeTeste.status = EnumTestStatus.TEST_PARAMETERIZING
                        delay(1000)
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_PARAMETERIZING) {
                        if (parametrizaCondicionamento()) {
                            countTest.start(resultadoDeTeste.timeTest)
                            pressure.start(resultadoDeTeste.desiredPressure)
                            resultadoDeTeste.status = EnumTestStatus.TEST_RUNNING
                            conditioningResult.postValue(resultadoDeTeste)
                            setChanged()
                            notifyObservers(resultadoDeTeste)
                        }
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_CANCELLED) {
                        countTest.stop()
                        pressure.stop()
                        conditioningResult.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        finish()
                        continue
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_PAUSED) {
                        countTest.stop()
                        pressure.stop()
                        conditioningResult.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        pause()
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_SKIPPED) {
                        Log.d(tagLog, "TESTE_PULADO")
                        countTest.stop()
                        pressure.stop()
                        conditioningResult.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        delay(1000)
                        finish()
                        continue
                    }

                    if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING) {
                        /*
                        resultadoDeTeste.pressaoAtual = respostaControle.getPressao()
                        resultadoDeTeste.temperatura = respostaControle.getTemperatura()
                        resultadoDeTeste.altaVoltagem = respostaControle.getAltaVoltagem()
                        resultadoDeTeste.limiarCH1 = respostaMedicao.getLimiarCH1()
                        resultadoDeTeste.falhaCH1 = respostaMedicao.getFalhasCH1()
                        resultadoDeTeste.limiarCH2 = respostaMedicao.getLimiarCH2()
                        resultadoDeTeste.falhaCH2 = respostaMedicao.getFalhasCH2()
                         */
                        conditioningResult.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        delay(1000)
                    }

                    if (resultadoDeTeste.status in
                            arrayOf(
                                EnumTestStatus.TEST_FINISHED,
                                    EnumTestStatus.TEST_FAIL)
                    ) {
                        countTest.stop()
                        pressure.stop()
                        conditioningResult.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        delay(1000)
                        finish()
                    }

                } //else setLogInfo("Paused")
            } //else setLogInfo("Not started")
        }
        Log.i(tagLog, "Finished job")
    }

    @Synchronized
    override fun startTest() {
        Log.i(tagLog, "startTest")
        resultadoDeTeste.status = EnumTestStatus.TEST_STARTING
        conditioningResult.postValue(resultadoDeTeste)
        setChanged()
        notifyObservers(resultadoDeTeste)
        start()
    }

    @Synchronized
    override fun cancelTest() {
        Log.i(tagLog, "cancelTest")
        resultadoDeTeste.status = EnumTestStatus.TEST_CANCELLED
    }

    @Synchronized
    override fun skipTest() {
        Log.i(tagLog, "skipTest")
        resultadoDeTeste.status = EnumTestStatus.TEST_SKIPPED
    }

    @Synchronized
    override fun pauseTest() {
        Log.i(tagLog, "pauseTest")
        resultadoDeTeste.status = EnumTestStatus.TEST_PAUSED
    }

//    @Synchronized
//    override fun finishTest() {
//        Log.i(tagLog, "finishTest")
//        resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
//    }

    @Synchronized
    override fun update(observable: Observable?, value: Any?) {
        if (observable is PressureDemo)
            if ((value != null) && (value is Long)) {
                    resultadoDeTeste.pressure = value
            }
    }
}