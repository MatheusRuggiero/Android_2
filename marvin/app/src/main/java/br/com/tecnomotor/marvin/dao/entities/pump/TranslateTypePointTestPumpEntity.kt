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
@Entity(tableName = "tbp_traducao_tipo_ponto_teste_bomba")
data class TranslateTypePointTestPumpEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ttptp_id")
    var id: Int = 0,

    @ColumnInfo(name = "tbp_idioma")
    var language: String? = "",

    @ColumnInfo(name = "tbp_tptb_id")
    var typePoint: Int = 0,

    @ColumnInfo(name = "tbp_traducao")
    var translate: String? = ""
) : Serializable