package br.com.tecnomotor.commonrail.device.utils.measurement

import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus

class MeasurementChannel(
    var channel: Channel,
    var process: EnumTestProcessForMeasurement = EnumTestProcessForMeasurement.NONE,
    var status: EnumTestStatus = EnumTestStatus.NONE,
    var error: Int = 0,
    var centerOfMass: Double = 0.0,
    var flowMls: Double = 0.0,
    var flowMlk: Double = 0.0,
    var coefR: Double = 0.0,
    var coefLinear: Double = 0.0,
    var conditionedVessel: Boolean = false,
    private var parameters: ByteArray = byteArrayOf()
) {

    private companion object {
        val MEASUREMENT_CM_10000 = byteArrayOf(0x27.toByte(), 0x10.toByte())
        val MEASUREMENT_CM_5000 = byteArrayOf(0x13.toByte(), 0x88.toByte())
    }

    fun loadParametrizacao(
        process: EnumTestProcessForMeasurement,
        frequency: Int,
        maximumFlow: Double,
        lowerThreshold: Int,
        upperThreshold: Int
    ) {
        parameters = byteArrayOf(process.value)
        parameters += if (maximumFlow > lowerThreshold) MEASUREMENT_CM_10000 else MEASUREMENT_CM_5000
        parameters += if (maximumFlow <= upperThreshold) MEASUREMENT_CM_5000 else MEASUREMENT_CM_10000
        parameters += byteArrayOf((frequency shr (8)).toByte(), frequency.toByte()) //Frequencia
        parameters += byteArrayOf(0x00.toByte(), 0x40.toByte()) //Timeout
    }

    fun getParametros(): ByteArray {
        return parameters
    }
}
