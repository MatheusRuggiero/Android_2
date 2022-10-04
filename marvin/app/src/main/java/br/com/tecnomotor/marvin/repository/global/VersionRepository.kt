package br.com.tecnomotor.marvin.repository.global

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.global.VersionSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.global.VersionEntity
import br.com.tecnomotor.marvin.dao.entities.global.XidVersionEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VERSION_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class VersionRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    fun saveListObjectSynchronized(list: List<VersionSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_VERSION_CURRENT_XID
                )!!.toInt()
                val version = VersionEntity()
                version.id = it.version.id
                version.released = it.version.released
                version.dateTimeRelease = it.version.dateTimeRelease

                val xidVersion = XidVersionEntity()
                xidVersion.id = it.xidVersion.id
                xidVersion.action = it.xidVersion.action
                xidVersion.actionNumber = it.xidVersion.actionNumber
                xidVersion.brand = it.xidVersion.brand
                xidVersion.brandId = it.xidVersion.brandId
                xidVersion.classResponsible = it.xidVersion.classResponsible
                xidVersion.createdDateObject = it.xidVersion.createdDateObject
                xidVersion.identification = it.xidVersion.identification
                xidVersion.identificationAux = it.xidVersion.identificationAux
                xidVersion.lastDateUpdate = it.xidVersion.lastDateUpdate
                xidVersion.objectCompositionId = it.xidVersion.objectCompositionId
                xidVersion.objectId = it.xidVersion.objectId
                xidVersion.variantNameTable1 = it.xidVersion.variantNameTable1
                xidVersion.variantNameTable2 = it.xidVersion.variantNameTable2
                xidVersion.variantNameTable3 = it.xidVersion.variantNameTable3
                xidVersion.variantNameTable4 = it.xidVersion.variantNameTable4
                xidVersion.variantNameTable5 = it.xidVersion.variantNameTable5
                xidVersion.variantNameTable6 = it.xidVersion.variantNameTable6
                xidVersion.responsibleId = it.xidVersion.responsibleId
                xidVersion.responsibleName = it.xidVersion.responsibleName
                xidVersion.responsibleToken = it.xidVersion.responsibleToken
                xidVersion.revisionId = it.xidVersion.revisionId
                xidVersion.revisionMotivation = it.xidVersion.revisionMotivation
                xidVersion.revisionMotivationObjectId = it.xidVersion.revisionMotivationObjectId
                xidVersion.revisionObjectMotivation = it.xidVersion.revisionObjectMotivation
                xidVersion.revisionObjectObservation = it.xidVersion.revisionObjectObservation
                xidVersion.versionDatabase = it.xidVersion.versionDatabase
                xidVersion.xid = it.xidVersion.xid
                xidVersion.platformId = it.xidVersion.platformId
                xidVersion.platform = it.xidVersion.platform
                xidVersion.backupDatabase = it.xidVersion.backupDatabase
                xidVersion.genericAuxInfo1 = it.xidVersion.genericAuxInfo1
                xidVersion.genericAuxInfo2 = it.xidVersion.genericAuxInfo2
                xidVersion.genericAuxInfo3 = it.xidVersion.genericAuxInfo3
                xidVersion.genericAuxInfo4 = it.xidVersion.genericAuxInfo4
                xidVersion.genericAuxIdentification1 = it.xidVersion.genericAuxIdentification1
                xidVersion.genericAuxIdentification2 = it.xidVersion.genericAuxIdentification2
                xidVersion.genericAuxIdentification3 = it.xidVersion.genericAuxIdentification3
                xidVersion.genericAuxIdentification4 = it.xidVersion.genericAuxIdentification4
                xidVersion.forAnythingExtra1 = it.xidVersion.forAnythingExtra1
                xidVersion.forAnythingExtra2 = it.xidVersion.forAnythingExtra2
                xidVersion.forAnythingExtra3 = it.xidVersion.forAnythingExtra3
                xidVersion.forAnythingExtra4 = it.xidVersion.forAnythingExtra4
                xidVersion.backupDatabase = it.xidVersion.backupDatabase
                xidVersion.betaDatabaseReleased = it.xidVersion.betaDatabaseReleased
                xidVersion.developmentDatabaseReleased = it.xidVersion.developmentDatabaseReleased
                xidVersion.experimentalDatabaseReleased = it.xidVersion.experimentalDatabaseReleased
                xidVersion.officialDatabaseReleased = it.xidVersion.officialDatabaseReleased
                xidVersion.other1DatabaseReleased = it.xidVersion.other1DatabaseReleased
                xidVersion.other2DatabaseReleased = it.xidVersion.other2DatabaseReleased

                if (it.xidVersion.action == ValueDefault.SRT_REMOVIDO
                    || it.xidVersion.action == ValueDefault.SRT_REMOVED
                    || it.xidVersion.action == ValueDefault.SRT_DELETADO
                    || it.xidVersion.action == ValueDefault.SRT_DELETED
                    || it.xidVersion.actionNumber == ValueDefault.REMOVIDO
                    || it.xidVersion.actionNumber == ValueDefault.REMOVED
                    || it.xidVersion.actionNumber == ValueDefault.DELETADO
                    || it.xidVersion.actionNumber == ValueDefault.DELETED
                ) {
                    val versionEntityAuxList: List<VersionEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.versionDao()?.findByIdList(it.version.id)

                    val xidVersionEntityList: List<XidVersionEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidVersionDao()?.findByObjectIdList(it.version.id.toString())

                    if (!versionEntityAuxList.isNullOrEmpty()) {
                        versionEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.versionDao()?.delete(it01)
                        }
                    }

                    if (!xidVersionEntityList.isNullOrEmpty()) {
                        xidVersionEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidVersionDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var versionEntityAux: VersionEntity? = null

                    val versionEntityAuxList: List<VersionEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.versionDao()?.findByIdList(it.version.id)

                    if (!versionEntityAuxList.isNullOrEmpty()
                        && versionEntityAuxList.size >= 2
                    ) {
                        versionEntityAuxList.forEach { it0 ->

                            AppDatabase.getDatabase(application.applicationContext)?.versionDao()?.delete(it0)
                        }
                    } else if (!versionEntityAuxList.isNullOrEmpty()) {
                        versionEntityAux = versionEntityAuxList[0]
                    }

                    //delay(10)

                    if (versionEntityAux == null
                        || (versionEntityAux != null
                                && versionEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.versionDao()?.insert(version)
                    } else if (versionEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.versionDao()?.update(versionEntityAux)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidVersionDao()?.insert(xidVersion)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidVersionDao()?.update(xidVersion)
                        }
                    }
                }
                if (it.xidVersion.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidVersion.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_VERSION_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidVersionDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VERSION_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VERSION_CURRENT_XID,
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
            PARAMETER_VERSION_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidVersionDao()?.maxXid()
            ?: 0
    }

}