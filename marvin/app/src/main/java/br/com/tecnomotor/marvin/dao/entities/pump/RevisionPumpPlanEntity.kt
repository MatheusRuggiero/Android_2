package br.com.tecnomotor.marvin.dao.entities.pump

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
@Entity(
    tableName = "rbp_revisoes_bomba_planos",
)
data class RevisionPumpPlanEntity(
    @PrimaryKey
    @ColumnInfo(name = "rbp_rev_id_plano")
    var id_rev_plano: Int = 1,

    @ColumnInfo(name = "rbp_plb_id")
    var id_plb: Int = 0,

    @ColumnInfo(name = "rbp_bom_id")
    var id_bom: Int = 0,

    @ColumnInfo(name = "rbp_rev_id_bomba")
    var id_rev_bom: Int = 0,

    @ColumnInfo(name = "date_hour")
    var dateHour: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "date_hour_str")
    var dataHoraStr: String? = "",

    @ColumnInfo(name = "rbp_motivo")
    var motivation: String? = "",

    @ColumnInfo(name = "rbp_observacao")
    var observation: String? = "",

    @ColumnInfo(name = "rbp_usuario")
    var user: String? = "",

    ) : Serializable
