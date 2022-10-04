package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.*
import br.com.tecnomotor.marvin.dao.entities.global.BrandEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "inj_injetores",
    foreignKeys = [ForeignKey(
        entity = BrandEntity::class,
        parentColumns = arrayOf("mar_id"),
        childColumns = arrayOf("inj_marca_id"),
        onDelete = ForeignKey.CASCADE
    )
    ],
    indices = [Index(value = ["inj_id", "inj_marca_id"])]
)
data class InjectorEntity(

    @PrimaryKey
    @ColumnInfo(name = "inj_id")
    var id: Int = 0,

    @ColumnInfo(name = "inj_codigo")
    var code: String? = "",

    @ColumnInfo(name = "inj_padrao")
    var standard: Boolean? = false,

    // TODO: utilizar @Embedded para carrega o objeto inteiro e não parte dele: melhoria para não precisar fazer duas consultas
    @ColumnInfo(name = "inj_marca_id", index = true)
    var brandId: Int = 0,

    @ColumnInfo(name = "inj_aplicacao")
    var application: String? = "",

    @ColumnInfo(name = "inj_tipo")
    var tipo: Int = 0,

    @ColumnInfo(name = "inj_adapt_pressao")
    var adaptPressure: String? = "",

    @ColumnInfo(name = "inj_adapt_retorno")
    var adaptReturn: String? = "",

    @ColumnInfo(name = "inj_adapt_conector")
    var adaptConnector: String? = "",

    @ColumnInfo(name = "inj_resistencia_minima")
    var resistanceMinimum: Double? = 0.0,

    @ColumnInfo(name = "inj_resistencia_maxima")
    var resistanceMaximum: Double? = 0.0,

    @ColumnInfo(name = "inj_resistenciab_minima")
    var resistanceMinimumB: Double? = 0.0,

    @ColumnInfo(name = "inj_resistenciab_maxima")
    var resistanceMaximumB: Double? = 0.0,

    @ColumnInfo(name = "inj_token")
    var token: String? = "",

    @ColumnInfo(name = "inj_revisao", index = true)
    var revision: Int = 0,

    @ColumnInfo(name = "inj_deleted")
    var deleted: Boolean? = false,

    ) : Serializable