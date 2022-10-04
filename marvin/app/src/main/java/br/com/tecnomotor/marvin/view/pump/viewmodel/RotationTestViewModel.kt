package br.com.tecnomotor.marvin.view.pump.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.marvin.controller.test.RotationTestController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@DelicateCoroutinesApi
@InternalCoroutinesApi
class RotationTestViewModel : ViewModel() {

    var rotationResultLiveData = MediatorLiveData<RotationResult>() //MutableLiveData<RotationResult> = MutableLiveData()
    private lateinit var rotationTestController: RotationTestController

    init {
        if (!this::rotationTestController.isInitialized) {
            rotationTestController = RotationTestController()
            rotationResultLiveData.addSource(rotationTestController.rotationResultValues, Observer { rotationResult ->
                rotationResultLiveData.postValue(rotationResult)
            })
        }
    }

    fun startTest() = rotationTestController.startTest()
    fun repeatTest() = rotationTestController.repeatTest()
    fun cancelTest() = rotationTestController.cancelTest()
    fun skipTest() = rotationTestController.skipTest()
    fun isFinished() = rotationTestController.isFinished()
}