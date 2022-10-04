package br.com.tecnomotor.marvin.model.configuration.pk

import br.com.tecnomotor.marvin.model.configuration.ParameterRemote
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
class PermissonParameterRemotePK : Serializable {
    @SerializedName("parametroRemoto")
    @JsonProperty("parametroRemoto")
    var parameterRemote : ParameterRemote? = ParameterRemote()

    @SerializedName("grupo")
    @JsonProperty("grupo")
    var group = 3
}