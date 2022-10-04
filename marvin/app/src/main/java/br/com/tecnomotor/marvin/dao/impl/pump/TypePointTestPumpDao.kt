package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.TypePointTestPumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TypePointTestPumpDao : GenericDao<TypePointTestPumpEntity> {
    @Transaction
    @Query("SELECT * FROM tptb_tipo_ponto_teste_bomba WHERE tptb_id = :id")
    fun findById(id: Int): TypePointTestPumpEntity?

    @Transaction
    @Query("SELECT * FROM tptb_tipo_ponto_teste_bomba WHERE tptb_id = :id")
    fun findByIdList(id: Int): MutableList<TypePointTestPumpEntity>?

    @Transaction
    @Query("SELECT * FROM tptb_tipo_ponto_teste_bomba")
    fun findAll(): MutableList<TypePointTestPumpEntity>?

    @Transaction
    @Query("SELECT * FROM tptb_tipo_ponto_teste_bomba WHERE tptb_id = :id")
    fun findByIdLiveData(id: Int): LiveData<TypePointTestPumpEntity>?

    @Transaction
    @Query("SELECT * FROM tptb_tipo_ponto_teste_bomba")
    fun findAllLiveData(): LiveData<MutableList<TypePointTestPumpEntity>>

    @Transaction
    @Query("SELECT * FROM tptb_tipo_ponto_teste_bomba WHERE tptb_id = :id")
    fun findByTypePumpIdLiveData(id: Int): LiveData<TypePointTestPumpEntity>?
}