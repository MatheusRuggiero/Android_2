package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.*
import br.com.tecnomotor.marvin.dao.entities.global.OrderServiceEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "tin_testes_injetores",
    foreignKeys = [
        ForeignKey(
            entity = OrderServiceEntity::class,
            parentColumns = arrayOf("ord_id"),
            childColumns = arrayOf("tin_ord_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = InjectorTestPlanReportEntity::class,
            parentColumns = arrayOf("pir_id"),
            childColumns = arrayOf("tin_pir_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["tin_id", "tin_ord_id", "tin_pir_id"])]
)
data class InjectorTestEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tin_id")
    var id: Int = 0,

    @ColumnInfo(name = "tin_ord_id", index = true)
    var orderServiceId: Int = 0,

    @ColumnInfo(name = "tin_data")
    var data: Date = Date(),

    @ColumnInfo(name = "tin_hora")
    var hour: String? = "",

    @ColumnInfo(name = "tin_versao_software")
    var softwareVersion: String? = "",

    @ColumnInfo(name = "tin_versao_plataforma")
    var platformVersion: String? = "",

    @ColumnInfo(name = "tin_removido")
    var removed: Boolean? = false,

    @ColumnInfo(name = "tin_teste_codificacao")
    var codingTest: Boolean? = false,

    @ColumnInfo(name = "tin_pir_id", index = true)
    var injectorTestPlanReportId: Int = 0,

) : Serializable
