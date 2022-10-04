package br.com.tecnomotor.marvin.dao.impl.global

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.global.TypePlanTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TypePlanTestDao : GenericDao<TypePlanTestEntity>{
    @Transaction @Query("SELECT * FROM tpl_tipo_plano_teste WHERE tpl_id = :id")
    fun findById(id : Int) : TypePlanTestEntity?

    @Transaction @Query("SELECT * FROM tpl_tipo_plano_teste")
    fun findAll() : List<TypePlanTestEntity>?

    @Transaction @Query("SELECT * FROM tpl_tipo_plano_teste WHERE tpl_id = :id")
    fun findByIdList(id: Int): List<TypePlanTestEntity>?

    @Transaction @Query("SELECT * FROM tpl_tipo_plano_teste WHERE tpl_id = :id")
    fun findByTypePlanTestUsingIdTypeListLiveData(id: Int): LiveData<MutableList<TypePlanTestEntity>>

    @Transaction @Query("SELECT * FROM tpl_tipo_plano_teste WHERE tpl_id = :id")
    fun findByIdLiveData(id: Int): LiveData<TypePlanTestEntity>

    @Transaction @Query("SELECT * FROM tpl_tipo_plano_teste WHERE tpl_id = :id")
    fun findByIdTypePlanTest(id: Int): TypePlanTestEntity
}