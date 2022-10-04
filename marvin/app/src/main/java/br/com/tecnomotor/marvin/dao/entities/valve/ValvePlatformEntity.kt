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
@Entity(tableName = "vpl_valvulas_plataformas")
data class ValvePlatformEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "vpl_plat_id")
    var platformEntityId: Int = 0,

    @ColumnInfo(name = "vpl_val_id")
    var valveEntityId: Int = 0

) : Serializable
