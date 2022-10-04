package br.com.tecnomotor.marvin.model.injector

import br.com.tecnomotor.marvin.controller.test.injector.InjectorTestPointType
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
class TypePointTestInjector : Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("descricao")
    @JsonProperty("descricao")
    var description: String = ""

    @SerializedName("tipoDoPonto")
    @JsonProperty("tipoDoPonto")
    var typePoint: Int = 0

    fun getTypeTest():InjectorTestPointType {
        return InjectorTestPointType.valueOf(this.typePoint)
    }

    fun isHeating():Boolean {
        return getTypeTest() == InjectorTestPointType.HEATING
    }

    constructor()
    constructor(id: Int, description: String, typePoint: Int) {
        this.id = id
        this.description = description
        this.typePoint = typePoint
    }
}
