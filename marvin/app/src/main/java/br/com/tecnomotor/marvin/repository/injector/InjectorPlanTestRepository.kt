package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.api.v1.model.injector.PlanTestInjectorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.injector.XidPlanTestInjectorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class InjectorPlanTestRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<InjectorPlanTestEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findByPlanTestInjectorUsingIdPlanIndividuallyLiveData(id: Int): LiveData<InjectorPlanTestEntity?> {
        return mAppDatabase?.injectorPlanTestDao()!!.findByPlanTestInjectorUsingIdPlanLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<PlanTestInjectorSynchronize>) {
        try {
            list.forEach {
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID
                )!!.toInt()
                var existInDatabase = false
                val planTestInjector = InjectorPlanTestEntity()
                planTestInjector.id = it.planTestInjector.id
                planTestInjector.typePlanId = it.planTestInjector.typePlanId
                planTestInjector.token = it.planTestInjector.token
                planTestInjector.typePlanTestOtherId = it.planTestInjector.typePlanTest!!.id
                planTestInjector.encoded = it.planTestInjector.encoded
                planTestInjector.usaAdaptSignal = it.planTestInjector.usaAdaptSignal
                planTestInjector.deleted = it.planTestInjector.deleted
                planTestInjector.revisionId = it.planTestInjector.revision!!.id

                val xidPlanTestInjector = XidPlanTestInjectorEntity()
                xidPlanTestInjector.id = it.xidPlanTestInjector.id
                xidPlanTestInjector.action = it.xidPlanTestInjector.action
                xidPlanTestInjector.actionNumber = it.xidPlanTestInjector.actionNumber
                xidPlanTestInjector.brand = it.xidPlanTestInjector.brand
                xidPlanTestInjector.brandId = it.xidPlanTestInjector.brandId
                xidPlanTestInjector.classResponsible = it.xidPlanTestInjector.classResponsible
                xidPlanTestInjector.createdDateObject = it.xidPlanTestInjector.createdDateObject
                xidPlanTestInjector.identification = it.xidPlanTestInjector.identification
                xidPlanTestInjector.identificationAux = it.xidPlanTestInjector.identificationAux
                xidPlanTestInjector.lastDateUpdate = it.xidPlanTestInjector.lastDateUpdate
                xidPlanTestInjector.objectCompositionId = it.xidPlanTestInjector.objectCompositionId
                xidPlanTestInjector.objectId = it.xidPlanTestInjector.objectId
                xidPlanTestInjector.variantNameTable1 = it.xidPlanTestInjector.variantNameTable1
                xidPlanTestInjector.variantNameTable2 = it.xidPlanTestInjector.variantNameTable2
                xidPlanTestInjector.variantNameTable3 = it.xidPlanTestInjector.variantNameTable3
                xidPlanTestInjector.variantNameTable4 = it.xidPlanTestInjector.variantNameTable4
                xidPlanTestInjector.variantNameTable5 = it.xidPlanTestInjector.variantNameTable5
                xidPlanTestInjector.variantNameTable6 = it.xidPlanTestInjector.variantNameTable6
                xidPlanTestInjector.responsibleId = it.xidPlanTestInjector.responsibleId
                xidPlanTestInjector.responsibleName = it.xidPlanTestInjector.responsibleName
                xidPlanTestInjector.responsibleToken = it.xidPlanTestInjector.responsibleToken
                xidPlanTestInjector.revisionId = it.xidPlanTestInjector.revisionId
                xidPlanTestInjector.revisionMotivation = it.xidPlanTestInjector.revisionMotivation
                xidPlanTestInjector.revisionMotivationObjectId = it.xidPlanTestInjector.revisionMotivationObjectId
                xidPlanTestInjector.revisionObjectMotivation = it.xidPlanTestInjector.revisionObjectMotivation
                xidPlanTestInjector.revisionObjectObservation = it.xidPlanTestInjector.revisionObjectObservation
                xidPlanTestInjector.versionDatabase = it.xidPlanTestInjector.versionDatabase
                xidPlanTestInjector.xid = it.xidPlanTestInjector.xid
                xidPlanTestInjector.platformId = it.xidPlanTestInjector.platformId
                xidPlanTestInjector.platform = it.xidPlanTestInjector.platform
                xidPlanTestInjector.genericAuxInfo1 = it.xidPlanTestInjector.genericAuxInfo1
                xidPlanTestInjector.genericAuxInfo2 = it.xidPlanTestInjector.genericAuxInfo2
                xidPlanTestInjector.genericAuxInfo3 = it.xidPlanTestInjector.genericAuxInfo3
                xidPlanTestInjector.genericAuxInfo4 = it.xidPlanTestInjector.genericAuxInfo4
                xidPlanTestInjector.genericAuxIdentification1 = it.xidPlanTestInjector.genericAuxIdentification1
                xidPlanTestInjector.genericAuxIdentification2 = it.xidPlanTestInjector.genericAuxIdentification2
                xidPlanTestInjector.genericAuxIdentification3 = it.xidPlanTestInjector.genericAuxIdentification3
                xidPlanTestInjector.genericAuxIdentification4 = it.xidPlanTestInjector.genericAuxIdentification4
                xidPlanTestInjector.forAnythingExtra1 = it.xidPlanTestInjector.forAnythingExtra1
                xidPlanTestInjector.forAnythingExtra2 = it.xidPlanTestInjector.forAnythingExtra2
                xidPlanTestInjector.forAnythingExtra3 = it.xidPlanTestInjector.forAnythingExtra3
                xidPlanTestInjector.forAnythingExtra4 = it.xidPlanTestInjector.forAnythingExtra4
                xidPlanTestInjector.backupDatabase = it.xidPlanTestInjector.backupDatabase
                xidPlanTestInjector.betaDatabaseReleased = it.xidPlanTestInjector.betaDatabaseReleased
                xidPlanTestInjector.developmentDatabaseReleased = it.xidPlanTestInjector.developmentDatabaseReleased
                xidPlanTestInjector.experimentalDatabaseReleased = it.xidPlanTestInjector.experimentalDatabaseReleased
                xidPlanTestInjector.officialDatabaseReleased = it.xidPlanTestInjector.officialDatabaseReleased
                xidPlanTestInjector.other1DatabaseReleased = it.xidPlanTestInjector.other1DatabaseReleased
                xidPlanTestInjector.other2DatabaseReleased = it.xidPlanTestInjector.other2DatabaseReleased

                if (it.xidPlanTestInjector.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPlanTestInjector.action == ValueDefault.SRT_REMOVED
                    || it.xidPlanTestInjector.action == ValueDefault.SRT_DELETADO
                    || it.xidPlanTestInjector.action == ValueDefault.SRT_DELETED
                    || it.xidPlanTestInjector.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPlanTestInjector.actionNumber == ValueDefault.REMOVED
                    || it.xidPlanTestInjector.actionNumber == ValueDefault.DELETADO
                    || it.xidPlanTestInjector.actionNumber == ValueDefault.DELETED
                ) {

                    val injectorPlanTestEntityAuxList: List<InjectorPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlanTestDao()?.findByIdList(it.planTestInjector.id)

                    val xidInjectorPlanTestEntityList: List<XidPlanTestInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlanTestDao()
                            ?.findByObjectIdList(it.planTestInjector.id.toString())

                    if (!injectorPlanTestEntityAuxList.isNullOrEmpty()) {
                        injectorPlanTestEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.injectorPlanTestDao()?.delete(it01)
                        }
                    }

                    if (!xidInjectorPlanTestEntityList.isNullOrEmpty()) {
                        xidInjectorPlanTestEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlanTestDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var injectorAuxPlanTestEntity: InjectorPlanTestEntity? = null

                    val injectorPlanTestEntityAuxList: List<InjectorPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlanTestDao()?.findByIdList(it.planTestInjector.id)

                    if (!injectorPlanTestEntityAuxList.isNullOrEmpty()
                        && injectorPlanTestEntityAuxList.size >= 2
                    ) {
                        injectorPlanTestEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.injectorPlanTestDao()?.delete(it1)
                        }
                    } else if (!injectorPlanTestEntityAuxList.isNullOrEmpty()) {
                        injectorAuxPlanTestEntity = injectorPlanTestEntityAuxList[0]
                    }

                    //delay(10)

                    if (injectorAuxPlanTestEntity == null
                        || (injectorAuxPlanTestEntity != null
                                && injectorAuxPlanTestEntity.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlanTestDao()?.insert(planTestInjector)
                    } else if (injectorAuxPlanTestEntity.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        planTestInjector.id = injectorAuxPlanTestEntity.id
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlanTestDao()?.update(planTestInjector)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {

                            AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlanTestDao()?.insert(xidPlanTestInjector)
                        } else if (bUpdate) {

                            AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlanTestDao()?.update(xidPlanTestInjector)
                        }
                    }
                }
                if (it.xidPlanTestInjector.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPlanTestInjector.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlanTestDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID,
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
            PARAMETER_INJECTOR_PLAN_TEST_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlanTestDao()?.maxXid()
            ?: 0
    }
}