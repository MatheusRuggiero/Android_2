package br.com.tecnomotor.marvin.api.v1.model.sensor

import br.com.tecnomotor.marvin.model.sensor.SensorPlatform
import br.com.tecnomotor.marvin.model.xId.sensor.XidSensorPlatform
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
class SensorPlatformSynchronize : Serializable {
    @SerializedName("sensorPlataforma")
    @JsonProperty("sensorPlataforma")
    var sensorPlatform = SensorPlatform()

    @SerializedName("xidSensorPlatform")
    @JsonProperty("xidSensorPlatform")
    var xidSensorPlatform = XidSensorPlatform()
}