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
@Entity(tableName = "tbo_testes_bombas")
data class TestPumpEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tbo_id")
    var id: Int = 0,


    @ColumnInfo(name = "tbo_ord_id")
    var orderServiceEntityId: Int = 0,

    @ColumnInfo(name = "tbo_vazao")
    var flowRate: Int = 0,

    @ColumnInfo(name = "tbo_numero_serie")
    var numberSerial: String? = "",

    @ColumnInfo(name = "tbo_observacao")
    var note: String? = "",

    @ColumnInfo(name = "tbo_data")
    var tboDate: Date = Date(),

    @ColumnInfo(name = "tbo_hora")
    var tboHour: String? = "",

    @ColumnInfo(name = "tbo_versao_software")
    var versionSoftware: String? = "",

    @ColumnInfo(name = "tbo_versao_plataforma")
    var versionPlatform: String? = "",

    @ColumnInfo(name = "tbo_removido")
    var removed: Boolean? = false,

    @ColumnInfo(name = "tbo_pbr_id")
    var pumpPlanTestReportEntityId: Int = 0

) : Serializable
