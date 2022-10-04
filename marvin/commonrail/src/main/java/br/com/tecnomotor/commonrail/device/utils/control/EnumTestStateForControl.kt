package br.com.tecnomotor.commonrail.device.utils.control

/**
 * /*******************************************************************************
 * Definitions for testpoints and process state machine
*******************************************************************************/
#define   PROCESSO_HARD_TEST_IDLE          0x40
#define   FLUID_MAINTENANCE                0x41
#define   READ_SENSORS                     0x42
#define   SET_ACTUATORS                    0x43
#define   TEST_POWER_SUPPLY                0x44
#define   TEST_DAC                         0x46
#define   TEST_PWM                         0x47
#define   TEST_ADC                         0x48
#define   TEST_CONTROL_RAIL_PRESSURE       0x49
#define   TEST_CONTROL_VALVE_CURRENT       0x50
#define   TEST_ROTATION                    0x51
#define   TEST_INJECTOR_INDUCTIVE          0x52
 */
enum class EnumTestStateForControl {
}