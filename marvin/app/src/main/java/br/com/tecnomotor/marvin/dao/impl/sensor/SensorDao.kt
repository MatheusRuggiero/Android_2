package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface SensorDao : GenericDao<SensorEntity> {
    @Transaction
    @Query("SELECT * FROM sen_sensores WHERE sen_id = :id")
    fun findById(id: Int): SensorEntity?

    @Transaction
    @Query("SELECT * FROM sen_sensores WHERE sen_id = :id")
    fun findByIdList(id: Int): MutableList<SensorEntity>?

    @Transaction
    @Query("SELECT * FROM sen_sensores")
    fun findAll(): MutableList<SensorEntity>?

    @Transaction
    @Query("SELECT * FROM sen_sensores WHERE sen_id = :id")
    fun findByIdLiveData(id: Int): LiveData<SensorEntity>?

    @Transaction
    @Query("SELECT * FROM sen_sensores")
    fun findAllLiveData(): LiveData<MutableList<SensorEntity>>
}