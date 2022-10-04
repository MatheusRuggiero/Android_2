package br.com.tecnomotor.marvin.dao.entities.global

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
@Entity(tableName = "cli_clientes")
data class ClientEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cli_id")
    var id: Int = 0,

    @ColumnInfo(name = "cli_nome")
    var name: String? = "",

    @ColumnInfo(name = "cli_endereco")
    var address: String? = "",

    @ColumnInfo(name = "cli_numero")
    var number: String? = "",

    @ColumnInfo(name = "cli_bairro")
    var district: String? = "",

    @ColumnInfo(name = "cli_cep")
    var cep: String? = "",

    @ColumnInfo(name = "cli_cidade")
    var city: String? = "",

    @ColumnInfo(name = "cli_estado")
    var state: String? = "",

    @ColumnInfo(name = "cli_telefone")
    var telephone: String? = "",

    @ColumnInfo(name = "cli_celular")
    var cell: String? = "",

    @ColumnInfo(name = "cli_fax")
    var fax: String? = "",

    @ColumnInfo(name = "cli_removido")
    var removed: Boolean? = false
) : Serializable
