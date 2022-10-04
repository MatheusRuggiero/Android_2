package br.com.tecnomotor.marvin.repository.configuration

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.api.v1.model.configuration.ParameterRemoteSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.configuration.XidParameterRemoteEntity
import br.com.tecnomotor.marvin.dao.entities.configuration.ParameterRemoteEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PARAMETER_REMOTE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class ParameterRemoteRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<ParameterRemoteEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun saveListObjectSynchronized(list: List<ParameterRemoteSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_PARAMETER_REMOTE_CURRENT_XID
                )!!.toInt()
                val parameterRemote = ParameterRemoteEntity()
                parameterRemote.id = it.parameterRemote.id
                parameterRemote.name = it.parameterRemote.name
                parameterRemote.position = it.parameterRemote.position
                parameterRemote.sizeData = it.parameterRemote.sizeData
                parameterRemote.dataType = it.parameterRemote.dataType
                parameterRemote.plateType = it.parameterRemote.plateType
                parameterRemote.valueDefault = it.parameterRemote.valueDefault

                val xidParameterRemote = XidParameterRemoteEntity()
                xidParameterRemote.id = it.xidParameterRemote.id
                xidParameterRemote.action = it.xidParameterRemote.action
                xidParameterRemote.actionNumber = it.xidParameterRemote.actionNumber
                xidParameterRemote.brand = it.xidParameterRemote.brand
                xidParameterRemote.brandId = it.xidParameterRemote.brandId
                xidParameterRemote.classResponsible = it.xidParameterRemote.classResponsible
                xidParameterRemote.createdDateObject = it.xidParameterRemote.createdDateObject
                xidParameterRemote.identification = it.xidParameterRemote.identification
                xidParameterRemote.identificationAux = it.xidParameterRemote.identificationAux
                xidParameterRemote.lastDateUpdate = it.xidParameterRemote.lastDateUpdate
                xidParameterRemote.objectCompositionId = it.xidParameterRemote.objectCompositionId
                xidParameterRemote.objectId = it.xidParameterRemote.objectId
                xidParameterRemote.variantNameTable1 = it.xidParameterRemote.variantNameTable1
                xidParameterRemote.variantNameTable2 = it.xidParameterRemote.variantNameTable2
                xidParameterRemote.variantNameTable3 = it.xidParameterRemote.variantNameTable3
                xidParameterRemote.variantNameTable4 = it.xidParameterRemote.variantNameTable4
                xidParameterRemote.variantNameTable5 = it.xidParameterRemote.variantNameTable5
                xidParameterRemote.variantNameTable6 = it.xidParameterRemote.variantNameTable6
                xidParameterRemote.responsibleId = it.xidParameterRemote.responsibleId
                xidParameterRemote.responsibleName = it.xidParameterRemote.responsibleName
                xidParameterRemote.responsibleToken = it.xidParameterRemote.responsibleToken
                xidParameterRemote.revisionId = it.xidParameterRemote.revisionId
                xidParameterRemote.revisionMotivation = it.xidParameterRemote.revisionMotivation
                xidParameterRemote.revisionMotivationObjectId = it.xidParameterRemote.revisionMotivationObjectId
                xidParameterRemote.revisionObjectMotivation = it.xidParameterRemote.revisionObjectMotivation
                xidParameterRemote.revisionObjectObservation = it.xidParameterRemote.revisionObjectObservation
                xidParameterRemote.versionDatabase = it.xidParameterRemote.versionDatabase
                xidParameterRemote.xid = it.xidParameterRemote.xid
                xidParameterRemote.platformId = it.xidParameterRemote.platformId
                xidParameterRemote.platform = it.xidParameterRemote.platform
                xidParameterRemote.genericAuxInfo1 = it.xidParameterRemote.genericAuxInfo1
                xidParameterRemote.genericAuxInfo2 = it.xidParameterRemote.genericAuxInfo2
                xidParameterRemote.genericAuxInfo3 = it.xidParameterRemote.genericAuxInfo3
                xidParameterRemote.genericAuxInfo4 = it.xidParameterRemote.genericAuxInfo4
                xidParameterRemote.genericAuxIdentification1 = it.xidParameterRemote.genericAuxIdentification1
                xidParameterRemote.genericAuxIdentification2 = it.xidParameterRemote.genericAuxIdentification2
                xidParameterRemote.genericAuxIdentification3 = it.xidParameterRemote.genericAuxIdentification3
                xidParameterRemote.genericAuxIdentification4 = it.xidParameterRemote.genericAuxIdentification4
                xidParameterRemote.forAnythingExtra1 = it.xidParameterRemote.forAnythingExtra1
                xidParameterRemote.forAnythingExtra2 = it.xidParameterRemote.forAnythingExtra2
                xidParameterRemote.forAnythingExtra3 = it.xidParameterRemote.forAnythingExtra3
                xidParameterRemote.forAnythingExtra4 = it.xidParameterRemote.forAnythingExtra4
                xidParameterRemote.backupDatabase = it.xidParameterRemote.backupDatabase
                xidParameterRemote.betaDatabaseReleased = it.xidParameterRemote.betaDatabaseReleased
                xidParameterRemote.developmentDatabaseReleased = it.xidParameterRemote.developmentDatabaseReleased
                xidParameterRemote.experimentalDatabaseReleased = it.xidParameterRemote.experimentalDatabaseReleased
                xidParameterRemote.officialDatabaseReleased = it.xidParameterRemote.officialDatabaseReleased
                xidParameterRemote.other1DatabaseReleased = it.xidParameterRemote.other1DatabaseReleased
                xidParameterRemote.other2DatabaseReleased = it.xidParameterRemote.other2DatabaseReleased

                if (it.xidParameterRemote.action == ValueDefault.SRT_REMOVIDO
                    || it.xidParameterRemote.action == ValueDefault.SRT_REMOVED
                    || it.xidParameterRemote.action == ValueDefault.SRT_DELETADO
                    || it.xidParameterRemote.action == ValueDefault.SRT_DELETED
                    || it.xidParameterRemote.actionNumber == ValueDefault.REMOVIDO
                    || it.xidParameterRemote.actionNumber == ValueDefault.REMOVED
                    || it.xidParameterRemote.actionNumber == ValueDefault.DELETADO
                    || it.xidParameterRemote.actionNumber == ValueDefault.DELETED
                ) {

                    val parameterRemoteEntityAuxList: List<ParameterRemoteEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.parameterRemoteDao()?.findByIdList(it.parameterRemote.id)

                    val xidParameterRemoteEntityList: List<XidParameterRemoteEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidParameterRemoteDao()
                            ?.findByObjectIdList(it.parameterRemote.id.toString())

                    if (!parameterRemoteEntityAuxList.isNullOrEmpty()) {
                        parameterRemoteEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.parameterRemoteDao()?.delete(it01)
                        }
                    }

                    if (!xidParameterRemoteEntityList.isNullOrEmpty()) {
                        xidParameterRemoteEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidParameterRemoteDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var parameterRemoteEntityAux: ParameterRemoteEntity? = null

                    val parameterRemoteEntityAuxList: List<ParameterRemoteEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.parameterRemoteDao()?.findByIdList(it.parameterRemote.id)

                    if (!parameterRemoteEntityAuxList.isNullOrEmpty()
                        && parameterRemoteEntityAuxList.size >= 2
                    ) {
                        parameterRemoteEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.parameterRemoteDao()?.delete(it1)
                        }
                    } else if (!parameterRemoteEntityAuxList.isNullOrEmpty()) {
                        parameterRemoteEntityAux = parameterRemoteEntityAuxList[0]
                    }

                    //delay(10)

                    if (parameterRemoteEntityAux == null
                        || (parameterRemoteEntityAux != null
                                && parameterRemoteEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.parameterRemoteDao()?.insert(parameterRemote)
                    } else if (parameterRemoteEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.parameterRemoteDao()?.update(parameterRemoteEntityAux)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidParameterRemoteDao()?.insert(xidParameterRemote)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidParameterRemoteDao()?.update(xidParameterRemote)
                        }
                    }
                }
                if (it.xidParameterRemote.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidParameterRemote.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_PARAMETER_REMOTE_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidParameterRemoteDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PARAMETER_REMOTE_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PARAMETER_REMOTE_CURRENT_XID,
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
            PARAMETER_PARAMETER_REMOTE_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidParameterRemoteDao()?.maxXid()
            ?: 0
    }

}