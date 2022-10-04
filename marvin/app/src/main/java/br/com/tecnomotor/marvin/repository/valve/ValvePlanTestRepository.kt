package br.com.tecnomotor.marvin.repository.valve

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.api.v1.model.valve.PlanTestValveSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.valve.ValvePlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.valve.XidPlanTestValveEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class ValvePlanTestRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<ValvePlanTestEntity>?>>()
    private val mediatorIndividually = MediatorLiveData<Resource<ValvePlanTestEntity?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findByPlanTestValveUsingIdPlanIndividuallyLiveData(id: Int): LiveData<ValvePlanTestEntity?> {
        return mAppDatabase?.valvePlanTestDao()!!.findByPlanTestValveUsingIdPlanLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<PlanTestValveSynchronize>) {
        list.forEach {
            if (it.planTestValve != null
                && it.planTestValve.id > 0
            ) {
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_VALVE_PLAN_TEST_CURRENT_XID
                )!!.toInt()
                var existInDatabase = false
                val valveTestPlanEntity = ValvePlanTestEntity()

                valveTestPlanEntity.id = it.planTestValve.id
                valveTestPlanEntity.typePlanId = it.planTestValve.typePlanTest.id
                valveTestPlanEntity.descriptionTypePlan = it.planTestValve.typePlanTest.description.toString()
                valveTestPlanEntity.duty1 = it.planTestValve.duty1
                valveTestPlanEntity.duty2 = it.planTestValve.duty2
                valveTestPlanEntity.duty3 = it.planTestValve.duty3
                valveTestPlanEntity.duty4 = it.planTestValve.duty4
                valveTestPlanEntity.duty5 = it.planTestValve.duty5
                valveTestPlanEntity.limit1Max = it.planTestValve.limit1Max.toDouble()
                valveTestPlanEntity.limit1Min = it.planTestValve.limit1Min.toDouble()
                valveTestPlanEntity.limit2Max = it.planTestValve.limit2Max.toDouble()
                valveTestPlanEntity.limit2Min = it.planTestValve.limit2Min.toDouble()
                valveTestPlanEntity.limit3Max = it.planTestValve.limit3Max.toDouble()
                valveTestPlanEntity.limit3Min = it.planTestValve.limit3Min.toDouble()
                valveTestPlanEntity.limit4Max = it.planTestValve.limit4Max.toDouble()
                valveTestPlanEntity.limit4Min = it.planTestValve.limit4Min.toDouble()
                valveTestPlanEntity.limit5Max = it.planTestValve.limit5Max.toDouble()
                valveTestPlanEntity.limit5Min = it.planTestValve.limit5Min.toDouble()
                valveTestPlanEntity.timeTest = it.planTestValve.timeTest
                valveTestPlanEntity.numberPoints = it.planTestValve.numberPoints
                valveTestPlanEntity.mainDuty = it.planTestValve.mainDuty
                valveTestPlanEntity.lockingDuty = it.planTestValve.lockingDuty
                valveTestPlanEntity.pwmTimeCourse = it.planTestValve.pwmTimeCourse
                valveTestPlanEntity.pwmPrescaler = it.planTestValve.pwmPrescaler
                valveTestPlanEntity.token = it.planTestValve.token
                valveTestPlanEntity.revisionEntityId = it.planTestValve.revision?.id ?: 0
                valveTestPlanEntity.deleted = it.planTestValve.deleted

                val xidPlanTestValve = XidPlanTestValveEntity()
                xidPlanTestValve.id = it.xidPlanTestValve.id
                xidPlanTestValve.action = it.xidPlanTestValve.action
                xidPlanTestValve.actionNumber = it.xidPlanTestValve.actionNumber
                xidPlanTestValve.brand = it.xidPlanTestValve.brand
                xidPlanTestValve.brandId = it.xidPlanTestValve.brandId
                xidPlanTestValve.classResponsible = it.xidPlanTestValve.classResponsible
                xidPlanTestValve.createdDateObject = it.xidPlanTestValve.createdDateObject
                xidPlanTestValve.identification = it.xidPlanTestValve.identification
                xidPlanTestValve.identificationAux = it.xidPlanTestValve.identificationAux
                xidPlanTestValve.lastDateUpdate = it.xidPlanTestValve.lastDateUpdate
                xidPlanTestValve.objectCompositionId = it.xidPlanTestValve.objectCompositionId
                xidPlanTestValve.objectId = it.xidPlanTestValve.objectId
                xidPlanTestValve.variantNameTable1 = it.xidPlanTestValve.variantNameTable1
                xidPlanTestValve.variantNameTable2 = it.xidPlanTestValve.variantNameTable2
                xidPlanTestValve.variantNameTable3 = it.xidPlanTestValve.variantNameTable3
                xidPlanTestValve.variantNameTable4 = it.xidPlanTestValve.variantNameTable4
                xidPlanTestValve.variantNameTable5 = it.xidPlanTestValve.variantNameTable5
                xidPlanTestValve.variantNameTable6 = it.xidPlanTestValve.variantNameTable6
                xidPlanTestValve.responsibleId = it.xidPlanTestValve.responsibleId
                xidPlanTestValve.responsibleName = it.xidPlanTestValve.responsibleName
                xidPlanTestValve.responsibleToken = it.xidPlanTestValve.responsibleToken
                xidPlanTestValve.revisionId = it.xidPlanTestValve.revisionId
                xidPlanTestValve.revisionMotivation = it.xidPlanTestValve.revisionMotivation
                xidPlanTestValve.revisionMotivationObjectId = it.xidPlanTestValve.revisionMotivationObjectId
                xidPlanTestValve.revisionObjectMotivation = it.xidPlanTestValve.revisionObjectMotivation
                xidPlanTestValve.revisionObjectObservation = it.xidPlanTestValve.revisionObjectObservation
                xidPlanTestValve.versionDatabase = it.xidPlanTestValve.versionDatabase
                xidPlanTestValve.xid = it.xidPlanTestValve.xid
                xidPlanTestValve.platformId = it.xidPlanTestValve.platformId
                xidPlanTestValve.platform = it.xidPlanTestValve.platform
                xidPlanTestValve.genericAuxInfo1 = it.xidPlanTestValve.genericAuxInfo1
                xidPlanTestValve.genericAuxInfo2 = it.xidPlanTestValve.genericAuxInfo2
                xidPlanTestValve.genericAuxInfo3 = it.xidPlanTestValve.genericAuxInfo3
                xidPlanTestValve.genericAuxInfo4 = it.xidPlanTestValve.genericAuxInfo4
                xidPlanTestValve.genericAuxIdentification1 = it.xidPlanTestValve.genericAuxIdentification1
                xidPlanTestValve.genericAuxIdentification2 = it.xidPlanTestValve.genericAuxIdentification2
                xidPlanTestValve.genericAuxIdentification3 = it.xidPlanTestValve.genericAuxIdentification3
                xidPlanTestValve.genericAuxIdentification4 = it.xidPlanTestValve.genericAuxIdentification4
                xidPlanTestValve.forAnythingExtra1 = it.xidPlanTestValve.forAnythingExtra1
                xidPlanTestValve.forAnythingExtra2 = it.xidPlanTestValve.forAnythingExtra2
                xidPlanTestValve.forAnythingExtra3 = it.xidPlanTestValve.forAnythingExtra3
                xidPlanTestValve.forAnythingExtra4 = it.xidPlanTestValve.forAnythingExtra4
                xidPlanTestValve.backupDatabase = it.xidPlanTestValve.backupDatabase
                xidPlanTestValve.betaDatabaseReleased = it.xidPlanTestValve.betaDatabaseReleased
                xidPlanTestValve.developmentDatabaseReleased = it.xidPlanTestValve.developmentDatabaseReleased
                xidPlanTestValve.experimentalDatabaseReleased = it.xidPlanTestValve.experimentalDatabaseReleased
                xidPlanTestValve.officialDatabaseReleased = it.xidPlanTestValve.officialDatabaseReleased
                xidPlanTestValve.other1DatabaseReleased = it.xidPlanTestValve.other1DatabaseReleased
                xidPlanTestValve.other2DatabaseReleased = it.xidPlanTestValve.other2DatabaseReleased

                if (it.xidPlanTestValve.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPlanTestValve.action == ValueDefault.SRT_REMOVED
                    || it.xidPlanTestValve.action == ValueDefault.SRT_DELETADO
                    || it.xidPlanTestValve.action == ValueDefault.SRT_DELETED
                    || it.xidPlanTestValve.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPlanTestValve.actionNumber == ValueDefault.REMOVED
                    || it.xidPlanTestValve.actionNumber == ValueDefault.DELETADO
                    || it.xidPlanTestValve.actionNumber == ValueDefault.DELETED
                ) {
                    val valvePlanTestEntityAuxList: List<ValvePlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlanTestDao()?.findByIdList(it.planTestValve.id)

                    val xidValveTestPlanEntityList: List<XidPlanTestValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestValveDao()
                            ?.findByObjectIdList(it.planTestValve.id.toString())

                    if (!valvePlanTestEntityAuxList.isNullOrEmpty()) {
                        valvePlanTestEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.valvePlanTestDao()?.delete(it01)
                        }
                    }

                    if (!xidValveTestPlanEntityList.isNullOrEmpty()) {
                        xidValveTestPlanEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestValveDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var valveAuxPlanTestEntity: ValvePlanTestEntity? = null

                    val valvePlanTestEntityAuxList: List<ValvePlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlanTestDao()?.findByIdList(it.planTestValve.id)

                    if (!valvePlanTestEntityAuxList.isNullOrEmpty()
                        && valvePlanTestEntityAuxList.size >= 2
                    ) {
                        valvePlanTestEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.valvePlanTestDao()?.delete(it1)
                        }
                    } else if (!valvePlanTestEntityAuxList.isNullOrEmpty()) {
                        valveAuxPlanTestEntity = valvePlanTestEntityAuxList[0]
                    }

                    //delay(10)

                    if (valveAuxPlanTestEntity == null
                        || (valveAuxPlanTestEntity != null
                                && valveAuxPlanTestEntity.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlanTestDao()?.insert(valveTestPlanEntity)
                    } else if (valveAuxPlanTestEntity.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        valveTestPlanEntity.id = valveAuxPlanTestEntity.id

                        AppDatabase.getDatabase(application.applicationContext)?.valvePlanTestDao()?.update(valveTestPlanEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {

                            AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestValveDao()?.insert(xidPlanTestValve)
                        } else if (bUpdate) {

                            AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestValveDao()?.update(xidPlanTestValve)
                        }
                    }
                }
                if (it.xidPlanTestValve.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPlanTestValve.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_VALVE_PLAN_TEST_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestValveDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VALVE_PLAN_TEST_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VALVE_PLAN_TEST_CURRENT_XID,
                            (maxXidCurrent + 1).toString()
                        )
                    }
                }
            }
        }
    }

    fun maxXid(): Int {
        return AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestValveDao()?.maxXid()
            ?: GlobalUtil.method.recoverSharedPreferences(
                spConfiguration,
                PARAMETER_VALVE_PLAN_TEST_CURRENT_XID
            )?.toInt() ?: 0
    }
}