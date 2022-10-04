package br.com.tecnomotor.marvin.dao.entities.valve

import androidx.room.ColumnInfo
import androidx.room.Entity
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
    tableName = "vpp_valvulas_plataformas_planos_testes",
//    foreignKeys = [
//        ForeignKey(
//            entity = ValvePlatformEntity::class,
//            parentColumns = arrayOf("id"),
//            childColumns = arrayOf("vpp_vpl_plat_id"),
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = PlatformEntity::class,
//            parentColumns = arrayOf("plat_id"),
//            childColumns = arrayOf("vpp_plv_id"),
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = VersionEntity::class,
//            parentColumns = arrayOf("ver_id"),
//            childColumns = arrayOf("vpp_ver_id"),
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
)
data class ValvePlatformPlanEntity  (

    @ColumnInfo(name = "vpp_vpl_plat_id")
    var valvePlatformId: Int = 0,

    @ColumnInfo(name = "vpp_vpl_plat_description")
    var valvePlatformDescription: String = "",

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vpp_vpl_val_id")
    var vplValId: Int = 0,

    @ColumnInfo(name = "vpp_plv_id")
    var planTestValveId: Int = 0,

    @ColumnInfo(name = "vpp_ver_id")
    var versionId: Int = 0,

) : Serializable
