package br.com.tecnomotor.commonrail.device.commands.result

import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import java.io.Serializable

open class TestResult(
    var status: EnumTestStatus = EnumTestStatus.NONE,
    var error: ECommonRailCommandException = ECommonRailCommandException(0)
) : Serializable {

    open fun ofByteArray(values: ByteArray?): TestResult {
        if ((values != null) && (values.size >= 21)) {
            status = EnumTestStatus.valueOf(values[2].toUByte().toInt())
            error = ECommonRailCommandException(values[3].toUByte().toInt())
        }
        return this
    }

    open fun deepCopy(value: Any) {
        value as TestResult
        this.status = value.status
        this.error = ECommonRailCommandException(value.error.getCommandError())
    }

    override fun toString(): String {
        return "TestResult(status=$status, error=$error)"
    }

}