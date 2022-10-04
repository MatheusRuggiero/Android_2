package br.com.tecnomotor.marvin.repository.injector

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import br.com.tecnomotor.marvin.dao.AppDatabase
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorEntity
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorTestReportEntity
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.model.Resource

class InjectorTestReportRepository(private val application: Application) {

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

    fun save(injectorTestReportEntity: InjectorTestReportEntity){
       mAppDatabase?.injectorTestReportDao()!!.insert(injectorTestReportEntity)
    }



}