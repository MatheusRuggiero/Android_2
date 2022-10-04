package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.pump.TypePumpSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.TypePumpEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidTypePumpEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class TypePumpRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<TypePumpEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }


    fun findAllPump(): LiveData<Resource<List<TypePumpEntity>?>> {
        mediator.addSource(findAll()) {

            mediator.value = Resource(data = it)


            val errorLiveData = MutableLiveData<Resource<List<TypePumpEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<TypePumpEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    fun findAll(): LiveData<MutableList<TypePumpEntity>> {
        return mAppDatabase?.typePumpDao()!!.findAllLiveData()
    }

    fun findByIdTypePump(id: Int): LiveData<TypePumpEntity>? {
        return mAppDatabase?.typePumpDao()!!.findByIdLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<TypePumpSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TYPE_PUMP_CURRENT_XID
                )!!.toInt()
                val typePumpEntity = TypePumpEntity()
                typePumpEntity.id = it.typePump.id
                typePumpEntity.brandId = it.typePump.brand.id
                typePumpEntity.name = it.typePump.brand.name.toString()


                val xidTypePumpEntity = XidTypePumpEntity()
                xidTypePumpEntity.id = it.xidTypePump.id
                xidTypePumpEntity.action = it.xidTypePump.action
                xidTypePumpEntity.actionNumber = it.xidTypePump.actionNumber
                xidTypePumpEntity.brand = it.xidTypePump.brand
                xidTypePumpEntity.brandId = it.xidTypePump.brandId
                xidTypePumpEntity.classResponsible = it.xidTypePump.classResponsible
                xidTypePumpEntity.createdDateObject = it.xidTypePump.createdDateObject
                xidTypePumpEntity.identification = it.xidTypePump.identification
                xidTypePumpEntity.identificationAux = it.xidTypePump.identificationAux
                xidTypePumpEntity.lastDateUpdate = it.xidTypePump.lastDateUpdate
                xidTypePumpEntity.objectCompositionId = it.xidTypePump.objectCompositionId
                xidTypePumpEntity.objectId = it.xidTypePump.objectId
                xidTypePumpEntity.variantNameTable1 = it.xidTypePump.variantNameTable1
                xidTypePumpEntity.variantNameTable2 = it.xidTypePump.variantNameTable2
                xidTypePumpEntity.variantNameTable3 = it.xidTypePump.variantNameTable3
                xidTypePumpEntity.variantNameTable4 = it.xidTypePump.variantNameTable4
                xidTypePumpEntity.variantNameTable5 = it.xidTypePump.variantNameTable5
                xidTypePumpEntity.variantNameTable6 = it.xidTypePump.variantNameTable6
                xidTypePumpEntity.responsibleId = it.xidTypePump.responsibleId
                xidTypePumpEntity.responsibleName = it.xidTypePump.responsibleName
                xidTypePumpEntity.responsibleToken = it.xidTypePump.responsibleToken
                xidTypePumpEntity.revisionId = it.xidTypePump.revisionId
                xidTypePumpEntity.revisionMotivation = it.xidTypePump.revisionMotivation
                xidTypePumpEntity.revisionMotivationObjectId = it.xidTypePump.revisionMotivationObjectId
                xidTypePumpEntity.revisionObjectMotivation = it.xidTypePump.revisionObjectMotivation
                xidTypePumpEntity.revisionObjectObservation = it.xidTypePump.revisionObjectObservation
                xidTypePumpEntity.versionDatabase = it.xidTypePump.versionDatabase
                xidTypePumpEntity.xid = it.xidTypePump.xid
                xidTypePumpEntity.platformId = it.xidTypePump.platformId
                xidTypePumpEntity.platform = it.xidTypePump.platform
                xidTypePumpEntity.genericAuxInfo1 = it.xidTypePump.genericAuxInfo1
                xidTypePumpEntity.genericAuxInfo2 = it.xidTypePump.genericAuxInfo2
                xidTypePumpEntity.genericAuxInfo3 = it.xidTypePump.genericAuxInfo3
                xidTypePumpEntity.genericAuxInfo4 = it.xidTypePump.genericAuxInfo4
                xidTypePumpEntity.genericAuxIdentification1 = it.xidTypePump.genericAuxIdentification1
                xidTypePumpEntity.genericAuxIdentification2 = it.xidTypePump.genericAuxIdentification2
                xidTypePumpEntity.genericAuxIdentification3 = it.xidTypePump.genericAuxIdentification3
                xidTypePumpEntity.genericAuxIdentification4 = it.xidTypePump.genericAuxIdentification4
                xidTypePumpEntity.forAnythingExtra1 = it.xidTypePump.forAnythingExtra1
                xidTypePumpEntity.forAnythingExtra2 = it.xidTypePump.forAnythingExtra2
                xidTypePumpEntity.forAnythingExtra3 = it.xidTypePump.forAnythingExtra3
                xidTypePumpEntity.forAnythingExtra4 = it.xidTypePump.forAnythingExtra4
                xidTypePumpEntity.backupDatabase = it.xidTypePump.backupDatabase
                xidTypePumpEntity.betaDatabaseReleased = it.xidTypePump.betaDatabaseReleased
                xidTypePumpEntity.developmentDatabaseReleased = it.xidTypePump.developmentDatabaseReleased
                xidTypePumpEntity.experimentalDatabaseReleased = it.xidTypePump.experimentalDatabaseReleased
                xidTypePumpEntity.officialDatabaseReleased = it.xidTypePump.officialDatabaseReleased
                xidTypePumpEntity.other1DatabaseReleased = it.xidTypePump.other1DatabaseReleased
                xidTypePumpEntity.other2DatabaseReleased = it.xidTypePump.other2DatabaseReleased

                if (it.xidTypePump.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTypePump.action == ValueDefault.SRT_REMOVED
                    || it.xidTypePump.action == ValueDefault.SRT_DELETADO
                    || it.xidTypePump.action == ValueDefault.SRT_DELETED
                    || it.xidTypePump.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTypePump.actionNumber == ValueDefault.REMOVED
                    || it.xidTypePump.actionNumber == ValueDefault.DELETADO
                    || it.xidTypePump.actionNumber == ValueDefault.DELETED
                ) {
                    val typePumpEntityAuxList: List<TypePumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.typePumpDao()?.findByIdList(it.typePump.id)

                    val xidTypePumpEntityList: List<XidTypePumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTypePumpDao()
                            ?.findByObjectIdList(it.typePump.id.toString())

                    if (!typePumpEntityAuxList.isNullOrEmpty()) {
                        typePumpEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.typePumpDao()?.delete(it01)
                        }
                    }

                    if (!xidTypePumpEntityList.isNullOrEmpty()) {
                        xidTypePumpEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePumpDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var typePumpEntityAux: TypePumpEntity? = null
                    val pumpEntityAuxList: List<TypePumpEntity>? =
                        mAppDatabase?.typePumpDao()?.findByIdList(it.typePump.id)

                    if (!pumpEntityAuxList.isNullOrEmpty()
                        && pumpEntityAuxList.size >= 2
                    ) {
                        pumpEntityAuxList.forEach { it1 ->
                            mAppDatabase?.typePumpDao()?.delete(it1)
                        }
                    } else if (!pumpEntityAuxList.isNullOrEmpty()) {
                        typePumpEntityAux = pumpEntityAuxList[0]
                    }

                    //delay(10)

                    if (typePumpEntityAux == null
                        || (typePumpEntityAux != null
                                && typePumpEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        mAppDatabase?.typePumpDao()?.insert(typePumpEntity)
                    } else if (typePumpEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        mAppDatabase?.typePumpDao()?.update(typePumpEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            mAppDatabase?.xidTypePumpDao()?.insert(xidTypePumpEntity)
                        } else if (bUpdate) {
                            mAppDatabase?.xidTypePumpDao()?.update(xidTypePumpEntity)
                        }
                    }
                }
                if (it.xidTypePump.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTypePump.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TYPE_PUMP_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTypePumpDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TYPE_PUMP_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TYPE_PUMP_CURRENT_XID,
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
            PARAMETER_TYPE_PUMP_CURRENT_XID
        )?.toInt()
            ?: mAppDatabase?.xidTypePumpDao()?.maxXid()
            ?: 0
    }


}