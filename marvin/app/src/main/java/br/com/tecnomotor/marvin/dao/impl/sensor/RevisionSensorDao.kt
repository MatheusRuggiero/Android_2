package br.com.tecnomotor.marvin.dao.impl.sensor

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.sensor.RevisionSensorEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface RevisionSensorDao : GenericDao<RevisionSensorEntity> {
    @Transaction
    @Query("SELECT * FROM rse_revisoes_sensores WHERE rse_rev_id = :revId")
    fun findById(revId: Int): RevisionSensorEntity?

    @Transaction
    @Query("SELECT * FROM rse_revisoes_sensores")
    fun findAll(): MutableList<RevisionSensorEntity>?

    @Transaction
    @Query("SELECT * FROM rse_revisoes_sensores WHERE rse_rev_id = :revId")
    fun findByRevision(revId: Int): MutableList<RevisionSensorEntity>?

    @Transaction
    @Query("SELECT * FROM rse_revisoes_sensores WHERE rse_sen_id = :idSensor  AND rse_rev_id = :idRev")
    fun findByRevisionSensor(idSensor: Int, idRev: Int): MutableList<RevisionSensorEntity>?
}