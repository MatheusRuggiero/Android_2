package br.com.tecnomotor.marvin.dao.entities.pump

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
@Entity(tableName = "rlb_bombas_relatorio")
data class PumpReportEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rlb_id")
    var id: Int = 0,

    @ColumnInfo(name = "rlb_codigo")
    var code: String? = "",

    @ColumnInfo(name = "rlb_padrao")
    var standard: Boolean? = false,

    @ColumnInfo(name = "rlb_revisao")
    var revision: Int = 0,

    @ColumnInfo(name = "rlb_aplicacao")
    var application: String? = "",

    @ColumnInfo(name = "rlb_marca")
    var brand: String? = "",

    @ColumnInfo(name = "rlb_token")
    var token: String? = ""

) : Serializable
