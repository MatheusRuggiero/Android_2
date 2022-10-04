package br.com.tecnomotor.marvin.view.injector.tests

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import br.com.tecnomotor.commonrail.device.commands.injector.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.controller.IViewController
import br.com.tecnomotor.marvin.controller.test.demo.RotationControllerDemo
import br.com.tecnomotor.marvin.controller.test.demo.RotationResultDemo
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.injector.PlanTestInjector
import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.utils.objects.FormatTestResults
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.injector.InjectorActivity
import com.github.mikephil.charting.charts.PieChart
import kotlinx.coroutines.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
class RotationInjectorFragment : Fragment(), IViewController {

    private var tagLog: String = this::class.java.simpleName
    private var pieChart: PieChart? = null
    private lateinit var groupPieChart: Group
    private var root: View? = null
    private var injector: Injector? = null
    private var platform: Platform? = null
    private var plan: PlanTestInjector? = null
    private lateinit var resultElectricalTest: ElectricalTestResult
    private lateinit var listPoint: MutableList<PointTestInjector>
    private lateinit var buttonRotationCancel: ImageButton
    private lateinit var buttonRotationFinalize: ImageButton
    private lateinit var groupButtonRotationCancel: Group
    private lateinit var groupButtonRotationFinalize: Group
    private lateinit var txtDesiredRotationRpm: TextView
    private lateinit var txtTemperature: TextView
    private lateinit var txtStatus: TextView
    private lateinit var txtError: TextView
    private var MAX_ROTATION_VALUE = 1000f

    private lateinit var rotationInfo: RotationControllerDemo

    private val controller by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_rotation_injector, container, false)
        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startVariable(view)

        super.onViewCreated(view, savedInstanceState)
        println("Argumentos: ${controller.graph.arguments.size}")
        controller.graph.arguments.forEach {
            println("Argumento[${it.key}]")
        }
        println("FragmentArgs: ${this.arguments?.size()}")
        this.arguments.let {
            println(it.toString())
        }
        val args = this.arguments
        if ((args != null) && (args.size() > 0)) {
            injector = args[Extra.injector] as Injector
//            plan = args[Extra.plan] as PlanTestInjector
//            platform = args[Extra.platform] as Platform
            listPoint = args[Extra.points] as MutableList<PointTestInjector>
            resultElectricalTest = args[Extra.resultElectrical] as ElectricalTestResult
            platform = args[Extra.platform] as Platform
        }

        rotationInfo = RotationControllerDemo(MAX_ROTATION_VALUE.toLong(), this)
        rotationInfo.resultado.observeForever { setValues(it) }
    }

    /**
     * Configurações do "Gauge View"
     */
    fun setValues(it: RotationResultDemo) {
        txtStatus.text = it.status.getLocalizedMessage(requireContext())
        txtTemperature.text = FormatTestResults.FORMAT_TEMP_CELCIUS.format(it.temperature)
        GlobalUtil.method.settingValuePieChart(pieChart!!, context, MAX_ROTATION_VALUE, it.value.toFloat(), FormatTestResults.FORMAT_RPM.format(it.value.toInt()))

        if (it.status == EnumTestStatus.TEST_FAIL) {
            txtError.text = it.error?.getLocalizedMessage(requireContext())
            groupButtonRotationCancel.visibility = View.GONE
            groupButtonRotationFinalize.visibility = View.VISIBLE
            groupPieChart.visibility = View.GONE
        }

        if (it.status == EnumTestStatus.TEST_FINISHED) {
            rotationInfo.skipTest()
            GlobalScope.launch(Dispatchers.Main) {
                nextPoint()
            }
        }
    }

    /**
     * Redirecionar para a "View" de teste estanqueidade do injetor
     */
    private fun redirectTestTightnessInjector() {
        val args = Bundle()
        args.putSerializable(Extra.injector, injector)
        args.putSerializable(Extra.platform, platform)
        args.putSerializable(Extra.points, ArrayList<PointTestInjector>(listPoint))
//        args.putSerializable(Extra.plan, plan)
//        args.putSerializable(Extra.platform, platform)
        args.putSerializable(Extra.resultElectrical, resultElectricalTest)
        Navigation.findNavController(requireView()).navigate(R.id.action_rotationInjectorFragment_to_testTightnessInjector, args)
    }

    private fun inicializeGraphValues() {
        txtDesiredRotationRpm.text = FormatTestResults.FORMAT_RPM.format(MAX_ROTATION_VALUE.toInt())
        GlobalUtil.method.settingValuePieChart(
            pieChart!!,
            context,
            MAX_ROTATION_VALUE,
            0f,
            ""
        )
    }

    private fun startVariable(view: View) {
        groupButtonRotationCancel = view.findViewById(R.id.groupButtonRotationCancel)
        groupButtonRotationFinalize = view.findViewById(R.id.groupButtonRotationFinalize)
        groupPieChart = view.findViewById(R.id.pieChartGroup)
        buttonRotationCancel = view.findViewById(R.id.btn_rotation_cancel)
        buttonRotationFinalize = view.findViewById(R.id.btn_finalize_rotation)
        txtDesiredRotationRpm = view.findViewById(R.id.value_pressure_point_test)
        txtTemperature = view.findViewById(R.id.txt_value_temp_rotation_injector)
        txtStatus = view.findViewById(R.id.value_status)
        txtError = view.findViewById(R.id.value_error)
        pieChart = view.findViewById(R.id.gauge)
        groupButtonRotationCancel.visibility = View.VISIBLE
        groupButtonRotationFinalize.visibility = View.GONE
        groupPieChart.visibility = View.VISIBLE
        inicializeGraphValues()
        cancelRotationEvent()
        returnRotationEvent()
    }

    /**
     * Cancelar Rotação
     */
    private fun cancelRotationEvent() {
        buttonRotationCancel.setOnClickListener {
            rotationInfo.cancelTest()
            redirectListInjector()
        }
    }

    /**
     * Retorna para lista de injetorees
     */
    private fun returnRotationEvent() {
        buttonRotationFinalize.setOnClickListener {
            rotationInfo.cancelTest()
            redirectListInjector()
        }
    }

//    /**
//     * Avança tela
//     */
//    private fun okRotationInjector() {
//        buttonRotationFinalize.setOnClickListener {
//            rotationInfo.cancelTest() // TODO: verificar se deve ser isso aqui mesmo
//            rotationInfo.finishTest() // TODO: verificar se deve ser isso aqui mesmo
//            redirectTestTightnessInjector()
//        }
//    }

    /**
     * Redireciona para a "view" de lista de injetores
     */
    private fun redirectListInjector() {
        val intent = Intent(activity, InjectorActivity::class.java)
        intent.putExtra(Extra.platform, platform)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        intent.putExtra(Extra.platform, platform)
        startActivity(intent)
        activity?.finish()
    }

    override suspend fun nextPoint() {
        Log.w(tagLog, "NextPoint")
        redirectTestTightnessInjector()
    }
}