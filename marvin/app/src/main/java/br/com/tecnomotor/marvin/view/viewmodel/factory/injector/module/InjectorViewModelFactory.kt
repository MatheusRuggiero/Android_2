package br.com.tecnomotor.marvin.view.viewmodel.factory.injector.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.marvin.repository.global.BrandRepository
import br.com.tecnomotor.marvin.repository.global.RevisionRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorRepository
import br.com.tecnomotor.marvin.repository.injector.RevisionInjectorRepository
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.InjectorViewModel

class InjectorViewModelFactory(
    private val repository1: InjectorRepository,
    private val repository2: BrandRepository,
    private val repository3: RevisionRepository,
    private val repository4: RevisionInjectorRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InjectorViewModel(repository1, repository2, repository3, repository4) as T
    }
}