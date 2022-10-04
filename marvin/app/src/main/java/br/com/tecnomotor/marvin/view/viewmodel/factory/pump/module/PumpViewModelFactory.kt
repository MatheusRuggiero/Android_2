package br.com.tecnomotor.marvin.view.viewmodel.factory.pump.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.marvin.repository.global.BrandRepository
import br.com.tecnomotor.marvin.repository.global.RevisionRepository
import br.com.tecnomotor.marvin.repository.pump.PumpRepository
import br.com.tecnomotor.marvin.repository.pump.RevisionPumpRepository
import br.com.tecnomotor.marvin.repository.pump.TypePumpRepository
import br.com.tecnomotor.marvin.view.viewmodel.pump.module.PumpViewModel

class PumpViewModelFactory(
    private val pumpRepository: PumpRepository,
    private val brandRepository: BrandRepository,
    private val revisionRepository: RevisionRepository,
    private val revisionPumpRepository: RevisionPumpRepository,
    private val typePumpRepository: TypePumpRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PumpViewModel(pumpRepository, brandRepository, revisionRepository, revisionPumpRepository, typePumpRepository) as T
    }
}