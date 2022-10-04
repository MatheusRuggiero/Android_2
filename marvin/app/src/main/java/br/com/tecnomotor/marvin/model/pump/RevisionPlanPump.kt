package br.com.tecnomotor.marvin.model.pump

import br.com.tecnomotor.marvin.model.pump.pk.RevisionPlanPumpPK
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
class RevisionPlanPump : Serializable {
    @SerializedName("revisaoPlanoBombaPK")
    @JsonProperty("revisaoPlanoBombaPK")
    var revisionPlanPumpPK = RevisionPlanPumpPK()

    @SerializedName("id_bom")
    @JsonProperty("id_bom")
    var id_bom = 0

    @SerializedName("id_rev_bom")
    @JsonProperty("id_rev_bom")
    var id_rev_bom = 0

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