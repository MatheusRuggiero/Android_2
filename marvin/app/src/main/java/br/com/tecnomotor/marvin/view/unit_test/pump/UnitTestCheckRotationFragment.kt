package br.com.tecnomotor.marvin.view.unit_test.pump

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
class UnitTestCheckRotationFragment : Fragment() {

    private val tagLog: String = this::class.java.simpleName
    private lateinit var viewModel: PumpTestUnitViewModel

    private lateinit var root: View
    private lateinit var btnHwCheckRotationParam: Button
    private lateinit var btnHWCheckRotationStart: Button
    private lateinit var btnHwCheckRotationYes: Button
    private lateinit var btnHwCheckRotationNo: Button
    private lateinit var btnHwCheckRotationRet: Button
    private lateinit var btnHwCheckRotationError: Button
    private lateinit var btnHwCheckRotationSkip: Button
    private lateinit var editHwRotation: EditText
    private lateinit var editHwTimeOut: EditText
    private lateinit var txtHwCheckRotationStatus: TextView
    private lateinit var txtHwCheckRotationError: TextView
    private lateinit var txtHwCheckRotationDesired: TextView
    private lateinit var txtHwCheckRotation: TextView
    private lateinit var txtHwCheckRotationTemp: TextView
    private lateinit var txtHwCheckRotationTimeout: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PumpTestUnitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_unit_test_check_rotation, container, false)

        setupUI()
        setupButton()
        setupViewModel()

        return root
    }

    private fun setupUI() {
        btnHwCheckRotationParam = root.findViewById(R.id.btn_HW_check_rotation_param)
        btnHWCheckRotationStart = root.findViewById(R.id.btn_HW_check_rotation_start)
        btnHwCheckRotationYes = root.findViewById(R.id.btn_HW_check_rotation_yes)
        btnHwCheckRotationNo = root.findViewById(R.id.btn_HW_check_rotation_no)
        btnHwCheckRotationRet = root.findViewById(R.id.btn_HW_check_rotation_ret)
        btnHwCheckRotationError = root.findViewById(R.id.btn_HW_check_rotation_error)
        btnHwCheckRotationSkip = root.findViewById(R.id.btn_HW_check_rotation_skip)
        editHwRotation = root.findViewById(R.id.edt_HW_check_rotation)
        editHwTimeOut = root.findViewById(R.id.edt_HW_timeout)
        txtHwCheckRotationStatus = root.findViewById(R.id.txt_value_check_HW_rotation_status)
        txtHwCheckRotationError = root.findViewById(R.id.txt_value_check_HW_rotation_error)
        txtHwCheckRotationDesired = root.findViewById(R.id.txt_value_check_HW_rotation_desired)
        txtHwCheckRotation = root.findViewById(R.id.txt_value_check_HW_rotation)
        txtHwCheckRotationTemp = root.findViewById(R.id.txt_value_check_HW_rotation_temp)
        txtHwCheckRotationTimeout = root.findViewById(R.id.txt_value_check_HW_rotation_timeout)
    }

    /**
     * Button Actions
     */
    private fun setupButton() {
        btnHwCheckRotationParam.setOnClickListener {
            val rotation = editHwRotation.text.toString()
            val timeout = editHwTimeOut.text.toString()
            viewModel.sendCheckRotationParam(rotation, timeout)
        }

        btnHWCheckRotationStart.setOnClickListener {
            viewModel.sendCheckRotationStart()
        }

        btnHwCheckRotationYes.setOnClickListener {
            viewModel.sendKeyYes()
        }

        btnHwCheckRotationNo.setOnClickListener {
            viewModel.sendKeyNo()
        }

        btnHwCheckRotationRet.setOnClickListener {
            viewModel.sendKeyRet()
        }

        btnHwCheckRotationError.setOnClickListener {
            viewModel.sendKeyError()
        }

        btnHwCheckRotationSkip.setOnClickListener {
            viewModel.sendKeyF4()
        }
    }

    private fun setupViewModel() {
        viewModel.commandToDeviceControlValues.observe(viewLifecycleOwner) {
            //updateControlValues(it)
//            Log.d(tagLog, "Chegou aqui")
        }
        viewModel.commandToDeviceMeasurementValues.observe(viewLifecycleOwner) {
//            //updateMeasuresValues(it)
//            Log.d(tagLog, "Chegou aqui 2")
        }
        viewModel.rotationResultValues.observe(viewLifecycleOwner) {
            updateScreenValues(it)
        }
    }

    /**
     * Exibir informações na View
     */
    private fun updateScreenValues(values: RotationResult) {
        txtHwCheckRotationStatus.text = values.status.getLocalizedMessage(requireActivity().application)
        txtHwCheckRotationError.text = values.error.getLocalizedMessage(requireActivity().application)
        txtHwCheckRotationDesired.text = values.desiredRotation.toString()
        txtHwCheckRotation.text = values.rotation.toString()
        txtHwCheckRotationTemp.text = values.temperature.toString()
        txtHwCheckRotationTimeout.text = values.timeout.toString()
    }
}
