package br.com.tecnomotor.marvin.model.valve

import br.com.tecnomotor.marvin.model.valve.pk.RevisionValvePK
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
class RevisionValve : Serializable {

    @SerializedName("revisaoValvulaPK")
    @JsonProperty("revisaoValvulaPK")
    var revisionValvePK: RevisionValvePK = RevisionValvePK()

    @SerializedName("id_rev_val")
    @JsonProperty("id_rev_val")
    var id_rev = 1

    @SerializedName("id_val")
    @JsonProperty("id_val")
    var id_val = 0

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
}