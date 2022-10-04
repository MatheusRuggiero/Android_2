package br.com.tecnomotor.marvin.model.injector

import br.com.tecnomotor.commonrail.device.commands.injector.result.PointTestInjectorResultDemo
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class PointTestInjector : Serializable {

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var planTestInjector: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var sequence: Int = 0

    @SerializedName("tipoTeste")
    @JsonProperty("tipoTeste")
    var typePointTestInjector: TypePointTestInjector? = TypePointTestInjector()

    @SerializedName("pressao")
    @JsonProperty("pressao")
    var pressure: Int = 0

    @SerializedName("frequencia")
    @JsonProperty("frequencia")
    var frequency: Int = 0

    @SerializedName("tempoDeTeste")
    @JsonProperty("tempoDeTeste")
    var timeTest: Int = 0

    @SerializedName("tempoDeInjecao")
    @JsonProperty("tempoDeInjecao")
    var timeInjection: Int = 0

    @SerializedName("tempoLigado")
    @JsonProperty("tempoLigado")
    var timeOn: Int = 0

    @SerializedName("tempoDesligado")
    @JsonProperty("tempoDesligado")
    var timeOff: Int = 0

    @SerializedName("tempoSinalAlto")
    @JsonProperty("tempoSinalAlto")
    var timeSignalHigh: Int = 0

    @SerializedName("tempoSinalCSTAlto")
    @JsonProperty("tempoSinalCSTAlto")
    var timeSignalCstHigh: Int = 0

    @SerializedName("injecaoAtiva")
    @JsonProperty("injecaoAtiva")
    var injectionActive: Boolean? = false

    @SerializedName("medirInjecao")
    @JsonProperty("medirInjecao")
    var measureInjection: Boolean? = false

    @SerializedName("medirRetorno")
    @JsonProperty("medirRetorno")
    var measureReturn: Boolean? = false

    @SerializedName("injecaoMaxima")
    @JsonProperty("injecaoMaxima")
    var injectionMaximum: BigDecimal? = BigDecimal.ZERO

    @SerializedName("injecaoMinima")
    @JsonProperty("injecaoMinima")
    var injectionMinimum: BigDecimal? = BigDecimal.ZERO

    @SerializedName("retornoMaximo")
    @JsonProperty("retornoMaximo")
    var returnMaximum: BigDecimal? = BigDecimal.ZERO

    @SerializedName("retornoMinimo")
    @JsonProperty("retornoMinimo")
    var returnMinimum: BigDecimal? = BigDecimal.ZERO

    @SerializedName("preEncher")
    @JsonProperty("preEncher")
    var preFillMedInj: Boolean? = false

    @SerializedName("presetUsuario")
    @JsonProperty("presetUsuario")
    var presetUser: Boolean? = false

    @SerializedName("origem")
    @JsonProperty("origem")
    var originPoint: String? = ""

    @SerializedName("tempoSinalDesligado")
    @JsonProperty("tempoSinalDesligado")
    var timeSignalOff: Int = 0

    @SerializedName("tensaoAlta")
    @JsonProperty("tensaoAlta")
    var tensionHigh: Int = 0

    @SerializedName("tensaoMenor")
    @JsonProperty("tensaoMenor")
    var tensionSmaller: Int = 0

    @SerializedName("emptyCH1")
    @JsonProperty("emptyCH1")
    var emptyCH1 = ""

    @SerializedName("correntePicoCH1")
    @JsonProperty("correntePicoCH1")
    var peakCurrentCH1 = 0

    @SerializedName("correnteMaiorCH1")
    @JsonProperty("correnteMaiorCH1")
    var currentMaximumCH1 = 0

    @SerializedName("correnteMenorCH1")
    @JsonProperty("correnteMenorCH1")
    var currentMinimumCH1 = 0

    @SerializedName("delayTimeCH1")
    @JsonProperty("delayTimeCH1")
    var delayTimeCH1 = 0

    @SerializedName("empty1CH1")
    @JsonProperty("empty1CH1")
    var empty1CH1 = false

    @SerializedName("empty2CH1")
    @JsonProperty("empty2CH1")
    var empty2CH1 = false

    @SerializedName("empty3CH1")
    @JsonProperty("empty3CH1")
    var empty3CH1 = false

    @SerializedName("empty4CH1")
    @JsonProperty("empty4CH1")
    var empty4CH1 = false

    @SerializedName("delayPulseCH1")
    @JsonProperty("delayPulseCH1")
    var delayPulseCH1 = false

    @SerializedName("firstCH1")
    @JsonProperty("firstCH1")
    var firstCH1 = false

    @SerializedName("tempoDeTeste2")
    @JsonProperty("tempoDeTeste2")
    var timeTest2: Int = 0

    @SerializedName("tempoDeInjecao2")
    @JsonProperty("tempoDeInjecao2")
    var timeInjection2: Int = 0

    @SerializedName("tempoLigado2")
    @JsonProperty("tempoLigado2")
    var timeOn2: Int = 0

    @SerializedName("tempoDesligado2")
    @JsonProperty("tempoDesligado2")
    var timeOff2: Int = 0

    @SerializedName("tempoSinalAlto2")
    @JsonProperty("tempoSinalAlto2")
    var timeSignalHigh2: Int = 0

    @SerializedName("tempoSinalCSTAlto2")
    @JsonProperty("tempoSinalCSTAlto2")
    var timeSignalCstHigh2: Int = 0

    @SerializedName("injetorPlataformaPK")
    @JsonProperty("injetorPlataformaPK")
    var injectionActive2: Boolean? = false

    @SerializedName("medirInjecao2")
    @JsonProperty("medirInjecao2")
    var measureInjection2: Boolean? = false

    @SerializedName("medirRetorno2")
    @JsonProperty("medirRetorno2")
    var measureReturn2: Boolean? = false

    @SerializedName("injecaoMaxima2")
    @JsonProperty("injecaoMaxima2")
    var injectionMaximum2: BigDecimal? = BigDecimal.ZERO

    @SerializedName("injecaoMinima2")
    @JsonProperty("injecaoMinima2")
    var injectionMinimum2: BigDecimal? = BigDecimal.ZERO

    @SerializedName("retornoMaximo2")
    @JsonProperty("retornoMaximo2")
    var returnMaximum2: BigDecimal? = BigDecimal.ZERO

    @SerializedName("retornoMinimo2")
    @JsonProperty("retornoMinimo2")
    var returnMinimum2: BigDecimal? = BigDecimal.ZERO

    @SerializedName("preEncher2")
    @JsonProperty("preEncher2")
    var preFillMedInj2: Boolean? = false

    @SerializedName("presetUsuario2")
    @JsonProperty("presetUsuario2")
    var presetUser2: Boolean? = false

    @SerializedName("origem2")
    @JsonProperty("origem2")
    var originPoint2: String? = ""

    @SerializedName("tempoSinalDesligado2")
    @JsonProperty("tempoSinalDesligado2")
    var timeSignalOff2: Int = 0

    @SerializedName("tensaoAlta2")
    @JsonProperty("tensaoAlta2")
    var tensionHigh2: Int = 0

    @SerializedName("tensaoMenor2")
    @JsonProperty("tensaoMenor2")
    var tensionSmaller2: Int = 0

    @SerializedName("emptyCH2")
    @JsonProperty("emptyCH2")
    var emptyCH2 = ""

    @SerializedName("correntePicoCH2")
    @JsonProperty("correntePicoCH2")
    var peakCurrentCH2 = 0

    @SerializedName("correnteMaiorCH2")
    @JsonProperty("correnteMaiorCH2")
    var currentMaximumCH2 = 0

    @SerializedName("correnteMenorCH2")
    @JsonProperty("correnteMenorCH2")
    var currentMinimumCH2 = 0

    @SerializedName("delayTimeCH2")
    @JsonProperty("delayTimeCH2")
    var delayTimeCH2 = 0

    @SerializedName("empty1CH2")
    @JsonProperty("empty1CH2")
    var empty1CH2 = false

    @SerializedName("empty2CH2")
    @JsonProperty("empty2CH2")
    var empty2CH2 = false

    @SerializedName("empty3CH2")
    @JsonProperty("empty3CH2")
    var empty3CH2 = false

    @SerializedName("empty4CH2")
    @JsonProperty("empty4CH2")
    var empty4CH2 = false

    @SerializedName("delayPulseCH2")
    @JsonProperty("delayPulseCH2")
    var delayPulseCH2 = false

    @SerializedName("firstCH2")
    @JsonProperty("firstCH2")
    var firstCH2 = false

    @SerializedName("responseTimeMax")
    @JsonProperty("responseTimeMax")
    var responseTimeMax: BigDecimal? = BigDecimal.ZERO

    @SerializedName("responseTimeMin")
    @JsonProperty("responseTimeMin")
    var responseTimeMin: BigDecimal? = BigDecimal.ZERO

    @SerializedName("testResults")
    @JsonProperty("testResults")
    var testInjectorResults: PointTestInjectorResultDemo = PointTestInjectorResultDemo(EnumTestStatus.NONE)

    constructor()

    private fun sendParameterPointTestInjector(pointTestInjector: PointTestInjector): HashMap<String, ByteArray> {
        return hashMapOf<String, ByteArray>(
//            "" to byteArrayOf(0x01),
            "canal" to byteArrayOf(0x00),
            "injecaoAtiva" to if (pointTestInjector.injectionActive!!) byteArrayOf(0x01) else byteArrayOf(0x00),
            "medirInjecao" to if (pointTestInjector.measureInjection!!) byteArrayOf(0x01) else byteArrayOf(0x00),
            "medirRetorno" to if (pointTestInjector.measureReturn!!) byteArrayOf(0x01) else byteArrayOf(0x00),
            "pressaoStep" to byteArrayOf(0x07.toByte(), 0x08.toByte()),
            "frequencia" to byteArrayOf(pointTestInjector.frequency.toByte()),
            "tempoInjecao" to byteArrayOf(pointTestInjector.timeInjection.toByte()),
            "tempoLigado" to byteArrayOf(pointTestInjector.timeOn.toByte()),
            "tempoDesligado" to byteArrayOf(pointTestInjector.timeOff.toByte()),
            "tempoDoSinalEmAlta" to byteArrayOf(pointTestInjector.timeSignalHigh.toByte()),
            "tempo12Volts" to byteArrayOf(pointTestInjector.timeSignalCstHigh.toByte()),
            "preEncherMedidoInjecao" to if (pointTestInjector.preFillMedInj!!) byteArrayOf(0x01) else byteArrayOf(0x00),
            "tempoDoSinalDesligado" to byteArrayOf(pointTestInjector.timeSignalOff.toByte()),
            "tensaoBaixa" to byteArrayOf(pointTestInjector.tensionSmaller.toByte()),
            "tensaoAlta" to byteArrayOf(pointTestInjector.tensionHigh.toByte()),


//            "canal2" to byteArrayOf(0x01),
//            "injecaoAtiva2" to if (pointTestInjector.injectionActive2!!) byteArrayOf(0x01) else byteArrayOf(0x00),
//            "medirInjecao2" to if (pointTestInjector.measureInjection2!!) byteArrayOf(0x01) else byteArrayOf(0x00),
//            "medirRetorno2" to if (pointTestInjector.measureReturn2!!) byteArrayOf(0x01) else byteArrayOf(0x00),
//            "pressaoStep2" to byteArrayOf(0x07.toByte(), 0x08.toByte()),
//            "frequencia2" to byteArrayOf(pointTestInjector.frequency2.toByte()),
//            "tempoInjecao2" to byteArrayOf(pointTestInjector.timeInjection2.toByte()),
//            "tempoLigado2" to byteArrayOf(pointTestInjector.timeOn2.toByte()),
//            "tempoDesligado2" to byteArrayOf(pointTestInjector.timeOff2.toByte()),
//            "tempoDoSinalEmAlta2" to byteArrayOf(pointTestInjector.timeSignalHigh2.toByte()),
//            "tempo12Volts2" to byteArrayOf(pointTestInjector.timeSignalCstHigh2.toByte()),
//            "preEncherMedidoInjecao2" to if (pointTestInjector.preFillMedInj2!!) byteArrayOf(0x01) else byteArrayOf(0x00),
//            "tempoDoSinalDesligado2" to byteArrayOf(pointTestInjector.timeSignalOff2.toByte()),
//            "tensaoBaixa2" to byteArrayOf(pointTestInjector.tensionSmaller2.toByte()),
//            "tensaoAlta2" to byteArrayOf(pointTestInjector.tensionHigh2.toByte()),
        )
    }

    override fun toString(): String {
        return "PointTestInjector(planTestInjector=${planTestInjector}, " +
                "sequence=$sequence, " +
                "pressure=$pressure, " +
                "frequency=$frequency, " +
                "timeTest=$timeTest, " +
                "timeInjection=$timeInjection, " +
                "timeOn=$timeOn, " +
                "timeOff=$timeOff, " +
                "timeSignalHigh=$timeSignalHigh, " +
                "timeSignalCstHigh=$timeSignalCstHigh, " +
                "injectionActive=$injectionActive, " +
                "measureInjection=$measureInjection, " +
                "measureReturn=$measureReturn, " +
                "injectionMaximum=$injectionMaximum, " +
                "injectionMinimum=$injectionMinimum, " +
                "returnMaximum=$returnMaximum, " +
                "returnMinimum=$returnMinimum, " +
                "preFillMedInj=$preFillMedInj, " +
                "presetUser=$presetUser, " +
                "originPoint=$originPoint, " +
                "timeSignalOff=$timeSignalOff, " +
                "tensionHigh=$tensionHigh, " +
                "tensionSmaller=$tensionSmaller, " +
                "emptyCH1='$emptyCH1', " +
                "peakCurrentCH1=$peakCurrentCH1, " +
                "currentMaximumCH1=$currentMaximumCH1, " +
                "currentMinimumCH1=$currentMinimumCH1, " +
                "delayTimeCH1=$delayTimeCH1, " +
                "empty1CH1=$empty1CH1, " +
                "empty2CH1=$empty2CH1, " +
                "empty3CH1=$empty3CH1, " +
                "empty4CH1=$empty4CH1, " +
                "delayPulseCH1=$delayPulseCH1, " +
                "firstCH1=$firstCH1, " +
                "timeTest2=$timeTest2, " +
                "timeInjection2=$timeInjection2, " +
                "timeOn2=$timeOn2, " +
                "timeOff2=$timeOff2, " +
                "timeSignalHigh2=$timeSignalHigh2, " +
                "timeSignalCstHigh2=$timeSignalCstHigh2, " +
                "injectionActive2=$injectionActive2, " +
                "measureInjection2=$measureInjection2, " +
                "measureReturn2=$measureReturn2, " +
                "injectionMaximum2=$injectionMaximum2, " +
                "injectionMinimum2=$injectionMinimum2, " +
                "returnMaximum2=$returnMaximum2, " +
                "returnMinimum2=$returnMinimum2, " +
                "preFillMedInj2=$preFillMedInj2, " +
                "presetUser2=$presetUser2, " +
                "originPoint2=$originPoint2, " +
                "timeSignalOff2=$timeSignalOff2, " +
                "tensionHigh2=$tensionHigh2, " +
                "tensionSmaller2=$tensionSmaller2, " +
                "emptyCH2='$emptyCH2', " +
                "peakCurrentCH2=$peakCurrentCH2, " +
                "currentMaximumCH2=$currentMaximumCH2, " +
                "currentMinimumCH2=$currentMinimumCH2, " +
                "delayTimeCH2=$delayTimeCH2, " +
                "empty1CH2=$empty1CH2, " +
                "empty2CH2=$empty2CH2, " +
                "empty3CH2=$empty3CH2, " +
                "empty4CH2=$empty4CH2, " +
                "delayPulseCH2=$delayPulseCH2, " +
                "firstCH2=$firstCH2, " +
                "responseTimeMax=$responseTimeMax, " +
                "responseTimeMin=$responseTimeMin)"
    }

    fun getInjectionMinimumGraph(): Float {
        var res = 0f
        if (measureInjection == false)
            return res
        val minValueInjection = injectionMinimum?.toFloat() ?: 0f
        res = minValueInjection - 10f
        if ((minValueInjection == 0f) || (res < 0f)) return 0f
        return res
    }

    fun getReturnMinimumGraph(): Float {
        var res = 0f
        if (measureReturn == false)
            return res
        val minValueReturn = returnMinimum?.toFloat() ?: 0f
        res = minValueReturn - 10f
        if ((minValueReturn == 0f) || (res < 0f)) return 0f
        return res
    }

    fun getMinimumGraph(): Float {
        val values = arrayListOf<Float>(getInjectionMinimumGraph(), getReturnMinimumGraph())
        return values.minOrNull() ?: 0f
    }

    fun getInjectionMaximumGraph(): Float {
        var res = 0f
        if (measureInjection == false)
            return res
        val maxValueInjection = injectionMaximum?.toFloat() ?: 0f
        if (maxValueInjection == 0f) return maxValueInjection
        res = maxValueInjection + 10f
        return res
    }

    fun getReturnMaximumGraph(): Float {
        var res = 0f
        if (measureReturn == false)
            return res
        val maxValueReturn = returnMaximum?.toFloat() ?: 0f
        if (maxValueReturn == 0f) return maxValueReturn
        res = maxValueReturn + 10f
        return res
    }

    fun getMaximumGraph(): Float {
        val values = arrayListOf<Float>(getInjectionMaximumGraph(), getReturnMaximumGraph())
        return values.maxOrNull() ?: 0f
    }

}
