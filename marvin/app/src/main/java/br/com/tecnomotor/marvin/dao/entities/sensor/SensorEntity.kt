package br.com.tecnomotor.marvin.dao.entities.sensor

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
    tableName = "sen_sensores",
    foreignKeys = [ForeignKey(
        entity = BrandEntity::class,
        parentColumns = arrayOf("mar_id"),
        childColumns = arrayOf("sen_marca_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["sen_marca_id"])]
)
data class SensorEntity(

    @PrimaryKey
    @ColumnInfo(name = "sen_id")
    var senId: Int = 0,

    @ColumnInfo(name = "sen_codigo")
    var senCode: String? = "",

    @ColumnInfo(name = "sen_padrao")
    var senStandard: Boolean? = false,

    @ColumnInfo(name = "sen_marca_id")
    var brandEntityId: Int = 0,

    @ColumnInfo(name = "sen_aplicacao")
    var senApplication: String? = "",

    @ColumnInfo(name = "sen_adapt_pressao")
    var senAdaptPressure: String? = "",

    @ColumnInfo(name = "sen_adapt_conector")
    var senAdaptConnector: String? = "",

    @ColumnInfo(name = "sen_token")
    var senToken: String? = "",

    @ColumnInfo(name = "sen_revisao")
    var revisionEntityId: Int = 0,

    @ColumnInfo(name = "sen_deleted")
    var senDeleted: Boolean? = false

) : Serializable
