package br.com.tecnomotor.marvin.repository.sensor

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.sensor.SensorPlatformSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorPlatformEntity
import br.com.tecnomotor.marvin.dao.entities.sensor.XidSensorPlatformEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_SENSOR_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class SensorPlatformRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<SensorPlatformSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_SENSOR_PLATFORM_CURRENT_XID
                )!!.toInt()
                val sensorPlatform = SensorPlatformEntity()
                sensorPlatform.platformEntityId = it.sensorPlatform.sensorPlatformPK.platform.id
                sensorPlatform.sensorEntityId = it.sensorPlatform.sensorPlatformPK.sensor.senId

                val xidSensorPlatform = XidSensorPlatformEntity()
                xidSensorPlatform.id = it.xidSensorPlatform.id
                xidSensorPlatform.action = it.xidSensorPlatform.action
                xidSensorPlatform.actionNumber = it.xidSensorPlatform.actionNumber
                xidSensorPlatform.brand = it.xidSensorPlatform.brand
                xidSensorPlatform.brandId = it.xidSensorPlatform.brandId
                xidSensorPlatform.classResponsible = it.xidSensorPlatform.classResponsible
                xidSensorPlatform.createdDateObject = it.xidSensorPlatform.createdDateObject
                xidSensorPlatform.identification = it.xidSensorPlatform.identification
                xidSensorPlatform.identificationAux = it.xidSensorPlatform.identificationAux
                xidSensorPlatform.lastDateUpdate = it.xidSensorPlatform.lastDateUpdate
                xidSensorPlatform.objectCompositionId = it.xidSensorPlatform.objectCompositionId
                xidSensorPlatform.objectId = it.xidSensorPlatform.objectId
                xidSensorPlatform.variantNameTable1 = it.xidSensorPlatform.variantNameTable1
                xidSensorPlatform.variantNameTable2 = it.xidSensorPlatform.variantNameTable2
                xidSensorPlatform.variantNameTable3 = it.xidSensorPlatform.variantNameTable3
                xidSensorPlatform.variantNameTable4 = it.xidSensorPlatform.variantNameTable4
                xidSensorPlatform.variantNameTable5 = it.xidSensorPlatform.variantNameTable5
                xidSensorPlatform.variantNameTable6 = it.xidSensorPlatform.variantNameTable6
                xidSensorPlatform.responsibleId = it.xidSensorPlatform.responsibleId
                xidSensorPlatform.responsibleName = it.xidSensorPlatform.responsibleName
                xidSensorPlatform.responsibleToken = it.xidSensorPlatform.responsibleToken
                xidSensorPlatform.revisionId = it.xidSensorPlatform.revisionId
                xidSensorPlatform.revisionMotivation = it.xidSensorPlatform.revisionMotivation
                xidSensorPlatform.revisionMotivationObjectId = it.xidSensorPlatform.revisionMotivationObjectId
                xidSensorPlatform.revisionObjectMotivation = it.xidSensorPlatform.revisionObjectMotivation
                xidSensorPlatform.revisionObjectObservation = it.xidSensorPlatform.revisionObjectObservation
                xidSensorPlatform.versionDatabase = it.xidSensorPlatform.versionDatabase
                xidSensorPlatform.xid = it.xidSensorPlatform.xid
                xidSensorPlatform.platformId = it.xidSensorPlatform.platformId
                xidSensorPlatform.platform = it.xidSensorPlatform.platform
                xidSensorPlatform.genericAuxInfo1 = it.xidSensorPlatform.genericAuxInfo1
                xidSensorPlatform.genericAuxInfo2 = it.xidSensorPlatform.genericAuxInfo2
                xidSensorPlatform.genericAuxInfo3 = it.xidSensorPlatform.genericAuxInfo3
                xidSensorPlatform.genericAuxInfo4 = it.xidSensorPlatform.genericAuxInfo4
                xidSensorPlatform.genericAuxIdentification1 = it.xidSensorPlatform.genericAuxIdentification1
                xidSensorPlatform.genericAuxIdentification2 = it.xidSensorPlatform.genericAuxIdentification2
                xidSensorPlatform.genericAuxIdentification3 = it.xidSensorPlatform.genericAuxIdentification3
                xidSensorPlatform.genericAuxIdentification4 = it.xidSensorPlatform.genericAuxIdentification4
                xidSensorPlatform.forAnythingExtra1 = it.xidSensorPlatform.forAnythingExtra1
                xidSensorPlatform.forAnythingExtra2 = it.xidSensorPlatform.forAnythingExtra2
                xidSensorPlatform.forAnythingExtra3 = it.xidSensorPlatform.forAnythingExtra3
                xidSensorPlatform.forAnythingExtra4 = it.xidSensorPlatform.forAnythingExtra4
                xidSensorPlatform.backupDatabase = it.xidSensorPlatform.backupDatabase
                xidSensorPlatform.betaDatabaseReleased = it.xidSensorPlatform.betaDatabaseReleased
                xidSensorPlatform.developmentDatabaseReleased = it.xidSensorPlatform.developmentDatabaseReleased
                xidSensorPlatform.experimentalDatabaseReleased = it.xidSensorPlatform.experimentalDatabaseReleased
                xidSensorPlatform.officialDatabaseReleased = it.xidSensorPlatform.officialDatabaseReleased
                xidSensorPlatform.other1DatabaseReleased = it.xidSensorPlatform.other1DatabaseReleased
                xidSensorPlatform.other2DatabaseReleased = it.xidSensorPlatform.other2DatabaseReleased

                if (it.xidSensorPlatform.action == ValueDefault.SRT_REMOVIDO
                    || it.xidSensorPlatform.action == ValueDefault.SRT_REMOVED
                    || it.xidSensorPlatform.action == ValueDefault.SRT_DELETADO
                    || it.xidSensorPlatform.action == ValueDefault.SRT_DELETED
                    || it.xidSensorPlatform.actionNumber == ValueDefault.REMOVIDO
                    || it.xidSensorPlatform.actionNumber == ValueDefault.REMOVED
                    || it.xidSensorPlatform.actionNumber == ValueDefault.DELETADO
                    || it.xidSensorPlatform.actionNumber == ValueDefault.DELETED
                ) {
                    val sensorPlatformEntityAuxList: List<SensorPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformDao()?.findByIdComposite(
                            it.sensorPlatform.sensorPlatformPK.sensor.senId,
                            it.sensorPlatform.sensorPlatformPK.platform.id
                        )

                    val xidSensorPlatformEntityList: List<XidSensorPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformDao()
                            ?.findByObjectIdAndCompositionId(
                                it.sensorPlatform.sensorPlatformPK.sensor.senId.toString(),
                                it.sensorPlatform.sensorPlatformPK.platform.id.toString()
                            )

                    if (!sensorPlatformEntityAuxList.isNullOrEmpty()) {
                        sensorPlatformEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformDao()?.delete(it01)
                        }
                    }

                    if (!xidSensorPlatformEntityList.isNullOrEmpty()) {
                        xidSensorPlatformEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformDao()?.delete(it02)
                        }
                    }


                } else {
                    var bInsert = false
                    var bUpdate = false

                    var sensorPlatformEntityAux: SensorPlatformEntity? = null

                    val sensorPlatformEntityAuxList: List<SensorPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformDao()?.findByIdComposite(
                            it.sensorPlatform.sensorPlatformPK.sensor.senId,
                            it.sensorPlatform.sensorPlatformPK.platform.id
                        )

                    if (!sensorPlatformEntityAuxList.isNullOrEmpty()
                        && sensorPlatformEntityAuxList.size >= 2
                    ) {
                        sensorPlatformEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformDao()?.delete(it1)
                        }
                    } else if (!sensorPlatformEntityAuxList.isNullOrEmpty()) {
                        sensorPlatformEntityAux = sensorPlatformEntityAuxList[0]
                    }

                    //delay(10)

                    if (sensorPlatformEntityAux == null
                        || (sensorPlatformEntityAux != null
                                && sensorPlatformEntityAux.sensorEntityId == 0
                                && sensorPlatformEntityAux.platformEntityId == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformDao()?.insert(sensorPlatform)
                    } else if (sensorPlatformEntityAux.sensorEntityId != 0
                        && sensorPlatformEntityAux.platformEntityId != 0
                    ) {
                        bUpdate = true
                        existInDatabase = true
                        sensorPlatform.id = sensorPlatformEntityAux.id
                        AppDatabase.getDatabase(application.applicationContext)?.sensorPlatformDao()?.update(sensorPlatform)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformDao()?.insert(xidSensorPlatform)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformDao()?.update(xidSensorPlatform)
                        }
                    }
                }
                if (it.xidSensorPlatform.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidSensorPlatform.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_SENSOR_PLATFORM_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_SENSOR_PLATFORM_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_SENSOR_PLATFORM_CURRENT_XID,
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
            PARAMETER_SENSOR_PLATFORM_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidSensorPlatformDao()?.maxXid()
            ?: 0
    }

}