package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.PumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface PumpDao : GenericDao<PumpEntity>{
    @Transaction @Query("SELECT * FROM bom_bombas WHERE bom_id = :id")
    fun findById(id : Int) : PumpEntity?

    @Transaction @Query("SELECT * FROM bom_bombas WHERE bom_id = :id")
    fun findByIdList(id : Int) : MutableList<PumpEntity>?

    @Transaction @Query("SELECT * FROM bom_bombas")
    fun findAll() : MutableList<PumpEntity>?

    @Transaction @Query("SELECT * FROM bom_bombas WHERE bom_id = :id")
    fun findByIdLiveData(id : Int) : LiveData<PumpEntity>?

    @Transaction @Query("SELECT * FROM bom_bombas ORDER BY bom_codigo")
    fun findAllLiveData(): LiveData<MutableList<PumpEntity>>

    @Query("SELECT * FROM bom_bombas WHERE bom_codigo LIKE :codePump")
    fun findByPumpUsingCode(codePump: String): PumpEntity
}