package br.com.tecnomotor.marvin.model.injector

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
class PlanTestInjector : Serializable {

    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("tipoPlanoTeste")
    @JsonProperty("tipoPlanoTeste")
    var typePlanTest: TypePlanTest? = TypePlanTest()

    @SerializedName("tipoId")
    @JsonProperty("tipoId")
    var typePlanId: Int = 0

    @SerializedName("token")
    @JsonProperty("token")
    var token: String? = ""

    @SerializedName("revisao")
    @JsonProperty("revisao")
    var revision: Revision? = Revision()

    @SerializedName("codifica")
    @JsonProperty("codifica")
    var encoded: Boolean? = false

    @SerializedName("deleted")
    @JsonProperty("deleted")
    var deleted: Boolean? = false

    @SerializedName("usaAdaptSinal")
    @JsonProperty("usaAdaptSinal")
    var usaAdaptSignal: Boolean? = false

    constructor()

    constructor(
        id: Int,
        typePlanTest: TypePlanTest?,
        typePlanId: Int,
        token: String?,
        revision: Revision?,
        encoded: Boolean?,
        deleted: Boolean?,
        usaAdaptSignal: Boolean?
    ) {
        this.id = id
        this.typePlanTest = typePlanTest
        this.typePlanId = typePlanId
        this.token = token
        this.revision = revision
        this.encoded = encoded
        this.deleted = deleted
        this.usaAdaptSignal = usaAdaptSignal
    }

    constructor(id: Int) {
        this.id = id
    }


}
