package br.com.tecnomotor.marvin.view.viewmodel.factory.pump.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.marvin.repository.global.TypePlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.*
import br.com.tecnomotor.marvin.view.viewmodel.pump.module.PumpPointTestViewModel

class PumpPointTestViewModelFactory(
    private val pumpPlanTestRepository: PumpPlanTestRepository,
    private val typePlanTestRepository: TypePlanTestRepository,
    private val pointTestPumpRepository: PointTestPumpRepository,
    private val typePointTestPumpRepository: TypePointTestPumpRepository,
    private val typeReferencePumpRepository: TypeReferencePumpRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PumpPointTestViewModel(pumpPlanTestRepository, typePlanTestRepository, pointTestPumpRepository, typePointTestPumpRepository, typeReferencePumpRepository) as T
    }
}