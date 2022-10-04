package br.com.tecnomotor.marvin.dao.entities.pump

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
@Entity(tableName = "pbr_planos_testes_bombas_relatorio")
data class PumpPlanTestReportEntity  (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pbr_id")
    var id: Int = 0,

    @ColumnInfo(name = "pbr_tipo_plano")
    var typePlan: Int = 0,

    @ColumnInfo(name = "pbr_tipo_plano_id")
    var typePlanId: Int = 0,

    @ColumnInfo(name = "pbr_revisao")
    var revision: Int = 0,

    @ColumnInfo(name = "pbr_plataforma")
    var platform: String?= "",

    @ColumnInfo(name = "pbr_nome")
    var name: String?= "",

    @ColumnInfo(name = "pbr_nome_en")
    var nameEn: String?= "",

    @ColumnInfo(name = "pbr_nome_es")
    var nameEs: String?= "",

    @ColumnInfo(name = "pbr_pressao")
    var pressure: Int = 0,

    @ColumnInfo(name = "pbr_rotacao")
    var rotation: Int = 0,

    @ColumnInfo(name = "pbr_vazao_minima")
    var minimumFlow: Int = 0,

    @ColumnInfo(name = "pbr_rlb_token")
    var rlbToken: String?= "",

    @ColumnInfo(name = "pbr_rlb_id")
    var pumpReportEntityId : Int = 0

) : Serializable
