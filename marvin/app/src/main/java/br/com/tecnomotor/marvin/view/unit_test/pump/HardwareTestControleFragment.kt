package br.com.tecnomotor.marvin.view.unit_test.pump

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.commands.result.HardwareControlTestResult
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@DelicateCoroutinesApi
class HardwareTestControleFragment : Fragment() {

    private val tagLog: String = this::class.java.simpleName
    private lateinit var resultHwControlTest: HardwareControlTestResult
    private lateinit var root: View

    private lateinit var viewModel: PumpTestUnitViewModel

    /* Hardware Test Variables */
    private lateinit var btnHwTestOnBuzzer: Button
    private lateinit var btnHwTestOffBuzzer: Button
    private lateinit var btnHwTestOnLed01: Button
    private lateinit var btnHwTestOffLed01: Button
    private lateinit var btnHwTestOnLed02: Button
    private lateinit var btnHwTestOffLed02: Button
    private lateinit var btnHwTestOnLed03: Button
    private lateinit var btnHwTestOffLed03: Button
    private lateinit var btnHwTestOnLed04: Button
    private lateinit var btnHwTestOffLed04: Button
    private lateinit var btnHwTestOnLed05: Button
    private lateinit var btnHwTestOffLed05: Button
    private lateinit var btnHwTestOnLed06: Button
    private lateinit var btnHwTestOffLed06: Button
    private lateinit var btnHwAllOff: Button
    private lateinit var btnHwParamAnalog: Button
    private lateinit var allButtonsGroup: Group
    private lateinit var allTextViewGroup: Group
    private lateinit var editHwParamDuty: EditText
    private lateinit var editHwParamPwm: EditText
    private lateinit var editHwParamChannel: EditText

    /* Analog Test Variables */
    private lateinit var btnStartAnalogTest: Button
    private lateinit var anPressaoTransf: TextView
    private lateinit var anTensDac: TextView
    private lateinit var anCorrMag2: TextView
    private lateinit var anTensPz: TextView
    private lateinit var anCorrMag1: TextView
    private lateinit var anCorrPz: TextView
    private lateinit var anCorrDrv1: TextView
    private lateinit var anCorrDrv2: TextView
    private lateinit var anTensFbInj: TextView
    private lateinit var anFbBooster: TextView
    private lateinit var anSensTemp1: TextView
    private lateinit var anSensTemp2: TextView
    private lateinit var anPressaoTeste: TextView
    private lateinit var anPressaoRail: TextView
    private lateinit var anCorrExt1: TextView
    private lateinit var anCorrExt2: TextView
    private lateinit var anRotat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PumpTestUnitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_hardware_controle_test, container, false)

        setupUI()
        setupButton()
        setupViewModel()

        return root
    }

    private fun setupUI() {
        btnHwTestOnBuzzer = root.findViewById(R.id.btnHwTestOnBuzzer)
        btnHwTestOffBuzzer = root.findViewById(R.id.btnHwTestOffBuzzer)
        btnHwTestOnLed01 = root.findViewById(R.id.btnHwTestOnLed01)
        btnHwTestOffLed01 = root.findViewById(R.id.btnHwTestOffLed01)
        btnHwTestOnLed02 = root.findViewById(R.id.btnHwTestOnLed02)
        btnHwTestOffLed02 = root.findViewById(R.id.btnHwTestOffLed02)
        btnHwTestOnLed03 = root.findViewById(R.id.btnHwTestOnLed03)
        btnHwTestOffLed03 = root.findViewById(R.id.btnHwTestOffLed03)
        btnHwTestOnLed04 = root.findViewById(R.id.btnHwTestOnLed04)
        btnHwTestOffLed04 = root.findViewById(R.id.btnHwTestOffLed04)
        btnHwTestOnLed05 = root.findViewById(R.id.btnHwTestOnLed05)
        btnHwTestOffLed05 = root.findViewById(R.id.btnHwTestOffLed05)
        btnHwTestOnLed06 = root.findViewById(R.id.btnHwTestOnLed06)
        btnHwTestOffLed06 = root.findViewById(R.id.btnHwTestOffLed06)
        btnHwAllOff = root.findViewById(R.id.btnAllHwOff)
        btnHwParamAnalog = root.findViewById(R.id.btnHwParamAnalog)
        allButtonsGroup = root.findViewById(R.id.allButtonsGroup)
        allTextViewGroup = root.findViewById(R.id.allTextViewGroup)
        editHwParamDuty = root.findViewById(R.id.editHwDuty)
        editHwParamPwm = root.findViewById(R.id.editHwPwm)
        editHwParamChannel = root.findViewById(R.id.editHwChannel)

        allButtonsGroup.visibility = View.VISIBLE
        allTextViewGroup.visibility = View.INVISIBLE

        /* Analog Test Variables */
        btnStartAnalogTest = root.findViewById(R.id.btnStartAnalogTest) as Button
        anPressaoTransf = root.findViewById(R.id.txtValueTestAnPressaoTransf) as TextView
        anTensDac = root.findViewById(R.id.txtValueTestAnTensDac) as TextView
        anCorrMag2 = root.findViewById(R.id.txtValueTestAnCorrMag2) as TextView
        anTensPz = root.findViewById(R.id.txtValueTestAnTensPz) as TextView
        anCorrMag1 = root.findViewById(R.id.txtValueTestAnCorrMag1) as TextView
        anCorrPz = root.findViewById(R.id.txtValueTestAnCorrPz) as TextView
        anCorrDrv1 = root.findViewById(R.id.txtValueTestAnCorrDrv1) as TextView
        anCorrDrv2 = root.findViewById(R.id.txtValueTestAnCorrDrv2) as TextView
        anTensFbInj = root.findViewById(R.id.txtValueTestAnTensFbInj) as TextView
        anFbBooster = root.findViewById(R.id.txtValueTestAnFbBooster) as TextView
        anSensTemp1 = root.findViewById(R.id.txtValueTestAnSensTemp1) as TextView
        anSensTemp2 = root.findViewById(R.id.txtValueTestAnSensTemp2) as TextView
        anPressaoTeste = root.findViewById(R.id.txtValueTestAnPressaoTeste) as TextView
        anPressaoRail = root.findViewById(R.id.txtValueTesteAnPressaoRail) as TextView
        anCorrExt1 = root.findViewById(R.id.txtValueTestAnCorrExt1) as TextView
        anCorrExt2 = root.findViewById(R.id.txtValueTestAnCorrExt2) as TextView
        anRotat = root.findViewById(R.id.txtValueTestAnRotat) as TextView

        enabledButtonLeds()
    }

    /**
     * Button Actions
     */
    private fun setupButton() {
        btnStartAnalogTest.setOnClickListener {
            Log.w(tagLog, "Start Controller Analog Test")
            viewModel.startAnalogTests()
            allTextViewGroup.visibility = View.VISIBLE
        }

        btnHwParamAnalog.setOnClickListener {
            Log.w(tagLog, "Start Param Analog Test")
            viewModel.paramAnalogTest(
                editHwParamChannel.text.toString(),
                editHwParamPwm.text.toString(),
                editHwParamDuty.text.toString()
            )
        }

        btnHwTestOffBuzzer.setOnClickListener {
            btnHwTestOffBuzzer.isEnabled = false
            btnHwTestOnBuzzer.isEnabled = true
            viewModel.offBuzzer()
        }
        btnHwTestOnBuzzer.setOnClickListener {
            btnHwTestOffBuzzer.isEnabled = true
            btnHwTestOnBuzzer.isEnabled = false
            viewModel.onBuzzer()
        }

        btnHwTestOnLed01.setOnClickListener {
            btnHwTestOnLed01.isEnabled = false
            btnHwTestOffLed01.isEnabled = true
            viewModel.onLed01()
        }

        btnHwTestOffLed01.setOnClickListener {
            btnHwTestOnLed01.isEnabled = true
            btnHwTestOffLed01.isEnabled = false
            viewModel.offLed01()
        }

        btnHwTestOnLed02.setOnClickListener {
            btnHwTestOnLed02.isEnabled = false
            btnHwTestOffLed02.isEnabled = true
            viewModel.onLed02()
        }

        btnHwTestOffLed02.setOnClickListener {
            btnHwTestOnLed02.isEnabled = true
            btnHwTestOffLed02.isEnabled = false
            viewModel.offLed02()
        }

        btnHwTestOnLed03.setOnClickListener {
            btnHwTestOnLed03.isEnabled = false
            btnHwTestOffLed03.isEnabled = true
            viewModel.onLed03()
        }

        btnHwTestOffLed03.setOnClickListener {
            btnHwTestOnLed03.isEnabled = true
            btnHwTestOffLed03.isEnabled = false
            viewModel.offLed03()
        }

        btnHwTestOnLed04.setOnClickListener {
            btnHwTestOnLed04.isEnabled = false
            btnHwTestOffLed04.isEnabled = true
            viewModel.onLed04()
        }

        btnHwTestOffLed04.setOnClickListener {
            btnHwTestOnLed04.isEnabled = true
            btnHwTestOffLed04.isEnabled = false
            viewModel.offLed04()
        }

        btnHwTestOnLed05.setOnClickListener {
            btnHwTestOnLed05.isEnabled = false
            btnHwTestOffLed05.isEnabled = true
            viewModel.onLed05()
        }

        btnHwTestOffLed05.setOnClickListener {
            btnHwTestOnLed05.isEnabled = true
            btnHwTestOffLed05.isEnabled = false
            viewModel.offLed05()
        }

        btnHwTestOnLed06.setOnClickListener {
            btnHwTestOnLed06.isEnabled = false
            btnHwTestOffLed06.isEnabled = true
            viewModel.onLed06()
        }

        btnHwTestOffLed06.setOnClickListener {
            btnHwTestOnLed06.isEnabled = true
            btnHwTestOffLed06.isEnabled = false
            viewModel.offLed06()
        }

        btnHwAllOff.setOnClickListener {
            disableAllDigitalOutputs()
        }
    }

    private fun setupViewModel() {
        viewModel.commandToDeviceControlValues.observe(viewLifecycleOwner) {
            resultHwControlTest = HardwareControlTestResult().ofByteArray(it.read)
            updateValuesTestAnalog()
        }
    }


    /**
     * Desliga todas as saídas digitais
     */
    private fun disableAllDigitalOutputs() {
        viewModel.offBuzzer()
        viewModel.offLed01()
        viewModel.offLed02()
        viewModel.offLed03()
        viewModel.offLed04()
        viewModel.offLed05()
        viewModel.offLed06()

        enabledButtonLeds()
    }

    /**
     * Desabilita os botões off e habilita os botões on
     */
    private fun enabledButtonLeds() {
        btnHwTestOffBuzzer.isEnabled = false
        btnHwTestOffLed01.isEnabled = false
        btnHwTestOffLed02.isEnabled = false
        btnHwTestOffLed03.isEnabled = false
        btnHwTestOffLed04.isEnabled = false
        btnHwTestOffLed05.isEnabled = false
        btnHwTestOffLed06.isEnabled = false

        btnHwTestOnBuzzer.isEnabled = true
        btnHwTestOnLed01.isEnabled = true
        btnHwTestOnLed02.isEnabled = true
        btnHwTestOnLed03.isEnabled = true
        btnHwTestOnLed04.isEnabled = true
        btnHwTestOnLed05.isEnabled = true
        btnHwTestOnLed06.isEnabled = true
    }


    /**
     *  Update Values Analog Test Information
     */
    private fun updateValuesTestAnalog() {
        anPressaoTransf.text = resultHwControlTest.anPressaoTransf.toString()
        anTensDac.text = resultHwControlTest.anTensDac.toString()
        anCorrMag2.text = resultHwControlTest.anCorrMag2.toString()
        anTensPz.text = resultHwControlTest.anTensPz.toString()
        anCorrMag1.text = resultHwControlTest.anCorrMag1.toString()
        anCorrPz.text = resultHwControlTest.anCorrPz.toString()
        anCorrDrv1.text = resultHwControlTest.anCorrDrv1.toString()
        anCorrDrv2.text = resultHwControlTest.anCorrDrv2.toString()
        anTensFbInj.text = resultHwControlTest.anTensFbInj.toString()
        anFbBooster.text = resultHwControlTest.anFbBooster.toString()
        anSensTemp1.text = resultHwControlTest.anSensTemp1.toString()
        anSensTemp2.text = resultHwControlTest.anSensTemp2.toString()
        anPressaoTeste.text = resultHwControlTest.anPressaoTeste.toString()
        anPressaoRail.text = resultHwControlTest.anPressaoRail.toString()
        anCorrExt1.text = resultHwControlTest.anCorrExt1.toString()
        anCorrExt2.text = resultHwControlTest.anCorrExt2.toString()
        anRotat.text = resultHwControlTest.anRotat.toString()
    }

}


