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
@Entity(tableName = "ver_versoes",
    indices = [Index("ver_id")])
data class VersionEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name  = "ver_id")
    var id: Int = 0,

    @ColumnInfo(name  = "ver_data_hora_liberacao")
    var dateTimeRelease: Date? = Calendar.getInstance().time,

    @ColumnInfo(name  = "ver_liberada")
    var released: Boolean? = false

) : Serializable
