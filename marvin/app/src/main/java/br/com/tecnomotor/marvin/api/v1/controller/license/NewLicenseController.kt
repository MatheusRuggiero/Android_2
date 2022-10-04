package br.com.tecnomotor.marvin.api.v1.controller.license
import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.api.authentication.BasicAuthClientDefault
import br.com.tecnomotor.marvin.api.model.DeviceAuthenticationParameter
import br.com.tecnomotor.marvin.api.v1.controller.maxXid.global.BrandMaxXidController
import br.com.tecnomotor.marvin.api.v1.interfaces.license.NewLicenseInterface
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ValueDefault.VALUES_PROGRESS_BAR
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


class NewLicenseController(
    var application: Application,
    var txtViewMessageProgress: TextView?,
    var btnStartSynchronization: Button?,
    var progressBarSynchronization: ProgressBar?,
    var activity: Activity?) {
    private val TAG: String = this::class.java.simpleName

    fun load() {
        try {
            val mobileUser = GlobalUtil.userRandom.generatedUserRandom()
            val params: MutableMap<String, String> = HashMap()

            params["device-id"] = GlobalUtil.method.getDeviceId(application.applicationContext)
            params["imei"] = GlobalUtil.method.getDeviceIMEI(application.applicationContext)
            params["code-board"] = "0"
            var isSynchronizationBackground = true

            if (txtViewMessageProgress != null) {
                activity?.runOnUiThread {
                    txtViewMessageProgress!!.text = application.applicationContext.getString(R.string.register_new_license)
                    activity?.runOnUiThread {
                        progressBarSynchronization!!.progress = (progressBarSynchronization!!.progress + VALUES_PROGRESS_BAR)
                    }
                    btnStartSynchronization!!.isEnabled = false
                }
                isSynchronizationBackground = false
            }

            val call = BasicAuthClientDefault<NewLicenseInterface>(
                mobileUser.userName!!,
                mobileUser.password!!,
                application.applicationContext
            )
                .create(NewLicenseInterface::class.java)
                .getNewLicense(params)

            call.enqueue(object : Callback<DeviceAuthenticationParameter> {
                override fun onFailure(call: Call<DeviceAuthenticationParameter>, t: Throwable) {
                    if (!isSynchronizationBackground) {
                        activity?.runOnUiThread {
                            GlobalUtil.method.shortTimeMessageAlert(application.applicationContext, application.applicationContext.getString(R.string.error_sync_download))
                        }
                    }
                    Log.e(TAG, t.message, t)
                }

                override fun onResponse(
                    call: Call<DeviceAuthenticationParameter>,
                    response: Response<DeviceAuthenticationParameter>) {
                    if (response.isSuccessful) {

                        GlobalScope.launch(Dispatchers.IO) {

                            if (!AppDatabase.getDatabase(application.applicationContext)?.deviceAuthenticationDao()?.findAll().isNullOrEmpty()) {

                                val listObject1 =
                                    AppDatabase.getDatabase(application.applicationContext)?.deviceAuthenticationDao()?.findAll()
                                if (!listObject1.isNullOrEmpty()) {
                                    listObject1.forEach {

                                        AppDatabase.getDatabase(application.applicationContext)?.deviceAuthenticationDao()?.delete(it)
                                    }
                                }
                            }

                            val deviceAuthenticationParameter: DeviceAuthenticationParameter? = response.body()

                            AppDatabase.getDatabase(application.applicationContext)?.deviceAuthenticationDao()?.insert(deviceAuthenticationParameter!!.deviceAuthentication)


                            val listObject2 = AppDatabase.getDatabase(application.applicationContext)?.productLicenseDeviceDao()?.findAll()
                            if (!listObject2.isNullOrEmpty()) {
                                listObject2.forEach {
                                    AppDatabase.getDatabase(application.applicationContext)?.productLicenseDeviceDao()?.delete(it)
                                }
                            }


                            AppDatabase.getDatabase(application.applicationContext)?.productLicenseDeviceDao()
                                ?.insert(deviceAuthenticationParameter?.productLicenseDevice!!)


                            val listObject3 = AppDatabase.getDatabase(application.applicationContext)?.xidDeviceAuthenticationDao()?.findAll()
                            if (!listObject3.isNullOrEmpty()) {
                                listObject3.forEach {
                                    AppDatabase.getDatabase(application.applicationContext)?.xidDeviceAuthenticationDao()?.delete(it)
                                }
                            }


                            AppDatabase.getDatabase(application.applicationContext)?.xidDeviceAuthenticationDao()
                                ?.insert(deviceAuthenticationParameter?.xidDeviceAuthentication!!)

                            val listObject4 =
                                AppDatabase.getDatabase(application.applicationContext)?.xidProductLicenseDeviceDao()?.findAll()
                            if (!listObject4.isNullOrEmpty()) {
                                listObject4.forEach {
                                    AppDatabase.getDatabase(application.applicationContext)?.xidProductLicenseDeviceDao()?.delete(it)
                                }
                            }


                            AppDatabase.getDatabase(application.applicationContext)?.xidProductLicenseDeviceDao()
                                ?.insert(deviceAuthenticationParameter?.xidProductLicenseDevice!!)

                            GlobalUtil.method.saveSharedPreferences(
                                application.applicationContext.getSharedPreferences(
                                    ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                                    Context.MODE_PRIVATE
                                ),
                                ParameterSharedPreferences.PARAMETER_LOGIN,
                                deviceAuthenticationParameter!!.deviceAuthentication.userName
                            )
                            GlobalUtil.method.saveSharedPreferences(
                                application.applicationContext.getSharedPreferences(
                                    ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                                    Context.MODE_PRIVATE
                                ),
                                ParameterSharedPreferences.PARAMETER_PASSWORD,
                                deviceAuthenticationParameter.deviceAuthentication.passwordBackup
                            )

                            val brandMaxXidController = BrandMaxXidController(
                application,
                txtViewMessageProgress,
                                btnStartSynchronization,
                                progressBarSynchronization,
                                activity!!,
                            )

                            brandMaxXidController.load()
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
}