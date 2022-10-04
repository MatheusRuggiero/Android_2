package br.com.tecnomotor.marvin.dao.entities.sensor

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
@Entity(tableName = "rls_sensores_relatorio")
data class SensorReportEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rls_id")
    var id: Int = 0,

    @ColumnInfo(name = "rls_codigo")
    var code: String? = "",

    @ColumnInfo(name = "rls_padrao")
    var standard: Boolean? = false,

    @ColumnInfo(name = "rls_revisao")
    var revision: Int = 0,

    @ColumnInfo(name = "rls_aplicacao")
    var application: String? = "",

    @ColumnInfo(name = "rls_marca")
    var brand: String? = "",

    @ColumnInfo(name = "rls_adapt_pressao")
    var adaptPressure: String? = "",

    @ColumnInfo(name = "rls_adapt_conector")
    var adaptConnector: String? = "",

    @ColumnInfo(name = "rls_token")
    var token: String? = ""

) : Serializable
