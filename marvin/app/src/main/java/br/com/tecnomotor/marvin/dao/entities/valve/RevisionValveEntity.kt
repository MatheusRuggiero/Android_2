package br.com.tecnomotor.marvin.dao.entities.valve

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(tableName = "rva_revisoes_valvulas")
data class RevisionValveEntity(
    @PrimaryKey
    @ColumnInfo(name = "rva_rev_id")
    var id_rev: Int = 1,
    @ColumnInfo(name = "rva_val_id")
    var id_val: Int = 0,
    @ColumnInfo(name = "rva_data_hora")
    var dateHour: Date = Calendar.getInstance().time,
    @ColumnInfo(name = "rva_data_hora_str")
    var dateHourStr: String = "",
    @ColumnInfo(name = "rva_motivo")
    var motivation: String = "",
    @ColumnInfo(name = "rva_observacao")
    var observation: String = "",
    @ColumnInfo(name = "rva_usuario")
    var user: String = ""
)