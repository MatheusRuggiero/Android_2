package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.global.XidBrandEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidBrandDao : GenericDao<XidBrandEntity>{
    @Transaction @Query("SELECT * FROM xid_brand WHERE id_xid = :id")
    fun findById(id : Int) : XidBrandEntity?

    @Transaction @Query("SELECT * FROM xid_brand")
    fun findAll() : List<XidBrandEntity>?

    @Transaction @Query("SELECT MAX(xid) FROM xid_brand")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_brand WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidBrandEntity?

    @Transaction @Query("SELECT * FROM xid_brand WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : List<XidBrandEntity>?

    @Transaction @Query("SELECT * FROM xid_brand WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidBrandEntity>?

    @Transaction @Query("SELECT * FROM xid_brand WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidBrandEntity>?
}