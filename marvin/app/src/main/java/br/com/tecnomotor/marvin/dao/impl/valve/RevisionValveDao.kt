package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.RevisionValveEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface RevisionValveDao : GenericDao<RevisionValveEntity> {
    @Transaction
    @Query("SELECT * FROM rva_revisoes_valvulas WHERE rva_rev_id = :revId")
    fun findById(revId: Int): RevisionValveEntity?

    @Transaction
    @Query("SELECT * FROM rva_revisoes_valvulas")
    fun findAll(): MutableList<RevisionValveEntity>?

    @Transaction
    @Query("SELECT * FROM rva_revisoes_valvulas WHERE rva_rev_id = :revId")
    fun findByRevision(revId: Int): MutableList<RevisionValveEntity>?

    @Transaction
    @Query("SELECT * FROM rva_revisoes_valvulas WHERE rva_val_id = :idValve  AND rva_rev_id = :idRev")
    fun findByRevisionValve(idValve: Int, idRev: Int): MutableList<RevisionValveEntity>?
}