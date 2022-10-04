package br.com.tecnomotor.marvin.model.pump.pk

import br.com.tecnomotor.marvin.model.pump.PumpPlanTest
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
class PointTestPumpPK : Serializable {

    @SerializedName("planoTesteBomba")
    @JsonProperty("planoTesteBomba")
    var planTestPump = PumpPlanTest()

    @SerializedName("sequencia")
    @JsonProperty("sequencia")
    var sequence = 0

    constructor()

    constructor(planTestPump: PumpPlanTest, sequence: Int) {
        this.planTestPump = planTestPump
        this.sequence = sequence
    }


}