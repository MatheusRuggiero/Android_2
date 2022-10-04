package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.XidTranslateTypePointTestInjectorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidTranslateTypePointTestInjectorDao : GenericDao<XidTranslateTypePointTestInjectorEntity>{
    @Transaction @Query("SELECT * FROM xid_translate_type_point_test_injector WHERE id_xid = :id")
    fun findById(id : Int) : XidTranslateTypePointTestInjectorEntity?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_test_injector")
    fun findAll() : List<XidTranslateTypePointTestInjectorEntity>?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_test_injector WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidTranslateTypePointTestInjectorEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_translate_type_point_test_injector")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_test_injector WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidTranslateTypePointTestInjectorEntity>?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_test_injector WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidTranslateTypePointTestInjectorEntity>?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_test_injector WHERE object_id = :objectId AND object_composition_id = :objectCompositionId AND generic_aux_identification_1 = :genericAuxIdentification1")
    fun findByTranslateTypePointTestInjector(objectId: String?, objectCompositionId: String?, genericAuxIdentification1: String?): List<XidTranslateTypePointTestInjectorEntity>?
}