package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.ValvePlanTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface ValvePlanTestDao : GenericDao<ValvePlanTestEntity> {
    @Transaction
    @Query("SELECT * FROM plv_planos_testes_valvulass WHERE plv_id = :id")
    fun findById(id: Int): ValvePlanTestEntity?

    @Transaction
    @Query("SELECT * FROM plv_planos_testes_valvulass WHERE plv_id = :id")
    fun findByIdList(id: Int): MutableList<ValvePlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM plv_planos_testes_valvulass")
    fun findAll(): MutableList<ValvePlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM plv_planos_testes_valvulass WHERE plv_id = :id")
    fun findByIdLiveData(id: Int): LiveData<ValvePlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM plv_planos_testes_valvulass WHERE plv_id = :id")
    fun findByPlanTestValveUsingIdPlanLiveData(id: Int): LiveData<ValvePlanTestEntity?>
}