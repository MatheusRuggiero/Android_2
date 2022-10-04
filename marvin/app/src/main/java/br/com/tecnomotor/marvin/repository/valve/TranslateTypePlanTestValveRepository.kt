package br.com.tecnomotor.marvin.repository.valve

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.valve.TranslateTypePlanTestValveSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.valve.TranslateTypePlanTestValveEntity
import br.com.tecnomotor.marvin.dao.entities.valve.XidTranslateTypePlanTestValveEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class TranslateTypePlanTestValveRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<TranslateTypePlanTestValveSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID
                )!!.toInt()
                val translateTypePlanTestValveEntity = TranslateTypePlanTestValveEntity()
                translateTypePlanTestValveEntity.translate = it.translateTypePlanTestValve.translate
                translateTypePlanTestValveEntity.typePlanTestEntityId =
                    it.translateTypePlanTestValve.translatePlanTypePlanTestPK!!.typePlanTest.id
                translateTypePlanTestValveEntity.language = it.translateTypePlanTestValve.translatePlanTypePlanTestPK!!.language

                val xidTranslateTypePlanTestValveEntity = XidTranslateTypePlanTestValveEntity()
                xidTranslateTypePlanTestValveEntity.id = it.xidTranslateTypePlanTestValve.id
                xidTranslateTypePlanTestValveEntity.action = it.xidTranslateTypePlanTestValve.action
                xidTranslateTypePlanTestValveEntity.actionNumber = it.xidTranslateTypePlanTestValve.actionNumber
                xidTranslateTypePlanTestValveEntity.brand = it.xidTranslateTypePlanTestValve.brand
                xidTranslateTypePlanTestValveEntity.brandId = it.xidTranslateTypePlanTestValve.brandId
                xidTranslateTypePlanTestValveEntity.classResponsible = it.xidTranslateTypePlanTestValve.classResponsible
                xidTranslateTypePlanTestValveEntity.createdDateObject = it.xidTranslateTypePlanTestValve.createdDateObject
                xidTranslateTypePlanTestValveEntity.identification = it.xidTranslateTypePlanTestValve.identification
                xidTranslateTypePlanTestValveEntity.identificationAux = it.xidTranslateTypePlanTestValve.identificationAux
                xidTranslateTypePlanTestValveEntity.lastDateUpdate = it.xidTranslateTypePlanTestValve.lastDateUpdate
                xidTranslateTypePlanTestValveEntity.objectCompositionId = it.xidTranslateTypePlanTestValve.objectCompositionId
                xidTranslateTypePlanTestValveEntity.objectId = it.xidTranslateTypePlanTestValve.objectId
                xidTranslateTypePlanTestValveEntity.variantNameTable1 = it.xidTranslateTypePlanTestValve.variantNameTable1
                xidTranslateTypePlanTestValveEntity.variantNameTable2 = it.xidTranslateTypePlanTestValve.variantNameTable2
                xidTranslateTypePlanTestValveEntity.variantNameTable3 = it.xidTranslateTypePlanTestValve.variantNameTable3
                xidTranslateTypePlanTestValveEntity.variantNameTable4 = it.xidTranslateTypePlanTestValve.variantNameTable4
                xidTranslateTypePlanTestValveEntity.variantNameTable5 = it.xidTranslateTypePlanTestValve.variantNameTable5
                xidTranslateTypePlanTestValveEntity.variantNameTable6 = it.xidTranslateTypePlanTestValve.variantNameTable6
                xidTranslateTypePlanTestValveEntity.responsibleId = it.xidTranslateTypePlanTestValve.responsibleId
                xidTranslateTypePlanTestValveEntity.responsibleName = it.xidTranslateTypePlanTestValve.responsibleName
                xidTranslateTypePlanTestValveEntity.responsibleToken = it.xidTranslateTypePlanTestValve.responsibleToken
                xidTranslateTypePlanTestValveEntity.revisionId = it.xidTranslateTypePlanTestValve.revisionId
                xidTranslateTypePlanTestValveEntity.revisionMotivation = it.xidTranslateTypePlanTestValve.revisionMotivation
                xidTranslateTypePlanTestValveEntity.revisionMotivationObjectId = it.xidTranslateTypePlanTestValve.revisionMotivationObjectId
                xidTranslateTypePlanTestValveEntity.revisionObjectMotivation = it.xidTranslateTypePlanTestValve.revisionObjectMotivation
                xidTranslateTypePlanTestValveEntity.revisionObjectObservation = it.xidTranslateTypePlanTestValve.revisionObjectObservation
                xidTranslateTypePlanTestValveEntity.versionDatabase = it.xidTranslateTypePlanTestValve.versionDatabase
                xidTranslateTypePlanTestValveEntity.xid = it.xidTranslateTypePlanTestValve.xid
                xidTranslateTypePlanTestValveEntity.platformId = it.xidTranslateTypePlanTestValve.platformId
                xidTranslateTypePlanTestValveEntity.platform = it.xidTranslateTypePlanTestValve.platform
                xidTranslateTypePlanTestValveEntity.genericAuxInfo1 = it.xidTranslateTypePlanTestValve.genericAuxInfo1
                xidTranslateTypePlanTestValveEntity.genericAuxInfo2 = it.xidTranslateTypePlanTestValve.genericAuxInfo2
                xidTranslateTypePlanTestValveEntity.genericAuxInfo3 = it.xidTranslateTypePlanTestValve.genericAuxInfo3
                xidTranslateTypePlanTestValveEntity.genericAuxInfo4 = it.xidTranslateTypePlanTestValve.genericAuxInfo4
                xidTranslateTypePlanTestValveEntity.genericAuxIdentification1 = it.xidTranslateTypePlanTestValve.genericAuxIdentification1
                xidTranslateTypePlanTestValveEntity.genericAuxIdentification2 = it.xidTranslateTypePlanTestValve.genericAuxIdentification2
                xidTranslateTypePlanTestValveEntity.genericAuxIdentification3 = it.xidTranslateTypePlanTestValve.genericAuxIdentification3
                xidTranslateTypePlanTestValveEntity.genericAuxIdentification4 = it.xidTranslateTypePlanTestValve.genericAuxIdentification4
                xidTranslateTypePlanTestValveEntity.forAnythingExtra1 = it.xidTranslateTypePlanTestValve.forAnythingExtra1
                xidTranslateTypePlanTestValveEntity.forAnythingExtra2 = it.xidTranslateTypePlanTestValve.forAnythingExtra2
                xidTranslateTypePlanTestValveEntity.forAnythingExtra3 = it.xidTranslateTypePlanTestValve.forAnythingExtra3
                xidTranslateTypePlanTestValveEntity.forAnythingExtra4 = it.xidTranslateTypePlanTestValve.forAnythingExtra4
                xidTranslateTypePlanTestValveEntity.backupDatabase = it.xidTranslateTypePlanTestValve.backupDatabase
                xidTranslateTypePlanTestValveEntity.betaDatabaseReleased = it.xidTranslateTypePlanTestValve.betaDatabaseReleased
                xidTranslateTypePlanTestValveEntity.developmentDatabaseReleased =
                    it.xidTranslateTypePlanTestValve.developmentDatabaseReleased
                xidTranslateTypePlanTestValveEntity.experimentalDatabaseReleased =
                    it.xidTranslateTypePlanTestValve.experimentalDatabaseReleased
                xidTranslateTypePlanTestValveEntity.officialDatabaseReleased = it.xidTranslateTypePlanTestValve.officialDatabaseReleased
                xidTranslateTypePlanTestValveEntity.other1DatabaseReleased = it.xidTranslateTypePlanTestValve.other1DatabaseReleased
                xidTranslateTypePlanTestValveEntity.other2DatabaseReleased = it.xidTranslateTypePlanTestValve.other2DatabaseReleased

                if (it.xidTranslateTypePlanTestValve.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTranslateTypePlanTestValve.action == ValueDefault.SRT_REMOVED
                    || it.xidTranslateTypePlanTestValve.action == ValueDefault.SRT_DELETADO
                    || it.xidTranslateTypePlanTestValve.action == ValueDefault.SRT_DELETED
                    || it.xidTranslateTypePlanTestValve.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTranslateTypePlanTestValve.actionNumber == ValueDefault.REMOVED
                    || it.xidTranslateTypePlanTestValve.actionNumber == ValueDefault.DELETADO
                    || it.xidTranslateTypePlanTestValve.actionNumber == ValueDefault.DELETED
                ) {
                    val translateTypePlanTestValveEntityAuxList: List<TranslateTypePlanTestValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestValveDao()
                            ?.findTranslateTypePlanTestValve(
                                it.translateTypePlanTestValve.translatePlanTypePlanTestPK!!.language,
                                it.translateTypePlanTestValve.translatePlanTypePlanTestPK!!.typePlanTest.id,
                                it.translateTypePlanTestValve.translate
                            )

                    val xidTypePlanTestEntityList: List<XidTranslateTypePlanTestValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestValveDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                it.translateTypePlanTestValve.translatePlanTypePlanTestPK!!.language,
                                it.translateTypePlanTestValve.translate!!,
                                it.translateTypePlanTestValve.translatePlanTypePlanTestPK!!.typePlanTest.id.toString()
                            )

                    if (!translateTypePlanTestValveEntityAuxList.isNullOrEmpty()) {
                        translateTypePlanTestValveEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestValveDao()?.delete(it01)
                        }
                    }

                    if (!xidTypePlanTestEntityList.isNullOrEmpty()) {
                        xidTypePlanTestEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestValveDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var translateTypePlanTestValveEntityAux: TranslateTypePlanTestValveEntity? = null

                    val translateTypePlanTestValveEntityAuxList: List<TranslateTypePlanTestValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestValveDao()
                            ?.findTranslateTypePlanTestValve(
                                it.translateTypePlanTestValve.translatePlanTypePlanTestPK!!.language,
                                it.translateTypePlanTestValve.translatePlanTypePlanTestPK!!.typePlanTest.id,
                                it.translateTypePlanTestValve.translate
                            )

                    if (!translateTypePlanTestValveEntityAuxList.isNullOrEmpty()
                        && translateTypePlanTestValveEntityAuxList.size >= 2
                    ) {
                        translateTypePlanTestValveEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestValveDao()?.delete(it1)
                        }
                    } else if (!translateTypePlanTestValveEntityAuxList.isNullOrEmpty()) {
                        translateTypePlanTestValveEntityAux =
                            translateTypePlanTestValveEntityAuxList[0]
                    }

                    //delay(10)

                    if (translateTypePlanTestValveEntityAux == null
                        || (translateTypePlanTestValveEntityAux != null
                                && translateTypePlanTestValveEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestValveDao()
                            ?.insert(translateTypePlanTestValveEntity)
                    } else if (translateTypePlanTestValveEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        translateTypePlanTestValveEntity.id = translateTypePlanTestValveEntityAux.id
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestValveDao()
                            ?.update(translateTypePlanTestValveEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestValveDao()
                                ?.insert(xidTranslateTypePlanTestValveEntity)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestValveDao()
                                ?.update(xidTranslateTypePlanTestValveEntity)
                        }
                    }
                }
                if (it.xidTranslateTypePlanTestValve.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTranslateTypePlanTestValve.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestValveDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID,
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
            PARAMETER_TRANSLATE_TYPE_PLAN_VALVE_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestValveDao()?.maxXid()
            ?: 0
    }
}