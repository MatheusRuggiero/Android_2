package br.com.tecnomotor.marvin.model.xId.sensor

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class XidSensorPlatform : Serializable {
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
    var objectCompositionId : String? = ""
    var objectId : String? = ""
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
    var xid = 0
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
}