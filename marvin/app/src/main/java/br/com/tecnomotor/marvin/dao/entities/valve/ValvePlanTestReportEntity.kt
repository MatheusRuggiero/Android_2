package br.com.tecnomotor.marvin.dao.entities.valve

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(tableName = "pvr_planos_testes_valvulass_relatorio")
data class ValvePlanTestReportEntity (

    @ColumnInfo(name = "pvr_id")
    var id: Int = 0,

    @ColumnInfo(name = "pvr_tipo_plano")
    var typePlan: Int = 0,

    @ColumnInfo(name = "pvr_tipo_plano_id")
    var typePlanId: Int = 0,

    @ColumnInfo(name = "pvr_revisao")
    var revision: Int = 0,

    @ColumnInfo(name = "pvr_plataforma")
    var platform: String?= "",

    @ColumnInfo(name = "pvr_nome")
    var name: String?= "",

    @ColumnInfo(name = "pvr_nome_en")
    var nameEn: String?= "",

    @ColumnInfo(name = "pvr_nome_es")
    var nameEs: String?= "",

    @ColumnInfo(name = "pvr_duty_1")
    var duty1: Int = 0,

    @ColumnInfo(name = "pvr_duty_2")
    var duty2: Int = 0,

    @ColumnInfo(name = "pvr_duty_3")
    var duty3: Int = 0,

    @ColumnInfo(name = "pvr_duty_4")
    var duty4: Int = 0,

    @ColumnInfo(name = "pvr_duty_5")
    var duty5: Int = 0,

    @ColumnInfo(name = "pvr_limite_1_min")
    var limit1Min: Double = 0.0,

    @ColumnInfo(name = "pvr_limite_1_max")
    var limit1Max: Double = 0.0,

    @ColumnInfo(name = "pvr_limite_2_min")
    var limit2Min: Double = 0.0,

    @ColumnInfo(name = "pvr_limite_2_max")
    var limit2Max: Double = 0.0,

    @ColumnInfo(name = "pvr_limite_3_min")
    var limit3Min: Double = 0.0,

    @ColumnInfo(name = "pvr_limite_3_max")
    var limit3Max: Double = 0.0,

    @ColumnInfo(name = "pvr_limite_4_min")
    var limit4Min: Double = 0.0,

    @ColumnInfo(name = "pvr_limite_4_max")
    var limit4Max: Double = 0.0,

    @ColumnInfo(name = "pvr_limite_5_min")
    var limit5Min: Double = 0.0,

    @ColumnInfo(name = "pvr_limite_5_max")
    var limit5Max: Double = 0.0,

    @ColumnInfo(name = "pvr_token")
    var token: String?= "",

    @ColumnInfo(name = "pvr_rlv_id")
    var valveReportEntityId: Int = 0 

    )  : Serializable 
