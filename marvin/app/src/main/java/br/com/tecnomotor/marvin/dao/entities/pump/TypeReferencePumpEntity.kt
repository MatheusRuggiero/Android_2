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
@Entity(
    tableName = "trb_tipo_referencia_bombas"
)
data class TypeReferencePumpEntity(
    @PrimaryKey
    @ColumnInfo(name = "trb_id")
    var id: Int = 0,
    @ColumnInfo(name = "trb_drv1")
    var drv1: Boolean = false,
    @ColumnInfo(name = "trb_drv2")
    var drv2: Boolean = false,
    @ColumnInfo(name = "trb_ext1")
    var ext1: Boolean = false,
    @ColumnInfo(name = "trb_ext2")
    var ext2: Boolean = false,
    @ColumnInfo(name = "trb_inj1")
    var inj1: Boolean = false,
    @ColumnInfo(name = "trb_inj2")
    var inj2: Boolean = false,
) : Serializable