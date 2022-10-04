package br.com.tecnomotor.marvin.model.injector.pk

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
class RevisionPlanInjectorPK : Serializable {
    @SerializedName("id_pli")
    @JsonProperty("id_pli")
    private val id_pli = 0

    @SerializedName("id_rev_plano")
    @JsonProperty("id_rev_plano")
    private val id_rev_plano = 1
}