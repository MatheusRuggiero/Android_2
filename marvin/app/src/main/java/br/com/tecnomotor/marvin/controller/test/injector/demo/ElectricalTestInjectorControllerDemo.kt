package br.com.tecnomotor.marvin.controller.test.injector.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.injector.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.utils.control.ElectricalTestCondition
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ElectricalTestInjectorControllerDemo(): ControllerTest()
{
    private var tagLog = this::class.java.simpleName
    private var resultadoDeTeste = ElectricalTestResult(EnumTestStatus.NONE)
    val resultTestElectric: MutableLiveData<ElectricalTestResult> = MutableLiveData(resultadoDeTeste)

    init {
        resultadoDeTeste.conditionA = ElectricalTestCondition.NONE
        resultadoDeTeste.resistanceA = 0.00
        resultadoDeTeste.conditionB = ElectricalTestCondition.NONE
        resultadoDeTeste.resistanceB = 0.00
    }

    private var resistenciasDemo = arrayOf(0.21, 0.29, 0.25, 0.32, 0.23, 0.28)

    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while (!finished) {
            if (started) {
                if (!paused) {
                    delay(2000)
                    resultadoDeTeste.conditionA = ElectricalTestCondition.OK
                    resultadoDeTeste.resistanceA = resistenciasDemo[(0..5).random()]
                    resultadoDeTeste.conditionB = ElectricalTestCondition.OK
                    resultadoDeTeste.resistanceB = resistenciasDemo[(0..5).random()]
                    resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
                    resultTestElectric.postValue(resultadoDeTeste)
                    finished = true
                } //else setLogInfo("Paused")
            } //else setLogInfo("Not started")
        }
        Log.i(tagLog, "Finished job")
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