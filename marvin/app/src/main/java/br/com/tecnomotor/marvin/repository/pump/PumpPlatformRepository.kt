package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import br.com.tecnomotor.marvin.api.v1.model.pump.PumpPlatformSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPlatformEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidPumpPlatformEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_PLATFORM_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class PumpPlatformRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    fun saveListObjectSynchronized(list: List<PumpPlatformSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_PUMP_PLATFORM_CURRENT_XID
                )!!.toInt()
                val pumpPlatform = PumpPlatformEntity()
                pumpPlatform.pumpId = it.pumpPlatform.pumpPlatformPK!!.pump.id
                pumpPlatform.platformId = it.pumpPlatform.pumpPlatformPK!!.platform.id
                pumpPlatform.electricAdapter = it.pumpPlatform.electricAdapter
                pumpPlatform.adapterHydraulicPressure = it.pumpPlatform.adapterHydraulicPressure
                pumpPlatform.adapterHydraulicReturn = it.pumpPlatform.adapterHydraulicReturn
                pumpPlatform.adapterHydraulicPower = it.pumpPlatform.adapterHydraulicPower
                pumpPlatform.adapterMechanical = it.pumpPlatform.adapterMechanical

                val xidPumpPlatform = XidPumpPlatformEntity()
                xidPumpPlatform.id = it.xidPumpPlatform.id
                xidPumpPlatform.action = it.xidPumpPlatform.action
                xidPumpPlatform.actionNumber = it.xidPumpPlatform.actionNumber
                xidPumpPlatform.brand = it.xidPumpPlatform.brand
                xidPumpPlatform.brandId = it.xidPumpPlatform.brandId
                xidPumpPlatform.classResponsible = it.xidPumpPlatform.classResponsible
                xidPumpPlatform.createdDateObject = it.xidPumpPlatform.createdDateObject
                xidPumpPlatform.identification = it.xidPumpPlatform.identification
                xidPumpPlatform.identificationAux = it.xidPumpPlatform.identificationAux
                xidPumpPlatform.lastDateUpdate = it.xidPumpPlatform.lastDateUpdate
                xidPumpPlatform.objectCompositionId = it.xidPumpPlatform.objectCompositionId
                xidPumpPlatform.objectId = it.xidPumpPlatform.objectId
                xidPumpPlatform.variantNameTable1 = it.xidPumpPlatform.variantNameTable1
                xidPumpPlatform.variantNameTable2 = it.xidPumpPlatform.variantNameTable2
                xidPumpPlatform.variantNameTable3 = it.xidPumpPlatform.variantNameTable3
                xidPumpPlatform.variantNameTable4 = it.xidPumpPlatform.variantNameTable4
                xidPumpPlatform.variantNameTable5 = it.xidPumpPlatform.variantNameTable5
                xidPumpPlatform.variantNameTable6 = it.xidPumpPlatform.variantNameTable6
                xidPumpPlatform.responsibleId = it.xidPumpPlatform.responsibleId
                xidPumpPlatform.responsibleName = it.xidPumpPlatform.responsibleName
                xidPumpPlatform.responsibleToken = it.xidPumpPlatform.responsibleToken
                xidPumpPlatform.revisionId = it.xidPumpPlatform.revisionId
                xidPumpPlatform.revisionMotivation = it.xidPumpPlatform.revisionMotivation
                xidPumpPlatform.revisionMotivationObjectId = it.xidPumpPlatform.revisionMotivationObjectId
                xidPumpPlatform.revisionObjectMotivation = it.xidPumpPlatform.revisionObjectMotivation
                xidPumpPlatform.revisionObjectObservation = it.xidPumpPlatform.revisionObjectObservation
                xidPumpPlatform.versionDatabase = it.xidPumpPlatform.versionDatabase
                xidPumpPlatform.xid = it.xidPumpPlatform.xid
                xidPumpPlatform.platformId = it.xidPumpPlatform.platformId
                xidPumpPlatform.platform = it.xidPumpPlatform.platform
                xidPumpPlatform.genericAuxInfo1 = it.xidPumpPlatform.genericAuxInfo1
                xidPumpPlatform.genericAuxInfo2 = it.xidPumpPlatform.genericAuxInfo2
                xidPumpPlatform.genericAuxInfo3 = it.xidPumpPlatform.genericAuxInfo3
                xidPumpPlatform.genericAuxInfo4 = it.xidPumpPlatform.genericAuxInfo4
                xidPumpPlatform.genericAuxIdentification1 = it.xidPumpPlatform.genericAuxIdentification1
                xidPumpPlatform.genericAuxIdentification2 = it.xidPumpPlatform.genericAuxIdentification2
                xidPumpPlatform.genericAuxIdentification3 = it.xidPumpPlatform.genericAuxIdentification3
                xidPumpPlatform.genericAuxIdentification4 = it.xidPumpPlatform.genericAuxIdentification4
                xidPumpPlatform.forAnythingExtra1 = it.xidPumpPlatform.forAnythingExtra1
                xidPumpPlatform.forAnythingExtra2 = it.xidPumpPlatform.forAnythingExtra2
                xidPumpPlatform.forAnythingExtra3 = it.xidPumpPlatform.forAnythingExtra3
                xidPumpPlatform.forAnythingExtra4 = it.xidPumpPlatform.forAnythingExtra4
                xidPumpPlatform.backupDatabase = it.xidPumpPlatform.backupDatabase
                xidPumpPlatform.betaDatabaseReleased = it.xidPumpPlatform.betaDatabaseReleased
                xidPumpPlatform.developmentDatabaseReleased = it.xidPumpPlatform.developmentDatabaseReleased
                xidPumpPlatform.experimentalDatabaseReleased = it.xidPumpPlatform.experimentalDatabaseReleased
                xidPumpPlatform.officialDatabaseReleased = it.xidPumpPlatform.officialDatabaseReleased
                xidPumpPlatform.other1DatabaseReleased = it.xidPumpPlatform.other1DatabaseReleased
                xidPumpPlatform.other2DatabaseReleased = it.xidPumpPlatform.other2DatabaseReleased

                if (it.xidPumpPlatform.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPumpPlatform.action == ValueDefault.SRT_REMOVED
                    || it.xidPumpPlatform.action == ValueDefault.SRT_DELETADO
                    || it.xidPumpPlatform.action == ValueDefault.SRT_DELETED
                    || it.xidPumpPlatform.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPumpPlatform.actionNumber == ValueDefault.REMOVED
                    || it.xidPumpPlatform.actionNumber == ValueDefault.DELETADO
                    || it.xidPumpPlatform.actionNumber == ValueDefault.DELETED
                ) {

                    val pumpPlatformEntityAuxList: List<PumpPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformDao()?.findByIdComposite(
                            it.pumpPlatform.pumpPlatformPK!!.pump.id,
                            it.pumpPlatform.pumpPlatformPK!!.platform.id
                        )

                    val xidPumpPlatformEntityList: List<XidPumpPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformDao()
                            ?.findByObjectIdAndCompositionId(
                                it.pumpPlatform.pumpPlatformPK!!.pump.id.toString(),
                                it.pumpPlatform.pumpPlatformPK!!.platform.id.toString()
                            )

                    if (!pumpPlatformEntityAuxList.isNullOrEmpty()) {
                        pumpPlatformEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformDao()?.delete(it01)
                        }
                    }

                    if (!xidPumpPlatformEntityList.isNullOrEmpty()) {
                        xidPumpPlatformEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var pumpPlatformEntityAux: PumpPlatformEntity? = null

                    val pumpPlatformEntityAuxList: List<PumpPlatformEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformDao()?.findByIdComposite(
                            it.pumpPlatform.pumpPlatformPK!!.pump.id,
                            it.pumpPlatform.pumpPlatformPK!!.platform.id
                        )

                    if (!pumpPlatformEntityAuxList.isNullOrEmpty()
                        && pumpPlatformEntityAuxList.size >= 2
                    ) {
                        pumpPlatformEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformDao()?.delete(it1)
                        }
                    } else if (!pumpPlatformEntityAuxList.isNullOrEmpty()) {
                        pumpPlatformEntityAux = pumpPlatformEntityAuxList[0]
                    }

                    //delay(10)

                    if (pumpPlatformEntityAux == null
                        || (pumpPlatformEntityAux != null
                                && pumpPlatformEntityAux.pumpId == 0
                                && pumpPlatformEntityAux.platformId == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformDao()?.insert(pumpPlatform)
                    } else if (pumpPlatformEntityAux.pumpId != 0
                        && pumpPlatformEntityAux.platformId != 0
                    ) {
                        bUpdate = true
                        existInDatabase = true
                        pumpPlatform.id = pumpPlatformEntityAux.id
                        AppDatabase.getDatabase(application.applicationContext)?.pumpPlatformDao()?.update(pumpPlatform)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformDao()?.insert(xidPumpPlatform)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformDao()?.update(xidPumpPlatform)
                        }
                    }
                }

                if (it.xidPumpPlatform.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPumpPlatform.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_PUMP_PLATFORM_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PUMP_PLATFORM_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PUMP_PLATFORM_CURRENT_XID,
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
            PARAMETER_PUMP_PLATFORM_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidPumpPlatformDao()?.maxXid()
            ?: 0
    }

}