package br.com.tecnomotor.marvin.model.injector

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class InjectorReport : Serializable {
    var injId: Int = 0
    var injCode: String?= ""
    var injStandard: Boolean? = false
    var injRevision: Int = 0
    var injBrandName: String?= ""
    var injApplication: String?= ""
    var injType: Int = 0
    var injAdaptPressure: String?= ""
    var injAdaptReturn: String?= ""
    var injAdaptConnector: String?= ""
    var injResistanceMinimum: Double? = 0.0
    var injResistanceMaximum: Double? = 0.0
    var injResistanceBMinimum: Double? = 0.0
    var injResistanceBMaximum: Double? = 0.0
    var injToken: String?= ""

    constructor()
    constructor(
        injId: Int,
        injCode: String?,
        injStandard: Boolean?,
        injRevision: Int,
        injBrandName: String?,
        injApplication: String?,
        injType: Int,
        injAdaptPressure: String?,
        injAdaptReturn: String?,
        injAdaptConnector: String?,
        injResistanceMinimum: Double?,
        injResistanceMaximum: Double?,
        injResistanceBMinimum: Double?,
        injResistanceBMaximum: Double?,
        injToken: String?) {
        this.injId = injId
        this.injCode = injCode
        this.injStandard = injStandard
        this.injRevision = injRevision
        this.injBrandName = injBrandName
        this.injApplication = injApplication
        this.injType = injType
        this.injAdaptPressure = injAdaptPressure
        this.injAdaptReturn = injAdaptReturn
        this.injAdaptConnector = injAdaptConnector
        this.injResistanceMinimum = injResistanceMinimum
        this.injResistanceMaximum = injResistanceMaximum
        this.injResistanceBMinimum = injResistanceBMinimum
        this.injResistanceBMaximum = injResistanceBMaximum
        this.injToken = injToken
    }
}
