package br.com.tecnomotor.marvin.api.v1.model.configuration

import br.com.tecnomotor.marvin.model.configuration.ParameterRemote
import br.com.tecnomotor.marvin.model.xId.global.XidParameterRemote
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
class ParameterRemoteSynchronize : Serializable {
    @SerializedName("parametroRemoto")
    @JsonProperty("parametroRemoto")
    var parameterRemote = ParameterRemote()

    @SerializedName("xidParameterRemote")
    @JsonProperty("xidParameterRemote")
    var xidParameterRemote = XidParameterRemote()
}