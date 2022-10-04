package br.com.tecnomotor.commonrail.device.commands.pump

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestKey
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@DelicateCoroutinesApi
@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
open class PumpCommands{
    val repository = CommonRailRepository.getInstance()
    suspend fun drv1ElectricalTest() {
        repository.controlCommands().sendCommand(byteArrayOf(0x26.toByte(), 0x17.toByte()))
    }

    suspend fun drv2ElectricalTest() {
        repository.controlCommands().sendCommand(byteArrayOf(0x26.toByte(), 0x18.toByte()))
    }

    suspend fun ext1ElectricalTest() {
        repository.controlCommands().sendCommand(byteArrayOf(0x26.toByte(), 0x19.toByte()))
    }

    suspend fun ext2ElectricalTest() {
        repository.controlCommands().sendCommand(byteArrayOf(0x26.toByte(), 0x1A.toByte()))
    }

    open suspend fun startTest() {
        repository.controlCommands().startTest()
    }

    suspend fun cancelTest() {
        repository.controlCommands().sendKey(EnumTestKey.RETURN)
        repository.measurementCommands().flush()
    }

    suspend fun finishTest() {
        repository.controlCommands().sendKey(EnumTestKey.YES)
        repository.measurementCommands().flush()
    }

    suspend fun pauseTest() {
        repository.controlCommands().sendKey(EnumTestKey.F4)
    }

    suspend fun canParameterize(): Boolean {
        return repository.controlCommands().canParameterize() ?: false
    }

    suspend fun startCommunication() {
        repository.controlCommands().startCommunication()
        repository.measurementCommands().startCommunication()
    }
}