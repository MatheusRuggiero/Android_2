package br.com.tecnomotor.marvin.model.valve

import br.com.tecnomotor.marvin.model.global.Version
import br.com.tecnomotor.marvin.model.valve.pk.ValvePlatformPlanPK
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
class ValvePlatformPlan : Serializable {
    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var valvePlatformId: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var vplValId: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var planTestValveId: Int = 0

    @SerializedName("valvulaPlataformaPlanoPK")
    @JsonProperty("valvulaPlataformaPlanoPK")
    var valvePlatformPlanPK: ValvePlatformPlanPK? = ValvePlatformPlanPK()

    @SerializedName("versao")
    @JsonProperty("versao")
    var version: Version? = Version()



    constructor()

    constructor(valvePlatformPlanPK: ValvePlatformPlanPK?) {
        this.valvePlatformPlanPK = valvePlatformPlanPK
    }


    constructor(valvePlatformId: Int, vplValId: Int, planTestValveId: Int, valvePlatformPlanPK: ValvePlatformPlanPK?, version: Version?) {
        this.valvePlatformId = valvePlatformId
        this.vplValId = vplValId
        this.planTestValveId = planTestValveId
        this.valvePlatformPlanPK = valvePlatformPlanPK
        this.version = version
    }

    constructor(valvePlatformPlanPK: ValvePlatformPlanPK?, version: Version?) {
        this.valvePlatformPlanPK = valvePlatformPlanPK
        this.version = version
    }


}
