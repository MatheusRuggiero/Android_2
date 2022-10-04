package br.com.tecnomotor.marvin.dao.entities.pump

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
@Entity(
    tableName = "rbo_revisoes_bombas",
)
data class RevisionPumpEntity(

    @PrimaryKey
    @ColumnInfo(name = "rbo_rev_id")
    var id_rev: Int = 0,

    @ColumnInfo(name = "rbo_bom_id")
    var id_bom: Int = 0,

    @ColumnInfo(name = "date_hour")
    var dateHour: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "date_hour_str")
    var dateHourStr: String? = "",

    @ColumnInfo(name = "rbo_motivo")
    var motivation: String? = "",

    @ColumnInfo(name = "rbo_observacao")
    var observation: String? = "",

    @ColumnInfo(name = "rbo_usuario")
    var user: String? = "",

    ) : Serializable
