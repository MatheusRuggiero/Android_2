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
class Revision : Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("motivo")
    @JsonProperty("motivo")
    var motivation: String? = ""

    @SerializedName("dataHora")
    @JsonProperty("dataHora")
    var dateHour: Date? = Calendar.getInstance().time

    constructor()
    constructor(id: Int, reason: String?, dateHour: Date?) {
        this.id = id
        this.motivation = reason
        this.dateHour = dateHour
    }
}
