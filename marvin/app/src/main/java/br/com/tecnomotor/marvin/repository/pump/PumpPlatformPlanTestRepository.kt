package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.pump.PumpPlatformPlanSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPlatformPlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidPumpPlatformPlanEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class PumpPlatformPlanTestRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<PumpPlatformPlanTestEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }


    fun findPlatformPlanTestByPumpIdLiveDataResource(platId: Int, pumpId: Int): LiveData<Resource<List<PumpPlatformPlanTestEntity>?>> {
        mediator.addSource(findPlatformPlanTestByPumpId(platId, pumpId)) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<PumpPlatformPlanTestEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<PumpPlatformPlanTestEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    private fun findPlatformPlanTestByPumpId(platId: Int, pumpId: Int): LiveData<List<PumpPlatformPlanTestEntity>> {
        return mAppDatabase?.pumpPlatformPlanTestDao()!!.findPlatformPlanTestByPumpIdLiveData(platId, pumpId)
    }

    fun saveListObjectSynchronized(list: List<PumpPlatformPlanSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                val pumpPlatformPlanTest = PumpPlatformPlanTestEntity()
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID
                )!!.toInt()

                pumpPlatformPlanTest.versionId = it.pumpPlatformPlanTest.version.id
                pumpPlatformPlanTest.bppPplBomId = it.pumpPlatformPlanTest.pumpPlatformPlanPK.pumpPlatform.pumpPlatformPK!!.pump.id
                pumpPlatformPlanTest.pumpPlanTestId = it.pumpPlatformPlanTest.pumpPlatformPlanPK.planTestPump.id
                pumpPlatformPlanTest.platformPumpId = it.pumpPlatformPlanTest.pumpPlatformPlanPK.pumpPlatform.pumpPlatformPK!!.platform.id
                
                val xidPumpPlatformPlanEntity = XidPumpPlatformPlanEntity()
                xidPumpPlatformPlanEntity.id = it.xidPumpPlatformPlan.id
                xidPumpPlatformPlanEntity.action = it.xidPumpPlatformPlan.action
                xidPumpPlatformPlanEntity.actionNumber = it.xidPumpPlatformPlan.actionNumber
                xidPumpPlatformPlanEntity.brand = it.xidPumpPlatformPlan.brand
                xidPumpPlatformPlanEntity.brandId = it.xidPumpPlatformPlan.brandId
                xidPumpPlatformPlanEntity.classResponsible = it.xidPumpPlatformPlan.classResponsible
                xidPumpPlatformPlanEntity.createdDateObject = it.xidPumpPlatformPlan.createdDateObject
                xidPumpPlatformPlanEntity.identification = it.xidPumpPlatformPlan.identification
                xidPumpPlatformPlanEntity.identificationAux = it.xidPumpPlatformPlan.identificationAux
                xidPumpPlatformPlanEntity.lastDateUpdate = it.xidPumpPlatformPlan.lastDateUpdate
                xidPumpPlatformPlanEntity.objectCompositionId = it.xidPumpPlatformPlan.objectCompositionId
                xidPumpPlatformPlanEntity.objectId = it.xidPumpPlatformPlan.objectId
                xidPumpPlatformPlanEntity.variantNameTable1 = it.xidPumpPlatformPlan.variantNameTable1
                xidPumpPlatformPlanEntity.variantNameTable2 = it.xidPumpPlatformPlan.variantNameTable2
                xidPumpPlatformPlanEntity.variantNameTable3 = it.xidPumpPlatformPlan.variantNameTable3
                xidPumpPlatformPlanEntity.variantNameTable4 = it.xidPumpPlatformPlan.variantNameTable4
                xidPumpPlatformPlanEntity.variantNameTable5 = it.xidPumpPlatformPlan.variantNameTable5
                xidPumpPlatformPlanEntity.variantNameTable6 = it.xidPumpPlatformPlan.variantNameTable6
                xidPumpPlatformPlanEntity.responsibleId = it.xidPumpPlatformPlan.responsibleId
                xidPumpPlatformPlanEntity.responsibleName = it.xidPumpPlatformPlan.responsibleName
                xidPumpPlatformPlanEntity.responsibleToken = it.xidPumpPlatformPlan.responsibleToken
                xidPumpPlatformPlanEntity.revisionId = it.xidPumpPlatformPlan.revisionId
                xidPumpPlatformPlanEntity.revisionMotivation = it.xidPumpPlatformPlan.revisionMotivation
                xidPumpPlatformPlanEntity.revisionMotivationObjectId = it.xidPumpPlatformPlan.revisionMotivationObjectId
                xidPumpPlatformPlanEntity.revisionObjectMotivation = it.xidPumpPlatformPlan.revisionObjectMotivation
                xidPumpPlatformPlanEntity.revisionObjectObservation = it.xidPumpPlatformPlan.revisionObjectObservation
                xidPumpPlatformPlanEntity.versionDatabase = it.xidPumpPlatformPlan.versionDatabase
                xidPumpPlatformPlanEntity.xid = it.xidPumpPlatformPlan.xid
                xidPumpPlatformPlanEntity.platformId = it.xidPumpPlatformPlan.platformId
                xidPumpPlatformPlanEntity.platform = it.xidPumpPlatformPlan.platform
                xidPumpPlatformPlanEntity.genericAuxInfo1 = it.xidPumpPlatformPlan.genericAuxInfo1
                xidPumpPlatformPlanEntity.genericAuxInfo2 = it.xidPumpPlatformPlan.genericAuxInfo2
                xidPumpPlatformPlanEntity.genericAuxInfo3 = it.xidPumpPlatformPlan.genericAuxInfo3
                xidPumpPlatformPlanEntity.genericAuxInfo4 = it.xidPumpPlatformPlan.genericAuxInfo4
                xidPumpPlatformPlanEntity.genericAuxIdentification1 = it.xidPumpPlatformPlan.genericAuxIdentification1
                xidPumpPlatformPlanEntity.genericAuxIdentification2 = it.xidPumpPlatformPlan.genericAuxIdentification2
                xidPumpPlatformPlanEntity.genericAuxIdentification3 = it.xidPumpPlatformPlan.genericAuxIdentification3
                xidPumpPlatformPlanEntity.genericAuxIdentification4 = it.xidPumpPlatformPlan.genericAuxIdentification4
                xidPumpPlatformPlanEntity.forAnythingExtra1 = it.xidPumpPlatformPlan.forAnythingExtra1
                xidPumpPlatformPlanEntity.forAnythingExtra2 = it.xidPumpPlatformPlan.forAnythingExtra2
                xidPumpPlatformPlanEntity.forAnythingExtra3 = it.xidPumpPlatformPlan.forAnythingExtra3
                xidPumpPlatformPlanEntity.forAnythingExtra4 = it.xidPumpPlatformPlan.forAnythingExtra4
                xidPumpPlatformPlanEntity.backupDatabase = it.xidPumpPlatformPlan.backupDatabase
                xidPumpPlatformPlanEntity.betaDatabaseReleased = it.xidPumpPlatformPlan.betaDatabaseReleased
                xidPumpPlatformPlanEntity.developmentDatabaseReleased = it.xidPumpPlatformPlan.developmentDatabaseReleased
                xidPumpPlatformPlanEntity.experimentalDatabaseReleased = it.xidPumpPlatformPlan.experimentalDatabaseReleased
                xidPumpPlatformPlanEntity.officialDatabaseReleased = it.xidPumpPlatformPlan.officialDatabaseReleased
                xidPumpPlatformPlanEntity.other1DatabaseReleased = it.xidPumpPlatformPlan.other1DatabaseReleased
                xidPumpPlatformPlanEntity.other2DatabaseReleased = it.xidPumpPlatformPlan.other2DatabaseReleased

                if (it.xidPumpPlatformPlan.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPumpPlatformPlan.action == ValueDefault.SRT_REMOVED
                    || it.xidPumpPlatformPlan.action == ValueDefault.SRT_DELETADO
                    || it.xidPumpPlatformPlan.action == ValueDefault.SRT_DELETED
                    || it.xidPumpPlatformPlan.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPumpPlatformPlan.actionNumber == ValueDefault.REMOVED
                    || it.xidPumpPlatformPlan.actionNumber == ValueDefault.DELETADO
                    || it.xidPumpPlatformPlan.actionNumber == ValueDefault.DELETED
                ) {

                    val pumpPlatformPlanTestEntityAuxList: List<PumpPlatformPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformPlanTestDao()?.findByObjectCompositeSelection(
                            pumpPlatformPlanTest.bppPplBomId,
                            pumpPlatformPlanTest.platformPumpId,
                            pumpPlatformPlanTest.pumpPlanTestId
                        )

                    val xidPumpPlatformPlanTestEntityList: List<XidPumpPlatformPlanEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformPlanTestDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                pumpPlatformPlanTest.pumpPlanTestId.toString(),
                                pumpPlatformPlanTest.platformPumpId.toString(),
                                pumpPlatformPlanTest.bppPplBomId.toString()
                            )

                    if (!pumpPlatformPlanTestEntityAuxList.isNullOrEmpty()) {
                        pumpPlatformPlanTestEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformPlanTestDao()?.delete(it01)
                        }
                    }

                    if (!xidPumpPlatformPlanTestEntityList.isNullOrEmpty()) {
                        xidPumpPlatformPlanTestEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformPlanTestDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var pumpPlatformPlanAuxTestEntity: PumpPlatformPlanTestEntity? = null

                    val pumpPlatformPlanAuxListTestEntity: List<PumpPlatformPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformPlanTestDao()?.findByObjectCompositeSelection(
                            pumpPlatformPlanTest.bppPplBomId,
                            pumpPlatformPlanTest.platformPumpId,
                            pumpPlatformPlanTest.pumpPlanTestId
                        )

                    if (!pumpPlatformPlanAuxListTestEntity.isNullOrEmpty()
                        && pumpPlatformPlanAuxListTestEntity.size >= 2
                    ) {
                        pumpPlatformPlanAuxListTestEntity.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformPlanTestDao()?.delete(it1)
                        }
                    } else if (!pumpPlatformPlanAuxListTestEntity.isNullOrEmpty()) {
                        pumpPlatformPlanAuxTestEntity =
                            pumpPlatformPlanAuxListTestEntity[0]
                    }

                    //delay(10)

                    if (pumpPlatformPlanAuxTestEntity == null
                        || (pumpPlatformPlanAuxTestEntity != null
                                && pumpPlatformPlanAuxTestEntity.bppPplBomId == 0
                                && pumpPlatformPlanAuxTestEntity.platformPumpId == 0
                                && pumpPlatformPlanAuxTestEntity.pumpPlanTestId == 0)
                    ) {
                        existInDatabase = true
                        bInsert = true
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformPlanTestDao()?.insert(pumpPlatformPlanTest)
                    } else if (
                        pumpPlatformPlanAuxTestEntity.bppPplBomId != 0
                        && pumpPlatformPlanAuxTestEntity.platformPumpId != 0
                        && pumpPlatformPlanAuxTestEntity.pumpPlanTestId != 0
                    ) {
                        existInDatabase = true
                        bUpdate = true
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformPlanTestDao()?.update(pumpPlatformPlanTest)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformPlanTestDao()
                                ?.insert(xidPumpPlatformPlanEntity)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformPlanTestDao()
                                ?.update(xidPumpPlatformPlanEntity)
                        }
                    }
                }
                if (it.xidPumpPlatformPlan.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPumpPlatformPlan.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID,
                        maxXidCurrent.toString()
                    )

                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformPlanTestDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID,
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
            PARAMETER_PUMP_PLATFORM_PLAN_TEST_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformPlanTestDao()?.maxXid()
            ?: 0
    }
}