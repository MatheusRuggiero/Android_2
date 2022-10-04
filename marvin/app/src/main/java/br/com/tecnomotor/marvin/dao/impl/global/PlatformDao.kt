package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.global.PlatformEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface PlatformDao : GenericDao<PlatformEntity>{
    @Transaction @Query("SELECT * FROM plat_plataformas WHERE plat_id = :id")
    fun findById(id : Int) : PlatformEntity?

    @Transaction @Query("SELECT * FROM plat_plataformas")
    fun findAll() : List<PlatformEntity>?

    @Transaction @Query("SELECT * FROM plat_plataformas WHERE plat_id = :id")
    fun findByIdList(id: Int): List<PlatformEntity>?
}