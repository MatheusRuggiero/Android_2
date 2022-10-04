package br.com.tecnomotor.marvin.dao.entities.pump

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import br.com.tecnomotor.marvin.dao.entities.global.TypePlanTestEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "plb_planos_testes_bombas",
    foreignKeys = [
        ForeignKey(
            entity = TypePlanTestEntity::class,
            parentColumns = arrayOf("tpl_id"),
            childColumns = arrayOf("plb_tpl_id")
        ),
    ]
//    indices = [Index(value = ["plb_tpl_id"])]

)
data class PumpPlanTestEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plb_id")
    var id: Int = 0,

    @ColumnInfo(name = "plb_tpl_id")
    var typePlanTestId: Int = 0,

    @ColumnInfo(name = "plb_tipo_plano_id")
    var typePlanId: Int = 0,

    @ColumnInfo(name = "plb_pressao")
    var pressure: Int = 0,

    @ColumnInfo(name = "plb_rotacao")
    var rotation: Int = 0,

    @ColumnInfo(name = "plb_vazao_minima")
    var minimumFlow: Int = 0,

    @ColumnInfo(name = "plb_periodo")
    var timeCourse: Int = 0,

    @ColumnInfo(name = "plb_prescaler")
    var prescaler: Int = 0,

    @ColumnInfo(name = "plb_token")
    var token: String? = "",

    @ColumnInfo(name = "plb_revisao")
    var revisionId: Int? = 0,

    @ColumnInfo(name = "plb_deleted")
    var deleted: Boolean? = false,

    @ColumnInfo(name = "plb_deleted_permanente")
    var deletePermanent: Boolean? = false

) : Serializable

