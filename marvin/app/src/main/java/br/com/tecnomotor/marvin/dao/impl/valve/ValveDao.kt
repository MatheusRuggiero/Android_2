package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.ValveEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface ValveDao : GenericDao<ValveEntity> {
    @Transaction
    @Query("SELECT * FROM var_valvulas WHERE var_id = :id")
    fun findById(id: Int): ValveEntity?

    @Transaction
    @Query("SELECT * FROM var_valvulas WHERE var_id = :id")
    fun findByIdList(id: Int): MutableList<ValveEntity>?

    @Transaction
    @Query("SELECT * FROM var_valvulas")
    fun findAll(): MutableList<ValveEntity>?

    @Transaction
    @Query("SELECT * FROM var_valvulas WHERE var_id = :id")
    fun findByIdLiveData(id: Int): LiveData<ValveEntity>?

    @Transaction
    @Query("SELECT * FROM var_valvulas")
    fun findAllLiveData(): LiveData<MutableList<ValveEntity>>
}