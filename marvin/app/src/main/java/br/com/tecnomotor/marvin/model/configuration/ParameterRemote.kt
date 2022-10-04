package br.com.tecnomotor.marvin.model.configuration

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
class ParameterRemote : Serializable {

    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("nome")
    @JsonProperty("nome")
    var name: String? = ""

    @SerializedName("posicao")
    @JsonProperty("posicao")
    var position: Int? = 0

    @SerializedName("valorDefault")
    @JsonProperty("valorDefault")
    var valueDefault: Int? = 0

    @SerializedName("tipoDado")
    @JsonProperty("tipoDado")
    var dataType: Int? = 0

    @SerializedName("tipoPlaca")
    @JsonProperty("tipoPlaca")
    var plateType: Int? = 0

    @SerializedName("tamanhoDado")
    @JsonProperty("tamanhoDado")
    var sizeData: Int? = 0
}