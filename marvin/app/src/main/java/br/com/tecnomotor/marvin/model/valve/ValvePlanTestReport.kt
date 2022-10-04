package br.com.tecnomotor.marvin.model.valve

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class ValvePlanTestReport : Serializable {
    var id: Int = 0
    var typePlan: Int = 0
    var typePlanId: Int = 0
    var revision: Int = 0
    var platform: String?= ""
    var name: String?= ""
    var nameEn: String?= ""
    var nameEs: String?= ""
    var duty1: Int = 0
    var duty2: Int = 0
    var duty3: Int = 0
    var duty4: Int = 0
    var duty5: Int = 0
    var limit1Min: Double = 0.0
    var limit1Max: Double = 0.0
    var limit2Min: Double = 0.0
    var limit2Max: Double = 0.0
    var limit3Min: Double = 0.0
    var limit3Max: Double = 0.0
    var limit4Min: Double = 0.0
    var limit4Max: Double = 0.0
    var limit5Min: Double = 0.0
    var limit5Max: Double = 0.0
    var token: String?= ""
    var valveReport: ValveReport = ValveReport()

    constructor()
    constructor(
        id: Int,
        typePlan: Int,
        typePlanId: Int,
        revision: Int,
        platform: String?,
        name: String?,
        nameEn: String?,
        nameEs: String?,
        duty1: Int,
        duty2: Int,
        duty3: Int,
        duty4: Int,
        duty5: Int,
        limit1Min: Double,
        limit1Max: Double,
        limit2Min: Double,
        limit2Max: Double,
        limit3Min: Double,
        limit3Max: Double,
        limit4Min: Double,
        limit4Max: Double,
        limit5Min: Double,
        limit5Max: Double,
        token: String?,
        valveReport: ValveReport
    ) {
        this.id = id
        this.typePlan = typePlan
        this.typePlanId = typePlanId
        this.revision = revision
        this.platform = platform
        this.name = name
        this.nameEn = nameEn
        this.nameEs = nameEs
        this.duty1 = duty1
        this.duty2 = duty2
        this.duty3 = duty3
        this.duty4 = duty4
        this.duty5 = duty5
        this.limit1Min = limit1Min
        this.limit1Max = limit1Max
        this.limit2Min = limit2Min
        this.limit2Max = limit2Max
        this.limit3Min = limit3Min
        this.limit3Max = limit3Max
        this.limit4Min = limit4Min
        this.limit4Max = limit4Max
        this.limit5Min = limit5Min
        this.limit5Max = limit5Max
        this.token = token
        this.valveReport = valveReport
    }
}
