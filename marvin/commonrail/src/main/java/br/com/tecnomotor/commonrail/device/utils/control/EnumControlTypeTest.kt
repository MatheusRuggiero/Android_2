package br.com.tecnomotor.commonrail.device.utils.control

enum class EnumControlTypeTest(val value: Byte) {
    NONE(0x00.toByte()),
    INJECTOR(0x01.toByte()),
    SYNC_PUMP(0x02.toByte()),
    INJECTOR_ELECTRIC_TEST(0x03.toByte()),
    VALVE(0x04.toByte()),
    VALVE_ELECTRIC_TEST(0x05.toByte()),
    SENSOR(0x06.toByte()),
    HEATING(0x07.toByte()),
    PUMP(0x09.toByte()),
    PUMP_LEAKAGE(0x11.toByte()),
    PROCESS(0x12.toByte()),
    HARD_TEST(0x13.toByte()),
    FLUID_MAINTENANCE(0x14.toByte()),
    READ_SENSORS(0x15.toByte()),
    SET_ACTUATORS(0x16.toByte()),
    INJECTOR_BEAKER(0x17.toByte()),
    VALVE_BEAKER(0x18.toByte()),
    TURN_ON_PUMPS(0x19.toByte()),
    POWER_SUPPLY(0x20.toByte()),
    RPM_TEST(0x21.toByte()),
    HARDWARE_SETUP(0x1A.toByte()),
    WAITING_COMMAND(0x1B.toByte());

    companion object {
        fun valueOf(value: Byte): EnumControlTypeTest {
            var res = NONE
            EnumControlTypeTest.values().forEach {
                if (it.value == value)
                    res = it
            }
            return res
        }
    }
}