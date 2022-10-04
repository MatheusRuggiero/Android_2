package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(tableName = "rli_injetores_teste_relatorio")
data class InjectorTestReportEntity (

    /**
     * Information report
     */

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rli_relatorio_id")
    var reportId: Int = 0,

    @ColumnInfo(name = "rli_inj_relatorio_data_execucao_teste")
    var reportDateExecuteTest: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "rli_inj_tempo_decorrido_teste")
    var reportElapsedTimeTest: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "rli_relatorio_inj_numero_teste")
    var reportNumberTest: Long = 0,

    /**
     * Information injector
     */
    @ColumnInfo(name = "rli_inj_adapt_conector")
    var injAdaptConnector: String? = "",

    @ColumnInfo(name = "rli_inj_adapt_retorno")
    var injAdaptReturn: String? = "",

    @ColumnInfo(name = "rli_inj_aplicacao")
    var injApplication: String? = "",

    @ColumnInfo(name = "rli_inj_marca_id")
    var injBrandId: Int? = 0,

    @ColumnInfo(name = "rli_inj_marca_nome")
    var injBrandName: String? = "",

    @ColumnInfo(name = "rli_inj_codigo")
    var injCode: String? = "",

    @ColumnInfo(name = "rli_inj_id")
    var injId: Int? = 0,

    @ColumnInfo(name = "rli_inj_resistencia_maxima")
    var injResistanceMaximum: Double? = 0.0,

    @ColumnInfo(name = "rli_inj_resistencia_b_maxima")
    var injResistanceBMaximum: Double? = 0.0,

    @ColumnInfo(name = "rli_inj_resistencia_minima")
    var injResistanceMinimum: Double? = 0.0,

    @ColumnInfo(name = "rli_inj_resistencia_b_minima")
    var injResistanceBMinimum: Double? = 0.0,

    @ColumnInfo(name = "rli_inj_revisao")
    var injRevision: Int? = 0,

    @ColumnInfo(name = "rli_inj_padrao")
    var injStandard: Boolean? = false,

    @ColumnInfo(name = "rli_inj_token")
    var injToken: String? = "",

    /**
     * Information test electrical
     */
    @ColumnInfo(name = "rli_inj_condicao_teste_eletrico")
    var injConditionTestElectrical: String? = "",

    @ColumnInfo(name = "rli_inj_status_teste_eletrico")
    var injStatusTestElectrical: String? = "",

    /**
     * Information test tightness
     */

    @ColumnInfo(name = "rli_inj_condicao_teste_estanquiedade")
    var injConditionTestTightness: String? = "",

    @ColumnInfo(name = "rli_inj_status_teste_estanquiedade")
    var injStatusTestTightness: String? = "",

    /**
     * Information test point
     */
    @ColumnInfo(name = "rli_inj_ponto_descricao")
    var injPointDescription: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_id")
    var injPointId: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_injecao_maxima")
    var injPointInjectionMaximum: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_injecao_minima")
    var injPointInjectionMinimum: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_injecao_2_maxima")
    var injPointInjection2Maximum: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_injecao_2_minima")
    var injPointInjection2Minimum: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_injecao_retorno_maxima")
    var injPointInjectionReturnMaximum: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_injecao_retorno_minima")
    var injPointInjectionReturnMinimum: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_injecao_retorno_2_maxima")
    var injPointInjectionReturn2Maximum: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_injecao_retorno_2_minima")
    var injPointInjectionReturn2Minimum: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_origem")
    var injPointOrigin: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_sequencia")
    var injPointSequence: Int = 0,

    @ColumnInfo(name = "rli_inj_ponto_descricao_en")
    var injPointDescriptionEn: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_descricao_es")
    var injPointDescriptionEs: String? = "",

    @ColumnInfo(name = "rli_inj_ponto_tipo_teste")
    var injPointTypeTest: Int = 0,

    @ColumnInfo(name = "rli_inj_ponto_pressao")
    var injPointPressure: Int = 0,

    @ColumnInfo(name = "rli_inj_ponto_frequencia")
    var injPointFrequency: Int = 0,

    @ColumnInfo(name = "rli_inj_ponto_tempo_injecao")
    var injPointInjectionTime: Int = 0,

    @ColumnInfo(name = "rli_inj_ponto_medir_injecao")
    var injPointMeasureInjection: Boolean? = false,

    @ColumnInfo(name = "rli_inj_ponto_medir_retorno ")
    var injPointMeasureReturn: Boolean? = false

) : Serializable