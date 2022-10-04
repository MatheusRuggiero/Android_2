package br.com.tecnomotor.marvin.api.v1.model.injector

import br.com.tecnomotor.marvin.model.injector.TranslateTypePlanTestInjector
import br.com.tecnomotor.marvin.model.xId.injector.XidTranslateTypePlanTestInjector
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
class TranslateTypePlanTestInjectorSynchronize : Serializable {

    @SerializedName("traducaoTipoPlanoTesteInjetor")
    @JsonProperty("traducaoTipoPlanoTesteInjetor")
    var translateTypePlanTestInjector = TranslateTypePlanTestInjector()

    @SerializedName("xidTranslateTypePlanTestInjector")
    @JsonProperty("xidTranslateTypePlanTestInjector")
    var xidTranslateTypePlanTestInjector = XidTranslateTypePlanTestInjector()
}