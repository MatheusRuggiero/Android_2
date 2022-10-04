package br.com.tecnomotor.marvin.utils.objects

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.TelephonyManager

class TelephonyInfo private constructor() {
    var imsiSIM1: String? = null
        private set

    /*public static void setImsiSIM1(String imeiSIM1) {
        TelephonyInfo.imeiSIM1 = imeiSIM1;
    }*/  var imsiSIM2: String? = null
        private set

    /*public static void setImsiSIM2(String imeiSIM2) {
        TelephonyInfo.imeiSIM2 = imeiSIM2;
    }*/  var isSIM1Ready = false
        private set

    /*public static void setSIM1Ready(boolean isSIM1Ready) {
        TelephonyInfo.isSIM1Ready = isSIM1Ready;
    }*/  var isSIM2Ready = false
        private set

    /*public static void setSIM2Ready(boolean isSIM2Ready) {
        TelephonyInfo.isSIM2Ready = isSIM2Ready;
    }*/
    val isDualSIM: Boolean
        get() = imsiSIM2 != null

    private class GeminiMethodNotFoundException(info: String?) : Exception(info) {
        companion object {
            private const val serialVersionUID = -996812356902545308L
        }
    }

    companion object {
        private var telephonyInfo: TelephonyInfo? = null

        @SuppressLint("MissingPermission", "HardwareIds")
        fun getInstance(context: Context): TelephonyInfo? {
            if (telephonyInfo == null) {
                telephonyInfo = TelephonyInfo()
                val telephonyManager =
                        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                telephonyInfo!!.imsiSIM1 = telephonyManager.deviceId
                telephonyInfo!!.imsiSIM2 = null
                try {
                    telephonyInfo!!.imsiSIM1 = getDeviceIdBySlot(context, "getDeviceIdGemini", 0)
                    telephonyInfo!!.imsiSIM2 = getDeviceIdBySlot(context, "getDeviceIdGemini", 1)
                } catch (e: GeminiMethodNotFoundException) {
                    e.printStackTrace()
                    try {
                        telephonyInfo!!.imsiSIM1 = getDeviceIdBySlot(context, "getDeviceId", 0)
                        telephonyInfo!!.imsiSIM2 = getDeviceIdBySlot(context, "getDeviceId", 1)
                    } catch (e1: GeminiMethodNotFoundException) {
                        //Call here for next manufacturer's predicted method name if you wish
                        e1.printStackTrace()
                    }
                }
                telephonyInfo!!.isSIM1Ready =
                        telephonyManager.simState == TelephonyManager.SIM_STATE_READY
                telephonyInfo!!.isSIM2Ready = false
                try {
                    telephonyInfo!!.isSIM1Ready = getSIMStateBySlot(context, "getSimStateGemini", 0)
                    telephonyInfo!!.isSIM2Ready = getSIMStateBySlot(context, "getSimStateGemini", 1)
                } catch (e: GeminiMethodNotFoundException) {
                    e.printStackTrace()
                    try {
                        telephonyInfo!!.isSIM1Ready = getSIMStateBySlot(context, "getSimState", 0)
                        telephonyInfo!!.isSIM2Ready = getSIMStateBySlot(context, "getSimState", 1)
                    } catch (e1: GeminiMethodNotFoundException) {
                        //Call here for next manufacturer's predicted method name if you wish
                        e1.printStackTrace()
                    }
                }
            }
            return telephonyInfo
        }

        @Throws(GeminiMethodNotFoundException::class)
        private fun getDeviceIdBySlot(
                context: Context,
                predictedMethodName: String,
                slotID: Int
        ): String? {
            var imei: String? = null
            val telephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            try {
                val telephonyClass = Class.forName(telephony.javaClass.name)
                val parameter = arrayOfNulls<Class<*>?>(1)
                parameter[0] = Int::class.javaPrimitiveType
                val getSimID = telephonyClass.getMethod(predictedMethodName, *parameter)
                val obParameter = arrayOfNulls<Any>(1)
                obParameter[0] = slotID
                val obPhone = getSimID.invoke(telephony, *obParameter)
                if (obPhone != null) {
                    imei = obPhone.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                throw GeminiMethodNotFoundException(predictedMethodName)
            }
            return imei
        }

        @Throws(GeminiMethodNotFoundException::class)
        private fun getSIMStateBySlot(
                context: Context,
                predictedMethodName: String,
                slotID: Int
        ): Boolean {
            var isReady = false
            val telephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            try {
                val telephonyClass = Class.forName(telephony.javaClass.name)
                val parameter = arrayOfNulls<Class<*>?>(1)
                parameter[0] = Int::class.javaPrimitiveType
                val getSimStateGemini = telephonyClass.getMethod(predictedMethodName, *parameter)
                val obParameter = arrayOfNulls<Any>(1)
                obParameter[0] = slotID
                val obPhone = getSimStateGemini.invoke(telephony, *obParameter)
                if (obPhone != null) {
                    val simState = obPhone.toString().toInt()
                    if (simState == TelephonyManager.SIM_STATE_READY) {
                        isReady = true
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                throw GeminiMethodNotFoundException(predictedMethodName)
            }
            return isReady
        }
    }
}