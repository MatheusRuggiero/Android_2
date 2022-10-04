package br.com.tecnomotor.marvin.view.unit_test.pump

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.tecnomotor.commonrail.device.commands.CommandSendRead
import br.com.tecnomotor.commonrail.device.commands.CommandToDevice
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestPumpResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestSynchronizedPumpResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.SingleElectricalTestResult
import br.com.tecnomotor.commonrail.device.commands.result.MeasurementResult
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@DelicateCoroutinesApi
@InternalCoroutinesApi
class PumpTestUnitViewModel : ViewModel() {
    private val deviceRepository = CommonRailRepository.getInstance()
    private var tagLog = this::class.java.simpleName
    private lateinit var pumpTestUnitController: PumpTestUnitController
    val commandToDeviceControlValues: MutableLiveData<CommandToDevice> = MutableLiveData()
    val commandToDeviceMeasurementValues: MutableLiveData<CommandToDevice> = MutableLiveData()
    val measurementResultValues: MutableLiveData<MeasurementResult> = MutableLiveData()
    val resultTestElectric: MutableLiveData<SingleElectricalTestResult> = MutableLiveData()
    val rotationResultValues: MutableLiveData<RotationResult> = MutableLiveData()
    val pointTestPumpPumpResultValues: MutableLiveData<PointTestPumpResult> = MutableLiveData()
    val pointTestSynchronizedPumpResultValues: MutableLiveData<PointTestSynchronizedPumpResult> = MutableLiveData()
    val commandControlSendRead: MutableLiveData<CommandSendRead> = MutableLiveData()
    val commandMeasurementSendRead: MutableLiveData<CommandSendRead> = MutableLiveData()

    init {
        Log.d(tagLog, "view model initializing")
    }

    /**
     * Funções de controle dos controllers
     */
    fun startControllers() {
        if (!this::pumpTestUnitController.isInitialized) {
            Log.d(tagLog, "inicializando contoller")
            pumpTestUnitController = PumpTestUnitController(deviceRepository)
            trataValoresControlController()
            trataValoresMeasurementController()
        }
    }

    private fun trataValoresControlController() {
        pumpTestUnitController.commandToDeviceControlValues.observeForever {
            commandToDeviceControlValues.postValue(it)
        }
        pumpTestUnitController.resultTestElectric.observeForever {
            resultTestElectric.postValue(it)
        }
        pumpTestUnitController.rotationResultValues.observeForever {
            rotationResultValues.postValue(it)
        }
        pumpTestUnitController.pointTestPumpPumpResultValues.observeForever {
            pointTestPumpPumpResultValues.postValue(it)
        }
        pumpTestUnitController.pointTestSynchronizedPumpResultValues.observeForever {
            pointTestSynchronizedPumpResultValues.postValue(it)
        }
        pumpTestUnitController.commandControlSendRead.observeForever {
            commandControlSendRead.postValue(it)
        }
    }

    private fun trataValoresMeasurementController() {
        pumpTestUnitController.commandToDeviceMeasurementValues.observeForever {
            commandToDeviceMeasurementValues.postValue(it)
        }
        pumpTestUnitController.measurementResultValues.observeForever {
            measurementResultValues.postValue(it)
        }
        pumpTestUnitController.commandMeasurementSendRead.observeForever {
            commandMeasurementSendRead.postValue(it)
        }
    }

    fun stopControllers() = pumpTestUnitController.stopCommunication()
    fun resetControlParam() = pumpTestUnitController.resetControlParam()
    fun resetMeasureParam() = pumpTestUnitController.resetMeasureParam()

    /**
     * #####################################################
     * ##### FUNÇÕES TESTES UNITÁRIOS - PLACA CONTROLE #####
     * #####################################################
     */

    /**
     * Testes Analógicos e Digital
     */
    fun onBuzzer() = pumpTestUnitController.onBuzzer()
    fun offBuzzer() = pumpTestUnitController.offBuzzer()
    fun onLed01() = pumpTestUnitController.onLed01()
    fun offLed01() = pumpTestUnitController.offLed01()
    fun onLed02() = pumpTestUnitController.onLed02()
    fun offLed02() = pumpTestUnitController.offLed02()
    fun onLed03() = pumpTestUnitController.onLed03()
    fun offLed03() = pumpTestUnitController.offLed03()
    fun onLed04() = pumpTestUnitController.onLed04()
    fun offLed04() = pumpTestUnitController.offLed04()
    fun onLed05() = pumpTestUnitController.onLed05()
    fun offLed05() = pumpTestUnitController.offLed05()
    fun onLed06() = pumpTestUnitController.onLed06()
    fun offLed06() = pumpTestUnitController.offLed06()
    fun startAnalogTests() = pumpTestUnitController.startAnalogTests()
    fun paramAnalogTest(channel: String, pwm: String, duty: String) = pumpTestUnitController.paramAnalogTests(channel.toInt(), pwm.toInt(), duty.toInt())

    /**
     * Teste elétrico
     */
    fun drv1() = pumpTestUnitController.drv1()
    fun drv2() = pumpTestUnitController.drv2()
    fun ext1() = pumpTestUnitController.ext1()
    fun ext2() = pumpTestUnitController.ext2()

    /**
     * Verificação Rotação
     */
    fun sendCheckRotationParam(desiredRotation: String, timeout: String) = pumpTestUnitController.checkRotationParam(desiredRotation, timeout)
    fun sendCheckRotationStart() = pumpTestUnitController.sendKeyCheckStart()

    fun sendKeyYes() = pumpTestUnitController.sendKeyFinished()
    fun sendKeyNo() = pumpTestUnitController.sendKeyPaused()
    fun sendKeyRet() = pumpTestUnitController.sendKeyRet()
    fun sendKeyError() = pumpTestUnitController.sendKeyError()
    fun sendKeyF4() = pumpTestUnitController.sendKeyF4()

    /**
     * Point Test Pump
     */
    fun sendPointTestPumpParam(listOpPointTestPump: ArrayList<String>) = pumpTestUnitController.paramPumpTest(listOpPointTestPump)
    fun sendPointTestPumpStart() = pumpTestUnitController.sendKeyPointTestStart()
    fun sendPointTestPumpFinished() = pumpTestUnitController.sendKeyFinished()
    fun sendPointTestPumpPaused() = pumpTestUnitController.sendKeyPaused()
    fun sendPointTestPumpRet() = pumpTestUnitController.sendKeyRet()
    fun sendPointTestPumpSkip() = pumpTestUnitController.sendKeyF4()

    /**
     * Synchronized Pump Test
     */
    fun sendSynchronizedTestPumpParam(listTestPumpSynchronized: ArrayList<String>) = pumpTestUnitController.btnTestSynchronizedPumpParam(listTestPumpSynchronized)
    fun sendSynchronizedTestPumpStart() = pumpTestUnitController.sendKeyStart()
    fun sendSynchronizedTestPumpStop() = pumpTestUnitController.sendKeyStop()
    fun sendSynchronizedTestPumpSkip() = pumpTestUnitController.sendKeySkip()
    fun sendSynchronizedTestPumpCancel() = pumpTestUnitController.sendKeyCancel()

    /**
     * ####################################################
     * ##### FUNÇÕES TESTES UNITÁRIOS - PLACA MEDIÇÃO #####
     * ####################################################
     */

    /**
     * Parametrização Medição
     */
    fun paramMed(dispositivo: Int, cmCH1: String, cmCH2: String, freq: String, timeout: String, canal:Int, flagPrincipal:Int, flagRetorno:Int){
        pumpTestUnitController.paramPumpTestMeasurement(
            dispositivo, cmCH1.toInt(), cmCH2.toInt(), freq.toInt(), timeout.toInt(), canal, flagPrincipal, flagRetorno)
    }

    fun sendKeyParamMedYes() = pumpTestUnitController.sendKeyParamMedSim()
    fun sendKeyParamMedNo() = pumpTestUnitController.sendKeyParamMedNao()
    fun sendKeyParamMedRet() = pumpTestUnitController.sendKeyParamMedRet()
    fun sendKeyParamMedError() = pumpTestUnitController.sendKeyParamMedError()
    fun sendKeyParamMedFlush() = pumpTestUnitController.sendKeyParamMedFlush()
    fun sendKeyParamMedCondic() = pumpTestUnitController.sendKeyParamMedCondic()
    fun sendKeyParamMedEstanq() = pumpTestUnitController.sendKeyParamMedEstanq()
    fun sendKeyParamMedInjector() = pumpTestUnitController.sendKeyParamMedInjector()
    fun sendKeyParamMedValve() = pumpTestUnitController.sendKeyParamMedValve()
    fun sendKeyParamMedPump() = pumpTestUnitController.sendKeyParamMedPump()

    /**
     * Testes Analógicos e Digital
     */
    fun startAnalogTestMeasurement() = pumpTestUnitController.startAnalogTestsMed()
    fun sendOnBuzzerMeasurement() = pumpTestUnitController.onBuzzerMeasurement()
    fun sendOffBuzzerMeasurement() = pumpTestUnitController.offBuzzerMeasurement()
    fun sendOnValvFlushRet() = pumpTestUnitController.onValvFlushRet()
    fun sendOffValvFlushRet() = pumpTestUnitController.offValvFlushRet()
    fun sendOnValvFlushInj() = pumpTestUnitController.onValvFlushInj()
    fun sendOffValvFlushInj() = pumpTestUnitController.offValvFlushInj()
    fun sendOnValvCond() = pumpTestUnitController.onValvCond()
    fun sendOffValvCond() = pumpTestUnitController.offValvCond()
    fun sendOnComut01() = pumpTestUnitController.onComut01()
    fun sendOffComut01() = pumpTestUnitController.offComut01()
    fun sendOnComut02() = pumpTestUnitController.onComut02()
    fun sendOffComut02() = pumpTestUnitController.offComut02()
    fun sendOnComut03() = pumpTestUnitController.onComut03()
    fun sendOffComut03() = pumpTestUnitController.offComut03()
    fun sendOnDreno01() = pumpTestUnitController.onDreno01()
    fun sendOffDreno01() = pumpTestUnitController.offDreno01()
    fun sendOnDreno02() = pumpTestUnitController.onDreno02()
    fun sendOffDreno02() = pumpTestUnitController.offDreno02()
    fun sendOnExtra01() = pumpTestUnitController.onExtra01()
    fun sendOffExtra01() = pumpTestUnitController.offExtra01()
    fun sendOnExtra02() = pumpTestUnitController.onExtra02()
    fun sendOffExtra02() = pumpTestUnitController.offExtra02()
}

