package br.com.tecnomotor.marvin.model.pump

import br.com.tecnomotor.marvin.model.global.Brand
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
class TypePointTestPump : Serializable {

    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("descricao")
    @JsonProperty("descricao")
    var description: String? = ""

    @SerializedName("tipo")
    @JsonProperty("tipo")
    var typePoint: Int = 0

    constructor()
    constructor(id: Int, description: String? = null, typePoint: Int = 0) {
        this.id = id
        this.description = description
        this.typePoint = typePoint
    }
}