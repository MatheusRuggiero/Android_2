package br.com.tecnomotor.marvin.repository.valve

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.valve.RevisionPlanValveSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.valve.RevisionPlanValveEntity
import br.com.tecnomotor.marvin.dao.entities.valve.XidRevisionPlanValveEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class RevisionValvePlanRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<RevisionPlanValveSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID
                )!!.toInt()
                val revisionPlanValveEntity = RevisionPlanValveEntity()
                revisionPlanValveEntity.id_plv = it.revisionPlanValve.revisionPlanValvePK!!.id_plv
                revisionPlanValveEntity.id_rev_plano = it.revisionPlanValve.revisionPlanValvePK!!.id_rev_plan
                revisionPlanValveEntity.id_val = it.revisionPlanValve.id_val
                revisionPlanValveEntity.id_rev_val = it.revisionPlanValve.id_rev_val
                revisionPlanValveEntity.dateHour = it.revisionPlanValve.dateHour
                revisionPlanValveEntity.motivation = it.revisionPlanValve.motivation
                revisionPlanValveEntity.observation = it.revisionPlanValve.observation
                revisionPlanValveEntity.user = it.revisionPlanValve.user

                val xidRevisionPlanValveEntity = XidRevisionPlanValveEntity()
                xidRevisionPlanValveEntity.id = it.xidRevisionPlanValve.id
                xidRevisionPlanValveEntity.action = it.xidRevisionPlanValve.action
                xidRevisionPlanValveEntity.actionNumber = it.xidRevisionPlanValve.actionNumber
                xidRevisionPlanValveEntity.brand = it.xidRevisionPlanValve.brand
                xidRevisionPlanValveEntity.brandId = it.xidRevisionPlanValve.brandId
                xidRevisionPlanValveEntity.classResponsible = it.xidRevisionPlanValve.classResponsible
                xidRevisionPlanValveEntity.createdDateObject = it.xidRevisionPlanValve.createdDateObject
                xidRevisionPlanValveEntity.identification = it.xidRevisionPlanValve.identification
                xidRevisionPlanValveEntity.identificationAux = it.xidRevisionPlanValve.identificationAux
                xidRevisionPlanValveEntity.lastDateUpdate = it.xidRevisionPlanValve.lastDateUpdate
                xidRevisionPlanValveEntity.objectCompositionId = it.xidRevisionPlanValve.objectCompositionId
                xidRevisionPlanValveEntity.objectId = it.xidRevisionPlanValve.objectId
                xidRevisionPlanValveEntity.variantNameTable1 = it.xidRevisionPlanValve.variantNameTable1
                xidRevisionPlanValveEntity.variantNameTable2 = it.xidRevisionPlanValve.variantNameTable2
                xidRevisionPlanValveEntity.variantNameTable3 = it.xidRevisionPlanValve.variantNameTable3
                xidRevisionPlanValveEntity.variantNameTable4 = it.xidRevisionPlanValve.variantNameTable4
                xidRevisionPlanValveEntity.variantNameTable5 = it.xidRevisionPlanValve.variantNameTable5
                xidRevisionPlanValveEntity.variantNameTable6 = it.xidRevisionPlanValve.variantNameTable6
                xidRevisionPlanValveEntity.responsibleId = it.xidRevisionPlanValve.responsibleId
                xidRevisionPlanValveEntity.responsibleName = it.xidRevisionPlanValve.responsibleName
                xidRevisionPlanValveEntity.responsibleToken = it.xidRevisionPlanValve.responsibleToken
                xidRevisionPlanValveEntity.revisionId = it.xidRevisionPlanValve.revisionId
                xidRevisionPlanValveEntity.revisionMotivation = it.xidRevisionPlanValve.revisionMotivation
                xidRevisionPlanValveEntity.revisionMotivationObjectId = it.xidRevisionPlanValve.revisionMotivationObjectId
                xidRevisionPlanValveEntity.revisionObjectMotivation = it.xidRevisionPlanValve.revisionObjectMotivation
                xidRevisionPlanValveEntity.revisionObjectObservation = it.xidRevisionPlanValve.revisionObjectObservation
                xidRevisionPlanValveEntity.versionDatabase = it.xidRevisionPlanValve.versionDatabase
                xidRevisionPlanValveEntity.xid = it.xidRevisionPlanValve.xid
                xidRevisionPlanValveEntity.platformId = it.xidRevisionPlanValve.platformId
                xidRevisionPlanValveEntity.platform = it.xidRevisionPlanValve.platform
                xidRevisionPlanValveEntity.backupDatabase = it.xidRevisionPlanValve.backupDatabase
                xidRevisionPlanValveEntity.genericAuxInfo1 = it.xidRevisionPlanValve.genericAuxInfo1
                xidRevisionPlanValveEntity.genericAuxInfo2 = it.xidRevisionPlanValve.genericAuxInfo2
                xidRevisionPlanValveEntity.genericAuxInfo3 = it.xidRevisionPlanValve.genericAuxInfo3
                xidRevisionPlanValveEntity.genericAuxInfo4 = it.xidRevisionPlanValve.genericAuxInfo4
                xidRevisionPlanValveEntity.genericAuxIdentification1 = it.xidRevisionPlanValve.genericAuxIdentification1
                xidRevisionPlanValveEntity.genericAuxIdentification2 = it.xidRevisionPlanValve.genericAuxIdentification2
                xidRevisionPlanValveEntity.genericAuxIdentification3 = it.xidRevisionPlanValve.genericAuxIdentification3
                xidRevisionPlanValveEntity.genericAuxIdentification4 = it.xidRevisionPlanValve.genericAuxIdentification4
                xidRevisionPlanValveEntity.forAnythingExtra1 = it.xidRevisionPlanValve.forAnythingExtra1
                xidRevisionPlanValveEntity.forAnythingExtra2 = it.xidRevisionPlanValve.forAnythingExtra2
                xidRevisionPlanValveEntity.forAnythingExtra3 = it.xidRevisionPlanValve.forAnythingExtra3
                xidRevisionPlanValveEntity.forAnythingExtra4 = it.xidRevisionPlanValve.forAnythingExtra4
                xidRevisionPlanValveEntity.betaDatabaseReleased = it.xidRevisionPlanValve.betaDatabaseReleased
                xidRevisionPlanValveEntity.developmentDatabaseReleased = it.xidRevisionPlanValve.developmentDatabaseReleased
                xidRevisionPlanValveEntity.experimentalDatabaseReleased = it.xidRevisionPlanValve.experimentalDatabaseReleased
                xidRevisionPlanValveEntity.officialDatabaseReleased = it.xidRevisionPlanValve.officialDatabaseReleased
                xidRevisionPlanValveEntity.other1DatabaseReleased = it.xidRevisionPlanValve.other1DatabaseReleased
                xidRevisionPlanValveEntity.other2DatabaseReleased = it.xidRevisionPlanValve.other2DatabaseReleased

                if (it.xidRevisionPlanValve.action == ValueDefault.SRT_REMOVIDO
                    || it.xidRevisionPlanValve.action == ValueDefault.SRT_REMOVED
                    || it.xidRevisionPlanValve.action == ValueDefault.SRT_DELETADO
                    || it.xidRevisionPlanValve.action == ValueDefault.SRT_DELETED
                    || it.xidRevisionPlanValve.actionNumber == ValueDefault.REMOVIDO
                    || it.xidRevisionPlanValve.actionNumber == ValueDefault.REMOVED
                    || it.xidRevisionPlanValve.actionNumber == ValueDefault.DELETADO
                    || it.xidRevisionPlanValve.actionNumber == ValueDefault.DELETED
                ) {

                    val revisionPlanValveEntityAuxList: List<RevisionPlanValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPlanValveDao()?.findByRevisionValvePlan(
                            revisionPlanValveEntity.id_plv,
                            revisionPlanValveEntity.id_rev_plano,
                        )

                    val xidRevisionPlanValveEntityList: List<XidRevisionPlanValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanValveDao()
                            ?.findByObjectIdAndCompositionId(
                                revisionPlanValveEntity.id_plv.toString(),
                                revisionPlanValveEntity.id_rev_plano.toString()
                            )

                    if (!revisionPlanValveEntityAuxList.isNullOrEmpty()) {
                        revisionPlanValveEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionPlanValveDao()?.delete(it01)
                        }
                    }

                    if (!xidRevisionPlanValveEntityList.isNullOrEmpty()) {
                        xidRevisionPlanValveEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanValveDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var revisionPlanValveEntityAux: RevisionPlanValveEntity? = null

                    val revisionPlanValveEntityAuxList: List<RevisionPlanValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPlanValveDao()?.findByRevisionValvePlan(
                            revisionPlanValveEntity.id_plv,
                            revisionPlanValveEntity.id_rev_plano,
                        )

                    if (!revisionPlanValveEntityAuxList.isNullOrEmpty()
                        && revisionPlanValveEntityAuxList.size >= 2
                    ) {
                        revisionPlanValveEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionPlanValveDao()?.delete(it1)
                        }
                    } else if (!revisionPlanValveEntityAuxList.isNullOrEmpty()) {
                        revisionPlanValveEntityAux = revisionPlanValveEntityAuxList[0]
                    }

                    //delay(10)

                    if (revisionPlanValveEntityAux == null
                        || (revisionPlanValveEntityAux != null
                                && revisionPlanValveEntityAux.id_rev_plano == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPlanValveDao()?.insert(revisionPlanValveEntity)
                    } else if (revisionPlanValveEntityAux.id_rev_plano != 0) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPlanValveDao()?.update(revisionPlanValveEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanValveDao()?.insert(xidRevisionPlanValveEntity)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanValveDao()?.update(xidRevisionPlanValveEntity)
                        }
                    }
                }
                if (it.xidRevisionPlanValve.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidRevisionPlanValve.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanValveDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID,
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
            PARAMETER_REVISION_VALVE_PLAN_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanValveDao()?.maxXid()
            ?: 0
    }

}