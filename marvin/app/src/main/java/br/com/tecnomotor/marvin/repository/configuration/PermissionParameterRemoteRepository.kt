package br.com.tecnomotor.marvin.repository.configuration

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.api.v1.model.configuration.PermissionParameterRemoteSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.configuration.XidPermissionParameterRemoteEntity
import br.com.tecnomotor.marvin.model.configuration.PermissionParameterRemote
import br.com.tecnomotor.marvin.dao.entities.configuration.PermissionParameterRemoteEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class PermissionParameterRemoteRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<PermissionParameterRemote>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun saveListObjectSynchronized(list: List<PermissionParameterRemoteSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
                )!!.toInt()
                val permissionParameterRemoteEntity = PermissionParameterRemoteEntity()
                permissionParameterRemoteEntity.group = it.permissionParameterRemote.permissonParameterRemotePK!!.group
                permissionParameterRemoteEntity.parameterRemoteEntityId =
                    it.permissionParameterRemote.permissonParameterRemotePK!!.parameterRemote!!.id
                permissionParameterRemoteEntity.typeOperation = it.permissionParameterRemote.typeOperation

                val xidPermissionParameterRemote = XidPermissionParameterRemoteEntity()
                xidPermissionParameterRemote.id = it.xidPermissionParameterRemote.id
                xidPermissionParameterRemote.action = it.xidPermissionParameterRemote.action
                xidPermissionParameterRemote.actionNumber = it.xidPermissionParameterRemote.actionNumber
                xidPermissionParameterRemote.brandId = it.xidPermissionParameterRemote.brandId
                xidPermissionParameterRemote.brand = it.xidPermissionParameterRemote.brand
                xidPermissionParameterRemote.classResponsible = it.xidPermissionParameterRemote.classResponsible
                xidPermissionParameterRemote.createdDateObject = it.xidPermissionParameterRemote.createdDateObject
                xidPermissionParameterRemote.identification = it.xidPermissionParameterRemote.identification
                xidPermissionParameterRemote.identificationAux = it.xidPermissionParameterRemote.identificationAux
                xidPermissionParameterRemote.lastDateUpdate = it.xidPermissionParameterRemote.lastDateUpdate
                xidPermissionParameterRemote.objectCompositionId = it.xidPermissionParameterRemote.objectCompositionId
                xidPermissionParameterRemote.objectId = it.xidPermissionParameterRemote.objectId
                xidPermissionParameterRemote.variantNameTable1 = it.xidPermissionParameterRemote.variantNameTable1
                xidPermissionParameterRemote.variantNameTable2 = it.xidPermissionParameterRemote.variantNameTable2
                xidPermissionParameterRemote.variantNameTable3 = it.xidPermissionParameterRemote.variantNameTable3
                xidPermissionParameterRemote.variantNameTable4 = it.xidPermissionParameterRemote.variantNameTable4
                xidPermissionParameterRemote.variantNameTable5 = it.xidPermissionParameterRemote.variantNameTable5
                xidPermissionParameterRemote.variantNameTable6 = it.xidPermissionParameterRemote.variantNameTable6
                xidPermissionParameterRemote.responsibleId = it.xidPermissionParameterRemote.responsibleId
                xidPermissionParameterRemote.responsibleName = it.xidPermissionParameterRemote.responsibleName
                xidPermissionParameterRemote.responsibleToken = it.xidPermissionParameterRemote.responsibleToken
                xidPermissionParameterRemote.revisionId = it.xidPermissionParameterRemote.revisionId
                xidPermissionParameterRemote.revisionMotivation = it.xidPermissionParameterRemote.revisionMotivation
                xidPermissionParameterRemote.revisionMotivationObjectId = it.xidPermissionParameterRemote.revisionMotivationObjectId
                xidPermissionParameterRemote.revisionObjectMotivation = it.xidPermissionParameterRemote.revisionObjectMotivation
                xidPermissionParameterRemote.revisionObjectObservation = it.xidPermissionParameterRemote.revisionObjectObservation
                xidPermissionParameterRemote.versionDatabase = it.xidPermissionParameterRemote.versionDatabase
                xidPermissionParameterRemote.xid = it.xidPermissionParameterRemote.xid
                xidPermissionParameterRemote.platformId = it.xidPermissionParameterRemote.platformId
                xidPermissionParameterRemote.platform = it.xidPermissionParameterRemote.platform
                xidPermissionParameterRemote.genericAuxInfo1 = it.xidPermissionParameterRemote.genericAuxInfo1
                xidPermissionParameterRemote.genericAuxInfo2 = it.xidPermissionParameterRemote.genericAuxInfo2
                xidPermissionParameterRemote.genericAuxInfo3 = it.xidPermissionParameterRemote.genericAuxInfo3
                xidPermissionParameterRemote.genericAuxInfo4 = it.xidPermissionParameterRemote.genericAuxInfo4
                xidPermissionParameterRemote.genericAuxIdentification1 = it.xidPermissionParameterRemote.genericAuxIdentification1
                xidPermissionParameterRemote.genericAuxIdentification2 = it.xidPermissionParameterRemote.genericAuxIdentification2
                xidPermissionParameterRemote.genericAuxIdentification3 = it.xidPermissionParameterRemote.genericAuxIdentification3
                xidPermissionParameterRemote.genericAuxIdentification4 = it.xidPermissionParameterRemote.genericAuxIdentification4
                xidPermissionParameterRemote.forAnythingExtra1 = it.xidPermissionParameterRemote.forAnythingExtra1
                xidPermissionParameterRemote.forAnythingExtra2 = it.xidPermissionParameterRemote.forAnythingExtra2
                xidPermissionParameterRemote.forAnythingExtra3 = it.xidPermissionParameterRemote.forAnythingExtra3
                xidPermissionParameterRemote.forAnythingExtra4 = it.xidPermissionParameterRemote.forAnythingExtra4
                xidPermissionParameterRemote.backupDatabase = it.xidPermissionParameterRemote.backupDatabase
                xidPermissionParameterRemote.betaDatabaseReleased = it.xidPermissionParameterRemote.betaDatabaseReleased
                xidPermissionParameterRemote.developmentDatabaseReleased = it.xidPermissionParameterRemote.developmentDatabaseReleased
                xidPermissionParameterRemote.experimentalDatabaseReleased = it.xidPermissionParameterRemote.experimentalDatabaseReleased
                xidPermissionParameterRemote.officialDatabaseReleased = it.xidPermissionParameterRemote.officialDatabaseReleased
                xidPermissionParameterRemote.other1DatabaseReleased = it.xidPermissionParameterRemote.other1DatabaseReleased
                xidPermissionParameterRemote.other2DatabaseReleased = it.xidPermissionParameterRemote.other2DatabaseReleased

                if (it.xidPermissionParameterRemote.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPermissionParameterRemote.action == ValueDefault.SRT_REMOVED
                    || it.xidPermissionParameterRemote.action == ValueDefault.SRT_DELETADO
                    || it.xidPermissionParameterRemote.action == ValueDefault.SRT_DELETED
                    || it.xidPermissionParameterRemote.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPermissionParameterRemote.actionNumber == ValueDefault.REMOVED
                    || it.xidPermissionParameterRemote.actionNumber == ValueDefault.DELETADO
                    || it.xidPermissionParameterRemote.actionNumber == ValueDefault.DELETED
                ) {

                    val permissionParameterRemoteEntityAuxList: List<PermissionParameterRemoteEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.permissionParameterRemoteDao()?.findByIdList(
                            permissionParameterRemoteEntity.group,
                            permissionParameterRemoteEntity.parameterRemoteEntityId,
                            permissionParameterRemoteEntity.typeOperation
                        )

                    if (!permissionParameterRemoteEntityAuxList.isNullOrEmpty()) {
                        permissionParameterRemoteEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.permissionParameterRemoteDao()?.delete(it01)
                        }
                    }

                    val xidPermissionParameterRemoteEntity: List<XidPermissionParameterRemoteEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidPermissionParameterRemoteDao()?.findByPermissionParameterList(
                            permissionParameterRemoteEntity.parameterRemoteEntityId.toString(),
                            permissionParameterRemoteEntity.group.toString(),
                            permissionParameterRemoteEntity.typeOperation.toString()
                        )

                    if (!xidPermissionParameterRemoteEntity.isNullOrEmpty()) {
                        xidPermissionParameterRemoteEntity.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidPermissionParameterRemoteDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var permissionParameterRemoteEntityAux: PermissionParameterRemoteEntity? = null
                    val permissionParameterRemoteEntityAuxList: List<PermissionParameterRemoteEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.permissionParameterRemoteDao()?.findByIdList(
                            permissionParameterRemoteEntity.group,
                            permissionParameterRemoteEntity.parameterRemoteEntityId,
                            permissionParameterRemoteEntity.typeOperation
                        )

                    if (!permissionParameterRemoteEntityAuxList.isNullOrEmpty()
                        && permissionParameterRemoteEntityAuxList.size >= 2
                    ) {
                        permissionParameterRemoteEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.permissionParameterRemoteDao()?.delete(it1)
                        }
                    } else if (!permissionParameterRemoteEntityAuxList.isNullOrEmpty()) {
                        permissionParameterRemoteEntityAux = permissionParameterRemoteEntityAuxList[0]
                    }

                    //delay(10)

                    if (permissionParameterRemoteEntityAux == null
                        || (permissionParameterRemoteEntityAux != null
                                && permissionParameterRemoteEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.permissionParameterRemoteDao()
                            ?.insert(permissionParameterRemoteEntity)
                    } else if (permissionParameterRemoteEntityAux.id != 0) {
                        bUpdate = false
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.permissionParameterRemoteDao()
                            ?.update(permissionParameterRemoteEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPermissionParameterRemoteDao()
                                ?.insert(xidPermissionParameterRemote)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPermissionParameterRemoteDao()
                                ?.update(xidPermissionParameterRemote)
                        }
                    }
                }

                if (it.xidPermissionParameterRemote.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPermissionParameterRemote.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidPermissionParameterRemoteDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID,
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
            PARAMETER_PERMISSION_PARAMETER_REMOTE_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidPermissionParameterRemoteDao()?.maxXid()
            ?: 0
    }

}