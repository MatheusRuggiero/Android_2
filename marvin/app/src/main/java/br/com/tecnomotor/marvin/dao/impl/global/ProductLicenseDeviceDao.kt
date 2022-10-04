package br.com.tecnomotor.marvin.dao.impl.global

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.api.model.ProductLicenseDevice
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface ProductLicenseDeviceDao : GenericDao<ProductLicenseDevice>{
    @Transaction @Query("SELECT * FROM product_license_device WHERE product_license_id = :id")
    fun findById(id : Int) : ProductLicenseDevice?

    @Transaction @Query("SELECT * FROM product_license_device")
    fun findAll() : List<ProductLicenseDevice>?
}