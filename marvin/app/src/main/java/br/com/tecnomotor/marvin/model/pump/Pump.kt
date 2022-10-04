package br.com.tecnomotor.marvin.model.pump

import br.com.tecnomotor.marvin.model.global.Brand
import br.com.tecnomotor.marvin.model.global.Revision
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class Pump : Serializable {
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

    @SerializedName("token")
    @JsonProperty("token")
    var token: String? = ""

    @SerializedName("revisao")
    @JsonProperty("revisao")
    var revision: Revision? = Revision()

    @SerializedName("deleted")
    @JsonProperty("deleted")
    var deleted: Boolean? = false

    @SerializedName("descricaoTipo")
    @JsonProperty("descricaoTipo")
    var description: String? = ""

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var typePump: Int = 0

    @SerializedName("tipo")
    @JsonProperty("tipo")
    var type: TypePump? = TypePump()

    @SerializedName("atuador")
    @JsonProperty("atuador")
    var nameActuator: String? = ""

    @SerializedName("referenciaAtuador")
    @JsonProperty("referenciaAtuador")
    var referenceActuator: Int = 0

    @SerializedName("tensaoAtuador")
    @JsonProperty("tensaoAtuador")
    var voltageActuator: Int = 0

    @SerializedName("sentidoGiro")
    @JsonProperty("sentidoGiro")
    var directionRotating: Boolean? = false

    @SerializedName("adaptadorEletrico")
    @JsonProperty("adaptadorEletrico")
    var adapterElectric: String? = ""

    @SerializedName("adptadorHidraulicoPressao")
    @JsonProperty("adptadorHidraulicoPressao")
    var adapterHhydraulicPressure: String? = ""

    @SerializedName("adaptadorHidraulicoAlimentacao")
    @JsonProperty("adaptadorHidraulicoAlimentacao")
    var adapterHydraulicPower: String? = ""

    @SerializedName("adaptadorHidraulicoRetorno")
    @JsonProperty("adaptadorHidraulicoRetorno")
    var adapterHydraulicReturn: String? = ""

    @SerializedName("adaptadorMecanico")
    @JsonProperty("adaptadorMecanico")
    var adapterMechanical: String? = ""

    constructor()
    constructor(
        id: Int,
        code: String?,
        standard: Boolean?,
        brand: Brand,
        application: String?,
        token: String?,
        revision: Revision,
        deleted: Boolean?,
        description: String?,
        typePump: Int,
        type: TypePump?,
        nameActuator: String?,
        referenceActuator: Int,
        voltageActuator: Int,
        directionRotating: Boolean?,
        adapterElectric: String?,
        hydraulicPressureAdapter: String?,
        hydraulicPowerAdapter: String?,
        hydraulicReturnAdapter: String?,
        mechanicalAdapter: String?
    ) {
        this.id = id
        this.code = code
        this.standard = standard
        this.brand = brand
        this.application = application
        this.token = token
        this.revision = revision
        this.deleted = deleted
        this.description = description
        this.typePump = typePump
        this.type = type
        this.nameActuator = nameActuator
        this.referenceActuator = referenceActuator
        this.voltageActuator = voltageActuator
        this.directionRotating = directionRotating
        this.adapterElectric = adapterElectric
        this.adapterHhydraulicPressure = hydraulicPressureAdapter
        this.adapterHydraulicPower = hydraulicPowerAdapter
        this.adapterHydraulicReturn = hydraulicReturnAdapter
        this.adapterMechanical = mechanicalAdapter
    }
}