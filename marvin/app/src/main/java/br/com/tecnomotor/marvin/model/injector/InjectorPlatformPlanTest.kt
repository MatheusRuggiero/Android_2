package br.com.tecnomotor.marvin.model.injector

import br.com.tecnomotor.marvin.model.global.Version
import br.com.tecnomotor.marvin.model.injector.pk.InjectorPlatformPlanPK
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
class InjectorPlatformPlanTest : Serializable {

    @SerializedName("injetorPlataformaPlanoPK")
    @JsonProperty("injetorPlataformaPlanoPK")
    var injectorPlatformPlanPK : InjectorPlatformPlanPK = InjectorPlatformPlanPK()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var injectorPlatform: InjectorPlatform = InjectorPlatform()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var planTestInjector: PlanTestInjector = PlanTestInjector()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var revisionInjector: RevisionInjector? = RevisionInjector()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var typePlanTestId: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var type: Int = 0

    @SerializedName("versao")
    @JsonProperty("versao")
    var version: Version = Version()

    constructor()
    constructor(
        id: Int,
        injectorPlatform: InjectorPlatform,
        planTestInjector: PlanTestInjector,
        revisionInjector: RevisionInjector?,
        version: Version
    ) {
        this.id = id
        this.injectorPlatform = injectorPlatform
        this.planTestInjector = planTestInjector
        this.revisionInjector = revisionInjector
        this.version = version
    }

    constructor(
        id: Int,
        injectorPlatform: InjectorPlatform,
        planTestInjector: PlanTestInjector,
        revisionInjector: RevisionInjector?,
        typePlanTestId: Int,
        type: Int,
        version: Version
    ) {
        this.id = id
        this.injectorPlatform = injectorPlatform
        this.planTestInjector = planTestInjector
        this.revisionInjector = revisionInjector
        this.typePlanTestId = typePlanTestId
        this.type = type
        this.version = version
    }

    constructor(injectorPlatformPlanPK: InjectorPlatformPlanPK, version: Version) {
        this.injectorPlatformPlanPK = injectorPlatformPlanPK
        this.version = version
    }

}

