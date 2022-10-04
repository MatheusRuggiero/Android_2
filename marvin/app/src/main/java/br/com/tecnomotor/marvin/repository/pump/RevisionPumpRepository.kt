package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.pump.RevisionPumpSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.RevisionPumpEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidRevisionPumpEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class RevisionPumpRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<RevisionPumpEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun saveListObjectSynchronized(list: List<RevisionPumpSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_REVISION_PUMP_CURRENT_XID
                )!!.toInt()
                val revisionPumpEntity = RevisionPumpEntity()
                revisionPumpEntity.id_bom = it.revisionPump.revisionPumpPK.id_bom
                revisionPumpEntity.id_rev = it.revisionPump.revisionPumpPK.id_rev
                revisionPumpEntity.dateHour = it.revisionPump.dateHour
                revisionPumpEntity.motivation = it.revisionPump.motivation
                revisionPumpEntity.observation = it.revisionPump.observation
                revisionPumpEntity.user = it.revisionPump.user


                val xidRevisionPump = XidRevisionPumpEntity()
                xidRevisionPump.id = it.xidRevisionPump.id
                xidRevisionPump.action = it.xidRevisionPump.action
                xidRevisionPump.actionNumber = it.xidRevisionPump.actionNumber
                xidRevisionPump.brand = it.xidRevisionPump.brand
                xidRevisionPump.brandId = it.xidRevisionPump.brandId
                xidRevisionPump.classResponsible = it.xidRevisionPump.classResponsible
                xidRevisionPump.createdDateObject = it.xidRevisionPump.createdDateObject
                xidRevisionPump.identification = it.xidRevisionPump.identification
                xidRevisionPump.identificationAux = it.xidRevisionPump.identificationAux
                xidRevisionPump.lastDateUpdate = it.xidRevisionPump.lastDateUpdate
                xidRevisionPump.objectCompositionId = it.xidRevisionPump.objectCompositionId
                xidRevisionPump.objectId = it.xidRevisionPump.objectId
                xidRevisionPump.variantNameTable1 = it.xidRevisionPump.variantNameTable1
                xidRevisionPump.variantNameTable2 = it.xidRevisionPump.variantNameTable2
                xidRevisionPump.variantNameTable3 = it.xidRevisionPump.variantNameTable3
                xidRevisionPump.variantNameTable4 = it.xidRevisionPump.variantNameTable4
                xidRevisionPump.variantNameTable5 = it.xidRevisionPump.variantNameTable5
                xidRevisionPump.variantNameTable6 = it.xidRevisionPump.variantNameTable6
                xidRevisionPump.responsibleId = it.xidRevisionPump.responsibleId
                xidRevisionPump.responsibleName = it.xidRevisionPump.responsibleName
                xidRevisionPump.responsibleToken = it.xidRevisionPump.responsibleToken
                xidRevisionPump.revisionId = it.xidRevisionPump.revisionId
                xidRevisionPump.revisionMotivation = it.xidRevisionPump.revisionMotivation
                xidRevisionPump.revisionMotivationObjectId = it.xidRevisionPump.revisionMotivationObjectId
                xidRevisionPump.revisionObjectMotivation = it.xidRevisionPump.revisionObjectMotivation
                xidRevisionPump.revisionObjectObservation = it.xidRevisionPump.revisionObjectObservation
                xidRevisionPump.versionDatabase = it.xidRevisionPump.versionDatabase
                xidRevisionPump.xid = it.xidRevisionPump.xid
                xidRevisionPump.platformId = it.xidRevisionPump.platformId
                xidRevisionPump.platform = it.xidRevisionPump.platform
                xidRevisionPump.backupDatabase = it.xidRevisionPump.backupDatabase
                xidRevisionPump.genericAuxInfo1 = it.xidRevisionPump.genericAuxInfo1
                xidRevisionPump.genericAuxInfo2 = it.xidRevisionPump.genericAuxInfo2
                xidRevisionPump.genericAuxInfo3 = it.xidRevisionPump.genericAuxInfo3
                xidRevisionPump.genericAuxInfo4 = it.xidRevisionPump.genericAuxInfo4
                xidRevisionPump.genericAuxIdentification1 = it.xidRevisionPump.genericAuxIdentification1
                xidRevisionPump.genericAuxIdentification2 = it.xidRevisionPump.genericAuxIdentification2
                xidRevisionPump.genericAuxIdentification3 = it.xidRevisionPump.genericAuxIdentification3
                xidRevisionPump.genericAuxIdentification4 = it.xidRevisionPump.genericAuxIdentification4
                xidRevisionPump.forAnythingExtra1 = it.xidRevisionPump.forAnythingExtra1
                xidRevisionPump.forAnythingExtra2 = it.xidRevisionPump.forAnythingExtra2
                xidRevisionPump.forAnythingExtra3 = it.xidRevisionPump.forAnythingExtra3
                xidRevisionPump.forAnythingExtra4 = it.xidRevisionPump.forAnythingExtra4
                xidRevisionPump.betaDatabaseReleased = it.xidRevisionPump.betaDatabaseReleased
                xidRevisionPump.developmentDatabaseReleased = it.xidRevisionPump.developmentDatabaseReleased
                xidRevisionPump.experimentalDatabaseReleased = it.xidRevisionPump.experimentalDatabaseReleased
                xidRevisionPump.officialDatabaseReleased = it.xidRevisionPump.officialDatabaseReleased
                xidRevisionPump.other1DatabaseReleased = it.xidRevisionPump.other1DatabaseReleased
                xidRevisionPump.other2DatabaseReleased = it.xidRevisionPump.other2DatabaseReleased

                if (it.xidRevisionPump.action == ValueDefault.SRT_REMOVIDO
                    || it.xidRevisionPump.action == ValueDefault.SRT_REMOVED
                    || it.xidRevisionPump.action == ValueDefault.SRT_DELETADO
                    || it.xidRevisionPump.action == ValueDefault.SRT_DELETED
                    || it.xidRevisionPump.actionNumber == ValueDefault.REMOVIDO
                    || it.xidRevisionPump.actionNumber == ValueDefault.REMOVED
                    || it.xidRevisionPump.actionNumber == ValueDefault.DELETADO
                    || it.xidRevisionPump.actionNumber == ValueDefault.DELETED
                ) {

                    val revisionPumpEntityAuxList: List<RevisionPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPumpDao()?.findByRevisionPump(
                            revisionPumpEntity.id_bom,
                            revisionPumpEntity.id_rev,
                        )

                    val xidRevisionPumpEntityList: List<XidRevisionPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPumpDao()
                            ?.findByObjectIdAndCompositionId(
                                revisionPumpEntity.id_bom.toString(),
                                revisionPumpEntity.id_rev.toString(),
                            )

                    if (!revisionPumpEntityAuxList.isNullOrEmpty()) {
                        revisionPumpEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionPumpDao()?.delete(it01)
                        }
                    }

                    if (!xidRevisionPumpEntityList.isNullOrEmpty()) {
                        xidRevisionPumpEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPumpDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var pumpRevisionEntityAux: RevisionPumpEntity? = null

                    val pumpRevisionEntityAuxList: List<RevisionPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPumpDao()?.findByRevisionPump(
                            revisionPumpEntity.id_bom,
                            revisionPumpEntity.id_rev,
                        )


                    if (!pumpRevisionEntityAuxList.isNullOrEmpty()
                        && pumpRevisionEntityAuxList.size >= 2
                    ) {
                        pumpRevisionEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionPumpDao()?.delete(it1)
                        }
                    } else if (!pumpRevisionEntityAuxList.isNullOrEmpty()) {
                        pumpRevisionEntityAux = pumpRevisionEntityAuxList[0]
                    }

                    //delay(10)

                    if (pumpRevisionEntityAux == null
                        || (pumpRevisionEntityAux != null
                                && pumpRevisionEntityAux.id_bom == 0
                                && pumpRevisionEntityAux.id_rev == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPumpDao()?.insert(revisionPumpEntity)
                    } else if (pumpRevisionEntityAux.id_bom != 0
                        && pumpRevisionEntityAux.id_rev != 0
                    ) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPumpDao()?.update(revisionPumpEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPumpDao()?.insert(xidRevisionPump)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPumpDao()?.update(xidRevisionPump)
                        }
                    }
                }
                if (it.xidRevisionPump.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidRevisionPump.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_REVISION_PUMP_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPumpDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_PUMP_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_PUMP_CURRENT_XID,
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
            PARAMETER_REVISION_PUMP_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPumpDao()?.maxXid()
            ?: 0
    }

    fun findByUsingRevPumpId(id: Int): LiveData<Resource<List<RevisionPumpEntity>?>> {
        mediator.addSource(mAppDatabase?.revisionPumpDao()!!.findByUsingPumpRevId(id)) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<RevisionPumpEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<RevisionPumpEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

}