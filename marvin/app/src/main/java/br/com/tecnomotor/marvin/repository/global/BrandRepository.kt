package br.com.tecnomotor.marvin.repository.global

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.api.v1.model.global.BrandSynchronize
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.global.BrandEntity
import br.com.tecnomotor.marvin.dao.entities.global.XidBrandEntity
import br.com.tecnomotor.marvin.model.Resource
import br.com.tecnomotor.marvin.model.global.Brand
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.PARAMETER_BRAND_CURRENT_XID
import br.com.tecnomotor.marvin.utils.constant.ValueDefault
import br.com.tecnomotor.marvin.utils.global.GlobalUtil

class BrandRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName
    private val spConfiguration = application.applicationContext.getSharedPreferences(
        ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
        Context.MODE_PRIVATE
    )

    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<Brand>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun saveListObjectSynchronized(list: List<BrandSynchronize>) {
        try {
            list.forEach {
                var existInDatabase = false
                var maxXidCurrent = GlobalUtil.method.recoverSharedPreferences(
                    spConfiguration,
                    PARAMETER_BRAND_CURRENT_XID
                )!!.toInt()
                val brand = BrandEntity()
                brand.id = it.brand.id
                brand.name = it.brand.name

                val xidBrand = XidBrandEntity()
                xidBrand.id = it.xidBrand.id
                xidBrand.action = it.xidBrand.action
                xidBrand.actionNumber = it.xidBrand.actionNumber
                xidBrand.brandId = it.xidBrand.brandId
                xidBrand.brand = it.xidBrand.brand
                xidBrand.classResponsible = it.xidBrand.classResponsible
                xidBrand.createdDateObject = it.xidBrand.createdDateObject
                xidBrand.identification = it.xidBrand.identification
                xidBrand.identificationAux = it.xidBrand.identificationAux
                xidBrand.lastDateUpdate = it.xidBrand.lastDateUpdate
                xidBrand.objectCompositionId = it.xidBrand.objectCompositionId
                xidBrand.objectId = it.xidBrand.objectId
                xidBrand.variantNameTable1 = it.xidBrand.variantNameTable1
                xidBrand.variantNameTable2 = it.xidBrand.variantNameTable2
                xidBrand.variantNameTable3 = it.xidBrand.variantNameTable3
                xidBrand.variantNameTable4 = it.xidBrand.variantNameTable4
                xidBrand.variantNameTable5 = it.xidBrand.variantNameTable5
                xidBrand.variantNameTable6 = it.xidBrand.variantNameTable6
                xidBrand.responsibleId = it.xidBrand.responsibleId
                xidBrand.responsibleName = it.xidBrand.responsibleName
                xidBrand.responsibleToken = it.xidBrand.responsibleToken
                xidBrand.revisionId = it.xidBrand.revisionId
                xidBrand.revisionMotivation = it.xidBrand.revisionMotivation
                xidBrand.revisionMotivationObjectId = it.xidBrand.revisionMotivationObjectId
                xidBrand.revisionObjectMotivation = it.xidBrand.revisionObjectMotivation
                xidBrand.revisionObjectObservation = it.xidBrand.revisionObjectObservation
                xidBrand.versionDatabase = it.xidBrand.versionDatabase
                xidBrand.xid = it.xidBrand.xid
                xidBrand.platformId = it.xidBrand.platformId
                xidBrand.platform = it.xidBrand.platform
                xidBrand.genericAuxInfo1 = it.xidBrand.genericAuxInfo1
                xidBrand.genericAuxInfo2 = it.xidBrand.genericAuxInfo2
                xidBrand.genericAuxInfo3 = it.xidBrand.genericAuxInfo3
                xidBrand.genericAuxInfo4 = it.xidBrand.genericAuxInfo4
                xidBrand.genericAuxIdentification1 = it.xidBrand.genericAuxIdentification1
                xidBrand.genericAuxIdentification2 = it.xidBrand.genericAuxIdentification2
                xidBrand.genericAuxIdentification3 = it.xidBrand.genericAuxIdentification3
                xidBrand.genericAuxIdentification4 = it.xidBrand.genericAuxIdentification4
                xidBrand.forAnythingExtra1 = it.xidBrand.forAnythingExtra1
                xidBrand.forAnythingExtra2 = it.xidBrand.forAnythingExtra2
                xidBrand.forAnythingExtra3 = it.xidBrand.forAnythingExtra3
                xidBrand.forAnythingExtra4 = it.xidBrand.forAnythingExtra4
                xidBrand.backupDatabase = it.xidBrand.backupDatabase
                xidBrand.betaDatabaseReleased = it.xidBrand.betaDatabaseReleased
                xidBrand.developmentDatabaseReleased = it.xidBrand.developmentDatabaseReleased
                xidBrand.experimentalDatabaseReleased = it.xidBrand.experimentalDatabaseReleased
                xidBrand.officialDatabaseReleased = it.xidBrand.officialDatabaseReleased
                xidBrand.other1DatabaseReleased = it.xidBrand.other1DatabaseReleased
                xidBrand.other2DatabaseReleased = it.xidBrand.other2DatabaseReleased

                if (it.xidBrand.action == ValueDefault.SRT_REMOVIDO
                    || it.xidBrand.action == ValueDefault.SRT_REMOVED
                    || it.xidBrand.action == ValueDefault.SRT_DELETADO
                    || it.xidBrand.action == ValueDefault.SRT_DELETED
                    || it.xidBrand.actionNumber == ValueDefault.REMOVIDO
                    || it.xidBrand.actionNumber == ValueDefault.REMOVED
                    || it.xidBrand.actionNumber == ValueDefault.DELETADO
                    || it.xidBrand.actionNumber == ValueDefault.DELETED
                ) {

                    val brandEntityAuxList: List<BrandEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.brandDao()?.findByIdList(it.brand.id)

                    if (!brandEntityAuxList.isNullOrEmpty()) {
                        brandEntityAuxList.forEach { it01 ->
                            AppDatabase.getDatabase(application.applicationContext)?.brandDao()?.delete(it01)
                        }
                    }

                    val xidBrandEntity: List<XidBrandEntity>? =
                        AppDatabase.getDatabase(application.applicationContext)?.xidBrandDao()?.findByObjectIdList(it.brand.id.toString())

                    if (!xidBrandEntity.isNullOrEmpty()) {
                        xidBrandEntity.forEach { it02 ->
                            AppDatabase.getDatabase(application.applicationContext)?.xidBrandDao()?.delete(it02)
                        }
                    }

                } else {
                    var bInsert = false
                    var bUpdate = false
                    var brandEntityAux: BrandEntity? = null
                    val brandEntityAuxList: List<BrandEntity>? = AppDatabase.getDatabase(application.applicationContext)?.brandDao()?.findByIdList(it.brand.id)

                    if (!brandEntityAuxList.isNullOrEmpty()
                        && brandEntityAuxList.size >= 2
                    ) {
                        brandEntityAuxList.forEach { it1 ->
                            AppDatabase.getDatabase(application.applicationContext)?.brandDao()?.delete(it1)
                        }
                    } else if (!brandEntityAuxList.isNullOrEmpty()) {
                        brandEntityAux = brandEntityAuxList[0]
                    }

                    //delay(10)

                    if (brandEntityAux == null
                        || (brandEntityAux != null
                                && brandEntityAux.id == 0)
                    ) {
                        bInsert = true
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.brandDao()?.insert(brand)
                    } else if (brandEntityAux.id != 0) {
                        bUpdate = false
                        existInDatabase = true
                        AppDatabase.getDatabase(application.applicationContext)?.brandDao()?.update(brand)
                    }

                    if (existInDatabase) {
                        //delay(10)
                        if (bInsert) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidBrandDao()?.insert(xidBrand)
                        } else if (bUpdate) {
                            AppDatabase.getDatabase(application.applicationContext)?.xidBrandDao()?.update(xidBrand)
                        }
                    }
                }

                if (it.xidBrand.xid > maxXidCurrent && existInDatabase) {
                    maxXidCurrent = it.xidBrand.xid
                    GlobalUtil.method.saveSharedPreferences(
                        spConfiguration,
                        PARAMETER_BRAND_CURRENT_XID,
                        maxXidCurrent.toString()
                    )
                } else {
                    val xidDatabase = AppDatabase.getDatabase(application.applicationContext)?.xidBrandDao()?.maxXid() ?: 0
                    if (xidDatabase > maxXidCurrent) {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_BRAND_CURRENT_XID,
                            xidDatabase.toString()
                        )
                    } else {
                        GlobalUtil.method.saveSharedPreferences(
                            spConfiguration,
                            PARAMETER_BRAND_CURRENT_XID,
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
            PARAMETER_BRAND_CURRENT_XID
        )?.toInt()
            ?: AppDatabase.getDatabase(application.applicationContext)?.xidBrandDao()?.maxXid()
            ?: 0
    }

    fun findByIdBrandLiveData(id: Int): LiveData<BrandEntity>? {
        return mAppDatabase?.brandDao()?.findByIdLiveData(id)
    }

    fun findByIdObject(brandId: Int): Brand {
        val brandEntity = mAppDatabase?.brandDao()?.findById(brandId)
        val brand: Brand = Brand()
        brand.id = brandEntity?.id ?: 0
        brand.name = brandEntity?.name ?: ""
        return brand
    }
}