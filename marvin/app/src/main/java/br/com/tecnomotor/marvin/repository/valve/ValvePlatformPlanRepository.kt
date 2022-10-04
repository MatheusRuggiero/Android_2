package br.com.tecnomotor.marvin.repository.valve

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.valve.ValvePlatformPlanSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.valve.ValvePlatformPlanEntity
import br.com.tecnomotor.marvin.dao.entities.valve.XidValvePlatformPlanEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class ValvePlatformPlanRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<ValvePlatformPlanEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }


    fun findValvePlatformPlanUsingIdValveListLiveDataResource(id: Int): LiveData<Resource<List<ValvePlatformPlanEntity>?>> {
        mediator.addSource(findValvePlatformPlanUsingIdValveListLive(id)) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<ValvePlatformPlanEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<ValvePlatformPlanEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    private fun findValvePlatformPlanUsingIdValveListLive(id: Int): LiveData<MutableList<ValvePlatformPlanEntity>> {
        return mAppDatabase?.valvePlatformPlanDao()!!.findValvePlatformPlanTestUsingIdValveListLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<ValvePlatformPlanSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                val valvePlatformPlanEntity = ValvePlatformPlanEntity()
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID
                )!!.toInt()

                valvePlatformPlanEntity.versionId =
                    if (it.valvePlatformPlan.version != null) it.valvePlatformPlan.version?.id ?: 0 else 0
                valvePlatformPlanEntity.vplValId = it.valvePlatformPlan.valvePlatformPlanPK!!.valvePlatform.valvePlatformPK!!.valve.id
                valvePlatformPlanEntity.planTestValveId = it.valvePlatformPlan.valvePlatformPlanPK!!.planTestValve.id
                valvePlatformPlanEntity.valvePlatformId =
                    it.valvePlatformPlan.valvePlatformPlanPK!!.valvePlatform.valvePlatformPK!!.platform.id
                valvePlatformPlanEntity.valvePlatformDescription =
                    it.valvePlatformPlan.valvePlatformPlanPK!!.valvePlatform.valvePlatformPK!!.platform.name.toString()

                val xidValvePlatformPlanEntity = XidValvePlatformPlanEntity()
                xidValvePlatformPlanEntity.id = it.xidValvePlatformPlan.id
                xidValvePlatformPlanEntity.action = it.xidValvePlatformPlan.action
                xidValvePlatformPlanEntity.actionNumber = it.xidValvePlatformPlan.actionNumber
                xidValvePlatformPlanEntity.brand = it.xidValvePlatformPlan.brand
                xidValvePlatformPlanEntity.brandId = it.xidValvePlatformPlan.brandId
                xidValvePlatformPlanEntity.classResponsible = it.xidValvePlatformPlan.classResponsible
                xidValvePlatformPlanEntity.createdDateObject = it.xidValvePlatformPlan.createdDateObject
                xidValvePlatformPlanEntity.identification = it.xidValvePlatformPlan.identification
                xidValvePlatformPlanEntity.identificationAux = it.xidValvePlatformPlan.identificationAux
                xidValvePlatformPlanEntity.lastDateUpdate = it.xidValvePlatformPlan.lastDateUpdate
                xidValvePlatformPlanEntity.objectCompositionId = it.xidValvePlatformPlan.objectCompositionId
                xidValvePlatformPlanEntity.objectId = it.xidValvePlatformPlan.objectId
                xidValvePlatformPlanEntity.variantNameTable1 = it.xidValvePlatformPlan.variantNameTable1
                xidValvePlatformPlanEntity.variantNameTable2 = it.xidValvePlatformPlan.variantNameTable2
                xidValvePlatformPlanEntity.variantNameTable3 = it.xidValvePlatformPlan.variantNameTable3
                xidValvePlatformPlanEntity.variantNameTable4 = it.xidValvePlatformPlan.variantNameTable4
                xidValvePlatformPlanEntity.variantNameTable5 = it.xidValvePlatformPlan.variantNameTable5
                xidValvePlatformPlanEntity.variantNameTable6 = it.xidValvePlatformPlan.variantNameTable6
                xidValvePlatformPlanEntity.responsibleId = it.xidValvePlatformPlan.responsibleId
                xidValvePlatformPlanEntity.responsibleName = it.xidValvePlatformPlan.responsibleName
                xidValvePlatformPlanEntity.responsibleToken = it.xidValvePlatformPlan.responsibleToken
                xidValvePlatformPlanEntity.revisionId = it.xidValvePlatformPlan.revisionId
                xidValvePlatformPlanEntity.revisionMotivation = it.xidValvePlatformPlan.revisionMotivation
                xidValvePlatformPlanEntity.revisionMotivationObjectId = it.xidValvePlatformPlan.revisionMotivationObjectId
                xidValvePlatformPlanEntity.revisionObjectMotivation = it.xidValvePlatformPlan.revisionObjectMotivation
                xidValvePlatformPlanEntity.revisionObjectObservation = it.xidValvePlatformPlan.revisionObjectObservation
                xidValvePlatformPlanEntity.versionDatabase = it.xidValvePlatformPlan.versionDatabase
                xidValvePlatformPlanEntity.xid = it.xidValvePlatformPlan.xid
                xidValvePlatformPlanEntity.platformId = it.xidValvePlatformPlan.platformId
                xidValvePlatformPlanEntity.platform = it.xidValvePlatformPlan.platform
                xidValvePlatformPlanEntity.genericAuxInfo1 = it.xidValvePlatformPlan.genericAuxInfo1
                xidValvePlatformPlanEntity.genericAuxInfo2 = it.xidValvePlatformPlan.genericAuxInfo2
                xidValvePlatformPlanEntity.genericAuxInfo3 = it.xidValvePlatformPlan.genericAuxInfo3
                xidValvePlatformPlanEntity.genericAuxInfo4 = it.xidValvePlatformPlan.genericAuxInfo4
                xidValvePlatformPlanEntity.genericAuxIdentification1 = it.xidValvePlatformPlan.genericAuxIdentification1
                xidValvePlatformPlanEntity.genericAuxIdentification2 = it.xidValvePlatformPlan.genericAuxIdentification2
                xidValvePlatformPlanEntity.genericAuxIdentification3 = it.xidValvePlatformPlan.genericAuxIdentification3
                xidValvePlatformPlanEntity.genericAuxIdentification4 = it.xidValvePlatformPlan.genericAuxIdentification4
                xidValvePlatformPlanEntity.forAnythingExtra1 = it.xidValvePlatformPlan.forAnythingExtra1
                xidValvePlatformPlanEntity.forAnythingExtra2 = it.xidValvePlatformPlan.forAnythingExtra2
                xidValvePlatformPlanEntity.forAnythingExtra3 = it.xidValvePlatformPlan.forAnythingExtra3
                xidValvePlatformPlanEntity.forAnythingExtra4 = it.xidValvePlatformPlan.forAnythingExtra4
                xidValvePlatformPlanEntity.backupDatabase = it.xidValvePlatformPlan.backupDatabase
                xidValvePlatformPlanEntity.betaDatabaseReleased = it.xidValvePlatformPlan.betaDatabaseReleased
                xidValvePlatformPlanEntity.developmentDatabaseReleased = it.xidValvePlatformPlan.developmentDatabaseReleased
                xidValvePlatformPlanEntity.experimentalDatabaseReleased = it.xidValvePlatformPlan.experimentalDatabaseReleased
                xidValvePlatformPlanEntity.officialDatabaseReleased = it.xidValvePlatformPlan.officialDatabaseReleased
                xidValvePlatformPlanEntity.other1DatabaseReleased = it.xidValvePlatformPlan.other1DatabaseReleased
                xidValvePlatformPlanEntity.other2DatabaseReleased = it.xidValvePlatformPlan.other2DatabaseReleased

                if (it.xidValvePlatformPlan.action == ValueDefault.SRT_REMOVIDO
                    || it.xidValvePlatformPlan.action == ValueDefault.SRT_REMOVED
                    || it.xidValvePlatformPlan.action == ValueDefault.SRT_DELETADO
                    || it.xidValvePlatformPlan.action == ValueDefault.SRT_DELETED
                    || it.xidValvePlatformPlan.actionNumber == ValueDefault.REMOVIDO
                    || it.xidValvePlatformPlan.actionNumber == ValueDefault.REMOVED
                    || it.xidValvePlatformPlan.actionNumber == ValueDefault.DELETADO
                    || it.xidValvePlatformPlan.actionNumber == ValueDefault.DELETED
                ) {

                    val valvePlatformPlanEntityAuxList: List<ValvePlatformPlanEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlatformPlanDao()?.findByObjectCompositeSelection(
                            valvePlatformPlanEntity.vplValId,
                            valvePlatformPlanEntity.valvePlatformId,
                            valvePlatformPlanEntity.planTestValveId
                        )

                    val xidValvePlatformPlanEntityList: List<XidValvePlatformPlanEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformPlanDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                valvePlatformPlanEntity.planTestValveId.toString(),
                                valvePlatformPlanEntity.valvePlatformId.toString(),
                                valvePlatformPlanEntity.planTestValveId.toString()
                            )

                    if (!valvePlatformPlanEntityAuxList.isNullOrEmpty()) {
                        valvePlatformPlanEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.valvePlatformPlanDao()?.delete(it01)
                        }
                    }

                    if (!xidValvePlatformPlanEntityList.isNullOrEmpty()) {
                        xidValvePlatformPlanEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformPlanDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var valvePlatformPlanAuxEntity: ValvePlatformPlanEntity? = null

                    val valvePlatformPlanAuxListEntity: List<ValvePlatformPlanEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlatformPlanDao()?.findByObjectCompositeSelection(
                            valvePlatformPlanEntity.vplValId,
                            valvePlatformPlanEntity.valvePlatformId,
                            valvePlatformPlanEntity.planTestValveId
                        )

                    if (!valvePlatformPlanAuxListEntity.isNullOrEmpty()
                        && valvePlatformPlanAuxListEntity.size >= 2
                    ) {
                        valvePlatformPlanAuxListEntity.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.valvePlatformPlanDao()?.delete(it1)
                        }
                    } else if (!valvePlatformPlanAuxListEntity.isNullOrEmpty()) {
                        valvePlatformPlanAuxEntity =
                            valvePlatformPlanAuxListEntity[0]
                    }

                    //delay(10)

                    if (valvePlatformPlanAuxEntity == null
                        || (valvePlatformPlanAuxEntity != null
                                && valvePlatformPlanAuxEntity.vplValId == 0
                                && valvePlatformPlanAuxEntity.valvePlatformId == 0
                                && valvePlatformPlanAuxEntity.planTestValveId == 0
                                )
                    ) {
                        existInDatabase = true
                        bInsert = true
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlatformPlanDao()?.insert(valvePlatformPlanEntity)
                    } else if (valvePlatformPlanAuxEntity.vplValId != 0
                        && valvePlatformPlanAuxEntity.valvePlatformId != 0
                        && valvePlatformPlanAuxEntity.planTestValveId != 0
                    ) {
                        existInDatabase = true
                        bUpdate = true
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlatformPlanDao()?.update(valvePlatformPlanEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformPlanDao()?.insert(xidValvePlatformPlanEntity)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformPlanDao()?.update(xidValvePlatformPlanEntity)
                        }
                    }
                }
                if (it.xidValvePlatformPlan.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidValvePlatformPlan.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else if (maxXidCurrent == maxXid()) {
                    maxXidCurrent = (maxXidCurrent + 1)
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_INJECTOR_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                }
                if (it.xidValvePlatformPlan.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidValvePlatformPlan.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformPlanDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID,
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
        return AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformPlanDao()?.maxXid()
            ?: GlobalUtil.method.recoverSharedPreferences(
                spConfiguration,
                PARAMETER_VALVE_PLATFORM_PLAN_CURRENT_XID
            )?.toInt() ?: 0
    }
}