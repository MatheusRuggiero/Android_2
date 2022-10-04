package br.com.tecnomotor.marvin.dao.entities.pump


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
    tableName = "tpb_traducao_tipo_plano_teste_bomba",
    foreignKeys = [
        ForeignKey(
            entity = TypePlanTestEntity::class,
            parentColumns = arrayOf("tpl_id"),
            childColumns = arrayOf("tpb_tpl_id"),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = ["tpb_tpl_id"])]
)
data class TranslateTypePlanTestPumpEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ttptp_id")
    var id: Int = 0,

    @ColumnInfo(name = "tpb_idioma")
    var language: String? = "",

    @ColumnInfo(name = "tpb_tpl_id")
    var typePlanTestId: Int = 0,

    @ColumnInfo(name = "tpb_traducao")
    var translate: String? = ""

) : Serializable
