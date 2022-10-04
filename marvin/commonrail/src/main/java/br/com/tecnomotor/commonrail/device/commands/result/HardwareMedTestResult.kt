package br.com.tecnomotor.commonrail.device.commands.result

class HardwareMedTestResult(
    var anSensTemp: Long = 0,
    var anDgt: Long = 0,
) : TestResult() {

    override fun deepCopy(value: Any) {
        super.deepCopy(value)
        value as HardwareMedTestResult
        this.anSensTemp = value.anSensTemp
        this.anDgt = value.anDgt
    }

    /**
     *      0           Resposta do 19: 0x59
     *      1           Tipo do Teste
     *      2           Status do Teste
     *      3           Erros do Teste
     *      4/5         AN_SENS_TEMP
     *      6/7/8/9     AN_DGT
     */
    override fun ofByteArray(values: ByteArray?): HardwareMedTestResult {
        if ((values != null) && (values.size >= 12)) {
            super.ofByteArray(values)
            anSensTemp = getValue(values[4].toUByte().toInt(), values[5].toUByte().toInt())
            anDgt = getValue(values.copyOfRange(6, 10))
        }
        return this
    }

    private fun getValue(mostSignificantValue: Int, leastSignificantValue: Int): Long {
        return (((mostSignificantValue shl (8)) + leastSignificantValue)).toLong()
    }

    fun getValue(values: ByteArray): Long {
        return (values[0].toUByte().toLong() * 0x1000000) + (values[1].toUByte().toLong() * 0x10000) +
                (values[2].toUByte().toLong() * 0x100) + (values[3].toUByte().toLong())
    }

}