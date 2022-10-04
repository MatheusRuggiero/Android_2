package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "tpo_traducao_tipo_ponto_teste_injetor",
    primaryKeys = ["tpo_idioma", "tpo_tpt_id"],
    foreignKeys = [
        ForeignKey(
            entity = TypePointInjectorTestEntity::class,
            parentColumns = arrayOf("tpt_id"),
            childColumns = arrayOf("tpo_tpt_id"),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [androidx.room.Index(value = ["tpo_tpt_id", "tpo_idioma"])]
)
data class TranslateTypePointTestInjectorEntity(

    @NonNull
    @ColumnInfo(name = "tpo_idioma", index = true)
    var language: String = "",

    @NonNull
    @ColumnInfo(name = "tpo_tpt_id", index = true)
    var typePointInjectorTestId: Int = 0,

    @ColumnInfo(name = "tpo_traducao")
    var translate: String? = ""
) : Serializable
