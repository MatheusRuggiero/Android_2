package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.XidPumpPlatformPlanEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidPumpPlatformPlanTestDao : GenericDao<XidPumpPlatformPlanEntity>{
    @Transaction @Query("SELECT * FROM xid_pump_platform_plan WHERE id_xid = :id")
    fun findById(id : Int) : XidPumpPlatformPlanEntity?

    @Transaction @Query("SELECT * FROM xid_pump_platform_plan")
    fun findAll() : MutableList<XidPumpPlatformPlanEntity>?

    @Transaction @Query("SELECT * FROM xid_pump_platform_plan WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidPumpPlatformPlanEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_pump_platform_plan")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_pump_platform_plan WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : MutableList<XidPumpPlatformPlanEntity>?

    @Transaction @Query("SELECT * FROM xid_pump_platform_plan WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidPumpPlatformPlanEntity>?

    @Transaction @Query("SELECT * FROM xid_pump_platform_plan WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidPumpPlatformPlanEntity>?
}