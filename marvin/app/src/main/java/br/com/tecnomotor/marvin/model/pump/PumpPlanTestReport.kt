package br.com.tecnomotor.marvin.model.pump

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class PumpPlanTestReport : Serializable {
    var id: Int = 0
    var typePlan: Int = 0
    var typePlanId: Int = 0
    var revision: Int = 0
    var platform: String?= ""
    var name: String?= ""
    var nameEn: String?= ""
    var nameEs: String?= ""
    var pressure: Int = 0
    var rotation: Int = 0
    var minimumFlow: Int = 0
    var rlbToken: String?= ""
    var pumpReport: PumpReport = PumpReport()

    constructor(
        id: Int,
        typePlan: Int,
        typePlanId: Int,
        revision: Int,
        platform: String?,
        name: String?,
        nameEn: String?,
        nameEs: String?,
        pressure: Int,
        rotation: Int,
        minimumFlow: Int,
        rlbToken: String?,
        pumpReport: PumpReport
    ) {
        this.id = id
        this.typePlan = typePlan
        this.typePlanId = typePlanId
        this.revision = revision
        this.platform = platform
        this.name = name
        this.nameEn = nameEn
        this.nameEs = nameEs
        this.pressure = pressure
        this.rotation = rotation
        this.minimumFlow = minimumFlow
        this.rlbToken = rlbToken
        this.pumpReport = pumpReport
    }

    constructor()
}
