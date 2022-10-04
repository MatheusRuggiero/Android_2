package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
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
    tableName = "tpi_traducao_tipo_plano_teste_injetor",
    primaryKeys = ["tpi_idioma", "tpi_tpl_id"],
    foreignKeys = [
        ForeignKey(
            entity = TypePlanTestEntity::class,
            parentColumns = arrayOf("tpl_id"),
            childColumns = arrayOf("tpi_tpl_id"),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [androidx.room.Index(value = ["tpi_tpl_id", "tpi_idioma"])]
)
data class TranslateTypePlanTestInjectorEntity(

    @NonNull
    @ColumnInfo(name = "tpi_idioma", index = true)
    var language: String = "",

    @NonNull
    @ColumnInfo(name = "tpi_tpl_id", index = true)
    var typePlanTestId: Int = 0,

    @ColumnInfo(name = "tpi_traducao")
    var translate: String? = ""
) : Serializable
