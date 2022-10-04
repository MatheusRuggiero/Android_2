package br.com.tecnomotor.marvin.dao.entities.valve

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
@Entity(tableName = "rlv_valvulass_relatorio")
data class ValveReportEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rlv_id")
    var id: Int = 0,

    @ColumnInfo(name = "rlv_codigo")
    var code: String? = "",

    @ColumnInfo(name = "rlv_padrao")
    var standard: Boolean? = false,

    @ColumnInfo(name = "rlv_revisao")
    var revision: Int = 0,

    @ColumnInfo(name = "rlv_aplicacao")
    var application: String? = "",

    @ColumnInfo(name = "rlv_adapt_pressao")
    var adaptPressure: String? = "",

    @ColumnInfo(name = "rlv_adapt_retorno")
    var adaptReturn: String? = "",

    @ColumnInfo(name = "rlv_adapt_conector")
    var adaptConnector: String? = "",

    @ColumnInfo(name = "rlv_marca_nome")
    var brandName: String? = "",

    @ColumnInfo(name = "rlv_resistencia_minima")
    var resistanceMinimum: Double = 0.0,

    @ColumnInfo(name = "rlv_resistencia_maxima")
    var resistanceMaximum: Double = 0.0,

    @ColumnInfo(name = "rlv_tipo_valvulas")
    var typeValve: Int = 0,

    @ColumnInfo(name = "rlv_operacao_valvulas")
    var operationValve: Int = 0,

    @ColumnInfo(name = "rlv_token")
    var token: String? = "",

) : Serializable
