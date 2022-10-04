package br.com.tecnomotor.marvin.api.v1.controller.maxXid.global

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.api.authentication.BasicAuthClientDefault
import br.com.tecnomotor.marvin.api.model.ValuesMaxXid
import br.com.tecnomotor.marvin.api.v1.controller.maxXid.injector.InjectorMaxXidController
import br.com.tecnomotor.marvin.api.v1.interfaces.maxXid.global.BrandMaxXidInterface
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_BRAND_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_BRAND_MAX_XID_TRY_AGAIN
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


class BrandMaxXidController(
    var application: Application,
    var txtViewMessageProgress: TextView?,
    var btnStartSynchronization: Button?,
    var progressBarSynchronization: ProgressBar?,
    var activity: Activity?
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
                    txtViewMessageProgress!!.text = application.applicationContext.getString(R.string.max_xid_brand)
                    btnStartSynchronization!!.isEnabled = false
                }
                isSynchronizationBackground = false
            }

            val call = BasicAuthClientDefault<BrandMaxXidInterface>(
                mobileUser.userName!!,
                mobileUser.password!!,
                application.applicationContext
            )
                .create(BrandMaxXidInterface::class.java)
                .getMax(params)

            call.enqueue(object : Callback<ValuesMaxXid> {
                override fun onFailure(call: Call<ValuesMaxXid>, t: Throwable) {
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
                    call: Call<ValuesMaxXid>,
                    response: Response<ValuesMaxXid>
                ) {
                    if (response.isSuccessful) {
                        GlobalScope.launch(Dispatchers.IO) {
                            val valuesMaxXid = response.body()
                            if (valuesMaxXid != null
                                && !valuesMaxXid.xid.isNullOrBlank()
                                && !valuesMaxXid.xid.isNullOrEmpty()
                            ) {
                                GlobalUtil.method.saveSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_BRAND_MAX_XID,
                                    valuesMaxXid.xid
                                )

                            } else {
                                if (!isSynchronizationBackground) {
                                    GlobalUtil.method.shortTimeMessageAlert(
                                        application.applicationContext,
                                        application.applicationContext.getString(R.string.not_values_brand_max_xid)
                                    )
                                }
                            }


                            var xidSp = 0

                            if (!GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_BRAND_MAX_XID
                                ).isNullOrEmpty()
                                && !GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_BRAND_MAX_XID
                                ).isNullOrBlank()
                            ) {
                                xidSp = GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_BRAND_MAX_XID
                                )!!.toInt()
                            }

                            if (xidSp == 0
                                && !GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_BRAND_MAX_XID_TRY_AGAIN
                                ).isNullOrEmpty()
                                && !GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_BRAND_MAX_XID_TRY_AGAIN
                                ).toBoolean()
                            ) {

                                if (valuesMaxXid != null
                                    && !valuesMaxXid.xid.isNullOrBlank()
                                    && !valuesMaxXid.xid.isNullOrEmpty()
                                ) {
                                    GlobalUtil.method.saveSharedPreferences(
                                        spConfiguration,
                                        PARAMETER_BRAND_MAX_XID,
                                        valuesMaxXid.xid
                                    )
                                }

                                if (!GlobalUtil.method.recoverSharedPreferences(
                                        spConfiguration,
                                        PARAMETER_BRAND_MAX_XID
                                    ).isNullOrEmpty()
                                    && !GlobalUtil.method.recoverSharedPreferences(
                                        spConfiguration,
                                        PARAMETER_BRAND_MAX_XID
                                    ).isNullOrBlank()
                                ) {
                                    xidSp = GlobalUtil.method.recoverSharedPreferences(
                                        spConfiguration,
                                        PARAMETER_BRAND_MAX_XID
                                    )!!.toInt()
                                }

                                if (xidSp == 0) {

                                    GlobalUtil.method.saveSharedPreferences(
                                        spConfiguration,
                                        PARAMETER_BRAND_MAX_XID_TRY_AGAIN,
                                        "true"
                                    )

                                    val brandMaxXidController = BrandMaxXidController(
                                        application,
                                        txtViewMessageProgress,
                                        btnStartSynchronization,
                                        progressBarSynchronization,
                                        activity
                                    )

                                    brandMaxXidController.load()
                                } else {
                                    nextMaxXid()
                                }
                            } else {
                                nextMaxXid()
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

    fun nextMaxXid() {
        if (progressBarSynchronization != null) {
            activity?.runOnUiThread {
                progressBarSynchronization!!.progress = (progressBarSynchronization!!.progress + VALUES_PROGRESS_BAR)
            }
        }

        val injectorMaxXidController = InjectorMaxXidController(
            application,
            txtViewMessageProgress,
            btnStartSynchronization,
            progressBarSynchronization,
            activity
        )

        injectorMaxXidController.load()
    }
}