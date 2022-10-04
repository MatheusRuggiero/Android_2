package br.com.tecnomotor.marvin.model.pump

import br.com.tecnomotor.marvin.model.global.OrderService
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class PumpTest : Serializable {
    var id: Int = 0
    var orderService: OrderService = OrderService()
    var flowRate: Int = 0
    var numberSerial: String? = ""
    var note: String? = ""
    var tboDate: Date = Date()
    var tboHour: String? = ""
    var versionSoftware: String? = ""
    var versionPlatform: String? = ""
    var removed: Boolean? = false
    var pumpPlanTestReport: PumpPlanTestReport = PumpPlanTestReport()

    constructor()
    constructor(
        id: Int,
        orderService: OrderService,
        flowRate: Int,
        numberSerial: String?,
        note: String?,
        tboDate: Date,
        tboHour: String?,
        versionSoftware: String?,
        versionPlatform: String?,
        removed: Boolean?,
        pumpPlanTestReport: PumpPlanTestReport
    ) {
        this.id = id
        this.orderService = orderService
        this.flowRate = flowRate
        this.numberSerial = numberSerial
        this.note = note
        this.tboDate = tboDate
        this.tboHour = tboHour
        this.versionSoftware = versionSoftware
        this.versionPlatform = versionPlatform
        this.removed = removed
        this.pumpPlanTestReport = pumpPlanTestReport
    }
}
