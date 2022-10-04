package br.com.tecnomotor.marvin.model.injector.pk

import br.com.tecnomotor.marvin.model.global.TypePlanTest
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
//@JsonRootName(value = "TraducaoTipoPlanoTesteInjetorPK")
class TranslateTypePlanTestInjectorPK : Serializable {
    @SerializedName("tipoPlanoTeste")
    @JsonProperty("tipoPlanoTeste")
    var typePlanTest = TypePlanTest()
    @SerializedName("idioma")
    @JsonProperty("idioma")
    var language : String? = ""
}