package br.com.tecnomotor.marvin.dao.entities.valve

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(tableName = "rvp_revisoes_valvula_planos")
data class RevisionPlanValveEntity(
    @PrimaryKey
    @ColumnInfo(name = "rvp_plv_id")
    var id_plv: Int = 0,
    @ColumnInfo(name = "rvp_rev_id_plano")
    var id_rev_plano: Int = 1,
    @ColumnInfo(name = "rvp_val_id")
    var id_val: Int = 0,
    @ColumnInfo(name = "rvp_rev_id_valvula")
    var id_rev_val: Int = 1,
    @ColumnInfo(name = "rvp_data_hora")
    var dateHour: Date = Calendar.getInstance().time,
    @ColumnInfo(name = "rvp_data_hora_str")
    var dateHourStr: String = "",
    @ColumnInfo(name = "rvp_motivo")
    var motivation: String = "",
    @ColumnInfo(name = "rvp_observacao")
    var observation: String = "",
    @ColumnInfo(name = "rvp_usuario")
    var user: String = ""
) : Serializable