package br.com.tecnomotor.marvin.controller.test.demo

import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.utils.CountUpTimer
import java.util.*

open class PressureDemo(
    var targetPressure: Long,
    var pressureVariation: Long = 5
):Observable() {

    private var tag = this::class.java.simpleName

    private var currentPressure: Long = 0
    val pressure: MutableLiveData<Long> = MutableLiveData(currentPressure)

    val countTime = object: CountUpTimer(300) {
        override fun onTick(millisFinished: Long) {
            val increasedPressure: Long = (targetPressure.toDouble() * 0.10).toLong()
            if (currentPressure < (targetPressure - pressureVariation))
                currentPressure += increasedPressure
            else {
                val randomPressure = ((targetPressure-pressureVariation)..(targetPressure-1)).random()
                currentPressure = randomPressure
            }
            pressure.postValue(currentPressure)
            setChanged()
            notifyObservers(currentPressure)
        }

        override fun onFinish() {
            currentPressure = 0
            pressure.postValue(currentPressure)
            setChanged()
            notifyObservers(currentPressure)
        }
    }

    fun start() {
        currentPressure = 0
        countTime.start()
    }

    fun start(targetPressure: Long) {
        this.targetPressure = targetPressure
        this.start()
    }

    fun stop() {
        countTime.stop()
    }
}