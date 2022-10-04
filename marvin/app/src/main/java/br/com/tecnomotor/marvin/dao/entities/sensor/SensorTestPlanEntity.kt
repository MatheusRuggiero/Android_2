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
@Entity(tableName = "pls_planos_testes_sensores")
data class SensorTestPlanEntity(

    @PrimaryKey
    @ColumnInfo(name = "pls_id")
    var id: Int = 0,

    @ColumnInfo(name = "tpl_descricao")
    var descriptionTypePlan: String? = "",

    @ColumnInfo(name = "pls_tipo_plano_id")
    var typePlanId: Int = 0,

    @ColumnInfo(name = "pls_pressao_1")
    var pressure1: Int = 0,

    @ColumnInfo(name = "pls_pressao_2")
    var pressure2: Int = 0,

    @ColumnInfo(name = "pls_pressao_3")
    var pressure3: Int = 0,

    @ColumnInfo(name = "pls_pressao_4")
    var pressure4: Int = 0,

    @ColumnInfo(name = "pls_pressao_5")
    var pressure5: Int = 0,

    @ColumnInfo(name = "pls_limite1_min")
    var limit1Min: Double = 0.0,

    @ColumnInfo(name = "pls_limite1_max")
    var limit1Max: Double = 0.0,

    @ColumnInfo(name = "pls_limite2_min")
    var limit2Min: Double = 0.0,

    @ColumnInfo(name = "pls_limite2_max")
    var limit2Max: Double = 0.0,

    @ColumnInfo(name = "pls_limite3_min")
    var limit3Min: Double = 0.0,

    @ColumnInfo(name = "pls_limite3_max")
    var limit3Max: Double = 0.0,

    @ColumnInfo(name = "pls_limite4_min")
    var limit4Min: Double = 0.0,

    @ColumnInfo(name = "pls_limite4_max")
    var limit4Max: Double = 0.0,

    @ColumnInfo(name = "pls_limite5_min")
    var limit5Min: Double = 0.0,

    @ColumnInfo(name = "pls_limite5_max")
    var limit5Max: Double = 0.0,

    @ColumnInfo(name = "pls_tempo_teste")
    var testTime: Int = 0,

    @ColumnInfo(name = "pls_numero_pontos")
    var numberPoints: Int = 0,

    @ColumnInfo(name = "pls_token")
    var token: String? = "",

    @ColumnInfo(name = "pls_revisao")
    var revisionEntityId: Int = 0,

    @ColumnInfo(name = "pls_deleted")
    var deleted: Boolean? = false

) : Serializable
