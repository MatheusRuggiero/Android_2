package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.XidTypePumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidTypePumpDao : GenericDao<XidTypePumpEntity>{
    @Transaction @Query("SELECT * FROM xid_type_pump WHERE id_xid = :id")
    fun findById(id : Int) : XidTypePumpEntity?

    @Transaction @Query("SELECT * FROM xid_type_pump")
    fun findAll() : MutableList<XidTypePumpEntity>?

    @Transaction @Query("SELECT * FROM xid_type_pump WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidTypePumpEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_type_pump")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_type_pump WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : MutableList<XidTypePumpEntity>?

    @Transaction @Query("SELECT * FROM xid_type_pump WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidTypePumpEntity>?

    @Transaction @Query("SELECT * FROM xid_type_pump WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidTypePumpEntity>?
}