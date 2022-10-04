package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.XidValveEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidValveDao : GenericDao<XidValveEntity>{
    @Transaction @Query("SELECT * FROM xid_valve WHERE id_xid = :id")
    fun findById(id : Int) : XidValveEntity?

    @Transaction @Query("SELECT * FROM xid_valve")
    fun findAll() : MutableList<XidValveEntity>?

    @Transaction @Query("SELECT * FROM xid_valve WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidValveEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_valve")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_valve WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : MutableList<XidValveEntity>?

    @Transaction @Query("SELECT * FROM xid_valve WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidValveEntity>?

    @Transaction @Query("SELECT * FROM xid_valve WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidValveEntity>?
}