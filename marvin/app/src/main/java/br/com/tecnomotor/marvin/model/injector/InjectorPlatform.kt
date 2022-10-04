package br.com.tecnomotor.marvin.model.injector

import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.injector.pk.InjectorPlatformPK
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
class InjectorPlatform : Serializable {

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @SerializedName("injetorPlataformaPK")
    @JsonProperty("injetorPlataformaPK")
    var injectorPlatformPK: InjectorPlatformPK = InjectorPlatformPK()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var injector: Injector? = Injector()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var platform: Platform? = Platform()

    @SerializedName("adaptadorConector")
    @JsonProperty("adaptadorConector")
    var adaptConnector: String? = ""

    @SerializedName("adaptadorPressao")
    @JsonProperty("adaptadorPressao")
    var adaptPressure: String? = ""

    @SerializedName("adaptadorRetorno")
    @JsonProperty("adaptadorRetorno")
    var adaptReturn: String? = ""

    constructor()

    constructor(id: Int, injector: Injector?, platform: Platform?, adaptConnector: String?, adaptPressure: String?, adaptReturn: String?) {
        this.id = id
        this.injector = injector
        this.platform = platform
        this.adaptConnector = adaptConnector
        this.adaptPressure = adaptPressure
        this.adaptReturn = adaptReturn
    }

    constructor(injectorPlatformPK: InjectorPlatformPK, adaptConnector: String?, adaptPressure: String?, adaptReturn: String?) {
        this.injectorPlatformPK = injectorPlatformPK
        this.adaptConnector = adaptConnector
        this.adaptPressure = adaptPressure
        this.adaptReturn = adaptReturn
    }

}
