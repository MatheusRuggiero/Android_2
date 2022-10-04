package br.com.tecnomotor.commonrail.device.commands.injector.result

import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.control.ElectricalTestCondition
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import java.io.Serializable

class ElectricalTestResult(
    state: EnumTestStatus,
    var conditionA: ElectricalTestCondition = ElectricalTestCondition.NONE,
    var resistanceA: Double = 0.0,
    var conditionB: ElectricalTestCondition = ElectricalTestCondition.NONE,
    var resistanceB: Double = 0.0
) : TestResult(state), Serializable {

    override fun ofByteArray(values: ByteArray?):ElectricalTestResult {
        if ((values != null) && (values.size >= 15)) {
            var byteSig = 4
            var byteNSig = 5
            conditionA = ElectricalTestCondition.valueOf(values[12].toUByte().toInt())
            if (conditionA != ElectricalTestCondition.NOT_INSTALLED)
                resistanceA = getResistance(
                    values[byteSig].toInt(),
                    values[byteNSig].toInt()
                )
            conditionB = ElectricalTestCondition.valueOf(values[13].toUByte().toInt())
            if (conditionB != ElectricalTestCondition.NOT_INSTALLED) {
                resistanceB = getResistance(
                    values[byteSig + 2].toInt(),
                    values[byteNSig + 2].toInt()
                )
            }
        }
        return this
    }

    private fun getResistance(mostSignificantValue: Int, leastSignificantValue: Int): Double {
        return ((mostSignificantValue shl (8)) + leastSignificantValue).toDouble() / 100
    }

    fun deepCopy(resultadoDeTeste: ElectricalTestResult) {
        super.deepCopy(resultadoDeTeste)
        this.conditionA = resultadoDeTeste.conditionA
        this.conditionB = resultadoDeTeste.conditionB
        this.resistanceA = resultadoDeTeste.resistanceA
        this.resistanceB = resultadoDeTeste.resistanceB
    }

    fun getTextResistanceA():String {
        return "${this.resistanceA} Ohm"
    }

    fun getTextResistanceB():String {
        return "${this.resistanceB} Ohm"
    }

    fun toStringAll(): String {
        return super.toString() + " / ElectricalTestResult(conditionA=$conditionA, resistanceA=$resistanceA, conditionB=$conditionB, resistanceB=$resistanceB)"
    }

    override fun toString(): String {
        return "ElectricalTestResult(conditionA=$conditionA, resistenceA=$resistanceA, conditionB=$conditionB, resistanceB=$resistanceB)"
    }


}