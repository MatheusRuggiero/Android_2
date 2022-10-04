package br.com.tecnomotor.marvin.api.v1.controller.lotXid.pump

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.api.authentication.BasicAuthClientDefault
import br.com.tecnomotor.marvin.api.v1.controller.lotXid.sensor.SensorLotXidController
import br.com.tecnomotor.marvin.api.v1.interfaces.lotXid.pump.TypePointTestPumpLotXidInterface
import br.com.tecnomotor.marvin.api.v1.model.pump.TypePointTestPumpSynchronize
import br.com.tecnomotor.marvin.repository.pump.TypePointTestPumpRepository
import br.com.tecnomotor.marvin.repository.sensor.SensorRepository
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_TEST_PUMP_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.constant.ValueDefault.VALUES_PROGRESS_BAR
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


class TypePointTestPumpLotXidController(
    var application: Application,
    var txtViewMessageProgress: TextView?,
    var btnStartSynchronization: Button?,
    var progressBarSynchronization: ProgressBar?,
    var activity: Activity?,
    val xid: Int
) {
    private val TAG: String = this::class.java.simpleName
    private val spConfiguration =
        application.applicationContext.getSharedPreferences(SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION, Context.MODE_PRIVATE)

    fun load() {
        try {
            val mobileUser = GlobalUtil.method.getUserLogin(application.applicationContext)
            val params: MutableMap<String, String> = HashMap()
            var isSynchronizationBackground = true

            if (txtViewMessageProgress != null) {
                activity?.runOnUiThread {
                    txtViewMessageProgress!!.text = application.applicationContext.getString(R.string.type_point_test_pump)
                    btnStartSynchronization!!.isEnabled = false
                }
                isSynchronizationBackground = false
            }

            params["xid"] = xid.toString()

            val call = BasicAuthClientDefault<TypePointTestPumpLotXidInterface>(
                mobileUser.userName!!,
                mobileUser.password!!, application.applicationContext
            )
                .create(TypePointTestPumpLotXidInterface::class.java)
                .getLotXid(params)

            call.enqueue(object : Callback<List<TypePointTestPumpSynchronize>> {
                override fun onFailure(
                    call: Call<List<TypePointTestPumpSynchronize>>,
                    t: Throwable
                ) {
                    if (!isSynchronizationBackground) {
                        activity?.runOnUiThread {
                            GlobalUtil.method.shortTimeMessageAlert(
                                application.applicationContext,
                                application.applicationContext.getString(R.string.error_sync_download)
                            )
                        }
                    }
                    Log.e(TAG, t.message, t)
                }

                override fun onResponse(
                    call: Call<List<TypePointTestPumpSynchronize>>,
                    response: Response<List<TypePointTestPumpSynchronize>>
                ) {
                    if (response.isSuccessful) {
                        GlobalScope.launch(Dispatchers.IO) {
                            val list = response.body()
                            var isCompletedSynchronization = false
                            val typePointTestPumpRepository = TypePointTestPumpRepository(application)

                            if (!list.isNullOrEmpty()) {
                                typePointTestPumpRepository.saveListObjectSynchronized(list)
                            }

                            var xidSp = 0

                            if (!GlobalUtil.method.recoverSharedPreferences(spConfiguration, PARAMETER_TYPE_POINT_TEST_PUMP_MAX_XID)
                                    .isNullOrEmpty()
                                && !GlobalUtil.method.recoverSharedPreferences(spConfiguration, PARAMETER_TYPE_POINT_TEST_PUMP_MAX_XID)
                                    .isNullOrBlank()
                            ) {
                                xidSp =
                                    GlobalUtil.method.recoverSharedPreferences(spConfiguration, PARAMETER_TYPE_POINT_TEST_PUMP_MAX_XID)!!
                                        .toInt()
                            }

                            if (xidSp > 0) {

                                val xidDatabase = typePointTestPumpRepository.maxXid()

                                if (xidSp >= (xidDatabase + 1)) {
                                    //delay(20)
                                    val typePointTestPumpLotXidController = TypePointTestPumpLotXidController(
                                        application,
                                        txtViewMessageProgress,
                                        btnStartSynchronization,
                                        progressBarSynchronization,
                                        activity,
                                        xidDatabase
                                    )
                                    typePointTestPumpLotXidController.load()
                                } else {
                                    isCompletedSynchronization = true
                                }
                            }

                            if (!isSynchronizationBackground && list.isNullOrEmpty()) {
                                activity?.runOnUiThread {
                                    GlobalUtil.method.shortTimeMessageAlert(
                                        application.applicationContext,
                                        application.applicationContext.getString(R.string.not_values_type_point_test_pump)
                                    )
                                }
                                isCompletedSynchronization = true
                            }

                            if (isCompletedSynchronization) {

                                if (progressBarSynchronization != null) {
                                    activity?.runOnUiThread {
                                        progressBarSynchronization!!.progress =
                                            (progressBarSynchronization!!.progress + VALUES_PROGRESS_BAR)
                                    }
                                }

                                nextLotXid()
                            }
                        }
                    } else if (response.errorBody().toString() == HttpURLConnection.HTTP_NOT_FOUND.toString()) {
                        if (!isSynchronizationBackground) {
                            GlobalUtil.method.shortTimeMessageAlert(
                                application.applicationContext, application.applicationContext.getString(R.string.error_sync_server)
                            )
                        }
                    } else if (response.errorBody().toString() == HttpURLConnection.HTTP_INTERNAL_ERROR.toString()) {
                        if (!isSynchronizationBackground) {
                            GlobalUtil.method.shortTimeMessageAlert(
                                application.applicationContext, application.applicationContext.getString(R.string.error_internal_server_sync)
                            )
                        }
                    }
                }
            })

        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }
    }

    private fun nextLotXid() {
//        if (progressBarSynchronization != null) {
//            activity?.runOnUiThread {
//                progressBarSynchronization!!.progress =
//                    (progressBarSynchronization!!.progress + VALUES_PROGRESS_BAR)
//                GlobalUtil.method.longTimeMessageAlert(
//                    application.applicationContext,
//                    application.applicationContext.getString(R.string.synchronization_success)
//                )
//            }
//        }
        val sensorRepository = SensorRepository(application)
        val sensorLotXidController =
            SensorLotXidController(
                application,
                txtViewMessageProgress,
                btnStartSynchronization,
                progressBarSynchronization,
                activity,
                sensorRepository.maxXid()
            )
        sensorLotXidController.load()
    }

}