package br.com.tecnomotor.marvin.dao.entities.pump

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(tableName = "xid_plan_test_pump")
data class XidPlanTestPumpEntity(

    @PrimaryKey
    @ColumnInfo(name = "id_xid")
    var id: Int = 0,

    @ColumnInfo(name = "action")
    var action: String? = "",

    @ColumnInfo(name = "action_number")
    var actionNumber: Int = 0,

    @ColumnInfo(name = "brand_id")
    var brandId: String? = "",

    @ColumnInfo(name = "brand")
    var brand: String? = "",

    @ColumnInfo(name = "class_responsible")
    var classResponsible: String? = "",

    @ColumnInfo(name = "created_date_object")
    var createdDateObject: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "identification")
    var identification: String? = "",

    @ColumnInfo(name = "identification_aux")
    var identificationAux: String? = "",

    @ColumnInfo(name = "last_date_update")
    var lastDateUpdate: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "object_id")
    var objectId: String? = "",

    @ColumnInfo(name = "object_composition_id")
    var objectCompositionId: String? = "",

    @ColumnInfo(name = "variant_name_table_1")
    var variantNameTable1: String? = "",

    @ColumnInfo(name = "variant_name_table_2")
    var variantNameTable2: String? = "",

    @ColumnInfo(name = "variant_name_table_3")
    var variantNameTable3: String? = "",

    @ColumnInfo(name = "variant_name_table_4")
    var variantNameTable4: String? = "",

    @ColumnInfo(name = "variant_name_table_5")
    var variantNameTable5: String? = "",

    @ColumnInfo(name = "variant_name_table_6")
    var variantNameTable6: String? = "",

    @ColumnInfo(name = "responsible_id")
    var responsibleId: String? = "",

    @ColumnInfo(name = "responsible_name")
    var responsibleName: String? = "",

    @ColumnInfo(name = "responsible_token")
    var responsibleToken: String? = "",

    @ColumnInfo(name = "revision_id")
    var revisionId: String? = "",

    @ColumnInfo(name = "revision_motivation")
    var revisionMotivation: String? = "",

    @ColumnInfo(name = "revision_object_id")
    var revisionMotivationObjectId: String? = "",

    @ColumnInfo(name = "revision_object_motivation")
    var revisionObjectMotivation: String? = "",

    @ColumnInfo(name = "revision_observation")
    var revisionObjectObservation: String? = "",

    @ColumnInfo(name = "version_database")
    var versionDatabase: String? = "",

    @ColumnInfo(name = "xid")
    var xid: Int = 0,

    @ColumnInfo(name = "platform_id")
    var platformId: String? = "",

    @ColumnInfo(name = "platform")
    var platform: String? = "",

    @ColumnInfo(name = "generic_aux_info_1")
    var genericAuxInfo1: String? = "",

    @ColumnInfo(name = "generic_aux_info_2")
    var genericAuxInfo2: String? = "",

    @ColumnInfo(name = "generic_aux_info_3")
    var genericAuxInfo3: String? = "",

    @ColumnInfo(name = "generic_aux_info_4")
    var genericAuxInfo4: String? = "",

    @ColumnInfo(name = "generic_aux_identification_1")
    var genericAuxIdentification1: String? = "",

    @ColumnInfo(name = "generic_aux_identification_2")
    var genericAuxIdentification2: String? = "",

    @ColumnInfo(name = "generic_aux_identification_3")
    var genericAuxIdentification3: String? = "",

    @ColumnInfo(name = "generic_aux_identification_4")
    var genericAuxIdentification4: String? = "",

    @ColumnInfo(name = "for_anything_extra_1")
    var forAnythingExtra1: String? = "",

    @ColumnInfo(name = "for_anything_extra_2")
    var forAnythingExtra2: String? = "",

    @ColumnInfo(name = "for_anything_extra_3")
    var forAnythingExtra3: String? = "",

    @ColumnInfo(name = "for_anything_extra_4")
    var forAnythingExtra4: String? = "",

    @ColumnInfo(name = "backup_database")
    var backupDatabase: Boolean = false,

    @ColumnInfo(name = "beta_database_released")
    var betaDatabaseReleased: Boolean = false,

    @ColumnInfo(name = "development_database_released")
    var developmentDatabaseReleased: Boolean = false,

    @ColumnInfo(name = "experimental_database_released")
    var experimentalDatabaseReleased: Boolean = false,

    @ColumnInfo(name = "official_database_released")
    var officialDatabaseReleased: Boolean = false,

    @ColumnInfo(name = "other_1_database_released")
    var other1DatabaseReleased: Boolean = false,

    @ColumnInfo(name = "other_2_database_released")
    var other2DatabaseReleased: Boolean = false,

    ) : Serializable