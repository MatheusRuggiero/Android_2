package br.com.tecnomotor.marvin.model.injector

import br.com.tecnomotor.marvin.model.injector.pk.RevisionPlanInjectorPK
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
class RevisionPlanInjector : Serializable {
    @SerializedName("revisaoPlanoInjetorPK")
    @JsonProperty("revisaoPlanoInjetorPK")
    var revisionPlanInjectorPK = RevisionPlanInjectorPK()

    @SerializedName("id_inj")
    @JsonProperty("id_inj")
    var id_inj = 0

    @SerializedName("id_rev_inj")
    @JsonProperty("id_rev_inj")
    var id_rev_inj = 0

    @SerializedName("dataHora")
    @JsonProperty("dataHora")
    var dateHour = Calendar.getInstance().time

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var dataHoraStr = ""

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