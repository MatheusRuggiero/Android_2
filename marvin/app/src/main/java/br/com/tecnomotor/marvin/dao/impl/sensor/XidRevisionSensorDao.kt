package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.XidRevisionSensorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidRevisionSensorDao : GenericDao<XidRevisionSensorEntity>{
    @Transaction @Query("SELECT * FROM xid_revision_sensor WHERE id_xid = :id")
    fun findById(id : Int) : XidRevisionSensorEntity?

    @Transaction @Query("SELECT * FROM xid_revision_sensor")
    fun findAll() : MutableList<XidRevisionSensorEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_sensor WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidRevisionSensorEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_revision_sensor")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_revision_sensor WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidRevisionSensorEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_sensor WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidRevisionSensorEntity>?

    @Transaction @Query("SELECT * FROM xid_revision_sensor WHERE object_id = :objectId")
    fun findByRevision(objectId: String?): MutableList<XidRevisionSensorEntity>?
}