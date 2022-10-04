package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorTestReportEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface InjectorTestReportDao : GenericDao<InjectorTestReportEntity> {
}