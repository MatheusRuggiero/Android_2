package br.com.tecnomotor.marvin.model.injector.pk

import br.com.tecnomotor.marvin.model.injector.TypePointTestInjector
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
//@JsonRootName(value = "TraducaoTipoPontoTesteInjetorPK")
class TranslateTypePointTestInjectorPK : Serializable {
    @SerializedName("tipoPontoTesteInjetor")
    @JsonProperty("tipoPontoTesteInjetor")
    var typePointTestInjector = TypePointTestInjector()

    @SerializedName("idioma")
    @JsonProperty("idioma")
    var language : String? = ""
}