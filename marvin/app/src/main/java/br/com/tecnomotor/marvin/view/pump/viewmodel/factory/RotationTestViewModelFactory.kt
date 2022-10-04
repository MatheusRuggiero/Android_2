package br.com.tecnomotor.marvin.view.pump.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.marvin.view.pump.viewmodel.RotationTestViewModel
import kotlinx.coroutines.*
import kotlin.reflect.KSuspendFunction0

@DelicateCoroutinesApi
@InternalCoroutinesApi
class RotationTestViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RotationTestViewModel() as T
    }
}