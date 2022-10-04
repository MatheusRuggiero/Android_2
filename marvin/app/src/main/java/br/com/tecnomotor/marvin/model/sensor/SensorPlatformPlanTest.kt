package br.com.tecnomotor.marvin.model.sensor


import br.com.tecnomotor.marvin.model.global.Version
import br.com.tecnomotor.marvin.model.sensor.pk.SensorPlatformPlanTestPK
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
class SensorPlatformPlanTest : Serializable {

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var planTestSensor: PlanTestSensor = PlanTestSensor()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var sensorPlatform: SensorPlatform = SensorPlatform()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var splSenId: Int = 0

    @SerializedName("sensorPlataformaPlanoTestePK")
    @JsonProperty("sensorPlataformaPlanoTestePK")
    var sensorPlatformPlanTestPK: SensorPlatformPlanTestPK? = SensorPlatformPlanTestPK()

    @SerializedName("versao")
    @JsonProperty("versao")
    var version: Version = Version()

    constructor(planTestSensor: PlanTestSensor, sensorPlatform: SensorPlatform, splSenId: Int, version: Version) {
        this.planTestSensor = planTestSensor
        this.sensorPlatform = sensorPlatform
        this.splSenId = splSenId
        this.version = version
    }

    constructor()

    constructor(sensorPlatformPlanTestPK: SensorPlatformPlanTestPK?, version: Version) {
        this.sensorPlatformPlanTestPK = sensorPlatformPlanTestPK
        this.version = version
    }
}
