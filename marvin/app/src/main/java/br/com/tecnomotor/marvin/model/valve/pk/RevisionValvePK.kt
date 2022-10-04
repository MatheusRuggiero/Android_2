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
class RevisionValvePK : Serializable {
    @SerializedName("id_rev")
    @JsonProperty("id_rev")
    var id_rev = 1

    @SerializedName("id_val")
    @JsonProperty("id_val")
    var id_val = 0
}