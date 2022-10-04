package br.com.tecnomotor.marvin.api.v1.model.sensor

import br.com.tecnomotor.marvin.model.sensor.RevisionPlanSensor
import br.com.tecnomotor.marvin.model.xId.sensor.XidRevisionPlanSensor
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
class RevisionPlanSensorSynchronize : Serializable {

    @SerializedName("revisaoPlanoSensor")
    @JsonProperty("revisaoPlanoSensor")
    var revisionPlanSensor = RevisionPlanSensor()

    @SerializedName("xidRevisionPlanSensor")
    @JsonProperty("xidRevisionPlanSensor")
    var xidRevisionPlanSensor = XidRevisionPlanSensor()
}