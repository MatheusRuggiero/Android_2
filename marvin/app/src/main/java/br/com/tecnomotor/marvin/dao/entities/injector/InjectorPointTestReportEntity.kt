package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(tableName = "ptr_pontos_testes_injetores_relatorio",
    indices = [Index(value = ["ptr_pir_id"])]
)
data class InjectorPointTestReportEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "inj_report_id")
    var id: Int = 0,

    @ColumnInfo(name = "ptr_pir_id")
    var pirId: Int = 0,

    @ColumnInfo(name = "ptr_sequencia")
    var sequence: Int = 0,

    @ColumnInfo(name = "ptr_descricao")
    var description: String? = "",

    @ColumnInfo(name = "ptr_descricao_en")
    var descriptionEn: String? = "",

    @ColumnInfo(name = "ptr_descricao_es")
    var descriptionEs: String? = "",

    @ColumnInfo(name = "ptr_tipo_teste")
    var typeTest: Int = 0,

    @ColumnInfo(name = "ptr_pressao")
    var pressure: Int = 0,

    @ColumnInfo(name = "ptr_frequencia")
    var frequency: Int = 0,

    @ColumnInfo(name = "ptr_tempo_injecao")
    var injectionTime: Int = 0,

    @ColumnInfo(name = "ptr_medir_injecao")
    var measureInjection: Boolean? = false,

    @ColumnInfo(name = "ptr_medir_retorno")
    var measureReturn: Boolean? = false,

    @ColumnInfo(name = "ptr_injecao_maxima")
    var injectionMaximum: Double? = 0.0,

    @ColumnInfo(name = "ptr_injecao_minima")
    var injectionMinimum: Double? = 0.0,

    @ColumnInfo(name = "ptr_retorno_maximo")
    var returnMaximum: Double? = 0.0,

    @ColumnInfo(name = "ptr_retorno_minimo")
    var returnMinimum: Double? = 0.0

)  : Serializable
