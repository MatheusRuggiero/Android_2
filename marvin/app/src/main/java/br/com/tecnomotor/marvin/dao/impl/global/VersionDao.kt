package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.global.VersionEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface VersionDao : GenericDao<VersionEntity>{
    @Transaction @Query("SELECT * FROM ver_versoes WHERE ver_id = :id")
    fun findById(id : Int) : VersionEntity?

    @Transaction @Query("SELECT * FROM ver_versoes")
    fun findAll() : List<VersionEntity>?

    @Transaction @Query("SELECT * FROM ver_versoes WHERE ver_id = :id")
    fun findByIdList(id: Int): List<VersionEntity>?
}