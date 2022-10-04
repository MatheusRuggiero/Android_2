package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.pump.PointTestPumpSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPointTestEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidPointTestPumpEntity
import br.com.tecnomotor.marvin.model.Resource
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_POINT_TEST_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class PointTestPumpRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<PumpPointTestEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findByPointTestUsingIdPlan(id: Int): LiveData<Resource<List<PumpPointTestEntity>?>> {
        mediator.addSource(findByPointTestPumpUsingIdPlanLiveData(id)) {
            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<PumpPointTestEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<PumpPointTestEntity>?> =
                    if (resourceActually != null) {
                        Resource(data = resourceFailure.data, error = resourceFailure.error)
                    } else {
                        resourceFailure
                    }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    private fun findByPointTestPumpUsingIdPlanLiveData(id: Int): LiveData<MutableList<PumpPointTestEntity>> {
        return mAppDatabase?.pointTestPumpDao()!!.findByPointTestPumpUsingIdPlanListLiveData(id)
    }


    fun saveListObjectSynchronized(list: List<PointTestPumpSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_POINT_TEST_PUMP_CURRENT_XID
                )!!.toInt()
                val pointTestPump = PumpPointTestEntity()
                pointTestPump.planId = it.pointTestPump.planId
                pointTestPump.sequence = it.pointTestPump.sequence
                pointTestPump.typePointId = it.pointTestPump.typePointTest!!.id
                pointTestPump.nameGeneric = it.pointTestPump.nameGeneric
                pointTestPump.typeRotation = it.pointTestPump.typeRotation
                pointTestPump.rotation = it.pointTestPump.rotation
                pointTestPump.rotationVariation = it.pointTestPump.rotationVariation
                pointTestPump.pressureFeed = it.pointTestPump.pressureFeed
                pointTestPump.tolerancePressureFeed = it.pointTestPump.tolerancePressureFeed
                pointTestPump.powerTemperature = it.pointTestPump.powerTemperature
                pointTestPump.toleranceTemperatureSupply = it.pointTestPump.toleranceTemperatureSupply
                pointTestPump.temperatureReturn = it.pointTestPump.temperatureReturn
                pointTestPump.toleranceTemperatureReturn = it.pointTestPump.toleranceTemperatureReturn
                pointTestPump.pressureTransference = it.pointTestPump.pressureTransference
                pointTestPump.tolerancePressureTransfer = it.pointTestPump.tolerancePressureTransfer
                pointTestPump.flowMain = it.pointTestPump.flowMain
                pointTestPump.toleranceFlowMain = it.pointTestPump.toleranceFlowMain
                pointTestPump.flowReturn = it.pointTestPump.flowReturn
                pointTestPump.toleranceFlowReturn = it.pointTestPump.toleranceFlowReturn
                pointTestPump.pressureTest = it.pointTestPump.pressureTest
                pointTestPump.tolerancePressureTest = it.pointTestPump.tolerancePressureTest
                pointTestPump.actuator11Type = it.pointTestPump.actuator1Type
                pointTestPump.actuator1Values = it.pointTestPump.actuator1Values
                pointTestPump.actuator1ValueTolerance = it.pointTestPump.actuator1ValueTolerance
                pointTestPump.actuator1ValueToleranceActivation = it.pointTestPump.actuator1ValueToleranceActivation
                pointTestPump.actuator2Type = it.pointTestPump.actuator2Type
                pointTestPump.actuator2Values = it.pointTestPump.actuator2Values
                pointTestPump.actuator2ToleranceValue = it.pointTestPump.actuator2ToleranceValue
                pointTestPump.actuator2Activation = it.pointTestPump.actuator2Activation
                pointTestPump.actuator2ValuesActivation = it.pointTestPump.actuator2ValuesActivation
                pointTestPump.actuator2ToleranceValorActivation = it.pointTestPump.actuator2ToleranceValorActivation
                pointTestPump.timeWaitingMeasurement = it.pointTestPump.timeWaitingMeasurement
                pointTestPump.presetUser = it.pointTestPump.presetUser
                pointTestPump.empty0 = it.pointTestPump.empty0
                pointTestPump.empty1 = it.pointTestPump.empty1
                pointTestPump.empty2 = it.pointTestPump.empty2
                pointTestPump.measureMain = it.pointTestPump.measureMain
                pointTestPump.measureReturn = it.pointTestPump.measureReturn

                val xidPointTestPump = XidPointTestPumpEntity()
                xidPointTestPump.id = it.xidPointTestPump.id
                xidPointTestPump.action = it.xidPointTestPump.action
                xidPointTestPump.actionNumber = it.xidPointTestPump.actionNumber
                xidPointTestPump.brand = it.xidPointTestPump.brand
                xidPointTestPump.brandId = it.xidPointTestPump.brandId
                xidPointTestPump.classResponsible = it.xidPointTestPump.classResponsible
                xidPointTestPump.createdDateObject = it.xidPointTestPump.createdDateObject
                xidPointTestPump.identification = it.xidPointTestPump.identification
                xidPointTestPump.identificationAux = it.xidPointTestPump.identificationAux
                xidPointTestPump.lastDateUpdate = it.xidPointTestPump.lastDateUpdate
                xidPointTestPump.objectCompositionId = it.xidPointTestPump.objectCompositionId
                xidPointTestPump.objectId = it.xidPointTestPump.objectId
                xidPointTestPump.variantNameTable1 = it.xidPointTestPump.variantNameTable1
                xidPointTestPump.variantNameTable2 = it.xidPointTestPump.variantNameTable2
                xidPointTestPump.variantNameTable3 = it.xidPointTestPump.variantNameTable3
                xidPointTestPump.variantNameTable4 = it.xidPointTestPump.variantNameTable4
                xidPointTestPump.variantNameTable5 = it.xidPointTestPump.variantNameTable5
                xidPointTestPump.variantNameTable6 = it.xidPointTestPump.variantNameTable6
                xidPointTestPump.responsibleId = it.xidPointTestPump.responsibleId
                xidPointTestPump.responsibleName = it.xidPointTestPump.responsibleName
                xidPointTestPump.responsibleToken = it.xidPointTestPump.responsibleToken
                xidPointTestPump.revisionId = it.xidPointTestPump.revisionId
                xidPointTestPump.revisionMotivation = it.xidPointTestPump.revisionMotivation
                xidPointTestPump.revisionMotivationObjectId = it.xidPointTestPump.revisionMotivationObjectId
                xidPointTestPump.revisionObjectMotivation = it.xidPointTestPump.revisionObjectMotivation
                xidPointTestPump.revisionObjectObservation = it.xidPointTestPump.revisionObjectObservation
                xidPointTestPump.versionDatabase = it.xidPointTestPump.versionDatabase
                xidPointTestPump.xid = it.xidPointTestPump.xid
                xidPointTestPump.platformId = it.xidPointTestPump.platformId
                xidPointTestPump.platform = it.xidPointTestPump.platform
                xidPointTestPump.genericAuxInfo1 = it.xidPointTestPump.genericAuxInfo1
                xidPointTestPump.genericAuxInfo2 = it.xidPointTestPump.genericAuxInfo2
                xidPointTestPump.genericAuxInfo3 = it.xidPointTestPump.genericAuxInfo3
                xidPointTestPump.genericAuxInfo4 = it.xidPointTestPump.genericAuxInfo4
                xidPointTestPump.genericAuxIdentification1 = it.xidPointTestPump.genericAuxIdentification1
                xidPointTestPump.genericAuxIdentification2 = it.xidPointTestPump.genericAuxIdentification2
                xidPointTestPump.genericAuxIdentification3 = it.xidPointTestPump.genericAuxIdentification3
                xidPointTestPump.genericAuxIdentification4 = it.xidPointTestPump.genericAuxIdentification4
                xidPointTestPump.forAnythingExtra1 = it.xidPointTestPump.forAnythingExtra1
                xidPointTestPump.forAnythingExtra2 = it.xidPointTestPump.forAnythingExtra2
                xidPointTestPump.forAnythingExtra3 = it.xidPointTestPump.forAnythingExtra3
                xidPointTestPump.forAnythingExtra4 = it.xidPointTestPump.forAnythingExtra4
                xidPointTestPump.backupDatabase = it.xidPointTestPump.backupDatabase
                xidPointTestPump.betaDatabaseReleased = it.xidPointTestPump.betaDatabaseReleased
                xidPointTestPump.developmentDatabaseReleased = it.xidPointTestPump.developmentDatabaseReleased
                xidPointTestPump.experimentalDatabaseReleased = it.xidPointTestPump.experimentalDatabaseReleased
                xidPointTestPump.officialDatabaseReleased = it.xidPointTestPump.officialDatabaseReleased
                xidPointTestPump.other1DatabaseReleased = it.xidPointTestPump.other1DatabaseReleased
                xidPointTestPump.other2DatabaseReleased = it.xidPointTestPump.other2DatabaseReleased

                if (it.xidPointTestPump.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPointTestPump.action == ValueDefault.SRT_REMOVED
                    || it.xidPointTestPump.action == ValueDefault.SRT_DELETADO
                    || it.xidPointTestPump.action == ValueDefault.SRT_DELETED
                    || it.xidPointTestPump.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPointTestPump.actionNumber == ValueDefault.REMOVED
                    || it.xidPointTestPump.actionNumber == ValueDefault.DELETADO
                    || it.xidPointTestPump.actionNumber == ValueDefault.DELETED
                ) {
                    val pumpPointTestEntityAuxList: List<PumpPointTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.pointTestPumpDao()
                            ?.findByObjectCompositeQueryList(
                                pointTestPump.planId,
                                pointTestPump.typePointId,
                                pointTestPump.sequence,
                            )

                    val xidPumpPointTestEntityList: List<XidPointTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)
                            ?.xidPointTestPumpDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                pointTestPump.planId.toString(),
                                pointTestPump.typePointId.toString(),
                                pointTestPump.sequence.toString()
                            )

                    if (!pumpPointTestEntityAuxList.isNullOrEmpty()) {
                        pumpPointTestEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)
                                ?.pointTestPumpDao()?.delete(it01)
                        }
                    }

                    if (!xidPumpPointTestEntityList.isNullOrEmpty()) {
                        xidPumpPointTestEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)
                                ?.xidPointTestPumpDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var pumpPointTestEntityAux: PumpPointTestEntity? = null

                    val pumpPointTestEntityAuxList: List<PumpPointTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.pointTestPumpDao()
                            ?.findByObjectCompositeQueryList(
                                pointTestPump.planId,
                                pointTestPump.typePointId,
                                pointTestPump.sequence,
                            )

                    if (!pumpPointTestEntityAuxList.isNullOrEmpty()
                        && pumpPointTestEntityAuxList.size >= 2
                    ) {
                        pumpPointTestEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)
                                ?.pointTestPumpDao()?.delete(it1)
                        }
                    } else if (!pumpPointTestEntityAuxList.isNullOrEmpty()) {
                        pumpPointTestEntityAux = pumpPointTestEntityAuxList[0]
                    }

                    if (pumpPointTestEntityAux == null
                        || (pumpPointTestEntityAux != null
                                && pointTestPump.planId == 0
                                && pumpPointTestEntityAux.typePointId == 0
                                && pumpPointTestEntityAux.sequence == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.pointTestPumpDao()?.insert(pointTestPump)
                    } else if (pointTestPump.planId != 0 && pumpPointTestEntityAux.typePointId != 0) {
                        bUpdate = true
                        existInDatabase = true
                        pointTestPump.planId = pumpPointTestEntityAux.planId
                        AppDatabase.getDatabase(application.applicationContext)?.pointTestPumpDao()?.update(pointTestPump)
                    }

                    if (existInDatabase) {
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPointTestPumpDao()?.insert(xidPointTestPump)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidPointTestPumpDao()?.update(xidPointTestPump)
                        }
                    }
                }
                if (it.xidPointTestPump.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPointTestPump.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_POINT_TEST_PUMP_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidPointTestPumpDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_POINT_TEST_PUMP_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_POINT_TEST_PUMP_CURRENT_XID,
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
            PARAMETER_POINT_TEST_PUMP_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidPointTestPumpDao()?.maxXid()
            ?: 0
    }
}