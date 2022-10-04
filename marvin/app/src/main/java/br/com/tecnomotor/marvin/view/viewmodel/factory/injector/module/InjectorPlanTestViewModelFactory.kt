package br.com.tecnomotor.marvin.view.viewmodel.factory.injector.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.marvin.repository.global.TypePlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorPlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorPlatformPlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.RevisionInjectorPlanRepository
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.InjectorPlanTestViewModel

class InjectorPlanTestViewModelFactory(
    private val repository1: InjectorPlanTestRepository,
    private val repository2: TypePlanTestRepository,
    private val repository3: InjectorPlatformPlanTestRepository,
    private val repositoty4: RevisionInjectorPlanRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InjectorPlanTestViewModel(repository1, repository2, repository3, repositoty4) as T
    }
}