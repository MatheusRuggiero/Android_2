package br.com.tecnomotor.marvin.model.pump

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
class PumpPlanTest : Serializable {

    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("tipoPlanoTeste")
    @JsonProperty("tipoPlanoTeste")
    var typePlanTest: TypePlanTest = TypePlanTest()

    @SerializedName("tipoId")
    @JsonProperty("tipoId")
    var typePlanId: Int = 0

    @SerializedName("pressao")
    @JsonProperty("pressao")
    var pressure: Int = 0

    @SerializedName("rotacao")
    @JsonProperty("rotacao")
    var rotation: Int = 0

    @SerializedName("vazaoMinima")
    @JsonProperty("vazaoMinima")
    var minimumFlow: Int = 0

    @SerializedName("periodo")
    @JsonProperty("periodo")
    var timeCourse: Int = 0

    @SerializedName("prescaler")
    @JsonProperty("prescaler")
    var prescaler: Int = 0

    @SerializedName("token")
    @JsonProperty("token")
    var token: String?= ""

    @SerializedName("revisao")
    @JsonProperty("revisao")
    var revision: Revision = Revision()

    @SerializedName("deleted")
    @JsonProperty("deleted")
    var deleted: Boolean? = false

    @SerializedName("deletedPermanente")
    @JsonProperty("deletedPermanente")
    var deletePermanent: Boolean? = false

    constructor()
    constructor(
        id: Int,
        typePlanTest: TypePlanTest,
        typePlanId: Int,
        pressure: Int,
        rotation: Int,
        minimumFlow: Int,
        timeCourse: Int,
        prescaler: Int,
        token: String?,
        revision: Revision,
        deleted: Boolean?,
        deletePermanent: Boolean?) {
        this.id = id
        this.typePlanTest = typePlanTest
        this.typePlanId = typePlanId
        this.pressure = pressure
        this.rotation = rotation
        this.minimumFlow = minimumFlow
        this.timeCourse = timeCourse
        this.prescaler = prescaler
        this.token = token
        this.revision = revision
        this.deleted = deleted
        this.deletePermanent = deletePermanent
    }
}

