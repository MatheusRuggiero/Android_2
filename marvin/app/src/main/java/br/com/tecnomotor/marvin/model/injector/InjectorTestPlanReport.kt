package br.com.tecnomotor.marvin.model.injector

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class InjectorTestPlanReport : Serializable {
    var id: Int = 0
    var typePlan: Int = 0
    var typePlanId: Int = 0
    var revision: Int = 0
    var platform: String?= ""
    var name: String?= ""
    var nameEn: String?= ""
    var nameEs: String?= ""
    var token: String?= ""
    var injectorReportId: Int = 0

    constructor(
        id: Int,
        typePlan: Int,
        typePlanId: Int,
        revision: Int,
        platform: String?,
        name: String?,
        nameEn: String?,
        nameEs: String?,
        token: String?,
        injectorReportId: Int) {
        this.id = id
        this.typePlan = typePlan
        this.typePlanId = typePlanId
        this.revision = revision
        this.platform = platform
        this.name = name
        this.nameEn = nameEn
        this.nameEs = nameEs
        this.token = token
        this.injectorReportId = injectorReportId
    }

    constructor()
}
