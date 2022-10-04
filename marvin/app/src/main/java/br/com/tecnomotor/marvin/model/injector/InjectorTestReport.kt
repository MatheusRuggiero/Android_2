package br.com.tecnomotor.marvin.model.injector

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class InjectorTestReport : Serializable {
    /**
     * Information report
     * @reportElapsedTime = This field would be to add the time it takes to run the test from the beginning.
     */
    var reportDateExecuteTest: Date? = Calendar.getInstance().time
    var reportElapsedTimeTest: Date? = Calendar.getInstance().time
    var reportId: Int = 0
    var reportNumberTest: Long = 0

    /**
     * Information injector
     */
    var injAdaptConnector: String? = ""
    var injAdaptReturn: String? = ""
    var injApplication: String? = ""
    var injBrandId: Int? = 0
    var injBrandName: String? = ""
    var injCode: String? = ""
    var injId: Int? = 0
    var injResistanceMaximum: Double? = 0.0
    var injResistanceBMaximum: Double? = 0.0
    var injResistanceMinimum: Double? = 0.0
    var injResistanceBMinimum: Double? = 0.0
    var injRevision: Int? = 0
    var injStandard: Boolean? = false
    var injToken: String? = ""

    /**
     * Information test electrical
     */
    var injConditionTestElectrical: String? = ""
    var injStatusTestElectrical: String? = ""

    /**
     * Information test tightness
     */
    var injConditionTestTightness: String? = ""
    var injStatusTestTightness: String? = ""

    /**
     * Information point test
     */
    var injPointDescription: String? = ""
    var injPointId: String? = ""
    var injPointInjectionMaximum: String? = ""
    var injPointInjectionMinimum: String? = ""
    var injPointInjection2Maximum: String? = ""
    var injPointInjection2Minimum: String? = ""
    var injPointInjectionReturnMaximum: String? = ""
    var injPointInjectionReturnMinimum: String? = ""
    var injPointInjectionReturn2Maximum: String? = ""
    var injPointInjectionReturn2Minimum: String? = ""
    var injPointOrigin: String? = ""

    var injPointSequence: Int = 0
    var injPointDescriptionEn: String? = ""
    var injPointDescriptionEs: String? = ""
    var injPointTypeTest: Int = 0
    var injPointPressure: Int = 0
    var injPointFrequency: Int = 0
    var injPointInjectionTime: Int = 0
    var injPointMeasureInjection: Boolean? = false
    var injPointMeasureReturn: Boolean? = false

}