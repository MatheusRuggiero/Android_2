package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.ValvePlatformEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface ValvePlatformDao : GenericDao<ValvePlatformEntity> {
    @Transaction
    @Query("SELECT * FROM vpl_valvulas_plataformas WHERE vpl_val_id = :valveId AND vpl_plat_id = :platId")
    fun findById(valveId: Int, platId: Int): ValvePlatformEntity?

    @Transaction
    @Query("SELECT * FROM vpl_valvulas_plataformas WHERE vpl_val_id = :valveId AND vpl_plat_id = :platId")
    fun findByIdList(valveId: Int, platId: Int): MutableList<ValvePlatformEntity>?

    @Transaction
    @Query("SELECT * FROM vpl_valvulas_plataformas")
    fun findAll(): MutableList<ValvePlatformEntity>?

    @Transaction
    @Query("SELECT * FROM vpl_valvulas_plataformas WHERE vpl_val_id = :valveId AND vpl_plat_id = :platId")
    fun findByIdLiveData(valveId: Int, platId: Int): LiveData<ValvePlatformEntity>?

    @Transaction
    @Query("SELECT * FROM vpl_valvulas_plataformas WHERE vpl_val_id = :valveId AND vpl_plat_id = :platId")
    fun findByIdComposite(valveId: Int, platId: Int): MutableList<ValvePlatformEntity>?
}