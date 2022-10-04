package br.com.tecnomotor.marvin.dao.impl.configuration

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao
import br.com.tecnomotor.marvin.dao.entities.configuration.ParameterRemoteEntity

@Dao
interface ParameterRemoteDao : GenericDao<ParameterRemoteEntity>{
    @Transaction @Query("SELECT * FROM par_parametros_remotos WHERE par_id = :id")
    fun findById(id : Int) : ParameterRemoteEntity?

    @Transaction @Query("SELECT * FROM par_parametros_remotos WHERE par_id = :id")
    fun findByIdList(id : Int) : List<ParameterRemoteEntity>?

    @Transaction @Query("SELECT * FROM par_parametros_remotos")
    fun findAll() : List<ParameterRemoteEntity>?

    @Query("SELECT * FROM par_parametros_remotos")
    fun findAllLiveData(): LiveData<List<ParameterRemoteEntity>>

}