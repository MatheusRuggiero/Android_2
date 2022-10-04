package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlanTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface InjectorPlanTestDao : GenericDao<InjectorPlanTestEntity> {
    @Transaction
    @Query("SELECT * FROM pli_planos_testes_injetores WHERE pli_id = :id")
    fun findById(id: Int): InjectorPlanTestEntity?

    @Transaction
    @Query("SELECT * FROM pli_planos_testes_injetores")
    fun findAll(): List<InjectorPlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM pli_planos_testes_injetores WHERE pli_id = :id")
    fun findByIdList(id: Int): List<InjectorPlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM pli_planos_testes_injetores WHERE pli_id = :id and pli_deleted=0")
    fun findByPlanTestInjectorUsingIdPlanLiveData(id: Int): LiveData<InjectorPlanTestEntity?>
}