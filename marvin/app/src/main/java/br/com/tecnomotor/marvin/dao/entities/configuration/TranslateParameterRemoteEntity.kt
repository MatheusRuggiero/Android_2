package br.com.tecnomotor.marvin.model.configuration

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
    tableName = "tpr_traducao_parametro_remoto",
    indices = [Index("tpr_parameter_id")]
)
data class TranslateParameterRemoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tpr_parameter_id")
    var id: Int = 0,
    @ColumnInfo(name = "tpr_par_id")
    var parId: Int = 0,
    @ColumnInfo(name = "tpr_par_nome")
    var parName: String? = "",
    @ColumnInfo(name = "tpr_idioma")
    var language: String? = "",
    @ColumnInfo(name = "tpr_traducao")
    var translate: String? = ""
) : Serializable