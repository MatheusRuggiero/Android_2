package br.com.tecnomotor.marvin.api.v1.model.injector

import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import br.com.tecnomotor.marvin.model.xId.injector.XidPointTestInjector
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
class PointTestInjectorSynchronize : Serializable {
    @SerializedName("pontoTesteInjetor")
    @JsonProperty("pontoTesteInjetor")
    var pointTestInjector = PointTestInjector()

    @SerializedName("xidPointTestInjector")
    @JsonProperty("xidPointTestInjector")
    var xidPointTestInjector = XidPointTestInjector()
}