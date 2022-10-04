package br.com.tecnomotor.commonrail.device.commands.result

import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumTestProcessForMeasurement

/**
 * 0 Resposta do comando 19 = 59
 * 1	                Tipo teste CH1
 * 2	                Status CH1
 * 3	                Erro CH1
 * 4 / 5	            Centro Massa CH1
 * 6 / 7 / 8 / 9        Vazão ml/s CH1
 * 10 / 11 / 12 / 13	Vazão ml/kst CH1
 * 14 / 15 / 16 / 17	Coeficiente R²
 * 18 / 19 / 20 / 21	Coeficiente Linear CH1
 * 22	                Vazio
 * 23	                Fototransistores ativados CH1
 * 24	                Contador de falhas CH1
 * 25	                Tipo teste CH2
 * 26	                Status CH2
 * 27	                Erro CH2
 * 28 / 29	            Centro Massa CH2
 * 30 / 31 / 32 / 33	Vazão ml/s CH2
 * 34 / 35 / 36 / 37	Vazão ml/kst CH2
 * 38 / 39 / 40 / 41	Coeficiente R²
 * 42 / 43 / 44 / 45	Coeficiente Linear CH2
 * 46	                Opacidade fluido
 * 47	                Fototransistores ativados CH2
 * 48	                Contador de falhas CH2
 * 49	                Tipo teste CH3
 * 50	                Status CH3
 * 51	                Erro CH3
 * 52	                Vazio
 * 53	                Vazio
 * 54 / 55 / 56 / 57    Vazão L/H CH3
 * 58 / 59 / 60 / 61	Vazão ml/kst CH3
 */
class MeasurementResult(
    var testTypeCH1: EnumTestProcessForMeasurement = EnumTestProcessForMeasurement.NONE,
    var statusCH1: EnumTestStatus = EnumTestStatus.NONE,
    var errorCH1: ECommonRailCommandException = ECommonRailCommandException(0),
    var centerOfMassCH1: Double = 0.0,
    var flowMlsCH1: Double = 0.0,
    var flowMlkCH1: Double = 0.0,
    var coefRCH1: Double = 0.0,
    var linearCoefCH1: Double = 0.0,
    var photoActivatedCH1: Int = 0,
    var failureCounterCH1: Int = 0,
    var testTypeCH2: EnumTestProcessForMeasurement = EnumTestProcessForMeasurement.NONE,
    var statusCH2: EnumTestStatus = EnumTestStatus.NONE,
    var errorCH2: ECommonRailCommandException = ECommonRailCommandException(0),
    var centerOfMassCH2: Double = 0.0,
    var flowMlsCH2: Double = 0.0,
    var flowMlkCH2: Double = 0.0,
    var coefRCH2: Double = 0.0,
    var linearCoefCH2: Double = 0.0,
    var photoActivatedCH2: Int = 0,
    var failureCounterCH2: Int = 0,
    var fluidOpacity: Int = 0,
    var testTypeCH3: EnumTestProcessForMeasurement = EnumTestProcessForMeasurement.NONE,
    var statusCH3: EnumTestStatus = EnumTestStatus.NONE,
    var errorCH3: ECommonRailCommandException = ECommonRailCommandException(0),
    var flowLHCH3: Double = 0.0,
    var flowMlkCH3: Double = 0.0,
    var vazio22: Int = 0,
    var vazio52: Int = 0,
    var vazio53: Int = 0
): TestResult() {
    override fun ofByteArray(values: ByteArray?):MeasurementResult {
        if ((values != null) && (values.size >= 62)) {
            testTypeCH1 = EnumTestProcessForMeasurement.valueOf(values[1])
            statusCH1 = EnumTestStatus.valueOf(values[2].toUByte().toInt())
            errorCH1 = ECommonRailCommandException(values[3].toUByte().toInt())
            centerOfMassCH1 = (getValue(values.copyOfRange(4, 6)).toDouble() / 100.00)
            flowMlsCH1 = (getValue(values.copyOfRange(6, 10)).toDouble() / 10000.00)
            flowMlkCH1 = (getValue(values.copyOfRange(10, 14)).toDouble() / 1000000.00)
            coefRCH1 = (getValue(values.copyOfRange(14, 18)).toDouble() / 100.00)
            linearCoefCH1 = (getValue(values.copyOfRange(18, 22)).toDouble() / 100.00)
            photoActivatedCH1 = values[23].toUByte().toInt()
            failureCounterCH1 = values[24].toUByte().toInt()
            testTypeCH2 = EnumTestProcessForMeasurement.valueOf(values[25])
            statusCH2 = EnumTestStatus.valueOf(values[26].toUByte().toInt())
            errorCH2 = ECommonRailCommandException(values[27].toUByte().toInt())
            centerOfMassCH2 = (getValue(values.copyOfRange(28, 30)).toDouble() / 100.00)
            flowMlsCH2 = (getValue(values.copyOfRange(30, 34)).toDouble() / 10000.00)
            flowMlkCH2 = (getValue(values.copyOfRange(34, 38)).toDouble() / 1000000.00)
            coefRCH2 = (getValue(values.copyOfRange(38, 42)).toDouble() / 100.00)
            linearCoefCH2 = (getValue(values.copyOfRange(42, 46)).toDouble() / 100.00)
            fluidOpacity = values[46].toUByte().toInt()
            photoActivatedCH2 = values[47].toUByte().toInt()
            failureCounterCH2 = values[48].toUByte().toInt()
            testTypeCH3 = EnumTestProcessForMeasurement.valueOf(values[49])
            statusCH3 = EnumTestStatus.valueOf(values[50].toUByte().toInt())
            errorCH3 = ECommonRailCommandException(values[51].toUByte().toInt())
            flowLHCH3 = (getValue(values.copyOfRange(54, 58)).toDouble() / 1000.00)
            flowMlkCH3 = (getValue(values.copyOfRange(58, 62)).toDouble() / 1000.00)
            status = statusCH1
            error = errorCH1
            vazio22 = values[22].toUByte().toInt()
            vazio52 = values[52].toUByte().toInt()
            vazio53 = values[53].toUByte().toInt()
        }
        return this
    }

    override fun deepCopy(value: Any) {
        super.deepCopy(value)
        value as MeasurementResult
        testTypeCH1 = value.testTypeCH1
        statusCH1 = value.statusCH1
        errorCH1 = ECommonRailCommandException(value.errorCH1.getCommandError())
        centerOfMassCH1 = value.centerOfMassCH1
        flowMlsCH1 = value.flowMlsCH1
        flowMlkCH1 = value.flowMlkCH1
        coefRCH1 = value.coefRCH1
        linearCoefCH1 = value.linearCoefCH1
        photoActivatedCH1 = value.photoActivatedCH1
        failureCounterCH1 = value.failureCounterCH1
        testTypeCH2 = value.testTypeCH2
        statusCH2 = value.statusCH2
        errorCH2 = ECommonRailCommandException(value.errorCH2.getCommandError())
        centerOfMassCH2 = value.centerOfMassCH2
        flowMlsCH2 = value.flowMlsCH2
        flowMlkCH2 = value.flowMlkCH2
        coefRCH2 = value.coefRCH2
        linearCoefCH2 = value.linearCoefCH2
        photoActivatedCH2 = value.photoActivatedCH2
        failureCounterCH2 = value.failureCounterCH2
        fluidOpacity = value.fluidOpacity
        testTypeCH3 = value.testTypeCH3
        statusCH3 = value.statusCH3
        errorCH3 = ECommonRailCommandException(value.errorCH3.getCommandError())
        flowLHCH3 = value.flowLHCH3
        flowMlkCH3 = value.flowMlkCH3
        vazio22 = value.vazio22
        vazio52 = value.vazio52
        vazio53 = value.vazio53
    }

    fun getValue(values: ByteArray): Int {
        //Log.w("getValue", "getValue(values=${values.contentToString()})")
        return when (values.size) {
            4 -> (values[0].toUByte().toInt() * 0x1000000) + (values[1].toUByte().toInt() * 0x10000) +
                    (values[2].toUByte().toInt() * 0x100) + (values[3].toUByte().toInt())
            2 -> return ((values[0].toUByte().toInt() * 256) + values[1].toUByte().toInt())
            else -> return 0
        }
    }

    override fun toString(): String {
        return "MeasurementResult(${super.toString()}, testTypeCH1=$testTypeCH1, statusCH1=$statusCH1, errorCH1=$errorCH1, centerOfMassCH1=$centerOfMassCH1, flowMlsCH1=$flowMlsCH1, flowMlkCH1=$flowMlkCH1, coefRCH1=$coefRCH1, linearCoefCH1=$linearCoefCH1, photoActivatedCH1=$photoActivatedCH1, failureCounterCH1=$failureCounterCH1, testTypeCH2=$testTypeCH2, statusCH2=$statusCH2, errorCH2=$errorCH2, centerOfMassCH2=$centerOfMassCH2, flowMlsCH2=$flowMlsCH2, flowMlkCH2=$flowMlkCH2, coefRCH2=$coefRCH2, linearCoefCH2=$linearCoefCH2, photoActivatedCH2=$photoActivatedCH2, failureCounterCH2=$failureCounterCH2, fluidOpacity=$fluidOpacity, testTypeCH3=$testTypeCH3, statusCH3=$statusCH3, errorCH3=$errorCH3, flowLHCH3=$flowLHCH3, flowMlkCH3=$flowMlkCH3)"
    }

}