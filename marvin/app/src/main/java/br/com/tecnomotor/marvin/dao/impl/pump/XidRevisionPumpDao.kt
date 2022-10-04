package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.XidRevisionPumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidRevisionPumpDao : GenericDao<XidRevisionPumpEntity>{
    @Transaction @Query("SELECT * FROM xid_revision_pump WHERE id_xid = :id")
    fun findById(id : Int) : XidRevisionPumpEntity?

    @Transaction @Query("SELECT * FROM xid_revision_pump")
    fun findAll() : MutableList<XidRevisionPumpEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_pump WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidRevisionPumpEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_revision_pump")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_revision_pump WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidRevisionPumpEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_pump WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidRevisionPumpEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_pump WHERE object_id = :objectId")
    fun findByRevision(objectId: String?): MutableList<XidRevisionPumpEntity>?
}