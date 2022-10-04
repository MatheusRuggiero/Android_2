package br.com.tecnomotor.marvin.dao.entities.global

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
    tableName = "tpl_tipo_plano_teste",
)
data class TypePlanTestEntity(

    @PrimaryKey
    @ColumnInfo(name = "tpl_id")
    var id: Int = 0,

    @ColumnInfo(name = "tpl_descricao")
    var description: String? = ""

) : Serializable