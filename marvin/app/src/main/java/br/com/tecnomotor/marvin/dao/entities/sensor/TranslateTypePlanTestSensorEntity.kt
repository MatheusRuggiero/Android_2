package br.com.tecnomotor.marvin.dao.entities.sensor


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
@Entity(tableName = "tps_traducao_tipo_plano_teste_sensor")
data class TranslateTypePlanTestSensorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tps_id")
    var id: Int = 0,

    @ColumnInfo(name = "tps_idioma")
    var language: String? = "",

    @ColumnInfo(name = "tps_tpl_id")
    var typePlanTestEntityId: Int = 0,

    @ColumnInfo(name = "tps_traducao")
    var translate: String? = ""

) : Serializable
