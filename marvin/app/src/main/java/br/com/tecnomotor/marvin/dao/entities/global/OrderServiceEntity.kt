package br.com.tecnomotor.marvin.dao.entities.global

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
@Entity(tableName = "ord_ordem_servicos")
class OrderServiceEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ord_id")
    var id: Int = 0,

    @ColumnInfo(name = "ord_codigo")
    var code: String? = "",

    @ColumnInfo(name = "ord_placa")
    var plate: String? = "",

    @ColumnInfo(name = "ord_data_criacao")
    var dateCreation: Date = Date(),

    @ColumnInfo(name = "ord_hora_criacao")
    var hourCreation: String? = "",

    @ColumnInfo(name = "ord_removido")
    var removed: Boolean? = false,

    @ColumnInfo(name = "ord_cli_id")
    var clientEntityId: Int = 0

) : Serializable

