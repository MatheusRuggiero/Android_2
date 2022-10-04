package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.*
import br.com.tecnomotor.marvin.dao.convert.BigDecimalConvert
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "pti_pontos_testes_injetores",
    primaryKeys = ["pti_pli_id", "pti_sequencia"],
    foreignKeys = [
        ForeignKey(
            entity = InjectorPlanTestEntity::class,
            parentColumns = arrayOf("pli_id"),
            childColumns = arrayOf("pti_pli_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TypePointInjectorTestEntity::class,
            parentColumns = arrayOf("tpt_id"),
            childColumns = arrayOf("pti_tpt_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PointInjectorTestEntity(

    @ColumnInfo(name = "pti_pli_id")
    var planTestInjector: Int = 0,

    @ColumnInfo(name = "pti_sequencia")
    var sequence: Int = 0,

    @ColumnInfo(name = "pti_tpt_id")
    var typePointTestInjector: Int = 0,

    @ColumnInfo(name = "pti_pressao")
    var pressure: Int = 0,

    @ColumnInfo(name = "pti_frequencia")
    var frequency: Int = 0,

    @ColumnInfo(name = "pti_tempo_de_teste")
    var timeTest: Int = 0,

    @ColumnInfo(name = "pti_tempo_de_injecao")
    var timeInjection: Int = 0,

    @ColumnInfo(name = "pti_tempo_ligado")
    var timeOn: Int = 0,

    @ColumnInfo(name = "pti_tempo_desligado")
    var timeOff: Int = 0,

    @ColumnInfo(name = "pti_tempo_sinal_alto")
    var timeSignalHigh: Int = 0,

    @ColumnInfo(name = "pti_tempo_sinal_cst_alto")
    var timeSignalCstHigh: Int = 0,

    @ColumnInfo(name = "pti_injecao_ativa")
    var injectionActive: Boolean? = false,

    @ColumnInfo(name = "pti_medir_injecao")
    var measureInjection: Boolean? = false,

    @ColumnInfo(name = "pti_medir_retorno")
    var measureReturn: Boolean? = false,

    @TypeConverters(BigDecimalConvert::class)
    @ColumnInfo(name = "pti_injecao_maxima")
    var injectionMaximum: Double? = 0.0,

    @TypeConverters(BigDecimalConvert::class)
    @ColumnInfo(name = "pti_injecao_minima")
    var injectionMinimum: Double? = 0.0,

    @TypeConverters(BigDecimalConvert::class)
    @ColumnInfo(name = "pti_retorno_maximo")
    var returnMaximum: Double? = 0.0,

    @TypeConverters(BigDecimalConvert::class)
    @ColumnInfo(name = "pti_retorno_minimo")
    var returnMinimum: Double? = 0.0,

    @ColumnInfo(name = "pti_pre_encher_med_inj")
    var preFillMedInj: Boolean? = false,

    @ColumnInfo(name = "pti_exec_preset_usuario")
    var presetUser: Boolean? = false,

    @ColumnInfo(name = "pti_origem_ponto")
    var originPoint: String? = "",

    @ColumnInfo(name = "pti_tempo_sinal_desligado")
    var timeSignalOff: Int = 0,

    @ColumnInfo(name = "pti_tensao_alta")
    var tensionHigh: Int = 0,

    @ColumnInfo(name = "pti_tensao_menor")
    var tensionSmaller: Int = 0,

    @ColumnInfo(name = "pti_empty_ch_1")
    var emptyCH1: String = "",

    @ColumnInfo(name = "pti_corrente_pico_ch_1")
    var peakCurrentCH1: Int? = 0,

    @ColumnInfo(name = "pti_corrente_maior_ch_1")
    var currentMaximumCH1: Int? = 0,

    @ColumnInfo(name = "pti_corrente_menor_ch_1")
    var currentMinimumCH1: Int? = 0,

    @ColumnInfo(name = "pti_delay_time_ch_1")
    var delayTimeCH1: Int? = 0,

    @ColumnInfo(name = "pti_empty_1_ch_1")
    var empty1CH1: Boolean = false,

    @ColumnInfo(name = "pti_empty_2_ch_1")
    var empty2CH1: Boolean = false,

    @ColumnInfo(name = "pti_empty_3_ch_1")
    var empty3CH1: Boolean = false,

    @ColumnInfo(name = "pti_empty_4_ch_1")
    var empty4CH1: Boolean = false,

    @ColumnInfo(name = "pti_delay_pulse_ch_1")
    var delayPulseCH1: Boolean = false,

    @ColumnInfo(name = "pti_first_ch_1")
    var firstCH1: Boolean = false,

    @ColumnInfo(name = "pti_tempo_de_teste_2")
    var timeTest2: Int = 0,

    @ColumnInfo(name = "pti_tempo_de_injecao_2")
    var timeInjection2: Int = 0,

    @ColumnInfo(name = "pti_tempo_ligado_2")
    var timeOn2: Int = 0,

    @ColumnInfo(name = "pti_tempo_desligado_2")
    var timeOff2: Int = 0,

    @ColumnInfo(name = "pti_tempo_sinal_alto_2")
    var timeSignalHigh2: Int = 0,

    @ColumnInfo(name = "pti_tempo_sinal_cst_alto_2")
    var timeSignalCstHigh2: Int = 0,

    @ColumnInfo(name = "pti_injecao_ativa_2")
    var injectionActive2: Boolean? = false,

    @ColumnInfo(name = "pti_medir_injecao_2")
    var measureInjection2: Boolean? = false,

    @ColumnInfo(name = "pti_medir_retorno_2")
    var measureReturn2: Boolean? = false,

    @TypeConverters(BigDecimalConvert::class)
    @ColumnInfo(name = "pti_injecao_maxima_2")
    var injectionMaximum2: Double? = 0.0,

    @TypeConverters(BigDecimalConvert::class)
    @ColumnInfo(name = "pti_injecao_minima_2")
    var injectionMinimum2: Double? = 0.0,

    @TypeConverters(BigDecimalConvert::class)
    @ColumnInfo(name = "pti_retorno_maximo_2")
    var returnMaximum2: Double? = 0.0,

    @TypeConverters(BigDecimalConvert::class)
    @ColumnInfo(name = "pti_retorno_minimo_2")
    var returnMinimum2: Double? = 0.0,

    @ColumnInfo(name = "pti_pre_encher_med_inj_2")
    var preFillMedInj2: Boolean? = false,

    @ColumnInfo(name = "pti_exec_preset_usuario_2")
    var presetUser2: Boolean? = false,

    @ColumnInfo(name = "pti_origem_ponto_2")
    var originPoint2: String? = "",

    @ColumnInfo(name = "pti_tempo_sinal_desligado_2")
    var timeSignalOff2: Int = 0,

    @ColumnInfo(name = "pti_tensao_alta_2")
    var tensionHigh2: Int = 0,

    @ColumnInfo(name = "pti_tensao_menor_2")
    var tensionSmaller2: Int = 0,

    @ColumnInfo(name = "pti_empty_ch_2")
    var emptyCH2: String = "",

    @ColumnInfo(name = "pti_corrente_pico_ch_2")
    var peakCurrentCH2: Int? = 0,

    @ColumnInfo(name = "pti_corrente_maior_ch_2")
    var currentMaximumCH2: Int? = 0,

    @ColumnInfo(name = "pti_corrente_menor_ch_2")
    var currentMinimumCH2: Int? = 0,

    @ColumnInfo(name = "pti_delay_time_ch_2")
    var delayTimeCH2: Int? = 0,

    @ColumnInfo(name = "pti_empty_1_ch_2")
    var empty1CH2: Boolean = false,

    @ColumnInfo(name = "pti_empty_2_ch_2")
    var empty2CH2: Boolean = false,

    @ColumnInfo(name = "pti_empty_3_ch_2")
    var empty3CH2: Boolean = false,

    @ColumnInfo(name = "pti_empty_4_ch_2")
    var empty4CH2: Boolean = false,

    @ColumnInfo(name = "pti_delay_pulse_ch_2")
    var delayPulseCH2: Boolean = false,

    @ColumnInfo(name = "pti_first_ch_2")
    var firstCH2: Boolean = false,

    @ColumnInfo(name = "pti_response_time_max ")
    var responseTimeMax: Double? = 0.0,

    @ColumnInfo(name = "pti_response_time_min")
    var responseTimeMin: Double? = 0.0

) : Serializable
