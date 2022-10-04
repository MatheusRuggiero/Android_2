package br.com.tecnomotor.marvin.api.v1.model.sensor

import br.com.tecnomotor.marvin.model.sensor.Sensor
import br.com.tecnomotor.marvin.model.xId.sensor.XidSensor
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
class SensorSynchronize : Serializable {

    @SerializedName("sensor")
    @JsonProperty("sensor")
    var sensor = Sensor()

    @SerializedName("xidSensor")
    @JsonProperty("xidSensor")
    var xidSensor = XidSensor()
}