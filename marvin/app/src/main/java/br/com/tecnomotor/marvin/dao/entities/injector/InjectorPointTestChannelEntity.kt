package br.com.tecnomotor.marvin.dao.entities.injector

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
@Entity(tableName = "cpi_canal_ponto_teste_injetor")
data class InjectorPointTestChannelEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cpi_canal_ponto_teste_injetor_id")
    var id: Int = 0,

    @ColumnInfo(name = "cpi_cti_id")
    var cpiCtiId: Int = 0,

    @ColumnInfo(name = "cpi_injector_test_channel_id")
    var injectorTestChannelEntityId: Int = 0,

    @ColumnInfo(name = "cpi_ptr_pir_id")
    var cpiPtrPirId: Int = 0,

    @ColumnInfo(name = "cpi_injetor_ponto_teste_report_id")
    var injectorPointTestReportEntityId: Int = 0,

    @ColumnInfo(name = "cpi_ptr_sequencia")
    var ptrSequence: Int = 0,

    @ColumnInfo(name = "cpi_injecao")
    var injection: Double? = 0.0,

    @ColumnInfo(name = "cpi_retorno")
    var cpiReturn: Double? = 0.0,

    @ColumnInfo(name = "cpi_status")
    var status: Int = 0,

) : Serializable
