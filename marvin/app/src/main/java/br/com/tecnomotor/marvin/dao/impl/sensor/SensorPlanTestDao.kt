package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorTestPlanEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface SensorPlanTestDao : GenericDao<SensorTestPlanEntity> {
    @Transaction
    @Query("SELECT * FROM pls_planos_testes_sensores WHERE pls_id = :id")
    fun findById(id: Int): SensorTestPlanEntity?

    @Transaction
    @Query("SELECT * FROM pls_planos_testes_sensores WHERE pls_id = :id")
    fun findByIdList(id: Int): MutableList<SensorTestPlanEntity>?

    @Transaction
    @Query("SELECT * FROM pls_planos_testes_sensores")
    fun findAll(): MutableList<SensorTestPlanEntity>?

    @Transaction
    @Query("SELECT * FROM pls_planos_testes_sensores WHERE pls_id = :id")
    fun findByIdLiveData(id: Int): LiveData<SensorTestPlanEntity>?

    @Transaction
    @Query("SELECT * FROM pls_planos_testes_sensores WHERE pls_id = :id")
    fun findByPlanTestSensorUsingIdPlanLiveData(id: Int): LiveData<SensorTestPlanEntity?>
}