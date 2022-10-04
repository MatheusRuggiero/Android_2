package br.com.tecnomotor.marvin.model.pump.pk

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
class RevisionPlanPumpPK : Serializable {

    @SerializedName("id_plb")
    @JsonProperty("id_plb")
    var id_plb = 0

    @SerializedName("id_rev_plano")
    @JsonProperty("id_rev_plano")
    var id_rev_plano = 1
}