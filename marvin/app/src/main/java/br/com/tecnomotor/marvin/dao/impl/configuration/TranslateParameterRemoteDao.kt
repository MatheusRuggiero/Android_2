package br.com.tecnomotor.marvin.dao.impl.configuration

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao
import br.com.tecnomotor.marvin.model.configuration.TranslateParameterRemoteEntity

@Dao
interface TranslateParameterRemoteDao : GenericDao<TranslateParameterRemoteEntity>{
    @Transaction @Query("SELECT * FROM tpr_traducao_parametro_remoto WHERE tpr_parameter_id = :id")
    fun findById(id : Int) : TranslateParameterRemoteEntity?

    @Transaction @Query("SELECT * FROM tpr_traducao_parametro_remoto")
    fun findAll() : List<TranslateParameterRemoteEntity>?

    @Transaction @Query("SELECT * FROM tpr_traducao_parametro_remoto WHERE tpr_idioma = :idioma AND tpr_par_id = :id AND tpr_traducao = :traducao")
    fun findTranslate(idioma: String?, id: Int?, traducao: String?): List<TranslateParameterRemoteEntity>?

}