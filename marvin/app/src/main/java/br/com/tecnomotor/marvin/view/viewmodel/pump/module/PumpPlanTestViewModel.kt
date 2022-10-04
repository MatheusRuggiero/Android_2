package br.com.tecnomotor.marvin.view.viewmodel.pump.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity
import br.com.tecnomotor.marvin.dao.entities.global.TypePlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPlatformPlanTestEntity
import br.com.tecnomotor.marvin.model.Resource
import br.com.tecnomotor.marvin.repository.global.TypePlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.PumpPlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.PumpPlatformPlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.RevisionPumpPlanRepository

class PumpPlanTestViewModel(
    private val planTest: PumpPlanTestRepository,
    private val typePlanTest: TypePlanTestRepository,
    private val platformPlanTest: PumpPlatformPlanTestRepository,
    private val revisionPlanTest: RevisionPumpPlanRepository,
) : ViewModel() {

    fun findPlatformPlanTestByPumpId(
        platId: Int,
        pumpId: Int,
    ): LiveData<Resource<List<PumpPlatformPlanTestEntity>?>> {
        return platformPlanTest.findPlatformPlanTestByPumpIdLiveDataResource(platId, pumpId)
    }

    fun findPlanTestById(id: Int): LiveData<PumpPlanTestEntity?> {
        return planTest.findPlanTestByIdLiveData(id)
    }

    fun findTypePlanTestById(id: Int): LiveData<TypePlanTestEntity> {
        return typePlanTest.findTypePlanByIdLiveData(id)
    }

    fun findRevisionById(id: Int): LiveData<RevisionEntity> {
        return revisionPlanTest.findRevisionById(id)
    }


}