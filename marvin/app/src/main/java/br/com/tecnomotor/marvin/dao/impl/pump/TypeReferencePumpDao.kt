package br.com.tecnomotor.marvin.dao.impl.pump

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.pump.TypeReferencePumpEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface TypeReferencePumpDao : GenericDao<TypeReferencePumpEntity> {
    @Transaction
    @Query("SELECT * FROM trb_tipo_referencia_bombas WHERE trb_id = :id")
    fun findById(id : Int) : TypeReferencePumpEntity?

    @Transaction
    @Query("SELECT * FROM trb_tipo_referencia_bombas WHERE trb_id = :id")
    fun findByIdList(id : Int) : MutableList<TypeReferencePumpEntity>?

    @Transaction
    @Query("SELECT * FROM trb_tipo_referencia_bombas")
    fun findAll() : MutableList<TypeReferencePumpEntity>?

    @Transaction
    @Query("SELECT * FROM trb_tipo_referencia_bombas WHERE trb_id = :id")
    fun findByIdLiveData(id : Int) : LiveData<TypeReferencePumpEntity>

    @Transaction
    @Query("SELECT * FROM trb_tipo_referencia_bombas")
    fun findAllLiveData(): LiveData<MutableList<TypeReferencePumpEntity>>
}