package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.RevisionPumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface RevisionPumpDao : GenericDao<RevisionPumpEntity> {
    @Transaction
    @Query("SELECT * FROM rbo_revisoes_bombas WHERE rbo_rev_id = :revId")
    fun findById(revId: Int): RevisionPumpEntity?

    @Transaction
    @Query("SELECT * FROM rbo_revisoes_bombas")
    fun findAll(): MutableList<RevisionPumpEntity>?

    @Transaction
    @Query("SELECT * FROM rbo_revisoes_bombas WHERE rbo_rev_id = :revId")
    fun findByRevision(revId: Int): MutableList<RevisionPumpEntity>?

    @Transaction
    @Query("SELECT * FROM rbo_revisoes_bombas WHERE rbo_bom_id = :idBom  AND rbo_rev_id = :idRev")
    fun findByRevisionPump(idBom: Int, idRev: Int): MutableList<RevisionPumpEntity>?

    @Transaction
    @Query("SELECT * FROM rbo_revisoes_bombas WHERE rbo_rev_id = :id")
    fun findByUsingPumpRevId(id: Int): LiveData<List<RevisionPumpEntity>?>
}