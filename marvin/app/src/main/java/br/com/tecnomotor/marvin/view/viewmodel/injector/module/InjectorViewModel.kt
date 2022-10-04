package br.com.tecnomotor.marvin.view.viewmodel.injector.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.tecnomotor.marvin.dao.entities.global.BrandEntity
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorEntity
import br.com.tecnomotor.marvin.dao.entities.injector.RevisionInjectorEntity
import br.com.tecnomotor.marvin.repository.global.BrandRepository
import br.com.tecnomotor.marvin.repository.global.RevisionRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorRepository
import br.com.tecnomotor.marvin.repository.injector.RevisionInjectorRepository
import br.com.tecnomotor.marvin.model.Resource

class InjectorViewModel(
    private val repository1: InjectorRepository,
    private val repository2: BrandRepository,
    private val repository3: RevisionRepository,
    private val repository4: RevisionInjectorRepository
) : ViewModel() {

    fun findAllInjector(): LiveData<Resource<List<InjectorEntity>?>> {
        return repository1.findAllInjector()
    }

    fun findByBrandId(id: Int): LiveData<BrandEntity>? {
        return repository2.findByIdBrandLiveData(id)
    }

    fun findByUsingRevisionId(id: Int): LiveData<Resource<List<RevisionEntity>?>> {
        return repository3.findByUsingRevisionId(id)
    }

    fun findByUsingInjectorId(id: Int): LiveData<Resource<List<RevisionInjectorEntity>?>> {
        return repository4.findByUsingInjectorId(id)
    }

}