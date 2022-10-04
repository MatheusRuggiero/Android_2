package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.XidTranslateTypePointTestPumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidTranslateTypePointTestPumpTestDao : GenericDao<XidTranslateTypePointTestPumpEntity>{
    @Transaction @Query("SELECT * FROM xid_translate_type_point_pump WHERE id_xid = :id")
    fun findById(id : Int) : XidTranslateTypePointTestPumpEntity?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_pump")
    fun findAll() : MutableList<XidTranslateTypePointTestPumpEntity>?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_pump WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidTranslateTypePointTestPumpEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_translate_type_point_pump")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_pump WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidTranslateTypePointTestPumpEntity>?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_pump WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidTranslateTypePointTestPumpEntity>?

    @Transaction @Query("SELECT * FROM xid_translate_type_point_pump WHERE object_id = :objectId AND object_composition_id = :objectCompositionId AND generic_aux_identification_1 = :genericAuxIdentification1")
    fun findTranslateTypePlanTestInjector(objectId: String?, objectCompositionId: String?, genericAuxIdentification1: String?): MutableList<XidTranslateTypePointTestPumpEntity>?
}