package br.com.tecnomotor.marvin.repository.valve

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.valve.ValvePlatformSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.valve.ValvePlatformEntity
import br.com.tecnomotor.marvin.dao.entities.valve.XidValvePlatformEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class ValvePlatformRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<ValvePlatformSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_VALVE_PLATFORM_CURRENT_XID
                )!!.toInt()
                val valvePlatform = ValvePlatformEntity()
                valvePlatform.platformEntityId = it.valvePlatform.valvePlatformPK!!.platform!!.id
                valvePlatform.valveEntityId = it.valvePlatform.valvePlatformPK!!.valve!!.id

                val xidValvePlatform = XidValvePlatformEntity()
                xidValvePlatform.id = it.xidValvePlatform.id
                xidValvePlatform.action = it.xidValvePlatform.action
                xidValvePlatform.actionNumber = it.xidValvePlatform.actionNumber
                xidValvePlatform.brand = it.xidValvePlatform.brand
                xidValvePlatform.brandId = it.xidValvePlatform.brandId
                xidValvePlatform.classResponsible = it.xidValvePlatform.classResponsible
                xidValvePlatform.createdDateObject = it.xidValvePlatform.createdDateObject
                xidValvePlatform.identification = it.xidValvePlatform.identification
                xidValvePlatform.identificationAux = it.xidValvePlatform.identificationAux
                xidValvePlatform.lastDateUpdate = it.xidValvePlatform.lastDateUpdate
                xidValvePlatform.objectCompositionId = it.xidValvePlatform.objectCompositionId
                xidValvePlatform.objectId = it.xidValvePlatform.objectId
                xidValvePlatform.variantNameTable1 = it.xidValvePlatform.variantNameTable1
                xidValvePlatform.variantNameTable2 = it.xidValvePlatform.variantNameTable2
                xidValvePlatform.variantNameTable3 = it.xidValvePlatform.variantNameTable3
                xidValvePlatform.variantNameTable4 = it.xidValvePlatform.variantNameTable4
                xidValvePlatform.variantNameTable5 = it.xidValvePlatform.variantNameTable5
                xidValvePlatform.variantNameTable6 = it.xidValvePlatform.variantNameTable6
                xidValvePlatform.responsibleId = it.xidValvePlatform.responsibleId
                xidValvePlatform.responsibleName = it.xidValvePlatform.responsibleName
                xidValvePlatform.responsibleToken = it.xidValvePlatform.responsibleToken
                xidValvePlatform.revisionId = it.xidValvePlatform.revisionId
                xidValvePlatform.revisionMotivation = it.xidValvePlatform.revisionMotivation
                xidValvePlatform.revisionMotivationObjectId = it.xidValvePlatform.revisionMotivationObjectId
                xidValvePlatform.revisionObjectMotivation = it.xidValvePlatform.revisionObjectMotivation
                xidValvePlatform.revisionObjectObservation = it.xidValvePlatform.revisionObjectObservation
                xidValvePlatform.versionDatabase = it.xidValvePlatform.versionDatabase
                xidValvePlatform.xid = it.xidValvePlatform.xid
                xidValvePlatform.platformId = it.xidValvePlatform.platformId
                xidValvePlatform.platform = it.xidValvePlatform.platform
                xidValvePlatform.genericAuxInfo1 = it.xidValvePlatform.genericAuxInfo1
                xidValvePlatform.genericAuxInfo2 = it.xidValvePlatform.genericAuxInfo2
                xidValvePlatform.genericAuxInfo3 = it.xidValvePlatform.genericAuxInfo3
                xidValvePlatform.genericAuxInfo4 = it.xidValvePlatform.genericAuxInfo4
                xidValvePlatform.genericAuxIdentification1 = it.xidValvePlatform.genericAuxIdentification1
                xidValvePlatform.genericAuxIdentification2 = it.xidValvePlatform.genericAuxIdentification2
                xidValvePlatform.genericAuxIdentification3 = it.xidValvePlatform.genericAuxIdentification3
                xidValvePlatform.genericAuxIdentification4 = it.xidValvePlatform.genericAuxIdentification4
                xidValvePlatform.forAnythingExtra1 = it.xidValvePlatform.forAnythingExtra1
                xidValvePlatform.forAnythingExtra2 = it.xidValvePlatform.forAnythingExtra2
                xidValvePlatform.forAnythingExtra3 = it.xidValvePlatform.forAnythingExtra3
                xidValvePlatform.forAnythingExtra4 = it.xidValvePlatform.forAnythingExtra4
                xidValvePlatform.backupDatabase = it.xidValvePlatform.backupDatabase
                xidValvePlatform.betaDatabaseReleased = it.xidValvePlatform.betaDatabaseReleased
                xidValvePlatform.developmentDatabaseReleased = it.xidValvePlatform.developmentDatabaseReleased
                xidValvePlatform.experimentalDatabaseReleased = it.xidValvePlatform.experimentalDatabaseReleased
                xidValvePlatform.officialDatabaseReleased = it.xidValvePlatform.officialDatabaseReleased
                xidValvePlatform.other1DatabaseReleased = it.xidValvePlatform.other1DatabaseReleased
                xidValvePlatform.other2DatabaseReleased = it.xidValvePlatform.other2DatabaseReleased

                if (it.xidValvePlatform.action == ValueDefault.SRT_REMOVIDO
                    || it.xidValvePlatform.action == ValueDefault.SRT_REMOVED
                    || it.xidValvePlatform.action == ValueDefault.SRT_DELETADO
                    || it.xidValvePlatform.action == ValueDefault.SRT_DELETED
                    || it.xidValvePlatform.actionNumber == ValueDefault.REMOVIDO
                    || it.xidValvePlatform.actionNumber == ValueDefault.REMOVED
                    || it.xidValvePlatform.actionNumber == ValueDefault.DELETADO
                    || it.xidValvePlatform.actionNumber == ValueDefault.DELETED
                ) {
                    val valvePlatformEntityAuxList: List<ValvePlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlatformDao()?.findByIdComposite(
                            it.valvePlatform.valvePlatformPK!!.valve.id,
                            it.valvePlatform.valvePlatformPK!!.platform.id
                        )

                    val xidValvePlatformEntityList: List<XidValvePlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformDao()
                            ?.findByObjectIdAndCompositionId(
                                it.valvePlatform.valvePlatformPK!!.valve.id.toString(),
                                it.valvePlatform.valvePlatformPK!!.platform.id.toString()
                            )

                    if (!valvePlatformEntityAuxList.isNullOrEmpty()) {
                        valvePlatformEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.valvePlatformDao()?.delete(it01)
                        }
                    }

                    if (!xidValvePlatformEntityList.isNullOrEmpty()) {
                        xidValvePlatformEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var valvePlatformEntityAux: ValvePlatformEntity? = null

                    val valvePlatformEntityAuxList: List<ValvePlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlatformDao()?.findByIdComposite(
                            it.valvePlatform.valvePlatformPK!!.valve.id,
                            it.valvePlatform.valvePlatformPK!!.platform.id
                        )

                    if (!valvePlatformEntityAuxList.isNullOrEmpty()
                        && valvePlatformEntityAuxList.size >= 2
                    ) {
                        valvePlatformEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.valvePlatformDao()?.delete(it1)
                        }
                    } else if (!valvePlatformEntityAuxList.isNullOrEmpty()) {
                        valvePlatformEntityAux = valvePlatformEntityAuxList[0]
                    }

                    //delay(10)

                    if (valvePlatformEntityAux == null
                        || (valvePlatformEntityAux != null
                                && valvePlatformEntityAux.valveEntityId == 0
                                && valvePlatformEntityAux.platformEntityId == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlatformDao()?.insert(valvePlatform)
                    } else if (valvePlatformEntityAux.valveEntityId != 0
                        && valvePlatformEntityAux.platformEntityId != 0
                    ) {
                        bUpdate = true
                        existInDatabase = true
                        valvePlatform.id = valvePlatformEntityAux.id
                        AppDatabase.getDatabase(application.applicationContext)?.valvePlatformDao()?.update(valvePlatform)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformDao()?.insert(xidValvePlatform)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformDao()?.update(xidValvePlatform)
                        }
                    }
                }
                if (it.xidValvePlatform.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidValvePlatform.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_VALVE_PLATFORM_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VALVE_PLATFORM_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VALVE_PLATFORM_CURRENT_XID,
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
            PARAMETER_VALVE_PLATFORM_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidValvePlatformDao()?.maxXid()
            ?: 0
    }

}