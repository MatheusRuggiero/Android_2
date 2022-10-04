package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.TranslateTypePointTestInjectorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TranslateTypePointTestInjectorDao : GenericDao<TranslateTypePointTestInjectorEntity> {
//    @Transaction @Query("SELECT * FROM tpo_traducao_tipo_ponto_teste_injetor WHERE ttpti_id = :id")
//    fun findById(id : Int) : TranslateTypePointTestInjectorEntity?

    @Transaction
    @Query("SELECT * FROM tpo_traducao_tipo_ponto_teste_injetor")
    fun findAll(): List<TranslateTypePointTestInjectorEntity>?

    @Transaction
    @Query("SELECT * FROM tpo_traducao_tipo_ponto_teste_injetor WHERE tpo_idioma = :idioma AND tpo_tpt_id = :id AND tpo_traducao = :traducao")
    fun findByTranslateTypePointTestInjector(idioma: String?, id: Int?, traducao: String?): List<TranslateTypePointTestInjectorEntity>?
}