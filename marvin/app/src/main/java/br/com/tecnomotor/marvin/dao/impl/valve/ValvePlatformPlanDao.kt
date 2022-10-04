package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.ValvePlatformPlanEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface ValvePlatformPlanDao : GenericDao<ValvePlatformPlanEntity> {
    @Transaction
    @Query("SELECT * FROM vpp_valvulas_plataformas_planos_testes WHERE vpp_vpl_val_id = :id")
    fun findById(id: Int): ValvePlatformPlanEntity?

    @Transaction
    @Query("SELECT * FROM vpp_valvulas_plataformas_planos_testes WHERE vpp_vpl_val_id = :id")
    fun findByIdList(id: Int): MutableList<ValvePlatformPlanEntity>?

    @Transaction
    @Query("SELECT * FROM vpp_valvulas_plataformas_planos_testes")
    fun findAll(): MutableList<ValvePlatformPlanEntity>?

    @Transaction
    @Query("SELECT * FROM vpp_valvulas_plataformas_planos_testes WHERE vpp_vpl_val_id = :id")
    fun findByIdLiveData(id: Int): LiveData<ValvePlatformPlanEntity>?

    @Transaction
    @Query("SELECT * FROM vpp_valvulas_plataformas_planos_testes WHERE vpp_vpl_val_id = :id")
    fun findValvePlatformPlanTestUsingIdValveListLiveData(id: Int): LiveData<MutableList<ValvePlatformPlanEntity>>

    @Transaction
    @Query("SELECT * FROM vpp_valvulas_plataformas_planos_testes WHERE vpp_vpl_val_id = :vppVplVal AND vpp_vpl_plat_id = :platformValId AND vpp_plv_id = :valPlanTestId")
    fun findByObjectCompositeSelection(vppVplVal: Int, platformValId: Int, valPlanTestId: Int): MutableList<ValvePlatformPlanEntity>?
}