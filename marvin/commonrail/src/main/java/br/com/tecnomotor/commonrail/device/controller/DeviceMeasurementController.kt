package br.com.tecnomotor.commonrail.device.controller

import android.util.Log
import br.com.tecnomotor.commonrail.device.EnumDeviceDefault
import br.com.tecnomotor.commonrail.device.commands.CommandHelper
import br.com.tecnomotor.commonrail.device.commands.CommandSendRead
import br.com.tecnomotor.commonrail.device.commands.CommandToDevice
import br.com.tecnomotor.commonrail.device.commands.Commands
import br.com.tecnomotor.commonrail.device.commands.result.MeasurementResult
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumDefinesTestTypeMeasurement.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class DeviceMeasurementController(
    deviceCommand: Commands,
) : Observable(), Observer {
    private val deviceController = DeviceController(deviceCommand, this.javaClass.simpleName)
    private var commandToDevice: CommandToDevice = CommandToDevice(byteArrayOf(), device = EnumDeviceDefault.Measurement)
    private var measurementResult: MeasurementResult = MeasurementResult()

    init {
        deviceController.addObserver(this)
    }

    fun addCommand(command: ByteArray) {
        addCommand(CommandToDevice(command,device = EnumDeviceDefault.Measurement))
    }

    private fun addCommand(command: CommandToDevice) {
        deviceController.addCommand(command)
    }

    private fun addCommand(command: ArrayList<CommandToDevice>) {
        command.forEach { addCommand(it) }
    }

    fun isFinished() = deviceController.isFinished()

    fun start() {
        deviceController.start()
    }

    fun stop() {
        deviceController.stop()
    }

    @Synchronized
    override fun update(observable: Observable?, value: Any?) {
        if (observable is DeviceController) {
                when(value) {
                    is CommandToDevice -> {
//            Log.d("${this.javaClass.simpleName} CommandToDevice", value.toString())
            val measurementValue = MeasurementResult().ofByteArray(value.read)
            val commandSendRead = CommandSendRead(value.send, value.read)
            setChanged()
            notifyObservers(commandSendRead)
            setChanged()
            if (measurementValue != measurementResult) { // por que '!=' ?
//                Log.i("${this.javaClass.simpleName} MeasurementNotify", measurementValue.toString())
                measurementResult = measurementValue
                notifyObservers(measurementResult)
            } else if (value.notEquals(commandToDevice)) {
//                Log.d("${this.javaClass.simpleName} CommandTestNotify", value.toString())
                commandToDevice = value
                notifyObservers(commandToDevice)
            }
        }
                    is Exception -> {
                        setChanged()
                        notifyObservers(value)
                    }
                }
        }
    }
}