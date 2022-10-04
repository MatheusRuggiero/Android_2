package br.com.tecnomotor.marvin.repository.sensor

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.sensor.TranslateTypePlanTestSensorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.sensor.TranslateTypePlanTestSensorEntity
import br.com.tecnomotor.marvin.dao.entities.sensor.XidTranslateTypePlanTestSensorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class TranslateTypePlanTestSensorRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<TranslateTypePlanTestSensorSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID
                )!!.toInt()
                val translateTypePlanTestSensorEntity = TranslateTypePlanTestSensorEntity()
                translateTypePlanTestSensorEntity.translate = it.translateTypePlanTestSensor.translate
                translateTypePlanTestSensorEntity.typePlanTestEntityId =
                    it.translateTypePlanTestSensor.translateTypePlanTestSensorPK.typePlanTest.id
                translateTypePlanTestSensorEntity.language = it.translateTypePlanTestSensor.translateTypePlanTestSensorPK.language

                val xidTranslateTypePlanTestSensorEntity = XidTranslateTypePlanTestSensorEntity()
                xidTranslateTypePlanTestSensorEntity.id = it.xidTranslateTypePlanTestSensor.id
                xidTranslateTypePlanTestSensorEntity.action = it.xidTranslateTypePlanTestSensor.action
                xidTranslateTypePlanTestSensorEntity.actionNumber = it.xidTranslateTypePlanTestSensor.actionNumber
                xidTranslateTypePlanTestSensorEntity.brand = it.xidTranslateTypePlanTestSensor.brand
                xidTranslateTypePlanTestSensorEntity.brandId = it.xidTranslateTypePlanTestSensor.brandId
                xidTranslateTypePlanTestSensorEntity.classResponsible = it.xidTranslateTypePlanTestSensor.classResponsible
                xidTranslateTypePlanTestSensorEntity.createdDateObject = it.xidTranslateTypePlanTestSensor.createdDateObject
                xidTranslateTypePlanTestSensorEntity.identification = it.xidTranslateTypePlanTestSensor.identification
                xidTranslateTypePlanTestSensorEntity.identificationAux = it.xidTranslateTypePlanTestSensor.identificationAux
                xidTranslateTypePlanTestSensorEntity.lastDateUpdate = it.xidTranslateTypePlanTestSensor.lastDateUpdate
                xidTranslateTypePlanTestSensorEntity.objectCompositionId = it.xidTranslateTypePlanTestSensor.objectCompositionId
                xidTranslateTypePlanTestSensorEntity.objectId = it.xidTranslateTypePlanTestSensor.objectId
                xidTranslateTypePlanTestSensorEntity.variantNameTable1 = it.xidTranslateTypePlanTestSensor.variantNameTable1
                xidTranslateTypePlanTestSensorEntity.variantNameTable2 = it.xidTranslateTypePlanTestSensor.variantNameTable2
                xidTranslateTypePlanTestSensorEntity.variantNameTable3 = it.xidTranslateTypePlanTestSensor.variantNameTable3
                xidTranslateTypePlanTestSensorEntity.variantNameTable4 = it.xidTranslateTypePlanTestSensor.variantNameTable4
                xidTranslateTypePlanTestSensorEntity.variantNameTable5 = it.xidTranslateTypePlanTestSensor.variantNameTable5
                xidTranslateTypePlanTestSensorEntity.variantNameTable6 = it.xidTranslateTypePlanTestSensor.variantNameTable6
                xidTranslateTypePlanTestSensorEntity.responsibleId = it.xidTranslateTypePlanTestSensor.responsibleId
                xidTranslateTypePlanTestSensorEntity.responsibleName = it.xidTranslateTypePlanTestSensor.responsibleName
                xidTranslateTypePlanTestSensorEntity.responsibleToken = it.xidTranslateTypePlanTestSensor.responsibleToken
                xidTranslateTypePlanTestSensorEntity.revisionId = it.xidTranslateTypePlanTestSensor.revisionId
                xidTranslateTypePlanTestSensorEntity.revisionMotivation = it.xidTranslateTypePlanTestSensor.revisionMotivation
                xidTranslateTypePlanTestSensorEntity.revisionMotivationObjectId =
                    it.xidTranslateTypePlanTestSensor.revisionMotivationObjectId
                xidTranslateTypePlanTestSensorEntity.revisionObjectMotivation = it.xidTranslateTypePlanTestSensor.revisionObjectMotivation
                xidTranslateTypePlanTestSensorEntity.revisionObjectObservation = it.xidTranslateTypePlanTestSensor.revisionObjectObservation
                xidTranslateTypePlanTestSensorEntity.versionDatabase = it.xidTranslateTypePlanTestSensor.versionDatabase
                xidTranslateTypePlanTestSensorEntity.xid = it.xidTranslateTypePlanTestSensor.xid
                xidTranslateTypePlanTestSensorEntity.platformId = it.xidTranslateTypePlanTestSensor.platformId
                xidTranslateTypePlanTestSensorEntity.platform = it.xidTranslateTypePlanTestSensor.platform
                xidTranslateTypePlanTestSensorEntity.genericAuxInfo1 = it.xidTranslateTypePlanTestSensor.genericAuxInfo1
                xidTranslateTypePlanTestSensorEntity.genericAuxInfo2 = it.xidTranslateTypePlanTestSensor.genericAuxInfo2
                xidTranslateTypePlanTestSensorEntity.genericAuxInfo3 = it.xidTranslateTypePlanTestSensor.genericAuxInfo3
                xidTranslateTypePlanTestSensorEntity.genericAuxInfo4 = it.xidTranslateTypePlanTestSensor.genericAuxInfo4
                xidTranslateTypePlanTestSensorEntity.genericAuxIdentification1 = it.xidTranslateTypePlanTestSensor.genericAuxIdentification1
                xidTranslateTypePlanTestSensorEntity.genericAuxIdentification2 = it.xidTranslateTypePlanTestSensor.genericAuxIdentification2
                xidTranslateTypePlanTestSensorEntity.genericAuxIdentification3 = it.xidTranslateTypePlanTestSensor.genericAuxIdentification3
                xidTranslateTypePlanTestSensorEntity.genericAuxIdentification4 = it.xidTranslateTypePlanTestSensor.genericAuxIdentification4
                xidTranslateTypePlanTestSensorEntity.forAnythingExtra1 = it.xidTranslateTypePlanTestSensor.forAnythingExtra1
                xidTranslateTypePlanTestSensorEntity.forAnythingExtra2 = it.xidTranslateTypePlanTestSensor.forAnythingExtra2
                xidTranslateTypePlanTestSensorEntity.forAnythingExtra3 = it.xidTranslateTypePlanTestSensor.forAnythingExtra3
                xidTranslateTypePlanTestSensorEntity.forAnythingExtra4 = it.xidTranslateTypePlanTestSensor.forAnythingExtra4
                xidTranslateTypePlanTestSensorEntity.backupDatabase = it.xidTranslateTypePlanTestSensor.backupDatabase
                xidTranslateTypePlanTestSensorEntity.betaDatabaseReleased = it.xidTranslateTypePlanTestSensor.betaDatabaseReleased
                xidTranslateTypePlanTestSensorEntity.developmentDatabaseReleased =
                    it.xidTranslateTypePlanTestSensor.developmentDatabaseReleased
                xidTranslateTypePlanTestSensorEntity.experimentalDatabaseReleased =
                    it.xidTranslateTypePlanTestSensor.experimentalDatabaseReleased
                xidTranslateTypePlanTestSensorEntity.officialDatabaseReleased = it.xidTranslateTypePlanTestSensor.officialDatabaseReleased
                xidTranslateTypePlanTestSensorEntity.other1DatabaseReleased = it.xidTranslateTypePlanTestSensor.other1DatabaseReleased
                xidTranslateTypePlanTestSensorEntity.other2DatabaseReleased = it.xidTranslateTypePlanTestSensor.other2DatabaseReleased

                if (it.xidTranslateTypePlanTestSensor.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTranslateTypePlanTestSensor.action == ValueDefault.SRT_REMOVED
                    || it.xidTranslateTypePlanTestSensor.action == ValueDefault.SRT_DELETADO
                    || it.xidTranslateTypePlanTestSensor.action == ValueDefault.SRT_DELETED
                    || it.xidTranslateTypePlanTestSensor.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTranslateTypePlanTestSensor.actionNumber == ValueDefault.REMOVED
                    || it.xidTranslateTypePlanTestSensor.actionNumber == ValueDefault.DELETADO
                    || it.xidTranslateTypePlanTestSensor.actionNumber == ValueDefault.DELETED
                ) {

                    val translateTypePlanTestSensorEntityAuxList: List<TranslateTypePlanTestSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestSensorTestDao()
                            ?.findTranslateTypePlanTestSensor(
                                it.translateTypePlanTestSensor.translateTypePlanTestSensorPK.language,
                                it.translateTypePlanTestSensor.translateTypePlanTestSensorPK.typePlanTest.id,
                                it.translateTypePlanTestSensor.translate
                            )

                    val xidTypePlanTestEntityList: List<XidTranslateTypePlanTestSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestSensorTestDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                it.translateTypePlanTestSensor.translateTypePlanTestSensorPK.language!!,
                                it.translateTypePlanTestSensor.translate!!,
                                it.translateTypePlanTestSensor.translateTypePlanTestSensorPK.typePlanTest.id.toString()
                            )

                    if (!translateTypePlanTestSensorEntityAuxList.isNullOrEmpty()) {
                        translateTypePlanTestSensorEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestSensorTestDao()?.delete(it01)
                        }
                    }

                    if (!xidTypePlanTestEntityList.isNullOrEmpty()) {
                        xidTypePlanTestEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestSensorTestDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var translateTypePlanTestSensorEntityAux: TranslateTypePlanTestSensorEntity? = null

                    val translateTypePlanTestSensorEntityAuxList: List<TranslateTypePlanTestSensorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestSensorTestDao()
                            ?.findTranslateTypePlanTestSensor(
                                it.translateTypePlanTestSensor.translateTypePlanTestSensorPK.language,
                                it.translateTypePlanTestSensor.translateTypePlanTestSensorPK.typePlanTest.id,
                                it.translateTypePlanTestSensor.translate
                            )

                    if (!translateTypePlanTestSensorEntityAuxList.isNullOrEmpty()
                        && translateTypePlanTestSensorEntityAuxList.size >= 2
                    ) {
                        translateTypePlanTestSensorEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestSensorTestDao()?.delete(it1)
                        }
                    } else if (!translateTypePlanTestSensorEntityAuxList.isNullOrEmpty()) {
                        translateTypePlanTestSensorEntityAux =
                            translateTypePlanTestSensorEntityAuxList[0]
                    }

                    //delay(10)

                    if (translateTypePlanTestSensorEntityAux == null
                        || (translateTypePlanTestSensorEntityAux != null
                                && translateTypePlanTestSensorEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestSensorTestDao()
                            ?.insert(translateTypePlanTestSensorEntity)
                    } else if (translateTypePlanTestSensorEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        translateTypePlanTestSensorEntity.id = translateTypePlanTestSensorEntityAux.id
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestSensorTestDao()
                            ?.update(translateTypePlanTestSensorEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestSensorTestDao()
                                ?.insert(xidTranslateTypePlanTestSensorEntity)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestSensorTestDao()
                                ?.update(xidTranslateTypePlanTestSensorEntity)
                        }
                    }
                }
                if (it.xidTranslateTypePlanTestSensor.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTranslateTypePlanTestSensor.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestSensorTestDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID,
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
            PARAMETER_TRANSLATE_TYPE_PLAN_SENSOR_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestSensorTestDao()?.maxXid()
            ?: 0
    }
}