package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.TranslateTypePointTestPumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TranslateTypePointTestPumpTestDao: GenericDao<TranslateTypePointTestPumpEntity> {
    @Transaction
    @Query("SELECT * FROM tbp_traducao_tipo_ponto_teste_bomba WHERE ttptp_id = :id")
    fun findById(id : Int) : TranslateTypePointTestPumpEntity?

    @Transaction
    @Query("SELECT * FROM tbp_traducao_tipo_ponto_teste_bomba")
    fun findAll() : MutableList<TranslateTypePointTestPumpEntity>?

    @Transaction
    @Query("SELECT * FROM tbp_traducao_tipo_ponto_teste_bomba WHERE tbp_idioma = :idioma AND tbp_tptb_id = :id AND tbp_traducao = :traducao")
    fun findTranslateTypePointTestPump(idioma: String?, id: Int?, traducao: String?): MutableList<TranslateTypePointTestPumpEntity>?
}
