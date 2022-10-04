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
class Brand : Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0
    @SerializedName("nome")
    @JsonProperty("name")
    var name: String? = ""

    constructor()
    constructor(id: Int, name: String?) {
        this.id = id
        this.name = name
    }
}





