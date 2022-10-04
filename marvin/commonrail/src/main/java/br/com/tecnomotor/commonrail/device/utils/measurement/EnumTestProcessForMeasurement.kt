package br.com.tecnomotor.commonrail.device.utils.measurement

import android.content.Context
import br.com.tecnomotor.commonrail.device.utils.ILocalizedMessage

enum class EnumTestProcessForMeasurement(val value: Byte) : ILocalizedMessage {
    NONE(0x00.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "NONE"
        }
    },
    Flush(0x01.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "FLUSH"
        }
    },
    Heating(0x02.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "HEATING"
        }
    },
    Conditioning(0x03.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "CONDITIONING"
        }
    },
    Leak(0x04.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "LEAK"
        }
    },
    Injector(0x05.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "INJECTOR"
        }
    },
    Valve(0x06.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "VALVE"
        }
    },
    Pump(0x07.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "PUMP"
        }
    },
    Empty(0x08.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "EMPTY"
        }
    },
    ReadSensors(0x15.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "READ SENSORS"
        }
    },
    SetActuators(0x16.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "SET ACTUATORS"
        }
    },
    SetCalibration(0x17.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "SET CALIBRATION"
        }
    },
    SetPWM(0x18.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "SET PWM"
        }
    },
    ReadRegretion(0x19.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "READ REGRETION"
        }
    },
    SecurityState(0xFF.toByte()) {
        override fun getLocalizedMessage(context: Context): String {
            return "SECURITY STATE"
        }
    };

    companion object {
        fun valueOf(value: Byte): EnumTestProcessForMeasurement {
            var res = NONE
            values().forEach {
                if (it.value == value)
                    res = it
            }
            return res
        }
    }
}