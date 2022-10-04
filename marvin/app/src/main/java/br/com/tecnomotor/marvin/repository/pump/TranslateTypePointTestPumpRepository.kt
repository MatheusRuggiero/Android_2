package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.pump.TranslateTypePointTestPumpSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.TranslateTypePointTestPumpEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidTranslateTypePointTestPumpEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class TranslateTypePointTestPumpRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<TranslateTypePointTestPumpSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID
                )!!.toInt()
                val translateTypePointTestPump = TranslateTypePointTestPumpEntity()
                translateTypePointTestPump.translate = it.translateTypePointTestPump.translate
                translateTypePointTestPump.typePoint = it.translateTypePointTestPump.translateTypePointTestPumpPK!!.typePointTestPump.id
                translateTypePointTestPump.language = it.translateTypePointTestPump.translateTypePointTestPumpPK!!.language

                val xidTranslateTypePointTestPump = XidTranslateTypePointTestPumpEntity()
                xidTranslateTypePointTestPump.id = it.xidTranslateTypePointTestPump.id
                xidTranslateTypePointTestPump.action = it.xidTranslateTypePointTestPump.action
                xidTranslateTypePointTestPump.actionNumber = it.xidTranslateTypePointTestPump.actionNumber
                xidTranslateTypePointTestPump.brand = it.xidTranslateTypePointTestPump.brand
                xidTranslateTypePointTestPump.brandId = it.xidTranslateTypePointTestPump.brandId
                xidTranslateTypePointTestPump.classResponsible = it.xidTranslateTypePointTestPump.classResponsible
                xidTranslateTypePointTestPump.createdDateObject = it.xidTranslateTypePointTestPump.createdDateObject
                xidTranslateTypePointTestPump.identification = it.xidTranslateTypePointTestPump.identification
                xidTranslateTypePointTestPump.identificationAux = it.xidTranslateTypePointTestPump.identificationAux
                xidTranslateTypePointTestPump.lastDateUpdate = it.xidTranslateTypePointTestPump.lastDateUpdate
                xidTranslateTypePointTestPump.objectCompositionId = it.xidTranslateTypePointTestPump.objectCompositionId
                xidTranslateTypePointTestPump.objectId = it.xidTranslateTypePointTestPump.objectId
                xidTranslateTypePointTestPump.variantNameTable1 = it.xidTranslateTypePointTestPump.variantNameTable1
                xidTranslateTypePointTestPump.variantNameTable2 = it.xidTranslateTypePointTestPump.variantNameTable2
                xidTranslateTypePointTestPump.variantNameTable3 = it.xidTranslateTypePointTestPump.variantNameTable3
                xidTranslateTypePointTestPump.variantNameTable4 = it.xidTranslateTypePointTestPump.variantNameTable4
                xidTranslateTypePointTestPump.variantNameTable5 = it.xidTranslateTypePointTestPump.variantNameTable5
                xidTranslateTypePointTestPump.variantNameTable6 = it.xidTranslateTypePointTestPump.variantNameTable6
                xidTranslateTypePointTestPump.responsibleId = it.xidTranslateTypePointTestPump.responsibleId
                xidTranslateTypePointTestPump.responsibleName = it.xidTranslateTypePointTestPump.responsibleName
                xidTranslateTypePointTestPump.responsibleToken = it.xidTranslateTypePointTestPump.responsibleToken
                xidTranslateTypePointTestPump.revisionId = it.xidTranslateTypePointTestPump.revisionId
                xidTranslateTypePointTestPump.revisionMotivation = it.xidTranslateTypePointTestPump.revisionMotivation
                xidTranslateTypePointTestPump.revisionMotivationObjectId = it.xidTranslateTypePointTestPump.revisionMotivationObjectId
                xidTranslateTypePointTestPump.revisionObjectMotivation = it.xidTranslateTypePointTestPump.revisionObjectMotivation
                xidTranslateTypePointTestPump.revisionObjectObservation = it.xidTranslateTypePointTestPump.revisionObjectObservation
                xidTranslateTypePointTestPump.versionDatabase = it.xidTranslateTypePointTestPump.versionDatabase
                xidTranslateTypePointTestPump.xid = it.xidTranslateTypePointTestPump.xid
                xidTranslateTypePointTestPump.platformId = it.xidTranslateTypePointTestPump.platformId
                xidTranslateTypePointTestPump.platform = it.xidTranslateTypePointTestPump.platform
                xidTranslateTypePointTestPump.genericAuxInfo1 = it.xidTranslateTypePointTestPump.genericAuxInfo1
                xidTranslateTypePointTestPump.genericAuxInfo2 = it.xidTranslateTypePointTestPump.genericAuxInfo2
                xidTranslateTypePointTestPump.genericAuxInfo3 = it.xidTranslateTypePointTestPump.genericAuxInfo3
                xidTranslateTypePointTestPump.genericAuxInfo4 = it.xidTranslateTypePointTestPump.genericAuxInfo4
                xidTranslateTypePointTestPump.genericAuxIdentification1 = it.xidTranslateTypePointTestPump.genericAuxIdentification1
                xidTranslateTypePointTestPump.genericAuxIdentification2 = it.xidTranslateTypePointTestPump.genericAuxIdentification2
                xidTranslateTypePointTestPump.genericAuxIdentification3 = it.xidTranslateTypePointTestPump.genericAuxIdentification3
                xidTranslateTypePointTestPump.genericAuxIdentification4 = it.xidTranslateTypePointTestPump.genericAuxIdentification4
                xidTranslateTypePointTestPump.forAnythingExtra1 = it.xidTranslateTypePointTestPump.forAnythingExtra1
                xidTranslateTypePointTestPump.forAnythingExtra2 = it.xidTranslateTypePointTestPump.forAnythingExtra2
                xidTranslateTypePointTestPump.forAnythingExtra3 = it.xidTranslateTypePointTestPump.forAnythingExtra3
                xidTranslateTypePointTestPump.forAnythingExtra4 = it.xidTranslateTypePointTestPump.forAnythingExtra4
                xidTranslateTypePointTestPump.backupDatabase = it.xidTranslateTypePointTestPump.backupDatabase
                xidTranslateTypePointTestPump.betaDatabaseReleased = it.xidTranslateTypePointTestPump.betaDatabaseReleased
                xidTranslateTypePointTestPump.developmentDatabaseReleased = it.xidTranslateTypePointTestPump.developmentDatabaseReleased
                xidTranslateTypePointTestPump.experimentalDatabaseReleased = it.xidTranslateTypePointTestPump.experimentalDatabaseReleased
                xidTranslateTypePointTestPump.officialDatabaseReleased = it.xidTranslateTypePointTestPump.officialDatabaseReleased
                xidTranslateTypePointTestPump.other1DatabaseReleased = it.xidTranslateTypePointTestPump.other1DatabaseReleased
                xidTranslateTypePointTestPump.other2DatabaseReleased = it.xidTranslateTypePointTestPump.other2DatabaseReleased

                if (it.xidTranslateTypePointTestPump.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTranslateTypePointTestPump.action == ValueDefault.SRT_REMOVED
                    || it.xidTranslateTypePointTestPump.action == ValueDefault.SRT_DELETADO
                    || it.xidTranslateTypePointTestPump.action == ValueDefault.SRT_DELETED
                    || it.xidTranslateTypePointTestPump.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTranslateTypePointTestPump.actionNumber == ValueDefault.REMOVED
                    || it.xidTranslateTypePointTestPump.actionNumber == ValueDefault.DELETADO
                    || it.xidTranslateTypePointTestPump.actionNumber == ValueDefault.DELETED
                ) {

                    val translateTypePointTestPumpEntityAuxList: List<TranslateTypePointTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestPumpDao()?.findTranslateTypePointTestPump(
                            it.translateTypePointTestPump.translateTypePointTestPumpPK!!.language,
                            it.translateTypePointTestPump.translateTypePointTestPumpPK!!.typePointTestPump.id,
                            it.translateTypePointTestPump.translate
                        )

                    val xidTranslateTypePointTestPumpEntityList: List<XidTranslateTypePointTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestPumpDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                it.translateTypePointTestPump.translateTypePointTestPumpPK!!.language,
                                it.translateTypePointTestPump.translate!!,
                                it.translateTypePointTestPump.translateTypePointTestPumpPK!!.typePointTestPump.id.toString()
                            )

                    if (!translateTypePointTestPumpEntityAuxList.isNullOrEmpty()) {
                        translateTypePointTestPumpEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestPumpDao()?.delete(it01)
                        }
                    }

                    if (!xidTranslateTypePointTestPumpEntityList.isNullOrEmpty()) {
                        xidTranslateTypePointTestPumpEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestPumpDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var translateTypePointTestPumpEntityAux: TranslateTypePointTestPumpEntity? = null

                    val translateTypePointTestPumpEntityAuxList: List<TranslateTypePointTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestPumpDao()?.findTranslateTypePointTestPump(
                            it.translateTypePointTestPump.translateTypePointTestPumpPK!!.language,
                            it.translateTypePointTestPump.translateTypePointTestPumpPK!!.typePointTestPump.id,
                            it.translateTypePointTestPump.translate
                        )

                    if (!translateTypePointTestPumpEntityAuxList.isNullOrEmpty()
                        && translateTypePointTestPumpEntityAuxList.size >= 2
                    ) {
                        translateTypePointTestPumpEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestPumpDao()?.delete(it1)
                        }
                    } else if (!translateTypePointTestPumpEntityAuxList.isNullOrEmpty()) {
                        translateTypePointTestPumpEntityAux =
                            translateTypePointTestPumpEntityAuxList[0]
                    }

                    //delay(10)

                    if (translateTypePointTestPumpEntityAux == null
                        || (translateTypePointTestPumpEntityAux != null
                                && translateTypePointTestPumpEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestPumpDao()?.insert(translateTypePointTestPump)
                    } else if (translateTypePointTestPumpEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        translateTypePointTestPump.id = translateTypePointTestPumpEntityAux.id
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestPumpDao()?.update(translateTypePointTestPump)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestPumpDao()
                                ?.insert(xidTranslateTypePointTestPump)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestPumpDao()
                                ?.update(xidTranslateTypePointTestPump)
                        }
                    }
                }
                if (it.xidTranslateTypePointTestPump.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTranslateTypePointTestPump.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestPumpDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID,
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
            PARAMETER_TRANSLATE_TYPE_POINT_TEST_PUMP_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestPumpDao()?.maxXid()
            ?: 0
    }
}