package br.com.tecnomotor.marvin.dao.entities.sensor


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(tableName = "spp_sensores_plataformas_planos_testes")
data class SensorPlatformPlanTestEntity(
    @PrimaryKey
    @ColumnInfo(name = "spp_spl_sen_id")
    var splSenId: Int = 0,

    @ColumnInfo(name = "spp_pls_id")
    var sensorTestPlanEntityId: Int = 0,

    @ColumnInfo(name = "spp_spl_plat_id")
    var sensorPlatformEntityId: Int = 0,

    @ColumnInfo(name = "spp_spl_plat_nome")
    var sensorPlatformEntityDescription: String = "",

    @ColumnInfo(name = "spp_ver_id")
    var versionEntityId: Int = 0

) : Serializable
