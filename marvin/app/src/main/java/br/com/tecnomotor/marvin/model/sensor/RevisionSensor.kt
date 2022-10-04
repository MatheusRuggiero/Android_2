package br.com.tecnomotor.marvin.model.sensor

import br.com.tecnomotor.marvin.model.sensor.pk.RevisionSensorPK
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class RevisionSensor : Serializable {
    @SerializedName("revisaoSensorPK")
    @JsonProperty("revisaoSensorPK")
    var revisionSensorPK = RevisionSensorPK()

    @SerializedName("dataHora")
    @JsonProperty("dataHora")
    var dateHour = Calendar.getInstance().time

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var dateHourStr = ""

    @SerializedName("motivo")
    @JsonProperty("motivo")
    var motivation = ""

    @SerializedName("observacao")
    @JsonProperty("observacao")
    var observation = ""

    @SerializedName("usuario")
    @JsonProperty("usuario")
    var user = ""


    constructor(
        revisionSensorPK: RevisionSensorPK,
        dateHour: Date?,
        dateHourStr: String,
        motivation: String,
        observation: String,
        user: String
    ) {
        this.revisionSensorPK = revisionSensorPK
        this.dateHour = dateHour
        this.dateHourStr = dateHourStr
        this.motivation = motivation
        this.observation = observation
        this.user = user
    }

        constructor()
}