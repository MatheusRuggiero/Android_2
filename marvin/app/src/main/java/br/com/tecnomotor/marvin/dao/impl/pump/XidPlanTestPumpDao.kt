package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.XidPlanTestPumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidPlanTestPumpDao : GenericDao<XidPlanTestPumpEntity>{
    @Transaction @Query("SELECT * FROM xid_plan_test_pump WHERE id_xid = :id")
    fun findById(id : Int) : XidPlanTestPumpEntity?

    @Transaction @Query("SELECT * FROM xid_plan_test_pump")
    fun findAll() : MutableList<XidPlanTestPumpEntity>?

    @Transaction @Query("SELECT MAX(xid) FROM xid_plan_test_pump")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_plan_test_pump WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidPlanTestPumpEntity?

    @Transaction @Query("SELECT * FROM xid_plan_test_pump WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : MutableList<XidPlanTestPumpEntity>?

    @Transaction @Query("SELECT * FROM xid_plan_test_pump WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidPlanTestPumpEntity>?

    @Transaction @Query("SELECT * FROM xid_plan_test_pump WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidPlanTestPumpEntity>?
}