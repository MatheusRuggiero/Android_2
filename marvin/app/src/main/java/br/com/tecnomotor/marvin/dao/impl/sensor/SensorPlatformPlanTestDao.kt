package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorPlatformPlanTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface SensorPlatformPlanTestDao : GenericDao<SensorPlatformPlanTestEntity> {
    @Transaction
    @Query("SELECT * FROM spp_sensores_plataformas_planos_testes WHERE spp_spl_sen_id = :id")
    fun findById(id: Int): SensorPlatformPlanTestEntity?

    @Transaction
    @Query("SELECT * FROM spp_sensores_plataformas_planos_testes WHERE spp_spl_sen_id = :id")
    fun findByIdList(id: Int): MutableList<SensorPlatformPlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM spp_sensores_plataformas_planos_testes")
    fun findAll(): MutableList<SensorPlatformPlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM spp_sensores_plataformas_planos_testes WHERE spp_spl_sen_id = :id")
    fun findByIdLiveData(id: Int): LiveData<SensorPlatformPlanTestEntity>?

    @Transaction
    @Query("SELECT * FROM spp_sensores_plataformas_planos_testes WHERE spp_spl_sen_id = :id")
    fun findSensorPlatformPlanTestUsingIdSensorListLiveData(id: Int): LiveData<MutableList<SensorPlatformPlanTestEntity>>

    @Transaction
    @Query("SELECT * FROM spp_sensores_plataformas_planos_testes WHERE spp_spl_sen_id = :splSenId AND spp_spl_plat_id = :platformSensorId AND spp_pls_id = :sensorPlanTesId")
    fun findByObjectCompositeSelection(splSenId: Int, platformSensorId: Int, sensorPlanTesId: Int): MutableList<SensorPlatformPlanTestEntity>?
}