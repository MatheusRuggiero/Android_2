package br.com.tecnomotor.marvin.api.v1.model.pump

import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.model.xId.pump.XidPump
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
class PumpSynchronize : Serializable {

    @SerializedName("bomba")
    @JsonProperty("bomba")
    var pump = Pump()

    @SerializedName("xidPump")
    @JsonProperty("xidPump")
    var xidPump = XidPump()
}