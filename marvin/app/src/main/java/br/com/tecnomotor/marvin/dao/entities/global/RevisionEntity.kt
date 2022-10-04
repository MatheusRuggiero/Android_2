package br.com.tecnomotor.marvin.dao.entities.global

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
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
    tableName = "rev_revisoes",
    indices = [Index("rev_id")]
)
data class RevisionEntity(

    @PrimaryKey
    @ColumnInfo(name = "rev_id")
    var id: Int = 0,

    @ColumnInfo(name = "rev_motivo")
    var motivation: String? = "",

    @ColumnInfo(name = "rev_data_hora")
    var dateHour: Date? = Calendar.getInstance().time

) : Serializable
