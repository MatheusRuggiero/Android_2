package br.com.tecnomotor.marvin.model.configuration

import br.com.tecnomotor.marvin.dao.entities.configuration.ParameterRemoteEntity
import br.com.tecnomotor.marvin.model.configuration.pk.TranslateParameterRemotePK
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class TranslateParameterRemote : Serializable {
    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var parameterRemote: ParameterRemoteEntity? = ParameterRemoteEntity()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var language: String? = ""

    @SerializedName("traducaoParametroRemotoPK")
    @JsonProperty("traducaoParametroRemotoPK")
    var translateParameterRemotePK: TranslateParameterRemotePK? = TranslateParameterRemotePK()

    @SerializedName("traducao")
    @JsonProperty("traducao")
    var translate: String? = ""
}