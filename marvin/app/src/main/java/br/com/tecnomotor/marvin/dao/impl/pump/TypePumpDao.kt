package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.TypePumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TypePumpDao : GenericDao<TypePumpEntity>{
    @Transaction @Query("SELECT * FROM tbo_tipo_bomba WHERE tbo_id = :id")
    fun findById(id : Int) : TypePumpEntity?

    @Transaction @Query("SELECT * FROM tbo_tipo_bomba WHERE tbo_id = :id")
    fun findByIdList(id : Int) : MutableList<TypePumpEntity>?

    @Transaction @Query("SELECT * FROM tbo_tipo_bomba")
    fun findAll() : MutableList<TypePumpEntity>?

    @Transaction @Query("SELECT * FROM tbo_tipo_bomba WHERE tbo_id = :id")
    fun findByIdLiveData(id : Int) : LiveData<TypePumpEntity>?

    @Transaction @Query("SELECT * FROM tbo_tipo_bomba")
    fun findAllLiveData(): LiveData<MutableList<TypePumpEntity>>
}