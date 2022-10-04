package br.com.tecnomotor.marvin.model.sensor

import br.com.tecnomotor.marvin.model.sensor.pk.RevisionPlanSensorPK
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
class RevisionPlanSensor : Serializable {
    @SerializedName("revisaoPlanoSensorPK")
    @JsonProperty("revisaoPlanoSensorPK")
    var revisionPlanSensorPK = RevisionPlanSensorPK()

    @SerializedName("id_sen")
    @JsonProperty("id_sen")
    var id_sen = 0

    @SerializedName("id_rev_sen")
    @JsonProperty("id_rev_sen")
    var id_rev_sen = 1

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
        revisionPlanSensorPK: RevisionPlanSensorPK,
        id_sen: Int,
        id_rev_sen: Int,
        dateHour: Date,
        dateHourStr: String,
        motivation: String,
        observation: String,
        user: String
    ) {
        this.revisionPlanSensorPK = revisionPlanSensorPK
        this.id_sen = id_sen
        this.id_rev_sen = id_rev_sen
        this.dateHour = dateHour
        this.dateHourStr = dateHourStr
        this.motivation = motivation
        this.observation = observation
        this.user = user
    }

    constructor(
        revisionPlanSensorPK: RevisionPlanSensorPK,
        id_sen: Int,
        id_rev_sen: Int,
        dateHour: Date,
        motivation: String,
        observation: String,
        user: String
    ) {
        this.revisionPlanSensorPK = revisionPlanSensorPK
        this.id_sen = id_sen
        this.id_rev_sen = id_rev_sen
        this.dateHour = dateHour
        this.motivation = motivation
        this.observation = observation
        this.user = user
    }

    constructor()
}