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
class RevisionPumpPK : Serializable {

    @SerializedName("id_rev")
    @JsonProperty("id_rev")
    var id_rev = 1

    @SerializedName("id_bom")
    @JsonProperty("id_bom")
    var id_bom = 0
}