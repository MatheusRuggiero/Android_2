package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.RevisionInjectorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface RevisionInjectorDao : GenericDao<RevisionInjectorEntity> {
//    @Transaction
//    @Query("SELECT * FROM rin_revisoes_injetores WHERE rev_id = :id")
//    fun findById(id: Int): RevisionInjectorEntity?

    @Transaction
    @Query("SELECT * FROM rin_revisoes_injetores")
    fun findAll(): List<RevisionInjectorEntity>?

    @Transaction
    @Query("SELECT * FROM rin_revisoes_injetores WHERE rin_inj_id = :idInj AND rin_rev_id = :idRev")
    fun findByRevisionInjector(idInj: Int, idRev: Int): List<RevisionInjectorEntity>?

    @Transaction
    @Query("SELECT * FROM rin_revisoes_injetores WHERE rin_inj_id = :id")
    fun findByUsingInjectorId(id: Int): LiveData<List<RevisionInjectorEntity>>

    @Transaction
    @Query("SELECT * FROM rin_revisoes_injetores WHERE rin_inj_id = :id")
    fun findByUsingInjectorIdList(id: Int): List<RevisionInjectorEntity>?
}