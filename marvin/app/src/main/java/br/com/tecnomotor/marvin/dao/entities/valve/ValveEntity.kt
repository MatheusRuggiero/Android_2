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
@Entity(tableName = "var_valvulas")
data class ValveEntity (
    @PrimaryKey
    @ColumnInfo(name = "var_id")
    var id: Int = 0,

    @ColumnInfo(name = "var_codigo")
    var code: String? = "",

    @ColumnInfo(name = "var_padrao")
    var standard: Boolean? = false,

    @ColumnInfo(name = "var_marca_id")
    var brandEntityId: Int = 0,

    @ColumnInfo(name = "var_aplicacao")
    var application: String? = "",

    @ColumnInfo(name = "var_adapt_pressao")
    var adaptPressure: String? = "",

    @ColumnInfo(name = "var_adapt_retorno")
    var adaptReturn: String? = "",

    @ColumnInfo(name = "var_adapt_conector")
    var adaptConnector: String? = "",

    @ColumnInfo(name = "var_resistencia_minima")
    var resistanceMinimum: Double = 0.0,

    @ColumnInfo(name = "var_resistencia_maxima")
    var resistanceMaximum: Double = 0.0,

    @ColumnInfo(name = "var_tipo_valvulas")
    var typeValve: Int = 0,

    @ColumnInfo(name = "var_operacao_valvulas")
    var operationValve: Int = 0,

    @ColumnInfo(name = "var_token")
    var token: String? = "",

    @ColumnInfo(name = "var_revisao")
    var revisionEntityId: Int = 0,

    @ColumnInfo(name = "var_deleted")
    var deleted: Boolean? = false

) : Serializable
