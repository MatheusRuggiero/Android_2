package br.com.tecnomotor.marvin.model.sensor

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class XidPlanTestSensor: Serializable {
    var id = 0
    var action : String? = ""
    var actionNumber = 0
    var brandId : String? = ""
    var brand : String? = ""
    var classResponsible : String? = ""
    var createdDateObject: Date? = Calendar.getInstance().time
    var identification : String? = ""
    var identificationAux : String? = ""
    var lastDateUpdate: Date? = Calendar.getInstance().time
    var objectId : String? = ""
    var objectCompositionId : String? = ""
    var variantNameTable1 : String? = ""
    var variantNameTable2 : String? = ""
    var variantNameTable3 : String? = ""
    var variantNameTable4 : String? = ""
    var variantNameTable5 : String? = ""
    var variantNameTable6 : String? = ""
    var responsibleId : String? = ""
    var responsibleName : String? = ""
    var responsibleToken : String? = ""
    var revisionId : String? = ""
    var revisionMotivation : String? = ""
    var revisionMotivationObjectId : String? = ""
    var revisionObjectMotivation : String? = ""
    var revisionObjectObservation : String? = ""
    var versionDatabase : String? = ""
    var xid : Int = 0
    var platformId : String? = ""
    var platform : String? = ""
    var genericAuxInfo1 : String? = ""
    var genericAuxInfo2 : String? = ""
    var genericAuxInfo3 : String? = ""
    var genericAuxInfo4 : String? = ""
    var genericAuxIdentification1 : String? = ""
    var genericAuxIdentification2 : String? = ""
    var genericAuxIdentification3 : String? = ""
    var genericAuxIdentification4 : String? = ""
    var forAnythingExtra1 : String? = ""
    var forAnythingExtra2 : String? = ""
    var forAnythingExtra3 : String? = ""
    var forAnythingExtra4 : String? = ""
    var backupDatabase = false
    var betaDatabaseReleased = false
    var developmentDatabaseReleased = false
    var experimentalDatabaseReleased = false
    var officialDatabaseReleased = false
    var other1DatabaseReleased = false
    var other2DatabaseReleased = false

    constructor()
    constructor(
        id: Int,
        action: String?,
        actionNumber: Int,
        brandId: String?,
        brand: String?,
        classResponsible: String?,
        createdDateObject: Date?,
        identification: String?,
        identificationAux: String?,
        lastDateUpdate: Date?,
        objectId: String?,
        objectCompositionId: String?,
        variantNameTable1: String?,
        variantNameTable2: String?,
        variantNameTable3: String?,
        variantNameTable4: String?,
        variantNameTable5: String?,
        variantNameTable6: String?,
        responsibleId: String?,
        responsibleName: String?,
        responsibleToken: String?,
        revisionId: String?,
        revisionMotivation: String?,
        revisionMotivationObjectId: String?,
        revisionObjectMotivation: String?,
        revisionObjectObservation: String?,
        versionDatabase: String?,
        xid: Int,
        platformId: String?,
        platform: String?,
        genericAuxInfo1: String?,
        genericAuxInfo2: String?,
        genericAuxInfo3: String?,
        genericAuxInfo4: String?,
        genericAuxIdentification1: String?,
        genericAuxIdentification2: String?,
        genericAuxIdentification3: String?,
        genericAuxIdentification4: String?,
        forAnythingExtra1: String?,
        forAnythingExtra2: String?,
        forAnythingExtra3: String?,
        forAnythingExtra4: String?,
        backupDatabase: Boolean,
        betaDatabaseReleased: Boolean,
        developmentDatabaseReleased: Boolean,
        experimentalDatabaseReleased: Boolean,
        officialDatabaseReleased: Boolean,
        other1DatabaseReleased: Boolean,
        other2DatabaseReleased: Boolean) {
        this.id = id
        this.action = action
        this.actionNumber = actionNumber
        this.brandId = brandId
        this.brand = brand
        this.classResponsible = classResponsible
        this.createdDateObject = createdDateObject
        this.identification = identification
        this.identificationAux = identificationAux
        this.lastDateUpdate = lastDateUpdate
        this.objectId = objectId
        this.objectCompositionId = objectCompositionId
        this.variantNameTable1 = variantNameTable1
        this.variantNameTable2 = variantNameTable2
        this.variantNameTable3 = variantNameTable3
        this.variantNameTable4 = variantNameTable4
        this.variantNameTable5 = variantNameTable5
        this.variantNameTable6 = variantNameTable6
        this.responsibleId = responsibleId
        this.responsibleName = responsibleName
        this.responsibleToken = responsibleToken
        this.revisionId = revisionId
        this.revisionMotivation = revisionMotivation
        this.revisionMotivationObjectId = revisionMotivationObjectId
        this.revisionObjectMotivation = revisionObjectMotivation
        this.revisionObjectObservation = revisionObjectObservation
        this.versionDatabase = versionDatabase
        this.xid = xid
        this.platformId = platformId
        this.platform = platform
        this.genericAuxInfo1 = genericAuxInfo1
        this.genericAuxInfo2 = genericAuxInfo2
        this.genericAuxInfo3 = genericAuxInfo3
        this.genericAuxInfo4 = genericAuxInfo4
        this.genericAuxIdentification1 = genericAuxIdentification1
        this.genericAuxIdentification2 = genericAuxIdentification2
        this.genericAuxIdentification3 = genericAuxIdentification3
        this.genericAuxIdentification4 = genericAuxIdentification4
        this.forAnythingExtra1 = forAnythingExtra1
        this.forAnythingExtra2 = forAnythingExtra2
        this.forAnythingExtra3 = forAnythingExtra3
        this.forAnythingExtra4 = forAnythingExtra4
        this.backupDatabase = backupDatabase
        this.betaDatabaseReleased = betaDatabaseReleased
        this.developmentDatabaseReleased = developmentDatabaseReleased
        this.experimentalDatabaseReleased = experimentalDatabaseReleased
        this.officialDatabaseReleased = officialDatabaseReleased
        this.other1DatabaseReleased = other1DatabaseReleased
        this.other2DatabaseReleased = other2DatabaseReleased
    }
}