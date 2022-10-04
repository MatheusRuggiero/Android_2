package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.*
import br.com.tecnomotor.marvin.dao.entities.global.PlatformEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "ipp_injetores_plataformas_planos_testes",
    foreignKeys = [
        ForeignKey(
            entity = InjectorPlanTestEntity::class,
            parentColumns = arrayOf("pli_id"),
            childColumns = arrayOf("ipp_pli_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlatformEntity::class,
            parentColumns = arrayOf("plat_id"),
            childColumns = arrayOf("ipp_ipl_plat_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
//        ForeignKey(
//            entity = VersionEntity::class,
//            parentColumns = arrayOf("ver_id"),
//            childColumns = arrayOf("ipp_ver_id"),
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE
//        )
    ],
    indices = [Index(value = ["id", "ipp_pli_id", "ipp_ipl_plat_id"])]
)
data class InjectorPlatformPlanTestEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "ipp_pli_id", index = true)
    var planTestInjectorId: Int = 0,

    @ColumnInfo(name = "ipp_ipl_plat_id", index = true)
    var platformId: Int = 0,

    @ColumnInfo(name = "ipp_ipl_inj_id")
    var injId: Int = 0,

    @ColumnInfo(name = "ipp_ver_id")
    var versionId: Int = 0,

    )

