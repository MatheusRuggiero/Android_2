package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao
import kotlinx.coroutines.flow.Flow

@Dao
interface InjectorDao : GenericDao<InjectorEntity> {
    @Transaction
    @Query("SELECT * FROM inj_injetores WHERE inj_id = :id")
    fun findById(id: Int): InjectorEntity?

    @Transaction
    @Query("SELECT * FROM inj_injetores WHERE inj_id = :id")
    fun findByIdList(id: Int): List<InjectorEntity>?

    @Transaction
    @Query("SELECT * FROM inj_injetores")
    fun findAll(): List<InjectorEntity>?

    @Query("SELECT * FROM inj_injetores ORDER BY inj_codigo")
    fun findAllLiveData(): LiveData<List<InjectorEntity>>

    @Query("SELECT * FROM inj_injetores WHERE inj_codigo LIKE :str")
    fun findByInjectorUsingCodeLiveData(str: String): LiveData<List<InjectorEntity>?>

    @Query("SELECT * FROM inj_injetores WHERE inj_codigo LIKE :str")
    fun findByInjectorUsingCodeList(str: String): List<InjectorEntity>?

    @Query("SELECT * FROM inj_injetores INNER JOIN mar_marcas ON inj_injetores.inj_marca_id = mar_marcas.mar_id WHERE mar_marcas.mar_nome LIKE :str")
    fun findByInjectorUsingBrandLiveData(str: String): LiveData<List<InjectorEntity>?>

    @Query("SELECT * FROM inj_injetores INNER JOIN mar_marcas ON inj_injetores.inj_marca_id = mar_marcas.mar_id WHERE mar_marcas.mar_nome LIKE :str")
    fun findByInjectorUsingBrandList(str: String): List<InjectorEntity>?

    @Query("SELECT * FROM inj_injetores WHERE inj_adapt_conector LIKE :str OR inj_adapt_retorno LIKE :str")
    fun findByInjectorUsingAdapterLiveData(str: String): LiveData<List<InjectorEntity>?>

    @Query("SELECT * FROM inj_injetores WHERE inj_adapt_conector LIKE :str OR inj_adapt_retorno LIKE :str")
    fun findByInjectorUsingAdapterList(str: String): List<InjectorEntity>?

    @Query("SELECT * FROM inj_injetores WHERE inj_aplicacao LIKE :str")
    fun findByInjectorUsingApplicationLiveData(str: String): LiveData<List<InjectorEntity>?>

    @Query("SELECT * FROM inj_injetores WHERE inj_aplicacao LIKE :str")
    fun findByInjectorUsingApplicationList(str: String): List<InjectorEntity>?

    @Query("SELECT * FROM inj_injetores WHERE inj_aplicacao LIKE :str")
    fun findByInjectorUsingCodeFlow(str: String): Flow<MutableList<InjectorEntity>>

    @Query("SELECT * FROM inj_injetores WHERE inj_codigo LIKE :codeInjector")
    fun findByInjectorUsingCode(codeInjector: String) : InjectorEntity
}