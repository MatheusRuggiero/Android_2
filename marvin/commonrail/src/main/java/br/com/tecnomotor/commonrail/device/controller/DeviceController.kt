package br.com.tecnomotor.commonrail.device.controller

import android.util.Log
import br.com.tecnomotor.commonrail.device.EnumDeviceDefault
import br.com.tecnomotor.commonrail.device.commands.CommandToDevice
import br.com.tecnomotor.commonrail.device.commands.Commands
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

@InternalCoroutinesApi
@DelicateCoroutinesApi
class DeviceController(
    private val deviceCommand: Commands,
    private var nameClassToLog: String = ""
): Observable() {

    private var tagLog: String = this.javaClass.simpleName + " - " + nameClassToLog
    private var commandList: ArrayList<CommandToDevice> = arrayListOf()
    private var finished: Boolean = true

    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    private lateinit var jobController: Job

    fun setNameClassToLog(value: String) {
        nameClassToLog = value
        tagLog = this.javaClass.simpleName + " - " + nameClassToLog
    }

    @Synchronized
    fun isFinished() = finished

    fun start() {
        if (isFinished())
            finished = false
        if ((!this::jobController.isInitialized) || (jobController.isCompleted))
            jobController = execJob()
    }

    fun stop() {
        finished = true
    }

    fun addCommand(command: CommandToDevice) {
        commandList.add(command)
    }

    @Synchronized
    private fun execJob() = scope.launch {
        Log.w(tagLog, "Job is started")
        while (!finished) {
            //Log.w(tagLog, "Job is running")
            if (commandList.size == 0)
                addCommand(CommandToDevice(byteArrayOf(0x19.toByte(),0x3D.toByte()),device = EnumDeviceDefault.Control))
            sendCommands()
        }
        Log.d(this.javaClass.simpleName, "job is stopped")
    }
    suspend fun sendCommand(cmd:CommandToDevice) = deviceCommand.sendCommand(cmd)

    private suspend fun sendCommands() {
        if (commandList.size > 0) {
            try {
                var commandTest: CommandToDevice = commandList[0]
                if (!commandTest.executed) {
                    commandTest = sendCommand(commandTest)
                    setChanged()
                    notifyObservers(commandTest)
                    commandList.remove(commandTest)
                }
            } catch (e: Exception) {//7F serão tratados aqui
                setChanged()
                notifyObservers(e)
            }
        }
    }
}