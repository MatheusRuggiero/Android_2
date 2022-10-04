package br.com.tecnomotor.marvin.api.v1.model.global

import br.com.tecnomotor.marvin.model.xId.global.XidHistoricRevision
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
class HistoricRevisionSynchronize : Serializable {
    //TODO OBJECT NOT IMPLEMENTED
    @SerializedName("historicoRevisao")
    @JsonProperty("historicoRevisao")
    var historicRevision = HistoricRevisionSynchronize()

    @SerializedName("xidHistoricRevision")
    @JsonProperty("xidHistoricRevision")
    var xidHistoricRevision = XidHistoricRevision()
}