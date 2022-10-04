package br.com.tecnomotor.marvin.model.pump

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
class TypeReferencePump : Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("drv1")
    @JsonProperty("drv1")
    var drv1: Boolean = false

    @SerializedName("drv2")
    @JsonProperty("drv2")
    var drv2: Boolean = false

    @SerializedName("ext1")
    @JsonProperty("ext1")
    var ext1: Boolean = false

    @SerializedName("ext2")
    @JsonProperty("ext2")
    var ext2: Boolean = false

    @SerializedName("inj1")
    @JsonProperty("inj1")
    var inj1: Boolean = false

    @SerializedName("inj2")
    @JsonProperty("inj2")
    var inj2: Boolean = false

    constructor()
    constructor(id: Int,
                drv1: Boolean,
                drv2: Boolean,
                ext1: Boolean,
                ext2: Boolean,
                inj1: Boolean,
                inj2: Boolean) {
        this.id = id
        this.drv1 = drv1
        this.drv2 = drv2
        this.ext1 = ext1
        this.ext2 = ext2
        this.inj1 = inj1
        this.inj2 = inj2
    }
}