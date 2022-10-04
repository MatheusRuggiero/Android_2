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
    tableName = "plat_plataformas",
    indices = [Index(value = ["plat_id"])]
)
data class PlatformEntity(

    @PrimaryKey
    @ColumnInfo(name = "plat_id")
    var id: Int = 0,

    @ColumnInfo(name = "plat_nome")
    var name: String? = "",

    @ColumnInfo(name = "plat_pressao_max")
    var pressureMax: Int = 0,

) : Serializable
