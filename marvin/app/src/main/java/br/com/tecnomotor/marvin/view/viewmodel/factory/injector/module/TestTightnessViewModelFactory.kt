package br.com.tecnomotor.marvin.view.viewmodel.factory.injector.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.marvin.repository.injector.InjectorRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorTestReportRepository
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.PointTestInjectorViewModel
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.TestTightnessViewModel

class TestTightnessViewModelFactory(
    var repository1: InjectorRepository,
    var repository2: InjectorTestReportRepository
)  : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TestTightnessViewModel(repository1,repository2) as T
    }
}