package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.pump.TranslateTypePlanTestPumpSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.TranslateTypePlanTestPumpEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidTranslateTypePlanTestPumpEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class TranslateTypePlanTestPumpRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<TranslateTypePlanTestPumpSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID
                )!!.toInt()
                val translateTypePlanTestPump =
                    TranslateTypePlanTestPumpEntity()
                translateTypePlanTestPump.translate = it.translateTypePlanTestPump.translation
                translateTypePlanTestPump.typePlanTestId = it.translateTypePlanTestPump.translateTypePlanTestPumpPK!!.typePlanTest.id
                translateTypePlanTestPump.language = it.translateTypePlanTestPump.translateTypePlanTestPumpPK!!.language

                val xidTranslateTypePlanTestPump =
                    XidTranslateTypePlanTestPumpEntity()
                xidTranslateTypePlanTestPump.id = it.xidTranslateTypePlanTestPump.id
                xidTranslateTypePlanTestPump.action = it.xidTranslateTypePlanTestPump.action
                xidTranslateTypePlanTestPump.actionNumber = it.xidTranslateTypePlanTestPump.actionNumber
                xidTranslateTypePlanTestPump.brand = it.xidTranslateTypePlanTestPump.brand
                xidTranslateTypePlanTestPump.brandId = it.xidTranslateTypePlanTestPump.brandId
                xidTranslateTypePlanTestPump.classResponsible = it.xidTranslateTypePlanTestPump.classResponsible
                xidTranslateTypePlanTestPump.createdDateObject = it.xidTranslateTypePlanTestPump.createdDateObject
                xidTranslateTypePlanTestPump.identification = it.xidTranslateTypePlanTestPump.identification
                xidTranslateTypePlanTestPump.identificationAux = it.xidTranslateTypePlanTestPump.identificationAux
                xidTranslateTypePlanTestPump.lastDateUpdate = it.xidTranslateTypePlanTestPump.lastDateUpdate
                xidTranslateTypePlanTestPump.objectCompositionId = it.xidTranslateTypePlanTestPump.objectCompositionId
                xidTranslateTypePlanTestPump.objectId = it.xidTranslateTypePlanTestPump.objectId
                xidTranslateTypePlanTestPump.variantNameTable1 = it.xidTranslateTypePlanTestPump.variantNameTable1
                xidTranslateTypePlanTestPump.variantNameTable2 = it.xidTranslateTypePlanTestPump.variantNameTable2
                xidTranslateTypePlanTestPump.variantNameTable3 = it.xidTranslateTypePlanTestPump.variantNameTable3
                xidTranslateTypePlanTestPump.variantNameTable4 = it.xidTranslateTypePlanTestPump.variantNameTable4
                xidTranslateTypePlanTestPump.variantNameTable5 = it.xidTranslateTypePlanTestPump.variantNameTable5
                xidTranslateTypePlanTestPump.variantNameTable6 = it.xidTranslateTypePlanTestPump.variantNameTable6
                xidTranslateTypePlanTestPump.responsibleId = it.xidTranslateTypePlanTestPump.responsibleId
                xidTranslateTypePlanTestPump.responsibleName = it.xidTranslateTypePlanTestPump.responsibleName
                xidTranslateTypePlanTestPump.responsibleToken = it.xidTranslateTypePlanTestPump.responsibleToken
                xidTranslateTypePlanTestPump.revisionId = it.xidTranslateTypePlanTestPump.revisionId
                xidTranslateTypePlanTestPump.revisionMotivation = it.xidTranslateTypePlanTestPump.revisionMotivation
                xidTranslateTypePlanTestPump.revisionMotivationObjectId = it.xidTranslateTypePlanTestPump.revisionMotivationObjectId
                xidTranslateTypePlanTestPump.revisionObjectMotivation = it.xidTranslateTypePlanTestPump.revisionObjectMotivation
                xidTranslateTypePlanTestPump.revisionObjectObservation = it.xidTranslateTypePlanTestPump.revisionObjectObservation
                xidTranslateTypePlanTestPump.versionDatabase = it.xidTranslateTypePlanTestPump.versionDatabase
                xidTranslateTypePlanTestPump.xid = it.xidTranslateTypePlanTestPump.xid
                xidTranslateTypePlanTestPump.platformId = it.xidTranslateTypePlanTestPump.platformId
                xidTranslateTypePlanTestPump.platform = it.xidTranslateTypePlanTestPump.platform
                xidTranslateTypePlanTestPump.genericAuxInfo1 = it.xidTranslateTypePlanTestPump.genericAuxInfo1
                xidTranslateTypePlanTestPump.genericAuxInfo2 = it.xidTranslateTypePlanTestPump.genericAuxInfo2
                xidTranslateTypePlanTestPump.genericAuxInfo3 = it.xidTranslateTypePlanTestPump.genericAuxInfo3
                xidTranslateTypePlanTestPump.genericAuxInfo4 = it.xidTranslateTypePlanTestPump.genericAuxInfo4
                xidTranslateTypePlanTestPump.genericAuxIdentification1 = it.xidTranslateTypePlanTestPump.genericAuxIdentification1
                xidTranslateTypePlanTestPump.genericAuxIdentification2 = it.xidTranslateTypePlanTestPump.genericAuxIdentification2
                xidTranslateTypePlanTestPump.genericAuxIdentification3 = it.xidTranslateTypePlanTestPump.genericAuxIdentification3
                xidTranslateTypePlanTestPump.genericAuxIdentification4 = it.xidTranslateTypePlanTestPump.genericAuxIdentification4
                xidTranslateTypePlanTestPump.forAnythingExtra1 = it.xidTranslateTypePlanTestPump.forAnythingExtra1
                xidTranslateTypePlanTestPump.forAnythingExtra2 = it.xidTranslateTypePlanTestPump.forAnythingExtra2
                xidTranslateTypePlanTestPump.forAnythingExtra3 = it.xidTranslateTypePlanTestPump.forAnythingExtra3
                xidTranslateTypePlanTestPump.forAnythingExtra4 = it.xidTranslateTypePlanTestPump.forAnythingExtra4
                xidTranslateTypePlanTestPump.backupDatabase = it.xidTranslateTypePlanTestPump.backupDatabase
                xidTranslateTypePlanTestPump.betaDatabaseReleased = it.xidTranslateTypePlanTestPump.betaDatabaseReleased
                xidTranslateTypePlanTestPump.developmentDatabaseReleased = it.xidTranslateTypePlanTestPump.developmentDatabaseReleased
                xidTranslateTypePlanTestPump.experimentalDatabaseReleased = it.xidTranslateTypePlanTestPump.experimentalDatabaseReleased
                xidTranslateTypePlanTestPump.officialDatabaseReleased = it.xidTranslateTypePlanTestPump.officialDatabaseReleased
                xidTranslateTypePlanTestPump.other1DatabaseReleased = it.xidTranslateTypePlanTestPump.other1DatabaseReleased
                xidTranslateTypePlanTestPump.other2DatabaseReleased = it.xidTranslateTypePlanTestPump.other2DatabaseReleased

                if (it.xidTranslateTypePlanTestPump.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTranslateTypePlanTestPump.action == ValueDefault.SRT_REMOVED
                    || it.xidTranslateTypePlanTestPump.action == ValueDefault.SRT_DELETADO
                    || it.xidTranslateTypePlanTestPump.action == ValueDefault.SRT_DELETED
                    || it.xidTranslateTypePlanTestPump.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTranslateTypePlanTestPump.actionNumber == ValueDefault.REMOVED
                    || it.xidTranslateTypePlanTestPump.actionNumber == ValueDefault.DELETADO
                    || it.xidTranslateTypePlanTestPump.actionNumber == ValueDefault.DELETED
                ) {

                    val translateTypePlanTestPumpEntityAuxList: List<TranslateTypePlanTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestPumpTestDao()
                            ?.findTranslateTypePlanTestPump(
                                it.translateTypePlanTestPump.translateTypePlanTestPumpPK!!.language,
                                it.translateTypePlanTestPump.translateTypePlanTestPumpPK!!.typePlanTest.id,
                                it.translateTypePlanTestPump.translation
                            )

                    val xidTypePlanTestEntityList: List<XidTranslateTypePlanTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestPumpTestDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                it.translateTypePlanTestPump.translateTypePlanTestPumpPK!!.language,
                                it.translateTypePlanTestPump.translation!!,
                                it.translateTypePlanTestPump.translateTypePlanTestPumpPK!!.typePlanTest.id.toString()
                            )

                    if (!translateTypePlanTestPumpEntityAuxList.isNullOrEmpty()) {
                        translateTypePlanTestPumpEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestPumpTestDao()?.delete(it01)
                        }
                    }

                    if (!xidTypePlanTestEntityList.isNullOrEmpty()) {
                        xidTypePlanTestEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestPumpTestDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var translateTypePlanTestPumpEntityAux: TranslateTypePlanTestPumpEntity? = null

                    val translateTypePlanTestPumpEntityAuxList: List<TranslateTypePlanTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestPumpTestDao()
                            ?.findTranslateTypePlanTestPump(
                                it.translateTypePlanTestPump.translateTypePlanTestPumpPK!!.language,
                                it.translateTypePlanTestPump.translateTypePlanTestPumpPK!!.typePlanTest.id,
                                it.translateTypePlanTestPump.translation!!
                            )

                    if (!translateTypePlanTestPumpEntityAuxList.isNullOrEmpty()
                        && translateTypePlanTestPumpEntityAuxList.size >= 2
                    ) {
                        translateTypePlanTestPumpEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestPumpTestDao()?.delete(it1)
                        }
                    } else if (!translateTypePlanTestPumpEntityAuxList.isNullOrEmpty()) {
                        translateTypePlanTestPumpEntityAux =
                            translateTypePlanTestPumpEntityAuxList[0]
                    }

                    //delay(10)

                    if (translateTypePlanTestPumpEntityAux == null
                        || (translateTypePlanTestPumpEntityAux != null
                                && translateTypePlanTestPumpEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestPumpTestDao()
                            ?.insert(translateTypePlanTestPump)
                    } else if (translateTypePlanTestPumpEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        translateTypePlanTestPump.id = translateTypePlanTestPumpEntityAux.id
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestPumpTestDao()
                            ?.update(translateTypePlanTestPump)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestPumpTestDao()
                                ?.insert(xidTranslateTypePlanTestPump)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestPumpTestDao()
                                ?.update(xidTranslateTypePlanTestPump)
                        }
                    }
                }
                if (it.xidTranslateTypePlanTestPump.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTranslateTypePlanTestPump.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestPumpTestDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID,
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
        return AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestPumpTestDao()?.maxXid()
            ?: GlobalUtil.method.recoverSharedPreferences(
                spConfiguration,
                PARAMETER_TRANSLATE_TYPE_PLAN_PUMP_CURRENT_XID
            )?.toInt() ?: 0
    }
}