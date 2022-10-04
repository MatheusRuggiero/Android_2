package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.TranslateTypePlanTestPumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TranslateTypePlanTestPumpTestDao: GenericDao<TranslateTypePlanTestPumpEntity> {
    @Transaction
    @Query("SELECT * FROM tpb_traducao_tipo_plano_teste_bomba WHERE ttptp_id = :id")
    fun findById(id : Int) : TranslateTypePlanTestPumpEntity?

    @Transaction
    @Query("SELECT * FROM tpb_traducao_tipo_plano_teste_bomba")
    fun findAll() : MutableList<TranslateTypePlanTestPumpEntity>?

    @Transaction
    @Query("SELECT * FROM tpb_traducao_tipo_plano_teste_bomba WHERE tpb_idioma = :idioma AND tpb_tpl_id = :id AND tpb_traducao = :traducao")
    fun findTranslateTypePlanTestPump(idioma: String?, id: Int?, traducao: String?): MutableList<TranslateTypePlanTestPumpEntity>?
}
