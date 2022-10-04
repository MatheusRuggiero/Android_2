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
@Entity(tableName = "device_authentication")
data class DeviceAuthentication(

    @PrimaryKey
    @ColumnInfo(name = "device_id")
    var id: Int = 0,

    @ColumnInfo(name = "user_name")
    var userName: String? = "",

    @ColumnInfo(name = "password")
    var password: String? = "",

    @ColumnInfo(name = "password_backup")
    var passwordBackup: String? = "",

    @ColumnInfo(name = "serial_card")
    var serialCard: String? = "",

    @ColumnInfo(name = "code_board")
    var codeBoard: String? = "",

    @ColumnInfo(name = "device")
    var deviceId: String? = "",

    @ColumnInfo(name = "imei")
    var imei: String? = "",

    @ColumnInfo(name = "created_date_object")
    var createdDateObject: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "last_date_update")
    var lastDateUpdate: Date? = Calendar.getInstance().time,

    @ColumnInfo(name = "token_1")
    var token1: String? = "",

    @ColumnInfo(name = "token_2")
    var token2: String? = "",

    @ColumnInfo(name = "token_3")
    var token3: String? = "",
) : Serializable