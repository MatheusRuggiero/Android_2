package br.com.tecnomotor.marvin.dao.entities.pump

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
@Entity(tableName = "tptb_tipo_ponto_teste_bomba")
data class TypeTestPointPumpEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tptb_id")
    var id: Int = 0,

    @ColumnInfo(name = "tptb_descricao")
    var description: String? = "",

    @ColumnInfo(name = "tptb_tipo_ponto")
    var typePoint: Int = 0,

    @ColumnInfo(name = "tptb_mar_id")
    var brandId: Int = 0,

) : Serializable