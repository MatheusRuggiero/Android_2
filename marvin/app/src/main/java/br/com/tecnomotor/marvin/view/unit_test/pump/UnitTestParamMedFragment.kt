package br.com.tecnomotor.marvin.view.unit_test.pump

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumDefinesTestTypeMeasurement
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
class UnitTestParamMedFragment : Fragment() {

    private val tagLog: String = this::class.java.simpleName
    private lateinit var viewModel: PumpTestUnitViewModel
    private lateinit var root: View

    // Componentes View
    private lateinit var spinner: Spinner
    private var opSpinner = ""
    private lateinit var btnParamMed: Button
    private lateinit var btnParamYesMed: Button
    private lateinit var btnParamNoMed: Button
    private lateinit var btnParamRetMed: Button
    private lateinit var btnParamErrorMed: Button
    private lateinit var btnParamFlushMed: Button
    private lateinit var btnParamCondicMed: Button
    private lateinit var btnParamEstanqMed: Button
    private lateinit var btnParamInjMed: Button
    private lateinit var btnParamValMed: Button
    private lateinit var btnParamPumpMed: Button
    private lateinit var editParamCh1Med: EditText
    private lateinit var editParamCh2Med: EditText
    private lateinit var editParamFreqMed: EditText
    private lateinit var editParamTimeOutMed: EditText
    private lateinit var radioParamMedicaoCH1Med: RadioButton
    private lateinit var radioParamMedicaoCH2Med: RadioButton
    private lateinit var radioParamMedicaoDGTMed: RadioButton
    private lateinit var radioParamInjecaoDesativadoMed: RadioButton
    private lateinit var radioParamInjecaoAtivadoMed: RadioButton
    private lateinit var radioParamRetornoDesativadoMed: RadioButton
    private lateinit var radioParamRetornoAtivadoMed: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PumpTestUnitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_unit_test_param_med, container, false)

        setupUI()
        setupButton()

        return root
    }

    private fun setupUI() {
        spinner = root.findViewById(R.id.spinner_param_dispositivo)
        btnParamMed = root.findViewById(R.id.btn_HW_test_pump_param_med)
        btnParamYesMed = root.findViewById(R.id.btn_HW_test_yes_param_med)
        btnParamNoMed = root.findViewById(R.id.btn_HW_test_no_param_med)
        btnParamRetMed = root.findViewById(R.id.btn_HW_test_ret_param_med)
        btnParamErrorMed = root.findViewById(R.id.btn_HW_test_error_param_med)
        btnParamFlushMed = root.findViewById(R.id.btn_HW_test_flush_med)
        btnParamCondicMed = root.findViewById(R.id.btn_HW_test_cond_med)
        btnParamEstanqMed = root.findViewById(R.id.btn_HW_test_estanq_med)
        btnParamInjMed = root.findViewById(R.id.btn_HW_test_inj_med)
        btnParamValMed = root.findViewById(R.id.btn_HW_test_val_med)
        btnParamPumpMed = root.findViewById(R.id.btn_HW_test_pump_med)
        editParamCh1Med = root.findViewById(R.id.edit_HW_test_param_ch1)
        editParamCh2Med = root.findViewById(R.id.edit_HW_test_param_ch2)
        editParamFreqMed = root.findViewById(R.id.edit_HW_test_param_freq)
        editParamTimeOutMed = root.findViewById(R.id.edit_HW_test_param_timeout)
        radioParamMedicaoCH1Med = root.findViewById(R.id.op_ch1)
        radioParamMedicaoCH2Med = root.findViewById(R.id.op_ch2)
        radioParamMedicaoDGTMed = root.findViewById(R.id.op_dgt)
        radioParamInjecaoDesativadoMed = root.findViewById(R.id.op_injecao_desativado)
        radioParamInjecaoAtivadoMed = root.findViewById(R.id.op_injecao_ativado)
        radioParamRetornoDesativadoMed = root.findViewById(R.id.op_retorno_desativado)
        radioParamRetornoAtivadoMed = root.findViewById(R.id.op_retorno_ativado)

        /**
         * Spinner
         */
        val devices = resources.getStringArray(R.array.dispositivos)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, devices)
        selectedOptionSpinner(adapter, devices)
    }

    /**
     * Button Actions
     */
    private fun setupButton() {
        btnParamMed.setOnClickListener {
            Log.i(tagLog, "UnitTestParamMedFragment - Parametrização Placa Medição")
            val opCh1 = editParamCh1Med.text.toString()
            val opCh2 = editParamCh2Med.text.toString()
            val opFreq = editParamFreqMed.text.toString()
            val opTimeOut = editParamTimeOutMed.text.toString()
            val opSpinner = checkSpinnerOp(opSpinner)
            val opMedicao = checkMedicaoOp()
            val opInjecao = checkFlagInjecaoOp()
            val opRetorno = checkFlagRetornoOp()
            viewModel.paramMed(opSpinner, opCh1, opCh2, opFreq, opTimeOut, opMedicao, opInjecao, opRetorno)
        }
        btnParamYesMed.setOnClickListener { viewModel.sendKeyParamMedYes() }
        btnParamNoMed.setOnClickListener { viewModel.sendKeyParamMedNo() }
        btnParamRetMed.setOnClickListener { viewModel.sendKeyParamMedRet() }
        btnParamErrorMed.setOnClickListener { viewModel.sendKeyParamMedError() }
        btnParamFlushMed.setOnClickListener { viewModel.sendKeyParamMedFlush() }
        btnParamCondicMed.setOnClickListener { viewModel.sendKeyParamMedCondic() }
        btnParamEstanqMed.setOnClickListener { viewModel.sendKeyParamMedEstanq() }
        btnParamInjMed.setOnClickListener { viewModel.sendKeyParamMedInjector() }
        btnParamValMed.setOnClickListener { viewModel.sendKeyParamMedValve() }
        btnParamPumpMed.setOnClickListener { viewModel.sendKeyParamMedPump() }
    }

    private fun checkMedicaoOp(): Int {
        var op = 0
        if (radioParamMedicaoCH1Med.isChecked) op = 1
        if (radioParamMedicaoCH2Med.isChecked) op = 2
        if (radioParamMedicaoDGTMed.isChecked) op = 3
        return op
    }

    private fun checkFlagInjecaoOp(): Int {
        var op = 0
        if (radioParamInjecaoDesativadoMed.isChecked) op = 0
        if (radioParamInjecaoAtivadoMed.isChecked) op = 1
        return op
    }

    private fun checkFlagRetornoOp(): Int {
        var op = 0
        if (radioParamRetornoDesativadoMed.isChecked) op = 0
        if (radioParamRetornoAtivadoMed.isChecked) op = 1
        return op
    }

    /**
     * Verifica qual foi a opção selecionada no Spinner(Dispositivo)
     */
    private fun selectedOptionSpinner(
        adapter: ArrayAdapter<String>,
        devices: Array<String>,
    ) {
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                opSpinner = devices[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    /**
     * Verifica qual comando tem que ser enviado
     */
    private fun checkSpinnerOp(op: String): Int {
        val status: EnumDefinesTestTypeMeasurement = EnumDefinesTestTypeMeasurement.valueOf(op)
        return status.getLocalizedCommand()
    }

}