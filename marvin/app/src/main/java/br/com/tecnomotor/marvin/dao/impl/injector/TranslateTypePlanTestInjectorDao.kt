package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.TranslateTypePlanTestInjectorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TranslateTypePlanTestInjectorDao : GenericDao<TranslateTypePlanTestInjectorEntity> {
//    @Transaction @Query("SELECT * FROM tpi_traducao_tipo_plano_teste_injetor WHERE ttpti_id = :id")
//    fun findById(id : Int) : TranslateTypePlanTestInjectorEntity?

    @Transaction
    @Query("SELECT * FROM tpi_traducao_tipo_plano_teste_injetor")
    fun findAll(): List<TranslateTypePlanTestInjectorEntity>?

    @Transaction
    @Query("SELECT * FROM tpi_traducao_tipo_plano_teste_injetor WHERE tpi_idioma = :idioma AND tpi_tpl_id = :id AND tpi_traducao = :traducao")
    fun findTranslateTypePlanTestInjector(idioma: String?, id: Int?, traducao: String?): List<TranslateTypePlanTestInjectorEntity>?
}