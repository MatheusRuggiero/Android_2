package br.com.tecnomotor.marvin.dao.entities.sensor

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
@Entity(tableName = "rsp_revisoes_sensor_planos")
data class RevisionPlanSensorEntity(
    @ColumnInfo(name = "rsp_pls_id")
    var id_pls: Int = 0,
    @PrimaryKey
    @ColumnInfo(name = "rsp_rev_id_plano")
    var id_rev_plano: Int = 1,
    @ColumnInfo(name = "rsp_sen_id")
    var id_sen: Int = 0,
    @ColumnInfo(name = "rsp_rev_id_sensor")
    var id_rev_sen: Int = 1,
    @ColumnInfo(name = "rsp_data_hora")
    var dateHour: Date? = Calendar.getInstance().time,
    @ColumnInfo(name = "rsp_data_hora_str")
    var dataHoraStr: String? = "",
    @ColumnInfo(name = "rsp_motivo")
    var motivation: String? = "",
    @ColumnInfo(name = "rsp_observacao")
    var observation: String? = "",
    @ColumnInfo(name = "rsp_usuario")
    var user: String? = ""
)