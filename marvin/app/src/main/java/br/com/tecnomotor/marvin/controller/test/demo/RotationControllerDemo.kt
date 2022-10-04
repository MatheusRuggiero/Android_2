package br.com.tecnomotor.marvin.controller.test.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.controller.IController
import br.com.tecnomotor.marvin.controller.IViewController
import br.com.tecnomotor.marvin.controller.test.IControllerTest
import kotlinx.coroutines.*
import java.util.*

@DelicateCoroutinesApi
class RotationControllerDemo(
    private val valueMaxRotation: Long,
    val viewController: IViewController
) : IControllerTest, IController, Observer {

    private var timeCheckRotation: Long = 5000
    private var resultadoDeTeste = RotationResultDemo(0)
    var resultado: MutableLiveData<RotationResultDemo> = MutableLiveData(resultadoDeTeste)
    private var rotation: RotationDemo

    init {
        rotation = RotationDemo(valueMaxRotation,9)
        rotation.addObserver(this)
        GlobalScope.launch(Dispatchers.IO) {
            delay(2000)
            rotation.start(valueMaxRotation,timeCheckRotation)
        }
    }

    override fun start() {
        rotation.start(valueMaxRotation,timeCheckRotation)
    }
    override fun stop() {
        rotation.stop()
    }
    override fun pause() {
        rotation.stop()
    }
    override fun finish() {
        rotation.stop()
    }

    override fun startTest() {
        rotation.start(valueMaxRotation,timeCheckRotation)
    }

    override fun cancelTest() {
        rotation.finish()
    }

    override fun isFinished(): Boolean {
        TODO("Not yet implemented")
    }

    override fun skipTest() {
        rotation.stop()
    }

    override fun pauseTest() {
        rotation.finish()
    }

    @Synchronized
    override fun update(observable: Observable?, value: Any?) {
        if(value is Exception)
            value.message?.let { Log.e("RotationControllerDemo", it) }

        if ((observable is RotationDemo) && (value is RotationResultDemo)) {
            resultadoDeTeste.value = value.value
            resultadoDeTeste.temperature = (55..56).random().toLong()
            resultadoDeTeste.status = value.status
            resultadoDeTeste.error = value.error
            when (value.status) {
                EnumTestStatus.TEST_RUNNING ->
                    resultado.postValue(resultadoDeTeste)
                EnumTestStatus.TEST_FAIL ->
                    resultado.postValue(resultadoDeTeste)
                EnumTestStatus.TEST_FINISHED ->
//                    GlobalScope.launch(Dispatchers.Main) {
                        resultado.postValue(resultadoDeTeste)
                        //viewController.nextPoint()
//                    }
                else -> {
                }
            }
        }
    }

}