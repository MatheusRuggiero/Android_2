package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlatformEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface InjectorPlatformDao : GenericDao<InjectorPlatformEntity> {
//    @Transaction @Query("SELECT * FROM ipl_injetores_plataformas WHERE id = :id")
//    fun findById(id: Int): InjectorPlatformEntity?

    @Transaction
    @Query("SELECT * FROM ipl_injetores_plataformas")
    fun findAll(): List<InjectorPlatformEntity>?

    @Transaction
    @Query("SELECT * FROM ipl_injetores_plataformas WHERE  ipl_inj_id = :id AND ipl_plat_id = :id1")
    fun findByIdComposite(id: Int?, id1: Int): List<InjectorPlatformEntity>?
}