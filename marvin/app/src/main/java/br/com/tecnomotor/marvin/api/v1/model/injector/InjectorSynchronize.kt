package br.com.tecnomotor.marvin.api.v1.model.injector

import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.xId.injector.XidInjector
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
class InjectorSynchronize : Serializable {
    @SerializedName("injetor")
    @JsonProperty("injetor")
    var injector = Injector()

    @SerializedName("xidInjector")
    @JsonProperty("xidInjector")
    var xidInjector = XidInjector()
}