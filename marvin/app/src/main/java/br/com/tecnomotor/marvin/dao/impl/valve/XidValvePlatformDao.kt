package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.XidValvePlatformEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidValvePlatformDao : GenericDao<XidValvePlatformEntity>{
    @Transaction @Query("SELECT * FROM xid_valve_platform WHERE id_xid = :id")
    fun findById(id : Int) : XidValvePlatformEntity?

    @Transaction @Query("SELECT * FROM xid_valve_platform")
    fun findAll() : MutableList<XidValvePlatformEntity>?

    @Transaction @Query("SELECT * FROM xid_valve_platform WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidValvePlatformEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_valve_platform")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_valve_platform WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : MutableList<XidValvePlatformEntity>?

    @Transaction @Query("SELECT * FROM xid_valve_platform WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidValvePlatformEntity>?

    @Transaction @Query("SELECT * FROM xid_valve_platform WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidValvePlatformEntity>?
}