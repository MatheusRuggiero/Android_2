package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.XidSensorPlatformPlanEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface XidSensorPlatformPlanTestDao : GenericDao<XidSensorPlatformPlanEntity>{
    @Transaction @Query("SELECT * FROM xid_sensor_platform_plan WHERE id_xid = :id")
    fun findById(id : Int) : XidSensorPlatformPlanEntity?

    @Transaction @Query("SELECT * FROM xid_sensor_platform_plan")
    fun findAll() : MutableList<XidSensorPlatformPlanEntity>?

    @Transaction @Query("SELECT * FROM xid_sensor_platform_plan WHERE object_id = :id1")
    fun findByObjectId(id1 : String) : XidSensorPlatformPlanEntity?

    @Transaction @Query("SELECT MAX(xid) FROM xid_sensor_platform_plan")
    fun maxXid() : Int?

    @Transaction @Query("SELECT * FROM xid_sensor_platform_plan WHERE object_id = :id1")
    fun findByObjectIdList(id1 : String) : MutableList<XidSensorPlatformPlanEntity>?

    @Transaction @Query("SELECT * FROM xid_sensor_platform_plan WHERE object_id = :id1 AND object_composition_id = :id2")
    fun findByObjectIdAndCompositionId(id1 : String, id2 : String) : MutableList<XidSensorPlatformPlanEntity>?

    @Transaction @Query("SELECT * FROM xid_sensor_platform_plan WHERE object_id = :id1 AND object_composition_id = :id2 AND generic_aux_identification_1 = :id3")
    fun findByObjectIdAndCompositionIdAndAuxIdentification1(id1 : String, id2 : String, id3 : String) : MutableList<XidSensorPlatformPlanEntity>?
}