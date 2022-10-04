package br.com.tecnomotor.marvin.model.valve.pk

import br.com.tecnomotor.marvin.model.valve.PlanTestValve
import br.com.tecnomotor.marvin.model.valve.ValvePlatform
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
class ValvePlatformPlanPK : Serializable {
    @SerializedName("valvulaPlataforma")
    @JsonProperty("valvulaPlataforma")
    var valvePlatform = ValvePlatform()

    @SerializedName("planoTesteValvula")
    @JsonProperty("planoTesteValvula")
    var planTestValve = PlanTestValve()
}