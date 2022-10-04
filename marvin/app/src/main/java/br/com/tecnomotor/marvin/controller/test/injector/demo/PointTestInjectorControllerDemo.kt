package br.com.tecnomotor.marvin.controller.test.injector.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.injector.result.ConditioningResult
import br.com.tecnomotor.commonrail.device.commands.injector.result.PointTestInjectorResultDemo
import br.com.tecnomotor.commonrail.device.utils.measurement.TestMeasurement
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.IViewController
import br.com.tecnomotor.marvin.controller.test.ControllerTest
import br.com.tecnomotor.marvin.controller.test.IControllerPointTest
import br.com.tecnomotor.marvin.controller.test.demo.PressureDemo
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import br.com.tecnomotor.marvin.utils.CountUpTimer
import kotlinx.coroutines.*
import java.util.*
import kotlin.math.floor

@DelicateCoroutinesApi
class PointTestInjectorControllerDemo(
    private val injector: Injector,
    private var pointTest: PointTestInjector,
    private val viewController: IViewController
) : ControllerTest(), IControllerPointTest<PointTestInjector>, Observer {

    private var tagLog = this::class.java.simpleName
    private var heatingController: HeatingTestInjectorControllerDemo? = null
    private var meterConditioning: MeterConditioningControllerDemo? = null
    private var conditioning: Boolean = false
    private var resultadoDeTeste = PointTestInjectorResultDemo(EnumTestStatus.NONE)
    val resultadoTesteInjetor: MutableLiveData<PointTestInjectorResultDemo> = MutableLiveData(resultadoDeTeste)

    /**
     * Tempo total do teste 15Min = 900.000 ms
     */
    private val totalTime: Long = 120000
    private var totalTestPointTime: Long = (totalTime * 0.2).toLong() // 20%
    var timeAdvanceInMilliseconds: Long = 10

    private var testMeasurement: TestMeasurement = TestMeasurement()

    private var countTest: CountUpTimer
    private var pressure: PressureDemo
    private var injectorInc: Double = 0.0
    private var injectorValue: Double = 0.0
    private var injectorValueOk: Double = 0.0
    private var returnInc: Double = 0.0
    private var returnValue: Double = 0.0
    private var returnValueOk: Double = 0.0

    init {
        pressure = PressureDemo(0)
        pressure.addObserver(this)

        this.addObserver(this)

        configIncrementValues()

        @Synchronized
        countTest = object : CountUpTimer(timeAdvanceInMilliseconds) {
            override fun onTick(millisFinished: Long) {
                // Ajustando a injeção e retorno de acordo com o tempo
                //Log.i(tag, "Tempo de teste: $millisFinished")
                resultadoDeTeste.testTime = millisFinished
                if (testMeasurement.flagInjecao) {
                    injectorValue += injectorInc
                    resultadoDeTeste.injectionValue = floor(injectorValue * 100) / 100
                    if (resultadoDeTeste.injectionValue >= injectorValueOk) {
                        resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
                        pressure.stop()
                        this.stop()
                    }
                }
                if (testMeasurement.flagRetorno) {
                    returnValue += returnInc
                    resultadoDeTeste.returnValue = floor(returnValue * 100) / 100
                    if (resultadoDeTeste.returnValue >= returnValueOk) {
                        resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
                        pressure.stop()
                        this.stop()
                    }
                }
            }

            override fun onFinish() {
                if (resultadoDeTeste.status == EnumTestStatus.TEST_RUNNING)
                    resultadoDeTeste.status = EnumTestStatus.TEST_FINISHED
            }
        }
    }

    private fun configIncrementValues() {
        val randomPercent: Double = ((80..90).random().toDouble() / 100.0)
        Log.i(tagLog, "Percent inc: $randomPercent")
        if (testMeasurement.flagInjecao) {
            injectorValue = 0.0
            injectorValueOk = ((pointTest.injectionMaximum?.toDouble() ?: 0.0) * randomPercent)
            injectorInc = (injectorValueOk / ((totalTestPointTime / timeAdvanceInMilliseconds) * randomPercent))
            Log.w(tagLog, "Injeção: inc=$injectorInc; ok=$injectorValueOk")
        } else if (testMeasurement.flagRetorno) {
            returnValue = 0.0
            returnValueOk = ((pointTest.returnMaximum?.toDouble() ?: 0.0) * randomPercent)
            returnInc = (returnValueOk / ((totalTestPointTime / timeAdvanceInMilliseconds) * randomPercent))
            Log.w(tagLog, "Retorno: inc=$returnInc; ok=$returnValueOk")
        }
    }

    private fun configPressure() {
        resultadoDeTeste.desiredPressure = pointTest.pressure.toLong()
        when (pointTest.typePointTestInjector?.id) {
            4 -> { /* Carga total 20% */
                resultadoDeTeste.desiredPressure = 1400
            }
            5 -> { /* Carga parcial 20% */
                resultadoDeTeste.desiredPressure = 800
            }
            6 -> { /* Marcha lenta 20% */
                resultadoDeTeste.desiredPressure = 350
            }
            7 -> { /* Pré-injeção 40% */
                resultadoDeTeste.desiredPressure = 800
                totalTestPointTime = (totalTime * 0.40).toLong()
            }
            else -> { /* 20% */
                resultadoDeTeste.desiredPressure = 200
            }
        }
        resultadoDeTeste.injectionTime = pointTest.timeInjection
        resultadoDeTeste.frequency = pointTest.frequency
    }

    @Synchronized
    override fun setNextPoint(pointTest: PointTestInjector) {
        Log.w(tagLog, "setNextPoint: ${pointTest.typePointTestInjector?.description}")
        Log.w(tagLog, pointTest.toString())
        this.pointTest = pointTest
        this.resultadoDeTeste = PointTestInjectorResultDemo(EnumTestStatus.NONE)
        this.resultadoDeTeste.minimumInjection = pointTest.injectionMinimum?.toDouble() ?: 0.0
        this.resultadoDeTeste.maximumInjection = pointTest.injectionMaximum?.toDouble() ?: 0.0
        this.resultadoDeTeste.minimumReturn = pointTest.returnMinimum?.toDouble() ?: 0.0
        this.resultadoDeTeste.maximumReturn = pointTest.returnMaximum?.toDouble() ?: 0.0
        this.resultadoDeTeste.frequency = pointTest.frequency
        this.resultadoDeTeste.injectionTime = pointTest.timeInjection
//        testMeasurement.loadParametrizacao(
//            EnumTestProcessForMeasurement.Injector,
//            pointTest.frequency,
//            pointTest.measureInjection == 1,
//            pointTest.measureReturn == 1,
//            pointTest.injectionMaximum?.toDouble() ?: 0.0,
//            pointTest.returnMaximum?.toDouble() ?: 0.0
//        )
//        Log.w(tagLog, testMeasurement.toString())
    }

    @Synchronized
    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while (!finished) {
            if (started) {
                if (!paused) {
                    if (pointTest.typePointTestInjector?.isHeating() == true) {
                        pause()
                        continue
                    } else { //Ponto de teste
                        Log.d(tagLog, "Running ${pointTest.typePointTestInjector?.description} (${resultadoDeTeste.status})")
                        when (resultadoDeTeste.status) {
                            EnumTestStatus.TEST_STARTING -> {
                                resultadoTesteInjetor.postValue(resultadoDeTeste)
                                delay(1000)
                                resultadoDeTeste.status = EnumTestStatus.TEST_RUNNING
                            }
                            EnumTestStatus.TEST_RUNNING -> {
                                resultadoTesteInjetor.postValue(resultadoDeTeste)
                                delay(500)
                            }
                            EnumTestStatus.TEST_PAUSED -> {
                                pause()
                                resultadoDeTeste.pressure = 0
                                resultadoTesteInjetor.postValue(resultadoDeTeste)
                            }
                            EnumTestStatus.TEST_CANCELLED -> {
                                resultadoDeTeste.pressure = 0
                                resultadoTesteInjetor.postValue(resultadoDeTeste)
                                finish()
                            }
                            EnumTestStatus.TEST_SKIPPED -> {
                                pause()
                                resultadoDeTeste.pressure = 0
                                resultadoTesteInjetor.postValue(resultadoDeTeste)
                                delay(2000)
                                setChanged()
                                notifyObservers(resultadoDeTeste)
                            }
                            EnumTestStatus.TEST_FINISHED -> {
                                pause()
                                pressure.stop()
                                countTest.stop()
                                resultadoDeTeste.pressure = 0
                                resultadoTesteInjetor.postValue(resultadoDeTeste)
                                delay(2000)
                                setChanged()
                                notifyObservers(resultadoDeTeste)
                            }
                            else -> {
                            }
                        }
                    }
                }
            }
        }
    }

    @Synchronized
    override fun startTest() {
        if (pointTest.typePointTestInjector?.isHeating() == true) {
            if (heatingController == null) {
                heatingController = HeatingTestInjectorControllerDemo(injector, pointTest)
                heatingController!!.addObserver(this@PointTestInjectorControllerDemo)
            }
            heatingController!!.startTest()
        } else {
            Log.i(tagLog, "startTest")
            meterConditioning = MeterConditioningControllerDemo()
            meterConditioning!!.addObserver(this@PointTestInjectorControllerDemo)
        }
    }

//    @Synchronized
//    override fun executeTest() {
//        if ((pointTest.typePointTestInjector?.isHeating() == true) && (heatingController != null)) {
//            heatingController!!.executeTest()
//        } else {
//            Log.i(tagLog, "executeTest")
//            meterConditioning?.startTest()
//            meterConditioning?.executeTest()
//            conditioning = true
//        }
//    }

    @Synchronized
    override fun cancelTest() {
        if ((pointTest.typePointTestInjector?.isHeating() == true) && (heatingController != null)) {
            heatingController!!.cancelTest()
        } else {
            Log.i(tagLog, "cancelTest")
            if (conditioning) {
                meterConditioning?.cancelTest()
            } else {
                resultadoDeTeste.status = EnumTestStatus.TEST_CANCELLED
                countTest.stop()
                pressure.stop()
            }
        }
    }

    @Synchronized
    override fun skipTest() {
        if ((pointTest.typePointTestInjector?.isHeating() == true) && (heatingController != null)) {
            heatingController?.skipTest()
        } else {
            if (conditioning) {
                Log.i(tagLog, "skipTest Condicionamento")
                meterConditioning?.skipTest()
            } else {
                Log.i( tagLog,"skipTest")
                resultadoDeTeste.status = EnumTestStatus.TEST_SKIPPED
                countTest.stop()
                pressure.stop()
            }
        }
    }

    @Synchronized
    override fun pauseTest() {
        if ((pointTest.typePointTestInjector?.isHeating() == true) && (heatingController != null)) {
            heatingController?.pauseTest()
        } else {
            Log.i(tagLog, "pauseTest")
            if (conditioning) {
                meterConditioning?.pauseTest()
            } else {
                resultadoDeTeste.status = EnumTestStatus.TEST_PAUSED
                countTest.stop()
                pressure.stop()
            }
        }
    }

//    @Synchronized
//    override fun finishTest() {
//        if ((pointTest.typePointTestInjector?.isHeating() == true) && (heatingController != null)) {
//            heatingController?.finishTest()
//        } else {
//            Log.i(tagLog, "finishTest")
//            countTest.stop()
//            pressure.stop()
//            finish()
//        }
//    }

    @Synchronized
    override fun update(observable: Observable?, value: Any?) {
        if ((observable is HeatingTestInjectorControllerDemo) &&
            (pointTest.typePointTestInjector?.isHeating() == true))
            if ((value != null) && (value is PointTestInjectorResultDemo)) {
                resultadoTesteInjetor.postValue(value)
                if ((value.status == EnumTestStatus.TEST_FINISHED) ||
                    (value.status == EnumTestStatus.TEST_SKIPPED)) {
                    GlobalScope.launch(Dispatchers.Main) {
                        Log.d(tagLog, "NextPoint")
                        viewController.nextPoint()
                    }
                }
            }
        if (observable is PressureDemo)
            if ((value != null) && (value is Long)) {
                resultadoDeTeste.pressure = value
                if (resultadoDeTeste.status != EnumTestStatus.TEST_RUNNING)
                    resultadoTesteInjetor.postValue(resultadoDeTeste)
            }
        if (observable is MeterConditioningControllerDemo)
            if ((value != null) && (value is ConditioningResult)) {
                if (value.status in
                    arrayOf(
                        EnumTestStatus.TEST_SKIPPED,
                        EnumTestStatus.TEST_PAUSED, EnumTestStatus.TEST_CANCELLED)) {
                    resultadoDeTeste.status = value.status
                    resultadoDeTeste.pressure = 0
                    conditioning = false
                } else {
                    //resultadoDeTeste.state = EnumTestState.TESTE_CONDICIONAMENTO
                    resultadoDeTeste.pressure = value.pressure
                }
                resultadoDeTeste.desiredPressure = value.desiredPressure
                resultadoDeTeste.injectionTime = value.injectionTime
                resultadoDeTeste.frequency = value.frequency
                resultadoTesteInjetor.postValue(resultadoDeTeste)
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
                        conditioning = false
                        GlobalScope.launch(Dispatchers.Main) {
                            delay(2000)
                            resultadoDeTeste.status = EnumTestStatus.TEST_STARTING
                            resultadoTesteInjetor.postValue(resultadoDeTeste)
                            if (testMeasurement.flagInjecao) {
                                Log.d(tagLog, "Injeção")
                                configPressure()
                                configIncrementValues()
                                countTest.start(totalTestPointTime)
                                pressure.start(resultadoDeTeste.desiredPressure)
                                start()
                            } else {
                                Log.d(tagLog, "Retorno")
                                configPressure()
                                configIncrementValues()
                                countTest.start(totalTestPointTime)
                                pressure.start(resultadoDeTeste.desiredPressure)
                                start()
                            }
                        }
                    }
                    else -> {
                    }
                }
            }
        if (observable is PointTestInjectorControllerDemo)
            if ((value != null) && (value is PointTestInjectorResultDemo)) {
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
                            meterConditioning!!.addObserver(this@PointTestInjectorControllerDemo)
                            meterConditioning!!.startTest()
                            paused = true
                        } else {
                            GlobalScope.launch(Dispatchers.Main) {
                                viewController.nextPoint()
                            }
                        }
                    }
                    else -> {
                    }
                }
            }
    }

}