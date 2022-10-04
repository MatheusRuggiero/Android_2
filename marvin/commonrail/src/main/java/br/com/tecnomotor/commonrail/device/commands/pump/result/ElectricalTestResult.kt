package br.com.tecnomotor.commonrail.device.commands.pump.result

import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import java.io.Serializable

class ElectricalTestResult(
    status: EnumTestStatus
) : TestResult(status), Serializable {
    var testResult: ArrayList<SingleElectricalTestResult> = arrayListOf()

    fun deepCopy(resultadoDeTeste: ElectricalTestResult) {
        super.deepCopy(resultadoDeTeste)
        testResult = arrayListOf()
        resultadoDeTeste.testResult.forEach {
            testResult.add(it.copy())
        }
    }

    fun clear() {
        this.status = EnumTestStatus.NONE
        this.error = ECommonRailCommandException(0)
        this.testResult.clear()
    }

    override fun toString(): String {
        return "ElectricalTestResult(${super.toString()}, testResult=$testResult)"
    }


}