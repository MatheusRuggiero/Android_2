package br.com.tecnomotor.marvin.dao.entities.pump

import androidx.room.*
import br.com.tecnomotor.marvin.dao.entities.global.BrandEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "tbo_tipo_bomba",
    foreignKeys = [
        ForeignKey(
            entity = BrandEntity::class,
            parentColumns = arrayOf("mar_id"),
            childColumns = arrayOf("tbo_mar_id"),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = ["tbo_mar_id"])]
)
data class TypePumpEntity(
    @PrimaryKey
    @ColumnInfo(name = "tbo_id")
    var id: Int = 0,
    @ColumnInfo(name = "tbo_nome")
    var name: String = "",
    @ColumnInfo(name = "tbo_mar_id")
    var brandId: Int = 0
) : Serializable