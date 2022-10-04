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
    tableName = "cti_canais_testes_injetores",
    foreignKeys = [
        ForeignKey(
            entity = InjectorEntity::class,
            parentColumns = arrayOf("tin_id"),
            childColumns = arrayOf("cti_tin_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["cti_id", "cti_tin_id"])]
)
data class InjectorTestChannelEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cti_id")
    var id: Int = 0,

    @ColumnInfo(name = "cti_tin_id", index = true)
    var testInjectorId: Int = 0,

    @ColumnInfo(name = "cti_posicao")
    var position: Int = 0,

    @ColumnInfo(name = "cti_numero_serie")
    var serialNumber: String? = "",

    @ColumnInfo(name = "cti_observacao")
    var note: String? = "",

    @ColumnInfo(name = "cti_teste_eletrico")
    var testElectrical: String? = "",

    @ColumnInfo(name = "cti_teste_estanqueidade")
    var testTightness: String? = "",

    @ColumnInfo(name = "cti_resistencia")
    var resistance: Double? = 0.0,

    @ColumnInfo(name = "cti_resistenciab")
    var resistanceB: Double? = 0.0,

    @ColumnInfo(name = "cti_ima")
    var ima: String? = "",

    @ColumnInfo(name = "cti_ima_antigo")
    var imaOld: String? = ""

) : Serializable
