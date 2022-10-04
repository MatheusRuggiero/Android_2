package br.com.tecnomotor.marvin.dao.entities.pump

import androidx.room.*
import br.com.tecnomotor.marvin.dao.entities.global.BrandEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "tptb_tipo_ponto_teste_bomba"
)
data class TypePointTestPumpEntity(
    @PrimaryKey
    @ColumnInfo(name = "tptb_id")
    var id: Int = 0,
    @ColumnInfo(name = "tptb_descricao")
    var description: String = "",
    @ColumnInfo(name = "tptb_tipo_ponto")
    var type: Int = 0
) : Serializable