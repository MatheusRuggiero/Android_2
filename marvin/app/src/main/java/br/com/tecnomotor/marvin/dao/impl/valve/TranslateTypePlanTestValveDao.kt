package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.TranslateTypePlanTestValveEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TranslateTypePlanTestValveDao : GenericDao<TranslateTypePlanTestValveEntity> {
    @Transaction
    @Query("SELECT * FROM tpv_traducao_tipo_plano_teste_valvulas WHERE tpv_id = :id")
    fun findById(id: Int): TranslateTypePlanTestValveEntity?

    @Transaction
    @Query("SELECT * FROM tpv_traducao_tipo_plano_teste_valvulas")
    fun findAll(): MutableList<TranslateTypePlanTestValveEntity>?

    @Transaction
    @Query("SELECT * FROM tpv_traducao_tipo_plano_teste_valvulas WHERE tpv_idioma = :idioma AND tpv_tpl_id = :id AND tpv_traducao = :traducao")
    fun findTranslateTypePlanTestValve(idioma: String?, id: Int?, traducao: String?): MutableList<TranslateTypePlanTestValveEntity>?
}
