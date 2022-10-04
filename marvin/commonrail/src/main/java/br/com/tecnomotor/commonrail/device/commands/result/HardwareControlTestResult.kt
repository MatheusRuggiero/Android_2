package br.com.tecnomotor.commonrail.device.commands.result

class HardwareControlTestResult(
    var anPressaoTransf: Long = 0,
    var anTensDac: Long = 0,
    var anCorrMag2: Long = 0,
    var anTensPz: Long = 0,
    var anCorrMag1: Long = 0,
    var anCorrPz: Long = 0,
    var anCorrDrv1: Long = 0,
    var anCorrDrv2: Long = 0,
    var anTensFbInj: Long = 0,
    var anFbBooster: Long = 0,
    var anSensTemp1: Int = 0,
    var anSensTemp2: Int = 0,
    var anPressaoTeste: Long = 0,
    var anPressaoRail: Long = 0,
    var anCorrExt1: Long = 0,
    var anCorrExt2: Long = 0,
    var anRotat: Long = 0
) : TestResult() {

    override fun deepCopy(value: Any) {
        super.deepCopy(value)
        value as HardwareControlTestResult
        this.anPressaoTransf = value.anPressaoTransf
        this.anTensDac = value.anTensDac
        this.anCorrMag2 = value.anCorrMag2
        this.anTensPz = value.anTensPz
        this.anCorrMag1 = value.anCorrMag1
        this.anCorrPz = value.anCorrPz
        this.anCorrDrv1 = value.anCorrDrv1
        this.anCorrDrv2 = value.anCorrDrv2
        this.anTensFbInj = value.anTensFbInj
        this.anFbBooster = value.anFbBooster
        this.anSensTemp1 = value.anSensTemp1
        this.anSensTemp2 = value.anSensTemp2
        this.anPressaoTeste = value.anPressaoTeste
        this.anPressaoRail = value.anPressaoRail
        this.anCorrExt1 = value.anCorrExt1
        this.anCorrExt2 = value.anCorrExt2
        this.anRotat = value.anRotat
    }

    /**
     *      0       Resposta do 19: 0x59
     *      1       Tipo do Teste
     *      2       Status do Teste
     *      3       Erros do Teste
     *      4/5     AN_PRESSAO_TRANSF
     *      6/7     AN_TENS_DAC
     *      8/9     AN_CORR_MAG2
     *      10/11    AN_TENS_PZ
     *      12/13   AN_CORR_MAG1
     *      14/15   AN_CORR_PZ
     *      16/17   AN_CORR_DRV1
     *      18/19   AN_CORR_DRV2
     *      20/21   AN_TENS_FB_INJ
     *      22/23   AN_FB_BOOSTER
     *      24      vazio
     *      25      AN_SENS_TEMP2
     *      26      vazio
     *      27      AN_SENS_TEMP1
     *      28/29   AN_PRESSAO_TESTE
     *      30/31   AN_PRESSAO_RAIL
     *      32/33   AN_CORR_EXT2
     *      34/35   AN_CORR_EXT1
     *      36/37   AN_ROTAT
     */
    override fun ofByteArray(values: ByteArray?): HardwareControlTestResult {
        if ((values != null) && (values.size >= 21)) {
            super.ofByteArray(values)
            anPressaoTransf = getValue(values[4].toUByte().toInt(), values[5].toUByte().toInt())
            anTensDac = getValue(values[6].toUByte().toInt(), values[7].toUByte().toInt())
            anCorrMag2 = getValue(values[8].toUByte().toInt(), values[9].toUByte().toInt())
            anTensPz = getValue(values[10].toUByte().toInt(), values[11].toUByte().toInt())
            anCorrMag1 = getValue(values[12].toUByte().toInt(), values[13].toUByte().toInt())
            anCorrPz = getValue(values[14].toUByte().toInt(), values[15].toUByte().toInt())
            anCorrDrv1 = getValue(values[16].toUByte().toInt(), values[17].toUByte().toInt())
            anCorrDrv2 = getValue(values[18].toUByte().toInt(), values[19].toUByte().toInt())
            anTensFbInj = getValue(values[20].toUByte().toInt(), values[21].toUByte().toInt())
            anFbBooster = getValue(values[22].toUByte().toInt(), values[23].toUByte().toInt())
            anSensTemp2 = values[25].toInt()
            anSensTemp1 = values[27].toInt()
            anPressaoTeste = getValue(values[28].toUByte().toInt(), values[29].toUByte().toInt())
            anPressaoRail = getValue(values[30].toUByte().toInt(), values[31].toUByte().toInt())
            anCorrExt2 = getValue(values[32].toUByte().toInt(), values[33].toUByte().toInt())
            anCorrExt1 = getValue(values[34].toUByte().toInt(), values[35].toUByte().toInt())
            anRotat = getValue(values[36].toUByte().toInt(), values[37].toUByte().toInt())
        }
        return this
    }

    private fun getValue(mostSignificantValue: Int, leastSignificantValue: Int): Long {
        return (((mostSignificantValue shl (8)) + leastSignificantValue)).toLong()
    }

    private fun getValue(mostSignificantValue: Byte, leastSignificantValue: Byte): Long {
        return (((mostSignificantValue * 256) + leastSignificantValue)).toLong()
    }

}