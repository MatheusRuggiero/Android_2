package br.com.tecnomotor.marvin.api.v1.model.sensor

import br.com.tecnomotor.marvin.model.sensor.RevisionSensor
import br.com.tecnomotor.marvin.model.xId.sensor.XidRevisionSensor
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
class RevisionSensorSynchronize : Serializable {
    @SerializedName("revisaoSensor")
    @JsonProperty("revisaoSensor")
    var revisionSensor = RevisionSensor()

    @SerializedName("xidRevisionSensor")
    @JsonProperty("xidRevisionSensor")
    var xidRevisionSensor = XidRevisionSensor()
}