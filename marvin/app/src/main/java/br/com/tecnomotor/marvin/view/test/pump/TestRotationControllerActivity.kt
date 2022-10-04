package br.com.tecnomotor.marvin.view.test.pump

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.controller.test.RotationTestController
import kotlinx.coroutines.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
class TestRotationControllerActivity : AppCompatActivity() {

    private val rotationTestController: RotationTestController by lazy {
        val rt = RotationTestController()
        rt.rotationResultValues.observe(this) {
            updateControlValues(it)
        }
        rt
    }

    private lateinit var btnStart: Button
    private lateinit var btnFinish: Button
    private lateinit var btnExecute: Button
    private lateinit var btnCancel: Button
    private lateinit var btnPause: Button
    private lateinit var btnSkip: Button
    private lateinit var btnContinue: Button

    private lateinit var btnParamTest: Button
    private lateinit var btnExecRot: Button
    private lateinit var btnExecTest: Button
    private lateinit var btnResetParam: Button
    private lateinit var btnSendYes: Button
    private lateinit var btnSendReturn: Button

    private lateinit var btnParamTestM: Button
    private lateinit var btnCondition: Button
    private lateinit var btnFlush: Button
    private lateinit var btnExecTestM: Button
    private lateinit var btnNext: Button

    private lateinit var tvControlLog: TextView
    private lateinit var tvControlStatus: TextView
    private lateinit var tvControlError: TextView
    private lateinit var tvMeasurementLog: TextView
    private lateinit var tvMeasurementStatus: TextView
    private lateinit var tvMeasurementError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_controller)

        supportActionBar?.title = "TestRotationActivity | RotationTestController"

        btnStart = findViewById(R.id.btnStart)
        btnExecute = findViewById(R.id.btnExecute)
        btnCancel = findViewById(R.id.btnCancel)
        btnPause = findViewById(R.id.btnPause)
        btnContinue = findViewById(R.id.btnContinue)
        btnFinish = findViewById(R.id.btnFinish)
        findViewById<Button>(R.id.btnRotationTest).visibility = View.GONE

        btnParamTest = findViewById(R.id.btnParamTest)
        btnExecRot = findViewById(R.id.btnExecRot)
        btnExecTest = findViewById(R.id.btnExecTest)
        btnResetParam = findViewById(R.id.btnResetParam)
        btnSendYes = findViewById(R.id.btnControlYes)
        btnSendReturn = findViewById(R.id.btnControlReturn)

        btnParamTestM = findViewById(R.id.btnParamMeasure)
        btnCondition = findViewById(R.id.btnCondition)
        btnFlush = findViewById(R.id.btnFlush)
        btnExecTestM = findViewById(R.id.btnExecTestMeasurement)
        btnNext = findViewById(R.id.btnNext)

        tvControlLog = findViewById(R.id.tvControlLog)
        tvControlStatus = findViewById(R.id.tvControlStatus)
        tvControlError = findViewById(R.id.tvControlError)
        tvMeasurementLog = findViewById(R.id.tvMedicaoRx)
        tvMeasurementStatus = findViewById(R.id.tvMeasurementStatus)
        tvMeasurementError = findViewById(R.id.tvMeasurementError)


        btnStart.setOnClickListener {
            rotationTestController.startTest()
        }
        btnCancel.setOnClickListener {
            rotationTestController.cancelTest()
        }

        btnPause.isEnabled = false
        btnPause.isEnabled = false
        btnContinue.isEnabled = false
        btnNext.isEnabled = false

        btnResetParam.visibility = View.INVISIBLE
        btnParamTest.visibility = View.INVISIBLE
        btnExecRot.visibility = View.INVISIBLE
        btnExecTest.visibility = View.INVISIBLE
        btnSendYes.visibility = View.INVISIBLE
        btnSendReturn.visibility = View.INVISIBLE
        btnParamTestM.visibility = View.INVISIBLE
        btnCondition.visibility = View.INVISIBLE
        btnFlush.visibility = View.INVISIBLE
        btnExecTestM.visibility = View.INVISIBLE
    }

    private fun updateControlValues(rotationResult: RotationResult) {
        GlobalScope.launch(Dispatchers.Main) {
            tvControlStatus.text = rotationResult.status.getLocalizedMessage(applicationContext) // EnumTestStatus
            tvControlError.text = rotationResult.error.getLocalizedMessage(applicationContext) // ECommonRailCommandException
            tvControlLog.text = rotationResult.toString() // String
        }
    }
}