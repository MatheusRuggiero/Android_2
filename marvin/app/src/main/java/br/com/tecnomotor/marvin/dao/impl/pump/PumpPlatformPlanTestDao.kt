package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPlatformPlanTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface PumpPlatformPlanTestDao : GenericDao<PumpPlatformPlanTestEntity> {
    @Transaction
    @Query("SELECT * FROM bpp_bombas_plataformas_planos_testes WHERE bpp_bom_id = :id")
    fun findById(id: Int): PumpPlatformPlanTestEntity?

    @Transaction
    @Query("SELECT * FROM bpp_bombas_plataformas_planos_testes WHERE bpp_bom_id = :id")
    fun findByIdList(id: Int): MutableList<PumpPlatformPlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM bpp_bombas_plataformas_planos_testes")
    fun findAll(): MutableList<PumpPlatformPlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM bpp_bombas_plataformas_planos_testes WHERE bpp_bom_id = :id")
    fun findByIdLiveData(id: Int): LiveData<PumpPlatformPlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM bpp_bombas_plataformas_planos_testes WHERE bpp_plat_id=:platId and bpp_bom_id=:pumpId")
    fun findPlatformPlanTestByPumpIdLiveData(platId: Int, pumpId: Int): LiveData<List<PumpPlatformPlanTestEntity>>

    @Transaction
    @Query("SELECT * FROM bpp_bombas_plataformas_planos_testes WHERE bpp_bom_id = :bppPplBomId AND bpp_plat_id = :platformPumpId AND bpp_plb_id = :pumpPlanTesId")
    fun findByObjectCompositeSelection(bppPplBomId: Int, platformPumpId: Int, pumpPlanTesId: Int): MutableList<PumpPlatformPlanTestEntity>?

    @Query("SELECT * FROM bpp_bombas_plataformas_planos_testes WHERE bpp_bom_id = :id")
    fun findPlataformPlanTestByPumpId(id: Int): MutableList<PumpPlatformPlanTestEntity>
}