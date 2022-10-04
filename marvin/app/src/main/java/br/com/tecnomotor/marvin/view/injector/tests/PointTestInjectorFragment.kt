package br.com.tecnomotor.marvin.view.injector.tests

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.commonrail.device.commands.injector.result.PointTestInjectorResultDemo
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.generic.injector.PointTestInjectorExecuteGeneric
import br.com.tecnomotor.marvin.adapter.injector.PointTestInjectorAdapter
import br.com.tecnomotor.marvin.controller.IViewController
import br.com.tecnomotor.marvin.controller.test.IControllerPointTest
import br.com.tecnomotor.marvin.controller.test.injector.InjectorTestPointType
import br.com.tecnomotor.marvin.controller.test.injector.demo.PointTestInjectorControllerDemo
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.utils.objects.FormatTestResults
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.injector.InjectorActivity
import br.com.tecnomotor.marvin.utils.CountUpTimer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.concurrent.TimeUnit

@InternalCoroutinesApi
@DelicateCoroutinesApi
class PointTestInjectorFragment : Fragment(), IViewController {

    private var tagLog = "PointTestInjectorFragment"

    private lateinit var deviceRepository: CommonRailRepository
    private var injector: Injector? = null
    private var platform: Platform? = null
    private var pointTestIndex = 0
    private lateinit var listPointTestInjectorTest: MutableList<PointTestInjector>
    private lateinit var listPointTestInjRecyclerView: RecyclerView
    private lateinit var root: View
    private lateinit var pointControllerInjector: IControllerPointTest<PointTestInjector>
    private lateinit var txtCountTime: TextView
    private var countTimeValue: MutableLiveData<String> = MutableLiveData("00:00:00")
    /* Botões */
    private lateinit var imgBtnFinish: ImageButton
    private lateinit var imgBtnSave: ImageButton
    private lateinit var imgBtnSkip: ImageButton
    private lateinit var imgBtnContinue: ImageButton
    private lateinit var imgBtnPause: ImageButton
    private lateinit var imgBtnCancel: ImageButton
    private lateinit var imgBtnComeBack: ImageView
    private lateinit var imgBtnForward: ImageView

    private val adapter by lazy {
        PointTestInjectorAdapter(activity?.applicationContext!!)
    }

    val countTime = object : CountUpTimer(1000) {
        override fun onTick(millisFinished: Long) {
            val timeStr = String.format(
                "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millisFinished),
                TimeUnit.MILLISECONDS.toMinutes(millisFinished) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisFinished)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(millisFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisFinished))
            )
            countTimeValue.postValue(timeStr)
        }
        override fun onFinish() {}
    }

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
        root = inflater.inflate(R.layout.fragment_point_test_injector, container, false)
        configureRecyclerView()

        txtCountTime = root.findViewById(R.id.txt_time_test_injector)

        imgBtnFinish = root.findViewById(R.id.btn_finish_point_test_injector)
        imgBtnSave = root.findViewById(R.id.btn_save_point_test_injector)
        imgBtnSkip = root.findViewById(R.id.btn_skip_point_test_injector)
        imgBtnContinue = root.findViewById(R.id.btn_continue_point_test_injector)
        imgBtnPause = root.findViewById(R.id.btn_pause_point_test_injector)
        imgBtnCancel = root.findViewById(R.id.btn_cancel_point_test_injector)
        imgBtnForward = root.findViewById(R.id.img_forward)
        imgBtnComeBack = root.findViewById(R.id.img_come_back)

        imgBtnForward.visibility = View.INVISIBLE
        imgBtnComeBack.visibility = View.INVISIBLE

        imgBtnCancel.setOnClickListener {
            pointControllerInjector.cancelTest()
            finishTest(false)
            returnMenu()
        }
        imgBtnPause.setOnClickListener {
            countTime.pause()
            pointControllerInjector.pauseTest()
            imgBtnPause.visibility = View.GONE
            imgBtnContinue.visibility = View.VISIBLE
        }
        imgBtnContinue.setOnClickListener {
            countTime.continueCount()
            pointControllerInjector.startTest()
            imgBtnPause.visibility = View.VISIBLE
            imgBtnContinue.visibility = View.GONE
        }
        imgBtnSkip.setOnClickListener {
            pointControllerInjector.skipTest()
        }
        imgBtnSave.setOnClickListener {
            imgBtnSave.visibility = View.GONE
            imgBtnFinish.visibility = View.VISIBLE
        }
        imgBtnFinish.setOnClickListener {
            returnMenu()
        }
        countTimeValue.observeForever {
            txtCountTime.text = it
//            setValues()
        }
        imgBtnForward.setOnClickListener {
            if (pointTestIndex < adapter.itemCount - 1) {
                pointTestIndex += 1
                listPointTestInjRecyclerView.scrollToPosition(pointTestIndex)
            }
        }
        imgBtnComeBack.setOnClickListener {
            if (pointTestIndex > 0) {
                pointTestIndex -= 1
                listPointTestInjRecyclerView.scrollToPosition(pointTestIndex)
            }
        }
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
//            deviceViewModel = args[Extra.deviceViewModel] as DeviceViewModel
            listPointTestInjectorTest = args[Extra.points] as MutableList<PointTestInjector>
            injector = args[Extra.injector] as Injector
            platform = args[Extra.platform] as Platform

            //TODO descomentar quando estiver com placa conectada
//             while (true) {
//                if ((deviceViewModel.getDeviceControle() != null) && (deviceViewModel.getDeviceMedicao() != null))
//                    break
//            }
            loadPointInjector()

            pointControllerInjector = PointTestInjectorControllerDemo(injector!!, getPointTest(), this)
            (pointControllerInjector as PointTestInjectorControllerDemo).resultadoTesteInjetor.observeForever { setValues(it) }
            pointControllerInjector.startTest()
            countTime.start()
        }
    }

    /**
     * Retornar para a lista de Injetores
     */
    private fun returnMenu() {
        val intent = Intent(activity, InjectorActivity::class.java)
        intent.putExtra(Extra.platform, platform)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        activity?.finish()
    }

    private fun finishTest(saveValues: Boolean = true) {
        if (saveValues) {
            imgBtnPause.visibility = View.GONE
            imgBtnContinue.visibility = View.GONE
            imgBtnSkip.visibility = View.GONE
            imgBtnSave.visibility = View.VISIBLE
        }
        countTime.stop()
    }

    private fun configureRecyclerView() {
        listPointTestInjRecyclerView = root.findViewById(R.id.recyclerview_list_point_test_injector)
        val recyclerLayoutManager = object : LinearLayoutManager(root.context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        listPointTestInjRecyclerView.layoutManager = recyclerLayoutManager
        val dividerItemDecoration = DividerItemDecoration(listPointTestInjRecyclerView.context, recyclerLayoutManager.orientation)
        listPointTestInjRecyclerView.addItemDecoration(dividerItemDecoration)
        listPointTestInjRecyclerView.adapter = adapter
    }


    /**
     * Lista os pontos de injetores
     */
    private fun loadPointInjector() {
        val list: MutableList<PointTestInjectorExecuteGeneric> = arrayListOf()
        listPointTestInjectorTest.forEach {
            if (it.typePointTestInjector?.getTypeTest() != InjectorTestPointType.LEAK
                && it.typePointTestInjector?.getTypeTest() != InjectorTestPointType.START
            ) {
                val pointTestInjectorExecuteGeneric = PointTestInjectorExecuteGeneric()
                pointTestInjectorExecuteGeneric.pointTestInjector = it
                list.add(pointTestInjectorExecuteGeneric)
            }
        }
        adapter.update(list)
    }

    fun setValues(it: PointTestInjectorResultDemo) {
        Log.i(tagLog, "setValues: $it")
        getPointTest().testInjectorResults.toAssign(it)
        //Log.w(tagLog, getPointTest().testResults.toString())
        getPointTestExecute().statusPointValue?.text = it.status.getLocalizedMessage(requireContext())
        if (getPointTestExecute().pieChartViewPointTestInjectorExecuteGeneric != null)
            GlobalUtil.method.settingValuePieChart(
                getPointTestExecute().pieChartViewPointTestInjectorExecuteGeneric!!,
                context,
                2000f,
                it.pressure.toFloat(),
                FormatTestResults.FORMAT_PRESSURE.format(it.pressure)
            )
        if (getPointTestExecute().chartFlowPointTestInjectorExecuteGeneric != null)
            GlobalUtil.method.settingValueBarChart(
                getPointTestExecute().chartFlowPointTestInjectorExecuteGeneric!!,
                it.injectionValue.toFloat(),
                it.minimumInjection.toFloat(),
                it.maximumInjection.toFloat(),
                getPointTest().getInjectionMinimumGraph(),
                getPointTest().getInjectionMaximumGraph(),
                it.getInjectionColor()
            )
        getPointTestExecute().flowPointValue?.text = FormatTestResults.FORMAT_MILLILITER.format(it.injectionValue)
        if (getPointTestExecute().chartReturnPointTestInjectorExecuteGeneric != null)
            GlobalUtil.method.settingValueBarChart(
                getPointTestExecute().chartReturnPointTestInjectorExecuteGeneric!!,
                it.returnValue.toFloat(),
                it.minimumReturn.toFloat(),
                it.maximumReturn.toFloat(),
                getPointTest().getReturnMinimumGraph(),
                getPointTest().getReturnMaximumGraph(),
                it.getReturnColor()
            )
        getPointTestExecute().returnPointValue?.text = FormatTestResults.FORMAT_MILLILITER.format(it.returnValue)
        getPointTestExecute().pressurePointValue?.text = FormatTestResults.FORMAT_PRESSURE.format(it.desiredPressure)
        getPointTestExecute().timePointValue?.text = FormatTestResults.FORMAT_TIME_TEST.format(it.testTime / 1000)
        if ((getPointTestExecute().frequencyPointValue != null) && (it.frequency > 0))
            getPointTestExecute().frequencyPointValue?.text = FormatTestResults.FORMAT_FREQUENCY.format(it.frequency.toDouble() / 10.0)
        if ((getPointTestExecute().timeInjectionPointValue != null) && (it.injectionTime > 0))
            getPointTestExecute().timeInjectionPointValue?.text = FormatTestResults.FORMAT_TIME_INJ.format(it.injectionTime)
    }

    fun getPointTestExecute(): PointTestInjectorExecuteGeneric {
        return adapter.getPointTestInjectorView(pointTestIndex)!!
    }

    fun getPointTest(): PointTestInjector {
        return getPointTestExecute().pointTestInjector!!
    }

    override suspend fun nextPoint() {
        Log.w(tagLog, "nextPoint()")
        val res: Boolean = (pointTestIndex + 1 < adapter.itemCount)
        if (res) {
            pointTestIndex++
            listPointTestInjRecyclerView.scrollToPosition(pointTestIndex)
            pointControllerInjector.setNextPoint(getPointTest())
            pointControllerInjector.startTest()
        } else { //Salvar o teste
            countTime.stop()
            imgBtnSkip.visibility = View.GONE
            imgBtnPause.visibility = View.GONE
            imgBtnSave.visibility = View.VISIBLE
            navegarTelas()
        }
    }

    /**
     * Permite o usuário navegar entre as telas do teste exibindo os botões
     * para avançar e retroceder e não atualizando os dados do recyclerview
     * Utilizando esta função enquanto não temos o relatório
     */
    fun navegarTelas() {
        imgBtnForward.visibility = View.VISIBLE
        imgBtnComeBack.visibility = View.VISIBLE
        adapter.bindData = false
    }

}