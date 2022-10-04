package br.com.tecnomotor.marvin.dao.entities.pump

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import br.com.tecnomotor.marvin.dao.entities.global.PlatformEntity
import br.com.tecnomotor.marvin.dao.entities.global.VersionEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "bpp_bombas_plataformas_planos_testes",
    foreignKeys = [
        ForeignKey(
            entity = PumpEntity::class,
            parentColumns = arrayOf("bom_id"),
            childColumns = arrayOf("bpp_bom_id")
        ),
        ForeignKey(
            entity = PumpPlanTestEntity::class,
            parentColumns = arrayOf("plb_id"),
            childColumns = arrayOf("bpp_plb_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlatformEntity::class,
            parentColumns = arrayOf("plat_id"),
            childColumns = arrayOf("bpp_plat_id")
        ),
        ForeignKey(
            entity = VersionEntity::class,
            parentColumns = arrayOf("ver_id"),
            childColumns = arrayOf("bpp_ver_id")
        )
    ]
//    indices = [Index(value = ["bpp_plb_id", "bpp_bpl_plat_id"])]

)
data class PumpPlatformPlanTestEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "bpp_bom_id")
    var bppPplBomId: Int = 0,

    @ColumnInfo(name = "bpp_plb_id")
    var pumpPlanTestId: Int = 0,

    @ColumnInfo(name = "bpp_plat_id")
    var platformPumpId: Int = 0,

    @ColumnInfo(name = "bpp_ver_id")
    var versionId: Int = 0

) : Serializable


