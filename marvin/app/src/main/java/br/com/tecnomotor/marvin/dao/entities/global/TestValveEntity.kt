package br.com.tecnomotor.marvin.dao.entities.global

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
@Entity(tableName = "tva_testes_valvulass")
data class TestValveEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tva_id")
    var id: Int = 0,

    @ColumnInfo(name = "tva_ord_id")
    var orderServiceEntityId: Int = 0,

    @ColumnInfo(name = "tva_result_teste1")
    var resultTest1: Double? = 0.0,

    @ColumnInfo(name = "tva_result_teste2")
    var resultTest2: Double? = 0.0,

    @ColumnInfo(name = "tva_result_teste3")
    var resultTest3: Double? = 0.0,

    @ColumnInfo(name = "tva_result_teste4")
    var resultTest4: Double? = 0.0,

    @ColumnInfo(name = "tva_result_teste5")
    var resultTest5: Double? = 0.0,

    @ColumnInfo(name = "tva_teste_eletrico")
    var electricalTest: Int = 0,

    @ColumnInfo(name = "tva_resistencia")
    var resistance: Double? = 0.0,

    @ColumnInfo(name = "tva_numero_serie")
    var numberSerial: String? = "",

    @ColumnInfo(name = "tva_observacao")
    var note: String? = "",

    @ColumnInfo(name = "tva_data")
    var dateTva: Date = Date(),

    @ColumnInfo(name = "tva_hora")
    var hourTva: String? = "",

    @ColumnInfo(name = "tva_versao_software")
    var versionSoftware: String? = "",

    @ColumnInfo(name = "tva_versao_plataforma")
    var versionPlatform: String? = "",

    @ColumnInfo(name = "tva_removido")
    var removed: Boolean? = false,

    @ColumnInfo(name = "tva_pvr_id")
    var sensorPlanTestReportEntityId: Int = 0

) : Serializable
