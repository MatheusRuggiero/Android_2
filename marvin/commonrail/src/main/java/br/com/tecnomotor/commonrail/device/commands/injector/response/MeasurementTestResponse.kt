package br.com.tecnomotor.commonrail.device.commands.injector.response

class MeasurementTestResponse(
    response: ByteArray?
) : TestResponse(response) {

    fun getThresholdCH1(): Byte {
        val response = getResponse()
        if ((response != null) && (response.size >= 27)) {
            return response[27]
        } else return 0
    }

    fun getFailCH1(): Byte {
        val response = getResponse()
        if ((response != null) && (response.size >= 28)) {
            return response[28]
        } else return 0
    }

    fun getThresholdCH2(): Byte {
        val response = getResponse()
        if ((response != null) && (response.size >= 52)) {
            return response[52]
        } else return 0
    }

    fun getFailCH2(): Byte {
        val response = getResponse()
        if ((response != null) && (response.size >= 53)) {
            return response[53]
        } else return 0
    }
}