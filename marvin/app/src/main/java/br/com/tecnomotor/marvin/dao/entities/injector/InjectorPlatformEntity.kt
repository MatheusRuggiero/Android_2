package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import br.com.tecnomotor.marvin.dao.entities.global.PlatformEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "ipl_injetores_plataformas",
    primaryKeys = ["ipl_inj_id", "ipl_plat_id"],
    foreignKeys = [ForeignKey(
        entity = InjectorEntity::class,
        parentColumns = arrayOf("inj_id"),
        childColumns = arrayOf("ipl_inj_id"),
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = PlatformEntity::class,
        parentColumns = arrayOf("plat_id"),
        childColumns = arrayOf("ipl_plat_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["ipl_inj_id", "ipl_plat_id"])]
)
data class InjectorPlatformEntity(

    @ColumnInfo(name = "ipl_inj_id", index = true)
    var injectorId: Int = 0,

    @ColumnInfo(name = "ipl_plat_id", index = true)
    var platformId: Int = 0,

    @ColumnInfo(name = "ipl_adapt_conector")
    var adaptConnector: String? = "",

    @ColumnInfo(name = "ipl_adapt_pressao")
    var adaptPressure: String? = "",

    @ColumnInfo(name = "ipl_adapt_retorno")
    var adaptReturn: String? = ""
) : Serializable
