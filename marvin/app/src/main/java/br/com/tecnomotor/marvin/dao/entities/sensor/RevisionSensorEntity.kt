package br.com.tecnomotor.marvin.dao.entities.sensor

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
@Entity(tableName = "rse_revisoes_sensores")
data class RevisionSensorEntity(
    @PrimaryKey
    @ColumnInfo(name = "rse_rev_id")
    var id_rev: Int = 1,

    @ColumnInfo(name = "rse_sen_id")
    var id_sen: Int = 0,

    @ColumnInfo(name = "rse_data_hora")
    var dateHour: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "rse_data_hora_str")
    var dataHoraStr: String? = "",

    @ColumnInfo(name = "rse_motivo")
    var motivation: String? = "",

    @ColumnInfo(name = "rse_observacao")
    var observation: String? = "",

    @ColumnInfo(name = "rse_usuario")
    var user: String? = ""

) : Serializable