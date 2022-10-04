package br.com.tecnomotor.commonrail.device

import br.com.tecnomotor.commonrail.device.commands.Commands
import br.com.tecnomotor.device.Device
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class CommonRailDevice(
    val name: CommonRailDeviceName,
    val commands: Commands
) : Device(commands) {

    @InternalCoroutinesApi
    public override suspend fun read(timeout: Int): ByteArray {
        return commands.read(timeout)
    }

    @InternalCoroutinesApi
    override suspend fun sendCommandS(cmd: ByteArray): ByteArray {
        return commands.sendCommand(cmd)
    }

    @InternalCoroutinesApi
    suspend fun startCommunication(): Boolean {
        return commands.startCommunication()
    }

    @InternalCoroutinesApi
    suspend fun stopCommunication(): Boolean {
        return commands.stopComunication()
    }

    @InternalCoroutinesApi
    suspend fun getVersion(): DeviceDataInfo {
        return commands.getVersion()
    }

    @InternalCoroutinesApi
    suspend fun reset(): Boolean {
        return commands.reset()
    }

    @InternalCoroutinesApi
    suspend fun testerPresent(): Boolean {
        return testerPresent()
    }

}