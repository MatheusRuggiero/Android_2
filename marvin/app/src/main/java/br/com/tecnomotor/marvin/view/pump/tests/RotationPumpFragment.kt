package br.com.tecnomotor.marvin.view.pump.tests

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import br.com.tecnomotor.commonrail.device.commands.pump.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.commands.result.RotationResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.config.RotationTestConfig
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.model.pump.PumpPlanTest
import br.com.tecnomotor.marvin.model.pump.TypeReferencePump
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.utils.objects.FormatTestResults
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.pump.PumpActivity
import br.com.tecnomotor.marvin.view.pump.viewmodel.RotationTestViewModel
import br.com.tecnomotor.marvin.view.pump.viewmodel.factory.RotationTestViewModelFactory
import com.github.mikephil.charting.charts.PieChart
import kotlinx.coroutines.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
class RotationPumpFragment : Fragment() {

    private val minTemperatureDesired = 38
    private val maxTemperatureDesired = 42
    private val minPressureDesired = 1f
    private val maxPressureDesired = 5f
    private var maxRotationValue = 4000f
    private var rotationDirection = ""
    private var tagLog: String = this::class.java.simpleName
    private var root: View? = null
    private var pump: Pump? = null
    private var plan: PumpPlanTest? = null
    private var typeReferencePump: TypeReferencePump? = null
    private lateinit var platform: Platform
    private lateinit var resultElectricalTest: ElectricalTestResult
    private lateinit var listPointTestPumpTest: MutableList<PointTestPump>

    //Components View
    private lateinit var buttonRotationCancel: ImageButton
    private lateinit var buttonRotationSkip: ImageButton
    private lateinit var buttonRotationFinalize: ImageButton
    private lateinit var buttonRotationRepeat: ImageButton
    private lateinit var groupButtonRotationCancel: Group
    private lateinit var groupButtonRotationSkip: Group
    private lateinit var groupButtonRotationFinalize: Group
    private lateinit var groupButtonRotationRepeat: Group
    private lateinit var txtDesiredRotationRpm: TextView
    private lateinit var txtTemperature: TextView
    private lateinit var txtStatus: TextView
    private lateinit var txtError: TextView
    private lateinit var txtRotationInstruction2: TextView
    private lateinit var txtRotationInstruction3: TextView
    private lateinit var txtRotationInstruction4: TextView
    private lateinit var txtTitleTemp: TextView
    private var pieChart: PieChart? = null
    private lateinit var groupPieChart: Group

    private val viewModel by lazy {
        val factory = RotationTestViewModelFactory()
        val vm = ViewModelProvider(this, factory)[RotationTestViewModel::class.java]
        vm.rotationResultLiveData.observe(this) { rotationResult ->
            setValues(rotationResult)
        }
        vm
    }

    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        root = inflater.inflate(R.layout.fragment_rotation_pump, container, false)
        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startVariable(view)

        super.onViewCreated(view, savedInstanceState)
        println("Arguments: ${navController.graph.arguments.size}")
        navController.graph.arguments.forEach {
            println("Argument[${it.key}]")
        }
        println("FragmentArgs: ${this.arguments?.size()}")
        this.arguments.let {
            println(it.toString())
        }
        val args = this.arguments
        if ((args != null) && (args.size() > 0)) {
            pump = args[Extra.pump] as Pump
            plan = args[Extra.plan] as PumpPlanTest
            typeReferencePump = args[Extra.typeReferencePump] as TypeReferencePump
            platform = args[Extra.platform] as Platform
            listPointTestPumpTest = args[Extra.points] as MutableList<PointTestPump>
            resultElectricalTest = args[Extra.resultElectrical] as ElectricalTestResult
        }
        viewModel.startTest()

        /**
         * TODO - Essa função será utilizado no futuro.
         * TODO - Ainda é preciso fazer a tratativa de erros no firmware.
         */
        //Verifica se será feita a verificação de rotação
//        val config = RotationTestConfig.getInstance()
//        if (config.getCheckRotation()) {
//            viewModel.startTest()
//        } else {
//            hideComponentViewNotRotation()
//            viewModel.startTestNotRotation()
//        }
    }

    /**
     * Settings "Gauge View" and instructions
     */
    private fun setValues(it: RotationResult) {
        rotationDirection = if (pump?.directionRotating!!) "DIREITO" else "ESQUERDO" //Verifica qual o sentido de giro da bomba selecionada

        txtStatus.text = it.status.getLocalizedMessage(requireContext())
        txtTemperature.text = FormatTestResults.FORMAT_TEMP_CELCIUS.format(it.temperature)
        txtDesiredRotationRpm.text = FormatTestResults.FORMAT_RPM.format(it.desiredRotation)
        txtRotationInstruction2.text = Html.fromHtml(getString(R.string.txt_info_rotacao_pump_02, listPointTestPumpTest[0].pressureFeed.toString()))
        txtRotationInstruction3.text = Html.fromHtml(getString(R.string.txt_info_rotacao_03, rotationDirection, it.desiredRotation)) // a versão mais nova do fromHtml não funciona no Android 6 do display de teste
        txtRotationInstruction4.text = Html.fromHtml(getString(R.string.txt_info_rotacao_04, minTemperatureDesired, maxTemperatureDesired))
        GlobalUtil.method.settingValuePieChart(pieChart!!, context, maxRotationValue, it.rotation.toFloat(), FormatTestResults.FORMAT_RPM.format(it.rotation.toInt()))
        when(it.status) {
            EnumTestStatus.TEST_FAIL, EnumTestStatus.TEST_STOPPED -> {
                txtError.text = it.error.getLocalizedMessage(requireContext())
                groupButtonRotationCancel.visibility = View.GONE
                groupButtonRotationSkip.visibility = View.VISIBLE
                groupButtonRotationFinalize.visibility = View.VISIBLE
                groupButtonRotationRepeat.visibility = View.VISIBLE
                groupPieChart.visibility = View.GONE
                // o firmware não atualiza o status para skipped quando há falha e tenta pular o teste
                // por isso o status skipped é alterado no controldor e enviado para o view model enviar para o fragment
            }
            EnumTestStatus.TEST_CANCELLED -> {
                redirectListPump()
            }
            EnumTestStatus.TEST_SKIPPED,
            EnumTestStatus.TEST_FINISHED -> {
                redirectPointTestPump()
            }
            else -> {}
        }
    }

    /**
     * Redirecionar para a "View" de pontos de teste de bomba
     */
    private fun redirectPointTestPump() {
        GlobalScope.launch(Dispatchers.Main) {
            while (!viewModel.isFinished()) delay(100)
            val args = Bundle()
            args.putSerializable(Extra.pump, pump)
            args.putSerializable(Extra.points, ArrayList<PointTestPump>(listPointTestPumpTest))
            args.putSerializable(Extra.typeReferencePump, typeReferencePump)
            args.putSerializable(Extra.plan, plan)
            args.putSerializable(Extra.platform, platform)
            args.putSerializable(Extra.resultElectrical, resultElectricalTest)
            Navigation.findNavController(requireView())
                .navigate(R.id.action_rotationPumpFragment_to_pointTestPumpFragment, args)
        }
    }

    private fun inicializeGraphValues() {
        txtDesiredRotationRpm.text = ""
        GlobalUtil.method.settingValuePieChart(
            pieChart!!,
            context,
            maxRotationValue,
            0f,
            ""
        )
    }

    private fun startVariable(view: View) {
        findViewById(view)
        inicializaVisibilidade()
        inicializeGraphValues()
        cancelRotationEvent()
        returnRotationEvent()
        skipRotationEvent()
        repeatRotationEvent()
    }

    private fun findViewById(view: View) {
        groupButtonRotationCancel = view.findViewById(R.id.groupButtonRotationCancel)
        groupButtonRotationSkip = view.findViewById(R.id.groupButtonRotationSkip)
        groupButtonRotationFinalize = view.findViewById(R.id.groupButtonRotationFinalize)
        groupButtonRotationRepeat = view.findViewById(R.id.groupButtonRotationRepeat)
        groupPieChart = view.findViewById(R.id.pieChartGroup)
        buttonRotationCancel = view.findViewById(R.id.btn_rotation_cancel)
        buttonRotationSkip = view.findViewById(R.id.btn_skip_rotation)
        buttonRotationFinalize = view.findViewById(R.id.btn_finalize_rotation)
        buttonRotationRepeat = view.findViewById(R.id.btn_rotation_repeat)
        txtDesiredRotationRpm = view.findViewById(R.id.value_pressure_point_test)
        txtTemperature = view.findViewById(R.id.txt_value_temp_rotation_pump)
        txtStatus = view.findViewById(R.id.value_status)
        txtError = view.findViewById(R.id.value_error)
        txtRotationInstruction2 = view.findViewById(R.id.txt_info_rotation_02)
        txtRotationInstruction3 = view.findViewById(R.id.txt_info_rotation_03)
        txtRotationInstruction4 = view.findViewById(R.id.txt_info_rotation_04)
        txtTitleTemp = view.findViewById(R.id.txt_title_temp_pump)
        pieChart = view.findViewById(R.id.gauge)
    }

    private fun inicializaVisibilidade() {
        txtError.text = ""
        groupButtonRotationCancel.visibility = View.VISIBLE
        groupButtonRotationFinalize.visibility = View.GONE
        groupButtonRotationRepeat.visibility = View.GONE
        groupPieChart.visibility = View.VISIBLE
    }

    private fun hideComponentViewNotRotation() {
        txtError.text = ""
        groupButtonRotationCancel.visibility = View.VISIBLE
        groupButtonRotationSkip.visibility = View.VISIBLE
        groupButtonRotationFinalize.visibility = View.GONE
        groupButtonRotationRepeat.visibility = View.GONE
        txtStatus.visibility = View.GONE
        txtTitleTemp.visibility = View.GONE
        txtTemperature.visibility = View.GONE
        groupPieChart.visibility = View.GONE
    }

    /**
     * Cancelar Rotação
     */
    private fun cancelRotationEvent() {
        buttonRotationCancel.setOnClickListener {
            viewModel.cancelTest()
        }
    }

    /**
     * Retorna para lista de bombas
     */
    private fun returnRotationEvent() {
        buttonRotationFinalize.setOnClickListener {
            viewModel.cancelTest()
            redirectListPump()
        }
    }

    /**
     * Pular teste de rotação
     */
    private fun skipRotationEvent() {
        buttonRotationSkip.setOnClickListener {
            viewModel.skipTest()
        }
    }

    /**
     * Repeat Rotation Check
     */
    private fun repeatRotationEvent() {
        buttonRotationRepeat.setOnClickListener {
            viewModel.repeatTest()
            GlobalScope.launch(Dispatchers.Main) {
                delay(100) // não gostei disso, é para o status TEST_FAIL não influenciar no teste novo
                inicializaVisibilidade()
                inicializeGraphValues()
            }

        }
    }

    /**
     * Redireciona para a "view" de lista de bombas
     */
    private fun redirectListPump() {
        val intent = Intent(activity, PumpActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra(Extra.platform, platform)
        startActivity(intent)
        activity?.finish()
    }

}
