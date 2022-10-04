package br.com.tecnomotor.marvin.model.global

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class Version : Serializable {

    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("dataHora")
    @JsonProperty("dataHora")
    var dateTimeRelease: Date? = Calendar.getInstance().time

    @SerializedName("liberada")
    @JsonProperty("liberada")
    var released: Boolean? = false

    constructor(id: Int, dateTimeRelease: Date?, released: Boolean?) {
        this.id = id
        this.dateTimeRelease = dateTimeRelease
        this.released = released
    }

    constructor()
}
