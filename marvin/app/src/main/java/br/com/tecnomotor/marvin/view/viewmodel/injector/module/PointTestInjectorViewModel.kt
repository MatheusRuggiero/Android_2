package br.com.tecnomotor.marvin.view.viewmodel.injector.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.injector.PointInjectorTestEntity
import br.com.tecnomotor.marvin.dao.entities.injector.TypePointInjectorTestEntity
import br.com.tecnomotor.marvin.repository.injector.InjectorPlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.PointTestInjectorRepository
import br.com.tecnomotor.marvin.repository.injector.TypePointInjectorTestRepository
import br.com.tecnomotor.marvin.model.Resource

class PointTestInjectorViewModel(
    private val repository1: InjectorPlanTestRepository,
    private val repository2: PointTestInjectorRepository,
    private val repository3: TypePointInjectorTestRepository
) : ViewModel() {

    fun findByPlanTestInjectorUsingIdPlanIndividuallyLiveData(id: Int): LiveData<InjectorPlanTestEntity?> {
        return repository1.findByPlanTestInjectorUsingIdPlanIndividuallyLiveData(id)
    }

    fun findByPointTestInjectorUsingIdPlanList(id: Int): LiveData<Resource<List<PointInjectorTestEntity>?>> {
        return repository2.findByPointTestInjectorUsingIdPlanList(id)
    }

    fun findByPointTestInjectorUsingIdPlanListLiveDataResource(id: Int): LiveData<Resource<List<PointInjectorTestEntity>?>> {
        return repository2.findByPointTestInjectorUsingIdPlanListLiveDataResource(id)
    }

//    fun findByPointTestInjectorUsingId(id: Int) : List<PointInjectorTestEntity>? {
//        return repository2.findByPointTestInjectorUsingId(id)
//    }

    fun findByPointTestInjectorUpdate(pointInjectorTestEntity: PointInjectorTestEntity) {
        return repository2.findByPointTestInjectorUpdate(pointInjectorTestEntity)
    }

    fun findByTypeInjectorIdLiveData(id: Int): LiveData<TypePointInjectorTestEntity>? {
        return repository3.findByTypeInjectorIdLiveData(id)
    }
}