package br.com.tecnomotor.marvin.api.model

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
@Entity(tableName = "product_license_device")
data class ProductLicenseDevice (

    @PrimaryKey
    @ColumnInfo(name = "product_license_id")
    var id: Int = 0,

    @ColumnInfo(name = "key")
    var key : String? = "",

    @ColumnInfo(name = "observation")
    var observation : String? = "",

    @ColumnInfo(name = "generated_new_key_in_how_many_day")
    var generatedNewKeyInHowManyDay: Int = 0,

    @ColumnInfo(name = "validation_license")
    var validationLicense: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "validation_license_year")
    var generatedNewKeyInHowManyYear: Int = 0,

    @ColumnInfo(name = "created_date_object")
    var createdDateObject: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "validation_license_date")
    var validationLicenseDate: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "last_date_update")
    var lastDateUpdate: Date? = Calendar.getInstance().time
) : Serializable