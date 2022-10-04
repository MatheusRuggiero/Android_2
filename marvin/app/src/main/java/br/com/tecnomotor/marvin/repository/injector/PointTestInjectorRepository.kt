package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.injector.PointTestInjectorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.injector.PointInjectorTestEntity
import br.com.tecnomotor.marvin.dao.entities.injector.XidPointTestInjectorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource

class PointTestInjectorRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<PointInjectorTestEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findByPointTestInjectorUsingIdPlanListLiveDataResource(id: Int): LiveData<Resource<List<PointInjectorTestEntity>?>> {
        mediator.addSource(findByPointTestInjectorUsingIdPlanListLiveData(id)) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<PointInjectorTestEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<PointInjectorTestEntity>?> =
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

    private fun findByPointTestInjectorUsingIdPlanListLiveData(id: Int): LiveData<MutableList<PointInjectorTestEntity>> {
        return mAppDatabase?.pointTestInjectorDao()!!
            .findByPointTestInjectorUsingIdPlanListLiveData(id)
    }


    fun findByPointTestInjectorUsingIdPlanList(id: Int): LiveData<Resource<List<PointInjectorTestEntity>?>> {
        mediator.addSource(findByPointTestInjectorUsingIdPlanLiveData(id)) {
            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<PointInjectorTestEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<PointInjectorTestEntity>?> =
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

    private fun findByPointTestInjectorUsingIdPlanLiveData(id: Int): LiveData<MutableList<PointInjectorTestEntity>> {
        return mAppDatabase?.pointTestInjectorDao()!!
            .findByPointTestInjectorUsingIdPlanListLiveData(id)
    }

//    fun findByPointTestInjectorUsingId(id: Int): List<PointInjectorTestEntity>? {
//        return mAppDatabase?.pointTestInjectorDao()!!.findByPointTestInjectorUsingId(id)
//    }

    fun findByPointTestInjectorUpdate(pointInjectorTestEntity: PointInjectorTestEntity) {
        return mAppDatabase?.pointTestInjectorDao()!!.update(pointInjectorTestEntity)
    }

    fun saveListObjectSynchronized(list: List<PointTestInjectorSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID
                )!!.toInt()
                val pointTestInjector = PointInjectorTestEntity()
                pointTestInjector.planTestInjector = it.pointTestInjector.planTestInjector
                pointTestInjector.typePointTestInjector = it.pointTestInjector.typePointTestInjector!!.id
                pointTestInjector.sequence = it.pointTestInjector.sequence
                pointTestInjector.frequency = it.pointTestInjector.frequency
                pointTestInjector.timeTest = it.pointTestInjector.timeTest
                pointTestInjector.timeInjection = it.pointTestInjector.timeInjection
                pointTestInjector.timeOn = it.pointTestInjector.timeOn
                pointTestInjector.timeOff = it.pointTestInjector.timeOff
                pointTestInjector.timeSignalHigh = it.pointTestInjector.timeSignalHigh
                pointTestInjector.timeSignalCstHigh = it.pointTestInjector.timeSignalCstHigh
                pointTestInjector.injectionActive = it.pointTestInjector.injectionActive
                pointTestInjector.measureInjection = it.pointTestInjector.measureInjection
                pointTestInjector.measureReturn = it.pointTestInjector.measureReturn
                pointTestInjector.injectionMaximum = it.pointTestInjector.injectionMaximum!!.toDouble()
                pointTestInjector.injectionMinimum = it.pointTestInjector.injectionMinimum!!.toDouble()
                pointTestInjector.returnMaximum = it.pointTestInjector.returnMaximum!!.toDouble()
                pointTestInjector.returnMinimum = it.pointTestInjector.returnMinimum!!.toDouble()
                pointTestInjector.preFillMedInj = it.pointTestInjector.preFillMedInj
                pointTestInjector.presetUser = it.pointTestInjector.presetUser
                pointTestInjector.originPoint = it.pointTestInjector.originPoint
                pointTestInjector.timeSignalOff = it.pointTestInjector.timeSignalOff
                pointTestInjector.measureInjection = it.pointTestInjector.measureInjection
                pointTestInjector.tensionHigh = it.pointTestInjector.tensionHigh
                pointTestInjector.tensionSmaller = it.pointTestInjector.tensionSmaller
                pointTestInjector.emptyCH1 = it.pointTestInjector.emptyCH1
                pointTestInjector.peakCurrentCH1 = it.pointTestInjector.peakCurrentCH1
                pointTestInjector.currentMaximumCH1 = it.pointTestInjector.currentMaximumCH1
                pointTestInjector.currentMinimumCH1 = it.pointTestInjector.currentMinimumCH1
                pointTestInjector.delayTimeCH1 = it.pointTestInjector.delayTimeCH1
                pointTestInjector.empty1CH1 = it.pointTestInjector.empty1CH1
                pointTestInjector.empty2CH1 = it.pointTestInjector.empty2CH1
                pointTestInjector.empty3CH1 = it.pointTestInjector.empty3CH1
                pointTestInjector.empty4CH1 = it.pointTestInjector.empty4CH1
                pointTestInjector.delayPulseCH1 = it.pointTestInjector.delayPulseCH1
                pointTestInjector.firstCH1 = it.pointTestInjector.firstCH1
                pointTestInjector.timeTest2 = it.pointTestInjector.timeTest2
                pointTestInjector.timeInjection2 = it.pointTestInjector.timeInjection2
                pointTestInjector.timeOn2 = it.pointTestInjector.timeOn2
                pointTestInjector.timeOff2 = it.pointTestInjector.timeOff2
                pointTestInjector.timeSignalHigh2 = it.pointTestInjector.timeSignalHigh2
                pointTestInjector.timeSignalCstHigh2 = it.pointTestInjector.timeSignalCstHigh2
                pointTestInjector.injectionActive2 = it.pointTestInjector.injectionActive2
                pointTestInjector.measureInjection2 = it.pointTestInjector.measureInjection2
                pointTestInjector.measureReturn2 = it.pointTestInjector.measureReturn2
                pointTestInjector.injectionMaximum2 = it.pointTestInjector.injectionMaximum2?.toDouble() ?: 0.00
                pointTestInjector.injectionMinimum2 = it.pointTestInjector.injectionMinimum2?.toDouble() ?: 0.00
                pointTestInjector.injectionMinimum2 = it.pointTestInjector.injectionMinimum2?.toDouble() ?: 0.00
                pointTestInjector.returnMinimum2 = it.pointTestInjector.returnMinimum2?.toDouble() ?: 0.00
                pointTestInjector.preFillMedInj2 = it.pointTestInjector.preFillMedInj2
                pointTestInjector.presetUser2 = it.pointTestInjector.presetUser2
                pointTestInjector.originPoint2 = it.pointTestInjector.originPoint2
                pointTestInjector.timeSignalOff2 = it.pointTestInjector.timeSignalOff2
                pointTestInjector.tensionHigh2 = it.pointTestInjector.tensionHigh2
                pointTestInjector.tensionSmaller2 = it.pointTestInjector.tensionSmaller2
                pointTestInjector.emptyCH2 = it.pointTestInjector.emptyCH2
                pointTestInjector.peakCurrentCH2 = it.pointTestInjector.peakCurrentCH2
                pointTestInjector.currentMaximumCH2 = it.pointTestInjector.currentMaximumCH2
                pointTestInjector.currentMinimumCH2 = it.pointTestInjector.currentMinimumCH2
                pointTestInjector.delayTimeCH2 = it.pointTestInjector.delayTimeCH2
                pointTestInjector.empty1CH2 = it.pointTestInjector.empty1CH2
                pointTestInjector.empty2CH2 = it.pointTestInjector.empty2CH2
                pointTestInjector.empty3CH2 = it.pointTestInjector.empty3CH2
                pointTestInjector.empty4CH2 = it.pointTestInjector.empty4CH2
                pointTestInjector.delayPulseCH2 = it.pointTestInjector.delayPulseCH2
                pointTestInjector.firstCH2 = it.pointTestInjector.firstCH2
                pointTestInjector.responseTimeMin = it.pointTestInjector.responseTimeMax?.toDouble() ?: 0.00
                pointTestInjector.responseTimeMax = it.pointTestInjector.responseTimeMin?.toDouble() ?: 0.00

                val xidPointTestInjector = XidPointTestInjectorEntity()
                xidPointTestInjector.id = it.xidPointTestInjector.id
                xidPointTestInjector.action = it.xidPointTestInjector.action
                xidPointTestInjector.actionNumber = it.xidPointTestInjector.actionNumber
                xidPointTestInjector.brand = it.xidPointTestInjector.brand
                xidPointTestInjector.brandId = it.xidPointTestInjector.brandId
                xidPointTestInjector.classResponsible = it.xidPointTestInjector.classResponsible
                xidPointTestInjector.createdDateObject = it.xidPointTestInjector.createdDateObject
                xidPointTestInjector.identification = it.xidPointTestInjector.identification
                xidPointTestInjector.identificationAux = it.xidPointTestInjector.identificationAux
                xidPointTestInjector.lastDateUpdate = it.xidPointTestInjector.lastDateUpdate
                xidPointTestInjector.objectCompositionId = it.xidPointTestInjector.objectCompositionId
                xidPointTestInjector.objectId = it.xidPointTestInjector.objectId
                xidPointTestInjector.variantNameTable1 = it.xidPointTestInjector.variantNameTable1
                xidPointTestInjector.variantNameTable2 = it.xidPointTestInjector.variantNameTable2
                xidPointTestInjector.variantNameTable3 = it.xidPointTestInjector.variantNameTable3
                xidPointTestInjector.variantNameTable4 = it.xidPointTestInjector.variantNameTable4
                xidPointTestInjector.variantNameTable5 = it.xidPointTestInjector.variantNameTable5
                xidPointTestInjector.variantNameTable6 = it.xidPointTestInjector.variantNameTable6
                xidPointTestInjector.responsibleId = it.xidPointTestInjector.responsibleId
                xidPointTestInjector.responsibleName = it.xidPointTestInjector.responsibleName
                xidPointTestInjector.responsibleToken = it.xidPointTestInjector.responsibleToken
                xidPointTestInjector.revisionId = it.xidPointTestInjector.revisionId
                xidPointTestInjector.revisionMotivation = it.xidPointTestInjector.revisionMotivation
                xidPointTestInjector.revisionMotivationObjectId = it.xidPointTestInjector.revisionMotivationObjectId
                xidPointTestInjector.revisionObjectMotivation = it.xidPointTestInjector.revisionObjectMotivation
                xidPointTestInjector.revisionObjectObservation = it.xidPointTestInjector.revisionObjectObservation
                xidPointTestInjector.versionDatabase = it.xidPointTestInjector.versionDatabase
                xidPointTestInjector.xid = it.xidPointTestInjector.xid
                xidPointTestInjector.platformId = it.xidPointTestInjector.platformId
                xidPointTestInjector.platform = it.xidPointTestInjector.platform
                xidPointTestInjector.genericAuxInfo1 = it.xidPointTestInjector.genericAuxInfo1
                xidPointTestInjector.genericAuxInfo2 = it.xidPointTestInjector.genericAuxInfo2
                xidPointTestInjector.genericAuxInfo3 = it.xidPointTestInjector.genericAuxInfo3
                xidPointTestInjector.genericAuxInfo4 = it.xidPointTestInjector.genericAuxInfo4
                xidPointTestInjector.genericAuxIdentification1 = it.xidPointTestInjector.genericAuxIdentification1
                xidPointTestInjector.genericAuxIdentification2 = it.xidPointTestInjector.genericAuxIdentification2
                xidPointTestInjector.genericAuxIdentification3 = it.xidPointTestInjector.genericAuxIdentification3
                xidPointTestInjector.genericAuxIdentification4 = it.xidPointTestInjector.genericAuxIdentification4
                xidPointTestInjector.forAnythingExtra1 = it.xidPointTestInjector.forAnythingExtra1
                xidPointTestInjector.forAnythingExtra2 = it.xidPointTestInjector.forAnythingExtra2
                xidPointTestInjector.forAnythingExtra3 = it.xidPointTestInjector.forAnythingExtra3
                xidPointTestInjector.forAnythingExtra4 = it.xidPointTestInjector.forAnythingExtra4
                xidPointTestInjector.backupDatabase = it.xidPointTestInjector.backupDatabase
                xidPointTestInjector.betaDatabaseReleased = it.xidPointTestInjector.betaDatabaseReleased
                xidPointTestInjector.developmentDatabaseReleased = it.xidPointTestInjector.developmentDatabaseReleased
                xidPointTestInjector.experimentalDatabaseReleased = it.xidPointTestInjector.experimentalDatabaseReleased
                xidPointTestInjector.officialDatabaseReleased = it.xidPointTestInjector.officialDatabaseReleased
                xidPointTestInjector.other1DatabaseReleased = it.xidPointTestInjector.other1DatabaseReleased
                xidPointTestInjector.other2DatabaseReleased = it.xidPointTestInjector.other2DatabaseReleased

                if (it.xidPointTestInjector.action == ValueDefault.SRT_REMOVIDO
                    || it.xidPointTestInjector.action == ValueDefault.SRT_REMOVED
                    || it.xidPointTestInjector.action == ValueDefault.SRT_DELETADO
                    || it.xidPointTestInjector.action == ValueDefault.SRT_DELETED
                    || it.xidPointTestInjector.actionNumber == ValueDefault.REMOVIDO
                    || it.xidPointTestInjector.actionNumber == ValueDefault.REMOVED
                    || it.xidPointTestInjector.actionNumber == ValueDefault.DELETADO
                    || it.xidPointTestInjector.actionNumber == ValueDefault.DELETED
                ) {

                    val pointTestEntityAuxListInjector: List<PointInjectorTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)
                            ?.pointTestInjectorDao()?.findByObjectCompositeQueryList(
                            pointTestInjector.planTestInjector,
                            pointTestInjector.typePointTestInjector,
                            pointTestInjector.sequence,
                        )

                    val xidPointTestInjectorEntityList: List<XidPointTestInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)
                            ?.xidPointTestInjectorDao()
                            ?.findByObjectIdAndCompositionIdAndAuxIdentification1(
                                pointTestInjector.planTestInjector.toString(),
                                pointTestInjector.typePointTestInjector.toString(),
                                pointTestInjector.sequence.toString(),
                            )

                    if (!pointTestEntityAuxListInjector.isNullOrEmpty()) {
                        pointTestEntityAuxListInjector.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)
                                ?.pointTestInjectorDao()?.delete(it01)
                        }
                    }

                    if (!xidPointTestInjectorEntityList.isNullOrEmpty()) {
                        xidPointTestInjectorEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)
                                ?.xidPointTestInjectorDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false

                    var pointInjectorTestEntityAux: PointInjectorTestEntity? = null

                    val pointInjectorTestEntityAuxList: List<PointInjectorTestEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)
                            ?.pointTestInjectorDao()?.findByObjectCompositeQueryList(
                            pointTestInjector.planTestInjector,
                            pointTestInjector.typePointTestInjector,
                            pointTestInjector.sequence,
                        )

                    if (!pointInjectorTestEntityAuxList.isNullOrEmpty()
                        && pointInjectorTestEntityAuxList.size >= 2
                    ) {
                        pointInjectorTestEntityAuxList.forEach { it1 ->

                            AppDatabase.getDatabase(application.applicationContext)
                                ?.pointTestInjectorDao()?.delete(it1)
                        }
                    } else if (!pointInjectorTestEntityAuxList.isNullOrEmpty()) {
                        pointInjectorTestEntityAux = pointInjectorTestEntityAuxList[0]
                    }

                    //delay(10)

                    if (pointInjectorTestEntityAux == null
                        || (pointInjectorTestEntityAux != null
                                && pointTestInjector.planTestInjector == 0
                                && pointInjectorTestEntityAux.typePointTestInjector == 0
                                && pointInjectorTestEntityAux.sequence == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)
                            ?.pointTestInjectorDao()?.insert(pointTestInjector)
                    } else if (pointTestInjector.planTestInjector != 0 && pointInjectorTestEntityAux.typePointTestInjector != 0) {
                        bUpdate = true
                        existInDatabase = true
                        pointTestInjector.planTestInjector =
                            pointInjectorTestEntityAux.planTestInjector
                        pointTestInjector.sequence = pointInjectorTestEntityAux.sequence
                        AppDatabase.getDatabase(application.applicationContext)
                            ?.pointTestInjectorDao()?.update(pointTestInjector)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)
                                ?.xidPointTestInjectorDao()?.insert(xidPointTestInjector)
                        } else if (bUpdate) {

                            AppDatabase.getDatabase(application.applicationContext)
                                ?.xidPointTestInjectorDao()?.update(xidPointTestInjector)
                        }
                    }
                }
                if (it.xidPointTestInjector.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidPointTestInjector.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)
                        ?.xidPointTestInjectorDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID,
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
            PARAMETER_POINT_TEST_INJECTOR_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidPointTestInjectorDao()?.maxXid()
            ?: 0
    }

}