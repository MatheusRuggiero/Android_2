package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.api.v1.model.injector.TypePointInjectorTestSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.injector.TypePointInjectorTestEntity
import br.com.tecnomotor.marvin.dao.entities.injector.XidTypePointInjectorTestEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class TypePointInjectorTestRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<TypePointInjectorTestEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findByTypeInjectorIdLiveData(id: Int): LiveData<TypePointInjectorTestEntity>? {
        return mAppDatabase?.typePointInjectorTestDao()?.findByTypeInjectorIdLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<TypePointInjectorTestSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID
                )!!.toInt()
                val typePointInjectorTest = TypePointInjectorTestEntity()
                typePointInjectorTest.id = it.typePointTestInjector.id
                typePointInjectorTest.description = it.typePointTestInjector.description
                typePointInjectorTest.typePoint = it.typePointTestInjector.typePoint

                val xidTypePointTestInjector = XidTypePointInjectorTestEntity()
                xidTypePointTestInjector.id = it.xidTypePointTestInjector.id
                xidTypePointTestInjector.action = it.xidTypePointTestInjector.action
                xidTypePointTestInjector.actionNumber = it.xidTypePointTestInjector.actionNumber
                xidTypePointTestInjector.brand = it.xidTypePointTestInjector.brand
                xidTypePointTestInjector.brandId = it.xidTypePointTestInjector.brandId
                xidTypePointTestInjector.classResponsible = it.xidTypePointTestInjector.classResponsible
                xidTypePointTestInjector.createdDateObject = it.xidTypePointTestInjector.createdDateObject
                xidTypePointTestInjector.identification = it.xidTypePointTestInjector.identification
                xidTypePointTestInjector.identificationAux = it.xidTypePointTestInjector.identificationAux
                xidTypePointTestInjector.lastDateUpdate = it.xidTypePointTestInjector.lastDateUpdate
                xidTypePointTestInjector.objectCompositionId = it.xidTypePointTestInjector.objectCompositionId
                xidTypePointTestInjector.objectId = it.xidTypePointTestInjector.objectId
                xidTypePointTestInjector.variantNameTable1 = it.xidTypePointTestInjector.variantNameTable1
                xidTypePointTestInjector.variantNameTable2 = it.xidTypePointTestInjector.variantNameTable2
                xidTypePointTestInjector.variantNameTable3 = it.xidTypePointTestInjector.variantNameTable3
                xidTypePointTestInjector.variantNameTable4 = it.xidTypePointTestInjector.variantNameTable4
                xidTypePointTestInjector.variantNameTable5 = it.xidTypePointTestInjector.variantNameTable5
                xidTypePointTestInjector.variantNameTable6 = it.xidTypePointTestInjector.variantNameTable6
                xidTypePointTestInjector.responsibleId = it.xidTypePointTestInjector.responsibleId
                xidTypePointTestInjector.responsibleName = it.xidTypePointTestInjector.responsibleName
                xidTypePointTestInjector.responsibleToken = it.xidTypePointTestInjector.responsibleToken
                xidTypePointTestInjector.revisionId = it.xidTypePointTestInjector.revisionId
                xidTypePointTestInjector.revisionMotivation = it.xidTypePointTestInjector.revisionMotivation
                xidTypePointTestInjector.revisionMotivationObjectId = it.xidTypePointTestInjector.revisionMotivationObjectId
                xidTypePointTestInjector.revisionObjectMotivation = it.xidTypePointTestInjector.revisionObjectMotivation
                xidTypePointTestInjector.revisionObjectObservation = it.xidTypePointTestInjector.revisionObjectObservation
                xidTypePointTestInjector.versionDatabase = it.xidTypePointTestInjector.versionDatabase
                xidTypePointTestInjector.xid = it.xidTypePointTestInjector.xid
                xidTypePointTestInjector.platformId = it.xidTypePointTestInjector.platformId
                xidTypePointTestInjector.platform = it.xidTypePointTestInjector.platform
                xidTypePointTestInjector.backupDatabase = it.xidTypePointTestInjector.backupDatabase
                xidTypePointTestInjector.genericAuxInfo1 = it.xidTypePointTestInjector.genericAuxInfo1
                xidTypePointTestInjector.genericAuxInfo2 = it.xidTypePointTestInjector.genericAuxInfo2
                xidTypePointTestInjector.genericAuxInfo3 = it.xidTypePointTestInjector.genericAuxInfo3
                xidTypePointTestInjector.genericAuxInfo4 = it.xidTypePointTestInjector.genericAuxInfo4
                xidTypePointTestInjector.genericAuxIdentification1 = it.xidTypePointTestInjector.genericAuxIdentification1
                xidTypePointTestInjector.genericAuxIdentification2 = it.xidTypePointTestInjector.genericAuxIdentification2
                xidTypePointTestInjector.genericAuxIdentification3 = it.xidTypePointTestInjector.genericAuxIdentification3
                xidTypePointTestInjector.genericAuxIdentification4 = it.xidTypePointTestInjector.genericAuxIdentification4
                xidTypePointTestInjector.forAnythingExtra1 = it.xidTypePointTestInjector.forAnythingExtra1
                xidTypePointTestInjector.forAnythingExtra2 = it.xidTypePointTestInjector.forAnythingExtra2
                xidTypePointTestInjector.forAnythingExtra3 = it.xidTypePointTestInjector.forAnythingExtra3
                xidTypePointTestInjector.forAnythingExtra4 = it.xidTypePointTestInjector.forAnythingExtra4
                xidTypePointTestInjector.betaDatabaseReleased = it.xidTypePointTestInjector.betaDatabaseReleased
                xidTypePointTestInjector.developmentDatabaseReleased = it.xidTypePointTestInjector.developmentDatabaseReleased
                xidTypePointTestInjector.experimentalDatabaseReleased = it.xidTypePointTestInjector.experimentalDatabaseReleased
                xidTypePointTestInjector.officialDatabaseReleased = it.xidTypePointTestInjector.officialDatabaseReleased
                xidTypePointTestInjector.other1DatabaseReleased = it.xidTypePointTestInjector.other1DatabaseReleased
                xidTypePointTestInjector.other2DatabaseReleased = it.xidTypePointTestInjector.other2DatabaseReleased

                if (it.xidTypePointTestInjector.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTypePointTestInjector.action == ValueDefault.SRT_REMOVED
                    || it.xidTypePointTestInjector.action == ValueDefault.SRT_DELETADO
                    || it.xidTypePointTestInjector.action == ValueDefault.SRT_DELETED
                    || it.xidTypePointTestInjector.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTypePointTestInjector.actionNumber == ValueDefault.REMOVED
                    || it.xidTypePointTestInjector.actionNumber == ValueDefault.DELETADO
                    || it.xidTypePointTestInjector.actionNumber == ValueDefault.DELETED
                ) {
                    val typePointInjectorEntityAuxList: List<TypePointInjectorTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.typePointInjectorTestDao()
                            ?.findByIdList(it.typePointTestInjector.id)

                    val xidTypePointInjectorEntityList: List<XidTypePointInjectorTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTypePointInjectorTestDao()
                            ?.findByObjectIdList(it.typePointTestInjector.id.toString())

                    if (!typePointInjectorEntityAuxList.isNullOrEmpty()) {
                        typePointInjectorEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.typePointInjectorTestDao()?.delete(it01)
                        }
                    }

                    if (!xidTypePointInjectorEntityList.isNullOrEmpty()) {
                        xidTypePointInjectorEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePointInjectorTestDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var typePointInjectorTestEntityAux: TypePointInjectorTestEntity? = null

                    val typePointInjectorTestEntityAuxList: List<TypePointInjectorTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.typePointInjectorTestDao()
                            ?.findByIdList(it.typePointTestInjector.id)

                    if (!typePointInjectorTestEntityAuxList.isNullOrEmpty()
                        && typePointInjectorTestEntityAuxList.size >= 2
                    ) {
                        typePointInjectorTestEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.typePointInjectorTestDao()?.delete(it1)
                        }
                    } else if (!typePointInjectorTestEntityAuxList.isNullOrEmpty()
                        && (typePointInjectorTestEntityAux != null
                                && typePointInjectorTestEntityAux.id == 0)
                    ) {
                        typePointInjectorTestEntityAux =
                            typePointInjectorTestEntityAuxList[0]
                    }

                    //delay(10)

                    if (typePointInjectorTestEntityAux == null
                        || (typePointInjectorTestEntityAux != null
                                && typePointInjectorTestEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.typePointInjectorTestDao()?.insert(typePointInjectorTest)
                    } else if (typePointInjectorTestEntityAux.id != 0) {
                        bUpdate = true
                        typePointInjectorTest.id = typePointInjectorTestEntityAux.id
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.typePointInjectorTestDao()?.update(typePointInjectorTest)
                    }


                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePointInjectorTestDao()?.insert(xidTypePointTestInjector)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePointInjectorTestDao()?.update(xidTypePointTestInjector)
                        }
                    }
                }

                if (it.xidTypePointTestInjector.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTypePointTestInjector.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTypePointInjectorTestDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID,
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
            PARAMETER_TYPE_POINT_INJECTOR_TEST_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidTypePointInjectorTestDao()?.maxXid()
            ?: 0
    }
}