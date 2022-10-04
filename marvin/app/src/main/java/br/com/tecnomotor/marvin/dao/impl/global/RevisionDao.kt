package br.com.tecnomotor.marvin.dao.impl.global

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao
import br.com.tecnomotor.marvin.model.global.Revision

@Dao
interface RevisionDao : GenericDao<RevisionEntity> {
    @Transaction
    @Query("SELECT * FROM rev_revisoes WHERE rev_id = :id")
    fun findById(id: Int): LiveData<RevisionEntity>

    @Transaction
    @Query("SELECT * FROM rev_revisoes")
    fun findAll(): List<RevisionEntity>?

    @Transaction
    @Query("SELECT * FROM rev_revisoes WHERE rev_id = :id")
    fun findByRevision(id: Int): List<RevisionEntity>?

    @Transaction
    @Query("SELECT * FROM rev_revisoes WHERE rev_id = :id")
    fun findByIdList(id: Int): List<RevisionEntity>?

    @Transaction
    @Query("SELECT * FROM rev_revisoes WHERE rev_id = :id")
    fun findByUsingRevisionId(id: Int): LiveData<List<RevisionEntity>>

    @Transaction
    @Query("SELECT * FROM rev_revisoes WHERE rev_id = :id")
    fun findByUsingRevisionIdList(id: Int): List<RevisionEntity>
}