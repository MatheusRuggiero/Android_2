package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.TypePointInjectorTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TypePointInjectorTestDao : GenericDao<TypePointInjectorTestEntity> {
    @Transaction
    @Query("SELECT * FROM tpt_tipo_ponto_teste_injetor WHERE tpt_id = :id")
    fun findById(id: Int): TypePointInjectorTestEntity?

    @Transaction
    @Query("SELECT * FROM tpt_tipo_ponto_teste_injetor")
    fun findAll(): List<TypePointInjectorTestEntity>?

    @Transaction
    @Query("SELECT * FROM tpt_tipo_ponto_teste_injetor WHERE tpt_id = :id")
    fun findByIdList(id: Int): List<TypePointInjectorTestEntity>?

    @Transaction
    @Query("SELECT * FROM tpt_tipo_ponto_teste_injetor WHERE tpt_id = :id")
    fun findByTypeInjectorIdLiveData(id: Int): LiveData<TypePointInjectorTestEntity>?
}