package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.XidInjectorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidInjectorDao : GenericDao<XidInjectorEntity>{
    @Transaction @Query("SELECT * FROM xid_injector WHERE id_xid = :id")
    fun findById(id : Int) : XidInjectorEntity?

    @Transaction @Query("SELECT * FROM xid_injector")
    fun findAll() : List<XidInjectorEntity>?

    @Transaction @Query("SELECT * FROM xid_injector WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidInjectorEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_injector")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_injector WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : List<XidInjectorEntity>?

    @Transaction @Query("SELECT * FROM xid_injector WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidInjectorEntity>?

    @Transaction @Query("SELECT * FROM xid_injector WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidInjectorEntity>?
}