package br.com.tecnomotor.marvin.model.injector.pk

import br.com.tecnomotor.marvin.model.injector.PlanTestInjector
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
//@JsonRootName(value = "PontoTesteInjetorPK")
class PointTestInjectorPK : Serializable {
    @SerializedName("planoTesteInjetor")
    @JsonProperty("planoTesteInjetor")
    var planTestInjector = PlanTestInjector()
    @SerializedName("sequencia")
    @JsonProperty("sequencia")
    var sequence = 0
}