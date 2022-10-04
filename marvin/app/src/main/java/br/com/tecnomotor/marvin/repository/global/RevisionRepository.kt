package br.com.tecnomotor.marvin.repository.global

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.global.RevisionSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity
import br.com.tecnomotor.marvin.dao.entities.global.XidRevisionEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class RevisionRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<RevisionEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findByUsingRevisionId(id: Int): LiveData<Resource<List<RevisionEntity>?>> {
        mediator.addSource(mAppDatabase?.revisionDao()!!.findByUsingRevisionId(id)) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<RevisionEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<RevisionEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }


    fun findByUsingRevisionIdList(id: Int): List<RevisionEntity> {
        return mAppDatabase?.revisionDao()!!.findByUsingRevisionIdList(id)
    }

    fun saveListObjectSynchronized(list: List<RevisionSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    ParameterSharedPreferences.PARAMETER_VERSION_CURRENT_XID
                )!!.toInt()
                val revision = RevisionEntity()
                revision.id = it.revision.id
                revision.motivation = it.revision.motivation
                revision.dateHour = it.revision.dateHour

                val xidRevision = XidRevisionEntity()
                xidRevision.id = it.xidRevision.id
                xidRevision.action = it.xidRevision.action
                xidRevision.actionNumber = it.xidRevision.actionNumber
                xidRevision.brand = it.xidRevision.brand
                xidRevision.brandId = it.xidRevision.brandId
                xidRevision.classResponsible = it.xidRevision.classResponsible
                xidRevision.createdDateObject = it.xidRevision.createdDateObject
                xidRevision.identification = it.xidRevision.identification
                xidRevision.identificationAux = it.xidRevision.identificationAux
                xidRevision.lastDateUpdate = it.xidRevision.lastDateUpdate
                xidRevision.objectCompositionId = it.xidRevision.objectCompositionId
                xidRevision.objectId = it.xidRevision.objectId
                xidRevision.variantNameTable1 = it.xidRevision.variantNameTable1
                xidRevision.variantNameTable2 = it.xidRevision.variantNameTable2
                xidRevision.variantNameTable3 = it.xidRevision.variantNameTable3
                xidRevision.variantNameTable4 = it.xidRevision.variantNameTable4
                xidRevision.variantNameTable5 = it.xidRevision.variantNameTable5
                xidRevision.variantNameTable6 = it.xidRevision.variantNameTable6
                xidRevision.responsibleId = it.xidRevision.responsibleId
                xidRevision.responsibleName = it.xidRevision.responsibleName
                xidRevision.responsibleToken = it.xidRevision.responsibleToken
                xidRevision.revisionId = it.xidRevision.revisionId
                xidRevision.revisionMotivation = it.xidRevision.revisionMotivation
                xidRevision.revisionMotivationObjectId = it.xidRevision.revisionMotivationObjectId
                xidRevision.revisionObjectMotivation = it.xidRevision.revisionObjectMotivation
                xidRevision.revisionObjectObservation = it.xidRevision.revisionObjectObservation
                xidRevision.versionDatabase = it.xidRevision.versionDatabase
                xidRevision.xid = it.xidRevision.xid
                xidRevision.platformId = it.xidRevision.platformId
                xidRevision.platform = it.xidRevision.platform
                xidRevision.backupDatabase = it.xidRevision.backupDatabase
                xidRevision.genericAuxInfo1 = it.xidRevision.genericAuxInfo1
                xidRevision.genericAuxInfo2 = it.xidRevision.genericAuxInfo2
                xidRevision.genericAuxInfo3 = it.xidRevision.genericAuxInfo3
                xidRevision.genericAuxInfo4 = it.xidRevision.genericAuxInfo4
                xidRevision.genericAuxIdentification1 = it.xidRevision.genericAuxIdentification1
                xidRevision.genericAuxIdentification2 = it.xidRevision.genericAuxIdentification2
                xidRevision.genericAuxIdentification3 = it.xidRevision.genericAuxIdentification3
                xidRevision.genericAuxIdentification4 = it.xidRevision.genericAuxIdentification4
                xidRevision.forAnythingExtra1 = it.xidRevision.forAnythingExtra1
                xidRevision.forAnythingExtra2 = it.xidRevision.forAnythingExtra2
                xidRevision.forAnythingExtra3 = it.xidRevision.forAnythingExtra3
                xidRevision.forAnythingExtra4 = it.xidRevision.forAnythingExtra4
                xidRevision.backupDatabase = it.xidRevision.backupDatabase
                xidRevision.betaDatabaseReleased = it.xidRevision.betaDatabaseReleased
                xidRevision.developmentDatabaseReleased = it.xidRevision.developmentDatabaseReleased
                xidRevision.experimentalDatabaseReleased = it.xidRevision.experimentalDatabaseReleased
                xidRevision.officialDatabaseReleased = it.xidRevision.officialDatabaseReleased
                xidRevision.other1DatabaseReleased = it.xidRevision.other1DatabaseReleased
                xidRevision.other2DatabaseReleased = it.xidRevision.other2DatabaseReleased

                if (it.xidRevision.action == ValueDefault.SRT_REMOVIDO
                    || it.xidRevision.action == ValueDefault.SRT_REMOVED
                    || it.xidRevision.action == ValueDefault.SRT_DELETADO
                    || it.xidRevision.action == ValueDefault.SRT_DELETED
                    || it.xidRevision.actionNumber == ValueDefault.REMOVIDO
                    || it.xidRevision.actionNumber == ValueDefault.REMOVED
                    || it.xidRevision.actionNumber == ValueDefault.DELETADO
                    || it.xidRevision.actionNumber == ValueDefault.DELETED
                ) {

                    val revisionEntityAuxList: List<RevisionEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionDao()?.findByIdList(it.revision.id)

                    val xidRevisionEntityList: List<XidRevisionEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidRevisionDao()?.findByObjectIdList(it.revision.id.toString())

                    if (!revisionEntityAuxList.isNullOrEmpty()) {
                        revisionEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionDao()?.delete(it01)
                        }
                    }

                    if (!xidRevisionEntityList.isNullOrEmpty()) {
                        xidRevisionEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var revisionEntityAux: RevisionEntity? = null
                    val revisionEntityAuxList: List<RevisionEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionDao()?.findByRevision(it.revision.id)

                    if (!revisionEntityAuxList.isNullOrEmpty()
                        && revisionEntityAuxList.size >= 2
                    ) {
                        revisionEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.revisionDao()?.delete(it1)
                        }
                    } else if (!revisionEntityAuxList.isNullOrEmpty()) {
                        revisionEntityAux = revisionEntityAuxList[0]
                    }

                    //delay(10)

                    if (revisionEntityAux == null
                        || (revisionEntityAux != null
                                && revisionEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionDao()?.insert(revision)
                    } else if (revisionEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionDao()?.update(revision)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionDao()?.insert(xidRevision)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionDao()?.update(xidRevision)
                        }
                    }
                }
                if (it.xidRevision.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidRevision.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_REVISION_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidRevisionDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_CURRENT_XID,
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
            PARAMETER_REVISION_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidRevisionDao()?.maxXid()
            ?: 0
    }


}