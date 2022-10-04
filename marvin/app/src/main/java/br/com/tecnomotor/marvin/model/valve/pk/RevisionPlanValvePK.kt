package br.com.tecnomotor.marvin.model.valve.pk

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
class RevisionPlanValvePK : Serializable {
    @SerializedName("id_plv")
    @JsonProperty("id_plv")
    var id_plv = 0

    @SerializedName("id_rev_plano")
    @JsonProperty("id_rev_plano")
    var id_rev_plan = 1
}