package br.com.tecnomotor.commonrail.device.commands.injector.response

import br.com.tecnomotor.commonrail.device.commands.injector.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.utils.control.ElectricalTestCondition
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus

class ElectricalTestResponse(
    response: ByteArray?
):TestResponse(response) {
    private var resultValue = ElectricalTestResult(EnumTestStatus.TEST_RUNNING)

    init {
        if ((response != null) && (response.size >= 15)) {
            var byteSig = 4
            var byteNSig = 5
            resultValue.conditionA = ElectricalTestCondition.valueOf(response[12].toUByte().toInt())
            if (resultValue.conditionA != ElectricalTestCondition.NOT_INSTALLED)
                resultValue.resistanceA = getResistance(
                    response[byteSig].toInt(),
                    response[byteNSig].toInt()
                )
            resultValue.conditionB = ElectricalTestCondition.valueOf(response[13].toUByte().toInt())
            if (resultValue.conditionB != ElectricalTestCondition.NOT_INSTALLED) {
                resultValue.resistanceB = getResistance(
                    response[byteSig + 2].toInt(),
                    response[byteNSig + 2].toInt()
                )
            }
        }
    }

    private fun getResistance(mostSignificantValue: Int, leastSignificantValue: Int): Double {
        return ((mostSignificantValue shl (8)) + leastSignificantValue).toDouble() / 100
    }

    fun getResult(): ElectricalTestResult = resultValue
}