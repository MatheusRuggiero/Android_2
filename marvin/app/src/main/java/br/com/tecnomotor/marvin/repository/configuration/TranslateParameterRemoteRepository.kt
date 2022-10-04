package br.com.tecnomotor.marvin.repository.configuration

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.configuration.TranslateParameterRemoteSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.configuration.XidTranslateParameterRemoteEntity
import br.com.tecnomotor.marvin.model.configuration.TranslateParameterRemoteEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class TranslateParameterRemoteRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<TranslateParameterRemoteSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
                )!!.toInt()
                val translateParameterRemote = TranslateParameterRemoteEntity()
                translateParameterRemote.parId = it.translateParameterRemote.translateParameterRemotePK!!.parameterRemote!!.id
                translateParameterRemote.parName = it.translateParameterRemote.translateParameterRemotePK!!.parameterRemote!!.name
                translateParameterRemote.language = it.translateParameterRemote.translateParameterRemotePK!!.language
                translateParameterRemote.translate = it.translateParameterRemote.translate

                val xidTranslateParameterRemote = XidTranslateParameterRemoteEntity()
                xidTranslateParameterRemote.id = it.xidTranslateParameterRemote.id
                xidTranslateParameterRemote.action = it.xidTranslateParameterRemote.action
                xidTranslateParameterRemote.actionNumber = it.xidTranslateParameterRemote.actionNumber
                xidTranslateParameterRemote.brand = it.xidTranslateParameterRemote.brand
                xidTranslateParameterRemote.brandId = it.xidTranslateParameterRemote.brandId
                xidTranslateParameterRemote.classResponsible = it.xidTranslateParameterRemote.classResponsible
                xidTranslateParameterRemote.createdDateObject = it.xidTranslateParameterRemote.createdDateObject
                xidTranslateParameterRemote.identification = it.xidTranslateParameterRemote.identification
                xidTranslateParameterRemote.identificationAux = it.xidTranslateParameterRemote.identificationAux
                xidTranslateParameterRemote.lastDateUpdate = it.xidTranslateParameterRemote.lastDateUpdate
                xidTranslateParameterRemote.objectCompositionId = it.xidTranslateParameterRemote.objectCompositionId
                xidTranslateParameterRemote.objectId = it.xidTranslateParameterRemote.objectId
                xidTranslateParameterRemote.variantNameTable1 = it.xidTranslateParameterRemote.variantNameTable1
                xidTranslateParameterRemote.variantNameTable2 = it.xidTranslateParameterRemote.variantNameTable2
                xidTranslateParameterRemote.variantNameTable3 = it.xidTranslateParameterRemote.variantNameTable3
                xidTranslateParameterRemote.variantNameTable4 = it.xidTranslateParameterRemote.variantNameTable4
                xidTranslateParameterRemote.variantNameTable5 = it.xidTranslateParameterRemote.variantNameTable5
                xidTranslateParameterRemote.variantNameTable6 = it.xidTranslateParameterRemote.variantNameTable6
                xidTranslateParameterRemote.responsibleId = it.xidTranslateParameterRemote.responsibleId
                xidTranslateParameterRemote.responsibleName = it.xidTranslateParameterRemote.responsibleName
                xidTranslateParameterRemote.responsibleToken = it.xidTranslateParameterRemote.responsibleToken
                xidTranslateParameterRemote.revisionId = it.xidTranslateParameterRemote.revisionId
                xidTranslateParameterRemote.revisionMotivation = it.xidTranslateParameterRemote.revisionMotivation
                xidTranslateParameterRemote.revisionMotivationObjectId = it.xidTranslateParameterRemote.revisionMotivationObjectId
                xidTranslateParameterRemote.revisionObjectMotivation = it.xidTranslateParameterRemote.revisionObjectMotivation
                xidTranslateParameterRemote.revisionObjectObservation = it.xidTranslateParameterRemote.revisionObjectObservation
                xidTranslateParameterRemote.versionDatabase = it.xidTranslateParameterRemote.versionDatabase
                xidTranslateParameterRemote.xid = it.xidTranslateParameterRemote.xid
                xidTranslateParameterRemote.platformId = it.xidTranslateParameterRemote.platformId
                xidTranslateParameterRemote.platform = it.xidTranslateParameterRemote.platform
                xidTranslateParameterRemote.genericAuxInfo1 = it.xidTranslateParameterRemote.genericAuxInfo1
                xidTranslateParameterRemote.genericAuxInfo2 = it.xidTranslateParameterRemote.genericAuxInfo2
                xidTranslateParameterRemote.genericAuxInfo3 = it.xidTranslateParameterRemote.genericAuxInfo3
                xidTranslateParameterRemote.genericAuxInfo4 = it.xidTranslateParameterRemote.genericAuxInfo4
                xidTranslateParameterRemote.genericAuxIdentification1 = it.xidTranslateParameterRemote.genericAuxIdentification1
                xidTranslateParameterRemote.genericAuxIdentification2 = it.xidTranslateParameterRemote.genericAuxIdentification2
                xidTranslateParameterRemote.genericAuxIdentification3 = it.xidTranslateParameterRemote.genericAuxIdentification3
                xidTranslateParameterRemote.genericAuxIdentification4 = it.xidTranslateParameterRemote.genericAuxIdentification4
                xidTranslateParameterRemote.forAnythingExtra1 = it.xidTranslateParameterRemote.forAnythingExtra1
                xidTranslateParameterRemote.forAnythingExtra2 = it.xidTranslateParameterRemote.forAnythingExtra2
                xidTranslateParameterRemote.forAnythingExtra3 = it.xidTranslateParameterRemote.forAnythingExtra3
                xidTranslateParameterRemote.forAnythingExtra4 = it.xidTranslateParameterRemote.forAnythingExtra4
                xidTranslateParameterRemote.backupDatabase = it.xidTranslateParameterRemote.backupDatabase
                xidTranslateParameterRemote.betaDatabaseReleased = it.xidTranslateParameterRemote.betaDatabaseReleased
                xidTranslateParameterRemote.developmentDatabaseReleased = it.xidTranslateParameterRemote.developmentDatabaseReleased
                xidTranslateParameterRemote.experimentalDatabaseReleased = it.xidTranslateParameterRemote.experimentalDatabaseReleased
                xidTranslateParameterRemote.officialDatabaseReleased = it.xidTranslateParameterRemote.officialDatabaseReleased
                xidTranslateParameterRemote.other1DatabaseReleased = it.xidTranslateParameterRemote.other1DatabaseReleased
                xidTranslateParameterRemote.other2DatabaseReleased = it.xidTranslateParameterRemote.other2DatabaseReleased

                if (it.xidTranslateParameterRemote.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTranslateParameterRemote.action == ValueDefault.SRT_REMOVED
                    || it.xidTranslateParameterRemote.action == ValueDefault.SRT_DELETADO
                    || it.xidTranslateParameterRemote.action == ValueDefault.SRT_DELETED
                    || it.xidTranslateParameterRemote.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTranslateParameterRemote.actionNumber == ValueDefault.REMOVED
                    || it.xidTranslateParameterRemote.actionNumber == ValueDefault.DELETADO
                    || it.xidTranslateParameterRemote.actionNumber == ValueDefault.DELETED
                ) {

                    val translateParameterRemoteEntityAuxList: List<TranslateParameterRemoteEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateParameterRemoteDao()
                            ?.findTranslate(
                                it.translateParameterRemote.translateParameterRemotePK!!.language,
                                it.translateParameterRemote.translateParameterRemotePK!!.parameterRemote!!.id,
                                it.translateParameterRemote.translate
                            )

                    val xidTranslateParameterRemoteEntityList: List<XidTranslateParameterRemoteEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTranslateParameterRemoteDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                it.translateParameterRemote.translateParameterRemotePK!!.language,
                                it.translateParameterRemote.translate.toString(),
                                it.translateParameterRemote.translateParameterRemotePK!!.parameterRemote!!.id.toString()
                            )

                    if (!translateParameterRemoteEntityAuxList.isNullOrEmpty()) {
                        translateParameterRemoteEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.translateParameterRemoteDao()?.delete(it01)
                        }
                    }

                    if (!xidTranslateParameterRemoteEntityList.isNullOrEmpty()) {
                        xidTranslateParameterRemoteEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateParameterRemoteDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var translateParameterRemoteEntityAux: TranslateParameterRemoteEntity? = null

                    val translateParameterRemoteEntityAuxList: List<TranslateParameterRemoteEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.translateParameterRemoteDao()
                            ?.findTranslate(
                                it.translateParameterRemote.translateParameterRemotePK!!.language,
                                it.translateParameterRemote.translateParameterRemotePK!!.parameterRemote!!.id,
                                it.translateParameterRemote.translate
                            )

                    if (!translateParameterRemoteEntityAuxList.isNullOrEmpty()
                        && translateParameterRemoteEntityAuxList.size >= 2
                    ) {
                        translateParameterRemoteEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.translateParameterRemoteDao()?.delete(it1)
                        }
                    } else if (!translateParameterRemoteEntityAuxList.isNullOrEmpty()) {
                        translateParameterRemoteEntityAux =
                            translateParameterRemoteEntityAuxList[0]
                    }

                    //delay(10)

                    if (translateParameterRemoteEntityAux == null
                        || (translateParameterRemoteEntityAux != null
                                && translateParameterRemoteEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.translateParameterRemoteDao()
                            ?.insert(translateParameterRemote)
                    } else if (translateParameterRemoteEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        translateParameterRemote.id = translateParameterRemoteEntityAux.id
                        AppDatabase.getDatabase(application.applicationContext)?.translateParameterRemoteDao()
                            ?.update(translateParameterRemote)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateParameterRemoteDao()
                                ?.insert(xidTranslateParameterRemote)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTranslateParameterRemoteDao()
                                ?.update(xidTranslateParameterRemote)
                        }
                    }
                }
                if (it.xidTranslateParameterRemote.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTranslateParameterRemote.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTranslateParameterRemoteDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID,
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
            PARAMETER_TRANSLATE_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidTranslateParameterRemoteDao()?.maxXid()
            ?: 0
    }
}