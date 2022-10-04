package br.com.tecnomotor.marvin.dao.interfaces

import androidx.room.*
import br.com.tecnomotor.marvin.dao.convert.DateConverter

@Dao
@TypeConverters(DateConverter::class)
interface GenericDao<E> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj : E)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj : E)
    @Delete
    fun delete(obj: E)
}