package br.com.tecnomotor.marvin.repository.sensor

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.api.v1.model.sensor.PlanTestSensorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorTestPlanEntity
import br.com.tecnomotor.marvin.dao.entities.sensor.XidPlanTestSensorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class SensorPlanTestRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<SensorTestPlanEntity>?>>()
    private val mediatorIndividually = MediatorLiveData<Resource<SensorTestPlanEntity?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findByPlanTestSensorUsingIdPlanIndividuallyLiveData(id: Int): LiveData<SensorTestPlanEntity?> {
        return mAppDatabase?.sensorPlanTestDao()!!.findByPlanTestSensorUsingIdPlanLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<PlanTestSensorSynchronize>) {
        try {
            list.forEach {
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID
                )!!.toInt()
                var existInDatabase = false
                val sensorTestPlanEntity = SensorTestPlanEntity()

                sensorTestPlanEntity.id = it.planTestSensor.id
                sensorTestPlanEntity.typePlanId = it.planTestSensor.typePlanTest!!.id
                sensorTestPlanEntity.descriptionTypePlan = it.planTestSensor.typePlanTest!!.description
                sensorTestPlanEntity.pressure1 = it.planTestSensor.pressure1
                sensorTestPlanEntity.pressure2 = it.planTestSensor.pressure2
                sensorTestPlanEntity.pressure3 = it.planTestSensor.pressure3
                sensorTestPlanEntity.pressure4 = it.planTestSensor.pressure4
                sensorTestPlanEntity.pressure5 = it.planTestSensor.pressure5
                sensorTestPlanEntity.limit1Min = it.planTestSensor.limit1Min.toDouble()
                sensorTestPlanEntity.limit1Max = it.planTestSensor.limit1Max.toDouble()
                sensorTestPlanEntity.limit2Min = it.planTestSensor.limit2Min.toDouble()
                sensorTestPlanEntity.limit2Max = it.planTestSensor.limit2Max.toDouble()
                sensorTestPlanEntity.limit3Min = it.planTestSensor.limit3Min.toDouble()
                sensorTestPlanEntity.limit3Max = it.planTestSensor.limit3Max.toDouble()
                sensorTestPlanEntity.limit4Min = it.planTestSensor.limit4Min.toDouble()
                sensorTestPlanEntity.limit4Max = it.planTestSensor.limit4Max.toDouble()
                sensorTestPlanEntity.limit5Min = it.planTestSensor.limit5Min.toDouble()
                sensorTestPlanEntity.limit5Max = it.planTestSensor.limit5Max.toDouble()
                sensorTestPlanEntity.testTime = it.planTestSensor.testTime
                sensorTestPlanEntity.numberPoints = it.planTestSensor.numberPoints
                sensorTestPlanEntity.token = it.planTestSensor.token
                sensorTestPlanEntity.revisionEntityId = it.planTestSensor.revision?.id ?: 0
                sensorTestPlanEntity.deleted = it.planTestSensor.deleted

                val xidPlanTestSensor = XidPlanTestSensorEntity()
                xidPlanTestSensor.id = it.xidPlanTestSensor.id
                xidPlanTestSensor.action = it.xidPlanTestSensor.action
                xidPlanTestSensor.actionNumber = it.xidPlanTestSensor.actionNumber
                xidPlanTestSensor.brand = it.xidPlanTestSensor.brand
                xidPlanTestSensor.brandId = it.xidPlanTestSensor.brandId
                xidPlanTestSensor.classResponsible = it.xidPlanTestSensor.classResponsible
                xidPlanTestSensor.createdDateObject = it.xidPlanTestSensor.createdDateObject
                xidPlanTestSensor.identification = it.xidPlanTestSensor.identification
                xidPlanTestSensor.identificationAux = it.xidPlanTestSensor.identificationAux
                xidPlanTestSensor.lastDateUpdate = it.xidPlanTestSensor.lastDateUpdate
                xidPlanTestSensor.objectCompositionId = it.xidPlanTestSensor.objectCompositionId
                xidPlanTestSensor.objectId = it.xidPlanTestSensor.objectId
                xidPlanTestSensor.variantNameTable1 = it.xidPlanTestSensor.variantNameTable1
                xidPlanTestSensor.variantNameTable2 = it.xidPlanTestSensor.variantNameTable2
                xidPlanTestSensor.variantNameTable3 = it.xidPlanTestSensor.variantNameTable3
                xidPlanTestSensor.variantNameTable4 = it.xidPlanTestSensor.variantNameTable4
                xidPlanTestSensor.variantNameTable5 = it.xidPlanTestSensor.variantNameTable5
                xidPlanTestSensor.variantNameTable6 = it.xidPlanTestSensor.variantNameTable6
                xidPlanTestSensor.responsibleId = it.xidPlanTestSensor.responsibleId
                xidPlanTestSensor.responsibleName = it.xidPlanTestSensor.responsibleName
                xidPlanTestSensor.responsibleToken = it.xidPlanTestSensor.responsibleToken
                xidPlanTestSensor.revisionId = it.xidPlanTestSensor.revisionId
                xidPlanTestSensor.revisionMotivation = it.xidPlanTestSensor.revisionMotivation
                xidPlanTestSensor.revisionMotivationObjectId = it.xidPlanTestSensor.revisionMotivationObjectId
                xidPlanTestSensor.revisionObjectMotivation = it.xidPlanTestSensor.revisionObjectMotivation
                xidPlanTestSensor.revisionObjectObservation = it.xidPlanTestSensor.revisionObjectObservation
                xidPlanTestSensor.versionDatabase = it.xidPlanTestSensor.versionDatabase
                xidPlanTestSensor.xid = it.xidPlanTestSensor.xid
                xidPlanTestSensor.platformId = it.xidPlanTestSensor.platformId
                xidPlanTestSensor.platform = it.xidPlanTestSensor.platform
                xidPlanTestSensor.genericAuxInfo1 = it.xidPlanTestSensor.genericAuxInfo1
                xidPlanTestSensor.genericAuxInfo2 = it.xidPlanTestSensor.genericAuxInfo2
                xidPlanTestSensor.genericAuxInfo3 = it.xidPlanTestSensor.genericAuxInfo3
                xidPlanTestSensor.genericAuxInfo4 = it.xidPlanTestSensor.genericAuxInfo4
                xidPlanTestSensor.genericAuxIdentification1 = it.xidPlanTestSensor.genericAuxIdentification1
                xidPlanTestSensor.genericAuxIdentification2 = it.xidPlanTestSensor.genericAuxIdentification2
                xidPlanTestSensor.genericAuxIdentification3 = it.xidPlanTestSensor.genericAuxIdentification3
                xidPlanTestSensor.genericAuxIdentification4 = it.xidPlanTestSensor.genericAuxIdentification4
                xidPlanTestSensor.forAnythingExtra1 = it.xidPlanTestSensor.forAnythingExtra1
                xidPlanTestSensor.forAnythingExtra2 = it.xidPlanTestSensor.forAnythingExtra2
                xidPlanTestSensor.forAnythingExtra3 = it.xidPlanTestSensor.forAnythingExtra3
                xidPlanTestSensor.forAnythingExtra4 = it.xidPlanTestSensor.forAnythingExtra4
                xidPlanTestSensor.backupDatabase = it.xidPlanTestSensor.backupDatabase
                xidPlanTestSensor.betaDatabaseReleased = it.xidPlanTestSensor.betaDatabaseReleased
                xidPlanTestSensor.developmentDatabaseReleased = it.xidPlanTestSensor.developmentDatabaseReleased
                xidPlanTestSensor.experimentalDatabaseReleased = it.xidPlanTestSensor.experimentalDatabaseReleased
                xidPlanTestSensor.officialDatabaseReleased = it.xidPlanTestSensor.officialDatabaseReleased
                xidPlanTestSensor.other1DatabaseReleased = it.xidPlanTestSensor.other1DatabaseReleased
                xidPlanTestSensor.other2DatabaseReleased = it.xidPlanTestSensor.other2DatabaseReleased

                if (it.xidPlanTestSensor.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPlanTestSensor.action == ValueDefault.SRT_REMOVED
                    || it.xidPlanTestSensor.action == ValueDefault.SRT_DELETADO
                    || it.xidPlanTestSensor.action == ValueDefault.SRT_DELETED
                    || it.xidPlanTestSensor.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPlanTestSensor.actionNumber == ValueDefault.REMOVED
                    || it.xidPlanTestSensor.actionNumber == ValueDefault.DELETADO
                    || it.xidPlanTestSensor.actionNumber == ValueDefault.DELETED
                ) {

                    val sensorPlanTestEntityAuxList: List<SensorTestPlanEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlanTestDao()?.findByIdList(it.planTestSensor.id)

                    val xidSensorTestPlanEntityList: List<XidPlanTestSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestSensorDao()
                            ?.findByObjectIdList(it.planTestSensor.id.toString())

                    if (!sensorPlanTestEntityAuxList.isNullOrEmpty()) {
                        sensorPlanTestEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.sensorPlanTestDao()?.delete(it01)
                        }
                    }

                    if (!xidSensorTestPlanEntityList.isNullOrEmpty()) {
                        xidSensorTestPlanEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestSensorDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var sensorAuxPlanTestEntity: SensorTestPlanEntity? = null

                    val sensorPlanTestEntityAuxList: List<SensorTestPlanEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlanTestDao()?.findByIdList(it.planTestSensor.id)

                    if (!sensorPlanTestEntityAuxList.isNullOrEmpty()
                        && sensorPlanTestEntityAuxList.size >= 2
                    ) {
                        sensorPlanTestEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.sensorPlanTestDao()?.delete(it1)
                        }
                    } else if (!sensorPlanTestEntityAuxList.isNullOrEmpty()) {
                        sensorAuxPlanTestEntity = sensorPlanTestEntityAuxList[0]
                    }

                    //delay(10)

                    if (sensorAuxPlanTestEntity == null
                        || (sensorAuxPlanTestEntity != null
                                && sensorAuxPlanTestEntity.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlanTestDao()?.insert(sensorTestPlanEntity)
                    } else if (sensorAuxPlanTestEntity.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        sensorTestPlanEntity.id = sensorAuxPlanTestEntity.id

                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlanTestDao()?.update(sensorTestPlanEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {

                            AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestSensorDao()?.insert(xidPlanTestSensor)
                        } else if (bUpdate) {

                            AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestSensorDao()?.update(xidPlanTestSensor)
                        }
                    }
                }
                if (it.xidPlanTestSensor.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPlanTestSensor.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestSensorDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID,
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
            PARAMETER_SENSOR_PLAN_TEST_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidPlanTestSensorDao()?.maxXid()
            ?: 0
    }
}