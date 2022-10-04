package br.com.tecnomotor.marvin.controller.test.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.utils.CountUpTimer
import kotlinx.coroutines.*
import java.util.*

@DelicateCoroutinesApi
open class RotationDemo(
    var targetRotation: Long,
    var rotationVariation: Long = 5,
    var timeInFuture: Long = 0
):Observable() {

    private var tagLog = this::class.java.simpleName

    val countInterval: Long = 300
    var countTime = getCountUpTime(timeInFuture)
    private var currentRotation = RotationResultDemo(0)
    val rotationLiveData: MutableLiveData<RotationResultDemo> = MutableLiveData(currentRotation)
    private var isRestart: Boolean = false


    private fun getCountUpTime(mTimeInFuture: Long) = object: CountUpTimer(countInterval, mTimeInFuture) {
        override fun onTick(millisFinished: Long) {
            var increased: Long = (targetRotation.toDouble() * 0.10).toLong()
            if (mTimeInFuture > 0)
                increased = (targetRotation.toDouble() / ((mTimeInFuture.toDouble() / (1000.0 / countInterval.toDouble())) / 100.0)).toLong()
            if ((currentRotation.value + increased) < (targetRotation - rotationVariation))
                currentRotation.value += increased
            else {
                val randomValue = ((targetRotation-rotationVariation)..(targetRotation-1)).random()
                currentRotation.value = randomValue
            }
            rotationLiveData.postValue(currentRotation)
            setChanged()
            notifyObservers(currentRotation)
        }

        override fun onFinish() {
            if (!isRestart) {
                currentRotation.status = EnumTestStatus.TEST_FINISHED
//                currentRotation.statusDoTeste = StatusTeste.TESTE_ERROR
//                currentRotation.error = ECommonRailCommandException(1)
                rotationLiveData.postValue(currentRotation)
                setChanged()
                notifyObservers(currentRotation)
            } else isRestart = false
        }
    }

    fun simpleStart() {
        Log.i(tagLog, "SimpleStart")
        if (!countTime.isFinished()) finish()
        GlobalScope.launch(Dispatchers.IO) {
            while (!countTime.isFinished()) {
                delay(100)
            }
            this@RotationDemo.timeInFuture = 0
            this@RotationDemo.countTime = getCountUpTime(this@RotationDemo.timeInFuture)
            this@RotationDemo.currentRotation = RotationResultDemo(0)
            this@RotationDemo.currentRotation.status = EnumTestStatus.TEST_RUNNING
            this@RotationDemo.isRestart = false
            this@RotationDemo.countTime.start()
        }

    }

    fun start(targetRotation: Long, timeInFuture: Long = 0, rotation: Long = 0) {
        Log.i(tagLog, "Start")
        if (!countTime.isFinished()) finish()
        GlobalScope.launch(Dispatchers.IO) {
            while (!countTime.isFinished()) {
                delay(100)
            }
            this@RotationDemo.timeInFuture = timeInFuture
            this@RotationDemo.countTime = getCountUpTime(this@RotationDemo.timeInFuture)
            this@RotationDemo.targetRotation = targetRotation
            this@RotationDemo.currentRotation = RotationResultDemo(rotation)
            this@RotationDemo.currentRotation.status = EnumTestStatus.TEST_RUNNING
            this@RotationDemo.isRestart = false
            this@RotationDemo.countTime.start()
        }
    }

    fun finish() {
        Log.i(tagLog, "Finish")
        isRestart = true
        countTime.stop()
    }
    fun stop() {
        Log.i(tagLog, "Stop")
        countTime.stop()
    }
}