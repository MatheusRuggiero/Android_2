package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "pir_planos_testes_injetores_relatorio",
    foreignKeys = [
        ForeignKey(
            entity = InjectorReportEntity::class,
            parentColumns = arrayOf("rli_injetores_relatorio"),
            childColumns = arrayOf("pir_rli_inj_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["pir_id", "pir_rli_inj_id"])]
)
data class InjectorTestPlanReportEntity(

    @PrimaryKey
    @ColumnInfo(name = "pir_id")
    var id: Int = 0,

    @ColumnInfo(name = "pir_tipo_plano")
    var typePlan: Int = 0,

    @ColumnInfo(name = "pir_tipo_plano_id")
    var typePlanId: Int = 0,

    @ColumnInfo(name = "pir_revisao")
    var revision: Int = 0,

    @ColumnInfo(name = "pir_plataforma")
    var platform: String? = "",

    @ColumnInfo(name = "pir_nome")
    var name: String? = "",

    @ColumnInfo(name = "pir_nome_en")
    var nameEn: String? = "",

    @ColumnInfo(name = "pir_nome_es")
    var nameEs: String? = "",

    @ColumnInfo(name = "pir_token")
    var token: String? = "",

    @ColumnInfo(name = "pir_rli_inj_id", index = true)
    var injectorReportId: Int = 0

) : Serializable
