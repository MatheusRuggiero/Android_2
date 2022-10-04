package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPlanTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface PumpPlanTestDao : GenericDao<PumpPlanTestEntity>{
    @Transaction @Query("SELECT * FROM plb_planos_testes_bombas WHERE plb_id = :id and plb_deleted=0")
    fun findById(id : Int) : PumpPlanTestEntity?

    @Transaction @Query("SELECT * FROM plb_planos_testes_bombas WHERE plb_id = :id and plb_deleted=0")
    fun findByIdList(id : Int) : MutableList<PumpPlanTestEntity>?

    @Transaction @Query("SELECT * FROM plb_planos_testes_bombas where plb_deleted=0")
    fun findAll() : LiveData<List<PumpPlanTestEntity>>

    @Transaction @Query("SELECT * FROM plb_planos_testes_bombas WHERE plb_id = :id and plb_deleted=0")
    fun findByIdLiveData(id : Int) : LiveData<PumpPlanTestEntity?>
}