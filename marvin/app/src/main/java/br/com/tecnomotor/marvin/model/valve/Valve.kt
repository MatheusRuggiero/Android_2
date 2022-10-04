package br.com.tecnomotor.marvin.model.valve


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
class Valve : Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("codigo")
    @JsonProperty("codigo")
    var code: String? = ""

    @SerializedName("padrao")
    @JsonProperty("padrao")
    var standard: Boolean? = false

    @SerializedName("marca")
    @JsonProperty("marca")
    var brand: Brand = Brand()

    @SerializedName("aplicacao")
    @JsonProperty("aplicacao")
    var application: String? = ""

    @SerializedName("adaptadorPressao")
    @JsonProperty("adaptadorPressao")
    var adaptPressure: String? = ""

    @SerializedName("adaptadorRetorno")
    @JsonProperty("adaptadorRetorno")
    var adaptReturn: String? = ""

    @SerializedName("adaptadorConector")
    @JsonProperty("adaptadorConector")
    var adaptConnector: String? = ""

    @SerializedName("resistenciaMinima")
    @JsonProperty("resistenciaMinima")
    var resistanceMinimum: Double = 0.0

    @SerializedName("resistenciaMaxima")
    @JsonProperty("resistenciaMaxima")
    var resistanceMaximum: Double = 0.0

    @SerializedName("tipo")
    @JsonProperty("tipo")
    var typeValve: Int = 0

    @SerializedName("operacao")
    @JsonProperty("operacao")
    var operationValve: Int = 0

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
        code: String?,
        standard: Boolean?,
        brand: Brand,
        application: String?,
        adaptPressure: String?,
        adaptReturn: String?,
        adaptConnector: String?,
        resistanceMinimum: Double,
        resistanceMaximum: Double,
        typeValve: Int,
        operationValve: Int,
        token: String?,
        revision: Revision,
        deleted: Boolean?) {
        this.id = id
        this.code = code
        this.standard = standard
        this.brand = brand
        this.application = application
        this.adaptPressure = adaptPressure
        this.adaptReturn = adaptReturn
        this.adaptConnector = adaptConnector
        this.resistanceMinimum = resistanceMinimum
        this.resistanceMaximum = resistanceMaximum
        this.typeValve = typeValve
        this.operationValve = operationValve
        this.token = token
        this.revision = revision
        this.deleted = deleted
    }
}
