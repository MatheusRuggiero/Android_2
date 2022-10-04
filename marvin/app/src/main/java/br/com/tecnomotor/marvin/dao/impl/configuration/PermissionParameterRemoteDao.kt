package br.com.tecnomotor.marvin.dao.impl.configuration

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao
import br.com.tecnomotor.marvin.dao.entities.configuration.PermissionParameterRemoteEntity

@Dao
interface PermissionParameterRemoteDao : GenericDao<PermissionParameterRemoteEntity> {
    @Transaction @Query("SELECT * FROM ppr_permissao_parametro_remoto WHERE ppr_id_auto_generated = :id")
    fun findById(id: Int): PermissionParameterRemoteEntity?

    @Transaction @Query("SELECT * FROM ppr_permissao_parametro_remoto")
    fun findAll(): List<PermissionParameterRemoteEntity>?

    @Transaction @Query("SELECT * FROM ppr_permissao_parametro_remoto WHERE  ppr_grupo = :id AND ppr_par_id = :id1")
    fun findByIdComposite(id: Int?, id1: Int): List<PermissionParameterRemoteEntity>?

    @Transaction @Query("SELECT * FROM ppr_permissao_parametro_remoto WHERE  ppr_grupo = :id AND ppr_par_id = :id1 AND ppr_tipo_operacao = :id2")
    fun findByIdComposite(id: Int?, id1: Int, id2: Int): List<PermissionParameterRemoteEntity>?

    @Transaction @Query("SELECT * FROM ppr_permissao_parametro_remoto WHERE ppr_grupo = :id AND ppr_par_id = :parameterRemoteEntityId AND ppr_tipo_operacao = :typeOperation")
    fun findByIdList(id: Int, parameterRemoteEntityId: Int, typeOperation: Int): List<PermissionParameterRemoteEntity>?
}