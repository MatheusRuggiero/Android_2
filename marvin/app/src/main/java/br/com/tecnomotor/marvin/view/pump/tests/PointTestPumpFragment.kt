package br.com.tecnomotor.marvin.view.pump.tests

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.generic.pump.PointTestPumpExecuteGeneric
import br.com.tecnomotor.marvin.adapter.pump.PointTestPumpAdapter
import br.com.tecnomotor.marvin.controller.test.pump.TestPointsResult
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.model.pump.PumpPlanTest
import br.com.tecnomotor.marvin.model.pump.TypeReferencePump
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.utils.objects.FormatTestResults
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.pump.PumpActivity
import br.com.tecnomotor.marvin.view.pump.viewmodel.PointTestPumpViewModel
import br.com.tecnomotor.marvin.view.pump.viewmodel.factory.PointTestPumpViewModelFactory
import com.github.mikephil.charting.charts.PieChart
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class PointTestPumpFragment : Fragment() {

    private var tagLog = "PointTestPumpFragment"
    private lateinit var pump: Pump
    private lateinit var plan: PumpPlanTest
    private lateinit var typeReferencePump: TypeReferencePump
    private lateinit var platform: Platform
    private lateinit var listPointTestPumpTest: MutableList<PointTestPump>
    private lateinit var listPointTestPumpRecyclerView: RecyclerView
    private lateinit var txtCountTime: TextView
    private lateinit var frameRotationBtnCancel: ImageButton
    private lateinit var frameRotationBtnSkipRotation: ImageButton
    private lateinit var frameRotationBtnSkipRotationPointTest: ImageButton
    private lateinit var frameRotationBtnRepeatGrp: Group
    private lateinit var frameRotationBtnRepeat: ImageButton
    private lateinit var root: View
    private var MAX_ROTATION_VALUE = 4000f
    private lateinit var testPointResult: TestPointsResult

    //View (Button, Char, etc...) --->
    private lateinit var imgBtnFinish: ImageButton
    private lateinit var imgBtnSave: ImageButton
    private lateinit var imgBtnSkip: ImageButton
    private lateinit var imgBtnContinue: ImageButton
    private lateinit var imgBtnPause: ImageButton
    private lateinit var imgBtnCancel: ImageButton
    private lateinit var imgBtnComeBack: ImageView
    private lateinit var imgBtnForward: ImageView
    private lateinit var frameRotation: View
    private lateinit var frameRotationChart: PieChart
    private lateinit var frameRotationValue: TextView
    private lateinit var frameRotationTextInfo: TextView
    // --->

    private val adapter by lazy {
        PointTestPumpAdapter(activity?.applicationContext!!)
    }

    private val navController by lazy {
        findNavController()
    }

    private val viewModel by lazy {
        val factory = PointTestPumpViewModelFactory(listPointTestPumpTest, this::nextPoint, pump)
        val vm = ViewModelProvider(this, factory)[PointTestPumpViewModel::class.java]
        vm.pointTestPumpResultLiveData.observe(this) { testPointResult ->
            setValues(testPointResult)
        }
        vm
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_point_test_pump, container, false)
        configureRecyclerView()
        configureViews()
        configureVisibility()
        configureButtons()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = configureArgs()
        startProcess(args)
    }

    private fun configureButtons() {
        imgBtnCancel.setOnClickListener {
            viewModel.cancelTest()
            val statusTest = testPointResult.getItem().pointTestPumpResult.status
            if (testPointResult.allTestFinished
                || statusTest == EnumTestStatus.TEST_SKIPPED
                || statusTest == EnumTestStatus.TEST_FINISHED
                || statusTest == EnumTestStatus.TEST_PAUSED)
                returnMenu()
        }
        imgBtnPause.setOnClickListener {
            viewModel.pauseTest()
        }
        imgBtnContinue.setOnClickListener {
            viewModel.startTest()
        }
        imgBtnSkip.setOnClickListener {
            viewModel.skipTest()
        }
        imgBtnSave.setOnClickListener {
            imgBtnSave.visibility = View.GONE
            imgBtnCancel.visibility = View.GONE
            imgBtnFinish.visibility = View.VISIBLE
        }
        imgBtnFinish.setOnClickListener {
            returnMenu()
        }
        frameRotationBtnCancel.setOnClickListener {
            viewModel.cancelTest()
            finishTest(false)
            returnMenu()
        }
        /**
         * Pular verificação rotação
         */
        frameRotationBtnSkipRotation.setOnClickListener {
            viewModel.skipRotation()
        }
        /**
         * Pular ponto de teste durante a rotação
         */
        frameRotationBtnSkipRotationPointTest.setOnClickListener {
            viewModel.skipTest()
            finishTest(false)
        }
        /**
         * Exibir próximo ponto de teste após finalizados os testes
         */
        imgBtnForward.setOnClickListener {
            testPointResult.nextPoint()
            listPointTestPumpRecyclerView.scrollToPosition(testPointResult.indexSelected)
        }
        /**
         * Exibir ponto de teste anterior após finalizados os testes
         */
        imgBtnComeBack.setOnClickListener {
            testPointResult.priorPoint()
            listPointTestPumpRecyclerView.scrollToPosition(testPointResult.indexSelected)
        }
        /**
         * Repetir a verificação de rotação antes do ponto de teste
         */
        frameRotationBtnRepeat.setOnClickListener {
            viewModel.repeatRotation()
        }
    }

    private fun configureVisibility() {
        imgBtnForward.visibility = View.INVISIBLE
        imgBtnComeBack.visibility = View.INVISIBLE
    }

    private fun configureViews() {
        txtCountTime = root.findViewById(R.id.txt_time_test_pump)
        imgBtnFinish = root.findViewById(R.id.btn_finish_point_test_pump)
        imgBtnSave = root.findViewById(R.id.btn_save_point_test_pump)
        imgBtnSkip = root.findViewById(R.id.btn_skip_point_test_pump)
        imgBtnContinue = root.findViewById(R.id.btn_continue_point_test_pump)
        imgBtnPause = root.findViewById(R.id.btn_pause_point_test_pump)
        imgBtnCancel = root.findViewById(R.id.btn_cancel_point_test_pump)
        imgBtnForward = root.findViewById(R.id.img_forward)
        imgBtnComeBack = root.findViewById(R.id.img_come_back)
        frameRotation = root.findViewById(R.id.frame_rotation)
        frameRotationChart = frameRotation.findViewById(R.id.pieChartRotation)
        frameRotationValue = frameRotation.findViewById(R.id.value_rotation)
        frameRotationTextInfo = frameRotation.findViewById(R.id.txt_info_rotation)
        frameRotationBtnCancel = frameRotation.findViewById(R.id.btn_cancel_rotation)
        frameRotationBtnSkipRotation = frameRotation.findViewById(R.id.btn_rotation_test_skip_rotation)
        frameRotationBtnSkipRotationPointTest = frameRotation.findViewById(R.id.btn_rotation_test_skip_test_point)
        frameRotationBtnRepeatGrp = frameRotation.findViewById(R.id.groupButtonRotationRepeat)
        frameRotationBtnRepeat = frameRotation.findViewById(R.id.btn_repeat_rotation)
    }

    private fun startProcess(args: Bundle?) {
        if ((args != null) && (args.size() > 0)) {
            pump = args[Extra.pump] as Pump
            plan = args[Extra.plan] as PumpPlanTest
            typeReferencePump = args[Extra.typeReferencePump] as TypeReferencePump
            platform = args[Extra.platform] as Platform
            listPointTestPumpTest = args[Extra.points] as MutableList<PointTestPump>
            loadPointPump()
            viewModel.startTest()
        }
    }

    private fun configureArgs(): Bundle? {
        println("Argumentos: ${navController.graph.arguments.size}")
        navController.graph.arguments.forEach {
            println("Argumento[${it.key}]")
        }
        println("FragmentArgs: ${this.arguments?.size()}")
        this.arguments.let {
            println(it.toString())
        }
        return this.arguments
    }

    private fun returnMenu() {
        val intent = Intent(activity, PumpActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra(Extra.platform, platform)
        startActivity(intent)
        activity?.finish()
    }

    /**
     * Exibe botão para salvar teste
     */
    private fun finishTest(showToSaveTests: Boolean) {
        if (showToSaveTests) {
            imgBtnPause.visibility = View.GONE
            imgBtnContinue.visibility = View.GONE
            imgBtnSkip.visibility = View.GONE
            imgBtnSave.visibility = View.VISIBLE
        }
    }

    private fun finishTest() {
        this.finishTest(true)
    }

    private fun configureRecyclerView() {
        listPointTestPumpRecyclerView = root.findViewById(R.id.recyclerview_list_point_test_pump)
        val recyclerLayoutManager = object : LinearLayoutManager(root.context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        listPointTestPumpRecyclerView.layoutManager = recyclerLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            listPointTestPumpRecyclerView.context,
            recyclerLayoutManager.orientation
        )
        listPointTestPumpRecyclerView.addItemDecoration(dividerItemDecoration)
        listPointTestPumpRecyclerView.adapter = adapter
    }

    /**
     * Lista os pontos de bombas
     */
    private fun loadPointPump() {
        val list: MutableList<PointTestPumpExecuteGeneric> = arrayListOf()
        listPointTestPumpTest.forEach {
//            if (it.typePointTestPump?.getTypeTest() != PumpTestPointType.LEAK
//                && it.typePointTestPump?.getTypeTest() != PumpTestPointType.START)
//            {
                val pointTestPumpExecuteGeneric = PointTestPumpExecuteGeneric()
                pointTestPumpExecuteGeneric.pointTestPump = it
                list.add(pointTestPumpExecuteGeneric)
//            }
        }
        adapter.update(list)
    }

    @SuppressLint("SetTextI18n")
    private fun setValues(it: TestPointsResult) {
//        Log.i(tagLog, "setValues: $it")
        this.testPointResult = it
        txtCountTime.text = "%02d".format(it.testTime/60) + ":" + "%02d".format(it.testTime.mod(60))
        getPointTestExecute().statusPointValue?.text = it.getItem().pointTestPumpResult.status.getLocalizedMessage(requireContext())

        testErrorControl(it)
        testErrorMeasurement(it)

        if (getPointTestExecute().pieChartViewPointTestPumpExecuteGeneric != null)
            GlobalUtil.method.settingValuePieChart(
                getPointTestExecute().pieChartViewPointTestPumpExecuteGeneric!!,
                context,
                2000f,
                it.getItem().pointTestPumpResult.pressure.toFloat(),
                FormatTestResults.FORMAT_PRESSURE.format(it.getItem().pointTestPumpResult.pressure)
            )

//        getPointTestExecute().pressurePointValue?.text = FormatTestResults.FORMAT_PRESSURE.format(it.getItem().pointTestResult.desiredPressure)
        getPointTestExecute().flowValue?.text = FormatTestResults.FORMAT_LITERS_HOURS.format(it.getItem().pointTestPumpResult.mainFlow)
        if (getPointTestExecute().chartFlowPointTestPumpExecuteGeneric != null)
            GlobalUtil.method.settingValueBarChart(
                getPointTestExecute().chartFlowPointTestPumpExecuteGeneric!!,
                it.getItem().pointTestPumpResult.mainFlow.toFloat(),
                it.getItem().pointTestPumpResult.minimumMainFlow.toFloat(),
                it.getItem().pointTestPumpResult.maximumMainFlow.toFloat(),
                it.getItem().pointTest.getFlowMainMinimumGraph(),
                it.getItem().pointTest.getFlowMainMaximumGraph(),
                it.getItem().pointTestPumpResult.getVazaoPrincipalColor()
            )
        getPointTestExecute().returnValue?.text = FormatTestResults.FORMAT_LITERS_HOURS.format(it.getItem().pointTestPumpResult.returnFlow)
        if (getPointTestExecute().chartReturnPointTestPumpExecuteGeneric != null)
            GlobalUtil.method.settingValueBarChart(
                getPointTestExecute().chartReturnPointTestPumpExecuteGeneric!!,
                it.getItem().pointTestPumpResult.returnFlow.toFloat(),
                it.getItem().pointTestPumpResult.minimumReturnFlow.toFloat(),
                it.getItem().pointTestPumpResult.maximumReturnFlow.toFloat(),
                it.getItem().pointTest.getFlowReturnMinimumGraph(),
                it.getItem().pointTest.getFlowReturnMaximumGraph(),
                it.getItem().pointTestPumpResult.getVazaoRetornoColor()
            )
        //getPointTestExecute().pressureFeedValue?.text = FormatTestResults.FORMAT_PRESSURE_FEED.format(it.pressaoDeAlimentacao)
        getPointTestExecute().rotationValue?.text = FormatTestResults.FORMAT_RPM.format(it.getItem().pointTestPumpResult.rotation)
        getPointTestExecute().timePointValue?.text = FormatTestResults.FORMAT_TIME_TEST.format(it.getItem().pointTestPumpResult.testTime)
        getPointTestExecute().currentPointValueExt1?.text = FormatTestResults.FORMAT_AMP.format(it.getItem().pointTestPumpResult.Ext1Current)
        getPointTestExecute().currentPointValueExt2?.text = FormatTestResults.FORMAT_AMP.format(it.getItem().pointTestPumpResult.Ext2Current)
        getPointTestExecute().temperatureValue?.text = FormatTestResults.FORMAT_TEMP_CELCIUS.format(it.getItem().pointTestPumpResult.temperature)
        getPointTestExecute().ml100RotValue?.text = if (it.getItem().pointTestPumpResult.rotation <= 0) "---" else FormatTestResults.FORMAT_ML_100_ROT.format(it.getItem().pointTestPumpResult.getCalcFlowRotation())

        if (it.getItem().pointTestPumpResult.checkRotation) {
            if (frameRotation.visibility == View.GONE) {
                frameRotation.visibility = View.VISIBLE
                frameRotationBtnRepeatGrp.visibility = View.GONE
                disableButtons()
            }
            frameRotationValue.text = FormatTestResults.FORMAT_RPM.format(it.getItem().pointTestPumpResult.desiredRotation)
            frameRotationTextInfo.text = (getText(R.string.text_info_rotation) as String).format(it.getItem().pointTestPumpResult.desiredRotation)
            GlobalUtil.method.settingValuePieChart(
                frameRotationChart,
                context,
                MAX_ROTATION_VALUE,
                it.getItem().pointTestPumpResult.rotation.toFloat(),
                FormatTestResults.FORMAT_RPM.format(it.getItem().pointTestPumpResult.rotation)
            )
        } else {
            if (frameRotation.visibility == View.VISIBLE) {
                frameRotation.visibility = View.GONE
                enableButtons()
            }
        }

        checkStatusTest(it)
    }

    /**
     * Erros da Placa de Medição
     */
    private fun testErrorMeasurement(it: TestPointsResult) {
        val errorMeasurement = it.getItem().pointTestPumpResult.errorMeasurement.getLocalizedMessage(requireContext())
        if (errorMeasurement.isNotEmpty()) {
            getPointTestExecute().errorMeasurePointValue?.text = errorMeasurement
            getPointTestExecute().errorMeasurePointValue?.visibility = View.VISIBLE
        } else {
            getPointTestExecute().errorMeasurePointValue?.visibility = View.GONE
        }
    }

    /**
     * Erros da Placa de Controle
     */
    private fun testErrorControl(it: TestPointsResult) {
        val errorControl = it.getItem().pointTestPumpResult.error.getLocalizedMessage(requireContext())
        if (errorControl.isNotEmpty()) {
            getPointTestExecute().errorControlPointValue?.text = errorControl
            getPointTestExecute().errorControlPointValue?.visibility = View.VISIBLE
            frameRotationBtnRepeatGrp.visibility = View.VISIBLE
        } else {
            getPointTestExecute().errorControlPointValue?.visibility = View.GONE
            frameRotationBtnRepeatGrp.visibility = View.GONE
        }
    }

    /**
     * Verifica o stataus do teste em execução e (Des)habilita botões na View
     */
    private fun checkStatusTest(it: TestPointsResult) {
        if (it.allTestFinished) { // EnumTestStatus.TEST_ALL_TESTS_FINISHED
            buttonFinishAllTest()
            showTestNavigation()
        } else {
            when (it.getItem().pointTestPumpResult.status) {
                EnumTestStatus.TEST_PAUSED -> {
                    imgBtnPause.visibility = View.GONE
                    imgBtnContinue.visibility = View.VISIBLE
                }
                EnumTestStatus.TEST_CANCELLED -> {
//                não retorna para o menu, apenas exibe botão para finalizar
//                imgBtnFinish.visibility = View.VISIBLE
//                imgBtnCancel.visibility = View.GONE
//                imgBtnPause.visibility = View.GONE
//                imgBtnSkip.visibility = View.GONE
                    returnMenu() // retorna para o menu
                }
                EnumTestStatus.TEST_RUNNING -> {
                    imgBtnPause.visibility = View.VISIBLE
                    imgBtnContinue.visibility = View.GONE
                }
                EnumTestStatus.TEST_STARTING -> {
                    imgBtnFinish.visibility = View.GONE
                    imgBtnCancel.visibility = View.VISIBLE
                    imgBtnPause.visibility = View.VISIBLE
                    imgBtnSkip.visibility = View.VISIBLE
                }
                EnumTestStatus.TEST_FINISHED -> {
                    disableButtonFinishSkip()
                }
                EnumTestStatus.TEST_SKIPPED -> {
                    disableButtonFinishSkip()
                }
                else -> {}
            }
        }
    }

    /**
     * Desabilita e Habilita botões da tela principal após finalizar TODOS os pontos de teste
     */
    private fun buttonFinishAllTest() {
        imgBtnSkip.visibility = View.GONE
        imgBtnPause.visibility = View.GONE
        imgBtnSave.visibility = View.VISIBLE
        imgBtnFinish.isEnabled = true
        imgBtnCancel.isEnabled = true
    }

    /**
     * Desabilita botões da tela principal após exibir frame de rotação
     */
    private fun disableButtons() {
        imgBtnFinish.isEnabled = false
        imgBtnSave.isEnabled = false
        imgBtnSkip.isEnabled = false
        imgBtnContinue.isEnabled = false
        imgBtnPause.isEnabled = false
        imgBtnCancel.isEnabled = false
    }

    /**
     * Desabilita botões quando o status do teste for "SKIPPED" ou "FINISHED"
     */
    private fun disableButtonFinishSkip() {
        imgBtnFinish.isEnabled = false
        imgBtnCancel.isEnabled = false
        imgBtnPause.isEnabled = false
        imgBtnSkip.isEnabled = false
    }

    /**
     * Habilita botões após fechar frame de rotação
     */
    private fun enableButtons() {
        imgBtnFinish.isEnabled = true
        imgBtnSave.isEnabled = true
        imgBtnSkip.isEnabled = true
        imgBtnContinue.isEnabled = true
        imgBtnPause.isEnabled = true
        imgBtnCancel.isEnabled = true
    }

    private fun getPointTestExecute(): PointTestPumpExecuteGeneric {
        return adapter.getPointTestPumpView(testPointResult.indexSelected)!!
    }

    fun nextPoint(index: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            Log.w(tagLog, "nextPoint()")
            listPointTestPumpRecyclerView.scrollToPosition(index)
        }
    }

    /**
     * Permite o usuário navegar entre as telas do teste exibindo os botões
     * para avançar e retroceder e não atualizando os dados do recyclerview
     * Utilizando esta função enquanto não temos o relatório
     */
    private fun showTestNavigation() {
        imgBtnForward.visibility = View.VISIBLE
        imgBtnComeBack.visibility = View.VISIBLE
        adapter.bindData = false
    }

}