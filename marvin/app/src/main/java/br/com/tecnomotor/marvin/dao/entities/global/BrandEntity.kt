package br.com.tecnomotor.marvin.dao.entities.global

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "mar_marcas",
    indices = [Index("mar_id")]
)
data class BrandEntity(
    @PrimaryKey
    @ColumnInfo(name = "mar_id")
    var id: Int = 0,

    @ColumnInfo(name = "mar_nome")
    var name: String? = ""
) : Serializable





