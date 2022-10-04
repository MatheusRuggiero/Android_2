package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorPlatformEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface SensorPlatformDao : GenericDao<SensorPlatformEntity> {
    @Transaction
    @Query("SELECT * FROM spl_sensores_plataformas WHERE spl_sen_id = :sensorId AND spl_plat_id = :platId")
    fun findById(sensorId: Int, platId: Int): SensorPlatformEntity?

    @Transaction
    @Query("SELECT * FROM spl_sensores_plataformas WHERE spl_sen_id = :sensorId AND spl_plat_id = :platId")
    fun findByIdList(sensorId: Int, platId: Int): MutableList<SensorPlatformEntity>?

    @Transaction
    @Query("SELECT * FROM spl_sensores_plataformas")
    fun findAll(): MutableList<SensorPlatformEntity>?

    @Transaction
    @Query("SELECT * FROM spl_sensores_plataformas WHERE spl_sen_id = :sensorId AND spl_plat_id = :platId")
    fun findByIdLiveData(sensorId: Int, platId: Int): LiveData<SensorPlatformEntity>?

    @Transaction
    @Query("SELECT * FROM spl_sensores_plataformas WHERE spl_sen_id = :sensorId AND spl_plat_id = :platId")
    fun findByIdComposite(sensorId: Int, platId: Int): MutableList<SensorPlatformEntity>?
}