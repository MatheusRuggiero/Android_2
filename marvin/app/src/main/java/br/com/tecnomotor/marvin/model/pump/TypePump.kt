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
class TypePump : Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("nome")
    @JsonProperty("nome")
    var description: String? = ""

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var typePoint: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var brandId: Int = 0

    @SerializedName("marca")
    @JsonProperty("marca")
    var brand: Brand = Brand()

    constructor()
    constructor(id: Int, description: String?, typePoint: Int, brandId: Int) {
        this.id = id
        this.description = description
        this.typePoint = typePoint
        this.brandId = brandId
    }

    constructor(id: Int, description: String?, brand: Brand) {
        this.id = id
        this.description = description
        this.brand = brand
    }


}