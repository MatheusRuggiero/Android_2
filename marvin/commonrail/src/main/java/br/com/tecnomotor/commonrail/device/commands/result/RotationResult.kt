package br.com.tecnomotor.commonrail.device.commands.result

import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestProcessForControl

class RotationResult(
    var typeOfTest: EnumControlTypeTest = EnumControlTypeTest.NONE,
    var process: EnumTestProcessForControl = EnumTestProcessForControl.NONE,
    var desiredRotation: Long = 0,
    var rotation: Long = 0,
    var temperature: Int = 0,
    var timeout: Long = 0
) : TestResult() {

    override fun deepCopy(value: Any) {
        super.deepCopy(value)
        value as RotationResult
        this.typeOfTest = value.typeOfTest
        this.process = value.process
        this.desiredRotation = value.desiredRotation
        this.rotation = value.rotation
        this.temperature = value.temperature
        this.timeout = value.timeout
    }

    /**
     *  0 Resposta do comando 19 = 59
     *  1 Tipo do teste
     *  2 Status do teste
     *  3 Erros do teste
     *  4 State = 0x20
     *  5 / 6 Rotação desejada
     *  7 / 8 Rotação atual
     *  9 Temperatura
     *  10 / 11 Timeout
     */
    override fun ofByteArray(values: ByteArray?): RotationResult {
        if ((values != null) && (values.size >= 12) && (EnumControlTypeTest.valueOf(values[1]) in
                    arrayOf(EnumControlTypeTest.RPM_TEST, EnumControlTypeTest.HARD_TEST))
        ) {
            super.ofByteArray(values)
            typeOfTest = EnumControlTypeTest.valueOf(values[1])
            process = EnumTestProcessForControl.valueOf(values[4])
            desiredRotation = getRotation(values[5].toUByte().toInt(), values[6].toUByte().toInt())
            rotation = getRotation(values[7].toUByte().toInt(), values[8].toUByte().toInt())
            temperature = values[9].toInt()
            timeout = getRotation(values[10].toUByte().toInt(), values[11].toUByte().toInt())
        }
        return this
    }

    private fun getRotation(mostSignificantValue: Int, leastSignificantValue: Int): Long {
        return (((mostSignificantValue shl (8)) + leastSignificantValue)).toLong()
    }

    fun clearValues() {
        this.rotation = 0
        this.temperature = 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RotationResult

        if (typeOfTest != other.typeOfTest) return false
        if (process != other.process) return false
        if (desiredRotation != other.desiredRotation) return false
        if (rotation != other.rotation) return false
        if (temperature != other.temperature) return false
        if (timeout != other.timeout) return false

        return true
    }

    fun notEquals(other: Any?): Boolean {
        return !equals(other)
    }

    override fun hashCode(): Int {
        var result = typeOfTest.hashCode()
        result = 31 * result + process.hashCode()
        result = 31 * result + desiredRotation.hashCode()
        result = 31 * result + rotation.hashCode()
        result = 31 * result + temperature
        result = 31 * result + timeout.hashCode()
        return result
    }

    override fun toString(): String {
        return "RotationResult(${super.toString()}, typeOfTest=$typeOfTest, process=$process, desiredRotation=$desiredRotation, rotation=$rotation, temperature=$temperature, timeout=$timeout)"
    }
}