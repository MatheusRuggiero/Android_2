package br.com.tecnomotor.marvin.view.unit_test.pump

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.commands.pump.result.SingleElectricalTestResult
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
class UnitTestElectricalPumpFragment : Fragment() {

    private val tagLog: String = this::class.java.simpleName

    private  lateinit var viewModel:PumpTestUnitViewModel

    private lateinit var root: View
    private lateinit var btnHwElectricalDRV1: Button
    private lateinit var btnHwElectricalDRV2: Button
    private lateinit var btnHwElectricalEXT1: Button
    private lateinit var btnHwElectricalEXT2: Button
    private lateinit var txtHwElectricalResistance: TextView
    private lateinit var txtHwElectricalCondicao: TextView
    private lateinit var txtHWElectricalEstado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PumpTestUnitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_unit_test_electrical, container, false)

        setupUI()
        setupButton()
        setupViewModel()

        return root
    }

    fun setupUI() {
        btnHwElectricalDRV1 = root.findViewById(R.id.btn_HW_electric_teste_drv1)
        btnHwElectricalDRV2 = root.findViewById(R.id.btn_HW_electric_teste_drv2)
        btnHwElectricalEXT1 = root.findViewById(R.id.btn_HW_electric_teste_ext1)
        btnHwElectricalEXT2 = root.findViewById(R.id.btn_HW_electric_teste_ext2)
        txtHwElectricalResistance = root.findViewById(R.id.txt_value_HW_electrical_test_resistencia)
        txtHwElectricalCondicao = root.findViewById(R.id.txt_value_HW_electrical_test_condition)
        txtHWElectricalEstado = root.findViewById(R.id.txt_value_HW_electrical_test_status)
    }

    /**
     * Button Actions
     */
    private fun setupButton() {
        btnHwElectricalDRV1.setOnClickListener { viewModel.drv1() }
        btnHwElectricalDRV2.setOnClickListener { viewModel.drv2() }
        btnHwElectricalEXT1.setOnClickListener { viewModel.ext1() }
        btnHwElectricalEXT2.setOnClickListener { viewModel.ext2() }
    }

    fun setupViewModel() {
        viewModel.commandToDeviceControlValues.observe(viewLifecycleOwner) {
            //updateControlValues(it)
//            Log.d(tagLog, "Chegou aqui")
        }
        viewModel.commandToDeviceMeasurementValues.observe(viewLifecycleOwner) {
//            //updateMeasuresValues(it)
//            Log.d(tagLog, "Chegou aqui 2")
        }
        viewModel.resultTestElectric.observe(viewLifecycleOwner) {
            updateScreenValues(it)
        }
    }

    /**
     * Exibir informações na View
     */
    private fun updateScreenValues(values: SingleElectricalTestResult) {
        txtHwElectricalResistance.text = values.resistance.toString()
        txtHwElectricalCondicao.text = values.condition.getLocalizedMessage(requireActivity().application)
        txtHWElectricalEstado.text = values.status.getLocalizedMessage(requireActivity().application)
    }
}
