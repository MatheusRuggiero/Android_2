package br.com.tecnomotor.marvin.view.viewmodel.injector.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity
import br.com.tecnomotor.marvin.dao.entities.global.TypePlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlatformPlanTestEntity
import br.com.tecnomotor.marvin.repository.global.TypePlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorPlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorPlatformPlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.RevisionInjectorPlanRepository
import br.com.tecnomotor.marvin.model.Resource

class InjectorPlanTestViewModel(
    private val planTestRepository: InjectorPlanTestRepository,
    private val typePlanTestRepository: TypePlanTestRepository,
    private val platformPlanTestRepository: InjectorPlatformPlanTestRepository,
    private val revisionPlanInjectorRepository: RevisionInjectorPlanRepository
) : ViewModel() {

    fun findByPlanTestInjectorUsingIdPlanIndividuallyLiveData(id: Int): LiveData<InjectorPlanTestEntity?> {
        return planTestRepository.findByPlanTestInjectorUsingIdPlanIndividuallyLiveData(id)
    }

    fun findTypePlanTestById(id: Int): LiveData<TypePlanTestEntity> {
        return typePlanTestRepository.findTypePlanByIdLiveData(id)
    }

    fun findByIdInjectorPlatformPlanTestList(id: Int): MutableList<InjectorPlatformPlanTestEntity> {
        return platformPlanTestRepository.findByIdInjectorPlatformPlanTestList(id)
    }

    fun findPlatformPlanTestByInjId(platId: Int, injId: Int): LiveData<Resource<List<InjectorPlatformPlanTestEntity>?>> {
        return platformPlanTestRepository.findInjectorPlatformPlanTestUsingIdInjectorListLiveDataResource(platId, injId)
    }

    fun findPlanTestById(id: Int): LiveData<InjectorPlanTestEntity?> {
        return typePlanTestRepository.findPlanTestByIdLIveData(id)
    }

    fun findRevisionById(id: Int): LiveData<RevisionEntity> {
        return revisionPlanInjectorRepository.findRevisionById(id)
    }

}