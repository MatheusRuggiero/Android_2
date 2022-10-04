package br.com.tecnomotor.marvin.model.sensor

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class SensorPlanTestReport : Serializable {
    var psrId: Int = 0
    var psrTypePlan: Int = 0
    var psrTypePlanId: Int = 0
    var psrRevision: Int = 0
    var psrPlatform: String?= ""
    var psrName: String?= ""
    var psrNameEn: String?= ""
    var psrNameEs: String?= ""
    var psrPressure1: Int = 0
    var psrPressure2: Int = 0
    var psrPressure3: Int = 0
    var psrPressure4: Int = 0
    var psrPressure5: Int = 0
    var psrLimit1Min: Double = 0.0
    var psrLimit1Max: Double = 0.0
    var psrLimit2Min: Double = 0.0
    var psrLimit2Max: Double = 0.0
    var psrLimit3Min: Double = 0.0
    var psrLimit3Max: Double = 0.0
    var psrLimit4Min: Double = 0.0
    var psrLimit4Max: Double = 0.0
    var psrLimit5Min: Double = 0.0
    var psrLimit5Max: Double = 0.0
    var psrToken: String?= ""
    var sensorReport: SensorReport = SensorReport()

    constructor()
    constructor(
        psrId: Int,
        psrTypePlan: Int,
        psrTypePlanId: Int,
        psrRevision: Int,
        psrPlatform: String?,
        psrName: String?,
        psrNameEn: String?,
        psrNameEs: String?,
        psrPressure1: Int,
        psrPressure2: Int,
        psrPressure3: Int,
        psrPressure4: Int,
        psrPressure5: Int,
        psrLimit1Min: Double,
        psrLimit1Max: Double,
        psrLimit2Min: Double,
        psrLimit2Max: Double,
        psrLimit3Min: Double,
        psrLimit3Max: Double,
        psrLimit4Min: Double,
        psrLimit4Max: Double,
        psrLimit5Min: Double,
        psrLimit5Max: Double,
        psrToken: String?,
        sensorReport: SensorReport
    ) {
        this.psrId = psrId
        this.psrTypePlan = psrTypePlan
        this.psrTypePlanId = psrTypePlanId
        this.psrRevision = psrRevision
        this.psrPlatform = psrPlatform
        this.psrName = psrName
        this.psrNameEn = psrNameEn
        this.psrNameEs = psrNameEs
        this.psrPressure1 = psrPressure1
        this.psrPressure2 = psrPressure2
        this.psrPressure3 = psrPressure3
        this.psrPressure4 = psrPressure4
        this.psrPressure5 = psrPressure5
        this.psrLimit1Min = psrLimit1Min
        this.psrLimit1Max = psrLimit1Max
        this.psrLimit2Min = psrLimit2Min
        this.psrLimit2Max = psrLimit2Max
        this.psrLimit3Min = psrLimit3Min
        this.psrLimit3Max = psrLimit3Max
        this.psrLimit4Min = psrLimit4Min
        this.psrLimit4Max = psrLimit4Max
        this.psrLimit5Min = psrLimit5Min
        this.psrLimit5Max = psrLimit5Max
        this.psrToken = psrToken
        this.sensorReport = sensorReport
    }
}
