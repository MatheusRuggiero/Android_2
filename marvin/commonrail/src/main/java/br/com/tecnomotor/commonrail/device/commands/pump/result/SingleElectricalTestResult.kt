package br.com.tecnomotor.commonrail.device.commands.pump.result

import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.control.ElectricalTestCondition
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import java.io.Serializable

class SingleElectricalTestResult(
    status: EnumTestStatus = EnumTestStatus.NONE,
    var typeOfTest: EnumControlTypeTest = EnumControlTypeTest.NONE,
    var condition: ElectricalTestCondition = ElectricalTestCondition.NONE,
    var resistance: Double = 0.0,
) : TestResult(status), Serializable {

    override fun ofByteArray(values: ByteArray?):SingleElectricalTestResult {
        if ((values != null) && (values.size >= 6)) {
            typeOfTest = EnumControlTypeTest.valueOf(values[1])
            condition = ElectricalTestCondition.valueOf(values[6].toUByte().toInt())
            if (condition != ElectricalTestCondition.NOT_INSTALLED)
                resistance = getResistance(
                    values[4].toUByte().toInt(),
                    values[5].toUByte().toInt()
                )
            status = EnumTestStatus.valueOf(values[2].toUByte().toInt())
            error = ECommonRailCommandException(values[3].toUByte().toInt())
        }
        return this
    }

    private fun getResistance(mostSignificantValue: Int, leastSignificantValue: Int): Double {
        return ((mostSignificantValue shl (8)) + leastSignificantValue).toDouble() / 1000
    }

    fun copy(): SingleElectricalTestResult {
        val res = SingleElectricalTestResult(this.status)
        res.typeOfTest = this.typeOfTest
        res.condition = this.condition
        res.resistance = this.resistance
        res.error = this.error
        return res
    }

    override fun toString(): String {
        return "${super.toString()}, condition=$condition, resistance=$resistance"
    }
}