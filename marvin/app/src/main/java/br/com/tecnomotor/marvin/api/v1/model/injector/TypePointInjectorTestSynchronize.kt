package br.com.tecnomotor.marvin.api.v1.model.injector

import br.com.tecnomotor.marvin.model.injector.TypePointTestInjector
import br.com.tecnomotor.marvin.model.xId.injector.XidTypePointTestInjector
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
class TypePointInjectorTestSynchronize : Serializable {

    @SerializedName("tipoPontoTesteInjetor")
    @JsonProperty("tipoPontoTesteInjetor")
    var typePointTestInjector = TypePointTestInjector()

    @SerializedName("xidTypePointTestInjector")
    @JsonProperty("xidTypePointTestInjector")
    var xidTypePointTestInjector = XidTypePointTestInjector()
}