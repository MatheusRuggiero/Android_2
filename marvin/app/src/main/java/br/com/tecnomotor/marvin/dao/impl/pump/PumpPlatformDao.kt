package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPlatformEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface PumpPlatformDao : GenericDao<PumpPlatformEntity> {
    @Transaction
    @Query("SELECT * FROM bpl_bombas_plataformas WHERE bpl_bom_id = :pumpId AND bpl_plat_id = :platId")
    fun findById(pumpId: Int, platId: Int): PumpPlatformEntity?

    @Transaction
    @Query("SELECT * FROM bpl_bombas_plataformas WHERE bpl_bom_id = :pumpId AND bpl_plat_id = :platId")
    fun findByIdList(pumpId: Int, platId: Int): MutableList<PumpPlatformEntity>?

    @Transaction
    @Query("SELECT * FROM bpl_bombas_plataformas")
    fun findAll(): MutableList<PumpPlatformEntity>?

    @Transaction
    @Query("SELECT * FROM bpl_bombas_plataformas WHERE bpl_bom_id = :pumpId AND bpl_plat_id = :platId")
    fun findByIdLiveData(pumpId: Int, platId: Int): LiveData<PumpPlatformEntity>?

    @Transaction
    @Query("SELECT * FROM bpl_bombas_plataformas WHERE bpl_bom_id = :pumpId AND bpl_plat_id = :platId")
    fun findByIdComposite(pumpId: Int, platId: Int): MutableList<PumpPlatformEntity>?
}