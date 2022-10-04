package br.com.tecnomotor.marvin.repository.valve

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.valve.ValveSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.valve.ValveEntity
import br.com.tecnomotor.marvin.dao.entities.valve.XidValveEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_VALVE_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class ValveRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<ValveEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }


    fun findAllValve(): LiveData<Resource<List<ValveEntity>?>> {
        mediator.addSource(findAll()) {

            mediator.value = Resource(data = it)


            val errorLiveData = MutableLiveData<Resource<List<ValveEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<ValveEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    fun findAll(): LiveData<MutableList<ValveEntity>> {
        return mAppDatabase?.valveDao()!!.findAllLiveData()
    }

    fun saveListObjectSynchronized(list: List<ValveSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_VALVE_CURRENT_XID
                )!!.toInt()
                val valveEntity = ValveEntity()
                valveEntity.id = it.valve.id
                valveEntity.code = it.valve.code
                valveEntity.standard = it.valve.standard
                valveEntity.brandEntityId = it.valve.brand.id
                valveEntity.application = it.valve.application
                valveEntity.adaptPressure = it.valve.adaptPressure
                valveEntity.adaptReturn = it.valve.adaptReturn
                valveEntity.adaptConnector = it.valve.adaptConnector
                valveEntity.resistanceMinimum = it.valve.resistanceMinimum.toDouble()
                valveEntity.resistanceMaximum = it.valve.resistanceMaximum.toDouble()
                valveEntity.typeValve = it.valve.typeValve
                valveEntity.operationValve = it.valve.operationValve
                valveEntity.token = it.valve.token
                valveEntity.revisionEntityId = it.valve.revision?.id ?: 0
                valveEntity.deleted = it.valve.deleted

                val xidValve = XidValveEntity()
                xidValve.id = it.xidValve.id
                xidValve.action = it.xidValve.action
                xidValve.actionNumber = it.xidValve.actionNumber
                xidValve.brand = it.xidValve.brand
                xidValve.brandId = it.xidValve.brandId
                xidValve.classResponsible = it.xidValve.classResponsible
                xidValve.createdDateObject = it.xidValve.createdDateObject
                xidValve.identification = it.xidValve.identification
                xidValve.identificationAux = it.xidValve.identificationAux
                xidValve.lastDateUpdate = it.xidValve.lastDateUpdate
                xidValve.objectCompositionId = it.xidValve.objectCompositionId
                xidValve.objectId = it.xidValve.objectId
                xidValve.variantNameTable1 = it.xidValve.variantNameTable1
                xidValve.variantNameTable2 = it.xidValve.variantNameTable2
                xidValve.variantNameTable3 = it.xidValve.variantNameTable3
                xidValve.variantNameTable4 = it.xidValve.variantNameTable4
                xidValve.variantNameTable5 = it.xidValve.variantNameTable5
                xidValve.variantNameTable6 = it.xidValve.variantNameTable6
                xidValve.responsibleId = it.xidValve.responsibleId
                xidValve.responsibleName = it.xidValve.responsibleName
                xidValve.responsibleToken = it.xidValve.responsibleToken
                xidValve.revisionId = it.xidValve.revisionId
                xidValve.revisionMotivation = it.xidValve.revisionMotivation
                xidValve.revisionMotivationObjectId = it.xidValve.revisionMotivationObjectId
                xidValve.revisionObjectMotivation = it.xidValve.revisionObjectMotivation
                xidValve.revisionObjectObservation = it.xidValve.revisionObjectObservation
                xidValve.versionDatabase = it.xidValve.versionDatabase
                xidValve.xid = it.xidValve.xid
                xidValve.platformId = it.xidValve.platformId
                xidValve.platform = it.xidValve.platform
                xidValve.genericAuxInfo1 = it.xidValve.genericAuxInfo1
                xidValve.genericAuxInfo2 = it.xidValve.genericAuxInfo2
                xidValve.genericAuxInfo3 = it.xidValve.genericAuxInfo3
                xidValve.genericAuxInfo4 = it.xidValve.genericAuxInfo4
                xidValve.genericAuxIdentification1 = it.xidValve.genericAuxIdentification1
                xidValve.genericAuxIdentification2 = it.xidValve.genericAuxIdentification2
                xidValve.genericAuxIdentification3 = it.xidValve.genericAuxIdentification3
                xidValve.genericAuxIdentification4 = it.xidValve.genericAuxIdentification4
                xidValve.forAnythingExtra1 = it.xidValve.forAnythingExtra1
                xidValve.forAnythingExtra2 = it.xidValve.forAnythingExtra2
                xidValve.forAnythingExtra3 = it.xidValve.forAnythingExtra3
                xidValve.forAnythingExtra4 = it.xidValve.forAnythingExtra4
                xidValve.backupDatabase = it.xidValve.backupDatabase
                xidValve.betaDatabaseReleased = it.xidValve.betaDatabaseReleased
                xidValve.developmentDatabaseReleased = it.xidValve.developmentDatabaseReleased
                xidValve.experimentalDatabaseReleased = it.xidValve.experimentalDatabaseReleased
                xidValve.officialDatabaseReleased = it.xidValve.officialDatabaseReleased
                xidValve.other1DatabaseReleased = it.xidValve.other1DatabaseReleased
                xidValve.other2DatabaseReleased = it.xidValve.other2DatabaseReleased

                if (it.xidValve.action == ValueDefault.SRT_REMOVIDO
                    || it.xidValve.action == ValueDefault.SRT_REMOVED
                    || it.xidValve.action == ValueDefault.SRT_DELETADO
                    || it.xidValve.action == ValueDefault.SRT_DELETED
                    || it.xidValve.actionNumber == ValueDefault.REMOVIDO
                    || it.xidValve.actionNumber == ValueDefault.REMOVED
                    || it.xidValve.actionNumber == ValueDefault.DELETADO
                    || it.xidValve.actionNumber == ValueDefault.DELETED
                ) {

                    val valveEntityAuxList: List<ValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.valveDao()?.findByIdList(it.valve.id)

                    val xidValveEntityList: List<XidValveEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidValveDao()
                            ?.findByObjectIdList(it.valve.id.toString())

                    if (!valveEntityAuxList.isNullOrEmpty()) {
                        valveEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.valveDao()?.delete(it01)
                        }
                    }

                    if (!xidValveEntityList.isNullOrEmpty()) {
                        xidValveEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidValveDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var valveEntityAux: ValveEntity? = null
                    val valveEntityAuxList: List<ValveEntity>? =
                        mAppDatabase?.valveDao()?.findByIdList(it.valve.id)

                    if (!valveEntityAuxList.isNullOrEmpty()
                        && valveEntityAuxList.size >= 2
                    ) {
                        valveEntityAuxList.forEach { it1 ->
                            mAppDatabase?.valveDao()?.delete(it1)
                        }
                    } else if (!valveEntityAuxList.isNullOrEmpty()) {
                        valveEntityAux = valveEntityAuxList[0]
                    }

                    //delay(10)

                    if (valveEntityAux == null
                        || (valveEntityAux != null
                                && valveEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        mAppDatabase?.valveDao()?.insert(valveEntity)
                    } else if (valveEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        mAppDatabase?.valveDao()?.update(valveEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            mAppDatabase?.xidValveDao()?.insert(xidValve)
                        } else if (bUpdate) {
                            mAppDatabase?.xidValveDao()?.update(xidValve)
                        }
                    }
                }
                if (it.xidValve.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidValve.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_VALVE_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidValveDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VALVE_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_VALVE_CURRENT_XID,
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
            PARAMETER_VALVE_CURRENT_XID
        )?.toInt()
            ?: mAppDatabase?.xidValveDao()?.maxXid()
            ?: 0
    }


}