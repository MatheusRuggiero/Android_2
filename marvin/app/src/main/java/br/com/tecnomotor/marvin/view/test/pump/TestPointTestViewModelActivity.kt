package br.com.tecnomotor.marvin.view.test.pump

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.view.pump.viewmodel.PointTestPumpViewModel
import br.com.tecnomotor.marvin.view.pump.viewmodel.factory.PointTestPumpViewModelFactory
import br.com.tecnomotor.marvin.view.test.pump.data.*
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
@SuppressLint("SetTextI18n")
class TestPointTestViewModelActivity : AppCompatActivity() {

    private lateinit var pointTestPumpList: ListaDePontosDeTeste

    private val viewModel by lazy {
        val factory = PointTestPumpViewModelFactory(pointTestPumpList, this::setPoint, Pump()) // TODO: corrigir esse Pump() vazio, colocar com valores específicos
        val vm = ViewModelProvider(this, factory)[PointTestPumpViewModel::class.java]
        vm.pointTestPumpResultLiveData.observe(this, Observer {
            tvControlStatus.text = it.getItem().pointTestPumpResult.status.getLocalizedMessage(applicationContext)
            tvControlError.text = it.getItem().pointTestPumpResult.error.getLocalizedMessage(applicationContext)
            tvControlLog.text = it.toString()
            tvMeasurementLog.text = it.getItem().pointTest.toString()
            btnPause.isEnabled = !it.getItem().pointTestPumpResult.checkRotation
            btnPause.isEnabled = (it.getItem().pointTestPumpResult.status != EnumTestStatus.TEST_FAIL)
            btnSkipRotation.isEnabled = (it.getItem().pointTestPumpResult.status != EnumTestStatus.TEST_FAIL)
            btnSkip.isEnabled = (it.getItem().pointTestPumpResult.status != EnumTestStatus.TEST_FAIL)
        })
        vm
    }

    private lateinit var btnStart: Button
    private lateinit var btnContinueTest: Button
    private lateinit var btnPause: Button
    private lateinit var btnCancel: Button
    private lateinit var btnSkipRotation: Button
    private lateinit var btnSkip: Button
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
        supportActionBar?.title = "TestPointTestViewModelActivity | PointTestPumpViewModel | PointTestPumpController"


        btnStart = findViewById(R.id.btnStart)
        btnPause = findViewById(R.id.btnExecute)
        btnPause.text = "Pausar"
        btnCancel = findViewById(R.id.btnCancel)
        btnSkipRotation = findViewById(R.id.btnPause)
        btnSkipRotation.text = "Pular Rotação"
        btnSkip = findViewById(R.id.btnContinue)
        btnSkip.text = getString(R.string.txt_skip)
        btnContinueTest = findViewById(R.id.btnFinish)
        btnContinueTest.text = "Continuar Teste"
        btnNext = findViewById(R.id.btnNext)
        btnNext.isEnabled = false
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
            .add(PontoDeTesteWarmUp())
            .add(PontoDeTesteVisualCheck())
            .add(PontoDeTesteKenn())
            .add(PontoDeTesteFill())
            .add(PontoDeTesteZeroDelivery())
            .add(PontoDeTesteEfficiency())
            .add(PontoDeTesteStart())

        setPoint(viewModel.testPointsResult.indexSelected)

        btnStart.setOnClickListener {
            viewModel.startTest()
        }
        btnCancel.setOnClickListener {
            viewModel.cancelTest()
        }
        btnSkipRotation.setOnClickListener {
            viewModel.skipRotation()
        }
        btnSkip.setOnClickListener {
            viewModel.skipTest()
        }
        btnPause.setOnClickListener {
            viewModel.pauseTest()
        }
        btnContinueTest.setOnClickListener {
            viewModel.continuarTeste()
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
        GlobalScope.launch(Dispatchers.Main) {
            btnNext.text =
                "${pointTestPumpList[index].typePointTest!!.description}"
        }
    }

    override fun onBackPressed() {
        viewModel.cancelTest()
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            super.onBackPressed()
        }
    }
}