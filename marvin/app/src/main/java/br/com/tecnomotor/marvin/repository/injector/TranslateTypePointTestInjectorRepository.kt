package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.injector.TranslateTypePointTestInjectorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.injector.TranslateTypePointTestInjectorEntity
import br.com.tecnomotor.marvin.dao.entities.injector.XidTranslateTypePointTestInjectorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class TranslateTypePointTestInjectorRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<TranslateTypePointTestInjectorSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID
                )!!.toInt()
                val translateTypePointTestInjector =
                    TranslateTypePointTestInjectorEntity()
                translateTypePointTestInjector.translate = it.translateTypePointTestInjector.translate
                translateTypePointTestInjector.language = it.translateTypePointTestInjector.translateTypePointInjectorTestPK!!.language!!
                translateTypePointTestInjector.typePointInjectorTestId =
                    it.translateTypePointTestInjector.translateTypePointInjectorTestPK!!.typePointTestInjector.id

                val xidTranslateTypePointTestInjector =
                    XidTranslateTypePointTestInjectorEntity()
                xidTranslateTypePointTestInjector.id = it.xidTranslateTypePointTestInjector.id
                xidTranslateTypePointTestInjector.action = it.xidTranslateTypePointTestInjector.action
                xidTranslateTypePointTestInjector.actionNumber = it.xidTranslateTypePointTestInjector.actionNumber
                xidTranslateTypePointTestInjector.brand = it.xidTranslateTypePointTestInjector.brand
                xidTranslateTypePointTestInjector.brandId = it.xidTranslateTypePointTestInjector.brandId
                xidTranslateTypePointTestInjector.classResponsible = it.xidTranslateTypePointTestInjector.classResponsible
                xidTranslateTypePointTestInjector.createdDateObject = it.xidTranslateTypePointTestInjector.createdDateObject
                xidTranslateTypePointTestInjector.identification = it.xidTranslateTypePointTestInjector.identification
                xidTranslateTypePointTestInjector.identificationAux = it.xidTranslateTypePointTestInjector.identificationAux
                xidTranslateTypePointTestInjector.lastDateUpdate = it.xidTranslateTypePointTestInjector.lastDateUpdate
                xidTranslateTypePointTestInjector.objectCompositionId = it.xidTranslateTypePointTestInjector.objectCompositionId
                xidTranslateTypePointTestInjector.objectId = it.xidTranslateTypePointTestInjector.objectId
                xidTranslateTypePointTestInjector.variantNameTable1 = it.xidTranslateTypePointTestInjector.variantNameTable1
                xidTranslateTypePointTestInjector.variantNameTable2 = it.xidTranslateTypePointTestInjector.variantNameTable2
                xidTranslateTypePointTestInjector.variantNameTable3 = it.xidTranslateTypePointTestInjector.variantNameTable3
                xidTranslateTypePointTestInjector.variantNameTable4 = it.xidTranslateTypePointTestInjector.variantNameTable4
                xidTranslateTypePointTestInjector.variantNameTable5 = it.xidTranslateTypePointTestInjector.variantNameTable5
                xidTranslateTypePointTestInjector.variantNameTable6 = it.xidTranslateTypePointTestInjector.variantNameTable6
                xidTranslateTypePointTestInjector.responsibleId = it.xidTranslateTypePointTestInjector.responsibleId
                xidTranslateTypePointTestInjector.responsibleName = it.xidTranslateTypePointTestInjector.responsibleName
                xidTranslateTypePointTestInjector.responsibleToken = it.xidTranslateTypePointTestInjector.responsibleToken
                xidTranslateTypePointTestInjector.revisionId = it.xidTranslateTypePointTestInjector.revisionId
                xidTranslateTypePointTestInjector.revisionMotivation = it.xidTranslateTypePointTestInjector.revisionMotivation
                xidTranslateTypePointTestInjector.revisionMotivationObjectId =
                    it.xidTranslateTypePointTestInjector.revisionMotivationObjectId
                xidTranslateTypePointTestInjector.revisionObjectMotivation = it.xidTranslateTypePointTestInjector.revisionObjectMotivation
                xidTranslateTypePointTestInjector.revisionObjectObservation = it.xidTranslateTypePointTestInjector.revisionObjectObservation
                xidTranslateTypePointTestInjector.versionDatabase = it.xidTranslateTypePointTestInjector.versionDatabase
                xidTranslateTypePointTestInjector.xid = it.xidTranslateTypePointTestInjector.xid
                xidTranslateTypePointTestInjector.platformId = it.xidTranslateTypePointTestInjector.platformId
                xidTranslateTypePointTestInjector.platform = it.xidTranslateTypePointTestInjector.platform
                xidTranslateTypePointTestInjector.genericAuxInfo1 = it.xidTranslateTypePointTestInjector.genericAuxInfo1
                xidTranslateTypePointTestInjector.genericAuxInfo2 = it.xidTranslateTypePointTestInjector.genericAuxInfo2
                xidTranslateTypePointTestInjector.genericAuxInfo3 = it.xidTranslateTypePointTestInjector.genericAuxInfo3
                xidTranslateTypePointTestInjector.genericAuxInfo4 = it.xidTranslateTypePointTestInjector.genericAuxInfo4
                xidTranslateTypePointTestInjector.genericAuxIdentification1 = it.xidTranslateTypePointTestInjector.genericAuxIdentification1
                xidTranslateTypePointTestInjector.genericAuxIdentification2 = it.xidTranslateTypePointTestInjector.genericAuxIdentification2
                xidTranslateTypePointTestInjector.genericAuxIdentification3 = it.xidTranslateTypePointTestInjector.genericAuxIdentification3
                xidTranslateTypePointTestInjector.genericAuxIdentification4 = it.xidTranslateTypePointTestInjector.genericAuxIdentification4
                xidTranslateTypePointTestInjector.forAnythingExtra1 = it.xidTranslateTypePointTestInjector.forAnythingExtra1
                xidTranslateTypePointTestInjector.forAnythingExtra2 = it.xidTranslateTypePointTestInjector.forAnythingExtra2
                xidTranslateTypePointTestInjector.forAnythingExtra3 = it.xidTranslateTypePointTestInjector.forAnythingExtra3
                xidTranslateTypePointTestInjector.forAnythingExtra4 = it.xidTranslateTypePointTestInjector.forAnythingExtra4
                xidTranslateTypePointTestInjector.backupDatabase = it.xidTranslateTypePointTestInjector.backupDatabase
                xidTranslateTypePointTestInjector.betaDatabaseReleased = it.xidTranslateTypePointTestInjector.betaDatabaseReleased
                xidTranslateTypePointTestInjector.developmentDatabaseReleased =
                    it.xidTranslateTypePointTestInjector.developmentDatabaseReleased
                xidTranslateTypePointTestInjector.experimentalDatabaseReleased =
                    it.xidTranslateTypePointTestInjector.experimentalDatabaseReleased
                xidTranslateTypePointTestInjector.officialDatabaseReleased = it.xidTranslateTypePointTestInjector.officialDatabaseReleased
                xidTranslateTypePointTestInjector.other1DatabaseReleased = it.xidTranslateTypePointTestInjector.other1DatabaseReleased
                xidTranslateTypePointTestInjector.other2DatabaseReleased = it.xidTranslateTypePointTestInjector.other2DatabaseReleased

                if (it.xidTranslateTypePointTestInjector.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTranslateTypePointTestInjector.action == ValueDefault.SRT_REMOVED
                    || it.xidTranslateTypePointTestInjector.action == ValueDefault.SRT_DELETADO
                    || it.xidTranslateTypePointTestInjector.action == ValueDefault.SRT_DELETED
                    || it.xidTranslateTypePointTestInjector.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTranslateTypePointTestInjector.actionNumber == ValueDefault.REMOVED
                    || it.xidTranslateTypePointTestInjector.actionNumber == ValueDefault.DELETADO
                    || it.xidTranslateTypePointTestInjector.actionNumber == ValueDefault.DELETED
                ) {

                    val translateTypePointTestInjectorEntityAuxList: List<TranslateTypePointTestInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestInjectorDao()
                            ?.findByTranslateTypePointTestInjector(
                                it.translateTypePointTestInjector.translateTypePointInjectorTestPK!!.language,
                                it.translateTypePointTestInjector.translateTypePointInjectorTestPK!!.typePointTestInjector.id,
                                it.translateTypePointTestInjector.translate
                            )

                    val xidTranslateTypePointTestInjectorEntityList: List<XidTranslateTypePointTestInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestInjectorDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                it.translateTypePointTestInjector.translateTypePointInjectorTestPK!!.language!!,
                                it.translateTypePointTestInjector.translate!!,
                                it.translateTypePointTestInjector.translateTypePointInjectorTestPK!!.typePointTestInjector.id.toString()
                            )

                    if (!translateTypePointTestInjectorEntityAuxList.isNullOrEmpty()) {
                        translateTypePointTestInjectorEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestInjectorDao()?.delete(it01)
                        }
                    }

                    if (!xidTranslateTypePointTestInjectorEntityList.isNullOrEmpty()) {
                        xidTranslateTypePointTestInjectorEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestInjectorDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var translateTypePointTestInjectorEntityAux: TranslateTypePointTestInjectorEntity? = null

                    val translateTypePointTestInjectorEntityAuxList: List<TranslateTypePointTestInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestInjectorDao()
                            ?.findByTranslateTypePointTestInjector(
                                it.translateTypePointTestInjector.translateTypePointInjectorTestPK!!.language,
                                it.translateTypePointTestInjector.translateTypePointInjectorTestPK!!.typePointTestInjector.id,
                                it.translateTypePointTestInjector.translate
                            )

                    if (!translateTypePointTestInjectorEntityAuxList.isNullOrEmpty()
                        && translateTypePointTestInjectorEntityAuxList.size >= 2
                    ) {
                        translateTypePointTestInjectorEntityAuxList.forEach { it0 ->

                            AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestInjectorDao()?.delete(it0)
                        }
                    } else if (!translateTypePointTestInjectorEntityAuxList.isNullOrEmpty()) {
                        translateTypePointTestInjectorEntityAux =
                            translateTypePointTestInjectorEntityAuxList[0]
                    }

                    //delay(10)

                    if (translateTypePointTestInjectorEntityAux == null
                        || (translateTypePointTestInjectorEntityAux != null
                                && translateTypePointTestInjectorEntityAux.typePointInjectorTestId == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestInjectorDao()
                            ?.insert(translateTypePointTestInjector)
                    } else if (translateTypePointTestInjectorEntityAux.typePointInjectorTestId != 0) {
                        bUpdate = true
                        existInDatabase = true
                        translateTypePointTestInjector.typePointInjectorTestId = translateTypePointTestInjectorEntityAux.typePointInjectorTestId
                        AppDatabase.getDatabase(application.applicationContext)?.translateTypePointTestInjectorDao()
                            ?.update(translateTypePointTestInjector)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestInjectorDao()
                                ?.insert(xidTranslateTypePointTestInjector)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestInjectorDao()
                                ?.update(xidTranslateTypePointTestInjector)
                        }
                    }
                }
                if (it.xidTranslateTypePointTestInjector.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTranslateTypePointTestInjector.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestInjectorDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID,
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
            PARAMETER_TRANSLATE_TYPE_POINT_INJECTOR_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidTranslateTypePointTestInjectorDao()?.maxXid()
            ?: 0
    }
}