package br.com.tecnomotor.marvin.view.unit_test.injector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.UnitTestInjectorTestViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
class UnitTestInjectorTestFragment : Fragment() {
    private val viewModel: UnitTestInjectorTestViewModel by viewModels()

    private val tagLog: String = this::class.java.simpleName
    private lateinit var root: View

    //Componentes View (UI) 
    private lateinit var btnHwTestStartInj: Button
    private lateinit var btnHwTestParamInj: Button
    private lateinit var btnHwTestStopInj: Button
    private lateinit var btnHwTestSkipInj: Button
    private lateinit var btnHwTestCancelInj: Button
    private lateinit var txtHwTestInjTimeProcess: TextView
    private lateinit var txtHwTestInjPressure: TextView
    private lateinit var txtHwTestInjRotation: TextView
    private lateinit var txtHwTestInjTemp: TextView
    private lateinit var txtHwTestInjVBooster: TextView
    private lateinit var txtHwTestInjCorrentePiezo: TextView
    private lateinit var txtHwTestInjTensaoPiezo: TextView
    private lateinit var txtHwTestStatusInj: TextView
    private lateinit var txtHWTestErrorInj: TextView
    private lateinit var editHwTestInjTempoBIco: EditText
    private lateinit var editHwTestInjPressureTest: EditText
    private lateinit var editHwTestInjFrequency: EditText
    private lateinit var editHwTestInjChopperOn: EditText
    private lateinit var editHwTestInjChopperOff: EditText
    private lateinit var editHwTestInjTempoInjecao: EditText
    private lateinit var editHwTestInjPressureHoldOff: EditText
    private lateinit var editHwTestInjTensaoAlta: EditText
    private lateinit var editHwTestInjRefPatamar: EditText
    private lateinit var editHwTestInjRefCharge: EditText
    private lateinit var editHwTestInjRefDischarge: EditText
    private lateinit var editHwTestInjVarTest: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_unit_test_inj_piezo, container, false)

        setupUI()
        setupButton()

        return root
    }

    private fun setupUI() {
        btnHwTestParamInj = root.findViewById(R.id.btn_HW_test_inj_param)
        btnHwTestStartInj = root.findViewById(R.id.btn_HW_test_inj_start)
        btnHwTestStopInj = root.findViewById(R.id.btn_HW_test_inj_stop)
        btnHwTestSkipInj = root.findViewById(R.id.btn_HW_test_inj_skip)
        btnHwTestCancelInj = root.findViewById(R.id.btn_HW_test_inj_cancel)
        txtHwTestInjTimeProcess = root.findViewById(R.id.txt_value_HW_test_inj_time_process)
        txtHwTestInjPressure = root.findViewById(R.id.txt_value_HW_test_inj_pressure)
        txtHwTestInjRotation = root.findViewById(R.id.txt_value_HW_test_inj_rotation)
        txtHwTestInjTemp = root.findViewById(R.id.txt_value_HW_test_inj_temp)
        txtHwTestInjVBooster = root.findViewById(R.id.txt_value_HW_test_inj_vBooster)
        txtHwTestInjCorrentePiezo = root.findViewById(R.id.txt_value_HW_test_inj_corrente_piezo)
        txtHwTestInjTensaoPiezo = root.findViewById(R.id.txt_value_HW_test_inj_tensao_piezo)
        txtHwTestStatusInj = root.findViewById(R.id.txt_value_HW_test_inj_status)
        txtHWTestErrorInj = root.findViewById(R.id.txt_value_HW_test_inj_error)

        editHwTestInjTempoBIco = root.findViewById(R.id.edit_HW_Test_inj_tempo_teste_bico)
        editHwTestInjPressureTest = root.findViewById(R.id.edit_HW_test_inj_pressao_test)
        editHwTestInjFrequency = root.findViewById(R.id.edit_HW_test_inj_frequencia)
        editHwTestInjChopperOn = root.findViewById(R.id.edit_HW_test_inj_chopper_on)
        editHwTestInjChopperOff = root.findViewById(R.id.edit_HW_test_inj_off)
        editHwTestInjTempoInjecao = root.findViewById(R.id.edit_HW_test_inj_injecao)
        editHwTestInjPressureHoldOff = root.findViewById(R.id.edit_HW_test_inj_hold_off)
        editHwTestInjTensaoAlta = root.findViewById(R.id.edit_HW_test_inj_tensao_alta)
        editHwTestInjRefPatamar = root.findViewById(R.id.edit_HW_test_inj_ref_patamar)
        editHwTestInjRefCharge = root.findViewById(R.id.edit_HW_test_inj_ref_charge)
        editHwTestInjRefDischarge = root.findViewById(R.id.edit_HW_test_inj_ref_discharge)
        editHwTestInjVarTest = root.findViewById(R.id.edit_HW_test_inj_var)

    }

    /**
     * Button Actions
     */
    private fun setupButton() {
        btnHwTestParamInj.setOnClickListener {

            val editArray = arrayOf(//Colocando os editText num array só para poder iterar
                editHwTestInjTempoBIco,
                editHwTestInjPressureTest,
                editHwTestInjFrequency,
                editHwTestInjChopperOn,
                editHwTestInjChopperOff,
                editHwTestInjTempoInjecao,
                editHwTestInjPressureHoldOff,
                editHwTestInjTensaoAlta,
                editHwTestInjRefPatamar,
                editHwTestInjRefCharge,
                editHwTestInjRefDischarge,
                editHwTestInjVarTest
            )

            val param = Array(editArray.size) { 0 }

            // atualiza os valores do param com os valores definidos em fragment_unit_test_inj_piezo.xml
            for((index,i) in editArray.withIndex())
                if (i.text.toString().isNotEmpty()) // mantém zero no param quando campo está vazio
                    param[index] = i.text.toString().toFloat().toInt()             

            println("Parametros: " + param.contentToString())
            viewModel.testParamInj(param)
        }

        btnHwTestStartInj.setOnClickListener {
            viewModel.testStartInj()
        }

        btnHwTestStopInj.setOnClickListener {
            viewModel.testStopInj()
        }

        btnHwTestSkipInj.setOnClickListener {
            viewModel.testSkipInj()
        }

        btnHwTestCancelInj.setOnClickListener {
            viewModel.testCancelInj()
        }
    }
}