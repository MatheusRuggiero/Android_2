package br.com.tecnomotor.marvin.model.pump

import br.com.tecnomotor.marvin.model.global.Version
import br.com.tecnomotor.marvin.model.pump.pk.PumpPlatformPlanPK
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
class PumpPlatformPlanTest : Serializable{
    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var pumpPlanTest: PumpPlanTest = PumpPlanTest()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var pumpPlatform: PumpPlatform = PumpPlatform()

    @SerializedName("bombaPlataformaPlanoPK")
    @JsonProperty("bombaPlataformaPlanoPK")
    var pumpPlatformPlanPK: PumpPlatformPlanPK = PumpPlatformPlanPK()

    @SerializedName("versao")
    @JsonProperty("versao")
    var version: Version = Version()

    constructor()
    constructor(id: Int, pumpPlanTest: PumpPlanTest, pumpPlatform: PumpPlatform, version: Version) {
        this.id = id
        this.pumpPlanTest = pumpPlanTest
        this.pumpPlatform = pumpPlatform
        this.version = version
    }

    constructor(pumpPlatformPlanPK: PumpPlatformPlanPK, version: Version) {
        this.pumpPlatformPlanPK = pumpPlatformPlanPK
        this.version = version
    }


}


