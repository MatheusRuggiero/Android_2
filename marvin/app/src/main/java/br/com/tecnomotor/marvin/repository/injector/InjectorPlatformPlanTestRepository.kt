package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.injector.InjectorPlatformPlanTestSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlatformPlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.injector.XidInjectorPlatformPlanTestEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class InjectorPlatformPlanTestRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<InjectorPlatformPlanTestEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }


    fun findInjectorPlatformPlanTestUsingIdInjectorListLiveDataResource(platId: Int, injId: Int): LiveData<Resource<List<InjectorPlatformPlanTestEntity>?>> {
        mediator.addSource(findplatformPlanTestByInjectorId(platId, injId)) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<InjectorPlatformPlanTestEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<InjectorPlatformPlanTestEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    private fun findplatformPlanTestByInjectorId(platId: Int, injId: Int): LiveData<List<InjectorPlatformPlanTestEntity>> {
        return mAppDatabase?.injectorPlatformPlanTestDao()!!.findInjectorPlatformPlanTestUsingIdInjectorListLiveData(platId, injId)
    }

    fun saveListObjectSynchronized(list: List<InjectorPlatformPlanTestSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                val injectorPlatformPlanTest = InjectorPlatformPlanTestEntity()
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID
                )!!.toInt()

                injectorPlatformPlanTest.versionId = it.injectorPlatformPlanTest.version.id
                injectorPlatformPlanTest.planTestInjectorId = it.injectorPlatformPlanTest.injectorPlatformPlanPK.planTestInjector.id
                injectorPlatformPlanTest.platformId = it.injectorPlatformPlanTest.injectorPlatformPlanPK.injectorPlatform.injectorPlatformPK.platform.id
                injectorPlatformPlanTest.injId = it.injectorPlatformPlanTest.injectorPlatformPlanPK.injectorPlatform.injectorPlatformPK.injetor.id

                val xidInjectorPlatformPlanTest = XidInjectorPlatformPlanTestEntity()
                xidInjectorPlatformPlanTest.id = it.xidInjectorPlatformPlanTest.id
                xidInjectorPlatformPlanTest.action = it.xidInjectorPlatformPlanTest.action
                xidInjectorPlatformPlanTest.actionNumber = it.xidInjectorPlatformPlanTest.actionNumber
                xidInjectorPlatformPlanTest.brand = it.xidInjectorPlatformPlanTest.brand
                xidInjectorPlatformPlanTest.brandId = it.xidInjectorPlatformPlanTest.brandId
                xidInjectorPlatformPlanTest.classResponsible = it.xidInjectorPlatformPlanTest.classResponsible
                xidInjectorPlatformPlanTest.createdDateObject = it.xidInjectorPlatformPlanTest.createdDateObject
                xidInjectorPlatformPlanTest.identification = it.xidInjectorPlatformPlanTest.identification
                xidInjectorPlatformPlanTest.identificationAux = it.xidInjectorPlatformPlanTest.identificationAux
                xidInjectorPlatformPlanTest.lastDateUpdate = it.xidInjectorPlatformPlanTest.lastDateUpdate
                xidInjectorPlatformPlanTest.objectCompositionId = it.xidInjectorPlatformPlanTest.objectCompositionId
                xidInjectorPlatformPlanTest.objectId = it.xidInjectorPlatformPlanTest.objectId
                xidInjectorPlatformPlanTest.variantNameTable1 = it.xidInjectorPlatformPlanTest.variantNameTable1
                xidInjectorPlatformPlanTest.variantNameTable2 = it.xidInjectorPlatformPlanTest.variantNameTable2
                xidInjectorPlatformPlanTest.variantNameTable3 = it.xidInjectorPlatformPlanTest.variantNameTable3
                xidInjectorPlatformPlanTest.variantNameTable4 = it.xidInjectorPlatformPlanTest.variantNameTable4
                xidInjectorPlatformPlanTest.variantNameTable5 = it.xidInjectorPlatformPlanTest.variantNameTable5
                xidInjectorPlatformPlanTest.variantNameTable6 = it.xidInjectorPlatformPlanTest.variantNameTable6
                xidInjectorPlatformPlanTest.responsibleId = it.xidInjectorPlatformPlanTest.responsibleId
                xidInjectorPlatformPlanTest.responsibleName = it.xidInjectorPlatformPlanTest.responsibleName
                xidInjectorPlatformPlanTest.responsibleToken = it.xidInjectorPlatformPlanTest.responsibleToken
                xidInjectorPlatformPlanTest.revisionId = it.xidInjectorPlatformPlanTest.revisionId
                xidInjectorPlatformPlanTest.revisionMotivation = it.xidInjectorPlatformPlanTest.revisionMotivation
                xidInjectorPlatformPlanTest.revisionMotivationObjectId = it.xidInjectorPlatformPlanTest.revisionMotivationObjectId
                xidInjectorPlatformPlanTest.revisionObjectMotivation = it.xidInjectorPlatformPlanTest.revisionObjectMotivation
                xidInjectorPlatformPlanTest.revisionObjectObservation = it.xidInjectorPlatformPlanTest.revisionObjectObservation
                xidInjectorPlatformPlanTest.versionDatabase = it.xidInjectorPlatformPlanTest.versionDatabase
                xidInjectorPlatformPlanTest.xid = it.xidInjectorPlatformPlanTest.xid
                xidInjectorPlatformPlanTest.platformId = it.xidInjectorPlatformPlanTest.platformId
                xidInjectorPlatformPlanTest.platform = it.xidInjectorPlatformPlanTest.platform
                xidInjectorPlatformPlanTest.genericAuxInfo1 = it.xidInjectorPlatformPlanTest.genericAuxInfo1
                xidInjectorPlatformPlanTest.genericAuxInfo2 = it.xidInjectorPlatformPlanTest.genericAuxInfo2
                xidInjectorPlatformPlanTest.genericAuxInfo3 = it.xidInjectorPlatformPlanTest.genericAuxInfo3
                xidInjectorPlatformPlanTest.genericAuxInfo4 = it.xidInjectorPlatformPlanTest.genericAuxInfo4
                xidInjectorPlatformPlanTest.genericAuxIdentification1 = it.xidInjectorPlatformPlanTest.genericAuxIdentification1
                xidInjectorPlatformPlanTest.genericAuxIdentification2 = it.xidInjectorPlatformPlanTest.genericAuxIdentification2
                xidInjectorPlatformPlanTest.genericAuxIdentification3 = it.xidInjectorPlatformPlanTest.genericAuxIdentification3
                xidInjectorPlatformPlanTest.genericAuxIdentification4 = it.xidInjectorPlatformPlanTest.genericAuxIdentification4
                xidInjectorPlatformPlanTest.forAnythingExtra1 = it.xidInjectorPlatformPlanTest.forAnythingExtra1
                xidInjectorPlatformPlanTest.forAnythingExtra2 = it.xidInjectorPlatformPlanTest.forAnythingExtra2
                xidInjectorPlatformPlanTest.forAnythingExtra3 = it.xidInjectorPlatformPlanTest.forAnythingExtra3
                xidInjectorPlatformPlanTest.forAnythingExtra4 = it.xidInjectorPlatformPlanTest.forAnythingExtra4
                xidInjectorPlatformPlanTest.backupDatabase = it.xidInjectorPlatformPlanTest.backupDatabase
                xidInjectorPlatformPlanTest.betaDatabaseReleased = it.xidInjectorPlatformPlanTest.betaDatabaseReleased
                xidInjectorPlatformPlanTest.developmentDatabaseReleased = it.xidInjectorPlatformPlanTest.developmentDatabaseReleased
                xidInjectorPlatformPlanTest.experimentalDatabaseReleased = it.xidInjectorPlatformPlanTest.experimentalDatabaseReleased
                xidInjectorPlatformPlanTest.officialDatabaseReleased = it.xidInjectorPlatformPlanTest.officialDatabaseReleased
                xidInjectorPlatformPlanTest.other1DatabaseReleased = it.xidInjectorPlatformPlanTest.other1DatabaseReleased
                xidInjectorPlatformPlanTest.other2DatabaseReleased = it.xidInjectorPlatformPlanTest.other2DatabaseReleased

                if (it.xidInjectorPlatformPlanTest.action == ValueDefault.SRT_REMOVIDO
                    || it.xidInjectorPlatformPlanTest.action == ValueDefault.SRT_REMOVED
                    || it.xidInjectorPlatformPlanTest.action == ValueDefault.SRT_DELETADO
                    || it.xidInjectorPlatformPlanTest.action == ValueDefault.SRT_DELETED
                    || it.xidInjectorPlatformPlanTest.actionNumber == ValueDefault.REMOVIDO
                    || it.xidInjectorPlatformPlanTest.actionNumber == ValueDefault.REMOVED
                    || it.xidInjectorPlatformPlanTest.actionNumber == ValueDefault.DELETADO
                    || it.xidInjectorPlatformPlanTest.actionNumber == ValueDefault.DELETED
                ) {

                    val injectorPlatformPlanAuxListTestEntity: List<InjectorPlatformPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformPlanTestDao()?.findByObjectCompositeSelection(
                            injectorPlatformPlanTest.injId,
                            injectorPlatformPlanTest.platformId,
                            injectorPlatformPlanTest.planTestInjectorId
                        )


                    val xidInjectorPlatformPlanEntityList: List<XidInjectorPlatformPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xIdInjectorPlatformPlanTestDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                injectorPlatformPlanTest.planTestInjectorId.toString(),
                                injectorPlatformPlanTest.platformId.toString(),
                                injectorPlatformPlanTest.injId.toString()
                            )

                    if (!injectorPlatformPlanAuxListTestEntity.isNullOrEmpty()) {
                        injectorPlatformPlanAuxListTestEntity.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformPlanTestDao()?.delete(it01)
                        }
                    }

                    if (!xidInjectorPlatformPlanEntityList.isNullOrEmpty()) {
                        xidInjectorPlatformPlanEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xIdInjectorPlatformPlanTestDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var injectorPlatformPlanAuxTestEntity: InjectorPlatformPlanTestEntity? = null

                    val injectorPlatformPlanAuxListTestEntity: List<InjectorPlatformPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformPlanTestDao()?.findByObjectCompositeSelection(
                            injectorPlatformPlanTest.injId,
                            injectorPlatformPlanTest.platformId,
                            injectorPlatformPlanTest.planTestInjectorId
                        )

                    if (!injectorPlatformPlanAuxListTestEntity.isNullOrEmpty()
                        && injectorPlatformPlanAuxListTestEntity.size >= 2
                    ) {
                        injectorPlatformPlanAuxListTestEntity.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformPlanTestDao()?.delete(it1)
                        }
                    } else if (!injectorPlatformPlanAuxListTestEntity.isNullOrEmpty()) {
                        injectorPlatformPlanAuxTestEntity = injectorPlatformPlanAuxListTestEntity[0]
                    }

                    //delay(10)

                    if (injectorPlatformPlanAuxTestEntity == null
                        || (injectorPlatformPlanAuxTestEntity != null
                                && injectorPlatformPlanAuxTestEntity.platformId == 0
                                && injectorPlatformPlanAuxTestEntity.planTestInjectorId == 0
                                && injectorPlatformPlanAuxTestEntity.injId == 0)
                    ) {
                        existInDatabase = true
                        bInsert = true
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformPlanTestDao()?.insert(injectorPlatformPlanTest)
                    } else {
                        existInDatabase = true
                        bUpdate = true
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformPlanTestDao()?.update(injectorPlatformPlanTest)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xIdInjectorPlatformPlanTestDao()
                                ?.insert(xidInjectorPlatformPlanTest)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xIdInjectorPlatformPlanTestDao()
                                ?.update(xidInjectorPlatformPlanTest)
                        }
                    }
                }

                if (it.xidInjectorPlatformPlanTest.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidInjectorPlatformPlanTest.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xIdInjectorPlatformPlanTestDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID,
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
            PARAMETER_INJECTOR_PLATFORM_PLAN_TEST_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xIdInjectorPlatformPlanTestDao()?.maxXid()
            ?: 0
    }

    fun findByIdInjectorPlatformPlanTestList(id: Int): MutableList<InjectorPlatformPlanTestEntity> {
        return mAppDatabase?.injectorPlatformPlanTestDao()!!.findByIdInjectorPlatformPlanTestList(id)
    }
}