package br.com.tecnomotor.marvin.controller.test.injector

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.commands.injector.TightnessTestCommands
import br.com.tecnomotor.commonrail.device.commands.injector.response.TestResponse
import br.com.tecnomotor.commonrail.device.commands.injector.result.TightnessTestResult
import br.com.tecnomotor.commonrail.device.utils.EnumLeakTestCondition
import br.com.tecnomotor.commonrail.device.utils.measurement.TestMeasurement
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import kotlinx.coroutines.*

/**
 * Para os status: INICIANDO E EXECUTANDO, exibir um aguarde
 * Para o status de erro, resultado em vermelho e não passa para o próximo teste
 * Para o status de finalizado, verificar condição, se OK resultado em verde se não em vermelho
 * Para o status de atenção, verificar condição, se OK resultando em laranja e exibir mensagem de erro, se não em vermelho
 */
@InternalCoroutinesApi
@DelicateCoroutinesApi
class TightnessTestInjectorController(
    private var commands: TightnessTestCommands,
    private var pontoDeTeste: HashMap<String, ByteArray>
) : ControllerTest() {

    private var tagLog = this::class.java.simpleName
    private var testMeasurement: TestMeasurement = TestMeasurement()
    private var testResult = TightnessTestResult(EnumTestStatus.TEST_STARTING)
    val TightnessTestResult: MutableLiveData<TightnessTestResult> = MutableLiveData(testResult)
    private lateinit var testResponse: TestResponse

    fun setPontoDeTeste(value: HashMap<String, ByteArray>) {
        this.pontoDeTeste = value
    }

    private fun verificaErroMedicao() {
        testResult.error = ECommonRailCommandException(testMeasurement.error)
        when (testMeasurement.error) {
            ECommonRailCommandException.REMOTE_ERROR_MEASURE_TIMEOUT_CH1,
            ECommonRailCommandException.REMOTE_ERROR_MEASURE_TIMEOUT_CH2,
            ECommonRailCommandException.REMOTE_ERROR_DEAD_VOLUME_TIMEOUT_CH1,
            ECommonRailCommandException.REMOTE_ERROR_DEAD_VOLUME_TIMEOUT_CH2 ->
                testResult.status = EnumTestStatus.TEST_WARNING
            ECommonRailCommandException.REMOTE_ERROR_LEAK_BACK_FLOW_FAIL,
            ECommonRailCommandException.REMOTE_ERROR_LEAK_INJECTION_FAIL -> {
                testResult.status = EnumTestStatus.TEST_FINISHED
                testMeasurement.status = EnumTestStatus.TEST_FAIL
            }
        }
    }

    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while (!finished) {
            if (started) {
                if ((!paused) && (commands.repository.controlIsOk()) && (commands.repository.measurementIsOk())) {
//                    if (resultadoDeTeste.state == EnumTestState.TESTE_LIGAR_BOMBAS) {
//                        commands.repository.commandControle()?.ligarBombas()
//                        resultadoDeTeste.state = EnumTestState.TEST_PARAMETERIZING
//                        continue
//                    }
                    if (testResult.status == EnumTestStatus.TEST_PARAMETERIZING) {
                        if (commands.canParameterize())
                            if (commands.parameterizeTest(pontoDeTeste)) {
                                commands.startTest()
                                testResult.status = EnumTestStatus.TEST_STARTING
                            } else {
                                testResult.status = EnumTestStatus.TEST_FAIL
                                testResult.error = ECommonRailCommandException(
                                    ECommonRailCommandException.ERROR_PARAMETRIZATION
                                )
                                TightnessTestResult.postValue(testResult)
                                finished = true
                            }
                        continue
                    }
                    testResponse =
                        TestResponse(commands.repository.controlCommands()?.getTestValues())
                    Log.i(tagLog, testResponse.toString())

                    //testMeasurement.parser(commands.repository.measurementCommands()!!.getTestValues())
                    Log.i(tagLog, testMeasurement.toString())

                    if (testResponse.status == EnumTestStatus.TEST_WAITING)
                        continue
                    if (testMeasurement.status in arrayOf(EnumTestStatus.TEST_STARTING, EnumTestStatus.TEST_RUNNING))
                        testResult.status = EnumTestStatus.TEST_RUNNING

                    if (testResult.status == EnumTestStatus.TEST_RUNNING) {
                        verificaErroMedicao()
                        TightnessTestResult.postValue(testResult)
                        if (testMeasurement.status in arrayOf(EnumTestStatus.TEST_FINISHED, EnumTestStatus.TEST_FAIL)) {
                            testResult.status = EnumTestStatus.TEST_FINISHED
                        }
                    }
                    if (testResponse.status == EnumTestStatus.TEST_WARNING) {
                        testResult.warning = true
                        testResult.error = testResponse.error
                        TightnessTestResult.postValue(testResult)
                        commands.finishTest()
                        finished = true
                        continue
                    }
                    if (testResult.status == EnumTestStatus.TEST_FINISHED) {
                        if (testMeasurement.status != EnumTestStatus.TEST_FAIL) {
                            if (testMeasurement.error in
                                arrayOf(
                                    ECommonRailCommandException.REMOTE_ERROR_LEAK_BACK_FLOW_FAIL,
                                    ECommonRailCommandException.REMOTE_ERROR_LEAK_INJECTION_FAIL
                                )
                            ) {
                                testResult.status = EnumTestStatus.TEST_FAIL
                                testResult.condition = EnumLeakTestCondition.LEAK
                            } else
                                testResult.condition = EnumLeakTestCondition.OK
                        } else {
                            testResult.status = EnumTestStatus.TEST_FAIL
                            testResult.condition = EnumLeakTestCondition.LEAK
                        }
                        TightnessTestResult.postValue(testResult)
                        commands.finishTest()
                        finished = true
                        continue
                    }
                    //Verificando erros ------------------------------------------------------------
                    if (testResponse.status == EnumTestStatus.TEST_FAIL) {
                        testResult.status = testResponse.status
                        testResult.error = testResponse.error
                        TightnessTestResult.postValue(testResult)
                        commands.finishTest()
                        finished = true
                        continue
                    }
                    if (testMeasurement.status == EnumTestStatus.TEST_FAIL) {
                        testResult.status = testMeasurement.status
                        testResult.error = ECommonRailCommandException(testMeasurement.error)
                        TightnessTestResult.postValue(testResult)
                        commands.finishTest()
                        finished = true
                        continue
                    }
                    //------------------------------------------------------------------------------
                } //else setLogInfo("Paused")
            } //else setLogInfo("Not started")
        }
        Log.i(tagLog, "Finished job")
    }

    override fun startTest() {
        testResult = TightnessTestResult(EnumTestStatus.TEST_STARTING)
        TightnessTestResult.postValue(testResult)
        this.start()
    }

    override fun cancelTest() {
        GlobalScope.launch(Dispatchers.IO) {
            commands.cancelTest()
            finish()
            testResult.condition = EnumLeakTestCondition.SKIPPED
            testResult.status = EnumTestStatus.TEST_CANCELLED
            TightnessTestResult.postValue(testResult)
        }
    }

    override fun skipTest() {
        GlobalScope.launch(Dispatchers.IO) {
            commands.finishTest()
            finish()
            testResult.condition = EnumLeakTestCondition.SKIPPED
            testResult.status = EnumTestStatus.TEST_SKIPPED
            TightnessTestResult.postValue(testResult)
        }
    }

    override fun pauseTest() {
        GlobalScope.launch(Dispatchers.IO) {
            commands.pauseTest()
            finish()
            testResult.status = EnumTestStatus.TEST_PAUSED
            TightnessTestResult.postValue(testResult)
        }
    }

//    override fun finishTest() {
//        GlobalScope.launch(Dispatchers.IO) {
//            commands.finishTest()
//            testResult.status = EnumTestStatus.TEST_FINISHED
//        }
//    }
}