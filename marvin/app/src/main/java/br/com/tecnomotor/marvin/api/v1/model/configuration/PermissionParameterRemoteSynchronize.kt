package br.com.tecnomotor.marvin.api.v1.model.configuration

import br.com.tecnomotor.marvin.model.configuration.PermissionParameterRemote
import br.com.tecnomotor.marvin.model.xId.global.XidPermissionParameterRemote
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
class PermissionParameterRemoteSynchronize : Serializable {

    @SerializedName("permissaoParametroRemoto")
    @JsonProperty("permissaoParametroRemoto")
    var permissionParameterRemote = PermissionParameterRemote()

    @SerializedName("xidPermissionParameterRemote")
    @JsonProperty("xidPermissionParameterRemote")
    var xidPermissionParameterRemote = XidPermissionParameterRemote()
}