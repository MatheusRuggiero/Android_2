package br.com.tecnomotor.marvin.api.v1.model.injector

import br.com.tecnomotor.marvin.model.injector.InjectorPlatform
import br.com.tecnomotor.marvin.model.xId.injector.XidInjectorPlatform
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
class InjectorPlatformSynchronize : Serializable {
    @SerializedName("injetorPlataforma")
    @JsonProperty("injetorPlataforma")
    var injectorPlatform = InjectorPlatform()

    @SerializedName("xidInjectorPlatform")
    @JsonProperty("xidInjectorPlatform")
    var xidInjectorPlatform = XidInjectorPlatform()
}