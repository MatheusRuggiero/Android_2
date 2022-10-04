package br.com.tecnomotor.marvin.model.pump

import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.pump.pk.PumpPlatformPK
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
class PumpPlatform : Serializable {

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var platform: Platform = Platform()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var pump: Pump = Pump()

    @SerializedName("bombaPlataformaPK")
    @JsonProperty("bombaPlataformaPK")
    var pumpPlatformPK: PumpPlatformPK? = PumpPlatformPK()

    @SerializedName("adaptadorEletrico")
    @JsonProperty("adaptadorEletrico")
    var electricAdapter: String? = ""

    @SerializedName("adaptadorHidraPressao")
    @JsonProperty("adaptadorHidraPressao")
    var adapterHydraulicPressure: String? = ""

    @SerializedName("adaptadorHidraAlimentacao")
    @JsonProperty("adaptadorHidraAlimentacao")
    var adapterHydraulicPower: String? = ""

    @SerializedName("adaptadorHidraRetorno")
    @JsonProperty("adaptadorHidraRetorno")
    var adapterHydraulicReturn: String? = ""

    @SerializedName("adaptadorMecanico")
    @JsonProperty("adaptadorMecanico")
    var adapterMechanical: String? = ""

    constructor()
    constructor(
        id: Int,
        platform: Platform,
        pump: Pump,
        electricAdapter: String?,
        adapterHydraulicPressure: String?,
        adapterHydraulicPower: String?,
        adapterHydraulicReturn: String?,
        adapterMechanical: String?
    ) {
        this.id = id
        this.platform = platform
        this.pump = pump
        this.electricAdapter = electricAdapter
        this.adapterHydraulicPressure = adapterHydraulicPressure
        this.adapterHydraulicPower = adapterHydraulicPower
        this.adapterHydraulicReturn = adapterHydraulicReturn
        this.adapterMechanical = adapterMechanical
    }

    constructor(
        pumpPlatformPK: PumpPlatformPK?,
        electricAdapter: String?,
        adapterHydraulicPressure: String?,
        adapterHydraulicPower: String?,
        adapterHydraulicReturn: String?,
        adapterMechanical: String?
    ) {
        this.pumpPlatformPK = pumpPlatformPK
        this.electricAdapter = electricAdapter
        this.adapterHydraulicPressure = adapterHydraulicPressure
        this.adapterHydraulicPower = adapterHydraulicPower
        this.adapterHydraulicReturn = adapterHydraulicReturn
        this.adapterMechanical = adapterMechanical
    }


}


