package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.XidValvePlatformPlanEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidValvePlatformPlanDao : GenericDao<XidValvePlatformPlanEntity>{
    @Transaction @Query("SELECT * FROM xid_valve_platform_plan WHERE id_xid = :id")
    fun findById(id : Int) : XidValvePlatformPlanEntity?

    @Transaction @Query("SELECT * FROM xid_valve_platform_plan")
    fun findAll() : MutableList<XidValvePlatformPlanEntity>?

    @Transaction @Query("SELECT * FROM xid_valve_platform_plan WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidValvePlatformPlanEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_valve_platform_plan")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_valve_platform_plan WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : MutableList<XidValvePlatformPlanEntity>?

    @Transaction @Query("SELECT * FROM xid_valve_platform_plan WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidValvePlatformPlanEntity>?

    @Transaction @Query("SELECT * FROM xid_valve_platform_plan WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidValvePlatformPlanEntity>?
}