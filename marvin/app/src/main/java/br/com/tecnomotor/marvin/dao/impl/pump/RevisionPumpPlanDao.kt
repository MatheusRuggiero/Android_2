package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.RevisionPumpPlanEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface RevisionPumpPlanDao : GenericDao<RevisionPumpPlanEntity>{
    @Transaction @Query("SELECT * FROM rbp_revisoes_bomba_planos WHERE rbp_rev_id_plano = :revisionPanId")
    fun findById(revisionPanId : Int) : RevisionPumpPlanEntity?

    @Transaction @Query("SELECT * FROM rbp_revisoes_bomba_planos")
    fun findAll() : MutableList<RevisionPumpPlanEntity>?

    @Transaction @Query("SELECT * FROM rbp_revisoes_bomba_planos WHERE rbp_rev_id_plano = :revisionPanId")
    fun findByRevision(revisionPanId: Int): MutableList<RevisionPumpPlanEntity>?

    @Transaction @Query("SELECT * FROM rbp_revisoes_bomba_planos WHERE rbp_plb_id = :idPlb AND rbp_rev_id_plano = :idRevPlano")
    fun findByRevisionPumpPlan(idPlb: Int, idRevPlano: Int): MutableList<RevisionPumpPlanEntity>?
}