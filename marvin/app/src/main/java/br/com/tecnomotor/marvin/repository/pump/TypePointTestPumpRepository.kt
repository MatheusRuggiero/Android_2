package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.api.v1.model.pump.TypePointTestPumpSynchronize

import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.TypePointTestPumpEntity
import br.com.tecnomotor.marvin.dao.entities.pump.XidTypePointTestPumpEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class TypePointTestPumpRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<TypePointTestPumpEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findByTypePumpIdLiveData(id: Int): LiveData<TypePointTestPumpEntity>? {
        return mAppDatabase?.typePointTestPumpDao()?.findByTypePumpIdLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<TypePointTestPumpSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID
                )!!.toInt()
                val typePointTestPumpEntity = TypePointTestPumpEntity()
                typePointTestPumpEntity.id = it.typePointTestPump.id
                typePointTestPumpEntity.description = it.typePointTestPump.description!!
                typePointTestPumpEntity.type = it.typePointTestPump.typePoint

                val xidTypePointTestPumpEntity = XidTypePointTestPumpEntity()
                xidTypePointTestPumpEntity.id = it.xidTypePointTestPump.id
                xidTypePointTestPumpEntity.action = it.xidTypePointTestPump.action
                xidTypePointTestPumpEntity.actionNumber = it.xidTypePointTestPump.actionNumber
                xidTypePointTestPumpEntity.brand = it.xidTypePointTestPump.brand
                xidTypePointTestPumpEntity.brandId = it.xidTypePointTestPump.brandId
                xidTypePointTestPumpEntity.classResponsible = it.xidTypePointTestPump.classResponsible
                xidTypePointTestPumpEntity.createdDateObject = it.xidTypePointTestPump.createdDateObject
                xidTypePointTestPumpEntity.identification = it.xidTypePointTestPump.identification
                xidTypePointTestPumpEntity.identificationAux = it.xidTypePointTestPump.identificationAux
                xidTypePointTestPumpEntity.lastDateUpdate = it.xidTypePointTestPump.lastDateUpdate
                xidTypePointTestPumpEntity.objectCompositionId = it.xidTypePointTestPump.objectCompositionId
                xidTypePointTestPumpEntity.objectId = it.xidTypePointTestPump.objectId
                xidTypePointTestPumpEntity.variantNameTable1 = it.xidTypePointTestPump.variantNameTable1
                xidTypePointTestPumpEntity.variantNameTable2 = it.xidTypePointTestPump.variantNameTable2
                xidTypePointTestPumpEntity.variantNameTable3 = it.xidTypePointTestPump.variantNameTable3
                xidTypePointTestPumpEntity.variantNameTable4 = it.xidTypePointTestPump.variantNameTable4
                xidTypePointTestPumpEntity.variantNameTable5 = it.xidTypePointTestPump.variantNameTable5
                xidTypePointTestPumpEntity.variantNameTable6 = it.xidTypePointTestPump.variantNameTable6
                xidTypePointTestPumpEntity.responsibleId = it.xidTypePointTestPump.responsibleId
                xidTypePointTestPumpEntity.responsibleName = it.xidTypePointTestPump.responsibleName
                xidTypePointTestPumpEntity.responsibleToken = it.xidTypePointTestPump.responsibleToken
                xidTypePointTestPumpEntity.revisionId = it.xidTypePointTestPump.revisionId
                xidTypePointTestPumpEntity.revisionMotivation = it.xidTypePointTestPump.revisionMotivation
                xidTypePointTestPumpEntity.revisionMotivationObjectId = it.xidTypePointTestPump.revisionMotivationObjectId
                xidTypePointTestPumpEntity.revisionObjectMotivation = it.xidTypePointTestPump.revisionObjectMotivation
                xidTypePointTestPumpEntity.revisionObjectObservation = it.xidTypePointTestPump.revisionObjectObservation
                xidTypePointTestPumpEntity.versionDatabase = it.xidTypePointTestPump.versionDatabase
                xidTypePointTestPumpEntity.xid = it.xidTypePointTestPump.xid
                xidTypePointTestPumpEntity.platformId = it.xidTypePointTestPump.platformId
                xidTypePointTestPumpEntity.platform = it.xidTypePointTestPump.platform
                xidTypePointTestPumpEntity.backupDatabase = it.xidTypePointTestPump.backupDatabase
                xidTypePointTestPumpEntity.genericAuxInfo1 = it.xidTypePointTestPump.genericAuxInfo1
                xidTypePointTestPumpEntity.genericAuxInfo2 = it.xidTypePointTestPump.genericAuxInfo2
                xidTypePointTestPumpEntity.genericAuxInfo3 = it.xidTypePointTestPump.genericAuxInfo3
                xidTypePointTestPumpEntity.genericAuxInfo4 = it.xidTypePointTestPump.genericAuxInfo4
                xidTypePointTestPumpEntity.genericAuxIdentification1 = it.xidTypePointTestPump.genericAuxIdentification1
                xidTypePointTestPumpEntity.genericAuxIdentification2 = it.xidTypePointTestPump.genericAuxIdentification2
                xidTypePointTestPumpEntity.genericAuxIdentification3 = it.xidTypePointTestPump.genericAuxIdentification3
                xidTypePointTestPumpEntity.genericAuxIdentification4 = it.xidTypePointTestPump.genericAuxIdentification4
                xidTypePointTestPumpEntity.forAnythingExtra1 = it.xidTypePointTestPump.forAnythingExtra1
                xidTypePointTestPumpEntity.forAnythingExtra2 = it.xidTypePointTestPump.forAnythingExtra2
                xidTypePointTestPumpEntity.forAnythingExtra3 = it.xidTypePointTestPump.forAnythingExtra3
                xidTypePointTestPumpEntity.forAnythingExtra4 = it.xidTypePointTestPump.forAnythingExtra4
                xidTypePointTestPumpEntity.betaDatabaseReleased = it.xidTypePointTestPump.betaDatabaseReleased
                xidTypePointTestPumpEntity.developmentDatabaseReleased = it.xidTypePointTestPump.developmentDatabaseReleased
                xidTypePointTestPumpEntity.experimentalDatabaseReleased = it.xidTypePointTestPump.experimentalDatabaseReleased
                xidTypePointTestPumpEntity.officialDatabaseReleased = it.xidTypePointTestPump.officialDatabaseReleased
                xidTypePointTestPumpEntity.other1DatabaseReleased = it.xidTypePointTestPump.other1DatabaseReleased
                xidTypePointTestPumpEntity.other2DatabaseReleased = it.xidTypePointTestPump.other2DatabaseReleased

                if (it.xidTypePointTestPump.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTypePointTestPump.action == ValueDefault.SRT_REMOVED
                    || it.xidTypePointTestPump.action == ValueDefault.SRT_DELETADO
                    || it.xidTypePointTestPump.action == ValueDefault.SRT_DELETED
                    || it.xidTypePointTestPump.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTypePointTestPump.actionNumber == ValueDefault.REMOVED
                    || it.xidTypePointTestPump.actionNumber == ValueDefault.DELETADO
                    || it.xidTypePointTestPump.actionNumber == ValueDefault.DELETED
                ) {

                    val typePointTestPumpEntityAuxList: List<TypePointTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.typePointTestPumpDao()
                            ?.findByIdList(it.typePointTestPump.id)

                    val xidTypePointTestPumpEntityList: List<XidTypePointTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTypePointTestPumpDao()
                            ?.findByObjectIdList(it.typePointTestPump.id.toString())

                    if (!typePointTestPumpEntityAuxList.isNullOrEmpty()) {
                        typePointTestPumpEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.typePointTestPumpDao()?.delete(it01)
                        }
                    }

                    if (!xidTypePointTestPumpEntityList.isNullOrEmpty()) {
                        xidTypePointTestPumpEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePointTestPumpDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var typePointTestPumpEntityAux: TypePointTestPumpEntity? = null

                    val typePointTestPumpEntityAuxList: List<TypePointTestPumpEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.typePointTestPumpDao()
                            ?.findByIdList(it.typePointTestPump.id)

                    if (!typePointTestPumpEntityAuxList.isNullOrEmpty()
                        && typePointTestPumpEntityAuxList.size >= 2
                    ) {
                        typePointTestPumpEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)?.typePointTestPumpDao()?.delete(it1)
                        }
                    } else if (!typePointTestPumpEntityAuxList.isNullOrEmpty()
                        && (typePointTestPumpEntityAux != null
                                && typePointTestPumpEntityAux.id == 0)
                    ) {
                        typePointTestPumpEntityAux =
                            typePointTestPumpEntityAuxList[0]
                    }

                    //delay(10)

                    if (typePointTestPumpEntityAux == null
                        || (typePointTestPumpEntityAux != null
                                && typePointTestPumpEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.typePointTestPumpDao()?.insert(typePointTestPumpEntity)
                    } else if (typePointTestPumpEntityAux.id != 0) {
                        bUpdate = true
                        typePointTestPumpEntity.id = typePointTestPumpEntityAux.id
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.typePointTestPumpDao()?.update(typePointTestPumpEntity)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePointTestPumpDao()?.insert(xidTypePointTestPumpEntity)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePointTestPumpDao()?.update(xidTypePointTestPumpEntity)
                        }
                    }
                }

                if (it.xidTypePointTestPump.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTypePointTestPump.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTypePointTestPumpDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID,
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
            PARAMETER_TYPE_POINT_TEST_PUMP_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidTypePointTestPumpDao()?.maxXid()
            ?: 0
    }


}