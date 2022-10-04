package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlatformPlanTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface InjectorPlatformPlanTestDao : GenericDao<InjectorPlatformPlanTestEntity> {
    @Transaction
    @Query("SELECT * FROM ipp_injetores_plataformas_planos_testes WHERE id = :id")
    fun findById(id: Int): InjectorPlatformPlanTestEntity?

    @Transaction
    @Query("SELECT * FROM ipp_injetores_plataformas_planos_testes")
    fun findAll(): List<InjectorPlatformPlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM ipp_injetores_plataformas_planos_testes WHERE ipp_ipl_inj_id = :injId AND ipp_ipl_plat_id = :platformId AND ipp_pli_id = :planId")
    fun findByObjectCompositeSelection(injId: Int?, platformId: Int, planId: Int?): List<InjectorPlatformPlanTestEntity>?

//    @Transaction @Query("SELECT * FROM ipp_injetores_plataformas_planos_testes WHERE ipp_ipl_inj_id = :id")
//    fun findByCodeInjector(id: Int): MutableList<InjectorPlatformPlanTestEntity>

    @Transaction
    @Query("SELECT * FROM ipp_injetores_plataformas_planos_testes WHERE ipp_ipl_plat_id = :platId and ipp_ipl_inj_id=:injId")
    fun findInjectorPlatformPlanTestUsingIdInjectorListLiveData(platId: Int, injId: Int): LiveData<List<InjectorPlatformPlanTestEntity>>

    @Transaction
    @Query("SELECT * FROM ipp_injetores_plataformas_planos_testes WHERE ipp_ipl_inj_id = :id")
    fun findByIdInjectorPlatformPlanTestList(id: Int): MutableList<InjectorPlatformPlanTestEntity>
}