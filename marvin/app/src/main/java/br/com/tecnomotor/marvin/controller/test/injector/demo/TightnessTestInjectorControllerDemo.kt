package br.com.tecnomotor.marvin.controller.test.injector.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.injector.result.TightnessTestResult
import br.com.tecnomotor.commonrail.device.utils.EnumLeakTestCondition
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import br.com.tecnomotor.marvin.utils.CountUpTimer
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class TightnessTestInjectorControllerDemo(): ControllerTest() {

    private var tagLog = this::class.java.simpleName
    private var resultadoDeTeste = TightnessTestResult(EnumTestStatus.TEST_STARTING)
    val TightnessTestResult: MutableLiveData<TightnessTestResult> = MutableLiveData(resultadoDeTeste)

    var count = object: CountUpTimer(1000, 10000) {
        override fun onTick(millisFinished: Long) {}

        override fun onFinish() {
            if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING)
            resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
        }
    }

    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while (!finished) {
            if (started) {
                if (!paused) {
//                    if (resultadoDeTeste.state == EnumTestState.TESTE_LIGAR_BOMBAS) {
//                        delay(500)
//                        resultadoDeTeste.state = EnumTestState.TEST_PARAMETERIZING
//                        continue
//                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_PARAMETERIZING) {
                        delay(500)
                        resultadoDeTeste.status = EnumTestStatus.TEST_STARTING
                        TightnessTestResult.postValue(resultadoDeTeste)
                        continue
                    }

                    if (resultadoDeTeste.status == EnumTestStatus.TEST_STARTING) {
                        delay(500)
                        resultadoDeTeste.status = EnumTestStatus.TEST_RUNNING
                        TightnessTestResult.postValue(resultadoDeTeste)
                    }

                    if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING) {
                        delay(500)
                        TightnessTestResult.postValue(resultadoDeTeste)
                    }
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_FINISHED) {
                        resultadoDeTeste.condition = EnumLeakTestCondition.OK
                        TightnessTestResult.postValue(resultadoDeTeste)
                        finished = true
                        continue
                    }
                } //else setLogInfo("Paused")
            } //else setLogInfo("Not started")
        }
        Log.i(tagLog, "Finished job")
    }

    override fun startTest() {
        resultadoDeTeste = TightnessTestResult(EnumTestStatus.TEST_STARTING)
        TightnessTestResult.postValue(resultadoDeTeste)
        count.start()
        this.start()
    }

    override fun cancelTest() {
        GlobalScope.launch(Dispatchers.IO) {
            finish()
            resultadoDeTeste.condition = EnumLeakTestCondition.SKIPPED
            resultadoDeTeste.status = EnumTestStatus.TEST_CANCELLED
            TightnessTestResult.postValue(resultadoDeTeste)
            count.stop()
        }
    }

    override fun skipTest() {
        GlobalScope.launch(Dispatchers.IO) {
            finish()
            resultadoDeTeste.condition = EnumLeakTestCondition.SKIPPED
            resultadoDeTeste.status = EnumTestStatus.TEST_SKIPPED
            TightnessTestResult.postValue(resultadoDeTeste)
            count.stop()
        }
    }

    override fun pauseTest() {
        GlobalScope.launch(Dispatchers.IO) {
            finish()
            resultadoDeTeste.status = EnumTestStatus.TEST_PAUSED
            TightnessTestResult.postValue(resultadoDeTeste)
            count.stop()
        }
    }

//    override fun finishTest() {
//        GlobalScope.launch(Dispatchers.IO) {
//            count.stop()
//            resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
//            TightnessTestResult.postValue(resultadoDeTeste)
//        }
//    }
}