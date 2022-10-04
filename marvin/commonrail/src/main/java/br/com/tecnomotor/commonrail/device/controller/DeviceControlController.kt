package br.com.tecnomotor.commonrail.device.controller

import android.util.Log
import br.com.tecnomotor.commonrail.device.EnumDeviceDefault
import br.com.tecnomotor.commonrail.device.commands.CommandSendRead
import br.com.tecnomotor.commonrail.device.commands.CommandToDevice
import br.com.tecnomotor.commonrail.device.commands.Commands
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestPumpResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestSynchronizedPumpResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.SingleElectricalTestResult
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import kotlinx.coroutines.*
import java.util.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class DeviceControlController(
    deviceCommand: Commands,
) : Observable(), Observer {
    private val deviceController = DeviceController(deviceCommand, this.javaClass.simpleName)

    init {
        deviceController.addObserver(this)
    }

    fun addCommand(command: ByteArray) {
        addCommand(CommandToDevice(command, device = EnumDeviceDefault.Control))
    }

    private fun addCommand(command: CommandToDevice) {
        deviceController.addCommand(command)
    }

    fun addCommand(command: ArrayList<CommandToDevice>) {
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
            when (value) {
                is CommandToDevice -> {
                    //Log.d("${this.javaClass.simpleName} CommandToDevice", value.toString())

                    val commandSendRead = CommandSendRead(value.send, value.read)
                    setChanged()
                    notifyObservers(commandSendRead)
                    setChanged()

                    val is19response = (value.read?.get(0)) == 0x59.toByte()
                    if (is19response) {
                        val typeTest = EnumControlTypeTest.valueOf(value.read?.get(1)
                            ?: EnumControlTypeTest.NONE.value)
                        when (typeTest) {
                            EnumControlTypeTest.RPM_TEST -> {
                                val rotationValue: RotationResult =
                                    RotationResult().ofByteArray(value.read)
                                //                    Log.i("${this.javaClass.simpleName} RotationNotify", rotationValue.toString())
                                notifyObservers(rotationValue)
                            }
                            EnumControlTypeTest.PUMP -> {
                                val pointTestPumpValue: PointTestPumpResult =
                                    PointTestPumpResult().ofByteArray(value.read)
                                //                    Log.i("${this.javaClass.simpleName} PointTestNotify", pointTestPumpValue.toString())
                                notifyObservers(pointTestPumpValue)
                            }
                            EnumControlTypeTest.SYNC_PUMP -> {
                                val pointTestSyncPumpResultValue: PointTestSynchronizedPumpResult =
                                    PointTestSynchronizedPumpResult().ofByteArray(value.read)
                                notifyObservers(pointTestSyncPumpResultValue)
                            }
                            EnumControlTypeTest.VALVE_ELECTRIC_TEST -> {
                                val electricalTestResultValue: SingleElectricalTestResult =
                                    SingleElectricalTestResult().ofByteArray(value.read)
                                notifyObservers(electricalTestResultValue)
                            }
                            else -> {
                                //                    Log.d("${this.javaClass.simpleName} CommandTestNotify", value.toString())
                                notifyObservers(value)
                            }
                        }
                    } else {
                        notifyObservers(value)
                    }
                }
                is Exception -> {
                    println("notifyObservers(value) em DeviceControlController")
                    setChanged()
                    notifyObservers(value)
                }
                else -> { }
            }
        }
    }
}