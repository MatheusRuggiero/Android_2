package br.com.tecnomotor.marvin.dao.entities.pump

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
    tableName = "bom_bombas",
    foreignKeys = [ForeignKey(
        entity = BrandEntity::class,
        parentColumns = arrayOf("mar_id"),
        childColumns = arrayOf("bom_marca_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["bom_marca_id"])]
)
data class PumpEntity(

    @PrimaryKey
    @ColumnInfo(name = "bom_id")
    var id: Int = 0,

    @ColumnInfo(name = "bom_codigo")
    var code: String? = "",

    @ColumnInfo(name = "bom_padrao")
    var standard: Boolean? = false,

    @ColumnInfo(name = "bom_marca_id")
    var brandId: Int = 0,

    @ColumnInfo(name = "bom_aplicacao")
    var application: String? = "",

    @ColumnInfo(name = "bom_token")
    var token: String? = "",

    @ColumnInfo(name = "bom_revisao")
    var revisionId: Int = 0,

    @ColumnInfo(name = "bom_tipo")
    var type: Int = 0,

    @ColumnInfo(name = "bom_deleted")
    var deleted: Boolean? = false,

    @ColumnInfo(name = "bom_descricao")
    var descriptionType: String? = "",

    @ColumnInfo(name = "bom_nome_atuador")
    var nameActuator: String? = "",

    @ColumnInfo(name = "bom_referencia_atuador")
    var referenceActuator: Int = 0,

    @ColumnInfo(name = "bom_tensao_atuador")
    var voltageActuator: Int = 0,

    @ColumnInfo(name = "bom_sentido_giro")
    var directionRotating: Boolean? = false,

    @ColumnInfo(name = "bom_adaptador_eletrico")
    var adapterElectric: String? = "",

    @ColumnInfo(name = "bom_adaptador_hidraulico_pressao")
    var hydraulicPressureAdapter: String? = "",

    @ColumnInfo(name = "bom_adaptador_hidraulico_alimentacao")
    var hydraulicPowerAdapter: String? = "",

    @ColumnInfo(name = "bom_adaptador_hidraulico_retorno")
    var hydraulicReturnAdapter: String? = "",

    @ColumnInfo(name = "bom_adaptador_mecanico")
    var mechanicalAdapter: String? = "",

    ) : Serializable