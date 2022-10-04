package br.com.tecnomotor.marvin.controller.test

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.CommandHelper
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.commonrail.device.controller.DeviceControlController
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestKey
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestProcessForControl
import br.com.tecnomotor.marvin.config.RotationTestConfig
import br.com.tecnomotor.marvin.utils.ExtentionFunctions.toByteArray16
import kotlinx.coroutines.*
import java.util.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
class RotationTestController : IControllerTest, Observer {

    private var tagLog = this::class.java.simpleName
    private var repository = CommonRailRepository.getInstance()
    private var rotationResult = RotationResult()
    private var controlController: DeviceControlController
    private var stopJobs = false
    val rotationResultValues: MutableLiveData<RotationResult> = MutableLiveData(rotationResult)

    init {
        Log.w(tagLog, "Init class")
        controlController = repository.controlController
    }

    /**
     * Inicializa o teste de rotação
     */
    override fun startTest() {
        stopJobs = false
        rotationResult.status = EnumTestStatus.TEST_STARTING
        rotationResult.rotation = 0
        rotationResult.temperature = 0
        rotationResultValues.postValue(rotationResult)

        controlController.deleteObserver(this@RotationTestController)
        if (controlController.isFinished())
            controlController.start()
        resetControlParam()
        controlController.addObserver(this@RotationTestController)

    }

    /**
     * Repete teste de rotação após erro
     */
    fun repeatTest() {
        Log.w(tagLog, "repeatTest: ResetControlParam")
        resetControlParam()
        GlobalScope.launch {
            delay(100) // não gostei disso, é para o status TEST_FAIL não incluenciar no teste novo
            Log.w(tagLog, "repeatTest: StartTest")
            startTest()
        }

    }

    override fun pauseTest() {
        this.cancelTest()
    }

    private fun parametrizaRotacao() {
        val config = RotationTestConfig.getInstance()
        val desiredRotation = config.getDesiredRotation()
        val timeout = config.getTimeoutRotation()
        val rotacaoByteArray: ByteArray = desiredRotation.toByteArray16()
        val timeoutByteArray: ByteArray = timeout.toByteArray16()
        val param = byteArrayOf(
            0x06.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),
            0x00.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte()) + rotacaoByteArray + timeoutByteArray
        controlController.addCommand(CommandHelper.CMD_SET_PARAMETER + param)
    }

    /**
     * Envia a tecla retorna para finalizar o teste
     */
    private fun stopTest() {
        when (rotationResult.status) {
            EnumTestStatus.TEST_STARTING -> stopJobController()
            EnumTestStatus.TEST_STOPPED, EnumTestStatus.TEST_FAIL -> {
                resetControlParam()
                GlobalScope.launch {
                    // sem o delay pára o controller sem
                    delay(100)
                    finishController()
                }
            }
            else -> sendKeyReturn()
        }
    }

    /**
     * Cancela o teste de rotação - igual ao finishTest
     */
    override fun cancelTest() {
        stopTest()
    }

    /**
     * Pula teste
     */
    override fun skipTest() {
        if(rotationResult.status in arrayOf(EnumTestStatus.TEST_FAIL, EnumTestStatus.TEST_STOPPED)) {
            resetControlParam()
            GlobalScope.launch {
                // sem o delay pára o controller sem
                delay(100)
                finishController()
                // forçar a tela passar para frente quando está com falha
                rotationResult.status = EnumTestStatus.TEST_SKIPPED
                rotationResultValues.postValue(rotationResult)
            }
        } else {
            sendKeyF4()
        }
    }

    /**
     * Finaliza a corrotina de comunicação com as placas
     */
    private fun stopJobController() {
        controlController.stop()
        controlController.deleteObserver(this@RotationTestController)
    }

    /**
     * Finaliza o controlador após envio de comandos sem tratar qualquer resposta
     */
    private fun finishController() { stopJobs = true }

    private fun sendKeyYes() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.YES.value)
    private fun sendKeyNo() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.NO.value)
    private fun sendKeyF4() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.F4.value)
    private fun sendKeyReturn() = controlController.addCommand(CommandHelper.CMD_SEND_KEY + EnumTestKey.RETURN.value)
    private fun resetControlParam() = controlController.addCommand(CommandHelper.CMD_PARAMETER_RESET)

    override fun isFinished() = controlController.isFinished()

    @Synchronized
    override fun update(observable: Observable?, value: Any?) {
        when (observable) {
            is DeviceControlController -> {
                if (stopJobs) {
                    stopJobController()
                    return
                }
                /**
                 * Executa o teste caso o status seja "TEST_STARTING"
                 * e a placa de controle esteja resetada
                 */
                when (rotationResult.status) {
                    EnumTestStatus.TEST_STARTING -> {
                        rotationResult.status = EnumTestStatus.TEST_PARAMETERIZING
                        parametrizaRotacao()
                        controlController.addCommand(CommandHelper.CMD_SET_TEST +
                                EnumTestProcessForControl.CheckTestRotation.value)
                        return
                    }
                    else -> {}
                }

                Log.d(tagLog, value.toString())
                when (value) {
                    is RotationResult -> {
                        rotationResult.deepCopy(value)
//                        Log.w(tagLog, rotationResult.toString())
                        when (value.status) {
                            EnumTestStatus.TEST_WAIT_KEY -> { // aguardando tecla sim para iniciar
                                sendKeyYes()
                            }
                            EnumTestStatus.TEST_STOPPED, EnumTestStatus.TEST_FAIL -> { // timeout
//                                resetControlParam()
//                                finishController()
                            }
                            EnumTestStatus.TEST_SKIPPED, // pulado
                            EnumTestStatus.TEST_CANCELLED, // cancelado
                            EnumTestStatus.TEST_FINISHED, // finalizado com sucesso
                            -> {
                                resetControlParam()
                                finishController()
                            }
                            else -> {}
                        }
                        rotationResultValues.postValue(rotationResult)
                    }
                }
            }
            else -> {}
        }
    }
}