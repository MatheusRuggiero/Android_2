package br.com.tecnomotor.marvin.model.valve


import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.valve.pk.ValvePlatformPK
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
class ValvePlatform : Serializable {

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var platform: Platform = Platform()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var valve: Valve = Valve()

    @SerializedName("valvulaPlataformaPK")
    @JsonProperty("valvulaPlataformaPK")
    var valvePlatformPK : ValvePlatformPK? = ValvePlatformPK()

    constructor()
    constructor(id: Int, platform: Platform, valve: Valve) {
        this.id = id
        this.platform = platform
        this.valve = valve
    }

    constructor(valvePlatformPK: ValvePlatformPK?) {
        this.valvePlatformPK = valvePlatformPK
    }


}
