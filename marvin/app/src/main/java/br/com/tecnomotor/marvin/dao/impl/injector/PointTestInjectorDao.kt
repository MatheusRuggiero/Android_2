package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.PointInjectorTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface PointTestInjectorDao : GenericDao<PointInjectorTestEntity>{

//    @Transaction
//    @Query("SELECT * FROM pti_pontos_testes_injetores WHERE pti_point_test_injector_id = :id")
//    fun findById(id : Int) : PointInjectorTestEntity?

    @Transaction
    @Query("SELECT * FROM pti_pontos_testes_injetores")
    fun findAll(): List<PointInjectorTestEntity>?

    @Transaction
    @Query("SELECT * FROM pti_pontos_testes_injetores WHERE pti_pli_id = :planTestInjectorId AND pti_tpt_id = :typeTestId AND pti_sequencia = :sequence")
    fun findByObjectCompositeQueryList(planTestInjectorId: Int?, typeTestId: Int?, sequence: Int?): List<PointInjectorTestEntity>?

    @Transaction
    @Query("SELECT * FROM pti_pontos_testes_injetores WHERE pti_pli_id = :id ORDER BY pti_sequencia")
    fun findByPointTestInjectorUsingIdPlanListLiveData(id: Int): LiveData<MutableList<PointInjectorTestEntity>>

//    @Transaction
//    @Query("SELECT * FROM pti_pontos_testes_injetores WHERE pti_point_test_injector_id = :id")
//    fun findByPointTestInjectorUsingId(id: Int): List<PointInjectorTestEntity>?

    @Transaction
    @Query("SELECT * FROM pti_pontos_testes_injetores WHERE pti_pli_id = :id ORDER BY pti_sequencia")
    fun findByPointTestInjectorUsingIdPlanList(id: Int): LiveData<MutableList<PointInjectorTestEntity>>
}