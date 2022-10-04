package br.com.tecnomotor.marvin.api.v1.model.configuration

import br.com.tecnomotor.marvin.model.configuration.TranslateParameterRemote
import br.com.tecnomotor.marvin.model.xId.global.XidTranslateParameterRemote
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
class TranslateParameterRemoteSynchronize : Serializable {

    @SerializedName("traducaoParametroRemoto")
    @JsonProperty("traducaoParametroRemoto")
    var translateParameterRemote = TranslateParameterRemote()

    @SerializedName("xidTranslateParameterRemote")
    @JsonProperty("xidTranslateParameterRemote")
    var xidTranslateParameterRemote = XidTranslateParameterRemote()
}