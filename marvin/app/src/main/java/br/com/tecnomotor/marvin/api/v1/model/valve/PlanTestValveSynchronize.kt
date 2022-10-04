package br.com.tecnomotor.marvin.api.v1.model.valve

import br.com.tecnomotor.marvin.model.valve.PlanTestValve
import br.com.tecnomotor.marvin.model.xId.valve.XidPlanTestValve
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
class PlanTestValveSynchronize : Serializable {
    @SerializedName("planoTesteValvula")
    @JsonProperty("planoTesteValvula")
    var planTestValve = PlanTestValve()

    @SerializedName("xidPlanTestValve")
    @JsonProperty("xidPlanTestValve")
    var xidPlanTestValve = XidPlanTestValve()
}