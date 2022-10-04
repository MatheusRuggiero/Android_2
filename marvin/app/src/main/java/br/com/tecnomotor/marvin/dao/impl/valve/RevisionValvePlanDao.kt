package br.com.tecnomotor.marvin.dao.impl.valve

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.valve.RevisionPlanValveEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface RevisionValvePlanDao : GenericDao<RevisionPlanValveEntity>{
    @Transaction @Query("SELECT * FROM rvp_revisoes_valvula_planos WHERE rvp_rev_id_plano = :revisionPanId")
    fun findById(revisionPanId : Int) : RevisionPlanValveEntity?

    @Transaction @Query("SELECT * FROM rvp_revisoes_valvula_planos")
    fun findAll() : MutableList<RevisionPlanValveEntity>?

    @Transaction @Query("SELECT * FROM rvp_revisoes_valvula_planos WHERE rvp_rev_id_plano = :revisionPanId")
    fun findByRevision(revisionPanId: Int): MutableList<RevisionPlanValveEntity>?

    @Transaction @Query("SELECT * FROM rvp_revisoes_valvula_planos WHERE rvp_plv_id = :idPls AND rvp_rev_id_plano = :idRevPlano")
    fun findByRevisionValvePlan(idPls: Int, idRevPlano: Int): MutableList<RevisionPlanValveEntity>?
}