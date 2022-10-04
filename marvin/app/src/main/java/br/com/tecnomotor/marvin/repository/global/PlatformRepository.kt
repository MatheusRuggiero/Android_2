package br.com.tecnomotor.marvin.repository.global

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.global.PlatformSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.global.PlatformEntity
import br.com.tecnomotor.marvin.dao.entities.global.XidPlatformEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class PlatformRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    fun saveListObjectSynchronized(list: List<PlatformSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_PLATFORM_CURRENT_XID
                )!!.toInt()
                val platform = PlatformEntity()
                platform.id = it.platform.id
                platform.name = it.platform.name!!
                platform.pressureMax = it.platform.pressureMax

                val xidPlatform = XidPlatformEntity()
                xidPlatform.id = it.xidPlatform.id
                xidPlatform.action = it.xidPlatform.action
                xidPlatform.actionNumber = it.xidPlatform.actionNumber
                xidPlatform.brand = it.xidPlatform.brand
                xidPlatform.brandId = it.xidPlatform.brandId
                xidPlatform.classResponsible = it.xidPlatform.classResponsible
                xidPlatform.createdDateObject = it.xidPlatform.createdDateObject
                xidPlatform.identification = it.xidPlatform.identification
                xidPlatform.identificationAux = it.xidPlatform.identificationAux
                xidPlatform.lastDateUpdate = it.xidPlatform.lastDateUpdate
                xidPlatform.objectCompositionId = it.xidPlatform.objectCompositionId
                xidPlatform.objectId = it.xidPlatform.objectId
                xidPlatform.variantNameTable1 = it.xidPlatform.variantNameTable1
                xidPlatform.variantNameTable2 = it.xidPlatform.variantNameTable2
                xidPlatform.variantNameTable3 = it.xidPlatform.variantNameTable3
                xidPlatform.variantNameTable4 = it.xidPlatform.variantNameTable4
                xidPlatform.variantNameTable5 = it.xidPlatform.variantNameTable5
                xidPlatform.variantNameTable6 = it.xidPlatform.variantNameTable6
                xidPlatform.responsibleId = it.xidPlatform.responsibleId
                xidPlatform.responsibleName = it.xidPlatform.responsibleName
                xidPlatform.responsibleToken = it.xidPlatform.responsibleToken
                xidPlatform.revisionId = it.xidPlatform.revisionId
                xidPlatform.revisionMotivation = it.xidPlatform.revisionMotivation
                xidPlatform.revisionMotivationObjectId = it.xidPlatform.revisionMotivationObjectId
                xidPlatform.revisionObjectMotivation = it.xidPlatform.revisionObjectMotivation
                xidPlatform.revisionObjectObservation = it.xidPlatform.revisionObjectObservation
                xidPlatform.versionDatabase = it.xidPlatform.versionDatabase
                xidPlatform.xid = it.xidPlatform.xid
                xidPlatform.platformId = it.xidPlatform.platformId
                xidPlatform.platform = it.xidPlatform.platform
                xidPlatform.genericAuxInfo1 = it.xidPlatform.genericAuxInfo1
                xidPlatform.genericAuxInfo2 = it.xidPlatform.genericAuxInfo2
                xidPlatform.genericAuxInfo3 = it.xidPlatform.genericAuxInfo3
                xidPlatform.genericAuxInfo4 = it.xidPlatform.genericAuxInfo4
                xidPlatform.genericAuxIdentification1 = it.xidPlatform.genericAuxIdentification1
                xidPlatform.genericAuxIdentification2 = it.xidPlatform.genericAuxIdentification2
                xidPlatform.genericAuxIdentification3 = it.xidPlatform.genericAuxIdentification3
                xidPlatform.genericAuxIdentification4 = it.xidPlatform.genericAuxIdentification4
                xidPlatform.forAnythingExtra1 = it.xidPlatform.forAnythingExtra1
                xidPlatform.forAnythingExtra2 = it.xidPlatform.forAnythingExtra2
                xidPlatform.forAnythingExtra3 = it.xidPlatform.forAnythingExtra3
                xidPlatform.forAnythingExtra4 = it.xidPlatform.forAnythingExtra4
                xidPlatform.backupDatabase = it.xidPlatform.backupDatabase
                xidPlatform.betaDatabaseReleased = it.xidPlatform.betaDatabaseReleased
                xidPlatform.developmentDatabaseReleased = it.xidPlatform.developmentDatabaseReleased
                xidPlatform.experimentalDatabaseReleased = it.xidPlatform.experimentalDatabaseReleased
                xidPlatform.officialDatabaseReleased = it.xidPlatform.officialDatabaseReleased
                xidPlatform.other1DatabaseReleased = it.xidPlatform.other1DatabaseReleased
                xidPlatform.other2DatabaseReleased = it.xidPlatform.other2DatabaseReleased

                if (it.xidPlatform.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPlatform.action == ValueDefault.SRT_REMOVED
                    || it.xidPlatform.action == ValueDefault.SRT_DELETADO
                    || it.xidPlatform.action == ValueDefault.SRT_DELETED
                    || it.xidPlatform.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPlatform.actionNumber == ValueDefault.REMOVED
                    || it.xidPlatform.actionNumber == ValueDefault.DELETADO
                    || it.xidPlatform.actionNumber == ValueDefault.DELETED
                ) {

                    val platformEntityAuxList: List<PlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.platformDao()?.findByIdList(it.platform.id)

                    val xidPlatformEntityList: List<XidPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidPlatformDao()?.findByObjectIdList(it.platform.id.toString())

                    if (!platformEntityAuxList.isNullOrEmpty()) {
                        platformEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.platformDao()?.delete(it01)
                        }
                    }

                    if (!xidPlatformEntityList.isNullOrEmpty()) {
                        xidPlatformEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidPlatformDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var platformEntityAux: PlatformEntity? = null

                    val platformEntityAuxList: List<PlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.platformDao()?.findByIdList(it.platform.id)

                    if (!platformEntityAuxList.isNullOrEmpty()
                        && platformEntityAuxList.size >= 2
                    ) {
                        platformEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.platformDao()?.delete(it1)
                        }
                    } else if (!platformEntityAuxList.isNullOrEmpty()) {
                        platformEntityAux = platformEntityAuxList[0]
                    }

                    //delay(10)

                    if (platformEntityAux == null
                        || (platformEntityAux != null
                                && platformEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.platformDao()?.insert(platform)
                    } else if (platformEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        platform.id = platformEntityAux.id
                        AppDatabase.getDatabase(application.applicationContext)?.platformDao()?.update(platform)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPlatformDao()?.insert(xidPlatform)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPlatformDao()?.update(xidPlatform)
                        }
                    }
                }

                if (it.xidPlatform.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPlatform.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_PLATFORM_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidPlatformDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PLATFORM_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PLATFORM_CURRENT_XID,
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
            PARAMETER_PLATFORM_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidPlatformDao()?.maxXid()
            ?: 0
    }

}