package br.com.tecnomotor.marvin.model.sensor

import br.com.tecnomotor.marvin.model.global.Brand
import br.com.tecnomotor.marvin.model.global.Revision
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
class Sensor : Serializable {

    @SerializedName("id")
    @JsonProperty("id")
    var senId: Int = 0

    @SerializedName("codigo")
    @JsonProperty("codigo")
    var senCode: String? = ""

    @SerializedName("padrao")
    @JsonProperty("padrao")
    var senStandard: Boolean? = false

    @SerializedName("marca")
    @JsonProperty("marca")
    var brand: Brand = Brand()

    @SerializedName("aplicacao")
    @JsonProperty("aplicacao")
    var senApplication: String? = ""

    @SerializedName("adaptadorPressao")
    @JsonProperty("adaptadorPressao")
    var senAdaptPressure: String? = ""

    @SerializedName("adaptadorConector")
    @JsonProperty("adaptadorConector")
    var senAdaptConnector: String? = ""

    @SerializedName("token")
    @JsonProperty("token")
    var senToken: String? = ""

    @SerializedName("revisao")
    @JsonProperty("revisao")
    var revision: Revision = Revision()

    @SerializedName("deleted")
    @JsonProperty("deleted")
    var senDeleted: Boolean? = false

    constructor()
    constructor(
        senId: Int,
        senCode: String?,
        senStandard: Boolean?,
        brand: Brand,
        senApplication: String?,
        senAdaptPressure: String?,
        senAdaptConnector: String?,
        senToken: String?,
        revision: Revision,
        senDeleted: Boolean?
    ) {
        this.senId = senId
        this.senCode = senCode
        this.senStandard = senStandard
        this.brand = brand
        this.senApplication = senApplication
        this.senAdaptPressure = senAdaptPressure
        this.senAdaptConnector = senAdaptConnector
        this.senToken = senToken
        this.revision = revision
        this.senDeleted = senDeleted
    }


}
