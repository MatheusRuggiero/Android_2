package br.com.tecnomotor.marvin.dao.entities.configuration

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
@Entity(
    tableName = "ppr_permissao_parametro_remoto",
    indices = [Index("ppr_id_auto_generated")]
)
data class PermissionParameterRemoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ppr_id_auto_generated")
    var id: Int = 0,
    @ColumnInfo(name = "ppr_par_id")
    var parameterRemoteEntityId: Int = 0,
    @ColumnInfo(name = "ppr_par_nome")
    var name: String? = "",
    @ColumnInfo(name = "ppr_grupo")
    var group: Int = 3,
    @ColumnInfo(name = "ppr_tipo_operacao")
    var typeOperation: Int = 0
) : Serializable