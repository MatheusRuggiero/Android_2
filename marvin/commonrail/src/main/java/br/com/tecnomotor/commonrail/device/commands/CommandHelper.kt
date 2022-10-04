package br.com.tecnomotor.commonrail.device.commands

import br.com.tecnomotor.commonrail.device.utils.measurement.EnumDefinesTestTypeMeasurement.*

object CommandHelper {
    val CMD_SET_PARAMETER = byteArrayOf(0x25.toByte())
    val CMD_SET_TEST = byteArrayOf(0x26.toByte())
    val CMD_PARAMETER_RESET = byteArrayOf(0x2F.toByte())
    val CMD_SEND_KEY = byteArrayOf(0x22.toByte())

    /**
     * TEST HARDWARE -->
     */
    private val CMD_HW_TEST = byteArrayOf(0x30.toByte())
    private val CMD_HW_DIGITAL_OUTPUT = byteArrayOf(0x60.toByte())
    private val CMD_BUZZER = byteArrayOf(0x01.toByte())
    private val CMD_HW_ON = byteArrayOf(0xFF.toByte())
    private val CMD_HW_OFF = byteArrayOf(0x00.toByte())
    val CMD_LED_01 = byteArrayOf(0x02.toByte())
    val CMD_LED_02 = byteArrayOf(0x03.toByte())
    val CMD_LED_03 = byteArrayOf(0x04.toByte())
    val CMD_LED_04 = byteArrayOf(0x05.toByte())
    val CMD_LED_05 = byteArrayOf(0x06.toByte())
    val CMD_LED_06 = byteArrayOf(0x07.toByte())
    val CMD_LED = CMD_LED_01 + CMD_LED_02 + CMD_LED_03 + CMD_LED_04 + CMD_LED_06

    val CMD_HW_ANALOGIC_PARAM = byteArrayOf(0x30.toByte(), 0x61.toByte())
    val CMD_START_ANALOG_TEST = byteArrayOf(0x30.toByte(), 0x41.toByte())
    val CMD_ANALOG_TEST_UPDATED_INFORMATIONS = byteArrayOf(0x19.toByte(), 0x2B.toByte())

    //Placa Medição
    private val CMD_HW_DIGITAL_OUTPUT_MED = byteArrayOf(0x43.toByte())
    private val CMD_BUZZER_MED = byteArrayOf(0x0B.toByte())
    val CMD_VALV_FLUSH_RET = byteArrayOf(0x02.toByte())
    val CMD_VALV_FLUSH_INJ = byteArrayOf(0x01.toByte())
    val CMD_VALV_COND = byteArrayOf(0x03.toByte())
    val CMD_VALV_COMUT_1 = byteArrayOf(0x04.toByte())
    val CMD_VALV_COMUT_2 = byteArrayOf(0x06.toByte())
    val CMD_VALV_COMUT_3 = byteArrayOf(0x08.toByte())
    val CMD_VALV_DRENO_M1 = byteArrayOf(0x05.toByte())
    val CMD_VALV_DRENO_M2 = byteArrayOf(0x07.toByte())
    val CMD_EXTRA_01 = byteArrayOf(0x0A.toByte())
    val CMD_EXTRA_02 = byteArrayOf(0x09.toByte())
    val CMD_START_ANALOG_TEST_MED = byteArrayOf(0x30.toByte(), 0x47.toByte())
    /**
     * TEST HARDWARE <--
     */

    val CMD_CONTROL_CAN_PARAMETERIZE = byteArrayOf(0x2D.toByte())
    val CMD_CONTROL_READ_REMOTE_CONFIG = byteArrayOf(0x7.toByte())
    val CMD_CONTROL_WRITE_REMOTE_CONFIG = byteArrayOf(0x8.toByte())

    val CMD_MEASURE_CALIBRA_PWM = byteArrayOf(0x30.toByte(), 0x45.toByte())
//    val CMD_MEASURE_SET_PARAMETER_FLUSH_PROCESS: ByteArray = byteArrayOf(
//        0x25.toByte(),
//        0x01.toByte(),
//        0x03.toByte(),
//        0xE8.toByte(),
//        0x03.toByte(),
//        0xE8.toByte(),
//        0x00.toByte(),
//        0x28.toByte(),
//        0x00.toByte(),
//        0x1E.toByte(),
//        0x03.toByte(),
//        0x01.toByte(),
//        0x00.toByte()
//    )

//    fun getFlushParameters(): ArrayList<CommandToDevice> {
//        val listParameters: ArrayList<CommandToDevice> = arrayListOf()
//        listParameters.add(CommandToDevice(CMD_MEASURE_SET_PARAMETER_FLUSH_PROCESS))
//        listParameters.add(CommandToDevice(CMD_MEASURE_SET_FLUSH_PROCESS))
//        return listParameters
//    }

//    val CMD_MEASURE_SET_PARAMETERS_HEATING_PROCESS = byteArrayOf(
//        0x25.toByte(),
//        0x02.toByte(),
//        0x03.toByte(),
//        0xE8.toByte(),
//        0x03.toByte(),
//        0xE8.toByte(),
//        0x00.toByte(),
//        0x28.toByte(),
//        0x00.toByte(),
//        0x1E.toByte(),
//        0x03.toByte(),
//        0x01.toByte(),
//        0x00.toByte()
//    )
//    val CMD_MEASURE_SET_PARAMETER_CONDITIONING_PROCESS = byteArrayOf(
//        0x25.toByte(),
//        0x03.toByte(),
//        0x03.toByte(),
//        0xE8.toByte(),
//        0x03.toByte(),
//        0xE8.toByte(),
//        0x00.toByte(),
//        0x28.toByte(),
//        0x00.toByte(),
//        0x1E.toByte(),
//        0x03.toByte(),
//        0x01.toByte(),
//        0x00.toByte()
//    )

//    fun getConditioningParameters(): ArrayList<CommandToDevice> {
//        val listParameters: ArrayList<CommandToDevice> = arrayListOf()
//        listParameters.add(CommandToDevice(CMD_MEASURE_SET_PARAMETER_CONDITIONING_PROCESS))
//        listParameters.add(CommandToDevice(CMD_MEASURE_SET_CONDITIONING_PROCESS))
//        return listParameters
//    }

    fun commandIsValid(value: ByteArray?): Boolean {
        return ((value != null) && (value.isNotEmpty()) && (value[0] == 0x59.toByte()))
    }

    val CMD_MEASURE_CM_TOP_10_LEAK = byteArrayOf(0x00.toByte()) + byteArrayOf(0x00.toByte())   // 3000 d   //#$27+#$10;   // 10000 d
    val CMD_MEASURE_CM_TOP_20_LEAK = byteArrayOf(0x13.toByte()) + byteArrayOf(0x88.toByte())   // 5000 d   //#$27+#$10;   // 10000 d
    val CMD_MEASURE_FREQ_LEAK = byteArrayOf(0x00.toByte()) + byteArrayOf(0x00.toByte())   // 0 d
    val CMD_MEASURE_TOUT_LEAK = byteArrayOf(0x00.toByte()) + byteArrayOf(0x0A.toByte())   // 10 s
    val CMD_MEASURE_LEAK_TIMEOUT = byteArrayOf(0x00.toByte(), 0x40.toByte())
    val CMD_MEASURE_MSR_LEAK_FLOW_DIR = byteArrayOf(0x02.toByte())  // 00 = discard; 01 - MSR @ CH1; 02 - MSR @ CH2
    val CMD_MEASURE_MSR_INJ_LEAK = byteArrayOf(0x00.toByte())       // 00 = no measurement - 01 = measure INJ
    val CMD_MEASURE_MSR_RET_LEAK = byteArrayOf(0x01.toByte())       // 00 = no measurement - 01 = measure RET
    val CMD_MEASURE_GET_DATA_TEST = byteArrayOf(0x19.toByte(), 0x3D.toByte())

    //Comandos compostos:
    /**
    REMOTE_SECURITY_STATE			0xFF	255
    REMOTE_TYPE_NONE				0x00	0
    REMOTE_FLUSH_PROCESS			0x01	1
    REMOTE_HEATING_PROCESS			0x02	2
    REMOTE_CONDITIONING_PROCESS		0x03	3
    REMOTE_LEAK_PROCESS				0x04	4
    REMOTE_INJECTOR_PROCESS			0x05	5
    REMOTE_MPROP_PROCESS			0x06	6
    REMOTE_PUMP_PROCESS				0x07	7

    REMOTE_EMPTY_PROCESS			0x08	8

    REMOTE_READ_SENSORS				0x15	21
    REMOTE_SET_ACTUATORS			0x16	22
    REMOTE_SET_CALIBRATION			0x17	23
    REMOTE_SET_PWM				    0X18	24
    REMOTE_READ_REGRETION			0X19	25 **/

    val CMD_MEASURE_SET_FLUSH_PROCESS        = CMD_SET_TEST + REMOTE_FLUSH_PROCESS.getLocalizedCommandByte()
    val CMD_MEASURE_SET_HEATING_PROCESS      = CMD_SET_TEST + REMOTE_HEATING_PROCESS.getLocalizedCommandByte()
    val CMD_MEASURE_SET_CONDITIONING_PROCESS = CMD_SET_TEST + REMOTE_CONDITIONING_PROCESS.getLocalizedCommandByte()
    val CMD_MEASURE_SET_ESTANQ_PROCESS       = CMD_SET_TEST + REMOTE_LEAK_PROCESS.getLocalizedCommandByte()
    val CMD_MEASURE_SET_INJET_PROCESS        = CMD_SET_TEST + REMOTE_INJECTOR_PROCESS.getLocalizedCommandByte()
    val CMD_MEASURE_SET_VALVULAS_PROCESS     = CMD_SET_TEST + REMOTE_MPROP_PROCESS.getLocalizedCommandByte()
    val CMD_MEASURE_SET_PUMP_PROGRESS        = CMD_SET_TEST + REMOTE_PUMP_PROCESS.getLocalizedCommandByte()

    val CMD_REMOTE_EMPTY_PROCESS             = CMD_SET_TEST + REMOTE_EMPTY_PROCESS.getLocalizedCommandByte()

    val CMD_REMOTE_READ_SENSORS              = CMD_SET_TEST + REMOTE_READ_SENSORS.getLocalizedCommandByte()
    val CMD_REMOTE_SET_ACTUATORS             = CMD_SET_TEST + REMOTE_SET_ACTUATORS.getLocalizedCommandByte()
    val CMD_REMOTE_SET_CALIBRATION           = CMD_SET_TEST + REMOTE_SET_CALIBRATION.getLocalizedCommandByte()
    val CMD_REMOTE_SET_PWM                   = CMD_SET_TEST + REMOTE_SET_PWM.getLocalizedCommandByte()
    val CMD_REMOTE_READ_REGRETION            = CMD_SET_TEST + REMOTE_READ_REGRETION.getLocalizedCommandByte()

    /**
     * Analog Teste (Placa Controle)
     */
    private fun buzzer(cmdHw: ByteArray): ByteArray {
        return CMD_HW_TEST + CMD_HW_DIGITAL_OUTPUT + CMD_BUZZER + cmdHw
    }

    fun buzzerON(): ByteArray {
        return buzzer(CMD_HW_ON)
    }

    fun buzzerOFF(): ByteArray {
        return buzzer(CMD_HW_OFF)
    }

    private fun cmdLed(led: ByteArray, cmdHw: ByteArray): ByteArray {
        return CMD_HW_TEST + CMD_HW_DIGITAL_OUTPUT + led + cmdHw
    }

    fun cmdLedON(led: ByteArray): ByteArray {
        return cmdLed(led, CMD_HW_ON)
    }

    fun cmdLedOFF(led: ByteArray): ByteArray {
        return cmdLed(led, CMD_HW_OFF)
    }


    /**
     * Analog Teste (Placa Medição)
     */
    private fun buzzerMed(cmdHw: ByteArray): ByteArray {
        return CMD_HW_TEST + CMD_HW_DIGITAL_OUTPUT_MED + CMD_BUZZER_MED + cmdHw
    }

    fun buzzerONMed(): ByteArray {
        return buzzerMed(CMD_HW_ON)
    }

    fun buzzerOFFMed(): ByteArray {
        return buzzerMed(CMD_HW_OFF)
    }

    private fun cmdMed(cmd: ByteArray, cmdHw: ByteArray): ByteArray {
        return CMD_HW_TEST + CMD_HW_DIGITAL_OUTPUT_MED + cmd + cmdHw
    }

    fun cmdONMed(cmd: ByteArray): ByteArray {
        return cmdMed(cmd, CMD_HW_ON)
    }

    fun cmdOFFMed(cmd: ByteArray): ByteArray {
        return cmdMed(cmd, CMD_HW_OFF)
    }

}