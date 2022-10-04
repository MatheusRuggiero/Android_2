package br.com.tecnomotor.marvin.model.valve

import br.com.tecnomotor.marvin.model.global.Revision
import br.com.tecnomotor.marvin.model.global.TypePlanTest
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class PlanTestValve: Serializable {

    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("tipoPlanoTeste")
    @JsonProperty("tipoPlanoTeste")
    var typePlanTest: TypePlanTest = TypePlanTest()

    @SerializedName("tipoId")
    @JsonProperty("tipoId")
    var typePlanId: Int = 0

    @SerializedName("duty1")
    @JsonProperty("duty1")
    var duty1: Int = 0

    @SerializedName("duty2")
    @JsonProperty("duty2")
    var duty2: Int = 0

    @SerializedName("duty3")
    @JsonProperty("duty3")
    var duty3: Int = 0

    @SerializedName("duty4")
    @JsonProperty("duty4")
    var duty4: Int = 0

    @SerializedName("duty5")
    @JsonProperty("duty5")
    var duty5: Int = 0

    @SerializedName("limite1Max")
    @JsonProperty("limite1Max")
    var limit1Max: Double = 0.0

    @SerializedName("limite1Min")
    @JsonProperty("limite1Min")
    var limit1Min: Double = 0.0

    @SerializedName("limite2Max")
    @JsonProperty("limite2Max")
    var limit2Max: Double = 0.0

    @SerializedName("limite2Min")
    @JsonProperty("limite2Min")
    var limit2Min: Double = 0.0

    @SerializedName("limite3Max")
    @JsonProperty("limite3Max")
    var limit3Max: Double = 0.0

    @SerializedName("limite3Min")
    @JsonProperty("limite3Min")
    var limit3Min: Double = 0.0

    @SerializedName("limite4Max")
    @JsonProperty("limite4Max")
    var limit4Max: Double = 0.0

    @SerializedName("limite4Min")
    @JsonProperty("limite4Min")
    var limit4Min: Double = 0.0

    @SerializedName("limite5Max")
    @JsonProperty("limite5Max")
    var limit5Max: Double = 0.0

    @SerializedName("limite5Min")
    @JsonProperty("limite5Min")
    var limit5Min: Double = 0.0

    @SerializedName("tempoTeste")
    @JsonProperty("tempoTeste")
    var timeTest: Int = 0

    @SerializedName("numeroPontos")
    @JsonProperty("numeroPontos")
    var numberPoints: Int = 0

    @SerializedName("mainDuty")
    @JsonProperty("mainDuty")
    var mainDuty: Int = 0

    @SerializedName("lockingDuty")
    @JsonProperty("lockingDuty")
    var lockingDuty: Int = 0

    @SerializedName("pwmPeriodo")
    @JsonProperty("pwmPeriodo")
    var pwmTimeCourse: Int = 0

    @SerializedName("pwmPrescaler")
    @JsonProperty("pwmPrescaler")
    var pwmPrescaler: Int = 0

    @SerializedName("token")
    @JsonProperty("token")
    var token: String?= ""

    @SerializedName("revisao")
    @JsonProperty("revisao")
    var revision: Revision = Revision()

    @SerializedName("deleted")
    @JsonProperty("deleted")
    var deleted: Boolean? = false

    constructor()
    constructor(
        id: Int,
        typePlanTest: TypePlanTest,
        typePlanId: Int,
        duty1: Int,
        duty2: Int,
        duty3: Int,
        duty4: Int,
        duty5: Int,
        limit1Max: Double,
        limit1Min: Double,
        limit2Max: Double,
        limit2Min: Double,
        limit3Max: Double,
        limit3Min: Double,
        limit4Max: Double,
        limit4Min: Double,
        limit5Max: Double,
        limit5Min: Double,
        timeTest: Int,
        numberPoints: Int,
        mainDuty: Int,
        lockingDuty: Int,
        pwmTimeCourse: Int,
        pwmPrescaler: Int,
        token: String?,
        revision: Revision,
        deleted: Boolean?) {
        this.id = id
        this.typePlanTest = typePlanTest
        this.typePlanId = typePlanId
        this.duty1 = duty1
        this.duty2 = duty2
        this.duty3 = duty3
        this.duty4 = duty4
        this.duty5 = duty5
        this.limit1Max = limit1Max
        this.limit1Min = limit1Min
        this.limit2Max = limit2Max
        this.limit2Min = limit2Min
        this.limit3Max = limit3Max
        this.limit3Min = limit3Min
        this.limit4Max = limit4Max
        this.limit4Min = limit4Min
        this.limit5Max = limit5Max
        this.limit5Min = limit5Min
        this.timeTest = timeTest
        this.numberPoints = numberPoints
        this.mainDuty = mainDuty
        this.lockingDuty = lockingDuty
        this.pwmTimeCourse = pwmTimeCourse
        this.pwmPrescaler = pwmPrescaler
        this.token = token
        this.revision = revision
        this.deleted = deleted
    }
}
