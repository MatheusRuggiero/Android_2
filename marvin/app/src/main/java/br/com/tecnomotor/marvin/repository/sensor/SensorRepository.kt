package br.com.tecnomotor.marvin.repository.sensor

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.sensor.SensorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorEntity
import br.com.tecnomotor.marvin.dao.entities.sensor.XidSensorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class SensorRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<SensorEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }


    fun findAllSensor(): LiveData<Resource<List<SensorEntity>?>> {
        mediator.addSource(findAll()) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<SensorEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<SensorEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    fun findAll(): LiveData<MutableList<SensorEntity>> {
        return mAppDatabase?.sensorDao()!!.findAllLiveData()
    }

    fun saveListObjectSynchronized(list: List<SensorSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_SENSOR_CURRENT_XID
                )!!.toInt()
                val sensor = SensorEntity()
                sensor.senId = it.sensor.senId
                sensor.senCode = it.sensor.senCode
                sensor.senStandard = it.sensor.senStandard
                sensor.brandEntityId = it.sensor.brand.id
                sensor.senApplication = it.sensor.senApplication
                sensor.senAdaptConnector = it.sensor.senAdaptConnector
                sensor.senAdaptPressure = it.sensor.senAdaptPressure
                sensor.revisionEntityId = it.sensor.revision.id
                sensor.senToken = it.sensor.senToken
                sensor.senDeleted = it.sensor.senDeleted

                val xidSensor = XidSensorEntity()
                xidSensor.id = it.xidSensor.id
                xidSensor.action = it.xidSensor.action
                xidSensor.actionNumber = it.xidSensor.actionNumber
                xidSensor.brand = it.xidSensor.brand
                xidSensor.brandId = it.xidSensor.brandId
                xidSensor.classResponsible = it.xidSensor.classResponsible
                xidSensor.createdDateObject = it.xidSensor.createdDateObject
                xidSensor.identification = it.xidSensor.identification
                xidSensor.identificationAux = it.xidSensor.identificationAux
                xidSensor.lastDateUpdate = it.xidSensor.lastDateUpdate
                xidSensor.objectCompositionId = it.xidSensor.objectCompositionId
                xidSensor.objectId = it.xidSensor.objectId
                xidSensor.variantNameTable1 = it.xidSensor.variantNameTable1
                xidSensor.variantNameTable2 = it.xidSensor.variantNameTable2
                xidSensor.variantNameTable3 = it.xidSensor.variantNameTable3
                xidSensor.variantNameTable4 = it.xidSensor.variantNameTable4
                xidSensor.variantNameTable5 = it.xidSensor.variantNameTable5
                xidSensor.variantNameTable6 = it.xidSensor.variantNameTable6
                xidSensor.responsibleId = it.xidSensor.responsibleId
                xidSensor.responsibleName = it.xidSensor.responsibleName
                xidSensor.responsibleToken = it.xidSensor.responsibleToken
                xidSensor.revisionId = it.xidSensor.revisionId
                xidSensor.revisionMotivation = it.xidSensor.revisionMotivation
                xidSensor.revisionMotivationObjectId = it.xidSensor.revisionMotivationObjectId
                xidSensor.revisionObjectMotivation = it.xidSensor.revisionObjectMotivation
                xidSensor.revisionObjectObservation = it.xidSensor.revisionObjectObservation
                xidSensor.versionDatabase = it.xidSensor.versionDatabase
                xidSensor.xid = it.xidSensor.xid
                xidSensor.platformId = it.xidSensor.platformId
                xidSensor.platform = it.xidSensor.platform
                xidSensor.genericAuxInfo1 = it.xidSensor.genericAuxInfo1
                xidSensor.genericAuxInfo2 = it.xidSensor.genericAuxInfo2
                xidSensor.genericAuxInfo3 = it.xidSensor.genericAuxInfo3
                xidSensor.genericAuxInfo4 = it.xidSensor.genericAuxInfo4
                xidSensor.genericAuxIdentification1 = it.xidSensor.genericAuxIdentification1
                xidSensor.genericAuxIdentification2 = it.xidSensor.genericAuxIdentification2
                xidSensor.genericAuxIdentification3 = it.xidSensor.genericAuxIdentification3
                xidSensor.genericAuxIdentification4 = it.xidSensor.genericAuxIdentification4
                xidSensor.forAnythingExtra1 = it.xidSensor.forAnythingExtra1
                xidSensor.forAnythingExtra2 = it.xidSensor.forAnythingExtra2
                xidSensor.forAnythingExtra3 = it.xidSensor.forAnythingExtra3
                xidSensor.forAnythingExtra4 = it.xidSensor.forAnythingExtra4
                xidSensor.backupDatabase = it.xidSensor.backupDatabase
                xidSensor.betaDatabaseReleased = it.xidSensor.betaDatabaseReleased
                xidSensor.developmentDatabaseReleased = it.xidSensor.developmentDatabaseReleased
                xidSensor.experimentalDatabaseReleased = it.xidSensor.experimentalDatabaseReleased
                xidSensor.officialDatabaseReleased = it.xidSensor.officialDatabaseReleased
                xidSensor.other1DatabaseReleased = it.xidSensor.other1DatabaseReleased
                xidSensor.other2DatabaseReleased = it.xidSensor.other2DatabaseReleased

                if (it.xidSensor.action == ValueDefault.SRT_REMOVIDO
                    || it.xidSensor.action == ValueDefault.SRT_REMOVED
                    || it.xidSensor.action == ValueDefault.SRT_DELETADO
                    || it.xidSensor.action == ValueDefault.SRT_DELETED
                    || it.xidSensor.actionNumber == ValueDefault.REMOVIDO
                    || it.xidSensor.actionNumber == ValueDefault.REMOVED
                    || it.xidSensor.actionNumber == ValueDefault.DELETADO
                    || it.xidSensor.actionNumber == ValueDefault.DELETED
                ) {

                    val sensorEntityAuxList: List<SensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.sensorDao()?.findByIdList(it.sensor.senId)

                    val xidSensorEntityList: List<XidSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidSensorDao()
                            ?.findByObjectIdList(it.sensor.senId.toString())

                    if (!sensorEntityAuxList.isNullOrEmpty()) {
                        sensorEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.sensorDao()?.delete(it01)
                        }
                    }

                    if (!xidSensorEntityList.isNullOrEmpty()) {
                        xidSensorEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidSensorDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var sensorEntityAux: SensorEntity? = null
                    val sensorEntityAuxList: List<SensorEntity>? =
                        mAppDatabase?.sensorDao()?.findByIdList(it.sensor.senId)

                    if (!sensorEntityAuxList.isNullOrEmpty()
                        && sensorEntityAuxList.size >= 2
                    ) {
                        sensorEntityAuxList.forEach { it1 ->
                            mAppDatabase?.sensorDao()?.delete(it1)
                        }
                    } else if (!sensorEntityAuxList.isNullOrEmpty()) {
                        sensorEntityAux = sensorEntityAuxList[0]
                    }

                    //delay(10)

                    if (sensorEntityAux == null
                        || (sensorEntityAux != null
                                && sensorEntityAux.senId == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        mAppDatabase?.sensorDao()?.insert(sensor)
                    } else if (sensorEntityAux.senId != 0) {
                        bUpdate = true
                        existInDatabase = true
                        mAppDatabase?.sensorDao()?.update(sensor)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            mAppDatabase?.xidSensorDao()?.insert(xidSensor)
                        } else if (bUpdate) {
                            mAppDatabase?.xidSensorDao()?.update(xidSensor)
                        }
                    }
                }
                if (it.xidSensor.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidSensor.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_SENSOR_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidSensorDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_SENSOR_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_SENSOR_CURRENT_XID,
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
            PARAMETER_SENSOR_CURRENT_XID
        )?.toInt()
            ?: mAppDatabase?.xidSensorDao()?.maxXid()
            ?: 0
    }


}