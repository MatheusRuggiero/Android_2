package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.api.v1.model.injector.InjectorSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorEntity
import br.com.tecnomotor.marvin.dao.entities.injector.XidInjectorEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_INJECTOR_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.model.Resource
import kotlinx.coroutines.flow.Flow

class InjectorRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<InjectorEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }


    fun findAllInjector(): LiveData<Resource<List<InjectorEntity>?>> {
        mediator.addSource(findAll()) {

            mediator.value = Resource(data = it)

            val errorLiveData = MutableLiveData<Resource<List<InjectorEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<InjectorEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }


    fun findByInjectorUsingCodeLiveData(str: String): LiveData<Resource<List<InjectorEntity>?>> {
        mediator.addSource(mAppDatabase?.injectorDao()!!.findByInjectorUsingCodeLiveData(str)) {

            mediator.value = Resource(data = it)


            val errorLiveData = MutableLiveData<Resource<List<InjectorEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<InjectorEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    fun findByInjectorUsingCodeList(str: String): List<InjectorEntity>? {
        return mAppDatabase?.injectorDao()!!.findByInjectorUsingCodeList(str)
    }

    fun findByInjectorUsingBrandLiveData(str: String): LiveData<Resource<List<InjectorEntity>?>> {
        mediator.addSource(mAppDatabase?.injectorDao()!!.findByInjectorUsingBrandLiveData(str)) {

            mediator.value = Resource(data = it)


            val errorLiveData = MutableLiveData<Resource<List<InjectorEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<InjectorEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    fun findByInjectorUsingBrandList(str: String): List<InjectorEntity>? {
        return mAppDatabase?.injectorDao()!!.findByInjectorUsingBrandList(str)
    }

    fun findByInjectorUsingAdapterLiveData(str: String): LiveData<Resource<List<InjectorEntity>?>> {
        mediator.addSource(mAppDatabase?.injectorDao()!!.findByInjectorUsingAdapterLiveData(str)) {

            mediator.value = Resource(data = it)


            val errorLiveData = MutableLiveData<Resource<List<InjectorEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<InjectorEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    fun findByInjectorUsingAdapterList(str: String): List<InjectorEntity>? {
        return mAppDatabase?.injectorDao()!!.findByInjectorUsingAdapterList(str)
    }

    fun findByInjectorUsingApplicationLiveData(str: String): LiveData<Resource<List<InjectorEntity>?>> {
        mediator.addSource(mAppDatabase?.injectorDao()!!.findByInjectorUsingApplicationLiveData(str)) {

            mediator.value = Resource(data = it)


            val errorLiveData = MutableLiveData<Resource<List<InjectorEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resourceActually = mediator.value
                val resourceNew: Resource<List<InjectorEntity>?> = if (resourceActually != null) {
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                } else {
                    resourceFailure
                }
                mediator.value = resourceNew
            }
        }

        return mediator
    }

    fun findByInjectorUsingApplicationList(str: String): List<InjectorEntity>? {
        return mAppDatabase?.injectorDao()!!.findByInjectorUsingApplicationList(str)
    }

    fun findByInjectorUsingCodeFlow(str: String): Flow<List<InjectorEntity>?> {
        return mAppDatabase?.injectorDao()!!.findByInjectorUsingCodeFlow(str)
    }


    fun findAll(): LiveData<List<InjectorEntity>> {
        return mAppDatabase?.injectorDao()!!.findAllLiveData()
    }

    fun saveListObjectSynchronized(list: List<InjectorSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_INJECTOR_CURRENT_XID
                )!!.toInt()
                val injector = InjectorEntity()
                injector.id = it.injector.id
                injector.code = it.injector.code
                injector.standard = it.injector.standard
                injector.brandId = it.injector.brand!!.id
                injector.application = it.injector.application
                injector.tipo = it.injector.typeId
                injector.adaptPressure = it.injector.adaptPressure
                injector.adaptConnector = it.injector.adaptConnector
                injector.adaptReturn = it.injector.adaptReturn
                injector.resistanceMinimum = it.injector.resistanceMinimum?.toDouble() ?: 0.00
                injector.resistanceMaximum = it.injector.resistanceMaximum?.toDouble() ?: 0.00
                injector.resistanceMinimumB = it.injector.resistanceMinimumB?.toDouble() ?: 0.00
                injector.resistanceMaximumB = it.injector.resistanceMinimumB?.toDouble() ?: 0.00
                injector.token = it.injector.token
                injector.revision = it.injector.revisionNumber
                injector.deleted = it.injector.deleted

                val xidInjector = XidInjectorEntity()
                xidInjector.id = it.xidInjector.id
                xidInjector.action = it.xidInjector.action
                xidInjector.actionNumber = it.xidInjector.actionNumber
                xidInjector.brand = it.xidInjector.brand
                xidInjector.brandId = it.xidInjector.brandId
                xidInjector.classResponsible = it.xidInjector.classResponsible
                xidInjector.createdDateObject = it.xidInjector.createdDateObject
                xidInjector.identification = it.xidInjector.identification
                xidInjector.identificationAux = it.xidInjector.identificationAux
                xidInjector.lastDateUpdate = it.xidInjector.lastDateUpdate
                xidInjector.objectCompositionId = it.xidInjector.objectCompositionId
                xidInjector.objectId = it.xidInjector.objectId
                xidInjector.variantNameTable1 = it.xidInjector.variantNameTable1
                xidInjector.variantNameTable2 = it.xidInjector.variantNameTable2
                xidInjector.variantNameTable3 = it.xidInjector.variantNameTable3
                xidInjector.variantNameTable4 = it.xidInjector.variantNameTable4
                xidInjector.variantNameTable5 = it.xidInjector.variantNameTable5
                xidInjector.variantNameTable6 = it.xidInjector.variantNameTable6
                xidInjector.responsibleId = it.xidInjector.responsibleId
                xidInjector.responsibleName = it.xidInjector.responsibleName
                xidInjector.responsibleToken = it.xidInjector.responsibleToken
                xidInjector.revisionId = it.xidInjector.revisionId
                xidInjector.revisionMotivation = it.xidInjector.revisionMotivation
                xidInjector.revisionMotivationObjectId = it.xidInjector.revisionMotivationObjectId
                xidInjector.revisionObjectMotivation = it.xidInjector.revisionObjectMotivation
                xidInjector.revisionObjectObservation = it.xidInjector.revisionObjectObservation
                xidInjector.versionDatabase = it.xidInjector.versionDatabase
                xidInjector.xid = it.xidInjector.xid
                xidInjector.platformId = it.xidInjector.platformId
                xidInjector.platform = it.xidInjector.platform
                xidInjector.genericAuxInfo1 = it.xidInjector.genericAuxInfo1
                xidInjector.genericAuxInfo2 = it.xidInjector.genericAuxInfo2
                xidInjector.genericAuxInfo3 = it.xidInjector.genericAuxInfo3
                xidInjector.genericAuxInfo4 = it.xidInjector.genericAuxInfo4
                xidInjector.genericAuxIdentification1 = it.xidInjector.genericAuxIdentification1
                xidInjector.genericAuxIdentification2 = it.xidInjector.genericAuxIdentification2
                xidInjector.genericAuxIdentification3 = it.xidInjector.genericAuxIdentification3
                xidInjector.genericAuxIdentification4 = it.xidInjector.genericAuxIdentification4
                xidInjector.forAnythingExtra1 = it.xidInjector.forAnythingExtra1
                xidInjector.forAnythingExtra2 = it.xidInjector.forAnythingExtra2
                xidInjector.forAnythingExtra3 = it.xidInjector.forAnythingExtra3
                xidInjector.forAnythingExtra4 = it.xidInjector.forAnythingExtra4
                xidInjector.backupDatabase = it.xidInjector.backupDatabase
                xidInjector.betaDatabaseReleased = it.xidInjector.betaDatabaseReleased
                xidInjector.developmentDatabaseReleased = it.xidInjector.developmentDatabaseReleased
                xidInjector.experimentalDatabaseReleased = it.xidInjector.experimentalDatabaseReleased
                xidInjector.officialDatabaseReleased = it.xidInjector.officialDatabaseReleased
                xidInjector.other1DatabaseReleased = it.xidInjector.other1DatabaseReleased
                xidInjector.other2DatabaseReleased = it.xidInjector.other2DatabaseReleased

                if (it.xidInjector.action == ValueDefault.SRT_REMOVIDO
                    || it.xidInjector.action == ValueDefault.SRT_REMOVED
                    || it.xidInjector.action == ValueDefault.SRT_DELETADO
                    || it.xidInjector.action == ValueDefault.SRT_DELETED
                    || it.xidInjector.actionNumber == ValueDefault.REMOVIDO
                    || it.xidInjector.actionNumber == ValueDefault.REMOVED
                    || it.xidInjector.actionNumber == ValueDefault.DELETADO
                    || it.xidInjector.actionNumber == ValueDefault.DELETED
                ) {

                    val injectorEntityAuxList: List<InjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.injectorDao()?.findByIdList(it.injector.id)

                    val xidInjectorEntityList: List<XidInjectorEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidInjectorDao()?.findByObjectIdList(it.injector.id.toString())

                    if (!injectorEntityAuxList.isNullOrEmpty()) {
                        injectorEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.injectorDao()?.delete(it01)
                        }
                    }

                    if (!xidInjectorEntityList.isNullOrEmpty()) {
                        xidInjectorEntityList.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidInjectorDao()?.delete(it02)
                        }
                    }
                } else {
                    var bInsert = false
                    var bUpdate = false

                    var injectorEntityAux: InjectorEntity? = null
                    val injectorEntityAuxList: List<InjectorEntity>? =
                        mAppDatabase?.injectorDao()?.findByIdList(it.injector.id)

                    if (!injectorEntityAuxList.isNullOrEmpty()
                        && injectorEntityAuxList.size >= 2
                    ) {
                        injectorEntityAuxList.forEach { it1 ->
                            mAppDatabase?.injectorDao()?.delete(it1)
                        }
                    } else if (!injectorEntityAuxList.isNullOrEmpty()) {
                        injectorEntityAux = injectorEntityAuxList[0]
                    }

                    //delay(10)

                    if (injectorEntityAux == null
                        || (injectorEntityAux != null
                                && injectorEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        mAppDatabase?.injectorDao()?.insert(injector)
                    } else if (injectorEntityAux.id != 0) {
                        bUpdate = true
                        existInDatabase = true
                        mAppDatabase?.injectorDao()?.update(injector)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            mAppDatabase?.xidInjectorDao()?.insert(xidInjector)
                        } else if (bUpdate) {
                            mAppDatabase?.xidInjectorDao()?.update(xidInjector)
                        }
                    }
                }
                if (it.xidInjector.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidInjector.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_INJECTOR_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidInjectorDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_INJECTOR_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_INJECTOR_CURRENT_XID,
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
            PARAMETER_INJECTOR_CURRENT_XID
        )?.toInt()
            ?: mAppDatabase?.xidInjectorDao()?.maxXid()
            ?: 0
    }

    fun findByInjectorUsingCode(codeInjector: String): InjectorEntity? {
        return mAppDatabase?.injectorDao()?.findByInjectorUsingCode(codeInjector)
    }


}