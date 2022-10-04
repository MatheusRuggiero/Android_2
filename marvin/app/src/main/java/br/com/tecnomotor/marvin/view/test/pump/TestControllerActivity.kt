package br.com.tecnomotor.marvin.view.test.pump

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import br.com.tecnomotor.commonrail.device.commands.CommandHelper
import br.com.tecnomotor.commonrail.device.commands.CommandToDevice
import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestPumpResult
import br.com.tecnomotor.commonrail.device.commands.result.MeasurementResult
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.commonrail.device.controller.DeviceControlController
import br.com.tecnomotor.commonrail.device.controller.DeviceMeasurementController
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestKey
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestProcessForControl
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumDefinesTestTypeMeasurement
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumDefinesTestTypeMeasurement.*
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.*
import java.util.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class TestControllerActivity : AppCompatActivity(), Observer {

    var deviceRepository = CommonRailRepository.getInstance()
    lateinit var controlController: DeviceControlController
    lateinit var measurementController: DeviceMeasurementController

    lateinit var btnStart: Button
    lateinit var btnFinish: Button

    lateinit var btnParamTest: Button
    lateinit var btnRotationTest: Button
    lateinit var btnExecRot: Button
    lateinit var btnExecTest: Button
    lateinit var btnResetParam: Button
    lateinit var btnSendYes: Button
    lateinit var btnSendReturn: Button

    lateinit var btnParamTestM: Button
    lateinit var btnCondition: Button
    lateinit var btnFlush: Button
    lateinit var btnExecTestM: Button

    lateinit var tvControlLog: TextView
    lateinit var tvControlStatus: TextView
    lateinit var tvControlError: TextView
    lateinit var tvMeasurementLog: TextView
    lateinit var tvMeasurementStatus: TextView
    lateinit var tvMeasurementError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_controller)
        supportActionBar?.title = "TestControllerActitivy"



        btnStart = findViewById(R.id.btnStart)
        btnFinish = findViewById(R.id.btnFinish)
        findViewById<Button>(R.id.btnNext).visibility = View.INVISIBLE

        btnParamTest = findViewById(R.id.btnParamTest)
        btnRotationTest = findViewById(R.id.btnRotationTest)
        btnExecRot = findViewById(R.id.btnExecRot)
        btnExecTest = findViewById(R.id.btnExecTest)
        btnResetParam = findViewById(R.id.btnResetParam)
        btnSendYes = findViewById(R.id.btnControlYes)
        btnSendReturn = findViewById(R.id.btnControlReturn)

        btnParamTestM = findViewById(R.id.btnParamMeasure)
        btnCondition = findViewById(R.id.btnCondition)
        btnFlush = findViewById(R.id.btnFlush)
        btnExecTestM = findViewById(R.id.btnExecTestMeasurement)

        tvControlLog = findViewById(R.id.tvControlLog)
        tvControlStatus = findViewById(R.id.tvControlStatus)
        tvControlError = findViewById(R.id.tvControlError)
        tvMeasurementLog = findViewById(R.id.tvMedicaoRx)
        tvMeasurementStatus = findViewById(R.id.tvMeasurementStatus)
        tvMeasurementError = findViewById(R.id.tvMeasurementError)

        controlController = deviceRepository.controlController
        controlController.addObserver(this)

        measurementController = deviceRepository.measurementController
        measurementController.addObserver(this)

        btnStart.setOnClickListener {

            controlController.start()


            measurementController.start()
        }
        btnFinish.setOnClickListener {
            controlController.stop()
            measurementController.stop()
        }
        btnResetParam.setOnClickListener {
            controlController.addCommand(CommandHelper.CMD_PARAMETER_RESET)
        }
        btnRotationTest.setOnClickListener {
            // comando alterado, utilizar o CheckTestRotation
            //controlController.addCommand(CommandHelper.CMD_SET_TEST + EnumTestProcessForControl.CheckStandardRotation.value)
        }
        btnParamTest.setOnClickListener {
            controlController.addCommand(CommandHelper.CMD_PARAMETER_RESET)
            //Warm up
//            val parameters = byteArrayOf(0x25.toByte(),
//                0x06.toByte(),0x01.toByte(),0x00.toByte(),0x28.toByte(),0x00.toByte(),0x00.toByte(),0x00.toByte(),0xB4.toByte(),
//                0x00.toByte(),0x00.toByte(),0x00.toByte(),0xFA.toByte(),0x07.toByte(),0xD0.toByte(),0x00.toByte(),0x1E.toByte())
            //Ken
            val parameters = CommandHelper.CMD_SET_PARAMETER + byteArrayOf(
                0x06.toByte(), 0x01.toByte(), 0x00.toByte(), 0x28.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0xB4.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x01.toByte(), 0xF4.toByte(), 0x0D.toByte(), 0xAC.toByte(), 0x01.toByte(), 0x2C.toByte())
            controlController.addCommand(parameters)
        }
        btnExecRot.setOnClickListener {
            controlController.addCommand(CommandHelper.CMD_SET_TEST + byteArrayOf(EnumControlTypeTest.RPM_TEST.value))
        }
        btnExecTest.setOnClickListener {
            controlController.addCommand(CommandHelper.CMD_SET_TEST + byteArrayOf(EnumTestProcessForControl.TestPumps.value))
        }
        btnSendYes.setOnClickListener {
            controlController.addCommand(CommandHelper.CMD_SEND_KEY + byteArrayOf(EnumTestKey.YES.value))
        }
        btnSendReturn.setOnClickListener {
            controlController.addCommand(CommandHelper.CMD_SEND_KEY + byteArrayOf(EnumTestKey.RETURN.value))
        }

        btnParamTestM.setOnClickListener {
            //Ken Vazão principal
            val parameters = CommandHelper.CMD_SET_PARAMETER + REMOTE_PUMP_PROCESS.getLocalizedCommandByte() +
                    0x00.toByte() + 0x00.toByte() + 0x00.toByte() + 0x00.toByte() + 0x00.toByte() +
                    0x00.toByte() + 0x00.toByte() + 0x14.toByte() + 0x03.toByte() + 0x01.toByte() + 0x00.toByte()
            measurementController.addCommand(parameters)
        }
        btnCondition.setOnClickListener {
            //Ken Vazão principal
            val parameters = CommandHelper.CMD_SET_PARAMETER + REMOTE_CONDITIONING_PROCESS.getLocalizedCommandByte() +
                0x00.toByte() + 0x00.toByte() + 0x00.toByte() + 0x00.toByte() + 0x00.toByte() +
                0x00.toByte() + 0x00.toByte() + 0x14.toByte() + 0x03.toByte() + 0x01.toByte() + 0x00.toByte()
            measurementController.addCommand(parameters)
            measurementController.addCommand(CommandHelper.CMD_MEASURE_SET_CONDITIONING_PROCESS)
        }
        btnFlush.setOnClickListener {
            //Ken Vazão principal
            val parameters = CommandHelper.CMD_SET_PARAMETER + REMOTE_FLUSH_PROCESS.getLocalizedCommandByte() +
                0x00.toByte() + 0x00.toByte() + 0x00.toByte() + 0x00.toByte() + 0x00.toByte() +
                0x00.toByte() + 0x00.toByte() + 0x14.toByte() + 0x03.toByte() + 0x01.toByte() + 0x00.toByte()
            measurementController.addCommand(parameters)
            measurementController.addCommand(CommandHelper.CMD_MEASURE_SET_FLUSH_PROCESS)
        }
        btnExecTestM.setOnClickListener {
            measurementController.addCommand(CommandHelper.CMD_MEASURE_SET_PUMP_PROGRESS)
        }
    }

    override fun onBackPressed() {
        controlController.stop()
        measurementController.stop()
        super.onBackPressed()
    }

    private fun updateControlValues(status: EnumTestStatus,
                                    error: ECommonRailCommandException, text: String) {
        GlobalScope.launch(Dispatchers.Main) {
            tvControlStatus.text = status.getLocalizedMessage(applicationContext)
            tvControlError.text = error.getLocalizedMessage(applicationContext)
            tvControlLog.text = text
        }
    }

    private fun updateMeasurementValues(status: EnumTestStatus,
                                        error: ECommonRailCommandException, text: String) {
        GlobalScope.launch(Dispatchers.Main) {
            tvMeasurementStatus.text = status.getLocalizedMessage(applicationContext)
            tvMeasurementError.text = error.getLocalizedMessage(applicationContext)
            tvMeasurementLog.text = text
        }
    }

    override fun update(observable: Observable?, value: Any?) {
        if(value is Exception)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                value.message?.let { Log.e(attributionTag, it) }
            }

        Log.d(this.javaClass.simpleName, "Observable: ${observable?.javaClass?.simpleName}")
        Log.d(this.javaClass.simpleName, "Value: ${value?.javaClass?.simpleName}")
        if (observable is DeviceControlController) {
            if (value is RotationResult)  updateControlValues(value.status, value.error, value.toString())
            if (value is PointTestPumpResult) updateControlValues(value.status, value.error, value.toString())
            if (value is CommandToDevice) updateControlValues(EnumTestStatus.NONE, ECommonRailCommandException(0), value.toString())
        }
        if (observable is DeviceMeasurementController) {
            if (value is MeasurementResult) updateMeasurementValues(value.statusCH3, value.errorCH3, value.toString()) // aqui precisa pegar os erros dos três canais e ver se algum está vazio para mostrar na tela ...
            if (value is CommandToDevice) updateControlValues(EnumTestStatus.NONE, ECommonRailCommandException(0), value.toString())
        }
    }
}