package br.com.tecnomotor.marvin.model.sensor.pk

import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.sensor.Sensor
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
class SensorPlatformPK : Serializable {
    @SerializedName("sensor")
    @JsonProperty("sensor")
    var sensor = Sensor()

    @SerializedName("aplataforma")
    @JsonProperty("aplataforma")
    var platform = Platform()
}