package br.com.tecnomotor.marvin.model.valve

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class ValveReport : Serializable {
    var id: Int = 0
    var code: String? = ""
    var standard: Boolean? = false
    var revision: Int = 0
    var application: String? = ""
    var adaptPressure: String? = ""
    var adaptReturn: String? = ""
    var adaptConnector: String? = ""
    var brandName: String? = ""
    var resistanceMinimum: Double = 0.0
    var resistanceMaximum: Double = 0.0
    var typeValve: Int = 0
    var operationValve: Int = 0
    var token: String? = ""

    constructor(
        id: Int,
        code: String?,
        standard: Boolean?,
        revision: Int,
        application: String?,
        adaptPressure: String?,
        adaptReturn: String?,
        adaptConnector: String?,
        brandName: String?,
        resistanceMinimum: Double,
        resistanceMaximum: Double,
        typeValve: Int,
        operationValve: Int,
        token: String?) {
        this.id = id
        this.code = code
        this.standard = standard
        this.revision = revision
        this.application = application
        this.adaptPressure = adaptPressure
        this.adaptReturn = adaptReturn
        this.adaptConnector = adaptConnector
        this.brandName = brandName
        this.resistanceMinimum = resistanceMinimum
        this.resistanceMaximum = resistanceMaximum
        this.typeValve = typeValve
        this.operationValve = operationValve
        this.token = token
    }

    constructor()
}
