package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.global.XidRevisionEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidRevisionDao : GenericDao<XidRevisionEntity>{
    @Transaction @Query("SELECT * FROM xid_revision WHERE id_xid = :id")
    fun findById(id : Int) : XidRevisionEntity?

    @Transaction @Query("SELECT * FROM xid_revision")
    fun findAll() : List<XidRevisionEntity>?

    @Transaction @Query("SELECT * FROM xid_revision WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidRevisionEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_revision")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_revision WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidRevisionEntity>?

    @Transaction @Query("SELECT * FROM xid_revision WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidRevisionEntity>?

    @Transaction @Query("SELECT * FROM xid_revision WHERE object_id = :objectId")
    fun findByRevision(objectId: String?): List<XidRevisionEntity>?

    @Transaction @Query("SELECT * FROM xid_revision WHERE object_id = :id ")
    fun findByObjectIdList(id: String): List<XidRevisionEntity>?
}