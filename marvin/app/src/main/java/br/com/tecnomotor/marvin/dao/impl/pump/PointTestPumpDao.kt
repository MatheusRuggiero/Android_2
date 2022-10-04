package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPointTestEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface PointTestPumpDao : GenericDao<PumpPointTestEntity> {
    @Transaction
    @Query("SELECT * FROM ptb_pontos_testes_bombas WHERE ptb_plb_id = :id and ptb_sequencia = :sequence")
    fun findById(id: Int, sequence: Int): PumpPointTestEntity?

    @Transaction
    @Query("SELECT * FROM ptb_pontos_testes_bombas")
    fun findAll(): List<PumpPointTestEntity>?

    @Transaction
    @Query("SELECT * FROM ptb_pontos_testes_bombas WHERE ptb_plb_id = :planTestPumpId AND ptb_tptb_id = :typeTestId AND ptb_sequencia = :sequence")
    fun findByObjectCompositeQueryList(planTestPumpId: Int?, typeTestId: Int?, sequence: Int?): List<PumpPointTestEntity>?

    @Transaction
    @Query("SELECT * FROM ptb_pontos_testes_bombas WHERE ptb_plb_id = :id ORDER BY ptb_sequencia")
    fun findByPointTestPumpUsingIdPlanListLiveData(id: Int): LiveData<MutableList<PumpPointTestEntity>>

    @Transaction
    @Query("SELECT * FROM ptb_pontos_testes_bombas WHERE ptb_plb_id = :id ORDER BY ptb_sequencia")
    fun findByPointTestPumpUsingIdPlanList(id: Int): MutableList<PumpPointTestEntity>?
}