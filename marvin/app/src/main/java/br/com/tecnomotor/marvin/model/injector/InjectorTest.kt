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
class InjectorTest : Serializable {
    var id: Int = 0
    var orderServiceId: Int = 0
    var data: Date = Date()
    var hour: String? = ""
    var softwareVersion: String? = ""
    var platformVersion: String? = ""
    var removed: Boolean? = false
    var codingTest: Boolean? = false
    var injectorTestPlanReportId: Int = 0

    constructor()
    constructor(
        id: Int,
        orderServiceId: Int,
        data: Date,
        hour: String?,
        softwareVersion: String?,
        platformVersion: String?,
        removed: Boolean?,
        codingTest: Boolean?,
        injectorTestPlanReportId: Int) {
        this.id = id
        this.orderServiceId = orderServiceId
        this.data = data
        this.hour = hour
        this.softwareVersion = softwareVersion
        this.platformVersion = platformVersion
        this.removed = removed
        this.codingTest = codingTest
        this.injectorTestPlanReportId = injectorTestPlanReportId
    }
}
