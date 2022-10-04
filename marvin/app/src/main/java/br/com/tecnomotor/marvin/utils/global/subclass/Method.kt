package br.com.tecnomotor.marvin.utils.global.subclass

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.widget.Toast
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.api.model.MobileUser
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_BRAND_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_BRAND_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_BRAND_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLAN_TEST_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLAN_TEST_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLATFORM_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLATFORM_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_LOGIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PARAMETER_REMOTE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PARAMETER_REMOTE_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PARAMETER_REMOTE_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PASSWORD
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PERMISSION_PARAMETER_REMOTE_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PERMISSION_PARAMETER_REMOTE_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PLATFORM_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PLATFORM_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_POINT_TEST_INJECTOR_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_POINT_TEST_INJECTOR_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_POINT_TEST_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_POINT_TEST_PUMP_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_POINT_TEST_PUMP_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLAN_TEST_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLATFORM_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLATFORM_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLATFORM_PLAN_TEST_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLATFORM_PLAN_TEST_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_INJECTOR_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_INJECTOR_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_PUMP_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_PUMP_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_PUMP_PLAN_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_PUMP_PLAN_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_SENSOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_SENSOR_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_SENSOR_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_SENSOR_PLAN_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_SENSOR_PLAN_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_VALVE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_VALVE_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_VALVE_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_VALVE_PLAN_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_VALVE_PLAN_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLAN_TEST_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLAN_TEST_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLATFORM_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLATFORM_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLATFORM_PLAN_TEST_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLATFORM_PLAN_TEST_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_PLAN_TEST_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_INJECTOR_TEST_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_INJECTOR_TEST_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_PUMP_TEST_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_TEST_PUMP_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_TEST_PUMP_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_PUMP_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_PUMP_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_SENSOR_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_VALVE_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLAN_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLAN_TEST_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLAN_TEST_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLATFORM_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLATFORM_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLATFORM_PLAN_MAX_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLATFORM_PLAN_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VERSION_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VERSION_MAX_XID_TRY_AGAIN
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.utils.objects.FormatTestResults
import br.com.tecnomotor.marvin.utils.objects.TelephonyInfo
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


@SuppressLint("MissingPermission", "HardwareIds", "LongLogTag")
class Method {

    fun saveSharedPreferences(sharedPreferences: SharedPreferences, key: String?, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun recoverSharedPreferences(sharedPreferences: SharedPreferences, key: String?): String? {
        return sharedPreferences.getString(key, "")
    }

    fun clearSharedPreferences(sharedPreferences: SharedPreferences) {
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }

    fun shortTimeMessageAlert(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun longTimeMessageAlert(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun getDeviceIMEI(context: Context): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var imei: String? = "0"
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            imei = tm.deviceId
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
            && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
        ) {
            imei = tm.imei
        }

        if (imei == null
            || imei.isEmpty()
            || imei == "00000000"
            || imei.trim().isEmpty()
            || imei == ""
        ) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O
            ) {
                imei = tm.deviceId
            }
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT
            && Build.VERSION.SDK_INT < Build.VERSION_CODES.O
        ) {
            if (imei == null
                || imei.isEmpty()
                || imei == "00000000"
                || imei.trim().isEmpty()
            ) {
                val telephonyInfo: TelephonyInfo = TelephonyInfo.getInstance(context)!!
                imei = telephonyInfo.imsiSIM2
            }
        }

        return if (!imei.isNullOrEmpty()) imei else "0"
    }

    fun getDeviceId(context: Context): String {
        return if (!Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                .isNullOrEmpty()
        )
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) else "0"
    }

    fun getUserLogin(context: Context): MobileUser {
        val mobileUser = MobileUser()
        if (recoverSharedPreferences(
                context.getSharedPreferences(
                    SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                    Context.MODE_PRIVATE
                ),
                PARAMETER_LOGIN
            ).isNullOrEmpty()
            && recoverSharedPreferences(
                context.getSharedPreferences(
                    SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                    Context.MODE_PRIVATE
                ), PARAMETER_PASSWORD
            ).isNullOrEmpty()
        ) {
            GlobalScope.async {
                val listMobileAuthentication =
                    AppDatabase.getDatabase(context)?.deviceAuthenticationDao()?.findAll()
                if (!listMobileAuthentication.isNullOrEmpty()) {
                    mobileUser.userName = listMobileAuthentication[0].userName
                    mobileUser.password = listMobileAuthentication[0].passwordBackup
                    saveSharedPreferences(
                        context.getSharedPreferences(
                            SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                            Context.MODE_PRIVATE
                        ),
                        PARAMETER_LOGIN,
                        listMobileAuthentication[0].userName
                    )
                    saveSharedPreferences(
                        context.getSharedPreferences(
                            SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                            Context.MODE_PRIVATE
                        ),
                        PARAMETER_PASSWORD,
                        listMobileAuthentication[0].passwordBackup
                    )
                }
            }
        } else {
            mobileUser.userName = recoverSharedPreferences(
                context.getSharedPreferences(
                    SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                    Context.MODE_PRIVATE
                ), PARAMETER_LOGIN
            )!!
            mobileUser.password = recoverSharedPreferences(
                context.getSharedPreferences(
                    SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                    Context.MODE_PRIVATE
                ), PARAMETER_PASSWORD
            )!!
        }

        return mobileUser
    }

    fun resetParametersTryAgain(sp: SharedPreferences) {

//        Module injector
        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_BRAND_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_BRAND_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_BRAND_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_INJECTOR_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_INJECTOR_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_INJECTOR_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_INJECTOR_PLATFORM_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_INJECTOR_PLATFORM_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_INJECTOR_PLATFORM_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID, "0")
        }


        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PLATFORM_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PLATFORM_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_PLATFORM_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_INJECTOR_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_REVISION_INJECTOR_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_INJECTOR_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_TYPE_PLAN_TEST_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_TYPE_PLAN_TEST_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TYPE_PLAN_TEST_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VERSION_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VERSION_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VERSION_CURRENT_XID, "0")
        }

        saveSharedPreferences(sp, PARAMETER_TYPE_POINT_INJECTOR_TEST_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_BRAND_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_INJECTOR_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_INJECTOR_PLATFORM_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_INJECTOR_PLAN_TEST_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_PLATFORM_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_POINT_TEST_INJECTOR_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_REVISION_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_REVISION_INJECTOR_MAX_XID, "0")

        saveSharedPreferences(sp, PARAMETER_TYPE_POINT_INJECTOR_TEST_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_BRAND_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_INJECTOR_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_INJECTOR_PLATFORM_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_INJECTOR_PLAN_TEST_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_PLATFORM_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_POINT_TEST_INJECTOR_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_REVISION_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_REVISION_INJECTOR_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(
            sp,
            PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_MAX_XID_TRY_AGAIN,
            "false"
        )
        saveSharedPreferences(
            sp,
            PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_MAX_XID_TRY_AGAIN,
            "false"
        )
        saveSharedPreferences(sp, PARAMETER_TYPE_PLAN_TEST_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_VERSION_MAX_XID_TRY_AGAIN, "false")

        //        Module pump
        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PUMP_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PUMP_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_PUMP_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PUMP_PLATFORM_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PUMP_PLATFORM_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_PUMP_PLATFORM_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_PUMP_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_PUMP_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_PUMP_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PUMP_PLAN_TEST_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PUMP_PLAN_TEST_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_PUMP_PLAN_TEST_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_TYPE_PUMP_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_TYPE_PUMP_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TYPE_PUMP_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_POINT_TEST_PUMP_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_POINT_TEST_PUMP_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_POINT_TEST_PUMP_CURRENT_XID, "0")
        }

        saveSharedPreferences(sp, PARAMETER_PUMP_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_PUMP_PLATFORM_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_PUMP_PLATFORM_PLAN_TEST_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_REVISION_PUMP_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_REVISION_PUMP_PLAN_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_PUMP_PLAN_TEST_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_TYPE_PUMP_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_TYPE_POINT_TEST_PUMP_MAX_XID, "0")
        saveSharedPreferences(sp, PARAMETER_POINT_TEST_PUMP_MAX_XID, "0")

        saveSharedPreferences(sp, PARAMETER_PUMP_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_PUMP_PLATFORM_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_PUMP_PLATFORM_PLAN_TEST_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_REVISION_PUMP_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_REVISION_PUMP_PLAN_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(
            sp,
            PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_MAX_XID_TRY_AGAIN,
            "false"
        )
        saveSharedPreferences(sp, PARAMETER_TYPE_PUMP_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_TYPE_POINT_TEST_PUMP_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_TYPE_POINT_PUMP_TEST_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_POINT_TEST_PUMP_MAX_XID_TRY_AGAIN, "false")

        //        Module sensor
        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_MAX_XID).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_SENSOR_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_SENSOR_PLATFORM_PLAN_TEST_MAX_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_SENSOR_PLATFORM_PLAN_TEST_MAX_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_PLAN_TEST_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_MAX_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_MAX_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_SENSOR_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_SENSOR_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_SENSOR_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_SENSOR_PLAN_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_REVISION_SENSOR_PLAN_MAX_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_SENSOR_PLAN_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_PLAN_TEST_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_PLAN_TEST_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_SENSOR_PLAN_TEST_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_SENSOR_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_SENSOR_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_SENSOR_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_SENSOR_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID, "0")
        }

        saveSharedPreferences(sp, PARAMETER_SENSOR_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_SENSOR_PLAN_TEST_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_SENSOR_PLATFORM_PLAN_TEST_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_REVISION_SENSOR_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_REVISION_SENSOR_PLAN_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_TYPE_SENSOR_MAX_XID_TRY_AGAIN, "false")

        //        Module valve
        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_MAX_XID).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VALVE_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLAN_TEST_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLAN_TEST_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VALVE_PLAN_TEST_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_PLAN_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_PLAN_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_PLAN_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_MAX_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_MAX_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_VALVE_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_VALVE_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_VALVE_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_VALVE_PLAN_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_VALVE_PLAN_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_VALVE_PLAN_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLAN_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLAN_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VALVE_PLAN_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VALVE_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_VALVE_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_REVISION_VALVE_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_VALVE_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLAN_TEST_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_VALVE_PLAN_TEST_CURRENT_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_VALVE_PLAN_TEST_CURRENT_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID, "0")
        }

        saveSharedPreferences(sp, PARAMETER_VALVE_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_VALVE_PLAN_TEST_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_VALVE_PLATFORM_PLAN_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_REVISION_VALVE_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_REVISION_VALVE_PLAN_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_TYPE_VALVE_MAX_XID_TRY_AGAIN, "false")


        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PARAMETER_REMOTE_CURRENT_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_PARAMETER_REMOTE_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_PARAMETER_REMOTE_CURRENT_XID, "0")
        }


        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID, "0")
        }


        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID,
                "0"
            )
        }

        if (GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PARAMETER_REMOTE_MAX_XID)
                .isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(sp, PARAMETER_PARAMETER_REMOTE_MAX_XID)
                .isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_PARAMETER_REMOTE_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_PERMISSION_PARAMETER_REMOTE_MAX_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_PERMISSION_PARAMETER_REMOTE_MAX_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_PERMISSION_PARAMETER_REMOTE_MAX_XID, "0")
        }

        if (GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_MAX_XID
            ).isNullOrEmpty()
            || GlobalUtil.method.recoverSharedPreferences(
                sp,
                PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_MAX_XID
            ).isNullOrBlank()
        ) {
            saveSharedPreferences(sp, PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_MAX_XID, "0")
        }

        saveSharedPreferences(sp, PARAMETER_PARAMETER_REMOTE_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(sp, PARAMETER_PERMISSION_PARAMETER_REMOTE_MAX_XID_TRY_AGAIN, "false")
        saveSharedPreferences(
            sp,
            PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_MAX_XID_TRY_AGAIN,
            "false"
        )

    }

    fun settingValueBarChart(
        chart: CombinedChart,
        value: Float,
        minValue: Float,
        maxValue: Float,
        minGraph: Float,
        maxGraph: Float,
        color: Int = Color.parseColor("#FAFF00"),
        colorMin: Int = Color.parseColor("#00678D"),
        colorMax: Int = Color.parseColor("#00678D"),
        legend: String = ""
    ) {
        val barEntries: MutableList<BarEntry> = arrayListOf()
        barEntries.add(BarEntry(0.1f, value))
        val barDataSet = BarDataSet(barEntries, legend)
        barDataSet.color = color
        val barData = BarData(barDataSet)
        barData.notifyDataChanged()

        val lineEntriesMin: MutableList<Entry> = arrayListOf()
        lineEntriesMin.add(Entry(0f, minValue))
        lineEntriesMin.add(Entry(0.2f, minValue))
        val lineDataSetMin = LineDataSet(lineEntriesMin, "")
        lineDataSetMin.color = colorMin
        lineDataSetMin.circleHoleColor = Color.TRANSPARENT
        lineDataSetMin.setCircleColor(Color.TRANSPARENT)
        lineDataSetMin.setDrawValues(false)
        lineDataSetMin.lineWidth = 2f

        val lineEntriesMax: MutableList<Entry> = arrayListOf()
        lineEntriesMax.add(Entry(0f, maxValue))
        lineEntriesMax.add(Entry(0.2f, maxValue))
        val lineDataSetMax = LineDataSet(lineEntriesMax, "")
        lineDataSetMax.color = colorMax
        lineDataSetMax.circleHoleColor = Color.TRANSPARENT
        lineDataSetMax.setCircleColor(Color.TRANSPARENT)
        lineDataSetMax.setDrawValues(false)
        lineDataSetMax.lineWidth = 2f

        val lineData = LineData(lineDataSetMin, lineDataSetMax)
        lineData.notifyDataChanged()
        lineData.isHighlightEnabled = false

        val combinedData = CombinedData()
        combinedData.setData(barData)
        combinedData.setData(lineData)

        chart.description.isEnabled = false
        chart.notifyDataSetChanged()
        chart.invalidate()
        chart.data = combinedData
        chart.data.setValueTextSize(20f)
        chart.setScaleEnabled(false)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        chart.axisLeft.axisMinimum = minGraph
        chart.axisLeft.axisMaximum = maxGraph
        chart.axisRight.isEnabled = false

        val vl: Array<String> = arrayOf(FormatTestResults.FORMAT_MILLILITER.format(value))
        val xAxis: XAxis = chart.xAxis!!
        xAxis.valueFormatter = IndexAxisValueFormatter(vl)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1F
        xAxis.setCenterAxisLabels(true)
        xAxis.isGranularityEnabled = true
        chart.legend.isEnabled = false
    }

    fun settingValuePieChart(
        chart: PieChart,
        context: Context?,
        maxValue: Float,
        value: Float,
        centerValue: String,
        legend: String = ""
    ) {
        val pieEntries: MutableList<PieEntry> = arrayListOf()
        pieEntries.add(PieEntry(value))                  //Valor atual
        pieEntries.add(PieEntry(maxValue - value))     //Valor máximo
        val dataSet = PieDataSet(pieEntries, "")
        val data = PieData(dataSet)

        chart.invalidate() //refresh
        data.notifyDataChanged()
        chart.notifyDataSetChanged()

        chart.setUsePercentValues(false);
        chart.data = data
        chart.centerText = centerValue
        chart.setCenterTextSize(30f)
        chart.setDrawEntryLabels(false)

        chart.maxAngle = 300f
        chart.rotationAngle = 120f
        chart.setEntryLabelTextSize(10f)
        chart.holeRadius = 65f
        chart.transparentCircleRadius = 0f
        chart.description.text = "\n $legend"
        chart.description.textSize = 15f
        chart.isDrawHoleEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            dataSet.colors = arrayListOf(context?.getColor(R.color.vermelho_tecnomotor), context?.getColor(R.color.dark_gray))
        else
            dataSet.colors = arrayListOf(Color.parseColor("#C8191B"), Color.parseColor("#525C63"))
        dataSet.valueTextColor = Color.TRANSPARENT
        chart.legend.isEnabled = false
        chart.isRotationEnabled = false
        chart.isClickable = false
//        chart.setBackgroundColor(Color.TRANSPARENT) //Alterar cor de fundo do gráfico
        chart.setHoleColor(Color.TRANSPARENT) //Alterar cor da parte de dentro do gráfico Pie


        //legend attributes
        //legend attributes
//        val legend: Legend = chart.legend
//        legend.form = Legend.LegendForm.CIRCLE
//        legend.textSize = 12f
//        legend.formSize = 20f
//        legend.formToTextSpace = 2f
    }

    fun settingValuePieChartRotationInjector(
        chart: PieChart,
        context: Context?,
        value: Float,
        legend: String
    ) {
        val pieEntries: MutableList<PieEntry> = arrayListOf()
        pieEntries.add(PieEntry(value))                  //Valor atual
        pieEntries.add(PieEntry(2000 - value))     //Valor máximo
        val dataSet = PieDataSet(pieEntries, "")
        val data = PieData(dataSet)

        chart.invalidate() //refresh
        data.notifyDataChanged()
        chart.notifyDataSetChanged()

        chart.setUsePercentValues(false);
        chart.data = data
        chart.centerText = FormatTestResults.FORMAT_PRESSURE.format(value.toInt())
        chart.setCenterTextSize(30f)
        chart.setDrawEntryLabels(false)

        chart.maxAngle = 300f
        chart.rotationAngle = 120f
        chart.setEntryLabelTextSize(10f)
        chart.holeRadius = 65f
        chart.transparentCircleRadius = 0f
        chart.description.text = "\n $legend"
        chart.description.textSize = 15f
        chart.isDrawHoleEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            dataSet.colors = arrayListOf(context?.getColor(R.color.vermelho_tecnomotor), context?.getColor(R.color.dark_gray))
        else
            dataSet.colors = arrayListOf(Color.parseColor("#C8191B"), Color.parseColor("#525C63"))
        dataSet.valueTextColor = Color.TRANSPARENT
        chart.legend.isEnabled = false
        chart.isRotationEnabled = false
        chart.isClickable = false
        chart.setHoleColor(Color.TRANSPARENT) //Alterar cor da parte de dentro do gráfico Pie
    }

}