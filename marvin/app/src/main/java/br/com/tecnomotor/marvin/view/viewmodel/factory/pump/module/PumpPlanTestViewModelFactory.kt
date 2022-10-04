package br.com.tecnomotor.marvin.view.viewmodel.factory.pump.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.marvin.repository.global.TypePlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.PumpPlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.PumpPlatformPlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.RevisionPumpPlanRepository
import br.com.tecnomotor.marvin.view.viewmodel.pump.module.PumpPlanTestViewModel

class PumpPlanTestViewModelFactory(
    private val repository1: PumpPlanTestRepository,
    private val repository2: TypePlanTestRepository,
    private val repository3: PumpPlatformPlanTestRepository,
    private val repository4: RevisionPumpPlanRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PumpPlanTestViewModel(repository1, repository2, repository3, repository4) as T
    }
}