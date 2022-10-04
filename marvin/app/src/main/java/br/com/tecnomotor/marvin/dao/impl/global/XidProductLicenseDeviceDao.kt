package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.api.model.XidProductLicenseDevice
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidProductLicenseDeviceDao : GenericDao<XidProductLicenseDevice>{
    @Transaction @Query("SELECT * FROM xid_product_license_device WHERE id_xid = :id")
    fun findById(id : Int) : XidProductLicenseDevice?

    @Transaction @Query("SELECT * FROM xid_product_license_device")
    fun findAll() : List<XidProductLicenseDevice>?

    @Transaction @Query("SELECT * FROM xid_product_license_device WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidProductLicenseDevice?

    @Transaction @Query("SELECT MAX(xid) FROM xid_product_license_device")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_product_license_device WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : List<XidProductLicenseDevice>?

    @Transaction @Query("SELECT * FROM xid_product_license_device WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : List<XidProductLicenseDevice>?
}