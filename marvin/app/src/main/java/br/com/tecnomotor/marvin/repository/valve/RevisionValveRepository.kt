package br.com.tecnomotor.marvin.repository.valve

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.valve.RevisionValveSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.valve.RevisionValveEntity
import br.com.tecnomotor.marvin.dao.entities.valve.XidRevisionValveEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_VALVE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class RevisionValveRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<RevisionValveSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_REVISION_VALVE_CURRENT_XID
                )!!.toInt()
                val revisionValveEntity = RevisionValveEntity()
                revisionValveEntity.id_val = it.revisionValve.revisionValvePK.id_val
                revisionValveEntity.id_rev = it.revisionValve.revisionValvePK.id_rev
                revisionValveEntity.dateHour = it.revisionValve.dateHour
                revisionValveEntity.motivation = it.revisionValve.motivation
                revisionValveEntity.observation = it.revisionValve.observation
                revisionValveEntity.user = it.revisionValve.user

                val xidRevisionValve = XidRevisionValveEntity()
                xidRevisionValve.id = it.xidRevisionValve.id
                xidRevisionValve.action = it.xidRevisionValve.action
                xidRevisionValve.actionNumber = it.xidRevisionValve.actionNumber
                xidRevisionValve.brand = it.xidRevisionValve.brand
                xidRevisionValve.brandId = it.xidRevisionValve.brandId
                xidRevisionValve.classResponsible = it.xidRevisionValve.classResponsible
                xidRevisionValve.createdDateObject = it.xidRevisionValve.createdDateObject
                xidRevisionValve.identification = it.xidRevisionValve.identification
                xidRevisionValve.identificationAux = it.xidRevisionValve.identificationAux
                xidRevisionValve.lastDateUpdate = it.xidRevisionValve.lastDateUpdate
                xidRevisionValve.objectCompositionId = it.xidRevisionValve.objectCompositionId
                xidRevisionValve.objectId = it.xidRevisionValve.objectId
                xidRevisionValve.variantNameTable1 = it.xidRevisionValve.variantNameTable1
                xidRevisionValve.variantNameTable2 = it.xidRevisionValve.variantNameTable2
                xidRevisionValve.variantNameTable3 = it.xidRevisionValve.variantNameTable3
                xidRevisionValve.variantNameTable4 = it.xidRevisionValve.variantNameTable4
                xidRevisionValve.variantNameTable5 = it.xidRevisionValve.variantNameTable5
                xidRevisionValve.variantNameTable6 = it.xidRevisionValve.variantNameTable6
                xidRevisionValve.responsibleId = it.xidRevisionValve.responsibleId
                xidRevisionValve.responsibleName = it.xidRevisionValve.responsibleName
                xidRevisionValve.responsibleToken = it.xidRevisionValve.responsibleToken
                xidRevisionValve.revisionId = it.xidRevisionValve.revisionId
                xidRevisionValve.revisionMotivation = it.xidRevisionValve.revisionMotivation
                xidRevisionValve.revisionMotivationObjectId = it.xidRevisionValve.revisionMotivationObjectId
                xidRevisionValve.revisionObjectMotivation = it.xidRevisionValve.revisionObjectMotivation
                xidRevisionValve.revisionObjectObservation = it.xidRevisionValve.revisionObjectObservation
                xidRevisionValve.versionDatabase = it.xidRevisionValve.versionDatabase
                xidRevisionValve.xid = it.xidRevisionValve.xid
                xidRevisionValve.platformId = it.xidRevisionValve.platformId
                xidRevisionValve.platform = it.xidRevisionValve.platform
                xidRevisionValve.backupDatabase = it.xidRevisionValve.backupDatabase
                xidRevisionValve.genericAuxInfo1 = it.xidRevisionValve.genericAuxInfo1
                xidRevisionValve.genericAuxInfo2 = it.xidRevisionValve.genericAuxInfo2
                xidRevisionValve.genericAuxInfo3 = it.xidRevisionValve.genericAuxInfo3
                xidRevisionValve.genericAuxInfo4 = it.xidRevisionValve.genericAuxInfo4
                xidRevisionValve.genericAuxIdentification1 = it.xidRevisionValve.genericAuxIdentification1
                xidRevisionValve.genericAuxIdentification2 = it.xidRevisionValve.genericAuxIdentification2
                xidRevisionValve.genericAuxIdentification3 = it.xidRevisionValve.genericAuxIdentification3
                xidRevisionValve.genericAuxIdentification4 = it.xidRevisionValve.genericAuxIdentification4
                xidRevisionValve.forAnythingExtra1 = it.xidRevisionValve.forAnythingExtra1
                xidRevisionValve.forAnythingExtra2 = it.xidRevisionValve.forAnythingExtra2
                xidRevisionValve.forAnythingExtra3 = it.xidRevisionValve.forAnythingExtra3
                xidRevisionValve.forAnythingExtra4 = it.xidRevisionValve.forAnythingExtra4
                xidRevisionValve.betaDatabaseReleased = it.xidRevisionValve.betaDatabaseReleased
                xidRevisionValve.developmentDatabaseReleased = it.xidRevisionValve.developmentDatabaseReleased
                xidRevisionValve.experimentalDatabaseReleased = it.xidRevisionValve.experimentalDatabaseReleased
                xidRevisionValve.officialDatabaseReleased = it.xidRevisionValve.officialDatabaseReleased
                xidRevisionValve.other1DatabaseReleased = it.xidRevisionValve.other1DatabaseReleased
                xidRevisionValve.other2DatabaseReleased = it.xidRevisionValve.other2DatabaseReleased

                if (it.xidRevisionValve.action == ValueDefault.SRT_REMOVIDO
                    || it.xidRevisionValve.action == ValueDefault.SRT_REMOVED
                    || it.xidRevisionValve.action == ValueDefault.SRT_DELETADO
                    || it.xidRevisionValve.action == ValueDefault.SRT_DELETED
                    || it.xidRevisionValve.actionNumber == ValueDefault.REMOVIDO
                    || it.xidRevisionValve.actionNumber == ValueDefault.REMOVED
                    || it.xidRevisionValve.actionNumber == ValueDefault.DELETADO
                    || it.xidRevisionValve.actionNumber == ValueDefault.DELETED
                ) {
                    val revisionValveEntityAuxList: List<RevisionValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionValveDao()?.findByRevisionValve(
                            revisionValveEntity.id_val,
                            revisionValveEntity.id_rev,
                        )

                    val xidRevisionValveEntityList: List<XidRevisionValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidRevisionValveDao()
                            ?.findByObjectIdAndCompositionId(
                                revisionValveEntity.id_val.toString(),
                                revisionValveEntity.id_rev.toString()
                            )

                    if (!revisionValveEntityAuxList.isNullOrEmpty()) {
                        revisionValveEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionValveDao()?.delete(it01)
                        }
                    }

                    if (!xidRevisionValveEntityList.isNullOrEmpty()) {
                        xidRevisionValveEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionValveDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var valveRevisionEntityAux: RevisionValveEntity? = null

                    val valveRevisionEntityAuxList: List<RevisionValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionValveDao()?.findByRevisionValve(
                            revisionValveEntity.id_val,
                            revisionValveEntity.id_rev,
                        )


                    if (!valveRevisionEntityAuxList.isNullOrEmpty()
                        && valveRevisionEntityAuxList.size >= 2
                    ) {
                        valveRevisionEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.revisionValveDao()?.delete(it1)
                        }
                    } else if (!valveRevisionEntityAuxList.isNullOrEmpty()) {
                        valveRevisionEntityAux = valveRevisionEntityAuxList[0]
                    }

                    //delay(10)

                    if (valveRevisionEntityAux == null
                        || (valveRevisionEntityAux != null
                                && valveRevisionEntityAux.id_val == 0
                                && valveRevisionEntityAux.id_rev == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionValveDao()?.insert(revisionValveEntity)
                    } else if (valveRevisionEntityAux.id_val != 0
                        && valveRevisionEntityAux.id_rev != 0
                    ) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionValveDao()?.update(revisionValveEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionValveDao()?.insert(xidRevisionValve)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionValveDao()?.update(xidRevisionValve)
                        }
                    }
                }
                if (it.xidRevisionValve.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidRevisionValve.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_REVISION_VALVE_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidRevisionValveDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_VALVE_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_VALVE_CURRENT_XID,
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
            PARAMETER_REVISION_VALVE_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidRevisionValveDao()?.maxXid()
            ?: 0
    }

}