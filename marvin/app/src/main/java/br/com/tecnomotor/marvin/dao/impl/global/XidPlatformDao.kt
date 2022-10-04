package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.global.XidPlatformEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidPlatformDao : GenericDao<XidPlatformEntity>{
    @Transaction @Query("SELECT * FROM xid_platform WHERE id_xid = :id")
    fun findById(id : Int) : XidPlatformEntity?

    @Transaction @Query("SELECT * FROM xid_platform")
    fun findAll() : List<XidPlatformEntity>?

    @Transaction @Query("SELECT * FROM xid_platform WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidPlatformEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_platform")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_platform WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidPlatformEntity>?

    @Transaction @Query("SELECT * FROM xid_platform WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidPlatformEntity>?

    @Transaction @Query("SELECT * FROM xid_platform WHERE object_id = :objectId")
    fun findByObjectIdList(objectId: String?): List<XidPlatformEntity>?
}