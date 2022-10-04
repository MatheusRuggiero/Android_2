package br.com.tecnomotor.marvin.model.injector

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class PointTestInjectorReport : Serializable {
    var pirId: Int = 0
    var sequence: Int = 0
    var description: String? = ""
    var descriptionEn: String? = ""
    var descriptionEs: String? = ""
    var typeTest: Int = 0
    var pressure: Int = 0
    var frequency: Int = 0
    var injectionTime: Int = 0
    var measureInjection: Boolean? = false
    var measureReturn: Boolean? = false
    var injectionMaximum: Double? = 0.0
    var injectionMinimum: Double? = 0.0
    var returnMaximum: Double? = 0.0
    var returnMinimum: Double? = 0.0

    constructor()
    constructor(
        pirId: Int,
        sequence: Int,
        description: String?,
        descriptionEn: String?,
        descriptionEs: String?,
        typeTest: Int,
        pressure: Int,
        frequency: Int,
        injectionTime: Int,
        measureInjection: Boolean?,
        measureReturn: Boolean?,
        injectionMaximum: Double?,
        injectionMinimum: Double?,
        returnMaximum: Double?,
        returnMinimum: Double?) {
        this.pirId = pirId
        this.sequence = sequence
        this.description = description
        this.descriptionEn = descriptionEn
        this.descriptionEs = descriptionEs
        this.typeTest = typeTest
        this.pressure = pressure
        this.frequency = frequency
        this.injectionTime = injectionTime
        this.measureInjection = measureInjection
        this.measureReturn = measureReturn
        this.injectionMaximum = injectionMaximum
        this.injectionMinimum = injectionMinimum
        this.returnMaximum = returnMaximum
        this.returnMinimum = returnMinimum
    }
}
