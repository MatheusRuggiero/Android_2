package br.com.tecnomotor.marvin.api.v1.controller.lotXid.global
import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.api.authentication.BasicAuthClientDefault
import br.com.tecnomotor.marvin.api.v1.interfaces.lotXid.global.VersionLotXidInterface
import br.com.tecnomotor.marvin.api.v1.model.global.VersionSynchronize
import br.com.tecnomotor.marvin.repository.global.RevisionRepository
import br.com.tecnomotor.marvin.repository.global.VersionRepository
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VERSION_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.constant.ValueDefault.VALUES_PROGRESS_BAR
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


@OptIn(DelicateCoroutinesApi::class)
class VersionLotXidController(
    var application: Application,
    var txtViewMessageProgress: TextView?,
    var btnStartSynchronization: Button?,
    var progressBarSynchronization: ProgressBar?,
    var activity: Activity?,
    val xid: Int) {
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
                    txtViewMessageProgress!!.text = application.applicationContext.getString(R.string.version)
                    btnStartSynchronization!!.isEnabled = false
                }
                isSynchronizationBackground = false
            }

            params["xid"] = xid.toString()

            val call = BasicAuthClientDefault<VersionLotXidInterface>(
                mobileUser.userName!!,
                mobileUser.password!!,
                application.applicationContext
            )
                .create(VersionLotXidInterface::class.java)
                .getLotXid(params)

            call.enqueue(object : Callback<List<VersionSynchronize>> {
                override fun onFailure(call: Call<List<VersionSynchronize>>, t: Throwable) {
                    if (!isSynchronizationBackground) {
                        activity?.runOnUiThread {
                            GlobalUtil.method.shortTimeMessageAlert(application.applicationContext, application.applicationContext.getString(R.string.error_sync_download))
                        }
                    }
                    Log.e(TAG, t.message, t)
                }

                override fun onResponse(
                    call: Call<List<VersionSynchronize>>,
                    response: Response<List<VersionSynchronize>>) {
                    if (response.isSuccessful) {
                        GlobalScope.launch(Dispatchers.IO) {
                            val list = response.body()
                            var isCompletedSynchronization = false
                            val versionRepository = VersionRepository(application)

                            if (!list.isNullOrEmpty()) {
                                versionRepository.saveListObjectSynchronized(list)
                            }

                            var xidSp = 0

                            if (!GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_VERSION_MAX_XID
                                ).isNullOrEmpty()
                                && !GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_VERSION_MAX_XID
                                ).isNullOrBlank()) {
                                xidSp = GlobalUtil.method.recoverSharedPreferences(
                                    spConfiguration,
                                    PARAMETER_VERSION_MAX_XID
                                )!!.toInt()
                            }

                            if (xidSp > 0) {

                                val xidDatabase = versionRepository.maxXid()

                                if (xidSp >= (xidDatabase + 1)) {
                                    //delay(20)
                                    val versionLotXidController =
                                        VersionLotXidController(
                application,
                txtViewMessageProgress,
                                            btnStartSynchronization,
                                            progressBarSynchronization,
                                            activity,
                                            xidDatabase
                                        )
                                    versionLotXidController.load()
                                } else {
                                    isCompletedSynchronization = true
                                }
                            }


                            if (!isSynchronizationBackground && list.isNullOrEmpty()) {
                                activity?.runOnUiThread {
                                    GlobalUtil.method.shortTimeMessageAlert(
                                        application.applicationContext,
                                        application.applicationContext.getString(R.string.not_values_version)
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
        val revisionRepository = RevisionRepository(application)
        val revisionLotXidController =
            RevisionLotXidController(
                application,
                txtViewMessageProgress,
                btnStartSynchronization,
                progressBarSynchronization,
                activity,
                revisionRepository.maxXid()
            )
        revisionLotXidController.load()
    }
}