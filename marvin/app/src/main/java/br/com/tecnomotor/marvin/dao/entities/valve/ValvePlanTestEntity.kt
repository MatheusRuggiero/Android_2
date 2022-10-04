package br.com.tecnomotor.marvin.dao.entities.valve

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
@Entity(tableName = "plv_planos_testes_valvulass")
data class ValvePlanTestEntity(
    @PrimaryKey
    @ColumnInfo(name = "plv_id")
    var id: Int = 0,

    @ColumnInfo(name = "plv_tpl_id")
    var typePlanTestEntityId: Int = 0,

    @ColumnInfo(name = "plv_tipo_plano_description")
    var  descriptionTypePlan : String = "",

    @ColumnInfo(name = "plv_tipo_plano_id")
    var typePlanId: Int = 0,

    @ColumnInfo(name = "plv_duty_1")
    var duty1: Int = 0,

    @ColumnInfo(name = "plv_duty_2")
    var duty2: Int = 0,

    @ColumnInfo(name = "plv_duty_3")
    var duty3: Int = 0,

    @ColumnInfo(name = "plv_duty_4")
    var duty4: Int = 0,

    @ColumnInfo(name = "plv_duty_5")
    var duty5: Int = 0,

    @ColumnInfo(name = "plv_limite_1_max")
    var limit1Max: Double = 0.0,

    @ColumnInfo(name = "plv_limite_1_min")
    var limit1Min: Double = 0.0,

    @ColumnInfo(name = "plv_limite_2_max")
    var limit2Max: Double = 0.0,

    @ColumnInfo(name = "plv_limite_2_min")
    var limit2Min: Double = 0.0,

    @ColumnInfo(name = "plv_limite_3_max")
    var limit3Max: Double = 0.0,

    @ColumnInfo(name = "plv_limite_3_min")
    var limit3Min: Double = 0.0,

    @ColumnInfo(name = "plv_limite_4_max")
    var limit4Max: Double = 0.0,

    @ColumnInfo(name = "plv_limite_4_min")
    var limit4Min: Double = 0.0,

    @ColumnInfo(name = "plv_limite_5_max")
    var limit5Max: Double = 0.0,

    @ColumnInfo(name = "plv_limite_5_min")
    var limit5Min: Double = 0.0,

    @ColumnInfo(name = "plv_tempo_teste")
    var timeTest: Int = 0,

    @ColumnInfo(name = "plv_numero_pontos")
    var numberPoints: Int = 0,

    @ColumnInfo(name = "plv_main_duty")
    var mainDuty: Int = 0,

    @ColumnInfo(name = "plv_locking_duty")
    var lockingDuty: Int = 0,

    @ColumnInfo(name = "plv_pwm_periodo")
    var pwmTimeCourse: Int = 0,

    @ColumnInfo(name = "plv_pwm_prescaler")
    var pwmPrescaler: Int = 0,

    @ColumnInfo(name = "plv_token")
    var token: String? = "",

    @ColumnInfo(name = "plv_revisao")
    var revisionEntityId: Int = 0,

    @ColumnInfo(name = "plv_deleted")
    var deleted: Boolean? = false

) : Serializable
