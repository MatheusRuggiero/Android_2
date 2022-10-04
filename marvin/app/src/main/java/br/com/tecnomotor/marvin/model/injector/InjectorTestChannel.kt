package br.com.tecnomotor.marvin.model.injector

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class InjectorTestChannel : Serializable {
    var id: Int = 0
    var testInjectorId: Int = 0
    var position: Int = 0
    var serialNumber: String?= ""
    var note: String?= ""
    var testElectrical: String?= ""
    var testTightness: String?= ""
    var resistance: Double? = 0.0
    var resistanceB: Double? = 0.0
    var ima: String?= ""
    var imaOld: String?= ""

}
