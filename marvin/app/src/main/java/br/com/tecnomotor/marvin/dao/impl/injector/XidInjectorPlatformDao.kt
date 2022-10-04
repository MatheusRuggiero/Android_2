package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.XidInjectorPlatformEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidInjectorPlatformDao : GenericDao<XidInjectorPlatformEntity>{
    @Transaction @Query("SELECT * FROM xid_injector_platform WHERE id_xid = :id")
    fun findById(id : Int) : XidInjectorPlatformEntity?

    @Transaction @Query("SELECT * FROM xid_injector_platform")
    fun findAll() : List<XidInjectorPlatformEntity>?

    @Transaction @Query("SELECT * FROM xid_injector_platform WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidInjectorPlatformEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_injector_platform")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_injector_platform WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidInjectorPlatformEntity>?

    @Transaction @Query("SELECT * FROM xid_injector_platform WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidInjectorPlatformEntity>?
}