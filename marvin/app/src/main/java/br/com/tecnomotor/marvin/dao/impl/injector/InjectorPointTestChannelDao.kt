package br.com.tecnomotor.marvin.dao.impl.injector

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPointTestChannelEntity
import br.com.tecnomotor.marvin.dao.interfaces.GenericDao

@Dao
interface InjectorPointTestChannelDao : GenericDao<InjectorPointTestChannelEntity> {
    @Transaction
    @Query("SELECT * FROM cpi_canal_ponto_teste_injetor WHERE cpi_canal_ponto_teste_injetor_id = :id")
    fun findById(id: Int): InjectorPointTestChannelEntity?
}