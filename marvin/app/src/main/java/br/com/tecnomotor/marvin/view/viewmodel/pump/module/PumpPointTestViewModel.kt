package br.com.tecnomotor.marvin.view.viewmodel.pump.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.tecnomotor.marvin.dao.entities.global.TypePlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPointTestEntity
import br.com.tecnomotor.marvin.dao.entities.pump.TypePointTestPumpEntity
import br.com.tecnomotor.marvin.dao.entities.pump.TypeReferencePumpEntity
import br.com.tecnomotor.marvin.model.Resource
import br.com.tecnomotor.marvin.repository.global.TypePlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.PointTestPumpRepository
import br.com.tecnomotor.marvin.repository.pump.PumpPlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.TypePointTestPumpRepository
import br.com.tecnomotor.marvin.repository.pump.TypeReferencePumpRepository

class PumpPointTestViewModel(
    private val planTestRepository: PumpPlanTestRepository,
    private val typePlanTestRepository: TypePlanTestRepository,
    private val pointTestRepository: PointTestPumpRepository,
    private val typePointTestRepository: TypePointTestPumpRepository,
    private val typeReferenceRepository: TypeReferencePumpRepository,
) : ViewModel() {
    fun findPlanTestById(id: Int): LiveData<PumpPlanTestEntity?> {
        return planTestRepository.findPlanTestByIdLiveData(id)
    }

    fun findTypePlanTestById(id: Int): LiveData<TypePlanTestEntity> {
        return typePlanTestRepository.findTypePlanByIdLiveData(id)
    }

    fun findPointTestByIdPlan(id: Int): LiveData<Resource<List<PumpPointTestEntity>?>> {
        return pointTestRepository.findByPointTestUsingIdPlan(id)
    }

    fun findByTypePointPumpID(typePointPumpId: Int): LiveData<TypePointTestPumpEntity>? {
        return typePointTestRepository.findByTypePumpIdLiveData(typePointPumpId)
    }

    fun findAllTypeReferencePump(): LiveData<Resource<List<TypeReferencePumpEntity>?>> {
        return typeReferenceRepository.findAllTypeReferencePump()
    }

    fun findByTypeReferencePumpId(id: Int): LiveData<TypeReferencePumpEntity> {
        return typeReferenceRepository.findById(id)
    }
}