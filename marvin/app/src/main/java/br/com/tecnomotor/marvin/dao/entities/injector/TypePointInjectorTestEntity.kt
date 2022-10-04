package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
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
    tableName = "tpt_tipo_ponto_teste_injetor",
    indices = [Index("tpt_id")]
)
data class TypePointInjectorTestEntity(

    @PrimaryKey
    @ColumnInfo(name = "tpt_id")
    var id: Int = 0,

    @ColumnInfo(name = "tpt_descricao")
    var description: String = "",

    @ColumnInfo(name = "tpt_tipo_ponto")
    var typePoint: Int = 0,

    ) : Serializable
