package br.com.tecnomotor.marvin.view.unit_test.pump

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestPumpResult
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
class UnitTestPumpTestFragment : Fragment() {

    private val tagLog: String = this::class.java.simpleName
    private lateinit var listOpPointTestPump: ArrayList<String>

    private lateinit var viewModel: PumpTestUnitViewModel

    //Componentes View (UI)
    private lateinit var root: View
    private lateinit var btnHwTestPumpStart: Button
    private lateinit var btnHwTestPumpParam: Button
    private lateinit var btnHwTestPumpFinished: Button
    private lateinit var btnHwTestPumpPaused: Button
    private lateinit var btnHwTestPumpRet: Button
    private lateinit var btnHwTestPumpSkip: Button
    private lateinit var txtHwTestPumpEXT1: TextView
    private lateinit var txtHwTestPumpEXT2: TextView
    private lateinit var txtHwTestPumpPressure: TextView
    private lateinit var txtHwTestPumpRotation: TextView
    private lateinit var txtHwTestPumpStatus: TextView
    private lateinit var txtHWTestPumpControlError: TextView
    private lateinit var txtHwTestPumpTemp: TextView
    private lateinit var editHwTestPumpControle: EditText
    private lateinit var editHwTestPumpPressureDesired: EditText
    private lateinit var editHWTestPumpExt1Desired: EditText
    private lateinit var editHWTestPumpExt2Desired: EditText
    private lateinit var editHwTestPumpFreqEXT1: EditText
    private lateinit var editHwTestPumpFreqEXT2: EditText
    private lateinit var editHWTestPumpRotationDesired: EditText
    private lateinit var editHWTestPumpTimeOut: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PumpTestUnitViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_unit_test_pump, container, false)

        setupUI()
        setupButton()
        setupViewModel()

        return root
    }

    private fun setupUI() {
        btnHwTestPumpStart = root.findViewById(R.id.btn_HW_test_pump_start)
        btnHwTestPumpParam = root.findViewById(R.id.btn_HW_test_pump_param)
        btnHwTestPumpFinished = root.findViewById(R.id.btn_HW_test_pump_finalizado)
        btnHwTestPumpPaused = root.findViewById(R.id.btn_HW_test_pump_pausado)
        btnHwTestPumpRet = root.findViewById(R.id.btn_HW_test_pump_ret)
        btnHwTestPumpSkip = root.findViewById(R.id.btn_HW_test_pump_skip)
        txtHwTestPumpEXT1 = root.findViewById(R.id.txt_value_HW_test_pump_ext1)
        txtHwTestPumpEXT2 = root.findViewById(R.id.txt_value_HW_test_pump_ext2)
        txtHwTestPumpPressure = root.findViewById(R.id.txt_value_HW_test_pump_pressure)
        txtHwTestPumpRotation = root.findViewById(R.id.txt_value_HW_test_pump_rotation)
        txtHwTestPumpStatus = root.findViewById(R.id.txt_value_HW_test_pump_status)
        txtHWTestPumpControlError = root.findViewById(R.id.txt_value_HW_test_pump_error)
        txtHwTestPumpTemp = root.findViewById(R.id.txt_value_HW_test_pump_temp)
        editHwTestPumpControle = root.findViewById(R.id.edit_HW_Test_pump_controle)
        editHwTestPumpPressureDesired = root.findViewById(R.id.edit_HW_test_pump_pressure_desired)
        editHWTestPumpExt1Desired = root.findViewById(R.id.edit_HW_test_pump_ext1_desired)
        editHWTestPumpExt2Desired = root.findViewById(R.id.edit_HW_test_pump_ext2_desired)
        editHwTestPumpFreqEXT1 = root.findViewById(R.id.edit_HW_test_pump_freq_ext1)
        editHwTestPumpFreqEXT2 = root.findViewById(R.id.edit_HW_test_pump_freq_ext2)
        editHWTestPumpRotationDesired = root.findViewById(R.id.edit_HW_test_pump_rotation_desired)
        editHWTestPumpTimeOut = root.findViewById(R.id.edit_HW_test_pump_timeout_desired)
    }

    /**
     * Button Actions
     */
    private fun setupButton() {
        btnHwTestPumpParam.setOnClickListener {
            Log.w(tagLog, "Parametrização Info. Teste Bomba")
            listOpPointTestPump = arrayListOf(
                editHwTestPumpControle.text.toString(),
                editHWTestPumpExt1Desired.text.toString(),
                editHWTestPumpExt2Desired.text.toString(),
                editHwTestPumpPressureDesired.text.toString(),
                editHWTestPumpRotationDesired.text.toString(),
                editHWTestPumpTimeOut.text.toString(),
                editHwTestPumpFreqEXT1.text.toString(),
                editHwTestPumpFreqEXT2.text.toString()
            )
            viewModel.sendPointTestPumpParam(listOpPointTestPump)
        }

        btnHwTestPumpStart.setOnClickListener {
            viewModel.sendPointTestPumpStart()
        }

        btnHwTestPumpFinished.setOnClickListener {
            viewModel.sendPointTestPumpFinished()
        }

        btnHwTestPumpPaused.setOnClickListener {
            viewModel.sendPointTestPumpPaused()
        }

        btnHwTestPumpRet.setOnClickListener {
            viewModel.sendPointTestPumpRet()
        }

        btnHwTestPumpSkip.setOnClickListener {
            viewModel.sendPointTestPumpSkip()
        }
    }

    private fun setupViewModel() {
        viewModel.pointTestPumpPumpResultValues.observe(viewLifecycleOwner) {
            updateScreenValues(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateScreenValues(values: PointTestPumpResult) {
        txtHwTestPumpEXT1.text = "%.2f".format(values.Ext1Current)
        txtHwTestPumpEXT2.text = "%.2f".format(values.Ext2Current)
        txtHwTestPumpPressure.text = values.pressure.toString()
        txtHwTestPumpRotation.text = values.rotation.toString()
        txtHwTestPumpTemp.text = values.temperature.toString()
        txtHwTestPumpStatus.text = values.status.getLocalizedMessage(requireActivity().application)
        txtHWTestPumpControlError.text = values.error.getLocalizedMessage(requireActivity().application)
    }
}
