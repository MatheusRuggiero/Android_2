package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.XidRevisionValveEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidRevisionValveDao : GenericDao<XidRevisionValveEntity>{
    @Transaction @Query("SELECT * FROM xid_revision_valve WHERE id_xid = :id")
    fun findById(id : Int) : XidRevisionValveEntity?

    @Transaction @Query("SELECT * FROM xid_revision_valve")
    fun findAll() : MutableList<XidRevisionValveEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_valve WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidRevisionValveEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_revision_valve")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_revision_valve WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidRevisionValveEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_valve WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidRevisionValveEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_valve WHERE object_id = :objectId")
    fun findByRevision(objectId: String?): MutableList<XidRevisionValveEntity>?
}