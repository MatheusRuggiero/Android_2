package br.com.tecnomotor.marvin.view.unit_test.pump

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnomotor.commonrail.device.commands.CommandSendRead
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.databinding.ActivityTestesFuncionaisBinding
import br.com.tecnomotor.marvin.utils.ExtentionFunctions.toHex
import br.com.tecnomotor.marvin.view.unit_test.SectionsPagerAdapter
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class UnitTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestesFuncionaisBinding
    private val viewModel: PumpTestUnitViewModel by viewModels()

    private lateinit var tvControleTx: TextView
    private lateinit var tvControleTxTime: TextView
    private lateinit var tvControleRx: TextView
    private lateinit var tvControleRxTime: TextView
    private lateinit var tvControleRx19: TextView
    private lateinit var tvControleRx19Time: TextView

    private lateinit var tvMedicaoTx: TextView
    private lateinit var tvMedicaoRxTime: TextView
    private lateinit var tvMedicaoRx: TextView
    private lateinit var tvMedicaoTxTime: TextView
    private lateinit var tvMedicaoRx19: TextView
    private lateinit var tvMedicaoRx19Time: TextView
    private lateinit var btnTestPumpResetControl: Button
    private lateinit var btnTestPumpResetMeasure: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        binding = ActivityTestesFuncionaisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupButton()
    }

    private fun setupViewModel() {
        viewModel.startControllers()
        viewModel.commandToDeviceControlValues.observe(this@UnitTestActivity) {

        }

        viewModel.pointTestPumpPumpResultValues.observe(this@UnitTestActivity) {

        }

        viewModel.rotationResultValues.observe(this@UnitTestActivity) {

        }

        viewModel.commandControlSendRead.observe(this@UnitTestActivity) {
            updateControlValues(it)
        }

        viewModel.commandToDeviceMeasurementValues.observe(this@UnitTestActivity) {
//            updateMeasuresValues(it)
        }

        viewModel.commandMeasurementSendRead.observe(this@UnitTestActivity) {
            updateMeasuresValues(it)
        }

    }

    private fun listenMeasurement() {
        viewModel.commandToDeviceMeasurementValues.observe(this@UnitTestActivity) {
            // TOOD
        }
        viewModel.measurementResultValues.observe(this@UnitTestActivity) {
            // TODO
        }
        viewModel.commandMeasurementSendRead.observe(this@UnitTestActivity) {
            updateMeasuresValues(it)
        }
    }

    private fun setupUI() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager) // fragment page adapter
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        tvControleTx = findViewById(R.id.tvControleTx)
        tvControleTxTime = findViewById(R.id.tvControleTxTime)
        tvControleRx = findViewById(R.id.tvControleRx)
        tvControleRxTime = findViewById(R.id.tvControleRxTime)
        tvControleRx19 = findViewById(R.id.tvControleRx19)
        tvControleRx19Time = findViewById(R.id.tvControleRx19Time)
        tvMedicaoTx = findViewById(R.id.tvMedicaoTx)
        tvMedicaoTxTime = findViewById(R.id.tvMedicaoTxTime)
        tvMedicaoRx = findViewById(R.id.tvMedicaoRx)
        tvMedicaoRxTime = findViewById(R.id.tvMedicaoRxTime)
        tvMedicaoRx19 = findViewById(R.id.tvMedicaoRx19)
        tvMedicaoRx19Time = findViewById(R.id.tvMedicaoRx19Time)
        btnTestPumpResetControl = findViewById(R.id.btn_test_unit_reset_control)
        btnTestPumpResetMeasure = findViewById(R.id.btn_test_unit_reset_measure)
    }

    /**
     * Button Actions
     */
    private fun setupButton() {
        btnTestPumpResetControl.setOnClickListener { viewModel.resetControlParam() }
        btnTestPumpResetMeasure.setOnClickListener { viewModel.resetMeasureParam() }
    }

    override fun onBackPressed() {
        viewModel.stopControllers()
        super.onBackPressed()
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun updateControlValues(value: CommandSendRead) {
        GlobalScope.launch(Dispatchers.Main) {
            val dateStr = SimpleDateFormat("HH:mm:ss.SSS").format(Date())
            val tx = value.send.toHex()
            val rx = value.read?.toHex() ?: ""
            tvControleRx19Time.text = dateStr
            tvControleRx19.text = "RX: $rx"
            if (tx.substring(1, 3) != "19") {
//                tvControleRxTime.text = dateStr
//                tvControleTxTime.text = dateStr
                tvControleTx.text = "TX: $tx"
                tvControleRx.text = "RX: $rx"
            }
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun updateMeasuresValues(value: CommandSendRead) {
        GlobalScope.launch(Dispatchers.Main) {
            val dateStr = SimpleDateFormat("HH:mm:ss.SSS").format(Date())
            val tx = value.send.toHex()
            val rx = value.read?.toHex() ?: ""
            tvMedicaoRx19Time.text = dateStr
            tvMedicaoRx19.text = "RX: $rx"
            if (tx.substring(1, 3) != "19") {
//                tvMedicaoRxTime.text = dateStr
//                tvMedicaoTxTime.text = dateStr
                tvMedicaoTx.text = "TX: $tx"
                tvMedicaoRx.text = "RX: $rx"
            }
        }
    }
}
