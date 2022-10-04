package br.com.tecnomotor.commonrail.device.commands.result

/**
 * Utilizada para obter a temperatura de testes "getTemperatureSetting" da placa de controle
 */
class TemperatureResult(
    var minimum: Int = 0,
    var average: Int = 0,
    var maximum: Int = 0
) {
    fun ofByteArray(values: ByteArray): TemperatureResult {
        if (values.size > 19) {
            minimum = values[14].toInt()
            average = values[16].toInt()
            maximum = values[19].toInt()
        }
        return this
    }
}