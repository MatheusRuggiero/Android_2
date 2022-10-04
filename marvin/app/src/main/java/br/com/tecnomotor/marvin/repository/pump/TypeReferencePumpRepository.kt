package br.com.tecnomotor.marvin.repository.pump

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.pump.TypeReferencePumpEntity
import br.com.tecnomotor.marvin.model.Resource

class TypeReferencePumpRepository(private val application: Application) {

    private val TAG: String = this::class.java.simpleName


    private var mAppDatabase: AppDatabase? = null
    private val mediator = MediatorLiveData<Resource<List<TypeReferencePumpEntity>?>>()

    init {
        val db: AppDatabase? = AppDatabase.getDatabase(application)
        mAppDatabase = db
    }

    fun findAllTypeReferencePump(): LiveData<Resource<List<TypeReferencePumpEntity>?>> {
        mediator.addSource(findAll()) {
            mediator.value = Resource(data = it)
            val errorLiveData = MutableLiveData<Resource<List<TypeReferencePumpEntity>?>>()
            mediator.addSource(errorLiveData) { resourceFailure ->
                val resouceActually = mediator.value
                val resourceNew: Resource<List<TypeReferencePumpEntity>?> = if (resouceActually != null)
                    Resource(data = resourceFailure.data, error = resourceFailure.error)
                else
                    resourceFailure
                mediator.value = resourceNew
            }
        }
        return mediator
    }

    fun findAll(): LiveData<MutableList<TypeReferencePumpEntity>> {
        return mAppDatabase?.typeReferencePumpDao()!!.findAllLiveData()
    }

    fun findById(id: Int): LiveData<TypeReferencePumpEntity> {
        return mAppDatabase?.typeReferencePumpDao()!!.findByIdLiveData(id)
    }


}