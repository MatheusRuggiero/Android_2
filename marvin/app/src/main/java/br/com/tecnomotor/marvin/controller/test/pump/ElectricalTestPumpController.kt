package br.com.tecnomotor.marvin.controller.test.pump

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.tecnomotor.commonrail.device.commands.CommandHelper
import br.com.tecnomotor.commonrail.device.commands.CommandToDevice
import br.com.tecnomotor.commonrail.device.commands.pump.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.commands.pump.result.SingleElectricalTestResult
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.commonrail.device.controller.DeviceControlController
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestProcessForControl
import br.com.tecnomotor.marvin.controller.test.IControllerTest
import br.com.tecnomotor.marvin.model.pump.TypeReferencePump
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class ElectricalTestPumpController(
    val typeReferencePump: TypeReferencePump
) : IControllerTest, Observer {
    private var tagLog = this::class.java.simpleName
    private var repository = CommonRailRepository.getInstance()
    private var controlController: DeviceControlController
    private var electricalTestResult = ElectricalTestResult(EnumTestStatus.NONE)
    val electricalTestResultLiveData = MutableLiveData(electricalTestResult)
    val electricalResultLiveData = MutableLiveData<SingleElectricalTestResult>()

    private enum class EnumTypeReferencePump { tpDrv1, tpDrv2, tpExt1, tpExt2, tpInj1, tpInj2 }
    private var listTests = arrayListOf<EnumTypeReferencePump>()
    private var indexTest = 0

    private fun resetControlParam() = controlController.addCommand(CommandHelper.CMD_PARAMETER_RESET)
    private fun drv1ElectricalTest() = controlController.addCommand(byteArrayOf(0x26.toByte(), 0x17.toByte()))
    private fun drv2ElectricalTest() = controlController.addCommand(byteArrayOf(0x26.toByte(), 0x18.toByte()))
    private fun ext1ElectricalTest() = controlController.addCommand(byteArrayOf(0x26.toByte(), 0x19.toByte()))
    private fun ext2ElectricalTest() = controlController.addCommand(byteArrayOf(0x26.toByte(), 0x1A.toByte()))

    init {
        repository.controlDevice.let {
            controlController = repository.controlController
        }
    }

    private fun loadListTests() {
        listTests.clear()
        if (typeReferencePump.drv1) listTests.add(EnumTypeReferencePump.tpDrv1)
        if (typeReferencePump.drv2) listTests.add(EnumTypeReferencePump.tpDrv2)
        if (typeReferencePump.ext1) listTests.add(EnumTypeReferencePump.tpExt1)
        if (typeReferencePump.ext2) listTests.add(EnumTypeReferencePump.tpExt2)
        if (typeReferencePump.inj1) listTests.add(EnumTypeReferencePump.tpInj1)
        if (typeReferencePump.inj2) listTests.add(EnumTypeReferencePump.tpInj2)
    }

    private fun indexIsValid() = (indexTest < listTests.size)
    private fun haveANewTest() = ((indexTest+1) < listTests.size)

    @Synchronized
    private fun nextTest() {
        indexTest++
        executeElectricalTest()
    }

    @Synchronized
    private fun executeElectricalTest() {
        if (indexIsValid()) {
            when (listTests[indexTest]) {
                EnumTypeReferencePump.tpDrv1 -> {
                    drv1ElectricalTest()
                }
                EnumTypeReferencePump.tpDrv2 -> {
                    drv2ElectricalTest()
                }
                EnumTypeReferencePump.tpExt1 -> {
                    ext1ElectricalTest()
                }
                EnumTypeReferencePump.tpExt2 -> {
                    ext2ElectricalTest()
                }
                EnumTypeReferencePump.tpInj1 -> {}
                EnumTypeReferencePump.tpInj2 -> {}
            }
        }
    }

    /**
     * Finaliza a corrotina de comunicação com a placa
     */
    private fun stopJobController() {
        controlController.stop()
        controlController.deleteObserver(this@ElectricalTestPumpController)
    }

    override fun startTest() {
        electricalTestResult.clear()
        indexTest = 0
        loadListTests()
        electricalTestResult.status = EnumTestStatus.TEST_STARTING
        electricalTestResultLiveData.postValue(electricalTestResult)

        controlController.deleteObserver(this@ElectricalTestPumpController)
        if (controlController.isFinished())
            controlController.start()
        resetControlParam()
        controlController.addObserver(this@ElectricalTestPumpController)
    }

    override fun pauseTest() {
        this.cancelTest()
    }

    override fun skipTest() {
        this.cancelTest()
    }

    override fun cancelTest() {
        stopJobController()
    }

    override fun isFinished() = controlController.isFinished()

    @Synchronized
    override fun update(observable: Observable?, value: Any?) {
        when (observable) {
            is DeviceControlController -> {
                when (value) {
                    is SingleElectricalTestResult -> {
                        electricalTestResult.deepCopy(value)
                        when (electricalTestResult.status) {
                            EnumTestStatus.TEST_FINISHED -> {
                                electricalTestResult.testResult.add(value)
                                Log.w(tagLog, electricalTestResult.testResult[electricalTestResult.testResult.size-1].toString())
                                electricalTestResultLiveData.postValue(electricalTestResult)
                                electricalResultLiveData.postValue(electricalTestResult.testResult[electricalTestResult.testResult.size-1])
                                if (!haveANewTest()) {
                                    electricalTestResult.status =
                                        EnumTestStatus.TEST_ALL_TESTS_FINISHED
                                    electricalTestResultLiveData.postValue(electricalTestResult)
                                    stopJobController()
                                } else {
                                    nextTest()
                                }
                            }
                            else -> {
                                electricalTestResultLiveData.postValue(electricalTestResult)
                            }
                        }
                    }
                    is CommandToDevice -> {
                        if (CommandHelper.commandIsValid(value.read)) {
                            if (electricalTestResult.status == EnumTestStatus.TEST_STARTING) {
                                electricalTestResult.status = EnumTestStatus.TEST_WAITING
                                executeElectricalTest()
                            }
                        }
                    }
                    is Exception -> {//Printa no log se recebeu alguma mensagem
                        value.message?.let { Log.e(tagLog, it) }
                    }
                }
            }
            else -> {}
        }
    }
}