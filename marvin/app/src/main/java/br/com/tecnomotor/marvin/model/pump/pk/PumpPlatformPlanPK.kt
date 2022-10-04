package br.com.tecnomotor.marvin.model.pump.pk

import br.com.tecnomotor.marvin.model.pump.PumpPlanTest
import br.com.tecnomotor.marvin.model.pump.PumpPlatform
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
class PumpPlatformPlanPK : Serializable {

    @SerializedName("planoTesteBomba")
    @JsonProperty("planoTesteBomba")
    var planTestPump = PumpPlanTest()

    @SerializedName("bombaPlataforma")
    @JsonProperty("bombaPlataforma")
    var pumpPlatform = PumpPlatform()
}