package br.com.tecnomotor.marvin.model.injector

import br.com.tecnomotor.marvin.model.injector.pk.RevisionInjectorPK
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
class RevisionInjector : Serializable, Comparable<RevisionInjector> {
    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id_rev: Int? = 1

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id_inj: Int = 0

    @SerializedName("revisaoInjetorPK")
    @JsonProperty("revisaoInjetorPK")
    var revisionInjectorPK: RevisionInjectorPK? = RevisionInjectorPK()

    @SerializedName("dataHora")
    @JsonProperty("dataHora")
    var dateHour: Date? = Calendar.getInstance().time

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var dataHoraStr: String? = ""

    @SerializedName("motivo")
    @JsonProperty("motivo")
    var motivation: String? = ""

    @SerializedName("observacao")
    @JsonProperty("observacao")
    var observation: String? = ""

    @SerializedName("usuario")
    @JsonProperty("usuario")
    var user: String? = ""

    constructor()
    constructor(
        id: Int,
        id_rev: Int?,
        id_inj: Int,
        dateHour: Date?,
        dataHoraStr: String?,
        reason: String?,
        observation: String?,
        user: String?
    ) {
        this.id = id
        this.id_rev = id_rev
        this.id_inj = id_inj
        this.dateHour = dateHour
        this.dataHoraStr = dataHoraStr
        this.motivation = reason
        this.observation = observation
        this.user = user
    }

    override fun compareTo(o: RevisionInjector): Int {
        return if (this.id_rev!! > o.id_rev!!) {
            1
        } else {
            -1
        }
    }

}