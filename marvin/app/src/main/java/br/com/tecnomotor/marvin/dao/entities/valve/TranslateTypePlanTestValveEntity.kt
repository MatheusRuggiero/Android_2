package br.com.tecnomotor.marvin.dao.entities.valve


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
@Entity(tableName = "tpv_traducao_tipo_plano_teste_valvulas")
data class TranslateTypePlanTestValveEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tpv_id")
    var id: Int = 0,

    @ColumnInfo(name = "tpv_idioma")
    var language: String? = "",

    @ColumnInfo(name = "tpv_tpl_id")
    var typePlanTestEntityId: Int = 0,

    @ColumnInfo(name = "tpv_traducao")
    var translate: String? = ""

) : Serializable
