package br.com.tecnomotor.marvin.model.sensor

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class SensorReport : Serializable {
    var id: Int = 0
    var code: String? = ""
    var standard: Boolean? = false
    var revision: Int = 0
    var application: String? = ""
    var brand: String? = ""
    var adaptPressure: String? = ""
    var adaptConnector: String? = ""
    var token: String? = ""

    constructor()
    constructor(
        id: Int,
        code: String?,
        standard: Boolean?,
        revision: Int,
        application: String?,
        brand: String?,
        adaptPressure: String?,
        adaptConnector: String?,
        token: String?) {
        this.id = id
        this.code = code
        this.standard = standard
        this.revision = revision
        this.application = application
        this.brand = brand
        this.adaptPressure = adaptPressure
        this.adaptConnector = adaptConnector
        this.token = token
    }
}
