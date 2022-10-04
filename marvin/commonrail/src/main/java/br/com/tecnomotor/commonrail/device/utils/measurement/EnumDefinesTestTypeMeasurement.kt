package br.com.tecnomotor.commonrail.device.utils.measurement

import android.content.Context
import br.com.tecnomotor.commonrail.device.utils.ILocalizedCommand

enum class EnumDefinesTestTypeMeasurement(val type: String) : ILocalizedCommand {
    REMOTE_SECURITY_STATE("REMOTE_SECURITY_STATE") {
        override fun getLocalizedCommand(): Int = 255
        override fun getLocalizedCommandByte(): Byte = 0xFF.toByte()
    },
    REMOTE_TYPE_NONE("REMOTE_TYPE_NONE") {
        override fun getLocalizedCommand(): Int = 0
        override fun getLocalizedCommandByte(): Byte = 0x00.toByte()
    },
    REMOTE_FLUSH_PROCESS("REMOTE_FLUSH_PROCESS") {
        override fun getLocalizedCommand(): Int = 1
        override fun getLocalizedCommandByte(): Byte = 0x01.toByte()
    },
    REMOTE_HEATING_PROCESS("REMOTE_HEATING_PROCESS") {
        override fun getLocalizedCommand(): Int = 2
        override fun getLocalizedCommandByte(): Byte = 0x02.toByte()
    },
    REMOTE_CONDITIONING_PROCESS("REMOTE_CONDITIONING_PROCESS") {
        override fun getLocalizedCommand(): Int = 3
        override fun getLocalizedCommandByte(): Byte = 0x03.toByte()
    },
    REMOTE_LEAK_PROCESS("REMOTE_LEAK_PROCESS") {
        override fun getLocalizedCommand(): Int = 4
        override fun getLocalizedCommandByte(): Byte = 0x04.toByte()
    },
    REMOTE_INJECTOR_PROCESS("REMOTE_INJECTOR_PROCESS") {
        override fun getLocalizedCommand(): Int = 5
        override fun getLocalizedCommandByte(): Byte = 0x05.toByte()
    },
    REMOTE_MPROP_PROCESS("REMOTE_MPROP_PROCESS") {
        override fun getLocalizedCommand(): Int = 6
        override fun getLocalizedCommandByte(): Byte = 0x06.toByte()
    },
    REMOTE_PUMP_PROCESS("REMOTE_PUMP_PROCESS") {
        override fun getLocalizedCommand(): Int = 7
        override fun getLocalizedCommandByte(): Byte = 0x07.toByte()
    },
    REMOTE_EMPTY_PROCESS("REMOTE_EMPTY_PROCESS") {
        override fun getLocalizedCommand(): Int = 8
        override fun getLocalizedCommandByte(): Byte = 0x08.toByte()
    },
    REMOTE_READ_SENSORS("REMOTE_READ_SENSORS") {
        override fun getLocalizedCommand(): Int = 21
        override fun getLocalizedCommandByte(): Byte = 0x15.toByte()
    },
    REMOTE_SET_ACTUATORS("REMOTE_SET_ACTUATORS") {
        override fun getLocalizedCommand(): Int = 22
        override fun getLocalizedCommandByte(): Byte = 0x16.toByte()
    },
    REMOTE_SET_CALIBRATION("REMOTE_SET_CALIBRATION") {
        override fun getLocalizedCommand(): Int = 23
        override fun getLocalizedCommandByte(): Byte = 0x17.toByte()
    },
    REMOTE_SET_PWM("REMOTE_SET_PWM") {
        override fun getLocalizedCommand(): Int = 24
        override fun getLocalizedCommandByte(): Byte = 0x18.toByte()
    },
    REMOTE_READ_REGRETION("REMOTE_READ_REGRETION") {
        override fun getLocalizedCommand(): Int = 25
        override fun getLocalizedCommandByte(): Byte = 0x19.toByte()
    };

    companion object {
        fun valueOf(type: String): EnumDefinesTestTypeMeasurement {
            var res = REMOTE_TYPE_NONE
            values().forEach {
                if (it.type == type)
                    res = it
            }
            return res
        }
    }

}