package br.com.tecnomotor.marvin.view.unit_test.injector

import android.util.Log
import br.com.tecnomotor.commonrail.device.commands.CommandHelper
import br.com.tecnomotor.commonrail.device.commands.CommandSendRead
import br.com.tecnomotor.commonrail.device.commands.CommandToDevice
import br.com.tecnomotor.commonrail.device.commands.injector.result.PointTestInjectorResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.SingleElectricalTestResult
import br.com.tecnomotor.commonrail.device.commands.result.MeasurementResult
import br.com.tecnomotor.commonrail.device.controller.DeviceControlController
import br.com.tecnomotor.commonrail.device.controller.DeviceMeasurementController
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestKey
import br.com.tecnomotor.marvin.utils.ExtentionFunctions.toByteArray16
import br.com.tecnomotor.marvin.utils.ExtentionFunctions.toByteArray8
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
class InjUnitTestController(var repository: CommonRailRepository) : Observer {

    private var tagLog = this::class.java.simpleName

    init{//essa parte poderia ser feita por uma classe mãe
        with(repository){
            with(controlController){
                addObserver(this@InjUnitTestController)
                start()
            }
            with(measurementController){
                addObserver(this@InjUnitTestController)
                start()
            }
        }


    }
    fun parametrizaInj(param: Array<Int>) {
        val paramToSend =
            byteArrayOf(0x03.toByte())  +   //Teste bico injetor piezo
            param[0].toByteArray16()    +   //Tempo do teste de bico injetor piezo
            param[1].toByteArray16()    +   //Pressão do teste
            param[2].toByteArray16()    +   //Frequencia
            param[3].toByteArray8()     +   //Tempo chopper on
            param[4].toByteArray8()     +   //Tempo chopper off
            param[5].toByteArray16()    +   //Tempo injeção
            param[6].toByteArray8()     +   //Tempo hold off
            param[7].toByteArray8()     +   //Tensão alta
            param[8].toByteArray16()    +   //V_REF_PATAMAR
            param[9].toByteArray16()    +   //I_REF_CHARGE
            param[10].toByteArray16()   +   //I_REF_DISCHARGE
            param[11].toByteArray16()       //VAR_TEST

        with(repository.controlController) {
            addCommand(CommandHelper.CMD_SET_PARAMETER + paramToSend)
        }
    }

    fun inicia(){
        repository.controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.START.value)
    }
    fun terminar(){
        repository.controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.STOP.value)
    }
    fun pular(){//f4
        repository.controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.SKIP.value)
    }
    fun cancela(){
        repository.controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.CANCEL.value)
    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * `notifyObservers` method to have all the object's
     * observers notified of the change.
     *
     * @param   o     the observable object.
     * @param   arg   an argument passed to the `notifyObservers`
     * method.
     */
    override fun update(observable: Observable?, value: Any?) {
        when(observable) {
            is DeviceControlController -> {
                when (value) {
                    is CommandToDevice -> {
                        Log.d(tagLog, "CommandToDevice - InjUnitTestController")
                    }
                    is CommandSendRead -> {
                        Log.d(tagLog, "CommandSendRead - InjUnitTestController")
                    }
                    is PointTestInjectorResult ->{
                        Log.d(tagLog, "PointTestInjectorResult - InjUnitTestController")
                    }
                    is Exception -> {
                        Log.d(tagLog, "Exception - InjUnitTestController")
                        value.message?.let { Log.e(tagLog, it) }
                    } else -> {
                        println("Nenhum valor compatível")
                    }
                }
            }
            is DeviceMeasurementController -> {
                when(value) {
                    is MeasurementResult -> {
                        Log.d(tagLog, "MeasurementResult - InjUnitTestController")
                    }
                    is CommandToDevice -> {
                        Log.d(tagLog, "CommandToDevice - InjUnitTestController")
                    }
                    is CommandSendRead -> {
                        Log.d(tagLog, "CommandSendRead - InjUnitTestController")
                    }
                    is Exception -> {
                        value.message?.let { Log.e(tagLog, it) }
                    }
                    else -> {}
                }
            } else -> {
                println("Nenhum observável compatível")
            }
        }
    }


}