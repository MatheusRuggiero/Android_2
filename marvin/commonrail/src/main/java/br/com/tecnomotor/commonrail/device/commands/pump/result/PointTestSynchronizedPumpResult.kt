package br.com.tecnomotor.commonrail.device.commands.pump.result

import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestProcessForControl

/**
 * Armazena o Resultado de um Ponto de Teste de Bombas Sincronizadas
 */
class PointTestSynchronizedPumpResult(
    status: EnumTestStatus = EnumTestStatus.NONE,
    var typeTest: EnumControlTypeTest = EnumControlTypeTest.NONE,
    var process: EnumTestProcessForControl = EnumTestProcessForControl.NONE,
    var testTime: Long = 0,
    var pressure: Long = 0,
    var rotation: Long = 0,
    var temperature: Long = 0,
    var value5: Long = 0,
    var value6: Long = 0,
    var value7: Long = 0,
    var value8: Long = 0,
    var value9: Long = 0,
    var value10: Long = 0,
    var value11: Long = 0,
    var value12: Long = 0
) : TestResult(status) {

    var errorMeasurement: ECommonRailCommandException = ECommonRailCommandException(0)
    override fun deepCopy(value: Any) {
        super.deepCopy(value)
        value as PointTestSynchronizedPumpResult
        this.typeTest = value.typeTest
        this.process = value.process
        this.testTime = value.testTime
        this.pressure = value.pressure
        this.rotation = value.rotation
        this.temperature = value.temperature
        this.value5 = value.value5
        this.value6 = value.value6
        this.value7 = value.value7
        this.value8 = value.value8
        this.value9 = value.value9
        this.value10 = value.value10
        this.value11 = value.value11
        this.value12 = value.value12
        this.errorMeasurement = value.errorMeasurement
    }

    /**
     *      0           byte 59
     *      1	        Tipo do teste
     *      2	        Status do teste
     *      3	        Erros do teste
     *      ...
     */
    override fun ofByteArray(values: ByteArray?): PointTestSynchronizedPumpResult {
        if ((values != null) && (values.size >= 22 /*TODO: verificar esse tamanho*/) && (EnumControlTypeTest.valueOf(values[1]) == EnumControlTypeTest.SYNC_PUMP)) {
            super.ofByteArray(values)
            testTime = getValue(values[4].toUByte().toInt(), values[5].toUByte().toInt())
            pressure = getValue(values[6].toUByte().toInt(), values[7].toUByte().toInt())
            rotation = getValue(values[8].toUByte().toInt(), values[9].toUByte().toInt())
            temperature = getValue(values[10].toUByte().toInt(), values[11].toUByte().toInt())
            value5 = getValue(values[12].toUByte().toInt(), values[13].toUByte().toInt())
            value6 = getValue(values[14].toUByte().toInt(), values[15].toUByte().toInt())
            value7 = getValue(values[16].toUByte().toInt(), values[17].toUByte().toInt())
            value8 = getValue(values[18].toUByte().toInt(), values[19].toUByte().toInt())
            value9 = getValue(values[20].toUByte().toInt(), values[21].toUByte().toInt())
            value10 = getValue(values[22].toUByte().toInt(), values[23].toUByte().toInt())
            value11 = getValue(values[24].toUByte().toInt(), values[25].toUByte().toInt())
            value12 = getValue(values[26].toUByte().toInt(), values[27].toUByte().toInt())
        }
        return this
    }

    private fun getValue(mostSignificantValue: Int, leastSignificantValue: Int): Long {
        return (((mostSignificantValue shl (8)) + leastSignificantValue)).toLong()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PointTestSynchronizedPumpResult

        if (pressure != other.pressure) return false
        if (rotation != other.rotation) return false
        if (temperature != other.temperature) return false
        if (value5 != other.value5) return false
        if (value6 != other.value6) return false
        if (value7 != other.value7) return false
        if (value8 != other.value8) return false
        if (value9 != other.value9) return false
        if (value10 != other.value10) return false
        if (value11 != other.value11) return false
        if (value12 != other.value12) return false
        if (errorMeasurement != other.errorMeasurement) return false

        return true
    }

    override fun hashCode(): Int {
        var result = typeTest.hashCode()
        result = 31 * result + process.hashCode()
        result = 31 * result + testTime.hashCode()
        result = 31 * result + pressure.hashCode()
        result = 31 * result + rotation.hashCode()
        result = 31 * result + temperature.hashCode()
        result = 31 * result + value5.hashCode()
        result = 31 * result + value6.hashCode()
        result = 31 * result + value7.hashCode()
        result = 31 * result + value8.hashCode()
        result = 31 * result + value9.hashCode()
        result = 31 * result + value10.hashCode()
        result = 31 * result + value11.hashCode()
        result = 31 * result + value12.hashCode()
        result = 31 * result + errorMeasurement.hashCode()
        return result
    }
}