package br.com.tecnomotor.marvin.dao.impl.configuration

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.configuration.XidParameterRemoteEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidParameterRemoteDao : GenericDao<XidParameterRemoteEntity>{
    @Transaction @Query("SELECT * FROM xid_parameter_remote WHERE id_xid = :id")
    fun findById(id : Int) : XidParameterRemoteEntity?

    @Transaction @Query("SELECT * FROM xid_parameter_remote")
    fun findAll() : List<XidParameterRemoteEntity>?

    @Transaction @Query("SELECT * FROM xid_parameter_remote WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidParameterRemoteEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_parameter_remote")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_parameter_remote WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : List<XidParameterRemoteEntity>?

    @Transaction @Query("SELECT * FROM xid_parameter_remote WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidParameterRemoteEntity>?

    @Transaction @Query("SELECT * FROM xid_parameter_remote WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidParameterRemoteEntity>?
}