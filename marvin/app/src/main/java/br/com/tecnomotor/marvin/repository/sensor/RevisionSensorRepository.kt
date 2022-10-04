package br.com.tecnomotor.marvin.repository.sensor

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.sensor.RevisionSensorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.sensor.RevisionSensorEntity
import br.com.tecnomotor.marvin.dao.entities.sensor.XidRevisionSensorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_SENSOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class RevisionSensorRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<RevisionSensorSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_REVISION_SENSOR_CURRENT_XID
                )!!.toInt()
                val revisionSensorEntity = RevisionSensorEntity()
                revisionSensorEntity.id_sen = it.revisionSensor.revisionSensorPK.id_sen
                revisionSensorEntity.id_rev = it.revisionSensor.revisionSensorPK.id_rev
                revisionSensorEntity.dateHour = it.revisionSensor.dateHour
                revisionSensorEntity.motivation = it.revisionSensor.motivation
                revisionSensorEntity.observation = it.revisionSensor.observation
                revisionSensorEntity.user = it.revisionSensor.user

                val xidRevisionSensor = XidRevisionSensorEntity()
                xidRevisionSensor.id = it.xidRevisionSensor.id
                xidRevisionSensor.action = it.xidRevisionSensor.action
                xidRevisionSensor.actionNumber = it.xidRevisionSensor.actionNumber
                xidRevisionSensor.brand = it.xidRevisionSensor.brand
                xidRevisionSensor.brandId = it.xidRevisionSensor.brandId
                xidRevisionSensor.classResponsible = it.xidRevisionSensor.classResponsible
                xidRevisionSensor.createdDateObject = it.xidRevisionSensor.createdDateObject
                xidRevisionSensor.identification = it.xidRevisionSensor.identification
                xidRevisionSensor.identificationAux = it.xidRevisionSensor.identificationAux
                xidRevisionSensor.lastDateUpdate = it.xidRevisionSensor.lastDateUpdate
                xidRevisionSensor.objectCompositionId = it.xidRevisionSensor.objectCompositionId
                xidRevisionSensor.objectId = it.xidRevisionSensor.objectId
                xidRevisionSensor.variantNameTable1 = it.xidRevisionSensor.variantNameTable1
                xidRevisionSensor.variantNameTable2 = it.xidRevisionSensor.variantNameTable2
                xidRevisionSensor.variantNameTable3 = it.xidRevisionSensor.variantNameTable3
                xidRevisionSensor.variantNameTable4 = it.xidRevisionSensor.variantNameTable4
                xidRevisionSensor.variantNameTable5 = it.xidRevisionSensor.variantNameTable5
                xidRevisionSensor.variantNameTable6 = it.xidRevisionSensor.variantNameTable6
                xidRevisionSensor.responsibleId = it.xidRevisionSensor.responsibleId
                xidRevisionSensor.responsibleName = it.xidRevisionSensor.responsibleName
                xidRevisionSensor.responsibleToken = it.xidRevisionSensor.responsibleToken
                xidRevisionSensor.revisionId = it.xidRevisionSensor.revisionId
                xidRevisionSensor.revisionMotivation = it.xidRevisionSensor.revisionMotivation
                xidRevisionSensor.revisionMotivationObjectId = it.xidRevisionSensor.revisionMotivationObjectId
                xidRevisionSensor.revisionObjectMotivation = it.xidRevisionSensor.revisionObjectMotivation
                xidRevisionSensor.revisionObjectObservation = it.xidRevisionSensor.revisionObjectObservation
                xidRevisionSensor.versionDatabase = it.xidRevisionSensor.versionDatabase
                xidRevisionSensor.xid = it.xidRevisionSensor.xid
                xidRevisionSensor.platformId = it.xidRevisionSensor.platformId
                xidRevisionSensor.platform = it.xidRevisionSensor.platform
                xidRevisionSensor.backupDatabase = it.xidRevisionSensor.backupDatabase
                xidRevisionSensor.genericAuxInfo1 = it.xidRevisionSensor.genericAuxInfo1
                xidRevisionSensor.genericAuxInfo2 = it.xidRevisionSensor.genericAuxInfo2
                xidRevisionSensor.genericAuxInfo3 = it.xidRevisionSensor.genericAuxInfo3
                xidRevisionSensor.genericAuxInfo4 = it.xidRevisionSensor.genericAuxInfo4
                xidRevisionSensor.genericAuxIdentification1 = it.xidRevisionSensor.genericAuxIdentification1
                xidRevisionSensor.genericAuxIdentification2 = it.xidRevisionSensor.genericAuxIdentification2
                xidRevisionSensor.genericAuxIdentification3 = it.xidRevisionSensor.genericAuxIdentification3
                xidRevisionSensor.genericAuxIdentification4 = it.xidRevisionSensor.genericAuxIdentification4
                xidRevisionSensor.forAnythingExtra1 = it.xidRevisionSensor.forAnythingExtra1
                xidRevisionSensor.forAnythingExtra2 = it.xidRevisionSensor.forAnythingExtra2
                xidRevisionSensor.forAnythingExtra3 = it.xidRevisionSensor.forAnythingExtra3
                xidRevisionSensor.forAnythingExtra4 = it.xidRevisionSensor.forAnythingExtra4
                xidRevisionSensor.betaDatabaseReleased = it.xidRevisionSensor.betaDatabaseReleased
                xidRevisionSensor.developmentDatabaseReleased = it.xidRevisionSensor.developmentDatabaseReleased
                xidRevisionSensor.experimentalDatabaseReleased = it.xidRevisionSensor.experimentalDatabaseReleased
                xidRevisionSensor.officialDatabaseReleased = it.xidRevisionSensor.officialDatabaseReleased
                xidRevisionSensor.other1DatabaseReleased = it.xidRevisionSensor.other1DatabaseReleased
                xidRevisionSensor.other2DatabaseReleased = it.xidRevisionSensor.other2DatabaseReleased

                if (it.xidRevisionSensor.action == ValueDefault.SRT_REMOVIDO
                    || it.xidRevisionSensor.action == ValueDefault.SRT_REMOVED
                    || it.xidRevisionSensor.action == ValueDefault.SRT_DELETADO
                    || it.xidRevisionSensor.action == ValueDefault.SRT_DELETED
                    || it.xidRevisionSensor.actionNumber == ValueDefault.REMOVIDO
                    || it.xidRevisionSensor.actionNumber == ValueDefault.REMOVED
                    || it.xidRevisionSensor.actionNumber == ValueDefault.DELETADO
                    || it.xidRevisionSensor.actionNumber == ValueDefault.DELETED
                ) {
                    val revisionSensorEntityAuxList: List<RevisionSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionSensorDao()?.findByRevisionSensor(
                            revisionSensorEntity.id_sen,
                            revisionSensorEntity.id_rev,
                        )

                    val xidRevisionSensorEntityList: List<XidRevisionSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidRevisionSensorDao()
                            ?.findByObjectIdAndCompositionId(
                                revisionSensorEntity.id_sen.toString(),
                                revisionSensorEntity.id_rev.toString()
                            )

                    if (!revisionSensorEntityAuxList.isNullOrEmpty()) {
                        revisionSensorEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionSensorDao()?.delete(it01)
                        }
                    }

                    if (!xidRevisionSensorEntityList.isNullOrEmpty()) {
                        xidRevisionSensorEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionSensorDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var sensorRevisionEntityAux: RevisionSensorEntity? = null

                    val sensorRevisionEntityAuxList: List<RevisionSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionSensorDao()?.findByRevisionSensor(
                            revisionSensorEntity.id_sen,
                            revisionSensorEntity.id_rev,
                        )


                    if (!sensorRevisionEntityAuxList.isNullOrEmpty()
                        && sensorRevisionEntityAuxList.size >= 2
                    ) {
                        sensorRevisionEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.revisionSensorDao()?.delete(it1)
                        }
                    } else if (!sensorRevisionEntityAuxList.isNullOrEmpty()) {
                        sensorRevisionEntityAux = sensorRevisionEntityAuxList[0]
                    }

                    //delay(10)

                    if (sensorRevisionEntityAux == null
                        || (sensorRevisionEntityAux != null
                                && sensorRevisionEntityAux.id_sen == 0
                                && sensorRevisionEntityAux.id_rev == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionSensorDao()?.insert(revisionSensorEntity)
                    } else if (sensorRevisionEntityAux.id_sen != 0
                        && sensorRevisionEntityAux.id_rev != 0
                    ) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionSensorDao()?.update(revisionSensorEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionSensorDao()?.insert(xidRevisionSensor)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionSensorDao()?.update(xidRevisionSensor)
                        }
                    }
                }
                if (it.xidRevisionSensor.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidRevisionSensor.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_REVISION_SENSOR_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidRevisionSensorDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_SENSOR_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_SENSOR_CURRENT_XID,
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
            PARAMETER_REVISION_SENSOR_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidRevisionSensorDao()?.maxXid()
            ?: 0
    }

}