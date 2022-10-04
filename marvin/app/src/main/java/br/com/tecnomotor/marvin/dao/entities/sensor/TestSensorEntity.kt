package br.com.tecnomotor.marvin.dao.entities.sensor

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
@Entity(tableName = "tse_testes_sensores")
data class TestSensorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tse_id")
    var id: Int = 0,

    @ColumnInfo(name = "tse_ord_id")
    var orderServiceEntityId: Int = 0,

    @ColumnInfo(name = "tse_result_teste1")
    var resultTest1: Double? = 0.0,

    @ColumnInfo(name = "tse_result_teste2")
    var resultTest2: Double? = 0.0,

    @ColumnInfo(name = "tse_result_teste3")
    var resultTest3: Double? = 0.0,

    @ColumnInfo(name = "tse_result_teste4")
    var resultTest4: Double? = 0.0,

    @ColumnInfo(name = "tse_result_teste5")
    var resultTest5: Double? = 0.0,

    @ColumnInfo(name = "tse_numero_serie")
    var numberSerial: String? = "",

    @ColumnInfo(name = "tse_observacao")
    var note: String? = "",

    @ColumnInfo(name = "tse_data")
    var tseDate: Date = Date(),

    @ColumnInfo(name = "tse_hora")
    var tseHour: String? = "",

    @ColumnInfo(name = "tse_versao_software")
    var versionSoftware: String? = "",

    @ColumnInfo(name = "tse_versao_plataforma")
    var versionPlatform: String? = "",

    @ColumnInfo(name = "tse_removido")
    var removed: Boolean? = false,

    @ColumnInfo(name = "tse_psr_id")
    var sensorPlanTestReportEntityId: Int = 0

) : Serializable
