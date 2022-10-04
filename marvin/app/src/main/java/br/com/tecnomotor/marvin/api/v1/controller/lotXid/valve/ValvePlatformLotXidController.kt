package br.com.tecnomotor.marvin.api.v1.controller.lotXid.valve

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.api.authentication.BasicAuthClientDefault
import br.com.tecnomotor.marvin.api.v1.interfaces.lotXid.valve.ValvePlatformLotXidInterface
import br.com.tecnomotor.marvin.api.v1.model.valve.ValvePlatformSynchronize
import br.com.tecnomotor.marvin.repository.valve.ValvePlanTestRepository
import br.com.tecnomotor.marvin.repository.valve.ValvePlatformRepository
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLATFORM_MAX_XID
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


class ValvePlatformLotXidController(
    var application: Application,
    var txtViewMessageProgress: TextView?,
    var btnStartSynchronization: Button?,
    var progressBarSynchronization: ProgressBar?,
    var activity: Activity?,
    val xid: Int
) {
    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun load() {
        try {
            val mobileUser = GlobalUtil.method.getUserLogin(application.applicationContext)
            val params: MutableMap<String, String> = HashMap()
            var isSynchronizationBackground = true

            if (txtViewMessageProgress != null) {
                activity?.runOnUiThread {
                    txtViewMessageProgress!!.text = application.applicationContext.getString(R.string.valve_platform)
                    btnStartSynchronization!!.isEnabled = false
                }
                isSynchronizationBackground = false
            }

            params["xid"] = xid.toString()

            val call = BasicAuthClientDefault<ValvePlatformLotXidInterface>(
                mobileUser.userName!!,
                mobileUser.password!!,
                application.applicationContext
            )
                .create(ValvePlatformLotXidInterface::class.java)
                .getLotXid(params)

            call.enqueue(object : Callback<List<ValvePlatformSynchronize>> {
                override fun onFailure(
                    call: Call<List<ValvePlatformSynchronize>>,
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
                    call: Call<List<ValvePlatformSynchronize>>,
                    response: Response<List<ValvePlatformSynchronize>>
                ) {
                    if (response.isSuccessful) {
                        GlobalScope.launch(Dispatchers.IO) {
                            val list = response.body()
                            val valvePlatformRepository = ValvePlatformRepository(application)
                            var isCompletedSynchronization = false
                            if (!list.isNullOrEmpty()) {
                                valvePlatformRepository.saveListObjectSynchronized(list)
                            }

                            var xidSp = 0

                            if (!GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_VALVE_PLATFORM_MAX_XID
                                ).isNullOrEmpty()
                                && !GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_VALVE_PLATFORM_MAX_XID
                                ).isNullOrBlank()
                            ) {
                                xidSp = GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_VALVE_PLATFORM_MAX_XID
                                )!!.toInt()
                            }

                            if (xidSp > 0) {

                                val xidDatabase = valvePlatformRepository.maxXid()

                                if (xidSp >= (xidDatabase + 1)) {
                                    //delay(20)
                                    val valvePlatformLotXidController =
                                        ValvePlatformLotXidController(
                                            application,
                                            txtViewMessageProgress,
                                            btnStartSynchronization,
                                            progressBarSynchronization,
                                            activity,
                                            xidDatabase
                                        )
                                    valvePlatformLotXidController.load()
                                } else {
                                    isCompletedSynchronization = true
                                }
                            }

                            if (!isSynchronizationBackground && list.isNullOrEmpty()) {
                                activity?.runOnUiThread {
                                    GlobalUtil.method.shortTimeMessageAlert(
                                        application.applicationContext,
                                        application.applicationContext.getString(R.string.not_values_valve_platform)
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
                    }  else if (response.errorBody().toString() == HttpURLConnection.HTTP_NOT_FOUND.toString()) {
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
        val valvePlanTestRepository = ValvePlanTestRepository(application)
        val valvePlanTestLotXidController =
            ValvePlanTestLotXidController(
                application,
                txtViewMessageProgress,
                btnStartSynchronization,
                progressBarSynchronization,
                activity,
                valvePlanTestRepository.maxXid()
            )
        valvePlanTestLotXidController.load()
    }
}