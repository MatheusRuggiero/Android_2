package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.*
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
    tableName = "pli_planos_testes_injetores",
    foreignKeys = [
        ForeignKey(
            entity = TypePlanTestEntity::class,
            parentColumns = arrayOf("tpl_id"),
            childColumns = arrayOf("pli_tpl_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
//        ForeignKey(
//            entity = Revision::class,
//            parentColumns = arrayOf("rev_id"),
//            childColumns = arrayOf("pli_revisao"),
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE
//        )
    ],
    indices = [Index(value = ["pli_id", "pli_tpl_id"])]
//            indices = [Index(value = ["pli_id", "pli_tpl_id", "pli_revisao"])]

)
data class InjectorPlanTestEntity(

    @PrimaryKey
    @ColumnInfo(name = "pli_id")
    var id: Int = 0,

    @ColumnInfo(name = "pli_tpl_id", index = true)
    var typePlanTestOtherId: Int = 0,

    @ColumnInfo(name = "pli_tipo_plano_id")
    var typePlanId: Int = 0,

    @ColumnInfo(name = "pli_token")
    var token: String? = "",

    @ColumnInfo(name = "pli_revisao")
    var revisionId: Int = 0,

    @ColumnInfo(name = "pli_codifica")
    var encoded: Boolean? = false,

    @ColumnInfo(name = "pli_deleted")
    var deleted: Boolean? = false,

    @ColumnInfo(name = "pli_usa_adapt_sinal")
    var usaAdaptSignal: Boolean? = false

) : Serializable
