package br.com.tecnomotor.marvin.model.pump

import br.com.tecnomotor.marvin.model.pump.pk.RevisionPumpPK
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
class RevisionPump : Serializable {
    @SerializedName("revisaoBombaPK")
    @JsonProperty("revisaoBombaPK")
    var revisionPumpPK = RevisionPumpPK()

    @SerializedName("dataHora")
    @JsonProperty("dataHora")
    var dateHour = Calendar.getInstance().time

    @SerializedName("dataHoraStr")
    @JsonProperty("dataHoraStr")
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