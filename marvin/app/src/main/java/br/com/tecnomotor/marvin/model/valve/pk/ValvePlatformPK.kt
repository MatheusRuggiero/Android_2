package br.com.tecnomotor.marvin.model.valve.pk

import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.valve.Valve
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
class ValvePlatformPK : Serializable {
    @SerializedName("valvula")
    @JsonProperty("valvula")
    var valve = Valve()

    @SerializedName("aplataforma")
    @JsonProperty("aplataforma")
    var platform = Platform()
}