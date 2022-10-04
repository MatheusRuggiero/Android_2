package br.com.tecnomotor.marvin.config

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

open class AppConfig(context: Context, fileName: String = "AppConfig"){

    private val cTestViews = "TestViews"
    private val preferenceFirstExecution = "First Execution"

    companion object {
        private var instance: AppConfig? = null

        fun getInstance(context: Context? = null):AppConfig {
            if (instance == null)
                if (context == null)
                    throw  ExceptionInInitializerError("O contexto deve ser enviado pelo menos na primeira vez que o método getInstance(contexto) for chamado")
                else {
                    instance = AppConfig(context.applicationContext)
                    RotationTestConfig.getInstance(context)
                    PointTestPumpConfig.getInstance(context)
                }
            return instance!!
        }
    }

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(fileName,
        AppCompatActivity.MODE_PRIVATE)

    fun edit():SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    fun setTestViews(value: Boolean) {
        edit().putBoolean(cTestViews, value).apply()
    }

    fun isTestViews(): Boolean {
        return sharedPreferences.getBoolean(cTestViews, false)
    }

    /**
     * Indica se o app foi executado pela 1a vez
     */
    fun setFirstExecution(firstExecution: Boolean) {
        edit().putBoolean(preferenceFirstExecution, firstExecution).apply()
    }
    fun firstExecution(): Boolean {
        return sharedPreferences.getBoolean(preferenceFirstExecution, true)
    }
}