package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.RevisionPlanSensorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface RevisionSensorPlanDao : GenericDao<RevisionPlanSensorEntity>{
    @Transaction @Query("SELECT * FROM rsp_revisoes_sensor_planos WHERE rsp_rev_id_plano = :revisionPanId")
    fun findById(revisionPanId : Int) : RevisionPlanSensorEntity?

    @Transaction @Query("SELECT * FROM rsp_revisoes_sensor_planos")
    fun findAll() : MutableList<RevisionPlanSensorEntity>?

    @Transaction @Query("SELECT * FROM rsp_revisoes_sensor_planos WHERE rsp_rev_id_plano = :revisionPanId")
    fun findByRevision(revisionPanId: Int): MutableList<RevisionPlanSensorEntity>?

    @Transaction @Query("SELECT * FROM rsp_revisoes_sensor_planos WHERE rsp_pls_id = :idPls AND rsp_rev_id_plano = :idRevPlano")
    fun findByRevisionSensorPlan(idPls: Int, idRevPlano: Int): MutableList<RevisionPlanSensorEntity>?
}