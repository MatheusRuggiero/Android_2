package br.com.tecnomotor.marvin.dao.entities.sensor

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
@Entity(tableName = "psr_planos_testes_sensores_relatorio")
data class SensorPlanTestReportEntity(

    @PrimaryKey
    @ColumnInfo(name = "psr_id")
    var psrId: Int = 0,

    @ColumnInfo(name = "psr_tipo_plano")
    var psrTypePlan: Int = 0,

    @ColumnInfo(name = "psr_tipo_plano_id")
    var psrTypePlanId: Int = 0,

    @ColumnInfo(name = "psr_revisao")
    var psrRevision: Int = 0,

    @ColumnInfo(name = "psr_plataforma")
    var psrPlatform: String? = "",

    @ColumnInfo(name = "psr_nome")
    var psrName: String? = "",

    @ColumnInfo(name = "psr_nome_en")
    var psrNameEn: String? = "",

    @ColumnInfo(name = "psr_nome_es")
    var psrNameEs: String? = "",

    @ColumnInfo(name = "psr_pressao_1")
    var psrPressure1: Int = 0,

    @ColumnInfo(name = "psr_pressao_2")
    var psrPressure2: Int = 0,

    @ColumnInfo(name = "psr_pressao_3")
    var psrPressure3: Int = 0,

    @ColumnInfo(name = "psr_pressao_4")
    var psrPressure4: Int = 0,

    @ColumnInfo(name = "psr_pressao_5")
    var psrPressure5: Int = 0,

    @ColumnInfo(name = "psr_limite1_min")
    var psrLimit1Min: Double = 0.0,

    @ColumnInfo(name = "psr_limite1_max")
    var psrLimit1Max: Double = 0.0,

    @ColumnInfo(name = "psr_limite2_min")
    var psrLimit2Min: Double = 0.0,

    @ColumnInfo(name = "psr_limite2_max")
    var psrLimit2Max: Double = 0.0,

    @ColumnInfo(name = "psr_limite3_min")
    var psrLimit3Min: Double = 0.0,

    @ColumnInfo(name = "psr_limite3_max")
    var psrLimit3Max: Double = 0.0,

    @ColumnInfo(name = "psr_limite4_min")
    var psrLimit4Min: Double = 0.0,

    @ColumnInfo(name = "psr_limite4_max")
    var psrLimit4Max: Double = 0.0,

    @ColumnInfo(name = "psr_limite5_min")
    var psrLimit5Min: Double = 0.0,

    @ColumnInfo(name = "psr_limite5_max")
    var psrLimit5Max: Double = 0.0,

    @ColumnInfo(name = "psr_token")
    var psrToken: String? = "",

    @ColumnInfo(name = "psr_rls_id")
    var sensorReportEntityId: Int = 0

) : Serializable
