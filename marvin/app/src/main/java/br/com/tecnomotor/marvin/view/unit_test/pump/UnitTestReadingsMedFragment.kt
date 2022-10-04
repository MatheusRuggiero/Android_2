package br.com.tecnomotor.marvin.view.unit_test.pump

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.commands.result.MeasurementResult
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
class UnitTestReadingsMedFragment : Fragment() {

    private val tagLog: String = this::class.java.simpleName
    private lateinit var root: View

    private lateinit var tipoTesteCh1Value: TextView
    private lateinit var statusCh1Value: TextView
    private lateinit var erroCh1Value: TextView
    private lateinit var centroMassaCh1Value: TextView
    private lateinit var vazaoMlsCh1Value: TextView
    private lateinit var vazaoMlkstCh1Value: TextView
    private lateinit var coeficienteR2Ch1Value: TextView
    private lateinit var coeficienteLinearCh1Value: TextView
    private lateinit var vazioCh1Value: TextView
    private lateinit var fototransitoresCh1Value: TextView
    private lateinit var falhasCh1Value: TextView

    private lateinit var tipoTesteCh2Value: TextView
    private lateinit var statusCh2Value: TextView
    private lateinit var erroCh2Value: TextView
    private lateinit var centroMassaCh2Value: TextView
    private lateinit var vazaoMlsCh2Value: TextView
    private lateinit var vazaoMlkstCh2Value: TextView
    private lateinit var coeficienteR2Ch2Value: TextView
    private lateinit var coeficienteLinearCh2Value: TextView
    private lateinit var opacidadeFluidoValue: TextView
    private lateinit var fototransitorCh2Value: TextView
    private lateinit var falhasCh2Value: TextView

    private lateinit var tipoTesteCh3Value: TextView
    private lateinit var statusCh3Value: TextView
    private lateinit var erroCh3Value: TextView
    private lateinit var vazio51Ch3Value: TextView
    private lateinit var vazio52Ch3Value: TextView
    private lateinit var vazaoLhCh3Value: TextView
    private lateinit var volumeCh3Value: TextView

    private lateinit var viewModel: PumpTestUnitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PumpTestUnitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_unit_test_readings_med, container, false)

        setupUI()
        setupViewModel()

        return root
    }

    private fun setupUI() {
        // Channel 1
        tipoTesteCh1Value = root.findViewById(R.id.tipo_teste_ch1_value)
        statusCh1Value = root.findViewById(R.id.status_ch1_value)
        erroCh1Value = root.findViewById(R.id.erro_ch1_value)
        centroMassaCh1Value = root.findViewById(R.id.centro_massa_ch1_value)
        vazaoMlsCh1Value = root.findViewById(R.id.vazao_mls_ch1_value)
        vazaoMlkstCh1Value = root.findViewById(R.id.vazao_mlkst_ch1_value)
        coeficienteR2Ch1Value = root.findViewById(R.id.coeficiente_r2_value_ch1)
        coeficienteLinearCh1Value = root.findViewById(R.id.coeficiente_linear_ch1_value)
        vazioCh1Value = root.findViewById(R.id.vazio_ch1_value)
        fototransitoresCh1Value = root.findViewById(R.id.fototransitores_ch1_value)
        falhasCh1Value = root.findViewById(R.id.falhas_ch1_value)

        // Channel 2
        tipoTesteCh2Value = root.findViewById(R.id.tipo_teste_ch2_value)
        statusCh2Value = root.findViewById(R.id.status_ch2_value)
        erroCh2Value = root.findViewById(R.id.erro_ch2_value)
        centroMassaCh2Value = root.findViewById(R.id.centro_massa_ch2_value)
        vazaoMlsCh2Value = root.findViewById(R.id.vazao_mls_ch2_value)
        vazaoMlkstCh2Value = root.findViewById(R.id.vazao_mlkst_ch2_value)
        coeficienteR2Ch2Value = root.findViewById(R.id.coeficiente_r2_value_ch2)
        coeficienteLinearCh2Value = root.findViewById(R.id.coeficiente_linear_ch2_value)
        opacidadeFluidoValue = root.findViewById(R.id.opacidade_fluido_value)
        fototransitorCh2Value = root.findViewById(R.id.fototransitor_ch2_value)
        falhasCh2Value = root.findViewById(R.id.falhas_ch2_value)

        // Channel 3
        tipoTesteCh3Value = root.findViewById(R.id.tipo_teste_ch3_value)
        statusCh3Value = root.findViewById(R.id.status_ch3_value)
        erroCh3Value = root.findViewById(R.id.erro_ch3_value)
        vazio51Ch3Value = root.findViewById(R.id.vazio_51_ch3_value)
        vazio52Ch3Value = root.findViewById(R.id.vazio_52_ch3_value)
        vazaoLhCh3Value = root.findViewById(R.id.vazao_lh_ch3_value)
        volumeCh3Value = root.findViewById(R.id.volume_ch3_value)

    }

    private fun setupViewModel() {
        viewModel.commandToDeviceMeasurementValues.observe(viewLifecycleOwner) {

        }
        viewModel.measurementResultValues.observe(viewLifecycleOwner) {
            updateMeasuresValues(it)
        }
    }

    /**
     *  Update Values Hardware Test Information
     */
    @SuppressLint("SetTextI18n")
    private fun updateMeasuresValues(it: MeasurementResult) {
        tipoTesteCh1Value.text = it.testTypeCH1.getLocalizedMessage(requireContext())
        statusCh1Value.text = it.statusCH1.getLocalizedMessage(requireContext())
        erroCh1Value.text = it.errorCH1.getLocalizedMessage(requireContext())
        centroMassaCh1Value.text = it.centerOfMassCH1.toString()
        vazaoMlsCh1Value.text = "%.3f".format(it.flowMlsCH1)
        vazaoMlkstCh1Value.text = "%.3f".format(it.flowMlkCH1)
        coeficienteR2Ch1Value.text = it.coefRCH1.toString()
        coeficienteLinearCh1Value.text = it.linearCoefCH1.toString()
        vazioCh1Value.text = it.vazio22.toString()
        fototransitoresCh1Value.text = it.photoActivatedCH1.toString()
        falhasCh1Value.text = it.failureCounterCH1.toString()

        tipoTesteCh2Value.text = it.testTypeCH2.getLocalizedMessage(requireContext())
        statusCh2Value.text = it.statusCH2.getLocalizedMessage(requireContext())
        erroCh2Value.text = it.errorCH2.getLocalizedMessage(requireContext())
        centroMassaCh2Value.text = it.centerOfMassCH2.toString()
        vazaoMlsCh2Value.text = "%.3f".format(it.flowMlsCH2)
        vazaoMlkstCh2Value.text = "%.3f".format(it.flowMlkCH2)
        coeficienteR2Ch2Value.text = it.coefRCH2.toString()
        coeficienteLinearCh2Value.text = it.linearCoefCH2.toString()
        opacidadeFluidoValue.text = it.fluidOpacity.toString()
        fototransitorCh2Value.text = it.photoActivatedCH2.toString()
        falhasCh2Value.text = it.failureCounterCH2.toString()

        tipoTesteCh3Value.text = it.testTypeCH3.getLocalizedMessage(requireContext())
        statusCh3Value.text = it.statusCH3.getLocalizedMessage(requireContext())
        erroCh3Value.text = it.errorCH3.getLocalizedMessage(requireContext())
        vazio51Ch3Value.text = it.vazio52.toString()
        vazio52Ch3Value.text = it.vazio53.toString()
        vazaoLhCh3Value.text = "%.3f".format(it.flowLHCH3)
        volumeCh3Value.text = "%.3f".format(it.flowMlkCH3)
    }
}