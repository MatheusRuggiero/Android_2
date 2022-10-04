package br.com.tecnomotor.marvin.dao.entities.pump

import androidx.room.*
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
    tableName = "bpl_bombas_plataformas",
    foreignKeys = [ForeignKey(
        entity = PumpEntity::class,
        parentColumns = arrayOf("bom_id"),
        childColumns = arrayOf("bpl_bom_id"),
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = PlatformEntity::class,
        parentColumns = arrayOf("plat_id"),
        childColumns = arrayOf("bpl_plat_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["bpl_bom_id", "bpl_plat_id"])]

)
data class PumpPlatformEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bomba_plat_id")
    var id: Int = 0,

    @ColumnInfo(name = "bpl_plat_id")
    var platformId: Int = 0,

    @ColumnInfo(name = "bpl_bom_id")
    var pumpId: Int = 0,

    @ColumnInfo(name = "bpl_adaptador_eletrico")
    var electricAdapter: String? = "",

    @ColumnInfo(name = "bpl_adaptador_hidraulico_pressao")
    var adapterHydraulicPressure: String? = "",

    @ColumnInfo(name = "bpl_adaptador_hidraulico_alimentacao")
    var adapterHydraulicPower: String? = "",

    @ColumnInfo(name = "bpl_adaptador_hidraulico_retorno")
    var adapterHydraulicReturn: String? = "",

    @ColumnInfo(name = "bpl_adaptador_mecanico")
    var adapterMechanical: String? = ""

) : Serializable


