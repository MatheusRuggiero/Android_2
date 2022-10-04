package br.com.tecnomotor.marvin.model.injector

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class PointTestInjectorChannel : Serializable {
    var injectorTestChannel: InjectorTestChannel = InjectorTestChannel()
    var pointTestInjectorReport: PointTestInjectorReport = PointTestInjectorReport()

    var ptrSequence: Int = 0
    var injection: Double? = 0.0
    var cpiReturn: Double? = 0.0
    var status: Int = 0

    var cpiCtiId: Int = 0
    var cpiPtrPirId: Int = 0

    constructor()
    constructor(
        injectorTestChannel: InjectorTestChannel,
        pointTestInjectorReport: PointTestInjectorReport,
        ptrSequence: Int,
        injection: Double?,
        cpiReturn: Double?,
        status: Int
    ) {
        this.injectorTestChannel = injectorTestChannel
        this.pointTestInjectorReport = pointTestInjectorReport
        this.ptrSequence = ptrSequence
        this.injection = injection
        this.cpiReturn = cpiReturn
        this.status = status
    }

    constructor(
        injectorTestChannel: InjectorTestChannel,
        pointTestInjectorReport: PointTestInjectorReport,
        ptrSequence: Int,
        injection: Double?,
        cpiReturn: Double?,
        status: Int,
        cpiCtiId: Int,
        cpiPtrPirId: Int
    ) {
        this.injectorTestChannel = injectorTestChannel
        this.pointTestInjectorReport = pointTestInjectorReport
        this.ptrSequence = ptrSequence
        this.injection = injection
        this.cpiReturn = cpiReturn
        this.status = status
        this.cpiCtiId = cpiCtiId
        this.cpiPtrPirId = cpiPtrPirId
    }


}
