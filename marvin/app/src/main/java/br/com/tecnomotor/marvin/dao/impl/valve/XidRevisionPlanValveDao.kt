package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

import br.com.tecnomotor.marvin.dao.entities.valve.XidRevisionPlanValveEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidRevisionPlanValveDao : GenericDao<XidRevisionPlanValveEntity>{
    @Transaction @Query("SELECT * FROM xid_revision_valve_plan WHERE id_xid = :id")
    fun findById(id : Int) : XidRevisionPlanValveEntity?

    @Transaction @Query("SELECT * FROM xid_revision_valve_plan")
    fun findAll() : MutableList<XidRevisionPlanValveEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_valve_plan WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidRevisionPlanValveEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_revision_valve_plan")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_revision_valve_plan WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidRevisionPlanValveEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_valve_plan WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidRevisionPlanValveEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_valve_plan WHERE object_id = :objectId")
    fun findByRevision(objectId: String?): MutableList<XidRevisionPlanValveEntity>?
}