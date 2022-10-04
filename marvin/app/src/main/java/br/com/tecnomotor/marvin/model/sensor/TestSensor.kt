package br.com.tecnomotor.marvin.model.sensor

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
class TestSensor : Serializable {
    var id: Int = 0
    var orderService: OrderService = OrderService()
    var resultTest1: Double? = 0.0
    var resultTest2: Double? = 0.0
    var resultTest3: Double? = 0.0
    var resultTest4: Double? = 0.0
    var resultTest5: Double? = 0.0
    var numberSerial: String? = ""
    var note: String? = ""
    var tseDate: Date = Date()
    var tseHour: String? = ""
    var versionSoftware: String? = ""
    var versionPlatform: String? = ""
    var removed: Boolean? = false
    var sensorPlanTestReport: SensorPlanTestReport = SensorPlanTestReport()

    constructor()
    constructor(
        id: Int,
        orderService: OrderService,
        resultTest1: Double?,
        resultTest2: Double?,
        resultTest3: Double?,
        resultTest4: Double?,
        resultTest5: Double?,
        numberSerial: String?,
        note: String?,
        tseDate: Date,
        tseHour: String?,
        versionSoftware: String?,
        versionPlatform: String?,
        removed: Boolean?,
        sensorPlanTestReport: SensorPlanTestReport
    ) {
        this.id = id
        this.orderService = orderService
        this.resultTest1 = resultTest1
        this.resultTest2 = resultTest2
        this.resultTest3 = resultTest3
        this.resultTest4 = resultTest4
        this.resultTest5 = resultTest5
        this.numberSerial = numberSerial
        this.note = note
        this.tseDate = tseDate
        this.tseHour = tseHour
        this.versionSoftware = versionSoftware
        this.versionPlatform = versionPlatform
        this.removed = removed
        this.sensorPlanTestReport = sensorPlanTestReport
    }
}
