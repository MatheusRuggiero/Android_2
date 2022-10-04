package br.com.tecnomotor.marvin.api.v1.model.valve

import br.com.tecnomotor.marvin.model.valve.RevisionValve
import br.com.tecnomotor.marvin.model.xId.valve.XidRevisionValve
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
class RevisionValveSynchronize : Serializable {

    @SerializedName("revisaoValvula")
    @JsonProperty("revisaoValvula")
    var revisionValve = RevisionValve()

    @SerializedName("xidRevisionValve")
    @JsonProperty("xidRevisionValve")
    var xidRevisionValve = XidRevisionValve()
}