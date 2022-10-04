package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.injector.RevisionInjectorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity
import br.com.tecnomotor.marvin.dao.entities.injector.RevisionInjectorEntity
import br.com.tecnomotor.marvin.dao.entities.injector.XidRevisionInjectorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class RevisionInjectorRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<RevisionInjectorEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findRevisionById(id: Int): LiveData<RevisionEntity> {
        return mAppDatabase?.revisionDao()!!.findById(id)
    }

    fun findByUsingInjectorId(id: Int): LiveData<Resource<List<RevisionInjectorEntity>?>> {
        mediator.addSource(mAppDatabase?.revisionInjectorDao()!!.findByUsingInjectorId(id)) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<RevisionInjectorEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<RevisionInjectorEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }


    fun findByUsingInjectorIdList(id: Int): List<RevisionInjectorEntity>? {
        return mAppDatabase?.revisionInjectorDao()!!.findByUsingInjectorIdList(id)
    }

    fun saveListObjectSynchronized(list: List<RevisionInjectorSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_REVISION_INJECTOR_CURRENT_XID
                )!!.toInt()
                val revisionInjector = RevisionInjectorEntity()
                revisionInjector.id_inj = it.revisionInjector.revisionInjectorPK!!.id_inj
                revisionInjector.id_rev = it.revisionInjector.revisionInjectorPK!!.id_rev
                revisionInjector.dataHoraStr = it.revisionInjector.dataHoraStr
                revisionInjector.reason = it.revisionInjector.motivation
                revisionInjector.observation = it.revisionInjector.observation
                revisionInjector.user = it.revisionInjector.user


                val xidRevisionInjector = XidRevisionInjectorEntity()
                xidRevisionInjector.id = it.xidRevisionInjector.id
                xidRevisionInjector.action = it.xidRevisionInjector.action
                xidRevisionInjector.actionNumber = it.xidRevisionInjector.actionNumber
                xidRevisionInjector.brand = it.xidRevisionInjector.brand
                xidRevisionInjector.brandId = it.xidRevisionInjector.brandId
                xidRevisionInjector.classResponsible = it.xidRevisionInjector.classResponsible
                xidRevisionInjector.createdDateObject = it.xidRevisionInjector.createdDateObject
                xidRevisionInjector.identification = it.xidRevisionInjector.identification
                xidRevisionInjector.identificationAux = it.xidRevisionInjector.identificationAux
                xidRevisionInjector.lastDateUpdate = it.xidRevisionInjector.lastDateUpdate
                xidRevisionInjector.objectCompositionId = it.xidRevisionInjector.objectCompositionId
                xidRevisionInjector.objectId = it.xidRevisionInjector.objectId
                xidRevisionInjector.variantNameTable1 = it.xidRevisionInjector.variantNameTable1
                xidRevisionInjector.variantNameTable2 = it.xidRevisionInjector.variantNameTable2
                xidRevisionInjector.variantNameTable3 = it.xidRevisionInjector.variantNameTable3
                xidRevisionInjector.variantNameTable4 = it.xidRevisionInjector.variantNameTable4
                xidRevisionInjector.variantNameTable5 = it.xidRevisionInjector.variantNameTable5
                xidRevisionInjector.variantNameTable6 = it.xidRevisionInjector.variantNameTable6
                xidRevisionInjector.responsibleId = it.xidRevisionInjector.responsibleId
                xidRevisionInjector.responsibleName = it.xidRevisionInjector.responsibleName
                xidRevisionInjector.responsibleToken = it.xidRevisionInjector.responsibleToken
                xidRevisionInjector.revisionId = it.xidRevisionInjector.revisionId
                xidRevisionInjector.revisionMotivation = it.xidRevisionInjector.revisionMotivation
                xidRevisionInjector.revisionMotivationObjectId = it.xidRevisionInjector.revisionMotivationObjectId
                xidRevisionInjector.revisionObjectMotivation = it.xidRevisionInjector.revisionObjectMotivation
                xidRevisionInjector.revisionObjectObservation = it.xidRevisionInjector.revisionObjectObservation
                xidRevisionInjector.versionDatabase = it.xidRevisionInjector.versionDatabase
                xidRevisionInjector.xid = it.xidRevisionInjector.xid
                xidRevisionInjector.platformId = it.xidRevisionInjector.platformId
                xidRevisionInjector.platform = it.xidRevisionInjector.platform
                xidRevisionInjector.backupDatabase = it.xidRevisionInjector.backupDatabase
                xidRevisionInjector.genericAuxInfo1 = it.xidRevisionInjector.genericAuxInfo1
                xidRevisionInjector.genericAuxInfo2 = it.xidRevisionInjector.genericAuxInfo2
                xidRevisionInjector.genericAuxInfo3 = it.xidRevisionInjector.genericAuxInfo3
                xidRevisionInjector.genericAuxInfo4 = it.xidRevisionInjector.genericAuxInfo4
                xidRevisionInjector.genericAuxIdentification1 = it.xidRevisionInjector.genericAuxIdentification1
                xidRevisionInjector.genericAuxIdentification2 = it.xidRevisionInjector.genericAuxIdentification2
                xidRevisionInjector.genericAuxIdentification3 = it.xidRevisionInjector.genericAuxIdentification3
                xidRevisionInjector.genericAuxIdentification4 = it.xidRevisionInjector.genericAuxIdentification4
                xidRevisionInjector.forAnythingExtra1 = it.xidRevisionInjector.forAnythingExtra1
                xidRevisionInjector.forAnythingExtra2 = it.xidRevisionInjector.forAnythingExtra2
                xidRevisionInjector.forAnythingExtra3 = it.xidRevisionInjector.forAnythingExtra3
                xidRevisionInjector.forAnythingExtra4 = it.xidRevisionInjector.forAnythingExtra4
                xidRevisionInjector.betaDatabaseReleased = it.xidRevisionInjector.betaDatabaseReleased
                xidRevisionInjector.developmentDatabaseReleased = it.xidRevisionInjector.developmentDatabaseReleased
                xidRevisionInjector.experimentalDatabaseReleased = it.xidRevisionInjector.experimentalDatabaseReleased
                xidRevisionInjector.officialDatabaseReleased = it.xidRevisionInjector.officialDatabaseReleased
                xidRevisionInjector.other1DatabaseReleased = it.xidRevisionInjector.other1DatabaseReleased
                xidRevisionInjector.other2DatabaseReleased = it.xidRevisionInjector.other2DatabaseReleased

                if (it.xidRevisionInjector.action == ValueDefault.SRT_REMOVIDO
                    || it.xidRevisionInjector.action == ValueDefault.SRT_REMOVED
                    || it.xidRevisionInjector.action == ValueDefault.SRT_DELETADO
                    || it.xidRevisionInjector.action == ValueDefault.SRT_DELETED
                    || it.xidRevisionInjector.actionNumber == ValueDefault.REMOVIDO
                    || it.xidRevisionInjector.actionNumber == ValueDefault.REMOVED
                    || it.xidRevisionInjector.actionNumber == ValueDefault.DELETADO
                    || it.xidRevisionInjector.actionNumber == ValueDefault.DELETED
                ) {

                    val revisionInjectorEntityAuxList: List<RevisionInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionInjectorDao()?.findByRevisionInjector(
                            idInj = revisionInjector.id_inj,
                            idRev = revisionInjector.id_rev!!
                        )

                    val xidRevisionInjectorEntityList: List<XidRevisionInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidRevisionInjectorDao()
                            ?.findByRevisionInjector(
                                objectId = revisionInjector.id_inj.toString(),
                                objectCompositionId = revisionInjector.id_rev!!.toString()
                            )

                    if (!revisionInjectorEntityAuxList.isNullOrEmpty()) {
                        revisionInjectorEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionInjectorDao()?.delete(it01)
                        }
                    }

                    if (!xidRevisionInjectorEntityList.isNullOrEmpty()) {
                        xidRevisionInjectorEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionInjectorDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var revisionInjectorEntityAux: RevisionInjectorEntity? = null

                    val revisionInjectorEntityAuxList: List<RevisionInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionInjectorDao()?.findByRevisionInjector(
                            revisionInjector.id_inj,
                            revisionInjector.id_rev!!,
                        )


                    if (!revisionInjectorEntityAuxList.isNullOrEmpty()
                        && revisionInjectorEntityAuxList.size >= 2
                    ) {
                        revisionInjectorEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.revisionInjectorDao()?.delete(it1)
                        }
                    } else if (!revisionInjectorEntityAuxList.isNullOrEmpty()) {
                        revisionInjectorEntityAux = revisionInjectorEntityAuxList[0]
                    }

                    //delay(10)

                    if (revisionInjectorEntityAux == null
                        || (revisionInjectorEntityAux != null
                                && revisionInjectorEntityAux.id_inj == 0
                                && revisionInjectorEntityAux.id_rev == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionInjectorDao()?.insert(revisionInjector)
                    } else if (revisionInjectorEntityAux.id_inj != 0 && revisionInjectorEntityAux.id_rev != 0) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionInjectorDao()?.update(revisionInjector)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionInjectorDao()?.insert(xidRevisionInjector)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionInjectorDao()?.update(xidRevisionInjector)
                        }
                    }
                }
                if (it.xidRevisionInjector.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidRevisionInjector.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_REVISION_INJECTOR_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidRevisionInjectorDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_INJECTOR_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_INJECTOR_CURRENT_XID,
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
            PARAMETER_REVISION_INJECTOR_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidRevisionInjectorDao()?.maxXid()
            ?: 0
    }


}