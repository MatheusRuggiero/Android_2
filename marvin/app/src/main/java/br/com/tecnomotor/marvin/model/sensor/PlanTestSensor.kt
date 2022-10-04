package br.com.tecnomotor.marvin.model.sensor

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
class PlanTestSensor : Serializable {

    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("tipoPlanoTeste")
    @JsonProperty("tipoPlanoTeste")
    var typePlanTest: TypePlanTest = TypePlanTest()

    @SerializedName("tipoId")
    @JsonProperty("tipoId")
    var typePlanId: Int = 0

    @SerializedName("pressao1")
    @JsonProperty("pressao1")
    var pressure1: Int = 0

    @SerializedName("pressao2")
    @JsonProperty("pressao2")
    var pressure2: Int = 0

    @SerializedName("pressao3")
    @JsonProperty("pressao3")
    var pressure3: Int = 0

    @SerializedName("pressao4")
    @JsonProperty("pressao4")
    var pressure4: Int = 0

    @SerializedName("pressao5")
    @JsonProperty("pressao5")
    var pressure5: Int = 0

    @SerializedName("limite1Min")
    @JsonProperty("limite1Min")
    var limit1Min: Double = 0.0

    @SerializedName("limite1Max")
    @JsonProperty("limite1Max")
    var limit1Max: Double = 0.0

    @SerializedName("limite2Min")
    @JsonProperty("limite2Min")
    var limit2Min: Double = 0.0

    @SerializedName("limite2Max")
    @JsonProperty("limite2Max")
    var limit2Max: Double = 0.0

    @SerializedName("limite3Min")
    @JsonProperty("limite3Min")
    var limit3Min: Double = 0.0

    @SerializedName("limite3Max")
    @JsonProperty("limite3Max")
    var limit3Max: Double = 0.0

    @SerializedName("limite4Min")
    @JsonProperty("limite4Min")
    var limit4Min: Double = 0.0

    @SerializedName("limite4Max")
    @JsonProperty("limite4Max")
    var limit4Max: Double = 0.0

    @SerializedName("limite5Min")
    @JsonProperty("limite5Min")
    var limit5Min: Double = 0.0

    @SerializedName("limite5Max")
    @JsonProperty("limite5Max")
    var limit5Max: Double = 0.0

    @SerializedName("tempoTeste")
    @JsonProperty("tempoTeste")
    var testTime: Int = 0

    @SerializedName("numeroPontos")
    @JsonProperty("numeroPontos")
    var numberPoints: Int = 0

    @SerializedName("token")
    @JsonProperty("token")
    var token: String? = ""

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
        pressure1: Int,
        pressure2: Int,
        pressure3: Int,
        pressure4: Int,
        pressure5: Int,
        limit1Min: Double,
        limit1Max: Double,
        limit2Min: Double,
        limit2Max: Double,
        limit3Min: Double,
        limit3Max: Double,
        limit4Min: Double,
        limit4Max: Double,
        limit5Min: Double,
        limit5Max: Double,
        testTime: Int,
        numberPoints: Int,
        token: String?,
        revision: Revision,
        deleted: Boolean?) {
        this.id = id
        this.typePlanTest = typePlanTest
        this.typePlanId = typePlanId
        this.pressure1 = pressure1
        this.pressure2 = pressure2
        this.pressure3 = pressure3
        this.pressure4 = pressure4
        this.pressure5 = pressure5
        this.limit1Min = limit1Min
        this.limit1Max = limit1Max
        this.limit2Min = limit2Min
        this.limit2Max = limit2Max
        this.limit3Min = limit3Min
        this.limit3Max = limit3Max
        this.limit4Min = limit4Min
        this.limit4Max = limit4Max
        this.limit5Min = limit5Min
        this.limit5Max = limit5Max
        this.testTime = testTime
        this.numberPoints = numberPoints
        this.token = token
        this.revision = revision
        this.deleted = deleted
    }
}
