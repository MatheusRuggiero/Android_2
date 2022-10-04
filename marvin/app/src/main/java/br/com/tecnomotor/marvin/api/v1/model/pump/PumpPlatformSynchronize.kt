package br.com.tecnomotor.marvin.api.v1.model.pump

import br.com.tecnomotor.marvin.model.pump.PumpPlatform
import br.com.tecnomotor.marvin.model.xId.pump.XidPumpPlatform
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
class PumpPlatformSynchronize : Serializable {

    @SerializedName("bombaPlataforma")
    @JsonProperty("bombaPlataforma")
    var pumpPlatform = PumpPlatform()

    @SerializedName("xidPumpPlatform")
    @JsonProperty("xidPumpPlatform")
    var xidPumpPlatform = XidPumpPlatform()
}