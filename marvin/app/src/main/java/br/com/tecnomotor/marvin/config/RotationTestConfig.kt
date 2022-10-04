package br.com.tecnomotor.marvin.config

import android.content.Context

class RotationTestConfig(context: Context) : AppConfig(context, "RotationTestConfig") {

    private val DESIRED_ROTATION = "DesiredRotation"
    private val desiredRotationDefault = 500
    private val TIMEOUT_ROTATION = "TimeoutRotation"
    private val timeoutRotationDefault = 60
    private val CHECK_ROTATION = "CheckRotation"
    private var checkRotationDefault = true

    companion object {
        private var instance: RotationTestConfig? = null

        fun getInstance(context: Context? = null):RotationTestConfig {
            if (instance == null)
                if (context == null)
                    throw  ExceptionInInitializerError("O contexto deve ser enviado pelo menos na primeira vez que o método getInstance(contexto) for chamado")
                else instance = RotationTestConfig(context.applicationContext)
            return instance!!
        }
    }

    fun getDesiredRotation(): Int {
        return sharedPreferences.getInt(DESIRED_ROTATION, desiredRotationDefault)
    }
    fun setDesiredRotation(value: Int) {
        edit().putInt(DESIRED_ROTATION, value)
    }

    fun getTimeoutRotation(): Int {
        return sharedPreferences.getInt(TIMEOUT_ROTATION, timeoutRotationDefault)
    }
    fun setTimeoutRotation(value: Int) {
        edit().putInt(TIMEOUT_ROTATION, value)
    }

    fun getCheckRotation(): Boolean {
        return sharedPreferences.getBoolean(CHECK_ROTATION, checkRotationDefault)
    }
    fun setCheckRotation(value: Boolean) {
        checkRotationDefault = value
        edit().putBoolean(CHECK_ROTATION, checkRotationDefault)
    }
}