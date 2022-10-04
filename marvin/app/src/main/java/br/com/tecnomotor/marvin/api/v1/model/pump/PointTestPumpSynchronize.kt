package br.com.tecnomotor.marvin.api.v1.model.pump

import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.xId.pump.XidPointTestPump
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
class PointTestPumpSynchronize : Serializable {

    @SerializedName("pontoTesteBomba")
    @JsonProperty("pontoTesteBomba")
    var pointTestPump = PointTestPump()

    @SerializedName("xidPointTestPump")
    @JsonProperty("xidPointTestPump")
    var xidPointTestPump = XidPointTestPump()
}