package br.com.tecnomotor.marvin.config

import android.content.Context

class PointTestPumpConfig(context: Context) : AppConfig(context, "PointTestPumpConfig") {

    private val TIMEOUT_CONTROL_WHEN_MEASURING = "TimeoutControlWhenMeasuring"
    private val timeoutControlWhenMeasuringDefault = 1800

    companion object {
        private var instance: PointTestPumpConfig? = null

        fun getInstance(context: Context? = null): PointTestPumpConfig {
            if (instance == null)
                if (context == null)
                    throw  ExceptionInInitializerError("O contexto deve ser enviado pelo menos na primeira vez que o método getInstance(contexto) for chamado")
                else instance = PointTestPumpConfig(context.applicationContext)
            return instance!!
        }
    }

    fun getTimeoutControlWhenMeasuring() =
        sharedPreferences.getInt(TIMEOUT_CONTROL_WHEN_MEASURING, timeoutControlWhenMeasuringDefault)

    fun setTimeoutControlWhenMeasuring(value: Int) {
        edit().putInt(TIMEOUT_CONTROL_WHEN_MEASURING, value)
    }

}
