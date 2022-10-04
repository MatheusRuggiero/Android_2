package br.com.tecnomotor.marvin.model.configuration

import br.com.tecnomotor.marvin.model.configuration.pk.PermissonParameterRemotePK
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
class PermissionParameterRemote : Serializable {

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var parameterRemote: ParameterRemote? = ParameterRemote()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var group: Int = 3

    @SerializedName("permissaoParametroRemotoPK")
    @JsonProperty("permissaoParametroRemotoPK")
    var permissonParameterRemotePK : PermissonParameterRemotePK? = PermissonParameterRemotePK()

    @SerializedName("tipoOperacao")
    @JsonProperty("tipoOperacao")
    var typeOperation: Int = 0
}