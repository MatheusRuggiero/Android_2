package br.com.tecnomotor.marvin.api.v1.model.global

import br.com.tecnomotor.marvin.model.global.TypePlanTest
import br.com.tecnomotor.marvin.model.xId.global.XidTypePlanTest
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
class TypePlanTestSynchronize : Serializable {

    @SerializedName("tipoPlanoTeste")
    @JsonProperty("tipoPlanoTeste")
    var typePlanTest = TypePlanTest()

    @SerializedName("xidTypePlanTest")
    @JsonProperty("xidTypePlanTest")
    var xidTypePlanTest = XidTypePlanTest()
}