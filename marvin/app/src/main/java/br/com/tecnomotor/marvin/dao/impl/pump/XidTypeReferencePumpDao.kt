package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.XidTypeReferencePumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidTypeReferencePumpDao : GenericDao<XidTypeReferencePumpEntity> {
    @Transaction
    @Query("SELECT * FROM xid_type_reference_pump WHERE id_xid = :id")
    fun findById(id : Int) : XidTypeReferencePumpEntity?

    @Transaction
    @Query("SELECT * FROM xid_type_reference_pump")
    fun findAll() : MutableList<XidTypeReferencePumpEntity>?

    @Transaction
    @Query("SELECT * FROM xid_type_reference_pump WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidTypeReferencePumpEntity?

    @Transaction
    @Query("SELECT MAX(xid) FROM xid_type_reference_pump")
    fun maxXid() : Int?

    @Transaction
    @Query("SELECT * FROM xid_type_reference_pump WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : MutableList<XidTypeReferencePumpEntity>?

    @Transaction
    @Query("SELECT * FROM xid_type_reference_pump WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidTypeReferencePumpEntity>?

    @Transaction
    @Query("SELECT * FROM xid_type_reference_pump WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidTypeReferencePumpEntity>?
}