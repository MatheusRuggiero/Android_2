package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.XidInjectorPlatformPlanTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidInjectorPlatformTestPlanDao : GenericDao<XidInjectorPlatformPlanTestEntity>{
    @Transaction @Query("SELECT * FROM xid_injector_platform_plan_test WHERE id_xid = :id")
    fun findById(id : Int) : XidInjectorPlatformPlanTestEntity?

    @Transaction @Query("SELECT * FROM xid_injector_platform_plan_test")
    fun findAll() : List<XidInjectorPlatformPlanTestEntity>?

    @Transaction @Query("SELECT * FROM xid_injector_platform_plan_test WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidInjectorPlatformPlanTestEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_injector_platform_plan_test")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_injector_platform_plan_test WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidInjectorPlatformPlanTestEntity>?

    @Transaction @Query("SELECT * FROM xid_injector_platform_plan_test WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidInjectorPlatformPlanTestEntity>?

    @Transaction @Query("SELECT * FROM xid_injector_platform_plan_test WHERE object_id = :objectId AND object_composition_id = :objectCompositionId AND generic_aux_identification_1 = :genericAuxIdentification1")
    fun findByObjectCompositeSelection(objectId: String?, objectCompositionId: String?, genericAuxIdentification1: String?): List<XidInjectorPlatformPlanTestEntity>?

    @Transaction @Query("SELECT * FROM xid_injector_platform_plan_test WHERE object_id = :objectId AND object_composition_id = :objectCompositionId AND generic_aux_identification_1 = :genericAuxIdentification1 AND generic_aux_identification_2 = :genericAuxIdentification2")
    fun findByObjectCompositeSelection(objectId: String?, objectCompositionId: String?, genericAuxIdentification1: String?, genericAuxIdentification2: String?): List<XidInjectorPlatformPlanTestEntity>?
}