package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.api.model.XidDeviceAuthentication
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidDeviceAuthenticationDao : GenericDao<XidDeviceAuthentication>{
    @Transaction @Query("SELECT * FROM xid_device_authentication WHERE id_xid = :id")
    fun findById(id : Int) : XidDeviceAuthentication?

    @Transaction @Query("SELECT * FROM xid_device_authentication")
    fun findAll() : List<XidDeviceAuthentication>?

    @Transaction @Query("SELECT * FROM xid_device_authentication WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidDeviceAuthentication?

    @Transaction @Query("SELECT MAX(xid) FROM xid_device_authentication")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_device_authentication WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidDeviceAuthentication>?

    @Transaction @Query("SELECT * FROM xid_device_authentication WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidDeviceAuthentication>?
}