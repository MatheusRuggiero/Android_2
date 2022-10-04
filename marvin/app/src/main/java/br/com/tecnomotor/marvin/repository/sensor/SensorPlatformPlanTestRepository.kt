package br.com.tecnomotor.marvin.repository.sensor

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.sensor.SensorPlatformPlanSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorPlatformPlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.sensor.XidSensorPlatformPlanEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class SensorPlatformPlanTestRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<SensorPlatformPlanTestEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }


    fun findSensorPlatformPlanTestUsingIdSensorListLiveDataResource(id: Int): LiveData<Resource<List<SensorPlatformPlanTestEntity>?>> {
        mediator.addSource(findSensorPlatformPlanTestUsingIdSensorListLive(id)) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<SensorPlatformPlanTestEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<SensorPlatformPlanTestEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    private fun findSensorPlatformPlanTestUsingIdSensorListLive(id: Int): LiveData<MutableList<SensorPlatformPlanTestEntity>> {
        return mAppDatabase?.sensorPlatformPlanTestDao()!!.findSensorPlatformPlanTestUsingIdSensorListLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<SensorPlatformPlanSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                val sensorPlatformPlanTestEntity = SensorPlatformPlanTestEntity()
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID
                )!!.toInt()

                sensorPlatformPlanTestEntity.versionEntityId = it.sensorPlatformPlanTest.version.id
                sensorPlatformPlanTestEntity.splSenId =
                    it.sensorPlatformPlanTest.sensorPlatformPlanTestPK!!.sensorPlatform!!.sensorPlatformPK.sensor.senId
                sensorPlatformPlanTestEntity.sensorTestPlanEntityId =
                    it.sensorPlatformPlanTest.sensorPlatformPlanTestPK!!.planTestSensor!!.id
                sensorPlatformPlanTestEntity.sensorPlatformEntityId =
                    it.sensorPlatformPlanTest.sensorPlatformPlanTestPK!!.sensorPlatform!!.platform.id
                sensorPlatformPlanTestEntity.sensorPlatformEntityDescription =
                    it.sensorPlatformPlanTest.sensorPlatformPlanTestPK!!.sensorPlatform!!.platform.name.toString()

                val xidSensorPlatformPlanEntity = XidSensorPlatformPlanEntity()
                xidSensorPlatformPlanEntity.id = it.xidSensorPlatformPlan.id
                xidSensorPlatformPlanEntity.action = it.xidSensorPlatformPlan.action
                xidSensorPlatformPlanEntity.actionNumber = it.xidSensorPlatformPlan.actionNumber
                xidSensorPlatformPlanEntity.brand = it.xidSensorPlatformPlan.brand
                xidSensorPlatformPlanEntity.brandId = it.xidSensorPlatformPlan.brandId
                xidSensorPlatformPlanEntity.classResponsible = it.xidSensorPlatformPlan.classResponsible
                xidSensorPlatformPlanEntity.createdDateObject = it.xidSensorPlatformPlan.createdDateObject
                xidSensorPlatformPlanEntity.identification = it.xidSensorPlatformPlan.identification
                xidSensorPlatformPlanEntity.identificationAux = it.xidSensorPlatformPlan.identificationAux
                xidSensorPlatformPlanEntity.lastDateUpdate = it.xidSensorPlatformPlan.lastDateUpdate
                xidSensorPlatformPlanEntity.objectCompositionId = it.xidSensorPlatformPlan.objectCompositionId
                xidSensorPlatformPlanEntity.objectId = it.xidSensorPlatformPlan.objectId
                xidSensorPlatformPlanEntity.variantNameTable1 = it.xidSensorPlatformPlan.variantNameTable1
                xidSensorPlatformPlanEntity.variantNameTable2 = it.xidSensorPlatformPlan.variantNameTable2
                xidSensorPlatformPlanEntity.variantNameTable3 = it.xidSensorPlatformPlan.variantNameTable3
                xidSensorPlatformPlanEntity.variantNameTable4 = it.xidSensorPlatformPlan.variantNameTable4
                xidSensorPlatformPlanEntity.variantNameTable5 = it.xidSensorPlatformPlan.variantNameTable5
                xidSensorPlatformPlanEntity.variantNameTable6 = it.xidSensorPlatformPlan.variantNameTable6
                xidSensorPlatformPlanEntity.responsibleId = it.xidSensorPlatformPlan.responsibleId
                xidSensorPlatformPlanEntity.responsibleName = it.xidSensorPlatformPlan.responsibleName
                xidSensorPlatformPlanEntity.responsibleToken = it.xidSensorPlatformPlan.responsibleToken
                xidSensorPlatformPlanEntity.revisionId = it.xidSensorPlatformPlan.revisionId
                xidSensorPlatformPlanEntity.revisionMotivation = it.xidSensorPlatformPlan.revisionMotivation
                xidSensorPlatformPlanEntity.revisionMotivationObjectId = it.xidSensorPlatformPlan.revisionMotivationObjectId
                xidSensorPlatformPlanEntity.revisionObjectMotivation = it.xidSensorPlatformPlan.revisionObjectMotivation
                xidSensorPlatformPlanEntity.revisionObjectObservation = it.xidSensorPlatformPlan.revisionObjectObservation
                xidSensorPlatformPlanEntity.versionDatabase = it.xidSensorPlatformPlan.versionDatabase
                xidSensorPlatformPlanEntity.xid = it.xidSensorPlatformPlan.xid
                xidSensorPlatformPlanEntity.platformId = it.xidSensorPlatformPlan.platformId
                xidSensorPlatformPlanEntity.platform = it.xidSensorPlatformPlan.platform
                xidSensorPlatformPlanEntity.genericAuxInfo1 = it.xidSensorPlatformPlan.genericAuxInfo1
                xidSensorPlatformPlanEntity.genericAuxInfo2 = it.xidSensorPlatformPlan.genericAuxInfo2
                xidSensorPlatformPlanEntity.genericAuxInfo3 = it.xidSensorPlatformPlan.genericAuxInfo3
                xidSensorPlatformPlanEntity.genericAuxInfo4 = it.xidSensorPlatformPlan.genericAuxInfo4
                xidSensorPlatformPlanEntity.genericAuxIdentification1 = it.xidSensorPlatformPlan.genericAuxIdentification1
                xidSensorPlatformPlanEntity.genericAuxIdentification2 = it.xidSensorPlatformPlan.genericAuxIdentification2
                xidSensorPlatformPlanEntity.genericAuxIdentification3 = it.xidSensorPlatformPlan.genericAuxIdentification3
                xidSensorPlatformPlanEntity.genericAuxIdentification4 = it.xidSensorPlatformPlan.genericAuxIdentification4
                xidSensorPlatformPlanEntity.forAnythingExtra1 = it.xidSensorPlatformPlan.forAnythingExtra1
                xidSensorPlatformPlanEntity.forAnythingExtra2 = it.xidSensorPlatformPlan.forAnythingExtra2
                xidSensorPlatformPlanEntity.forAnythingExtra3 = it.xidSensorPlatformPlan.forAnythingExtra3
                xidSensorPlatformPlanEntity.forAnythingExtra4 = it.xidSensorPlatformPlan.forAnythingExtra4
                xidSensorPlatformPlanEntity.backupDatabase = it.xidSensorPlatformPlan.backupDatabase
                xidSensorPlatformPlanEntity.betaDatabaseReleased = it.xidSensorPlatformPlan.betaDatabaseReleased
                xidSensorPlatformPlanEntity.developmentDatabaseReleased = it.xidSensorPlatformPlan.developmentDatabaseReleased
                xidSensorPlatformPlanEntity.experimentalDatabaseReleased = it.xidSensorPlatformPlan.experimentalDatabaseReleased
                xidSensorPlatformPlanEntity.officialDatabaseReleased = it.xidSensorPlatformPlan.officialDatabaseReleased
                xidSensorPlatformPlanEntity.other1DatabaseReleased = it.xidSensorPlatformPlan.other1DatabaseReleased
                xidSensorPlatformPlanEntity.other2DatabaseReleased = it.xidSensorPlatformPlan.other2DatabaseReleased

                if (it.xidSensorPlatformPlan.action == ValueDefault.SRT_REMOVIDO
                    || it.xidSensorPlatformPlan.action == ValueDefault.SRT_REMOVED
                    || it.xidSensorPlatformPlan.action == ValueDefault.SRT_DELETADO
                    || it.xidSensorPlatformPlan.action == ValueDefault.SRT_DELETED
                    || it.xidSensorPlatformPlan.actionNumber == ValueDefault.REMOVIDO
                    || it.xidSensorPlatformPlan.actionNumber == ValueDefault.REMOVED
                    || it.xidSensorPlatformPlan.actionNumber == ValueDefault.DELETADO
                    || it.xidSensorPlatformPlan.actionNumber == ValueDefault.DELETED
                ) {

                    val sensorPlatformPlanEntityAuxList: List<SensorPlatformPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformPlanTestDao()?.findByObjectCompositeSelection(
                            sensorPlatformPlanTestEntity.splSenId,
                            sensorPlatformPlanTestEntity.sensorPlatformEntityId,
                            sensorPlatformPlanTestEntity.sensorTestPlanEntityId
                        )

                    val xidSensorPlatformPlanTestEntityList: List<XidSensorPlatformPlanEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformPlanTestDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                sensorPlatformPlanTestEntity.sensorTestPlanEntityId.toString(),
                                sensorPlatformPlanTestEntity.sensorPlatformEntityId.toString(),
                                sensorPlatformPlanTestEntity.splSenId.toString()
                            )

                    if (!sensorPlatformPlanEntityAuxList.isNullOrEmpty()) {
                        sensorPlatformPlanEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformPlanTestDao()?.delete(it01)
                        }
                    }

                    if (!xidSensorPlatformPlanTestEntityList.isNullOrEmpty()) {
                        xidSensorPlatformPlanTestEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformPlanTestDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var sensorPlatformPlanAuxTestEntity: SensorPlatformPlanTestEntity? = null

                    val sensorPlatformPlanAuxListTestEntity: List<SensorPlatformPlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformPlanTestDao()?.findByObjectCompositeSelection(
                            sensorPlatformPlanTestEntity.splSenId,
                            sensorPlatformPlanTestEntity.sensorPlatformEntityId,
                            sensorPlatformPlanTestEntity.sensorTestPlanEntityId
                        )

                    if (!sensorPlatformPlanAuxListTestEntity.isNullOrEmpty()
                        && sensorPlatformPlanAuxListTestEntity.size >= 2
                    ) {
                        sensorPlatformPlanAuxListTestEntity.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformPlanTestDao()?.delete(it1)
                        }
                    } else if (!sensorPlatformPlanAuxListTestEntity.isNullOrEmpty()) {
                        sensorPlatformPlanAuxTestEntity =
                            sensorPlatformPlanAuxListTestEntity[0]
                    }

                    //delay(10)

                    if (sensorPlatformPlanAuxTestEntity == null
                        || (sensorPlatformPlanAuxTestEntity != null
                                && sensorPlatformPlanAuxTestEntity.splSenId == 0
                                && sensorPlatformPlanAuxTestEntity.sensorPlatformEntityId == 0
                                && sensorPlatformPlanAuxTestEntity.sensorTestPlanEntityId == 0)
                    ) {
                        existInDatabase = true
                        bInsert = true
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformPlanTestDao()?.insert(sensorPlatformPlanTestEntity)
                    } else if (sensorPlatformPlanAuxTestEntity.splSenId != 0
                        && sensorPlatformPlanAuxTestEntity.sensorPlatformEntityId != 0
                        && sensorPlatformPlanAuxTestEntity.sensorTestPlanEntityId != 0
                    ) {
                        existInDatabase = true
                        bUpdate = true
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformPlanTestDao()?.update(sensorPlatformPlanTestEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformPlanTestDao()
                                ?.insert(xidSensorPlatformPlanEntity)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformPlanTestDao()
                                ?.update(xidSensorPlatformPlanEntity)
                        }
                    }
                }

                if (it.xidSensorPlatformPlan.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidSensorPlatformPlan.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformPlanTestDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID,
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
            PARAMETER_SENSOR_PLATFORM_PLAN_TEST_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformPlanTestDao()?.maxXid()
            ?: 0
    }
}