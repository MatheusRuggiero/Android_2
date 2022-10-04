package br.com.tecnomotor.marvin.controller.test.pump.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestPumpResult
import br.com.tecnomotor.commonrail.device.utils.measurement.TestMeasurement
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumTestProcessForMeasurement
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.IViewController
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import br.com.tecnomotor.marvin.controller.test.IControllerPointTest
import br.com.tecnomotor.marvin.controller.test.demo.PressureDemo
import br.com.tecnomotor.marvin.controller.test.demo.RotationDemo
import br.com.tecnomotor.marvin.controller.test.demo.RotationResultDemo
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.utils.objects.FormatTestResults
import br.com.tecnomotor.marvin.utils.CountUpTimer
import kotlinx.coroutines.*
import java.util.*
import kotlin.math.floor

@DelicateCoroutinesApi
class PointTestPumpControllerDemo(
    private val pump: Pump,
    private var pointTestPump: PointTestPump,
    private val viewController: IViewController
): ControllerTest(), IControllerPointTest<PointTestPump>, Observer {

    private var tagLog = this::class.java.simpleName
    private var meterConditioning: MeterConditioningControllerDemo? = null
    private var conditioning: Boolean = false
    private var resultadoDeTeste = PointTestPumpResult(EnumTestStatus.NONE)
    val resultadoTesteBomba: MutableLiveData<PointTestPumpResult> = MutableLiveData(resultadoDeTeste)

    /**
     * Tempo total do teste 15Min = 900.000 ms
     */
    val totalTime: Long = 120000
    var totalTestPointTime: Long = (totalTime * 0.2).toLong() // 20%
    var timeAdvanceInMilliseconds: Long = 10

    private var testMeasurement: TestMeasurement = TestMeasurement()

    private var countTest: CountUpTimer
    private var pressure: PressureDemo
    private var rotation: RotationDemo

    private var pumpInc: Double = 0.0
    private var pumpValue: Double = 0.0
    private var pumpValueOk: Double = 0.0
    private var returnInc: Double = 0.0
    private var returnValue: Double = 0.0
    private var returnValueOk: Double = 0.0

    @Synchronized
    fun getCountTest() = object: CountUpTimer(timeAdvanceInMilliseconds) {
        var timeValue: Int = 0
        override fun onTick(millisFinished: Long) {
            val timeInSeconds = (millisFinished / 1000).toInt()
            if (timeInSeconds != timeValue) {
                timeValue = timeInSeconds
                Log.i(tagLog, "Tempo de teste: ${FormatTestResults.FORMAT_TIME_TEST.format(timeValue)}")
            }
            resultadoDeTeste.testTime = millisFinished
            resultadoDeTeste.temperature = (55..56).random()
            resultadoDeTeste.Ext1Current = pointTestPump.currentExt1
            resultadoDeTeste.Ext2Current = pointTestPump.currentExt2
            if (testMeasurement.flagInjecao) {
                pumpValue += pumpInc
                resultadoDeTeste.mainFlow = floor(pumpValue * 100) / 100
                if (resultadoDeTeste.mainFlow >= pumpValueOk) {
                    resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
                    pressure.stop()
                    rotation.finish()
                    this.stop()
                }
            }
            if (testMeasurement.flagRetorno) {
                returnValue += returnInc
                resultadoDeTeste.returnFlow = floor(returnValue * 100) / 100
                if (resultadoDeTeste.returnFlow >= returnValueOk) {
                    resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
                    pressure.stop()
                    rotation.finish()
                    this.stop()
                }
            }
        }
        override fun onFinish() {
            Log.w(tagLog, "Tempo: Finalizado!")
            resultadoDeTeste.testTime = this.millisInFuture / 1000
            timeValue = 0
            if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING)
                resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
        }
    }

    init {
        countTest = getCountTest()
        pressure = PressureDemo(pointTestPump.pressureTest.toLong())
        pressure.addObserver(this)
        rotation = RotationDemo(pointTestPump.rotation.toLong(),8)
        rotation.addObserver(this)

        this.addObserver(this)

        configPressure()
        configIncrementValues()
    }

    private fun configIncrementValues() {
        val randomPercent: Double = ((85..95).random().toDouble() / 100.0)
        Log.i(tagLog, "Percent inc: $randomPercent")
        if (testMeasurement.flagInjecao) {
            pumpValue = 0.0
            pumpValueOk = ((pointTestPump.getMaxFlowMain()) * randomPercent)
            pumpInc = (pumpValueOk / ((totalTestPointTime / timeAdvanceInMilliseconds) * randomPercent))
            Log.w(tagLog, "Vazão principal: inc=$pumpInc; ok=$pumpValueOk")
        } else if (testMeasurement.flagRetorno) {
            returnValueOk = ((pointTestPump.getMaxFlowReturn()) * randomPercent)
            returnInc = (returnValueOk / ((totalTestPointTime / timeAdvanceInMilliseconds) * randomPercent))
            Log.w(tagLog,"Vazão retorno: inc=$returnInc; ok=$returnValueOk")
        }
    }

    private fun configPressure() {
        resultadoDeTeste.frequencyExt1 = pointTestPump.frequencyExt1
        resultadoDeTeste.frequencyExt2 = pointTestPump.frequencyExt2
        resultadoDeTeste.Ext1Current = pointTestPump.currentExt1
        resultadoDeTeste.Ext2Current = pointTestPump.currentExt2
        resultadoDeTeste.testTime = 0
        resultadoDeTeste.desiredPressure = pointTestPump.pressureTest.toLong()
        resultadoDeTeste.desiredRotation = pointTestPump.rotation.toLong()
    }

    @Synchronized
    override fun setNextPoint(pointTest: PointTestPump) {
        Log.w(tagLog, "setNextPoint: ${pointTest.typePointTest?.description}")
        Log.w(tagLog, pointTest.toString())
        this.pointTestPump = pointTest
        resultadoDeTeste = PointTestPumpResult(EnumTestStatus.NONE)
        resultadoDeTeste.minimumMainFlow = pointTest.getMinFlowMain().toDouble()
        resultadoDeTeste.maximumMainFlow = pointTest.getMaxFlowMain().toDouble()
        resultadoDeTeste.minimumReturnFlow = pointTest.getMinFlowReturn().toDouble()
        resultadoDeTeste.maximumReturnFlow = pointTest.getMaxFlowReturn().toDouble()
        resultadoDeTeste.mainFlow = 0.0
        resultadoDeTeste.returnFlow = 0.0

        configPressure()

        testMeasurement.loadParametrizacao(
            EnumTestProcessForMeasurement.Pump,
            pointTest.frequencyExt1,
            pointTest.measureMain,
            pointTest.measureReturn,
            pointTest.getMaxFlowMain().toDouble(),
            pointTest.getMaxFlowReturn().toDouble()
        )
        Log.w(tagLog, testMeasurement.toString())
    }

    @Synchronized
    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while(!finished) {
            if (started) {
                if (!paused) {
                    // Ponto de teste
                    Log.d(tagLog, "Running ${pointTestPump.typePointTest?.description} (${resultadoDeTeste.status})")
                    when (resultadoDeTeste.status) {
                        EnumTestStatus.TEST_STARTING -> {
                            resultadoTesteBomba.postValue(resultadoDeTeste)
                            delay(1000)
                            resultadoDeTeste.status = EnumTestStatus.TEST_RUNNING
                            rotation.start(resultadoDeTeste.desiredRotation, 5000)
                            resultadoDeTeste.checkRotation = true
                        }
                        EnumTestStatus.TEST_RUNNING -> {
                            if (!resultadoDeTeste.checkRotation) {
                                resultadoTesteBomba.postValue(resultadoDeTeste)
                                delay(500)
                            } else pause()
                        }
                        EnumTestStatus.TEST_PAUSED -> {
                            pause()
                            resultadoDeTeste.pressure = 0
                            resultadoDeTeste.rotation = 0
                            resultadoTesteBomba.postValue(resultadoDeTeste)
                        }
                        EnumTestStatus.TEST_CANCELLED -> {
                            resultadoDeTeste.pressure = 0
                            resultadoDeTeste.rotation = 0
                            resultadoTesteBomba.postValue(resultadoDeTeste)
                            finish()
                        }
                        EnumTestStatus.TEST_SKIPPED -> {
                            pause()
                            resultadoDeTeste.pressure = 0
                            resultadoDeTeste.rotation = 0
                            resultadoTesteBomba.postValue(resultadoDeTeste)
                            delay(2000)
                            setChanged()
                            notifyObservers(resultadoDeTeste)
                        }
                        EnumTestStatus.TEST_FINISHED -> {
                            resultadoTesteBomba.postValue(resultadoDeTeste)
                            delay(2000)
                            if ((testMeasurement.flagInjecao) && (testMeasurement.medirRetorno)) {
                                testMeasurement.mudarDeCanal()
//                                meterConditioning = MeterConditioningControllerDemo(tempoDeTesteCondicionamento)
//                                meterConditioning!!.addObserver(this@PointTestPumpControllerDemo)
//                                meterConditioning!!.startTest()
//                                meterConditioning!!.executeTest()
//                                paused = true

                                pause()
                                Log.d(tagLog, "Retorno")
                                resultadoDeTeste.status = EnumTestStatus.TEST_STARTING
                                //configPressure() não vai ter isso no teste de bombas
                                configIncrementValues()
                                rotation.start(resultadoDeTeste.desiredRotation, 0, resultadoDeTeste.desiredRotation-1)
                                countTest.start(totalTestPointTime)
                                if (resultadoDeTeste.desiredPressure > 0)
                                    pressure.start(resultadoDeTeste.desiredPressure)
                                start()
                            } else {
                                pause()
                                countTest.stop()
                                rotation.stop()
                                pressure.stop()
                                GlobalScope.launch(Dispatchers.Main) {
                                    viewController.nextPoint()
                                }
                            }
                            //pause()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    @Synchronized
    override fun startTest() {
        Log.i(tagLog,"startTest")
        resultadoDeTeste.status = EnumTestStatus.TEST_STARTING
        resultadoTesteBomba.postValue(resultadoDeTeste)
        start()
    }

    @Synchronized
    override fun cancelTest() {
        Log.i(tagLog,"cancelTest")
        if (meterConditioning?.isStarted() == true) {
            meterConditioning?.cancelTest()
        } else {
            resultadoDeTeste.status = EnumTestStatus.TEST_CANCELLED
            countTest.stop()
            pressure.stop()
            rotation.finish()
        }
    }

    @Synchronized
    override fun skipTest() {
        if (meterConditioning?.isStarted() == true) {
            Log.i(tagLog,"skipTest Condicionamento")
            meterConditioning?.skipTest()
        } else {
            Log.i(tagLog,"skipTest")
            resultadoDeTeste.status = EnumTestStatus.TEST_SKIPPED
            countTest.stop()
            pressure.stop()
            rotation.finish()
        }
    }

    @Synchronized
    override fun pauseTest() {
        Log.i(tagLog,"pauseTest")
        if (meterConditioning?.isStarted() == true) {
            meterConditioning?.pauseTest()
        } else {
            resultadoDeTeste.status = EnumTestStatus.TEST_PAUSED
            countTest.stop()
            pressure.stop()
            rotation.finish()
        }
    }

//    @Synchronized
//    override fun finishTest() {
//        Log.i(tagLog,"finishTest")
//        countTest.stop()
//        pressure.stop()
//        rotation.finish()
//        finish()
//    }

    @Synchronized
    override fun update(observable: Observable?, value: Any?) {
        if(value is Exception)
            value.message?.let { Log.e(tagLog, it) }

        if (observable is PressureDemo)
            if ((value != null) && (value is Long)) {
                resultadoDeTeste.pressure = value
                if (resultadoDeTeste.status != EnumTestStatus.TEST_RUNNING)
                    resultadoTesteBomba.postValue(resultadoDeTeste)
            }
        if (observable is RotationDemo)
            if ((value != null) && (value is RotationResultDemo)) {
                Log.d(tagLog, "Rotação: ${value.value} rpm")
                resultadoDeTeste.rotation = value.value
                resultadoTesteBomba.postValue(resultadoDeTeste)
                if (value.status == EnumTestStatus.TEST_FINISHED) {
                    Log.d(tagLog, "Verificação por rotação finalizada")
                    if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING) {
                        Log.w(tagLog, "Definindo a rotação, tempo e pressão para o teste")
                        if (testMeasurement.medirInjecao || testMeasurement.medirRetorno) {
                            configIncrementValues()
                            countTest.start(totalTestPointTime)
                        } else {
                            countTest.start(pointTestPump.timeWaitingMeasurement.toLong() * 1000)
                        }
                        rotation.start(resultadoDeTeste.desiredRotation, 0, resultadoDeTeste.desiredRotation - 1)
                        if (resultadoDeTeste.desiredPressure > 0)
                            pressure.start(resultadoDeTeste.desiredPressure)
                    }
                    resultadoDeTeste.checkRotation = false
                    start()
                }
            }
        if (observable is MeterConditioningControllerDemo)
            if ((value != null) && (value is TestResult)) {
                if (value.status in
                    arrayOf(
                        EnumTestStatus.TEST_SKIPPED,
                        EnumTestStatus.TEST_PAUSED, EnumTestStatus.TEST_CANCELLED)) {
                    resultadoDeTeste.status = value.status
                } else {
                    //resultadoDeTeste.state = EnumTestState.TESTE_CONDICIONAMENTO
                }
                resultadoTesteBomba.postValue(resultadoDeTeste)
                when (value.status) {
                    EnumTestStatus.TEST_SKIPPED -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            Log.d(tagLog, "NextPoint")
                            viewController.nextPoint()
                        }
                    }
                    EnumTestStatus.TEST_CANCELLED -> {
                        finish()
                    }
                    EnumTestStatus.TEST_FINISHED -> {
                        Log.i(tagLog, testMeasurement.toString())
                        GlobalScope.launch(Dispatchers.Main) {
                            delay(2000)
                            resultadoDeTeste.status = EnumTestStatus.TEST_STARTING
                            if (testMeasurement.flagInjecao) {
                                Log.d(tagLog, "Injeção")
                                //configPressure() não vai ter isso no teste de bombas
                                configIncrementValues()
                                rotation.start(resultadoDeTeste.desiredRotation, 0, resultadoDeTeste.desiredRotation-1)
                                countTest.start(totalTestPointTime)
                                if (resultadoDeTeste.desiredPressure > 0)
                                    pressure.start(resultadoDeTeste.desiredPressure)
                                start()
                            } else {
                                Log.d(tagLog, "Retorno")
                                //configPressure() não vai ter isso no teste de bombas
                                configIncrementValues()
                                rotation.start(resultadoDeTeste.desiredRotation, 0, resultadoDeTeste.desiredRotation-1)
                                countTest.start(totalTestPointTime)
                                if (resultadoDeTeste.desiredPressure > 0)
                                    pressure.start(resultadoDeTeste.desiredPressure)
                                start()
                            }
                        }
                    } else -> {}
                }
            }
        if (observable is PointTestPumpControllerDemo)
            if ((value != null) && (value is PointTestPumpResult)) {
                when (value.status) {
                    EnumTestStatus.TEST_CANCELLED -> {
                        finish()
                    }
                    EnumTestStatus.TEST_SKIPPED -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            Log.d(tagLog, "NextPoint")
                            viewController.nextPoint()
                        }
                    }
                    EnumTestStatus.TEST_FINISHED -> {
                        if ((testMeasurement.flagInjecao) && (testMeasurement.medirRetorno)) {
                            testMeasurement.mudarDeCanal()
                            meterConditioning = MeterConditioningControllerDemo()
                            meterConditioning!!.addObserver(this@PointTestPumpControllerDemo)
                            meterConditioning!!.startTest()
                            paused = true
                        } else {
                            GlobalScope.launch(Dispatchers.Main) {
                                viewController.nextPoint()
                            }
                        }
                    }
                    else -> {}
                }
            }
    }

}