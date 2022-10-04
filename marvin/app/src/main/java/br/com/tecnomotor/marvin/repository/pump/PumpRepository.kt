package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.pump.PumpSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.PumpEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidPumpEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class PumpRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<PumpEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }


    fun findAllPump(): LiveData<Resource<List<PumpEntity>?>> {
        mediator.addSource(findAll()) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<PumpEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<PumpEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    fun findAll(): LiveData<MutableList<PumpEntity>> {
        return mAppDatabase?.pumpDao()!!.findAllLiveData()
    }

    fun saveListObjectSynchronized(list: List<PumpSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_PUMP_CURRENT_XID
                )!!.toInt()
                val pump = PumpEntity()
                pump.id = it.pump.id
                pump.code = it.pump.code
                pump.standard = it.pump.standard
                pump.brandId = it.pump.brand.id
                pump.application = it.pump.application
                pump.type = it.pump.type!!.id
                pump.token = it.pump.token
                pump.revisionId = it.pump.revision?.id ?: 0
                pump.deleted = it.pump.deleted
                pump.nameActuator = it.pump.nameActuator
                pump.referenceActuator = it.pump.referenceActuator
                pump.voltageActuator = it.pump.voltageActuator
                pump.directionRotating = it.pump.directionRotating
                pump.adapterElectric = it.pump.adapterElectric
                pump.hydraulicPressureAdapter = it.pump.adapterHhydraulicPressure
                pump.hydraulicPowerAdapter = it.pump.adapterHydraulicPower
                pump.adapterElectric = it.pump.adapterElectric
                pump.descriptionType = it.pump.description

                val xidPump = XidPumpEntity()
                xidPump.id = it.xidPump.id
                xidPump.action = it.xidPump.action
                xidPump.actionNumber = it.xidPump.actionNumber
                xidPump.brand = it.xidPump.brand
                xidPump.brandId = it.xidPump.brandId
                xidPump.classResponsible = it.xidPump.classResponsible
                xidPump.createdDateObject = it.xidPump.createdDateObject
                xidPump.identification = it.xidPump.identification
                xidPump.identificationAux = it.xidPump.identificationAux
                xidPump.lastDateUpdate = it.xidPump.lastDateUpdate
                xidPump.objectCompositionId = it.xidPump.objectCompositionId
                xidPump.objectId = it.xidPump.objectId
                xidPump.variantNameTable1 = it.xidPump.variantNameTable1
                xidPump.variantNameTable2 = it.xidPump.variantNameTable2
                xidPump.variantNameTable3 = it.xidPump.variantNameTable3
                xidPump.variantNameTable4 = it.xidPump.variantNameTable4
                xidPump.variantNameTable5 = it.xidPump.variantNameTable5
                xidPump.variantNameTable6 = it.xidPump.variantNameTable6
                xidPump.responsibleId = it.xidPump.responsibleId
                xidPump.responsibleName = it.xidPump.responsibleName
                xidPump.responsibleToken = it.xidPump.responsibleToken
                xidPump.revisionId = it.xidPump.revisionId
                xidPump.revisionMotivation = it.xidPump.revisionMotivation
                xidPump.revisionMotivationObjectId = it.xidPump.revisionMotivationObjectId
                xidPump.revisionObjectMotivation = it.xidPump.revisionObjectMotivation
                xidPump.revisionObjectObservation = it.xidPump.revisionObjectObservation
                xidPump.versionDatabase = it.xidPump.versionDatabase
                xidPump.xid = it.xidPump.xid
                xidPump.platformId = it.xidPump.platformId
                xidPump.platform = it.xidPump.platform
                xidPump.genericAuxInfo1 = it.xidPump.genericAuxInfo1
                xidPump.genericAuxInfo2 = it.xidPump.genericAuxInfo2
                xidPump.genericAuxInfo3 = it.xidPump.genericAuxInfo3
                xidPump.genericAuxInfo4 = it.xidPump.genericAuxInfo4
                xidPump.genericAuxIdentification1 = it.xidPump.genericAuxIdentification1
                xidPump.genericAuxIdentification2 = it.xidPump.genericAuxIdentification2
                xidPump.genericAuxIdentification3 = it.xidPump.genericAuxIdentification3
                xidPump.genericAuxIdentification4 = it.xidPump.genericAuxIdentification4
                xidPump.forAnythingExtra1 = it.xidPump.forAnythingExtra1
                xidPump.forAnythingExtra2 = it.xidPump.forAnythingExtra2
                xidPump.forAnythingExtra3 = it.xidPump.forAnythingExtra3
                xidPump.forAnythingExtra4 = it.xidPump.forAnythingExtra4
                xidPump.backupDatabase = it.xidPump.backupDatabase
                xidPump.betaDatabaseReleased = it.xidPump.betaDatabaseReleased
                xidPump.developmentDatabaseReleased = it.xidPump.developmentDatabaseReleased
                xidPump.experimentalDatabaseReleased = it.xidPump.experimentalDatabaseReleased
                xidPump.officialDatabaseReleased = it.xidPump.officialDatabaseReleased
                xidPump.other1DatabaseReleased = it.xidPump.other1DatabaseReleased
                xidPump.other2DatabaseReleased = it.xidPump.other2DatabaseReleased

                if (it.xidPump.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPump.action == ValueDefault.SRT_REMOVED
                    || it.xidPump.action == ValueDefault.SRT_DELETADO
                    || it.xidPump.action == ValueDefault.SRT_DELETED
                    || it.xidPump.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPump.actionNumber == ValueDefault.REMOVED
                    || it.xidPump.actionNumber == ValueDefault.DELETADO
                    || it.xidPump.actionNumber == ValueDefault.DELETED
                ) {

                    val pumpEntityAuxList: List<PumpEntity>? =
                        mAppDatabase?.pumpDao()?.findByIdList(it.pump.id)

                    val xidPumpEntityList: List<XidPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidPumpDao()
                            ?.findByObjectIdList(it.pump.id.toString())

                    if (!pumpEntityAuxList.isNullOrEmpty()) {
                        pumpEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.pumpDao()?.delete(it01)
                        }
                    }

                    if (!xidPumpEntityList.isNullOrEmpty()) {
                        xidPumpEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidPumpDao()?.delete(it02)
                        }
                    }

                    mAppDatabase?.pumpDao()?.delete(pump)
                    mAppDatabase?.xidPumpDao()?.delete(xidPump)

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var pumpEntityAux: PumpEntity? = null
                    val pumpEntityAuxList: List<PumpEntity>? =
                        mAppDatabase?.pumpDao()?.findByIdList(it.pump.id)

                    if (!pumpEntityAuxList.isNullOrEmpty()
                        && pumpEntityAuxList.size >= 2
                    ) {
                        pumpEntityAuxList.forEach { it1 ->
                            mAppDatabase?.pumpDao()?.delete(it1)
                        }
                    } else if (!pumpEntityAuxList.isNullOrEmpty()) {
                        pumpEntityAux = pumpEntityAuxList[0]
                    }

                    //delay(10)

                    if (pumpEntityAux == null
                        || (pumpEntityAux != null
                                && pumpEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        mAppDatabase?.pumpDao()?.insert(pump)
                    } else if (pumpEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        mAppDatabase?.pumpDao()?.update(pump)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            mAppDatabase?.xidPumpDao()?.insert(xidPump)
                        } else if (bUpdate) {
                            mAppDatabase?.xidPumpDao()?.update(xidPump)
                        }
                    }
                }
                if (it.xidPump.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPump.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_PUMP_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidPumpDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PUMP_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_PUMP_CURRENT_XID,
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
            PARAMETER_PUMP_CURRENT_XID
        )?.toInt()
            ?: mAppDatabase?.xidPumpDao()?.maxXid()
            ?: 0
    }

    fun findByPumpUsingCode(codePump: String): PumpEntity? {
        return mAppDatabase?.pumpDao()?.findByPumpUsingCode(codePump)
    }


}