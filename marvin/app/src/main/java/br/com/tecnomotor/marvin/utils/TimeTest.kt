package br.com.tecnomotor.marvin.utils

import br.com.tecnomotor.marvin.utils.CountUpTimer

class TimeTest {
    companion object {
        @Synchronized
        fun getTimeTest(
            millisInFuture: Long,
            onTick: (millisFinished: Long) -> Unit,
            onFinish: () -> Unit
        ) = object : CountUpTimer(1000, millisInFuture) {
            override fun onTick(millisFinished: Long) {
                onTick(millisFinished)
            }

            override fun onFinish() {
                onFinish()
            }
        }
    }
}