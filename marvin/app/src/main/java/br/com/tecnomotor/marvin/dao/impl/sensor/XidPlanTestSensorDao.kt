package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.XidPlanTestSensorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidPlanTestSensorDao : GenericDao<XidPlanTestSensorEntity>{
    @Transaction @Query("SELECT * FROM xid_plan_test_sensor WHERE id_xid = :id")
    fun findById(id : Int) : XidPlanTestSensorEntity?

    @Transaction @Query("SELECT * FROM xid_plan_test_sensor")
    fun findAll() : MutableList<XidPlanTestSensorEntity>?

    @Transaction @Query("SELECT MAX(xid) FROM xid_plan_test_sensor")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_plan_test_sensor WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidPlanTestSensorEntity?

    @Transaction @Query("SELECT * FROM xid_plan_test_sensor WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : MutableList<XidPlanTestSensorEntity>?

    @Transaction @Query("SELECT * FROM xid_plan_test_sensor WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidPlanTestSensorEntity>?

    @Transaction @Query("SELECT * FROM xid_plan_test_sensor WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidPlanTestSensorEntity>?
}