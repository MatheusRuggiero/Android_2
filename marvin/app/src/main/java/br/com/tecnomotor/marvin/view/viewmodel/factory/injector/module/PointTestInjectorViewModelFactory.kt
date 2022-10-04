package br.com.tecnomotor.marvin.view.viewmodel.factory.injector.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.marvin.repository.injector.InjectorPlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.PointTestInjectorRepository
import br.com.tecnomotor.marvin.repository.injector.TypePointInjectorTestRepository
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.PointTestInjectorViewModel

class PointTestInjectorViewModelFactory(
    private val repository1: InjectorPlanTestRepository,
    private val repository2: PointTestInjectorRepository,
    private val repository3: TypePointInjectorTestRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PointTestInjectorViewModel(repository1, repository2, repository3) as T
    }
}