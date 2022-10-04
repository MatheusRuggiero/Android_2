package br.com.tecnomotor.commonrail.device.commands.injector.response

import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus

open class TestResponse(
    private val response: ByteArray?
) {
    var status: EnumTestStatus = EnumTestStatus.NONE
    var error: ECommonRailCommandException = ECommonRailCommandException(0)

    init {
        if ((response != null) && (response.size >= 15)) {
            status = EnumTestStatus.valueOf(response[2].toUByte().toInt())
            error = ECommonRailCommandException(response[3].toUByte().toInt())
        }
    }

    fun getResponse(): ByteArray? = response

    override fun toString(): String {
        return "TestResponse(state=$status, response=${response?.contentToString()}, error=$error)"
    }
}