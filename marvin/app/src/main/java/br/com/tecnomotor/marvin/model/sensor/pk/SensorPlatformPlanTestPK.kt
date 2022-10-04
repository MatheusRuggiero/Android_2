package br.com.tecnomotor.marvin.model.sensor.pk

import br.com.tecnomotor.marvin.model.sensor.PlanTestSensor
import br.com.tecnomotor.marvin.model.sensor.SensorPlatform
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
class SensorPlatformPlanTestPK : Serializable {
    @SerializedName("sensorPlataforma")
    @JsonProperty("sensorPlataforma")
    var sensorPlatform : SensorPlatform? = SensorPlatform()

    @SerializedName("planoTesteSensor")
    @JsonProperty("planoTesteSensor")
    var planTestSensor : PlanTestSensor? = PlanTestSensor()
}