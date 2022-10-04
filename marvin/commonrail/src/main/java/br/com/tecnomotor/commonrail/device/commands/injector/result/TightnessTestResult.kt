package br.com.tecnomotor.commonrail.device.commands.injector.result

import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.EnumLeakTestCondition
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus

class TightnessTestResult(
    state: EnumTestStatus,
    var condition: EnumLeakTestCondition = EnumLeakTestCondition.NONE,
    var warning: Boolean = false
) : TestResult(state) {

    fun deepCopy(resultadoDeTeste: TightnessTestResult) {
        super.deepCopy(resultadoDeTeste)
        this.condition = resultadoDeTeste.condition
    }

    override fun toString(): String {
        return "tightnessTestResult(condition=$condition, warning=$warning, ${super.toString()})"
    }

}