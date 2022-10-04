package br.com.tecnomotor.marvin.controller

import android.content.Context
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.DeviceControleDataInfo
import br.com.tecnomotor.commonrail.device.DeviceDataInfo
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class VersionController(
    val context: Context
): Controller() {

    private var repository: CommonRailRepository = CommonRailRepository.getInstance(context)
    private var dataInfoControl: DeviceControleDataInfo? = null
    private var dataInfoMeasurement: DeviceDataInfo? = null

    var dataInfoControlLiveData = MutableLiveData<DeviceControleDataInfo>(dataInfoControl)
    var dataInfoMeasurementLiveData = MutableLiveData<DeviceDataInfo>(dataInfoMeasurement)


    override fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while (!finished) {
            if (started) {
                if (!paused) {
                    if (repository.controlIsOk() && repository.measurementIsOk()) {
                        dataInfoControl = repository.controlCommands().getVersion()
                        dataInfoControlLiveData.postValue(dataInfoControl!!)
                        dataInfoMeasurement = repository.measurementCommands().getVersion()
                        dataInfoMeasurementLiveData.postValue(dataInfoMeasurement!!)
                        finish()
                    }
                }
            }
        }
    }
}