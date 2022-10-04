package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.injector.InjectorPlatformSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlatformEntity
import br.com.tecnomotor.marvin.dao.entities.injector.XidInjectorPlatformEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class InjectorPlatformRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<InjectorPlatformSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_INJECTOR_PLATFORM_CURRENT_XID
                )!!.toInt()
                val injectorPlatform = InjectorPlatformEntity()
                injectorPlatform.injectorId = it.injectorPlatform.injectorPlatformPK.injetor.id
                injectorPlatform.platformId = it.injectorPlatform.injectorPlatformPK.platform.id
                injectorPlatform.adaptConnector = it.injectorPlatform.adaptConnector
                injectorPlatform.adaptPressure = it.injectorPlatform.adaptPressure
                injectorPlatform.adaptReturn = it.injectorPlatform.adaptReturn

                val xidInjectorPlatform = XidInjectorPlatformEntity()
                xidInjectorPlatform.id = it.xidInjectorPlatform.id
                xidInjectorPlatform.action = it.xidInjectorPlatform.action
                xidInjectorPlatform.actionNumber = it.xidInjectorPlatform.actionNumber
                xidInjectorPlatform.brand = it.xidInjectorPlatform.brand
                xidInjectorPlatform.brandId = it.xidInjectorPlatform.brandId
                xidInjectorPlatform.classResponsible = it.xidInjectorPlatform.classResponsible
                xidInjectorPlatform.createdDateObject = it.xidInjectorPlatform.createdDateObject
                xidInjectorPlatform.identification = it.xidInjectorPlatform.identification
                xidInjectorPlatform.identificationAux = it.xidInjectorPlatform.identificationAux
                xidInjectorPlatform.lastDateUpdate = it.xidInjectorPlatform.lastDateUpdate
                xidInjectorPlatform.objectCompositionId = it.xidInjectorPlatform.objectCompositionId
                xidInjectorPlatform.objectId = it.xidInjectorPlatform.objectId
                xidInjectorPlatform.variantNameTable1 = it.xidInjectorPlatform.variantNameTable1
                xidInjectorPlatform.variantNameTable2 = it.xidInjectorPlatform.variantNameTable2
                xidInjectorPlatform.variantNameTable3 = it.xidInjectorPlatform.variantNameTable3
                xidInjectorPlatform.variantNameTable4 = it.xidInjectorPlatform.variantNameTable4
                xidInjectorPlatform.variantNameTable5 = it.xidInjectorPlatform.variantNameTable5
                xidInjectorPlatform.variantNameTable6 = it.xidInjectorPlatform.variantNameTable6
                xidInjectorPlatform.responsibleId = it.xidInjectorPlatform.responsibleId
                xidInjectorPlatform.responsibleName = it.xidInjectorPlatform.responsibleName
                xidInjectorPlatform.responsibleToken = it.xidInjectorPlatform.responsibleToken
                xidInjectorPlatform.revisionId = it.xidInjectorPlatform.revisionId
                xidInjectorPlatform.revisionMotivation = it.xidInjectorPlatform.revisionMotivation
                xidInjectorPlatform.revisionMotivationObjectId = it.xidInjectorPlatform.revisionMotivationObjectId
                xidInjectorPlatform.revisionObjectMotivation = it.xidInjectorPlatform.revisionObjectMotivation
                xidInjectorPlatform.revisionObjectObservation = it.xidInjectorPlatform.revisionObjectObservation
                xidInjectorPlatform.versionDatabase = it.xidInjectorPlatform.versionDatabase
                xidInjectorPlatform.xid = it.xidInjectorPlatform.xid
                xidInjectorPlatform.platformId = it.xidInjectorPlatform.platformId
                xidInjectorPlatform.platform = it.xidInjectorPlatform.platform
                xidInjectorPlatform.genericAuxInfo1 = it.xidInjectorPlatform.genericAuxInfo1
                xidInjectorPlatform.genericAuxInfo2 = it.xidInjectorPlatform.genericAuxInfo2
                xidInjectorPlatform.genericAuxInfo3 = it.xidInjectorPlatform.genericAuxInfo3
                xidInjectorPlatform.genericAuxInfo4 = it.xidInjectorPlatform.genericAuxInfo4
                xidInjectorPlatform.genericAuxIdentification1 = it.xidInjectorPlatform.genericAuxIdentification1
                xidInjectorPlatform.genericAuxIdentification2 = it.xidInjectorPlatform.genericAuxIdentification2
                xidInjectorPlatform.genericAuxIdentification3 = it.xidInjectorPlatform.genericAuxIdentification3
                xidInjectorPlatform.genericAuxIdentification4 = it.xidInjectorPlatform.genericAuxIdentification4
                xidInjectorPlatform.forAnythingExtra1 = it.xidInjectorPlatform.forAnythingExtra1
                xidInjectorPlatform.forAnythingExtra2 = it.xidInjectorPlatform.forAnythingExtra2
                xidInjectorPlatform.forAnythingExtra3 = it.xidInjectorPlatform.forAnythingExtra3
                xidInjectorPlatform.forAnythingExtra4 = it.xidInjectorPlatform.forAnythingExtra4
                xidInjectorPlatform.backupDatabase = it.xidInjectorPlatform.backupDatabase
                xidInjectorPlatform.betaDatabaseReleased = it.xidInjectorPlatform.betaDatabaseReleased
                xidInjectorPlatform.developmentDatabaseReleased = it.xidInjectorPlatform.developmentDatabaseReleased
                xidInjectorPlatform.experimentalDatabaseReleased = it.xidInjectorPlatform.experimentalDatabaseReleased
                xidInjectorPlatform.officialDatabaseReleased = it.xidInjectorPlatform.officialDatabaseReleased
                xidInjectorPlatform.other1DatabaseReleased = it.xidInjectorPlatform.other1DatabaseReleased
                xidInjectorPlatform.other2DatabaseReleased = it.xidInjectorPlatform.other2DatabaseReleased

                if (it.xidInjectorPlatform.action == ValueDefault.SRT_REMOVIDO
                    || it.xidInjectorPlatform.action == ValueDefault.SRT_REMOVED
                    || it.xidInjectorPlatform.action == ValueDefault.SRT_DELETADO
                    || it.xidInjectorPlatform.action == ValueDefault.SRT_DELETED
                    || it.xidInjectorPlatform.actionNumber == ValueDefault.REMOVIDO
                    || it.xidInjectorPlatform.actionNumber == ValueDefault.REMOVED
                    || it.xidInjectorPlatform.actionNumber == ValueDefault.DELETADO
                    || it.xidInjectorPlatform.actionNumber == ValueDefault.DELETED
                ) {

                    val injectorPlatformEntityAuxList: List<InjectorPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformDao()?.findByIdComposite(
                            it.injectorPlatform.injectorPlatformPK.injetor.id,
                            it.injectorPlatform.injectorPlatformPK.platform.id
                        )

                    val xidInjectorPlatformEntityList: List<XidInjectorPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlatformDao()
                            ?.findByObjectIdAndCompositionId(
                                it.injectorPlatform.injectorPlatformPK.injetor.id.toString(),
                                it.injectorPlatform.injectorPlatformPK.platform.id.toString()
                            )

                    if (!injectorPlatformEntityAuxList.isNullOrEmpty()) {
                        injectorPlatformEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformDao()?.delete(it01)
                        }
                    }

                    if (!xidInjectorPlatformEntityList.isNullOrEmpty()) {
                        xidInjectorPlatformEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlatformDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var injectorPlatformEntityAux: InjectorPlatformEntity? = null

                    val injectorPlatformEntityAuxList: List<InjectorPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformDao()?.findByIdComposite(
                            it.injectorPlatform.injectorPlatformPK.injetor.id,
                            it.injectorPlatform.injectorPlatformPK.platform.id
                        )

                    if (!injectorPlatformEntityAuxList.isNullOrEmpty()
                        && injectorPlatformEntityAuxList.size >= 2
                    ) {
                        injectorPlatformEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformDao()?.delete(it1)
                        }
                    } else if (!injectorPlatformEntityAuxList.isNullOrEmpty()) {
                        injectorPlatformEntityAux = injectorPlatformEntityAuxList[0]
                    }

                    //delay(10)

                    if (injectorPlatformEntityAux == null
                        || (injectorPlatformEntityAux != null
                                && injectorPlatformEntityAux.injectorId == 0
                                && injectorPlatformEntityAux.platformId == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformDao()?.insert(injectorPlatform)
                    } else if (injectorPlatformEntityAux.injectorId != 0
                        && injectorPlatformEntityAux.platformId != 0
                    ) {
                        bUpdate = true
                        existInDatabase = true
                        injectorPlatform.platformId = injectorPlatformEntityAux.platformId
                        injectorPlatform.injectorId = injectorPlatformEntityAux.injectorId
                        AppDatabase.getDatabase(application.applicationContext)?.injectorPlatformDao()?.update(injectorPlatform)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlatformDao()?.insert(xidInjectorPlatform)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlatformDao()?.update(xidInjectorPlatform)
                        }
                    }
                }
                if (it.xidInjectorPlatform.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidInjectorPlatform.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_INJECTOR_PLATFORM_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlatformDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_INJECTOR_PLATFORM_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_INJECTOR_PLATFORM_CURRENT_XID,
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
            PARAMETER_INJECTOR_PLATFORM_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidInjectorPlatformDao()?.maxXid()
            ?: 0
    }

}