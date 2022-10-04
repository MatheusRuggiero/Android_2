package br.com.tecnomotor.marvin.controller.test.pump.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.pump.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.SingleElectricalTestResult
import br.com.tecnomotor.commonrail.device.utils.control.ElectricalTestCondition
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ElectricalTestPumpControllerDemo(): ControllerTest() {

    private var tagLog = this::class.java.simpleName
    private var resultadoDeTeste = ElectricalTestResult(EnumTestStatus.NONE)
    val resultTestElectric: MutableLiveData<ElectricalTestResult> = MutableLiveData(resultadoDeTeste)

    private var resistenciasDemo = arrayOf(0.21, 0.29, 0.25, 0.32, 0.23, 0.28)

    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while (!finished) {
            if (started) {
                if (!paused) {
                    delay(500)
                    resultadoDeTeste.testResult.add(
                        SingleElectricalTestResult(
                            EnumTestStatus.TEST_FINISHED,
                            EnumControlTypeTest.VALVE_ELECTRIC_TEST,
                            ElectricalTestCondition.OK,resistenciasDemo[(resistenciasDemo.indices).random()]))
                    resultTestElectric.postValue(resultadoDeTeste)
                    delay(500)
                    resultadoDeTeste.testResult.add(
                        SingleElectricalTestResult(
                            EnumTestStatus.TEST_FINISHED,
                            EnumControlTypeTest.VALVE_ELECTRIC_TEST,
                            ElectricalTestCondition.OK,resistenciasDemo[(resistenciasDemo.indices).random()]))
                    resultTestElectric.postValue(resultadoDeTeste)
                    delay(500)
                    resultadoDeTeste.testResult.add(
                        SingleElectricalTestResult(
                            EnumTestStatus.TEST_FINISHED,
                            EnumControlTypeTest.VALVE_ELECTRIC_TEST,
                            ElectricalTestCondition.OK,resistenciasDemo[(resistenciasDemo.indices).random()]))
                    resultTestElectric.postValue(resultadoDeTeste)
//                    delay(500)
//                    resultadoDeTeste.condicaoD = CondicaoDoTesteEletrico.OK
//                    resultadoDeTeste.resistenciaD = resistenciasDemo[(resistenciasDemo.indices).random()]
//                    resultTestElectric.postValue(resultadoDeTeste)
                    delay(500)
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

//    override fun finishTest() {
//        resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
//        resultTestElectric.postValue(resultadoDeTeste)
//        finished = true
//    }

}