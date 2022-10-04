package br.com.tecnomotor.marvin.repository.global

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.api.v1.model.global.TypePlanTestSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.global.TypePlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.global.XidTypePlanTestEntity
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlanTestEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_TYPE_PLAN_TEST_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class TypePlanTestRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<TypePlanTestEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findTypePlanByIdLiveData(id: Int): LiveData<TypePlanTestEntity> {
        return mAppDatabase?.typePlanTestDao()!!.findByIdLiveData(id)
    }

    fun findPlanTestByIdLIveData(id: Int): LiveData<InjectorPlanTestEntity?> {
        return mAppDatabase?.injectorPlanTestDao()!!.findByPlanTestInjectorUsingIdPlanLiveData(id)
    }

    fun saveListObjectSynchronized(list: List<TypePlanTestSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_TYPE_PLAN_TEST_CURRENT_XID
                )!!.toInt()
                val typePlanTest = TypePlanTestEntity()
                typePlanTest.id = it.typePlanTest.id
                typePlanTest.description = it.typePlanTest.description

                val xidTypePlanTest = XidTypePlanTestEntity()
                xidTypePlanTest.id = it.xidTypePlanTest.id
                xidTypePlanTest.action = it.xidTypePlanTest.action
                xidTypePlanTest.actionNumber = it.xidTypePlanTest.actionNumber
                xidTypePlanTest.brand = it.xidTypePlanTest.brand
                xidTypePlanTest.brandId = it.xidTypePlanTest.brandId
                xidTypePlanTest.classResponsible = it.xidTypePlanTest.classResponsible
                xidTypePlanTest.createdDateObject = it.xidTypePlanTest.createdDateObject
                xidTypePlanTest.identification = it.xidTypePlanTest.identification
                xidTypePlanTest.identificationAux = it.xidTypePlanTest.identificationAux
                xidTypePlanTest.lastDateUpdate = it.xidTypePlanTest.lastDateUpdate
                xidTypePlanTest.objectCompositionId = it.xidTypePlanTest.objectCompositionId
                xidTypePlanTest.objectId = it.xidTypePlanTest.objectId
                xidTypePlanTest.variantNameTable1 = it.xidTypePlanTest.variantNameTable1
                xidTypePlanTest.variantNameTable2 = it.xidTypePlanTest.variantNameTable2
                xidTypePlanTest.variantNameTable3 = it.xidTypePlanTest.variantNameTable3
                xidTypePlanTest.variantNameTable4 = it.xidTypePlanTest.variantNameTable4
                xidTypePlanTest.variantNameTable5 = it.xidTypePlanTest.variantNameTable5
                xidTypePlanTest.variantNameTable6 = it.xidTypePlanTest.variantNameTable6
                xidTypePlanTest.responsibleId = it.xidTypePlanTest.responsibleId
                xidTypePlanTest.responsibleName = it.xidTypePlanTest.responsibleName
                xidTypePlanTest.responsibleToken = it.xidTypePlanTest.responsibleToken
                xidTypePlanTest.revisionId = it.xidTypePlanTest.revisionId
                xidTypePlanTest.revisionMotivation = it.xidTypePlanTest.revisionMotivation
                xidTypePlanTest.revisionMotivationObjectId = it.xidTypePlanTest.revisionMotivationObjectId
                xidTypePlanTest.revisionObjectMotivation = it.xidTypePlanTest.revisionObjectMotivation
                xidTypePlanTest.revisionObjectObservation = it.xidTypePlanTest.revisionObjectObservation
                xidTypePlanTest.versionDatabase = it.xidTypePlanTest.versionDatabase
                xidTypePlanTest.xid = it.xidTypePlanTest.xid
                xidTypePlanTest.platformId = it.xidTypePlanTest.platformId
                xidTypePlanTest.platform = it.xidTypePlanTest.platform
                xidTypePlanTest.genericAuxInfo1 = it.xidTypePlanTest.genericAuxInfo1
                xidTypePlanTest.genericAuxInfo2 = it.xidTypePlanTest.genericAuxInfo2
                xidTypePlanTest.genericAuxInfo3 = it.xidTypePlanTest.genericAuxInfo3
                xidTypePlanTest.genericAuxInfo4 = it.xidTypePlanTest.genericAuxInfo4
                xidTypePlanTest.genericAuxIdentification1 = it.xidTypePlanTest.genericAuxIdentification1
                xidTypePlanTest.genericAuxIdentification2 = it.xidTypePlanTest.genericAuxIdentification2
                xidTypePlanTest.genericAuxIdentification3 = it.xidTypePlanTest.genericAuxIdentification3
                xidTypePlanTest.genericAuxIdentification4 = it.xidTypePlanTest.genericAuxIdentification4
                xidTypePlanTest.forAnythingExtra1 = it.xidTypePlanTest.forAnythingExtra1
                xidTypePlanTest.forAnythingExtra2 = it.xidTypePlanTest.forAnythingExtra2
                xidTypePlanTest.forAnythingExtra3 = it.xidTypePlanTest.forAnythingExtra3
                xidTypePlanTest.forAnythingExtra4 = it.xidTypePlanTest.forAnythingExtra4
                xidTypePlanTest.backupDatabase = it.xidTypePlanTest.backupDatabase
                xidTypePlanTest.betaDatabaseReleased = it.xidTypePlanTest.betaDatabaseReleased
                xidTypePlanTest.developmentDatabaseReleased = it.xidTypePlanTest.developmentDatabaseReleased
                xidTypePlanTest.experimentalDatabaseReleased = it.xidTypePlanTest.experimentalDatabaseReleased
                xidTypePlanTest.officialDatabaseReleased = it.xidTypePlanTest.officialDatabaseReleased
                xidTypePlanTest.other1DatabaseReleased = it.xidTypePlanTest.other1DatabaseReleased
                xidTypePlanTest.other2DatabaseReleased = it.xidTypePlanTest.other2DatabaseReleased

                if (it.xidTypePlanTest.action == ValueDefault.SRT_REMOVIDO
                    || it.xidTypePlanTest.action == ValueDefault.SRT_REMOVED
                    || it.xidTypePlanTest.action == ValueDefault.SRT_DELETADO
                    || it.xidTypePlanTest.action == ValueDefault.SRT_DELETED
                    || it.xidTypePlanTest.actionNumber == ValueDefault.REMOVIDO
                    || it.xidTypePlanTest.actionNumber == ValueDefault.REMOVED
                    || it.xidTypePlanTest.actionNumber == ValueDefault.DELETADO
                    || it.xidTypePlanTest.actionNumber == ValueDefault.DELETED
                ) {

                    val typePlanTestEntityAuxList: List<TypePlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.typePlanTestDao()?.findByIdList(it.typePlanTest.id)

                    val xidTypePlanTestEntityList: List<XidTypePlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidTypePlanTestDao()
                            ?.findByObjectIdList(it.typePlanTest.id.toString())

                    if (!typePlanTestEntityAuxList.isNullOrEmpty()) {
                        typePlanTestEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.typePlanTestDao()?.delete(it01)
                        }
                    }

                    if (!xidTypePlanTestEntityList.isNullOrEmpty()) {
                        xidTypePlanTestEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePlanTestDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var typePlanTestEntityAux: TypePlanTestEntity? = null

                    val typePlanTestEntityAuxList: List<TypePlanTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.typePlanTestDao()?.findByIdList(it.typePlanTest.id)

                    if (!typePlanTestEntityAuxList.isNullOrEmpty()
                        && typePlanTestEntityAuxList.size >= 2
                    ) {
                        typePlanTestEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.typePlanTestDao()?.delete(it1)
                        }
                    } else if (!typePlanTestEntityAuxList.isNullOrEmpty()) {
                        typePlanTestEntityAux = typePlanTestEntityAuxList[0]
                    }


                    //delay(10)

                    if (typePlanTestEntityAux == null
                        || (typePlanTestEntityAux != null
                                && typePlanTestEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.typePlanTestDao()?.insert(typePlanTest)
                    } else if (typePlanTestEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.typePlanTestDao()?.update(typePlanTestEntityAux)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePlanTestDao()?.insert(xidTypePlanTest)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidTypePlanTestDao()?.update(xidTypePlanTest)
                        }
                    }
                }
                if (it.xidTypePlanTest.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidTypePlanTest.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_TYPE_PLAN_TEST_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidTypePlanTestDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TYPE_PLAN_TEST_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_TYPE_PLAN_TEST_CURRENT_XID,
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
            PARAMETER_TYPE_PLAN_TEST_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidTypePlanTestDao()?.maxXid()
            ?: 0
    }

}