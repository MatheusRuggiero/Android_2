package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPointTestReportEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface InjectorPointTestReportDao : GenericDao<InjectorPointTestReportEntity> {
    @Transaction
    @Query("SELECT * FROM ptr_pontos_testes_injetores_relatorio WHERE inj_report_id = :id")
    fun findById(id: Int): InjectorPointTestReportEntity?
}