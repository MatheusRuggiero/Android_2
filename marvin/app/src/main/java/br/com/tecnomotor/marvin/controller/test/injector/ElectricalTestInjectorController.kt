package br.com.tecnomotor.marvin.controller.test.injector

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.injector.InjectorCommands
import br.com.tecnomotor.commonrail.device.commands.injector.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
open class ElectricalTestInjectorController(
    val commands: InjectorCommands
) : ControllerTest() {

    private var tagLog = this::class.java.simpleName
    private var resultadoDeTeste = ElectricalTestResult(EnumTestStatus.NONE)
    val resultTestElectric: MutableLiveData<ElectricalTestResult> = MutableLiveData(resultadoDeTeste)


    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while (!finished) {
            if (started) {
                if (!paused) {
                    commands.startCommunication()
                    resultadoDeTeste.deepCopy(commands.performElectricalTest())
                    resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
                    resultTestElectric.postValue(resultadoDeTeste)
                    finished = true
                } //else setLogInfo("Paused")
            } //else setLogInfo("Not started")
        }
        Log.i(tagLog,"Finished job")
    }

    override fun startTest() {
        resultadoDeTeste = ElectricalTestResult(EnumTestStatus.NONE)
        resultadoDeTeste.status = EnumTestStatus.TEST_STARTING
        resultTestElectric.postValue(resultadoDeTeste)
        this.start()
    }

    override fun cancelTest() {
        resultadoDeTeste.status = EnumTestStatus.TEST_CANCELLED
        resultTestElectric.postValue(resultadoDeTeste)
        this.stop()
    }

    override fun pauseTest() { }

    override fun skipTest() { //Pular o teste
        this.stop()
        resultadoDeTeste.status = EnumTestStatus.TEST_SKIPPED
        resultTestElectric.postValue(resultadoDeTeste)
    }

}