package br.com.tecnomotor.marvin.dao.entities.injector

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "rin_revisoes_injetores",
    primaryKeys = ["rin_rev_id", "rin_inj_id"],
    foreignKeys = [
        ForeignKey(
            entity = InjectorEntity::class,
            parentColumns = arrayOf("inj_id"),
            childColumns = arrayOf("rin_inj_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RevisionEntity::class,
            parentColumns = arrayOf("rev_id"),
            childColumns = arrayOf("rin_rev_id"),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [androidx.room.Index(value = ["rin_rev_id", "rin_inj_id"])]
)
data class RevisionInjectorEntity(
    @ColumnInfo(name = "rin_rev_id")
    var id_rev: Int = 1,

    @ColumnInfo(name = "rin_inj_id")
    var id_inj: Int = 0,

    @ColumnInfo(name = "rin_data_hora")
    var dataHoraStr: String? = "",

    @ColumnInfo(name = "rin_motivo")
    var reason: String? = "",

    @ColumnInfo(name = "rin_observacao")
    var observation: String? = "",

    @ColumnInfo(name = "rin_usuario")
    var user: String? = "",

    ) : Serializable, Comparable<RevisionInjectorEntity> {
    override fun compareTo(o: RevisionInjectorEntity): Int {
        return if (this.id_rev > o.id_rev) {
            1
        } else {
            -1
        }
    }
}