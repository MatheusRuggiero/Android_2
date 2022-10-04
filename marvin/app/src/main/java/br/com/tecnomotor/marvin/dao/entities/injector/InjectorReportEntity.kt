package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(tableName = "rli_injetores_relatorio")
data class InjectorReportEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rli_inj_id")
    var rliInjId: Int = 0,

    @ColumnInfo(name = "inj_id")
    var injId: Int = 0,

    @ColumnInfo(name = "rli_inj_codigo")
    var injCode: String? = "",

    @ColumnInfo(name = "rli_inj_padrao")
    var injStandard: Boolean? = false,

    @ColumnInfo(name = "rli_inj_revisao")
    var injRevision: Int = 0,

    @ColumnInfo(name = "rli_inj_marca_nome")
    var injBrandName: String? = "",

    @ColumnInfo(name = "rli_inj_aplicacao")
    var injApplication: String? = "",

    @ColumnInfo(name = "rli_inj_tipo")
    var injType: Int = 0,

    @ColumnInfo(name = "rli_inj_adapt_pressao")
    var injAdaptPressure: String? = "",

    @ColumnInfo(name = "rli_inj_adapt_retorno")
    var injAdaptReturn: String? = "",

    @ColumnInfo(name = "rli_inj_adapt_conector")
    var injAdaptConnector: String? = "",

    @ColumnInfo(name = "rli_inj_resistencia_minima")
    var injResistanceMinimum: Double? = 0.0,

    @ColumnInfo(name = "rli_inj_resistencia_maxima")
    var injResistanceMaximum: Double? = 0.0,

    @ColumnInfo(name = "rli_inj_resistenciab_minima")
    var injResistanceBMinimum: Double? = 0.0,

    @ColumnInfo(name = "rli_inj_resistenciab_maxima")
    var injResistanceBMaximum: Double? = 0.0,

    @ColumnInfo(name = "rli_inj_token")
    var injToken: String? = ""

) : Serializable
