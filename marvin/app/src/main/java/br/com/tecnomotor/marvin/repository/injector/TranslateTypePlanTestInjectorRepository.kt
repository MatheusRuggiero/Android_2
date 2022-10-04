package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.injector.TranslateTypePlanTestInjectorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.injector.TranslateTypePlanTestInjectorEntity
import br.com.tecnomotor.marvin.dao.entities.injector.XidTranslateTypePlanTestInjectorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class TranslateTypePlanTestInjectorRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<TranslateTypePlanTestInjectorSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID
                )!!.toInt()
                val translateTypePlanTestInjector =
                    TranslateTypePlanTestInjectorEntity()
                translateTypePlanTestInjector.translate = it.translateTypePlanTestInjector.translate
                translateTypePlanTestInjector.typePlanTestId =
                    it.translateTypePlanTestInjector.translateTypePlanTestInjectorPK!!.typePlanTest.id
                translateTypePlanTestInjector.language = it.translateTypePlanTestInjector.translateTypePlanTestInjectorPK!!.language!!

                val xidTranslateTypePlanTestInjector = XidTranslateTypePlanTestInjectorEntity()
                xidTranslateTypePlanTestInjector.id = it.xidTranslateTypePlanTestInjector.id
                xidTranslateTypePlanTestInjector.action = it.xidTranslateTypePlanTestInjector.action
                xidTranslateTypePlanTestInjector.actionNumber = it.xidTranslateTypePlanTestInjector.actionNumber
                xidTranslateTypePlanTestInjector.brand = it.xidTranslateTypePlanTestInjector.brand
                xidTranslateTypePlanTestInjector.brandId = it.xidTranslateTypePlanTestInjector.brandId
                xidTranslateTypePlanTestInjector.classResponsible = it.xidTranslateTypePlanTestInjector.classResponsible
                xidTranslateTypePlanTestInjector.createdDateObject = it.xidTranslateTypePlanTestInjector.createdDateObject
                xidTranslateTypePlanTestInjector.identification = it.xidTranslateTypePlanTestInjector.identification
                xidTranslateTypePlanTestInjector.identificationAux = it.xidTranslateTypePlanTestInjector.identificationAux
                xidTranslateTypePlanTestInjector.lastDateUpdate = it.xidTranslateTypePlanTestInjector.lastDateUpdate
                xidTranslateTypePlanTestInjector.objectCompositionId = it.xidTranslateTypePlanTestInjector.objectCompositionId
                xidTranslateTypePlanTestInjector.objectId = it.xidTranslateTypePlanTestInjector.objectId
                xidTranslateTypePlanTestInjector.variantNameTable1 = it.xidTranslateTypePlanTestInjector.variantNameTable1
                xidTranslateTypePlanTestInjector.variantNameTable2 = it.xidTranslateTypePlanTestInjector.variantNameTable2
                xidTranslateTypePlanTestInjector.variantNameTable3 = it.xidTranslateTypePlanTestInjector.variantNameTable3
                xidTranslateTypePlanTestInjector.variantNameTable4 = it.xidTranslateTypePlanTestInjector.variantNameTable4
                xidTranslateTypePlanTestInjector.variantNameTable5 = it.xidTranslateTypePlanTestInjector.variantNameTable5
                xidTranslateTypePlanTestInjector.variantNameTable6 = it.xidTranslateTypePlanTestInjector.variantNameTable6
                xidTranslateTypePlanTestInjector.responsibleId = it.xidTranslateTypePlanTestInjector.responsibleId
                xidTranslateTypePlanTestInjector.responsibleName = it.xidTranslateTypePlanTestInjector.responsibleName
                xidTranslateTypePlanTestInjector.responsibleToken = it.xidTranslateTypePlanTestInjector.responsibleToken
                xidTranslateTypePlanTestInjector.revisionId = it.xidTranslateTypePlanTestInjector.revisionId
                xidTranslateTypePlanTestInjector.revisionMotivation = it.xidTranslateTypePlanTestInjector.revisionMotivation
                xidTranslateTypePlanTestInjector.revisionMotivationObjectId = it.xidTranslateTypePlanTestInjector.revisionMotivationObjectId
                xidTranslateTypePlanTestInjector.revisionObjectMotivation = it.xidTranslateTypePlanTestInjector.revisionObjectMotivation
                xidTranslateTypePlanTestInjector.revisionObjectObservation = it.xidTranslateTypePlanTestInjector.revisionObjectObservation
                xidTranslateTypePlanTestInjector.versionDatabase = it.xidTranslateTypePlanTestInjector.versionDatabase
                xidTranslateTypePlanTestInjector.xid = it.xidTranslateTypePlanTestInjector.xid
                xidTranslateTypePlanTestInjector.platformId = it.xidTranslateTypePlanTestInjector.platformId
                xidTranslateTypePlanTestInjector.platform = it.xidTranslateTypePlanTestInjector.platform
                xidTranslateTypePlanTestInjector.genericAuxInfo1 = it.xidTranslateTypePlanTestInjector.genericAuxInfo1
                xidTranslateTypePlanTestInjector.genericAuxInfo2 = it.xidTranslateTypePlanTestInjector.genericAuxInfo2
                xidTranslateTypePlanTestInjector.genericAuxInfo3 = it.xidTranslateTypePlanTestInjector.genericAuxInfo3
                xidTranslateTypePlanTestInjector.genericAuxInfo4 = it.xidTranslateTypePlanTestInjector.genericAuxInfo4
                xidTranslateTypePlanTestInjector.genericAuxIdentification1 = it.xidTranslateTypePlanTestInjector.genericAuxIdentification1
                xidTranslateTypePlanTestInjector.genericAuxIdentification2 = it.xidTranslateTypePlanTestInjector.genericAuxIdentification2
                xidTranslateTypePlanTestInjector.genericAuxIdentification3 = it.xidTranslateTypePlanTestInjector.genericAuxIdentification3
                xidTranslateTypePlanTestInjector.genericAuxIdentification4 = it.xidTranslateTypePlanTestInjector.genericAuxIdentification4
                xidTranslateTypePlanTestInjector.forAnythingExtra1 = it.xidTranslateTypePlanTestInjector.forAnythingExtra1
                xidTranslateTypePlanTestInjector.forAnythingExtra2 = it.xidTranslateTypePlanTestInjector.forAnythingExtra2
                xidTranslateTypePlanTestInjector.forAnythingExtra3 = it.xidTranslateTypePlanTestInjector.forAnythingExtra3
                xidTranslateTypePlanTestInjector.forAnythingExtra4 = it.xidTranslateTypePlanTestInjector.forAnythingExtra4
                xidTranslateTypePlanTestInjector.backupDatabase = it.xidTranslateTypePlanTestInjector.backupDatabase
                xidTranslateTypePlanTestInjector.betaDatabaseReleased = it.xidTranslateTypePlanTestInjector.betaDatabaseReleased
                xidTranslateTypePlanTestInjector.developmentDatabaseReleased =
                    it.xidTranslateTypePlanTestInjector.developmentDatabaseReleased
                xidTranslateTypePlanTestInjector.experimentalDatabaseReleased =
                    it.xidTranslateTypePlanTestInjector.experimentalDatabaseReleased
                xidTranslateTypePlanTestInjector.officialDatabaseReleased = it.xidTranslateTypePlanTestInjector.officialDatabaseReleased
                xidTranslateTypePlanTestInjector.other1DatabaseReleased = it.xidTranslateTypePlanTestInjector.other1DatabaseReleased
                xidTranslateTypePlanTestInjector.other2DatabaseReleased = it.xidTranslateTypePlanTestInjector.other2DatabaseReleased

                if (it.xidTranslateTypePlanTestInjector.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTranslateTypePlanTestInjector.action == ValueDefault.SRT_REMOVED
                    || it.xidTranslateTypePlanTestInjector.action == ValueDefault.SRT_DELETADO
                    || it.xidTranslateTypePlanTestInjector.action == ValueDefault.SRT_DELETED
                    || it.xidTranslateTypePlanTestInjector.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTranslateTypePlanTestInjector.actionNumber == ValueDefault.REMOVED
                    || it.xidTranslateTypePlanTestInjector.actionNumber == ValueDefault.DELETADO
                    || it.xidTranslateTypePlanTestInjector.actionNumber == ValueDefault.DELETED
                ) {

                    val translateTypePlanTestInjectorEntityAuxList: List<TranslateTypePlanTestInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestInjectorDao()
                            ?.findTranslateTypePlanTestInjector(
                                it.translateTypePlanTestInjector.translateTypePlanTestInjectorPK!!.language,
                                it.translateTypePlanTestInjector.translateTypePlanTestInjectorPK!!.typePlanTest.id,
                                it.translateTypePlanTestInjector.translate
                            )

                    val xidTranslateTypePlanTestInjectorEntityList: List<XidTranslateTypePlanTestInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestInjectorDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                it.translateTypePlanTestInjector.translateTypePlanTestInjectorPK!!.language!!,
                                it.translateTypePlanTestInjector.translate!!,
                                it.translateTypePlanTestInjector.translateTypePlanTestInjectorPK!!.typePlanTest.id.toString()
                            )

                    if (!translateTypePlanTestInjectorEntityAuxList.isNullOrEmpty()) {
                        translateTypePlanTestInjectorEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestInjectorDao()?.delete(it01)
                        }
                    }

                    if (!xidTranslateTypePlanTestInjectorEntityList.isNullOrEmpty()) {
                        xidTranslateTypePlanTestInjectorEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestInjectorDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var translateTypePlanTestInjectorEntityAux: TranslateTypePlanTestInjectorEntity? = null

                    val translateTypePlanTestInjectorEntityAuxList: List<TranslateTypePlanTestInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestInjectorDao()
                            ?.findTranslateTypePlanTestInjector(
                                it.translateTypePlanTestInjector.translateTypePlanTestInjectorPK!!.language,
                                it.translateTypePlanTestInjector.translateTypePlanTestInjectorPK!!.typePlanTest.id,
                                it.translateTypePlanTestInjector.translate
                            )

                    if (!translateTypePlanTestInjectorEntityAuxList.isNullOrEmpty()
                        && translateTypePlanTestInjectorEntityAuxList.size >= 2
                    ) {
                        translateTypePlanTestInjectorEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestInjectorDao()?.delete(it1)
                        }
                    } else if (!translateTypePlanTestInjectorEntityAuxList.isNullOrEmpty()) {
                        translateTypePlanTestInjectorEntityAux =
                            translateTypePlanTestInjectorEntityAuxList[0]
                    }

                    //delay(10)

                    if (translateTypePlanTestInjectorEntityAux == null
                        || (translateTypePlanTestInjectorEntityAux != null
                                && translateTypePlanTestInjectorEntityAux.typePlanTestId == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestInjectorDao()
                            ?.insert(translateTypePlanTestInjector)
                    } else if (translateTypePlanTestInjectorEntityAux.typePlanTestId != 0) {
                        bUpdate = true
                        existInDatabase = true
                        translateTypePlanTestInjector.typePlanTestId = translateTypePlanTestInjectorEntityAux.typePlanTestId
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePlanTestInjectorDao()
                            ?.update(translateTypePlanTestInjector)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestInjectorDao()
                                ?.insert(xidTranslateTypePlanTestInjector)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestInjectorDao()
                                ?.update(xidTranslateTypePlanTestInjector)
                        }
                    }
                }
                if (it.xidTranslateTypePlanTestInjector.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTranslateTypePlanTestInjector.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestInjectorDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID,
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
            PARAMETER_TRANSLATE_TYPE_PLAN_TEST_INJECTOR_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePlanTestInjectorDao()?.maxXid()
            ?: 0
    }
}