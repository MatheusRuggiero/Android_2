package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.pump.PumpPlanTestSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidPlanTestPumpEntity
import br.com.tecnomotor.marvin.model.Resource
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class PumpPlanTestRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<PumpPlanTestEntity>?>>()
    private val mediatorIndividually = MediatorLiveData<Resource<PumpPlanTestEntity?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findByPlanTest(): LiveData<Resource<List<PumpPlanTestEntity>?>> {
        mediator.addSource(findAllLiveData()) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<PumpPlanTestEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<PumpPlanTestEntity>?> =
                    if (resourceActually != null) {
                        Resource(data = resourceFailure.data, error = resourceFailure.error)
                    } else {
                        resourceFailure
                    }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    private fun findAllLiveData(): LiveData<List<PumpPlanTestEntity>> {
        return mAppDatabase?.pumpPlanTestDao()!!.findAll()
    }

    fun findPlanTestByIdLiveData(id: Int): LiveData<PumpPlanTestEntity?> {
        return mAppDatabase?.pumpPlanTestDao()!!.findByIdLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<PumpPlanTestSynchronize>) {
        try {
            list.forEach {
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_PUMP_PLAN_TEST_CURRENT_XID
                )!!.toInt()
                val existInDatabase = false
                val planTestPump = PumpPlanTestEntity()

                planTestPump.id = it.planTestPump.id
                planTestPump.typePlanId = it.planTestPump.typePlanId
                planTestPump.typePlanTestId = it.planTestPump.typePlanTest.id
                //planTestPump.typePlanTestDescription = it.planTestPump.typePlanTest!!.description.toString()
                planTestPump.pressure = it.planTestPump.pressure
                planTestPump.rotation = it.planTestPump.rotation
                planTestPump.minimumFlow = it.planTestPump.minimumFlow
                planTestPump.timeCourse = it.planTestPump.timeCourse
                planTestPump.prescaler = it.planTestPump.prescaler
                planTestPump.token = it.planTestPump.token
                planTestPump.revisionId = it.planTestPump.revision.id
                planTestPump.deleted = it.planTestPump.deleted

                val xidPlanTestPump = XidPlanTestPumpEntity()
                xidPlanTestPump.id = it.xidPlanTestPump.id
                xidPlanTestPump.action = it.xidPlanTestPump.action
                xidPlanTestPump.actionNumber = it.xidPlanTestPump.actionNumber
                xidPlanTestPump.brand = it.xidPlanTestPump.brand
                xidPlanTestPump.brandId = it.xidPlanTestPump.brandId
                xidPlanTestPump.classResponsible = it.xidPlanTestPump.classResponsible
                xidPlanTestPump.createdDateObject = it.xidPlanTestPump.createdDateObject
                xidPlanTestPump.identification = it.xidPlanTestPump.identification
                xidPlanTestPump.identificationAux = it.xidPlanTestPump.identificationAux
                xidPlanTestPump.lastDateUpdate = it.xidPlanTestPump.lastDateUpdate
                xidPlanTestPump.objectCompositionId = it.xidPlanTestPump.objectCompositionId
                xidPlanTestPump.objectId = it.xidPlanTestPump.objectId
                xidPlanTestPump.variantNameTable1 = it.xidPlanTestPump.variantNameTable1
                xidPlanTestPump.variantNameTable2 = it.xidPlanTestPump.variantNameTable2
                xidPlanTestPump.variantNameTable3 = it.xidPlanTestPump.variantNameTable3
                xidPlanTestPump.variantNameTable4 = it.xidPlanTestPump.variantNameTable4
                xidPlanTestPump.variantNameTable5 = it.xidPlanTestPump.variantNameTable5
                xidPlanTestPump.variantNameTable6 = it.xidPlanTestPump.variantNameTable6
                xidPlanTestPump.responsibleId = it.xidPlanTestPump.responsibleId
                xidPlanTestPump.responsibleName = it.xidPlanTestPump.responsibleName
                xidPlanTestPump.responsibleToken = it.xidPlanTestPump.responsibleToken
                xidPlanTestPump.revisionId = it.xidPlanTestPump.revisionId
                xidPlanTestPump.revisionMotivation = it.xidPlanTestPump.revisionMotivation
                xidPlanTestPump.revisionMotivationObjectId = it.xidPlanTestPump.revisionMotivationObjectId
                xidPlanTestPump.revisionObjectMotivation = it.xidPlanTestPump.revisionObjectMotivation
                xidPlanTestPump.revisionObjectObservation = it.xidPlanTestPump.revisionObjectObservation
                xidPlanTestPump.versionDatabase = it.xidPlanTestPump.versionDatabase
                xidPlanTestPump.xid = it.xidPlanTestPump.xid
                xidPlanTestPump.platformId = it.xidPlanTestPump.platformId
                xidPlanTestPump.platform = it.xidPlanTestPump.platform
                xidPlanTestPump.genericAuxInfo1 = it.xidPlanTestPump.genericAuxInfo1
                xidPlanTestPump.genericAuxInfo2 = it.xidPlanTestPump.genericAuxInfo2
                xidPlanTestPump.genericAuxInfo3 = it.xidPlanTestPump.genericAuxInfo3
                xidPlanTestPump.genericAuxInfo4 = it.xidPlanTestPump.genericAuxInfo4
                xidPlanTestPump.genericAuxIdentification1 = it.xidPlanTestPump.genericAuxIdentification1
                xidPlanTestPump.genericAuxIdentification2 = it.xidPlanTestPump.genericAuxIdentification2
                xidPlanTestPump.genericAuxIdentification3 = it.xidPlanTestPump.genericAuxIdentification3
                xidPlanTestPump.genericAuxIdentification4 = it.xidPlanTestPump.genericAuxIdentification4
                xidPlanTestPump.forAnythingExtra1 = it.xidPlanTestPump.forAnythingExtra1
                xidPlanTestPump.forAnythingExtra2 = it.xidPlanTestPump.forAnythingExtra2
                xidPlanTestPump.forAnythingExtra3 = it.xidPlanTestPump.forAnythingExtra3
                xidPlanTestPump.forAnythingExtra4 = it.xidPlanTestPump.forAnythingExtra4
                xidPlanTestPump.backupDatabase = it.xidPlanTestPump.backupDatabase
                xidPlanTestPump.betaDatabaseReleased = it.xidPlanTestPump.betaDatabaseReleased
                xidPlanTestPump.developmentDatabaseReleased = it.xidPlanTestPump.developmentDatabaseReleased
                xidPlanTestPump.experimentalDatabaseReleased = it.xidPlanTestPump.experimentalDatabaseReleased
                xidPlanTestPump.officialDatabaseReleased = it.xidPlanTestPump.officialDatabaseReleased
                xidPlanTestPump.other1DatabaseReleased = it.xidPlanTestPump.other1DatabaseReleased
                xidPlanTestPump.other2DatabaseReleased = it.xidPlanTestPump.other2DatabaseReleased

                if (it.xidPlanTestPump.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPlanTestPump.action == ValueDefault.SRT_REMOVED
                    || it.xidPlanTestPump.action == ValueDefault.SRT_DELETADO
                    || it.xidPlanTestPump.action == ValueDefault.SRT_DELETED
                    || it.xidPlanTestPump.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPlanTestPump.actionNumber == ValueDefault.REMOVED
                    || it.xidPlanTestPump.actionNumber == ValueDefault.DELETADO
                    || it.xidPlanTestPump.actionNumber == ValueDefault.DELETED
                ) {
                    val pumpPlanTestEntityAuxList: List<PumpPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlanTestDao()?.findByIdList(it.planTestPump.id)

                    val xidPumpPlanTestEntityList: List<XidPlanTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlanTestDao()
                            ?.findByObjectIdList(it.planTestPump.id.toString())

                    if (!pumpPlanTestEntityAuxList.isNullOrEmpty()) {
                        pumpPlanTestEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.pumpPlanTestDao()?.delete(it01)
                        }
                    }

                    if (!xidPumpPlanTestEntityList.isNullOrEmpty()) {
                        xidPumpPlanTestEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlanTestDao()?.delete(it02)
                        }
                    }

                } else {
                    val bInsert = false
                    val bUpdate = false

                    var pumpAuxPlanTestEntity: PumpPlanTestEntity? = null

                    val pumpPlanTestEntityAuxList: List<PumpPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlanTestDao()?.findByIdList(it.planTestPump.id)

                    if (!pumpPlanTestEntityAuxList.isNullOrEmpty()
                        && pumpPlanTestEntityAuxList.size >= 2
                    ) {
                        pumpPlanTestEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.pumpPlanTestDao()?.delete(it1)
                        }
                    } else if (!pumpPlanTestEntityAuxList.isNullOrEmpty()) {
                        pumpAuxPlanTestEntity = pumpPlanTestEntityAuxList[0]
                    }

                    //delay(10)

//                    if (pumpAuxPlanTestEntity == null
//                        || (pumpAuxPlanTestEntity != null
//                                && pumpAuxPlanTestEntity.pumpPlanTestId == 0)
//                    ) {
//                        bInsert = true
//                        existInDatabase = true
//                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlanTestDao()?.insert(planTestPump)
//                    } else if (pumpAuxPlanTestEntity.pumpPlanTestId != 0) {
//                        bUpdate = true
//                        existInDatabase = true
//                        planTestPump.id = pumpAuxPlanTestEntity.id
//                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlanTestDao()?.update(planTestPump)
//                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlanTestDao()?.insert(xidPlanTestPump)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlanTestDao()?.update(xidPlanTestPump)
                        }
                    }
                }
                if (it.xidPlanTestPump.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPlanTestPump.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_PUMP_PLAN_TEST_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlanTestDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PUMP_PLAN_TEST_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PUMP_PLAN_TEST_CURRENT_XID,
                            (maxXidCurrent + 1).toString()
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun maxXid(): Int {
        return GlobalUtil.method.recoverSharedPreferences(
            spConfiguration,
            PARAMETER_PUMP_PLAN_TEST_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlanTestDao()?.maxXid()
            ?: 0
    }
}