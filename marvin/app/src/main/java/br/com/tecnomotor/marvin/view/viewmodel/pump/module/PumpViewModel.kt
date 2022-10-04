package br.com.tecnomotor.marvin.view.viewmodel.pump.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.tecnomotor.marvin.dao.entities.global.BrandEntity
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity
import br.com.tecnomotor.marvin.dao.entities.pump.PumpEntity
import br.com.tecnomotor.marvin.dao.entities.pump.RevisionPumpEntity
import br.com.tecnomotor.marvin.dao.entities.pump.TypePumpEntity
import br.com.tecnomotor.marvin.repository.global.BrandRepository
import br.com.tecnomotor.marvin.repository.global.RevisionRepository
import br.com.tecnomotor.marvin.repository.pump.PumpRepository
import br.com.tecnomotor.marvin.repository.pump.RevisionPumpRepository
import br.com.tecnomotor.marvin.repository.pump.TypePumpRepository
import br.com.tecnomotor.marvin.model.Resource

class PumpViewModel(
    private val pumpRepository: PumpRepository,
    private val brandRepository: BrandRepository,
    private val revisionRepository: RevisionRepository,
    private val revisionPumpRepository: RevisionPumpRepository,
    private val typePumpRepository: TypePumpRepository
) : ViewModel() {

    fun findAllPump(): LiveData<Resource<List<PumpEntity>?>> {
        return pumpRepository.findAllPump()
    }

    fun findByBrandId(id: Int): LiveData<BrandEntity>? {
        return brandRepository.findByIdBrandLiveData(id)
    }

    fun findByUsingRevisionId(id: Int): LiveData<Resource<List<RevisionEntity>?>> {
        return revisionRepository.findByUsingRevisionId(id)
    }

    fun findByUsingRevisionPumpId(id: Int): LiveData<Resource<List<RevisionPumpEntity>?>> {
        return revisionPumpRepository.findByUsingRevPumpId(id)
    }

    fun findByUsingTypeId(id: Int): LiveData<TypePumpEntity>? {
        return typePumpRepository.findByIdTypePump(id)
    }

}