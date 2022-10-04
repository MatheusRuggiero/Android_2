package br.com.tecnomotor.commonrail.device.commands.injector.result

import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestProcessForControl

/**
 * Armazena o Resultado de um Ponto de Teste de Injetor
 */
class PointTestInjectorResult(
    status: EnumTestStatus = EnumTestStatus.NONE,
    var typeTest: EnumControlTypeTest = EnumControlTypeTest.NONE,
    var process: EnumTestProcessForControl = EnumTestProcessForControl.NONE,
    var testTime: Long = 0,
    var pressure: Long = 0,
    var rotation: Long = 0,
    var temperature: Int = 0,
    var vBooster: Long = 0,
    var piezoCurrent: Long = 0,
    var piezoVoltage: Long = 0
) : TestResult(status = status) {

    override fun deepCopy(value: Any) {
        super.deepCopy(value)
        value as PointTestInjectorResult

        this.typeTest = value.typeTest
        this.process = value.process
        this.testTime = value.testTime
        this.pressure = value.pressure
        this.rotation = value.rotation
        this.temperature = value.temperature
        this.vBooster = value.vBooster
        this.piezoCurrent = value.piezoCurrent
        this.piezoVoltage = value.piezoVoltage
    }

    /**
     *      0           byte 59
     *      1	        Tipo do teste   // vem pelo TestResult
     *      2	        Status do teste // vem pelo TestResult
     *      3	        Erros do teste  // vem pelo TestResult
     *      4 / 5	    Tempo do processo injetor
     *      6 / 7       Pressão
     *      8 / 9       Rotação
     *      10          Temperatura
     *      11 / 12     VBooster
     *      13 / 14     Corrente piezo
     *      15 / 16     Tensão piezo
     */
    override fun ofByteArray(values: ByteArray?): PointTestInjectorResult {
        if ((values != null) && (values.size >= 17) && (EnumControlTypeTest.valueOf(values[1]) == EnumControlTypeTest.INJECTOR_ELECTRIC_TEST)) {
            super.ofByteArray(values)

            typeTest = EnumControlTypeTest.valueOf(values[1]) // TODO: jogar isso para o ResultTest
            testTime = getValue(values[4].toUByte().toInt(), values[5].toUByte().toInt())
            pressure = getValue(values[6].toUByte().toInt(), values[7].toUByte().toInt())
            rotation = getValue(values[8].toUByte().toInt(), values[9].toUByte().toInt())
            temperature = values[10].toInt()
            vBooster = getValue(values[11].toUByte().toInt(), values[12].toUByte().toInt())
            piezoCurrent = getValue(values[13].toUByte().toInt(), values[14].toUByte().toInt())
            piezoVoltage = getValue(values[15].toUByte().toInt(), values[16].toUByte().toInt())
        }
        return this
    }

    private fun getValue(mostSignificantValue: Int, leastSignificantValue: Int): Long {
        return (((mostSignificantValue shl (8)) + leastSignificantValue)).toLong()
    }

    override fun toString(): String {
        return "PointTestInjectorResult(typeTest=$typeTest, process=$process, testTime=$testTime, pressure=$pressure, rotation=$rotation, temperature=$temperature, vBooster=$vBooster, piezoCurrent=$piezoCurrent, piezoVoltage=$piezoVoltage)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PointTestInjectorResult

        if (typeTest != other.typeTest) return false
        if (process != other.process) return false
        if (testTime != other.testTime) return false
        if (pressure != other.pressure) return false
        if (rotation != other.rotation) return false
        if (temperature != other.temperature) return false
        if (vBooster != other.vBooster) return false
        if (piezoCurrent != other.piezoCurrent) return false
        if (piezoVoltage != other.piezoVoltage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = typeTest.hashCode()
        result = 31 * result + process.hashCode()
        result = 31 * result + testTime.hashCode()
        result = 31 * result + pressure.hashCode()
        result = 31 * result + rotation.hashCode()
        result = 31 * result + temperature
        result = 31 * result + vBooster.hashCode()
        result = 31 * result + piezoCurrent.hashCode()
        result = 31 * result + piezoVoltage.hashCode()
        return result
    }

}