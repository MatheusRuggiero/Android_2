package br.com.tecnomotor.marvin.view.viewmodel.injector.module

import androidx.lifecycle.ViewModel
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorTestReportEntity
import br.com.tecnomotor.marvin.repository.injector.InjectorRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorTestReportRepository

class TestTightnessViewModel(
    var repository1: InjectorRepository,
    var repository2: InjectorTestReportRepository
)  : ViewModel() {

    fun saveInjectorTestReport(injectorTestReportEntity: InjectorTestReportEntity){
        repository2.save(injectorTestReportEntity)
    }

}