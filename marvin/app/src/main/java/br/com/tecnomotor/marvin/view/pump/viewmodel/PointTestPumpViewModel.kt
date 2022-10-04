package br.com.tecnomotor.marvin.view.pump.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.test.pump.PointTestPumpController
import br.com.tecnomotor.marvin.controller.test.pump.TestPointsResult
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.utils.CountUpTimer
import br.com.tecnomotor.marvin.utils.TimeTest
import kotlinx.coroutines.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
class PointTestPumpViewModel(
    pointTestPumpList: MutableList<PointTestPump>,
    private var setPointInView: (index: Int) -> Unit = {},
    pump: Pump
) : ViewModel() {
    private var tagLog = this::class.java.simpleName
    private lateinit var pointTestPumpTestController: PointTestPumpController
    var pointTestPumpResultLiveData = MediatorLiveData<TestPointsResult>()    // MutableLiveData<TestPointsResult> = MutableLiveData()
    lateinit var testPointsResult: TestPointsResult
        private set
    private var timerJob: CountUpTimer? = null // relacionado ao tempo total do teste

    init {
        if (!this::pointTestPumpTestController.isInitialized) {
            testPointsResult = TestPointsResult(pointTestPumpList)
            pointTestPumpTestController = PointTestPumpController(
                testPointsResult.getItem().pointTest,
                pump
            )
            pointTestPumpResultLiveData
                .addSource(pointTestPumpTestController.rotationResultValues) { rotationResult ->
                    val pointTestResult = testPointsResult.getItem().pointTestPumpResult
                    pointTestResult.status = rotationResult.status
                    pointTestResult.error = rotationResult.error
                    pointTestResult.rotation = rotationResult.rotation
                    pointTestResult.desiredRotation = rotationResult.desiredRotation
                    pointTestResult.checkRotation = true
                    when (rotationResult.status) {
                        EnumTestStatus.TEST_CANCELLED -> {
                            stoptestTime()
                            pointTestResult.checkRotation = false
                        }
                        EnumTestStatus.TEST_SKIPPED -> {
                            checkIsNewTestPoint()
                            pointTestResult.checkRotation = false
                        }
                        else -> {}
                    }
                    testPointsResult.updateResult(pointTestResult)
                    pointTestPumpResultLiveData.postValue(testPointsResult)
                }

            pointTestPumpResultLiveData
                .addSource(pointTestPumpTestController.pointTestPumpResultValues) { pointTestPumpResult ->
                    testPointsResult.updateResult(pointTestPumpResult)
                    when (pointTestPumpResult.status) {
                        EnumTestStatus.TEST_CANCELLED,
                        EnumTestStatus.TEST_FAIL -> stoptestTime()
                        EnumTestStatus.TEST_PAUSED -> pauseTestTime()
                        EnumTestStatus.TEST_SKIPPED, EnumTestStatus.TEST_FINISHED -> checkIsNewTestPoint()
                        else -> {}
                    }
                    pointTestPumpResultLiveData.postValue(testPointsResult)
                }
            timerJob = TimeTest.getTimeTest(0, this::timeOnTick, this::timeOnFinish)
        }
    }

    private fun checkIsNewTestPoint() {
        Log.i(tagLog, "Checks if it is a new test point")
        GlobalScope.launch(Dispatchers.IO) {
            while(!pointTestPumpTestController.isFinished()) delay(100)
            delay(2000) // tempo de ver a mensagem finalizando
            if (testPointsResult.isNewTestPoint()) {
                testPointsResult.nextPoint()
                setPointInView(testPointsResult.indexSelected)
                pointTestPumpTestController.setPointTestPump(testPointsResult.getItem().pointTest)
                startTest()
            } else {
                stoptestTime()
                testPointsResult.allTestFinished = true
                pointTestPumpResultLiveData.postValue(testPointsResult)
            }
        }
    }

    private fun startTestTime() {
        if (timerJob?.isStarted() == true)
            timerJob?.continueCount()
        else
            timerJob?.start()
    }

    private fun pauseTestTime() {
        timerJob?.pause()
    }

    private fun stoptestTime() {
        timerJob?.stop()
    }

    @Synchronized
    private fun timeOnTick(millisFinished: Long) {
        val timeInSeconds = (millisFinished / 1000)
        if (timeInSeconds != testPointsResult.testTime) {
            testPointsResult.testTime = timeInSeconds
            pointTestPumpResultLiveData.postValue(testPointsResult)
        }
    }

    private fun timeOnFinish() {}

    fun startTest() {
        if (::pointTestPumpTestController.isInitialized) {
            startTestTime()
            pointTestPumpTestController.startTest()
        }
    }

    fun cancelTest() {
        if (::pointTestPumpTestController.isInitialized) {
            pointTestPumpTestController.cancelTest()
        }
    }

    fun pauseTest() {
        if (::pointTestPumpTestController.isInitialized) {
            pointTestPumpTestController.pauseTest()
        }
    }

    /**
     * Pula ponto de teste enquanto está executando
     */
    fun skipTest() {
        if (::pointTestPumpTestController.isInitialized) {
            pointTestPumpTestController.skipTest()
        }
    }

    /**
     * Repete teste de rotação
     */
    fun repeatRotation() {
        pointTestPumpTestController.repeatRotation()
    }

    /**
     * Pula verificação de rotação
     */
    fun skipRotation() {
        if (::pointTestPumpTestController.isInitialized) {
            pointTestPumpTestController.skipRotation()
        }
    }

    fun continuarTeste() {
        if (::pointTestPumpTestController.isInitialized) {
            pointTestPumpTestController.continuarTest()
        }
    }

    fun isFinished() = pointTestPumpTestController.isFinished()
}