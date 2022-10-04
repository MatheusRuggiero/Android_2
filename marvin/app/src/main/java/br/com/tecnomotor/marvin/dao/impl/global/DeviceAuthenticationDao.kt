package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.api.model.DeviceAuthentication
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface DeviceAuthenticationDao : GenericDao<DeviceAuthentication>{
    @Transaction @Query("SELECT * FROM device_authentication WHERE device_id = :id")
    fun findById(id : Int) : DeviceAuthentication?

    @Transaction @Query("SELECT * FROM device_authentication")
    fun findAll() : List<DeviceAuthentication>?
}