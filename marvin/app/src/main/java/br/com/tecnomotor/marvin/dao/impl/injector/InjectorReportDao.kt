package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorReportEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface InjectorReportDao : GenericDao<InjectorReportEntity> {
    @Transaction
    @Query("SELECT * FROM rli_injetores_relatorio WHERE rli_inj_id = :id")
    fun findById(id: Int): InjectorReportEntity?
}