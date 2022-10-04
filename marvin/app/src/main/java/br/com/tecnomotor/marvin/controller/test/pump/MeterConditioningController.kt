package br.com.tecnomotor.marvin.controller.test.pump

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import br.com.tecnomotor.marvin.controller.test.demo.PressureDemo
import br.com.tecnomotor.marvin.utils.CountUpTimer
import kotlinx.coroutines.*
import java.util.*

@DelicateCoroutinesApi
class MeterConditioningController(): ControllerTest(), Observer {

    private var tagLog = this::class.java.simpleName
    private var resultadoDeTeste = TestResult(EnumTestStatus.TEST_STARTING)
    val resultadoTesteCondicionamento: MutableLiveData<TestResult> = MutableLiveData(resultadoDeTeste)
    private var countTest: CountUpTimer

    init {
//        pressure = PressureDemo(0)
//        pressure.addObserver(this)

        @Synchronized
        countTest = object: CountUpTimer(1000, 20000) {
            override fun onTick(millisFinished: Long) {
                if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING) {
                    Log.w(tagLog, "Tempo de condicionamento: ${(millisFinished / 1000).toInt()}")
                    //resultadoDeTeste.tempoDeTesteAtual = millisFinished
                }
            }

            override fun onFinish() {
                Log.w(tagLog, "Condicionamento finalizado: $resultadoDeTeste")
                if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING)
                    resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
            }
        }
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
//                        if (parametrizaCondicionamento()) {
                            countTest.start(20000)
//                            pressure.start(resultadoDeTeste.pressaoDesejada)
                            resultadoDeTeste.status = EnumTestStatus.TEST_RUNNING
                            resultadoTesteCondicionamento.postValue(resultadoDeTeste)
                            setChanged()
                            notifyObservers(resultadoDeTeste)
//                        }
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_CANCELLED) {
                        countTest.stop()
//                        pressure.stop()
                        resultadoTesteCondicionamento.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        finish()
                        continue
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_PAUSED) {
                        countTest.stop()
//                        pressure.stop()
                        resultadoTesteCondicionamento.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        pause()
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_SKIPPED) {
                        Log.d(tagLog, "TESTE_PULADO")
                        countTest.stop()
//                        pressure.stop()
                        resultadoTesteCondicionamento.postValue(resultadoDeTeste)
                        setChanged()
                        notifyObservers(resultadoDeTeste)
                        delay(1000)
                        finish()
                        continue
                    }

                    if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING) {
                        resultadoTesteCondicionamento.postValue(resultadoDeTeste)
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
//                        pressure.stop()
                        resultadoTesteCondicionamento.postValue(resultadoDeTeste)
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
        resultadoTesteCondicionamento.postValue(resultadoDeTeste)
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

    @Synchronized
    override fun update(observable: Observable?, value: Any?) {
        if (observable is PressureDemo)
            if ((value != null) && (value is Long)) {
//                resultadoDeTeste.pressao = value
            }
    }
}