package br.com.tecnomotor.marvin.api.v1.model.global

import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.xId.global.XidPlatform
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
class PlatformSynchronize : Serializable {

    @SerializedName("plataforma")
    @JsonProperty("plataforma")
    var platform = Platform()

    @SerializedName("xidPlatform")
    @JsonProperty("xidPlatform")
    var xidPlatform = XidPlatform()
}