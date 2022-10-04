package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.global.XidTypePlanTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidTypePlanTestDao : GenericDao<XidTypePlanTestEntity>{
    @Transaction @Query("SELECT * FROM xid_type_plan_test WHERE id_xid = :id")
    fun findById(id : Int) : XidTypePlanTestEntity?

    @Transaction @Query("SELECT * FROM xid_type_plan_test")
    fun findAll() : List<XidTypePlanTestEntity>?

    @Transaction @Query("SELECT * FROM xid_type_plan_test WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidTypePlanTestEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_type_plan_test")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_type_plan_test WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidTypePlanTestEntity>?

    @Transaction @Query("SELECT * FROM xid_type_plan_test WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidTypePlanTestEntity>?

    @Transaction @Query("SELECT * FROM xid_type_plan_test WHERE object_id = :objectId")
    fun findByObjectIdList(objectId: String?): List<XidTypePlanTestEntity>?
}