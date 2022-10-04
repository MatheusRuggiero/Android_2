package br.com.tecnomotor.marvin.dao.impl.configuration

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.configuration.XidPermissionParameterRemoteEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidPermissionParameterRemoteDao : GenericDao<XidPermissionParameterRemoteEntity>{
    @Transaction @Query("SELECT * FROM xid_permission_parameter_remote WHERE id_xid = :id")
    fun findById(id : Int) : XidPermissionParameterRemoteEntity?

    @Transaction @Query("SELECT * FROM xid_permission_parameter_remote")
    fun findAll() : List<XidPermissionParameterRemoteEntity>?

    @Transaction @Query("SELECT * FROM xid_permission_parameter_remote WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidPermissionParameterRemoteEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_permission_parameter_remote")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_permission_parameter_remote WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByPermissionParameterList(id1: String, id2: String, id3: String) : List<XidPermissionParameterRemoteEntity>?

}