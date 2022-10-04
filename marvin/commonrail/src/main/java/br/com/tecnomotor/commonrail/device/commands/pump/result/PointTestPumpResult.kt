package br.com.tecnomotor.commonrail.device.commands.pump.result

import android.graphics.Color
import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestProcessForControl

/**
 * Armazena o Resultado de um Ponto de Teste
 */
class PointTestPumpResult(
    status: EnumTestStatus = EnumTestStatus.NONE,
    var typeTest: EnumControlTypeTest = EnumControlTypeTest.NONE,
    var process: EnumTestProcessForControl = EnumTestProcessForControl.NONE,
    var testTime: Long = 0,
    var frequencyExt1: Int = 0,
    var frequencyExt2: Int = 0,
    var desiredPressure: Long = 0,
    var pressure: Long = 0,
    var desiredFeedPressure: Long = 0,
    var feedPressure: Long = 0,
    var checkRotation: Boolean = false,
    var desiredRotation: Long = 0,
    var rotation: Long = 0,
    var desiredExt1Current: Double = 0.0,
    var Ext1Current: Double = 0.0,
    var desiredExt2Current: Double = 0.0,
    var Ext2Current: Double = 0.0,
    var temperature: Int = 0,
    var mainFlow: Double = 0.0,
    var minimumMainFlow: Double = 0.0,
    var maximumMainFlow: Double = 0.0,
    var returnFlow: Double = 0.0,
    var minimumReturnFlow: Double = 0.0,
    var maximumReturnFlow: Double = 0.0,
) : TestResult(status) {
    var errorMeasurement: ECommonRailCommandException = ECommonRailCommandException(0)
    override fun deepCopy(value: Any) {
        super.deepCopy(value)
        value as PointTestPumpResult
        this.typeTest = value.typeTest
        this.process = value.process
        this.testTime = value.testTime
        this.frequencyExt1 = value.frequencyExt1
        this.frequencyExt2 = value.frequencyExt2
        this.desiredPressure = value.desiredPressure
        this.pressure = value.pressure
        this.desiredFeedPressure = value.desiredFeedPressure
        this.feedPressure = value.feedPressure
        this.checkRotation = value.checkRotation
        this.desiredRotation = value.desiredRotation
        this.rotation = value.rotation
        this.desiredExt1Current = value.desiredExt1Current
        this.Ext1Current = value.Ext1Current
        this.desiredExt2Current = value.desiredExt2Current
        this.Ext2Current = value.Ext2Current
        this.temperature = value.temperature
        this.mainFlow = value.mainFlow
        this.minimumMainFlow = value.minimumMainFlow
        this.maximumMainFlow = value.maximumMainFlow
        this.returnFlow = value.returnFlow
        this.minimumReturnFlow = value.minimumReturnFlow
        this.maximumReturnFlow = value.maximumReturnFlow
        this.errorMeasurement = value.errorMeasurement
    }

    /**
     *      0           byte 59
     *      1	        Tipo do teste
     *      2	        Status do teste
     *      3	        Erros do teste
     *      4	        State
     *      5 / 6	    Pressão desejada
     *      7 / 8	    Pressão do rail
     *      9 / 10	    Corrente ext 1 desejada
     *      11 / 12	    corrente real ext 1
     *      13 / 14	    Corrente ext 2 desejada
     *      15 / 16	    corrente real ext 2
     *      17 / 18	    Rotação desejada
     *      19 / 20	    Rotação atual
     *      21	        Temperatura
     */
    override fun ofByteArray(values: ByteArray?): PointTestPumpResult {
        if ((values != null) && (values.size >= 22) && (EnumControlTypeTest.valueOf(values[1]) == EnumControlTypeTest.PUMP)) {
            super.ofByteArray(values)
            typeTest = EnumControlTypeTest.valueOf(values[1])
            process = EnumTestProcessForControl.valueOf(values[4])
            desiredPressure = getValue(values[5].toUByte().toInt(),values[6].toUByte().toInt())
            pressure = getValue(values[7].toUByte().toInt(),values[8].toUByte().toInt())
            desiredExt1Current = getCurrentValue(values[9].toUByte().toInt(), values[10].toUByte().toInt())
            Ext1Current = getCurrentValue(values[11].toUByte().toInt(), values[12].toUByte().toInt())
            desiredExt2Current = getCurrentValue(values[13].toUByte().toInt(), values[14].toUByte().toInt())
            Ext2Current = getCurrentValue(values[15].toUByte().toInt(), values[16].toUByte().toInt())
            desiredRotation = getValue(values[17].toUByte().toInt(),values[18].toUByte().toInt())
            rotation = getValue(values[19].toUByte().toInt(),values[20].toUByte().toInt())
            temperature = values[21].toInt()
        }
        return this
    }

    private fun getValue(mostSignificantValue: Int, leastSignificantValue: Int): Long {
        return (((mostSignificantValue shl (8)) + leastSignificantValue)).toLong()
    }

    /**
     * Obtém um valor double a partir de dois bytes e divide por cem
     */
    private fun getCurrentValue(mostSignificantValue: Int, leastSignificantValue: Int): Double {
        return ((((mostSignificantValue shl (8)) + leastSignificantValue)).toDouble() / 100.0)
    }

    fun getVazaoPrincipalColor(): Int {
        if (mainFlow < minimumMainFlow)
            return Color.parseColor("#00678D")
        else if (mainFlow > maximumMainFlow)
            return Color.parseColor("#00678D")
        return Color.parseColor("#00FF19")
    }

    fun getCalcFlowRotation(): Double {
        return ((mainFlow / (rotation * 60)) * 100000)
    }

    fun getVazaoRetornoColor(): Int {
        if (returnFlow < minimumReturnFlow)
            return Color.parseColor("#00678D")
        else if (returnFlow > maximumReturnFlow)
            return Color.parseColor("#00678D")
        return Color.parseColor("#00FF19")
    }

    override fun toString(): String {
        return "PointTestResult(${super.toString()}, typeTest=$typeTest, process=$process, testTime=$testTime, frequencyExt1=$frequencyExt1, frequencyExt2=$frequencyExt2, desiredPressure=$desiredPressure, pressure=$pressure, desiredFeedPressure=$desiredFeedPressure, feedPressure=$feedPressure, checkRotation=$checkRotation, desiredRotation=$desiredRotation, rotation=$rotation, desiredExt1Current=$desiredExt1Current, Ext1Current=$Ext1Current, desiredExt2Current=$desiredExt2Current, Ext2Current=$Ext2Current, temperature=$temperature, mainFlow=$mainFlow, minimumMainFlow=$minimumMainFlow, maximumMainFlow=$maximumMainFlow, returnFlow=$returnFlow, minimumReturnFlow=$minimumReturnFlow, maximumReturnFlow=$maximumReturnFlow, errorMeasurement=$errorMeasurement)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PointTestPumpResult

        if (typeTest != other.typeTest) return false
        if (process != other.process) return false
        if (testTime != other.testTime) return false
        if (frequencyExt1 != other.frequencyExt1) return false
        if (frequencyExt2 != other.frequencyExt2) return false
        if (desiredPressure != other.desiredPressure) return false
        if (pressure != other.pressure) return false
        if (desiredFeedPressure != other.desiredFeedPressure) return false
        if (feedPressure != other.feedPressure) return false
        if (checkRotation != other.checkRotation) return false
        if (desiredRotation != other.desiredRotation) return false
        if (rotation != other.rotation) return false
        if (desiredExt1Current != other.desiredExt1Current) return false
        if (Ext1Current != other.Ext1Current) return false
        if (desiredExt2Current != other.desiredExt2Current) return false
        if (Ext2Current != other.Ext2Current) return false
        if (temperature != other.temperature) return false
        if (mainFlow != other.mainFlow) return false
        if (minimumMainFlow != other.minimumMainFlow) return false
        if (maximumMainFlow != other.maximumMainFlow) return false
        if (returnFlow != other.returnFlow) return false
        if (minimumReturnFlow != other.minimumReturnFlow) return false
        if (maximumReturnFlow != other.maximumReturnFlow) return false
        if (errorMeasurement != other.errorMeasurement) return false

        return true
    }

    fun notEquals(other: Any?): Boolean {
        return !equals(other)
    }

    override fun hashCode(): Int {
        var result = typeTest.hashCode()
        result = 31 * result + process.hashCode()
        result = 31 * result + testTime.hashCode()
        result = 31 * result + frequencyExt1
        result = 31 * result + frequencyExt2
        result = 31 * result + desiredPressure.hashCode()
        result = 31 * result + pressure.hashCode()
        result = 31 * result + desiredFeedPressure.hashCode()
        result = 31 * result + feedPressure.hashCode()
        result = 31 * result + checkRotation.hashCode()
        result = 31 * result + desiredRotation.hashCode()
        result = 31 * result + rotation.hashCode()
        result = 31 * result + desiredExt1Current.hashCode()
        result = 31 * result + Ext1Current.hashCode()
        result = 31 * result + desiredExt2Current.hashCode()
        result = 31 * result + Ext2Current.hashCode()
        result = 31 * result + temperature
        result = 31 * result + mainFlow.hashCode()
        result = 31 * result + minimumMainFlow.hashCode()
        result = 31 * result + maximumMainFlow.hashCode()
        result = 31 * result + returnFlow.hashCode()
        result = 31 * result + minimumReturnFlow.hashCode()
        result = 31 * result + maximumReturnFlow.hashCode()
        result = 31 * result + errorMeasurement.hashCode()
        return result
    }

}