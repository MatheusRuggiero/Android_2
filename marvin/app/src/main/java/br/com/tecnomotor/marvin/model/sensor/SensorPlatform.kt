package br.com.tecnomotor.marvin.model.sensor


import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.sensor.pk.SensorPlatformPK
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class SensorPlatform : Serializable {

    @SerializedName("sensorPlataformaPK")
    @JsonProperty("sensorPlataformaPK")
    var sensorPlatformPK: SensorPlatformPK = SensorPlatformPK()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var platform: Platform = Platform()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var sensor: Sensor = Sensor()

    constructor()
    constructor(id: Int, platform: Platform, sensor: Sensor) {
        this.id = id
        this.platform = platform
        this.sensor = sensor
    }

    constructor(sensorPlatformPK: SensorPlatformPK) {
        this.sensorPlatformPK = sensorPlatformPK
    }

    constructor(sensorPlatformPK: SensorPlatformPK, id: Int, platform: Platform, sensor: Sensor) {
        this.sensorPlatformPK = sensorPlatformPK
        this.id = id
        this.platform = platform
        this.sensor = sensor
    }


}
