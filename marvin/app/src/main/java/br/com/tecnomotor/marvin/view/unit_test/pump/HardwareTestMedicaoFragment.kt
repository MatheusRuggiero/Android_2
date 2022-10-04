package br.com.tecnomotor.marvin.view.unit_test.pump

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.commands.result.HardwareMedTestResult
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@DelicateCoroutinesApi
class HardwareTestMedicaoFragment : Fragment() {

    private val tagLog: String = this::class.java.simpleName
    private lateinit var root: View
    private lateinit var resultHwTestMeasurement: HardwareMedTestResult
    private lateinit var viewModel: PumpTestUnitViewModel

    /* Hardware Test Variables */
    private lateinit var btnHwTestOffBuzzerMed: Button
    private lateinit var btnHwTestOnBuzzerMed: Button
    private lateinit var btnValvFlushRetOFFMed: Button
    private lateinit var btnValvFlushRetONMed: Button
    private lateinit var btnValvFlushInjOFFMed: Button
    private lateinit var btnValvFlushInjONMed: Button
    private lateinit var btnHwValvCondOFFMed: Button
    private lateinit var btnHwValvCondONMed: Button
    private lateinit var btnHwValvComut01OFFMed: Button
    private lateinit var btnHwValvComut01ONMed: Button
    private lateinit var btnHwValvComut02OFFMed: Button
    private lateinit var btnHwValvComut02ONMed: Button
    private lateinit var btnHwValvComut03OFFMed: Button
    private lateinit var btnHwValvComut03ONMed: Button
    private lateinit var btnHwDrenoM1OFFMed: Button
    private lateinit var btnHwDrenoM1ONMed: Button
    private lateinit var btnHwDrenoM2OFFMed: Button
    private lateinit var btnHwDrenoM2ONMed: Button
    private lateinit var btnHwExtra01OFFMed: Button
    private lateinit var btnHwExtra01ONMed: Button
    private lateinit var btnHwExtra02OFFMed: Button
    private lateinit var btnHwExtra02ONMed: Button
    private lateinit var btnAllHwOffMed: Button
    private lateinit var allButtonsGroupMed: Group
    private lateinit var allTextViewGroupMed: Group

    /* Analog Test Variables */
    private lateinit var btnStartAnalogTestMed: Button
    private lateinit var txtValueSensTemp: TextView
    private lateinit var txtValueDGT: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PumpTestUnitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_hardware_medicao_test, container, false)

        setupUI()
        setupButton()
        setupViewModel()

        return root
    }

    private fun setupUI() {
        btnHwTestOffBuzzerMed = root.findViewById(R.id.btnHwTestOffBuzzerMed)
        btnHwTestOnBuzzerMed = root.findViewById(R.id.btnHwTestOnBuzzerMed)
        btnValvFlushRetOFFMed = root.findViewById(R.id.btnValvFlushRetOFFMed)
        btnValvFlushRetONMed = root.findViewById(R.id.btnValvFlushRetONMed)
        btnValvFlushInjOFFMed = root.findViewById(R.id.btnValvFlushInjOFFMed)
        btnValvFlushInjONMed = root.findViewById(R.id.btnValvFlushInjONMed)
        btnHwValvCondOFFMed = root.findViewById(R.id.btnHwValvCondOFFMed)
        btnHwValvCondONMed = root.findViewById(R.id.btnHwValvCondONMed)
        btnHwValvComut01OFFMed = root.findViewById(R.id.btnHwValvComut01OFFMed)
        btnHwValvComut01ONMed = root.findViewById(R.id.btnHwValvComut01ONMed)
        btnHwValvComut02OFFMed = root.findViewById(R.id.btnHwValvComut02OFFMed)
        btnHwValvComut02ONMed = root.findViewById(R.id.btnHwValvComut02ONMed)
        btnHwValvComut03OFFMed = root.findViewById(R.id.btnHwValvComut03OFFMed)
        btnHwValvComut03ONMed = root.findViewById(R.id.btnHwValvComut03ONMed)
        btnAllHwOffMed = root.findViewById(R.id.btnAllHwOffMed)
        btnHwDrenoM1OFFMed = root.findViewById(R.id.btnHwDrenoM1OFFMed)
        btnHwDrenoM1ONMed = root.findViewById(R.id.btnHwDrenoM1ONMed)
        btnHwDrenoM2OFFMed = root.findViewById(R.id.btnHwDrenoM2OFFMed)
        btnHwDrenoM2ONMed = root.findViewById(R.id.btnHwDrenoM2ONMed)
        btnHwExtra01OFFMed = root.findViewById(R.id.btnHwExtra01OFFMed)
        btnHwExtra01ONMed = root.findViewById(R.id.btnHwExtra01ONMed)
        btnHwExtra02OFFMed = root.findViewById(R.id.btnHwExtra02OFFMed)
        btnHwExtra02ONMed = root.findViewById(R.id.btnHwExtra02ONMed)

        allButtonsGroupMed = root.findViewById(R.id.allButtonsGroupMed)
        allTextViewGroupMed = root.findViewById(R.id.allTextViewGroupMed)

        /* Analog Test Variables */
        btnStartAnalogTestMed = root.findViewById(R.id.btnStartAnalogTestMed)
        txtValueSensTemp = root.findViewById(R.id.txtValueSensTemp)
        txtValueDGT = root.findViewById(R.id.txtValueDGT)

        enabledButtonLeds()
    }

    /**
     * Button Actions
     */
    private fun setupButton() {
        btnStartAnalogTestMed.setOnClickListener {
            Log.w(tagLog, "Start Measurement Analog Test")
            viewModel.startAnalogTestMeasurement()
            allTextViewGroupMed.visibility = View.VISIBLE
        }
        btnAllHwOffMed.setOnClickListener { disableAllDigitalOutputs() }

        btnHwTestOffBuzzerMed.setOnClickListener {
            btnHwTestOffBuzzerMed.isEnabled = false
            btnHwTestOnBuzzerMed.isEnabled = true
            viewModel.sendOffBuzzerMeasurement()
        }
        btnHwTestOnBuzzerMed.setOnClickListener {
            btnHwTestOffBuzzerMed.isEnabled = true
            btnHwTestOnBuzzerMed.isEnabled = false
            viewModel.sendOnBuzzerMeasurement()
        }
        btnValvFlushRetOFFMed.setOnClickListener {
            btnValvFlushRetOFFMed.isEnabled = false
            btnValvFlushRetONMed.isEnabled = true
            viewModel.sendOffValvFlushRet()
        }
        btnValvFlushRetONMed.setOnClickListener {
            btnValvFlushRetOFFMed.isEnabled = true
            btnValvFlushRetONMed.isEnabled = false
            viewModel.sendOnValvFlushRet()
        }
        btnValvFlushInjOFFMed.setOnClickListener {
            btnValvFlushInjOFFMed.isEnabled = false
            btnValvFlushInjONMed.isEnabled = true
            viewModel.sendOffValvFlushInj()
        }
        btnValvFlushInjONMed.setOnClickListener {
            btnValvFlushInjOFFMed.isEnabled = true
            btnValvFlushInjONMed.isEnabled = false
            viewModel.sendOnValvFlushInj()
        }
        btnHwValvCondOFFMed.setOnClickListener {
            btnHwValvCondOFFMed.isEnabled = false
            btnHwValvCondONMed.isEnabled = true
            viewModel.sendOffValvCond()
        }
        btnHwValvCondONMed.setOnClickListener {
            btnHwValvCondOFFMed.isEnabled = true
            btnHwValvCondONMed.isEnabled = false
            viewModel.sendOnValvCond()
        }
        btnHwValvComut01OFFMed.setOnClickListener {
            btnHwValvComut01OFFMed.isEnabled = false
            btnHwValvComut01ONMed.isEnabled = true
            viewModel.sendOffComut01()
        }
        btnHwValvComut01ONMed.setOnClickListener {
            btnHwValvComut01OFFMed.isEnabled = true
            btnHwValvComut01ONMed.isEnabled = false
            viewModel.sendOnComut01()
        }
        btnHwValvComut02OFFMed.setOnClickListener {
            btnHwValvComut02OFFMed.isEnabled = false
            btnHwValvComut02ONMed.isEnabled = true
            viewModel.sendOffComut02()
        }
        btnHwValvComut02ONMed.setOnClickListener {
            btnHwValvComut02OFFMed.isEnabled = true
            btnHwValvComut02ONMed.isEnabled = false
            viewModel.sendOnComut02()
        }
        btnHwValvComut03OFFMed.setOnClickListener {
            btnHwValvComut03OFFMed.isEnabled = false
            btnHwValvComut03ONMed.isEnabled = true
            viewModel.sendOffComut03()
        }
        btnHwValvComut03ONMed.setOnClickListener {
            btnHwValvComut03OFFMed.isEnabled = true
            btnHwValvComut03ONMed.isEnabled = false
            viewModel.sendOnComut03()
        }
        btnHwDrenoM1OFFMed.setOnClickListener {
            btnHwDrenoM1OFFMed.isEnabled = false
            btnHwDrenoM1ONMed.isEnabled = true
            viewModel.sendOffDreno01()
        }
        btnHwDrenoM1ONMed.setOnClickListener {
            btnHwDrenoM1OFFMed.isEnabled = true
            btnHwDrenoM1ONMed.isEnabled = false
            viewModel.sendOnDreno01()
        }
        btnHwDrenoM2OFFMed.setOnClickListener {
            btnHwDrenoM2OFFMed.isEnabled = false
            btnHwDrenoM2ONMed.isEnabled = true
            viewModel.sendOffDreno02()
        }
        btnHwDrenoM2ONMed.setOnClickListener {
            btnHwDrenoM2OFFMed.isEnabled = true
            btnHwDrenoM2ONMed.isEnabled = false
            viewModel.sendOnDreno02()
        }
        btnHwExtra01OFFMed.setOnClickListener {
            btnHwExtra01OFFMed.isEnabled = false
            btnHwExtra01ONMed.isEnabled = true
            viewModel.sendOffExtra01()
        }
        btnHwExtra01ONMed.setOnClickListener {
            btnHwExtra01OFFMed.isEnabled = true
            btnHwExtra01ONMed.isEnabled = false
            viewModel.sendOnExtra01()
        }
        btnHwExtra02OFFMed.setOnClickListener {
            btnHwExtra02OFFMed.isEnabled = false
            btnHwExtra02ONMed.isEnabled = true
            viewModel.sendOffExtra02()
        }
        btnHwExtra02ONMed.setOnClickListener {
            btnHwExtra02OFFMed.isEnabled = true
            btnHwExtra02ONMed.isEnabled = false
            viewModel.sendOnExtra02()
        }
    }

    private fun setupViewModel() {
        viewModel.commandMeasurementSendRead.observe(viewLifecycleOwner) {
            resultHwTestMeasurement = HardwareMedTestResult().ofByteArray(it.read)
            updateValuesTestAnalog()
        }
    }

    /**
     * Desliga todas as saídas digitais
     */
    private fun disableAllDigitalOutputs() {
        viewModel.sendOffBuzzerMeasurement()
        viewModel.sendOffValvFlushRet()
        viewModel.sendOffValvFlushInj()
        viewModel.sendOffValvCond()
        viewModel.sendOffComut01()
        viewModel.sendOffComut02()
        viewModel.sendOffComut03()
        viewModel.sendOffDreno01()
        viewModel.sendOffDreno02()
        viewModel.sendOffExtra01()
        viewModel.sendOffExtra02()

        enabledButtonLeds()
    }

    /**
     * Desabilita os botões off e habilita os botões on
     */
    private fun enabledButtonLeds() {
        btnHwTestOffBuzzerMed.isEnabled = false
        btnValvFlushRetOFFMed.isEnabled = false
        btnValvFlushInjOFFMed.isEnabled = false
        btnHwValvCondOFFMed.isEnabled = false
        btnHwValvComut01OFFMed.isEnabled = false
        btnHwValvComut02OFFMed.isEnabled = false
        btnHwValvComut03OFFMed.isEnabled = false
        btnHwDrenoM1OFFMed.isEnabled = false
        btnHwDrenoM2OFFMed.isEnabled = false
        btnHwExtra01OFFMed.isEnabled = false
        btnHwExtra02OFFMed.isEnabled = false

        btnHwTestOnBuzzerMed.isEnabled = true
        btnValvFlushRetONMed.isEnabled = true
        btnValvFlushInjONMed.isEnabled = true
        btnHwValvCondONMed.isEnabled = true
        btnHwValvComut01ONMed.isEnabled = true
        btnHwValvComut02ONMed.isEnabled = true
        btnHwValvComut03ONMed.isEnabled = true
        btnHwDrenoM1ONMed.isEnabled = true
        btnHwDrenoM2ONMed.isEnabled = true
        btnHwExtra01ONMed.isEnabled = true
        btnHwExtra02ONMed.isEnabled = true
    }

    /**
     *  Update Values Analog Test Information
     */
    private fun updateValuesTestAnalog() {
        val valueDGT = (resultHwTestMeasurement.anDgt / 1000)
        txtValueSensTemp.text = resultHwTestMeasurement.anSensTemp.toString()
        txtValueDGT.text = valueDGT.toString()
    }

}

