package br.com.tecnomotor.marvin.model.global

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
class Platform : Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("nome")
    @JsonProperty("nome")
    var name: String = ""

    @SerializedName("pressaoMaxima")
    @JsonProperty("pressaoMaxima")
    var pressureMax: Int = 0

    constructor()

    constructor(id: Int, name: String, pressureMax: Int) {
        this.id = id
        this.name = name
        this.pressureMax = pressureMax
    }
}
