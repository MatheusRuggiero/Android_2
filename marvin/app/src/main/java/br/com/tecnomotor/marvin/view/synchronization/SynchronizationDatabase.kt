package br.com.tecnomotor.marvin.view.synchronization

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.api.v1.controller.license.NewLicenseController
import br.com.tecnomotor.marvin.api.v1.controller.maxXid.global.BrandMaxXidController
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class SynchronizationDatabase : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_synchronization_database)

        val btnStartSynchronization = findViewById<Button>(R.id.btn_start_synchronization)
        val txtViewMessageInfo = findViewById<TextView>(R.id.txt_message_progress)
        val progress = findViewById<ProgressBar>(R.id.progress_bar_synchronization)
        btnStartSynchronization.setOnClickListener {
//            GlobalUtil.method.saveSharedPreferences(
//                applicationContext.getSharedPreferences(
//                    ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
//                    Context.MODE_PRIVATE
//                ),
//                ParameterSharedPreferences.LINK_OPTION_DEFAULT,
//                "http://192.168.0.109:8089/"
//            )

            GlobalUtil.method.saveSharedPreferences(
                applicationContext.getSharedPreferences(
                    ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                    Context.MODE_PRIVATE
                ),
                ParameterSharedPreferences.LINK_OPTION_DEFAULT,
                "http://192.168.31.146:8089/"
            )

//            GlobalUtil.method.saveSharedPreferences(
//                applicationContext.getSharedPreferences(
//                    ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
//                    Context.MODE_PRIVATE
//                ),
//                ParameterSharedPreferences.LINK_OPTION_DEFAULT,
//                "http://172.30.80.68:8089/"
//            )

//            GlobalUtil.method.saveSharedPreferences(
//                applicationContext.getSharedPreferences(
//                    ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
//                    Context.MODE_PRIVATE
//                ),
//                ParameterSharedPreferences.LINK_OPTION_DEFAULT,
//                "http://192.168.0.115:8089/"
//            )

//            GlobalUtil.method.saveSharedPreferences(
//                applicationContext.getSharedPreferences(
//                    ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
//                    Context.MODE_PRIVATE
//                ),
//                ParameterSharedPreferences.LINK_OPTION_DEFAULT,
//                "http://172.30.80.79:8089/"
//            )
//
//            GlobalUtil.method.saveSharedPreferences(

//                applicationContext.getSharedPreferences(
//                    ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
//                    Context.MODE_PRIVATE
//                ),
//                ParameterSharedPreferences.LINK_OPTION_DEFAULT,
//                "http://192.168.50.142:8089/"
//            )

            GlobalScope.launch(Dispatchers.IO) {
                val listDeviceAuthentication = AppDatabase.getDatabase(applicationContext)?.deviceAuthenticationDao()?.findAll()
                if (listDeviceAuthentication.isNullOrEmpty()) {
                    val newLicenseController = NewLicenseController(
                        application,
                        txtViewMessageInfo,
                        btnStartSynchronization,
                        progress,
                        this@SynchronizationDatabase
                    )
                    newLicenseController.load()
                } else {

                    GlobalUtil.method.resetParametersTryAgain(
                        applicationContext.getSharedPreferences(
                            ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                            Context.MODE_PRIVATE
                        )
                    )

                    val brandMaxXidController = BrandMaxXidController(
                        application,
                        txtViewMessageInfo,
                        btnStartSynchronization,
                        progress,
                        this@SynchronizationDatabase
                    )

                    brandMaxXidController.load()
                }
            }
        }
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        super.onDestroy()
    }
}