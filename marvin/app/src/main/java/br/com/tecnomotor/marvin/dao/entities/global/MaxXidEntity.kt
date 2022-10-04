package br.com.tecnomotor.marvin.dao.entities.global

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
@Entity(tableName = "max_xid_table")
data class MaxXidEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "max_xid_id")
    var id: Int = 0,

    @ColumnInfo(name = "parameter")
    var parameter : String? = "",

    @ColumnInfo(name = "xid")
    var xid: Int = 0

) : Serializable