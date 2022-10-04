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
import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestSynchronizedPumpResult
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
class UnitTestSynchronizedPumpTestFragment : Fragment() {

    private val tagLog: String = this::class.java.simpleName
    private lateinit var listTestPumpSynchronized: ArrayList<String>
    private lateinit var root: View
    private lateinit var viewModel: PumpTestUnitViewModel

    //Componentes View (UI)
    private lateinit var btnHwTestStartPumpSyn: Button
    private lateinit var btnHwTestParamPumpSyn: Button
    private lateinit var btnHwTestStopPumpSyn: Button
    private lateinit var btnHwTestSkipPumpSyn: Button
    private lateinit var btnHwTestCancelPumpSyn: Button
    private lateinit var txtHwTestPumpSynValueTestTime: TextView
    private lateinit var txtHwTestPumpSynValuePressure: TextView
    private lateinit var txtHwTestPumpSynValueRotation: TextView
    private lateinit var txtHwTestPumpSynValueTemperature: TextView
    private lateinit var txtHwTestPumpSynValue05: TextView
    private lateinit var txtHwTestPumpSynValue06: TextView
    private lateinit var txtHwTestPumpSynValue07: TextView
    private lateinit var txtHwTestPumpSynValue08: TextView
    private lateinit var txtHwTestPumpSynValue09: TextView
    private lateinit var txtHwTestPumpSynValue10: TextView
    private lateinit var txtHwTestPumpSynValue11: TextView
    private lateinit var txtHwTestPumpSynValue12: TextView

    private lateinit var txtHwTestStatusPumpSyn: TextView
    private lateinit var txtHWTestErrorPumpSyn: TextView

    private lateinit var editHwTestPumpSynParamTestTime: EditText
    private lateinit var editHwTestPumpSynParam02: EditText
    private lateinit var editHwTestPumpSynParam03: EditText
    private lateinit var editHwTestPumpSynParam04: EditText
    private lateinit var editHwTestPumpSynParam05: EditText
    private lateinit var editHwTestPumpSynParam06: EditText
    private lateinit var editHwTestPumpSynParam07: EditText
    private lateinit var editHwTestPumpSynParam08: EditText
    private lateinit var editHwTestPumpSynParam09: EditText
    private lateinit var editHwTestPumpSynParam10: EditText
    private lateinit var editHwTestPumpSynParam11: EditText
    private lateinit var editHwTestPumpSynParam12: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PumpTestUnitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_unit_test_synchronized_pump, container, false)

        setupUI()
        setupButton()
        setupViewModel()

        return root
    }

    private fun setupUI() {
        btnHwTestStartPumpSyn = root.findViewById(R.id.btn_HW_test_synchronized_start)
        btnHwTestParamPumpSyn = root.findViewById(R.id.btn_HW_test_synchronized_param)
        btnHwTestStopPumpSyn = root.findViewById(R.id.btn_HW_test_synchronized_stop)
        btnHwTestSkipPumpSyn = root.findViewById(R.id.btn_HW_test_synchronized_skip)
        btnHwTestCancelPumpSyn = root.findViewById(R.id.btn_HW_test_synchronized_cancel)
        txtHwTestPumpSynValueTestTime = root.findViewById(R.id.txt_value_HW_test_leitura_tempo)
        txtHwTestPumpSynValuePressure = root.findViewById(R.id.txt_value_HW_test_leitura_pressao)
        txtHwTestPumpSynValueRotation = root.findViewById(R.id.txt_value_HW_test_leitura_rotacao)
        txtHwTestPumpSynValueTemperature = root.findViewById(R.id.txt_value_HW_test_leitura_temperatura)
        txtHwTestPumpSynValue05 = root.findViewById(R.id.txt_value_HW_test_leitura5)
        txtHwTestPumpSynValue06 = root.findViewById(R.id.txt_value_HW_test_leitura6)
        txtHwTestPumpSynValue07 = root.findViewById(R.id.txt_value_HW_test_leitura7)
        txtHwTestPumpSynValue08 = root.findViewById(R.id.txt_value_HW_test_leitura8)
        txtHwTestPumpSynValue09 = root.findViewById(R.id.txt_value_HW_test_leitura9)
        txtHwTestPumpSynValue10 = root.findViewById(R.id.txt_value_HW_test_leitura10)
        txtHwTestPumpSynValue11 = root.findViewById(R.id.txt_value_HW_test_leitura11)
        txtHwTestPumpSynValue12 = root.findViewById(R.id.txt_value_HW_test_leitura12)
        txtHwTestStatusPumpSyn = root.findViewById(R.id.txt_value_HW_test_synchronized_status)
        txtHWTestErrorPumpSyn = root.findViewById(R.id.txt_value_HW_test_synchronized_error)
        editHwTestPumpSynParamTestTime = root.findViewById(R.id.edit_HW_test_param1)
        editHwTestPumpSynParam02 = root.findViewById(R.id.edit_HW_test_param2)
        editHwTestPumpSynParam03 = root.findViewById(R.id.edit_HW_test_param3)
        editHwTestPumpSynParam04 = root.findViewById(R.id.edit_HW_test_param4)
        editHwTestPumpSynParam05 = root.findViewById(R.id.edit_HW_test_param5)
        editHwTestPumpSynParam06 = root.findViewById(R.id.edit_HW_test_param6)
        editHwTestPumpSynParam07 = root.findViewById(R.id.edit_HW_test_param7)
        editHwTestPumpSynParam08 = root.findViewById(R.id.edit_HW_test_param8)
        editHwTestPumpSynParam09 = root.findViewById(R.id.edit_HW_test_param9)
        editHwTestPumpSynParam10 = root.findViewById(R.id.edit_HW_test_param10)
        editHwTestPumpSynParam11 = root.findViewById(R.id.edit_HW_test_param11)
        editHwTestPumpSynParam12 = root.findViewById(R.id.edit_HW_test_param12)
    }

    /**
     * Button Actions
     */
    private fun setupButton() {
        btnHwTestParamPumpSyn.setOnClickListener {
            Log.w(tagLog, "Parametrização Teste Bombas Sincronizadas")
            listTestPumpSynchronized = arrayListOf(
                editHwTestPumpSynParamTestTime.text.toString(),
                editHwTestPumpSynParam02.text.toString(),
                editHwTestPumpSynParam03.text.toString(),
                editHwTestPumpSynParam04.text.toString(),
                editHwTestPumpSynParam05.text.toString(),
                editHwTestPumpSynParam06.text.toString(),
                editHwTestPumpSynParam07.text.toString(),
                editHwTestPumpSynParam08.text.toString(),
                editHwTestPumpSynParam09.text.toString(),
                editHwTestPumpSynParam10.text.toString(),
                editHwTestPumpSynParam11.text.toString(),
                editHwTestPumpSynParam12.text.toString()
            )
            viewModel.sendSynchronizedTestPumpParam(listTestPumpSynchronized)
        }

        btnHwTestStartPumpSyn.setOnClickListener {
            viewModel.sendSynchronizedTestPumpStart()
        }

        btnHwTestStopPumpSyn.setOnClickListener {
            viewModel.sendSynchronizedTestPumpStop()
        }

        btnHwTestSkipPumpSyn.setOnClickListener {
            viewModel.sendSynchronizedTestPumpSkip()
        }

        btnHwTestCancelPumpSyn.setOnClickListener {
            viewModel.sendSynchronizedTestPumpCancel()
        }
    }

    private fun setupViewModel() {
        viewModel.pointTestSynchronizedPumpResultValues.observe(viewLifecycleOwner) {
            updateScreenValues(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateScreenValues(values: PointTestSynchronizedPumpResult) {
        txtHwTestPumpSynValueTestTime.text = values.testTime.toString()
        txtHwTestPumpSynValuePressure.text = values.pressure.toString()
        txtHwTestPumpSynValueRotation.text = values.rotation.toString()
        txtHwTestPumpSynValueTemperature.text = values.temperature.toString()
        txtHwTestPumpSynValue05.text = values.value5.toString()
        txtHwTestPumpSynValue06.text = values.value6.toString()
        txtHwTestPumpSynValue07.text = values.value7.toString()
        txtHwTestPumpSynValue08.text = values.value8.toString()
        txtHwTestPumpSynValue09.text = values.value9.toString()
        txtHwTestPumpSynValue10.text = values.value10.toString()
        txtHwTestPumpSynValue11.text = values.value11.toString()
        txtHwTestPumpSynValue12.text = values.value12.toString()
    }
}