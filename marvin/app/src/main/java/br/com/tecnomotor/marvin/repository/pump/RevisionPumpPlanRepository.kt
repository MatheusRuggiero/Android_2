package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import br.com.tecnomotor.marvin.api.v1.model.pump.RevisionPumpPlanSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity
import br.com.tecnomotor.marvin.dao.entities.pump.RevisionPumpPlanEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidRevisionPlanPumpEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class RevisionPumpPlanRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )
    private var mAppDatabase: AppDatabase? = null

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findRevisionById(id: Int): LiveData<RevisionEntity> {
        return mAppDatabase?.revisionDao()!!.findById(id)
    }

    fun saveListObjectSynchronized(list: List<RevisionPumpPlanSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID
                )!!.toInt()
                val revisionPumpPlan = RevisionPumpPlanEntity()
                revisionPumpPlan.id_plb = it.revisionPlanPump.revisionPlanPumpPK.id_plb
                revisionPumpPlan.id_rev_plano = it.revisionPlanPump.revisionPlanPumpPK.id_rev_plano
                revisionPumpPlan.dateHour = it.revisionPlanPump.dateHour
                revisionPumpPlan.motivation = it.revisionPlanPump.motivation
                revisionPumpPlan.observation = it.revisionPlanPump.observation
                revisionPumpPlan.user = it.revisionPlanPump.user


                val xidRevisionPlanPump = XidRevisionPlanPumpEntity()
                xidRevisionPlanPump.id = it.xidRevisionPlanPump.id
                xidRevisionPlanPump.action = it.xidRevisionPlanPump.action
                xidRevisionPlanPump.actionNumber = it.xidRevisionPlanPump.actionNumber
                xidRevisionPlanPump.brand = it.xidRevisionPlanPump.brand
                xidRevisionPlanPump.brandId = it.xidRevisionPlanPump.brandId
                xidRevisionPlanPump.classResponsible = it.xidRevisionPlanPump.classResponsible
                xidRevisionPlanPump.createdDateObject = it.xidRevisionPlanPump.createdDateObject
                xidRevisionPlanPump.identification = it.xidRevisionPlanPump.identification
                xidRevisionPlanPump.identificationAux = it.xidRevisionPlanPump.identificationAux
                xidRevisionPlanPump.lastDateUpdate = it.xidRevisionPlanPump.lastDateUpdate
                xidRevisionPlanPump.objectCompositionId = it.xidRevisionPlanPump.objectCompositionId
                xidRevisionPlanPump.objectId = it.xidRevisionPlanPump.objectId
                xidRevisionPlanPump.variantNameTable1 = it.xidRevisionPlanPump.variantNameTable1
                xidRevisionPlanPump.variantNameTable2 = it.xidRevisionPlanPump.variantNameTable2
                xidRevisionPlanPump.variantNameTable3 = it.xidRevisionPlanPump.variantNameTable3
                xidRevisionPlanPump.variantNameTable4 = it.xidRevisionPlanPump.variantNameTable4
                xidRevisionPlanPump.variantNameTable5 = it.xidRevisionPlanPump.variantNameTable5
                xidRevisionPlanPump.variantNameTable6 = it.xidRevisionPlanPump.variantNameTable6
                xidRevisionPlanPump.responsibleId = it.xidRevisionPlanPump.responsibleId
                xidRevisionPlanPump.responsibleName = it.xidRevisionPlanPump.responsibleName
                xidRevisionPlanPump.responsibleToken = it.xidRevisionPlanPump.responsibleToken
                xidRevisionPlanPump.revisionId = it.xidRevisionPlanPump.revisionId
                xidRevisionPlanPump.revisionMotivation = it.xidRevisionPlanPump.revisionMotivation
                xidRevisionPlanPump.revisionMotivationObjectId = it.xidRevisionPlanPump.revisionMotivationObjectId
                xidRevisionPlanPump.revisionObjectMotivation = it.xidRevisionPlanPump.revisionObjectMotivation
                xidRevisionPlanPump.revisionObjectObservation = it.xidRevisionPlanPump.revisionObjectObservation
                xidRevisionPlanPump.versionDatabase = it.xidRevisionPlanPump.versionDatabase
                xidRevisionPlanPump.xid = it.xidRevisionPlanPump.xid
                xidRevisionPlanPump.platformId = it.xidRevisionPlanPump.platformId
                xidRevisionPlanPump.platform = it.xidRevisionPlanPump.platform
                xidRevisionPlanPump.backupDatabase = it.xidRevisionPlanPump.backupDatabase
                xidRevisionPlanPump.genericAuxInfo1 = it.xidRevisionPlanPump.genericAuxInfo1
                xidRevisionPlanPump.genericAuxInfo2 = it.xidRevisionPlanPump.genericAuxInfo2
                xidRevisionPlanPump.genericAuxInfo3 = it.xidRevisionPlanPump.genericAuxInfo3
                xidRevisionPlanPump.genericAuxInfo4 = it.xidRevisionPlanPump.genericAuxInfo4
                xidRevisionPlanPump.genericAuxIdentification1 = it.xidRevisionPlanPump.genericAuxIdentification1
                xidRevisionPlanPump.genericAuxIdentification2 = it.xidRevisionPlanPump.genericAuxIdentification2
                xidRevisionPlanPump.genericAuxIdentification3 = it.xidRevisionPlanPump.genericAuxIdentification3
                xidRevisionPlanPump.genericAuxIdentification4 = it.xidRevisionPlanPump.genericAuxIdentification4
                xidRevisionPlanPump.forAnythingExtra1 = it.xidRevisionPlanPump.forAnythingExtra1
                xidRevisionPlanPump.forAnythingExtra2 = it.xidRevisionPlanPump.forAnythingExtra2
                xidRevisionPlanPump.forAnythingExtra3 = it.xidRevisionPlanPump.forAnythingExtra3
                xidRevisionPlanPump.forAnythingExtra4 = it.xidRevisionPlanPump.forAnythingExtra4
                xidRevisionPlanPump.betaDatabaseReleased = it.xidRevisionPlanPump.betaDatabaseReleased
                xidRevisionPlanPump.developmentDatabaseReleased = it.xidRevisionPlanPump.developmentDatabaseReleased
                xidRevisionPlanPump.experimentalDatabaseReleased = it.xidRevisionPlanPump.experimentalDatabaseReleased
                xidRevisionPlanPump.officialDatabaseReleased = it.xidRevisionPlanPump.officialDatabaseReleased
                xidRevisionPlanPump.other1DatabaseReleased = it.xidRevisionPlanPump.other1DatabaseReleased
                xidRevisionPlanPump.other2DatabaseReleased = it.xidRevisionPlanPump.other2DatabaseReleased

                if (it.xidRevisionPlanPump.action == ValueDefault.SRT_REMOVIDO
                    || it.xidRevisionPlanPump.action == ValueDefault.SRT_REMOVED
                    || it.xidRevisionPlanPump.action == ValueDefault.SRT_DELETADO
                    || it.xidRevisionPlanPump.action == ValueDefault.SRT_DELETED
                    || it.xidRevisionPlanPump.actionNumber == ValueDefault.REMOVIDO
                    || it.xidRevisionPlanPump.actionNumber == ValueDefault.REMOVED
                    || it.xidRevisionPlanPump.actionNumber == ValueDefault.DELETADO
                    || it.xidRevisionPlanPump.actionNumber == ValueDefault.DELETED
                ) {

                    val revisionPumpPlanEntityAuxList: List<RevisionPumpPlanEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPumpPlanDao()?.findByRevisionPumpPlan(
                            revisionPumpPlan.id_plb,
                            revisionPumpPlan.id_rev_plano,
                        )

                    val xidRevisionPumpPlanEntityList: List<XidRevisionPlanPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanPumpDao()
                            ?.findByObjectIdAndCompositionId(
                                revisionPumpPlan.id_plb.toString(),
                                revisionPumpPlan.id_rev_plano.toString(),
                            )

                    if (!revisionPumpPlanEntityAuxList.isNullOrEmpty()) {
                        revisionPumpPlanEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.revisionPumpPlanDao()?.delete(it01)
                        }
                    }

                    if (!xidRevisionPumpPlanEntityList.isNullOrEmpty()) {
                        xidRevisionPumpPlanEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanPumpDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var injectorRevisionEntityAux: RevisionPumpPlanEntity? = null

                    val injectorRevisionEntityAuxList: List<RevisionPumpPlanEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPumpPlanDao()?.findByRevisionPumpPlan(
                            revisionPumpPlan.id_plb,
                            revisionPumpPlan.id_rev_plano,
                        )

                    if (!injectorRevisionEntityAuxList.isNullOrEmpty()
                        && injectorRevisionEntityAuxList.size >= 2
                    ) {
                        injectorRevisionEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.revisionPumpPlanDao()?.delete(it1)
                        }
                    } else if (!injectorRevisionEntityAuxList.isNullOrEmpty()) {
                        injectorRevisionEntityAux = injectorRevisionEntityAuxList[0]
                    }

                    //delay(10)

                    if (injectorRevisionEntityAux == null
                        || (injectorRevisionEntityAux != null
                                && injectorRevisionEntityAux.id_plb == 0
                                && injectorRevisionEntityAux.id_rev_plano == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPumpPlanDao()?.insert(revisionPumpPlan)
                    } else if (injectorRevisionEntityAux.id_plb != 0
                        && injectorRevisionEntityAux.id_rev_plano != 0
                    ) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.revisionPumpPlanDao()?.update(revisionPumpPlan)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanPumpDao()?.insert(xidRevisionPlanPump)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanPumpDao()?.update(xidRevisionPlanPump)
                        }
                    }
                }
                if (it.xidRevisionPlanPump.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidRevisionPlanPump.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanPumpDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID,
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
            PARAMETER_REVISION_PUMP_PLAN_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidRevisionPlanPumpDao()?.maxXid()
            ?: 0
    }

}