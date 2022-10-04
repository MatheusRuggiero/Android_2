package br.com.tecnomotor.commonrail.device.commands.injector

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.tecnomotor.commonrail.device.commands.injector.response.ElectricalTestResponse
import br.com.tecnomotor.commonrail.device.commands.injector.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestKey
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@OptIn(DelicateCoroutinesApi::class)
open class InjectorCommands(
    open val repository: CommonRailRepository
) {
    suspend fun performElectricalTest(): ElectricalTestResult {

        repository.controlCommands().sendCommand(byteArrayOf(0x26.toByte(), 0x01.toByte()))
        repository.controlCommands().setParameters(
            byteArrayOf(0x03.toByte(), 0x02.toByte(), 0x01.toByte())
        )
        repository.measurementCommands().conditioning()

        //Verifica injetores
        repository.controlCommands().sendCommand(byteArrayOf(0x26.toByte(), 0x02.toByte()))
        delay(500)
        var tentative = 0
        var canParameterize =
            if (repository.controlIsOk()) repository.controlCommands().canParameterize() else false
        while (!canParameterize) {
            delay(1000)
            tentative++
            if (repository.controlIsOk() && (tentative > 10)) {
                repository.controlCommands().sendKey(EnumTestKey.RETURN)
                delay(1000)
                repository.controlCommands().startCommunication()
                repository.controlCommands().sendCommand(byteArrayOf(0x26.toByte(), 0x02.toByte()))
                delay(1000)
            }
            canParameterize = if (repository.controlIsOk()) repository.controlCommands()
                .canParameterize() else false
        }
        repository.controlCommands()
            .sendCommand(byteArrayOf(0x26.toByte(), 0x0B.toByte())) //Executa o teste
        repository.controlCommands()
            .sendCommand(byteArrayOf(0x26.toByte(), 0x0E.toByte())) //Relatorio teste eletrico

        return ElectricalTestResponse(repository.controlCommands().getTestValues()).getResult()
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
        repository.controlCommands().getVersion()
        repository.controlCommands().sendCommand(byteArrayOf(0x07.toByte()))
    }
}