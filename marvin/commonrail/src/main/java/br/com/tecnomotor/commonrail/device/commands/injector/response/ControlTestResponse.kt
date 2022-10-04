package br.com.tecnomotor.commonrail.device.commands.injector.response

class ControlTestResponse(
    response: ByteArray?
) : TestResponse(response) {

    fun getPressure(): Int {
        val response = getResponse()
        return if ((response != null) && (response.size >= 11)) {
            (((response[10].toUByte().toInt()) shl (8)) + response[11].toUByte().toInt())
        } else 0
    }

    fun getTemperature(): Int {
        val response = getResponse()
        if ((response != null) && (response.size >= 12)) {
            return response[12].toUByte().toInt()
        } else return 0
    }

    fun getHighVoltage(): Byte {
        val response = getResponse()
        if ((response != null) && (response.size >= 13)) {
            return response[13]
        } else return 0
    }
}