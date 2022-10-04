package br.com.tecnomotor.marvin.dao.entities.configuration

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
    tableName = "par_parametros_remotos",
    indices = [Index("par_id")]
)
data class ParameterRemoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "par_id")
    var id: Int = 0,
    @ColumnInfo(name = "par_nome")
    var name: String? = "",
    @ColumnInfo(name = "par_posicao")
    var position: Int? = 0,
    @ColumnInfo(name = "par_valor_default")
    var valueDefault: Int? = 0,
    @ColumnInfo(name = "par_tipo_dado")
    var dataType: Int? = 0,
    @ColumnInfo(name = "par_tipo_placa")
    var plateType: Int? = 0,
    @ColumnInfo(name = "par_tamanho_dado")
    var sizeData: Int? = 0
) : Serializable