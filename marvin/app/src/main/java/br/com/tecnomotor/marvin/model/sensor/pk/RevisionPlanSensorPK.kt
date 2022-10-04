package br.com.tecnomotor.marvin.model.sensor.pk

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
class RevisionPlanSensorPK : Serializable {
    @SerializedName("id_pls")
    @JsonProperty("id_pls")
    var id_pls = 0

    @SerializedName("id_rev_plano")
    @JsonProperty("id_rev_plano")
    var id_rev_plan = 1
}