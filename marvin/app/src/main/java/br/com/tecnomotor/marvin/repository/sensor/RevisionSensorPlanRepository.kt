package br.com.tecnomotor.marvin.repository.sensor

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.sensor.RevisionPlanSensorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.sensor.RevisionPlanSensorEntity
import br.com.tecnomotor.marvin.dao.entities.sensor.XidRevisionPlanSensorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class RevisionSensorPlanRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<RevisionPlanSensorSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID
                )!!.toInt()
                val revisionPlanSensorEntity = RevisionPlanSensorEntity()
                revisionPlanSensorEntity.id_pls = it.revisionPlanSensor.revisionPlanSensorPK.id_pls
                revisionPlanSensorEntity.id_rev_plano = it.revisionPlanSensor.revisionPlanSensorPK.id_rev_plan
                revisionPlanSensorEntity.id_sen = it.revisionPlanSensor.id_sen
                revisionPlanSensorEntity.id_rev_sen = it.revisionPlanSensor.id_rev_sen
                revisionPlanSensorEntity.dateHour = it.revisionPlanSensor.dateHour
                revisionPlanSensorEntity.motivation = it.revisionPlanSensor.motivation
                revisionPlanSensorEntity.observation = it.revisionPlanSensor.observation
                revisionPlanSensorEntity.user = it.revisionPlanSensor.user

                val xidRevisionPlanSensorEntity = XidRevisionPlanSensorEntity()
                xidRevisionPlanSensorEntity.id = it.xidRevisionPlanSensor.id
                xidRevisionPlanSensorEntity.action = it.xidRevisionPlanSensor.action
                xidRevisionPlanSensorEntity.actionNumber = it.xidRevisionPlanSensor.actionNumber
                xidRevisionPlanSensorEntity.brand = it.xidRevisionPlanSensor.brand
                xidRevisionPlanSensorEntity.brandId = it.xidRevisionPlanSensor.brandId
                xidRevisionPlanSensorEntity.classResponsible = it.xidRevisionPlanSensor.classResponsible
                xidRevisionPlanSensorEntity.createdDateObject = it.xidRevisionPlanSensor.createdDateObject
                xidRevisionPlanSensorEntity.identification = it.xidRevisionPlanSensor.identification
                xidRevisionPlanSensorEntity.identificationAux = it.xidRevisionPlanSensor.identificationAux
                xidRevisionPlanSensorEntity.lastDateUpdate = it.xidRevisionPlanSensor.lastDateUpdate
                xidRevisionPlanSensorEntity.objectCompositionId = it.xidRevisionPlanSensor.objectCompositionId
                xidRevisionPlanSensorEntity.objectId = it.xidRevisionPlanSensor.objectId
                xidRevisionPlanSensorEntity.variantNameTable1 = it.xidRevisionPlanSensor.variantNameTable1
                xidRevisionPlanSensorEntity.variantNameTable2 = it.xidRevisionPlanSensor.variantNameTable2
                xidRevisionPlanSensorEntity.variantNameTable3 = it.xidRevisionPlanSensor.variantNameTable3
                xidRevisionPlanSensorEntity.variantNameTable4 = it.xidRevisionPlanSensor.variantNameTable4
                xidRevisionPlanSensorEntity.variantNameTable5 = it.xidRevisionPlanSensor.variantNameTable5
                xidRevisionPlanSensorEntity.variantNameTable6 = it.xidRevisionPlanSensor.variantNameTable6
                xidRevisionPlanSensorEntity.responsibleId = it.xidRevisionPlanSensor.responsibleId
                xidRevisionPlanSensorEntity.responsibleName = it.xidRevisionPlanSensor.responsibleName
                xidRevisionPlanSensorEntity.responsibleToken = it.xidRevisionPlanSensor.responsibleToken
                xidRevisionPlanSensorEntity.revisionId = it.xidRevisionPlanSensor.revisionId
                xidRevisionPlanSensorEntity.revisionMotivation = it.xidRevisionPlanSensor.revisionMotivation
                xidRevisionPlanSensorEntity.revisionMotivationObjectId = it.xidRevisionPlanSensor.revisionMotivationObjectId
                xidRevisionPlanSensorEntity.revisionObjectMotivation = it.xidRevisionPlanSensor.revisionObjectMotivation
                xidRevisionPlanSensorEntity.revisionObjectObservation = it.xidRevisionPlanSensor.revisionObjectObservation
                xidRevisionPlanSensorEntity.versionDatabase = it.xidRevisionPlanSensor.versionDatabase
                xidRevisionPlanSensorEntity.xid = it.xidRevisionPlanSensor.xid
                xidRevisionPlanSensorEntity.platformId = it.xidRevisionPlanSensor.platformId
                xidRevisionPlanSensorEntity.platform = it.xidRevisionPlanSensor.platform
                xidRevisionPlanSensorEntity.backupDatabase = it.xidRevisionPlanSensor.backupDatabase
                xidRevisionPlanSensorEntity.genericAuxInfo1 = it.xidRevisionPlanSensor.genericAuxInfo1
                xidRevisionPlanSensorEntity.genericAuxInfo2 = it.xidRevisionPlanSensor.genericAuxInfo2
                xidRevisionPlanSensorEntity.genericAuxInfo3 = it.xidRevisionPlanSensor.genericAuxInfo3
                xidRevisionPlanSensorEntity.genericAuxInfo4 = it.xidRevisionPlanSensor.genericAuxInfo4
                xidRevisionPlanSensorEntity.genericAuxIdentification1 = it.xidRevisionPlanSensor.genericAuxIdentification1
                xidRevisionPlanSensorEntity.genericAuxIdentification2 = it.xidRevisionPlanSensor.genericAuxIdentification2
                xidRevisionPlanSensorEntity.genericAuxIdentification3 = it.xidRevisionPlanSensor.genericAuxIdentification3
                xidRevisionPlanSensorEntity.genericAuxIdentification4 = it.xidRevisionPlanSensor.genericAuxIdentification4
                xidRevisionPlanSensorEntity.forAnythingExtra1 = it.xidRevisionPlanSensor.forAnythingExtra1
                xidRevisionPlanSensorEntity.forAnythingExtra2 = it.xidRevisionPlanSensor.forAnythingExtra2
                xidRevisionPlanSensorEntity.forAnythingExtra3 = it.xidRevisionPlanSensor.forAnythingExtra3
                xidRevisionPlanSensorEntity.forAnythingExtra4 = it.xidRevisionPlanSensor.forAnythingExtra4
                xidRevisionPlanSensorEntity.betaDatabaseReleased = it.xidRevisionPlanSensor.betaDatabaseReleased
                xidRevisionPlanSensorEntity.developmentDatabaseReleased = it.xidRevisionPlanSensor.developmentDatabaseReleased
                xidRevisionPlanSensorEntity.experimentalDatabaseReleased = it.xidRevisionPlanSensor.experimentalDatabaseReleased
                xidRevisionPlanSensorEntity.officialDatabaseReleased = it.xidRevisionPlanSensor.officialDatabaseReleased
                xidRevisionPlanSensorEntity.other1DatabaseReleased = it.xidRevisionPlanSensor.other1DatabaseReleased
                xidRevisionPlanSensorEntity.other2DatabaseReleased = it.xidRevisionPlanSensor.other2DatabaseReleased

                if (it.xidRevisionPlanSensor.action == ValueDefault.SRT_REMOVIDO
                    || it.xidRevisionPlanSensor.action == ValueDefault.SRT_REMOVED
                    || it.xidRevisionPlanSensor.action == ValueDefault.SRT_DELETADO
                    || it.xidRevisionPlanSensor.action == ValueDefault.SRT_DELETED
                    || it.xidRevisionPlanSensor.actionNumber == ValueDefault.REMOVIDO
                    || it.xidRevisionPlanSensor.actionNumber == ValueDefault.REMOVED
                    || it.xidRevisionPlanSensor.actionNumber == ValueDefault.DELETADO
                    || it.xidRevisionPlanSensor.actionNumber == ValueDefault.DELETED
                ) {

                    val revisionPlanSensorEntityAuxList: List<RevisionPlanSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPlanSensorDao()?.findByRevisionSensorPlan(
                            revisionPlanSensorEntity.id_pls,
                            revisionPlanSensorEntity.id_rev_plano,
                        )

                    val xidRevisionPlanSensorEntityList: List<XidRevisionPlanSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanSensorDao()
                            ?.findByObjectIdAndCompositionId(
                                revisionPlanSensorEntity.id_pls.toString(),
                                revisionPlanSensorEntity.id_rev_plano.toString()
                            )

                    if (!revisionPlanSensorEntityAuxList.isNullOrEmpty()) {
                        revisionPlanSensorEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionPlanSensorDao()?.delete(it01)
                        }
                    }

                    if (!xidRevisionPlanSensorEntityList.isNullOrEmpty()) {
                        xidRevisionPlanSensorEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanSensorDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var injectorRevisionEntityAux: RevisionPlanSensorEntity? = null

                    val injectorRevisionEntityAuxList: List<RevisionPlanSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPlanSensorDao()?.findByRevisionSensorPlan(
                            revisionPlanSensorEntity.id_pls,
                            revisionPlanSensorEntity.id_rev_plano,
                        )


                    if (!injectorRevisionEntityAuxList.isNullOrEmpty()
                        && injectorRevisionEntityAuxList.size >= 2
                    ) {
                        injectorRevisionEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.revisionPlanSensorDao()?.delete(it1)
                        }
                    } else if (!injectorRevisionEntityAuxList.isNullOrEmpty()) {
                        injectorRevisionEntityAux = injectorRevisionEntityAuxList[0]
                    }

                    //delay(10)

                    if (injectorRevisionEntityAux == null
                        || (injectorRevisionEntityAux != null
                                && injectorRevisionEntityAux.id_pls == 0
                                && injectorRevisionEntityAux.id_rev_plano == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPlanSensorDao()?.insert(revisionPlanSensorEntity)
                    } else if (injectorRevisionEntityAux.id_pls != 0
                        && injectorRevisionEntityAux.id_rev_plano != 0
                    ) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPlanSensorDao()?.update(revisionPlanSensorEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanSensorDao()?.insert(xidRevisionPlanSensorEntity)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanSensorDao()?.update(xidRevisionPlanSensorEntity)
                        }
                    }
                }
                if (it.xidRevisionPlanSensor.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidRevisionPlanSensor.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanSensorDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID,
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
            PARAMETER_REVISION_SENSOR_PLAN_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanSensorDao()?.maxXid()
            ?: 0
    }

}