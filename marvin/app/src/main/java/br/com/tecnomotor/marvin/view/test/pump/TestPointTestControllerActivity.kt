package br.com.tecnomotor.marvin.view.test.pump

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.controller.test.pump.PointTestPumpController
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.view.test.pump.data.*
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
@SuppressLint("SetTextI18n")
class TestPointTestControllerActivity : AppCompatActivity() {

    private var indexPointTestController = 0
    private lateinit var pointTestPumpList: ListaDePontosDeTeste

    private val pointTestController: PointTestPumpController by lazy {
        PointTestPumpController(pointTestPumpList[indexPointTestController], Pump()) // TODO: corrigir esse Pump vazio, criar com valores específicos ...
    }

    private lateinit var btnStart: Button
    private lateinit var btnContinuarTeste: Button
    private lateinit var btnCancel: Button
    private lateinit var btnPause: Button
    private lateinit var btnSkip: Button
    private lateinit var btnSkipRotation: Button
    private lateinit var btnNext: Button

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

    private lateinit var tvControlLog: TextView
    private lateinit var tvControlStatus: TextView
    private lateinit var tvControlError: TextView
    private lateinit var tvMeasurementLog: TextView
    private lateinit var tvMeasurementStatus: TextView
    private lateinit var tvMeasurementError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_controller)
        supportActionBar?.title = "TestPointTestControllerActivity | PointTestPumpController"

        btnStart = findViewById(R.id.btnStart)
        btnSkip = findViewById(R.id.btnExecute)
        btnSkip.text = "Pular"
        btnCancel = findViewById(R.id.btnCancel)
        btnPause = findViewById(R.id.btnPause)
        btnSkipRotation = findViewById(R.id.btnContinue)
        btnSkipRotation.text = "Pular Rotação"
        btnContinuarTeste = findViewById(R.id.btnFinish)
        btnContinuarTeste.text = "Continuar Teste"
        btnNext = findViewById(R.id.btnNext)
        btnNext.visibility = View.INVISIBLE
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

        tvControlLog = findViewById(R.id.tvControlLog)
        tvControlStatus = findViewById(R.id.tvControlStatus)
        tvControlError = findViewById(R.id.tvControlError)
        tvMeasurementLog = findViewById(R.id.tvMedicaoRx)
        tvMeasurementStatus = findViewById(R.id.tvMeasurementStatus)
        tvMeasurementError = findViewById(R.id.tvMeasurementError)

        pointTestPumpList = ListaDePontosDeTeste()
        pointTestPumpList
//            .add(PontoDeTesteWarmUp())
//            .add(PontoDeTesteVisualCheck())
            .add(PontoDeTesteKenn())
            .add(PontoDeTesteFill())
            .add(PontoDeTesteZeroDelivery())
            .add(PontoDeTesteEfficiency())
            .add(PontoDeTesteStart())
        setPoint(indexPointTestController)

        pointTestController.rotationResultValues.observe(this) { value ->
            tvControlStatus.text =
                value.status.getLocalizedMessage(applicationContext)
            tvControlError.text =
                value.error.getLocalizedMessage(applicationContext)
            tvControlLog.text = value.toString()
        }
        pointTestController.pointTestPumpResultValues.observe(this) { value ->
            tvControlStatus.text =
                value.status.getLocalizedMessage(applicationContext)
            tvControlError.text =
                value.error.getLocalizedMessage(applicationContext)
            tvControlLog.text = value.toString()
        }

        btnStart.setOnClickListener {
            pointTestController.startTest()
        }
        btnCancel.setOnClickListener {
            pointTestController.cancelTest()
        }
        btnPause.setOnClickListener {
            pointTestController.pauseTest()
        }
        btnSkip.setOnClickListener {
            pointTestController.skipTest()
        }
        btnSkipRotation.setOnClickListener {
            pointTestController.skipRotation()
        }
        btnContinuarTeste.setOnClickListener {
            pointTestController.continuarTest()
        }
        btnNext.setOnClickListener {
            indexPointTestController++
            if (indexPointTestController >= pointTestPumpList.size)
                indexPointTestController = 0
            setPoint(indexPointTestController)
        }
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

    fun setPoint(index: Int) {
        btnNext.text = "${pointTestPumpList[index].typePointTest!!.description}"
        pointTestController.setPointTestPump(pointTestPumpList[index])
    }

    override fun onBackPressed() {
        pointTestController.cancelTest()
        super.onBackPressed()
    }
}