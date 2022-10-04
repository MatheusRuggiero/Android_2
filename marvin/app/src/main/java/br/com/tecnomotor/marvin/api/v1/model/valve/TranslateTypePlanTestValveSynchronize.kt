package br.com.tecnomotor.marvin.api.v1.model.valve

import br.com.tecnomotor.marvin.model.valve.TranslateTypePlanTestValve
import br.com.tecnomotor.marvin.model.xId.valve.XidTranslateTypePlanTestValve
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
class TranslateTypePlanTestValveSynchronize : Serializable {

    @SerializedName("traducaoTipoPlanoTesteValvula")
    @JsonProperty("traducaoTipoPlanoTesteValvula")
    var translateTypePlanTestValve = TranslateTypePlanTestValve()

    @SerializedName("xidTranslateTypePlanTestValve")
    @JsonProperty("xidTranslateTypePlanTestValve")
    var xidTranslateTypePlanTestValve = XidTranslateTypePlanTestValve()
}