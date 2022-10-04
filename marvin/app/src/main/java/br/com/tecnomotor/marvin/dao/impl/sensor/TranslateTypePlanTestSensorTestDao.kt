package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.TranslateTypePlanTestSensorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TranslateTypePlanTestSensorTestDao: GenericDao<TranslateTypePlanTestSensorEntity> {
    @Transaction
    @Query("SELECT * FROM tps_traducao_tipo_plano_teste_sensor WHERE tps_id = :id")
    fun findById(id : Int) : TranslateTypePlanTestSensorEntity?

    @Transaction
    @Query("SELECT * FROM tps_traducao_tipo_plano_teste_sensor")
    fun findAll() : MutableList<TranslateTypePlanTestSensorEntity>?

    @Transaction
    @Query("SELECT * FROM tps_traducao_tipo_plano_teste_sensor WHERE tps_idioma = :idioma AND tps_tpl_id = :id AND tps_traducao = :traducao")
    fun findTranslateTypePlanTestSensor(idioma: String?, id: Int?, traducao: String?): MutableList<TranslateTypePlanTestSensorEntity>?
}
