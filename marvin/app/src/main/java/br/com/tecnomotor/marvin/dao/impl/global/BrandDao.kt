package br.com.tecnomotor.marvin.dao.impl.global

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.global.BrandEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface BrandDao : GenericDao<BrandEntity>{
    @Transaction @Query("SELECT * FROM mar_marcas WHERE mar_id = :id")
    fun findById(id : Int) : BrandEntity?

    @Transaction @Query("SELECT * FROM mar_marcas WHERE mar_id = :id")
    fun findByIdList(id : Int) : List<BrandEntity>?

    @Transaction @Query("SELECT * FROM mar_marcas")
    fun findAll() : List<BrandEntity>?

    @Transaction @Query("SELECT * FROM mar_marcas WHERE mar_id = :id")
    fun findByIdLiveData(id : Int) : LiveData<BrandEntity>?

    @Transaction @Query("SELECT * FROM mar_marcas WHERE mar_id = :id")
    fun findByIdLiveDataTest(id : Int) : BrandEntity
}