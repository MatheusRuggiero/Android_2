package br.com.tecnomotor.marvin.view.injector

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.generic.injector.PointInjectorGeneric
import br.com.tecnomotor.marvin.adapter.injector.InjectorTestPointAdapter
import br.com.tecnomotor.marvin.controller.test.injector.InjectorTestPointType
import br.com.tecnomotor.marvin.dao.entities.injector.PointInjectorTestEntity
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.injector.PlanTestInjector
import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import br.com.tecnomotor.marvin.model.injector.TypePointTestInjector
import br.com.tecnomotor.marvin.repository.injector.InjectorPlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.PointTestInjectorRepository
import br.com.tecnomotor.marvin.repository.injector.TypePointInjectorTestRepository
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.injector.tests.TestInjectorActivity
import br.com.tecnomotor.marvin.view.viewmodel.factory.injector.module.PointTestInjectorViewModelFactory
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.PointTestInjectorViewModel
import kotlinx.coroutines.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
class PointInjectorActivity : AppCompatActivity() {

    private val tagLog: String = this::class.java.simpleName
    private val PONTOS_INJETOR = "Pontos de Teste"
    private val listInjectorPointTestGeneric: MutableList<PointInjectorGeneric> = ArrayList()
    private lateinit var loadingSpinner: ProgressBar
    private lateinit var button: Button

    private val adapter by lazy {
        InjectorTestPointAdapter(context = this)
    }

    private val viewModel by lazy {
        val injectorPlanTestRepository = InjectorPlanTestRepository(application)
        val pointTestInjectorRepository = PointTestInjectorRepository(application)
        val typePointInjectorTestRepository = TypePointInjectorTestRepository(application)
        val factory =
            PointTestInjectorViewModelFactory(
                injectorPlanTestRepository,
                pointTestInjectorRepository,
                typePointInjectorTestRepository
            )
        ViewModelProvider(this, factory).get(PointTestInjectorViewModel::class.java)
    }

    private lateinit var plan: PlanTestInjector
    private lateinit var injector: Injector
    private lateinit var platform: Platform

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.injector_activity_points)
        button = findViewById(R.id.btn_test_ponto_inj)
        button.visibility = View.INVISIBLE
        loadingSpinner = findViewById(R.id.loading_spinner)
        loadingSpinner.visibility = View.VISIBLE

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_voltar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        plan = intent.getSerializableExtra(Extra.plan) as PlanTestInjector
        injector = intent.getSerializableExtra(Extra.injector) as Injector
        platform = intent.getSerializableExtra(Extra.platform) as Platform

        configureRecyclerView()
        findAllListPoint()

        redirectExecuteTestInjector(plan, injector)
        titleActivity()
    }

    private fun titleActivity() {
        title = "INJETOR - ${injector.code}  |  ${injector.brand?.name}  |  ${injector.type}  |  Rev.Inj. ${injector.revisionNumber} " +
                " |  ${plan.typePlanTest?.description}  |  Rev.Plan. ${plan.revision?.id}  |  $PONTOS_INJETOR "
    }

    /**
     * Inicializa a activity
     */
    private fun configureRecyclerView() {
        val listInjRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_list_pontos_inj)
        val recyclerLayoutManager = LinearLayoutManager(applicationContext)
        listInjRecyclerView.layoutManager = recyclerLayoutManager
        val dividerItemDecoration = DividerItemDecoration(listInjRecyclerView.context, recyclerLayoutManager.orientation)
        listInjRecyclerView.addItemDecoration(dividerItemDecoration)
        listInjRecyclerView.adapter = adapter
        configureAdapter()
    }

    /**
     * Inicializa o adapter
     */
    private fun configureAdapter() {
        adapter.whenItemClicked = this::checkListPointTestInjector
    }

    /**
     * Listar Pontos de teste do Injetor
     */
    @DelicateCoroutinesApi
    private fun findAllListPoint() {
        var listLoadDataBase: ArrayList<PointInjectorTestEntity>?
        viewModel.findByPointTestInjectorUsingIdPlanList(plan.id).observe(this, { resource ->
            listLoadDataBase = resource.data as ArrayList<PointInjectorTestEntity>?
            if (!listLoadDataBase.isNullOrEmpty()) {
                listLoadDataBase?.forEach { pointTestInjectorEntity ->
                    var existRegister = false
                    val pointTestInjector = PointTestInjector()

                    if (pointTestInjectorEntity.typePointTestInjector != InjectorTestPointType.LEAK.id //ESTANQUEIDADE
                        && pointTestInjectorEntity.typePointTestInjector != InjectorTestPointType.START.id //PARTIDA
                    ) {

                        viewModel.findByTypeInjectorIdLiveData(pointTestInjectorEntity.typePointTestInjector)?.observe(this, { typePointTest ->
                            Log.d(tagLog, "TypePointTestInjector: ${typePointTest.description}")
                            if (typePointTest != null) {
                                pointTestInjector.typePointTestInjector = TypePointTestInjector(
                                    typePointTest.id,
                                    typePointTest.description,
                                    typePointTest.typePoint
                                )
                            }
                        })

                        pointTestInjector.planTestInjector = pointTestInjectorEntity.planTestInjector
                        pointTestInjector.sequence = pointTestInjectorEntity.sequence
                        pointTestInjector.injectionMaximum = pointTestInjectorEntity.injectionMaximum?.toBigDecimal()
                        pointTestInjector.injectionMaximum2 = pointTestInjectorEntity.injectionMaximum2?.toBigDecimal()
                        pointTestInjector.injectionMinimum = pointTestInjectorEntity.injectionMinimum?.toBigDecimal()
                        pointTestInjector.injectionMinimum2 = pointTestInjectorEntity.injectionMinimum2?.toBigDecimal()
                        pointTestInjector.originPoint = pointTestInjectorEntity.originPoint
                        pointTestInjector.originPoint2 = pointTestInjectorEntity.originPoint2
                        pointTestInjector.returnMaximum = pointTestInjectorEntity.returnMaximum?.toBigDecimal()
                        pointTestInjector.returnMaximum2 = pointTestInjectorEntity.returnMaximum2?.toBigDecimal()
                        pointTestInjector.returnMinimum = pointTestInjectorEntity.returnMinimum?.toBigDecimal()
                        pointTestInjector.returnMinimum2 = pointTestInjectorEntity.returnMinimum2?.toBigDecimal()
                        pointTestInjector.frequency = pointTestInjectorEntity.frequency
                        pointTestInjector.pressure = pointTestInjectorEntity.pressure
                        pointTestInjector.timeTest = pointTestInjectorEntity.timeTest
                        pointTestInjector.timeInjection = pointTestInjectorEntity.timeInjection
                        pointTestInjector.timeOn = pointTestInjectorEntity.timeOn
                        pointTestInjector.timeOff = pointTestInjectorEntity.timeOff
                        pointTestInjector.timeSignalHigh = pointTestInjectorEntity.timeSignalHigh
                        pointTestInjector.timeSignalCstHigh = pointTestInjectorEntity.timeSignalCstHigh
                        pointTestInjector.injectionActive = pointTestInjectorEntity.injectionActive
                        pointTestInjector.measureInjection = pointTestInjectorEntity.measureInjection
                        pointTestInjector.measureReturn = pointTestInjectorEntity.measureReturn
                        pointTestInjector.preFillMedInj = pointTestInjectorEntity.preFillMedInj
                        pointTestInjector.presetUser = pointTestInjectorEntity.presetUser
                        pointTestInjector.originPoint = pointTestInjectorEntity.originPoint
                        pointTestInjector.timeSignalOff = pointTestInjectorEntity.timeSignalOff
                        pointTestInjector.tensionHigh = pointTestInjectorEntity.tensionHigh
                        pointTestInjector.tensionSmaller = pointTestInjectorEntity.tensionSmaller
                        pointTestInjector.emptyCH1 = pointTestInjectorEntity.emptyCH1
                        pointTestInjector.peakCurrentCH1 = pointTestInjectorEntity.peakCurrentCH1 ?: 0
                        pointTestInjector.currentMaximumCH1 = pointTestInjectorEntity.currentMaximumCH1 ?: 0
                        pointTestInjector.currentMinimumCH1 = pointTestInjectorEntity.currentMinimumCH1 ?: 0
                        pointTestInjector.delayTimeCH1 = pointTestInjectorEntity.delayTimeCH1 ?: 0
                        pointTestInjector.empty1CH1 = pointTestInjectorEntity.empty1CH1
                        pointTestInjector.empty2CH1 = pointTestInjectorEntity.empty2CH1
                        pointTestInjector.empty3CH1 = pointTestInjectorEntity.empty3CH1
                        pointTestInjector.empty4CH1 = pointTestInjectorEntity.empty4CH1
                        pointTestInjector.delayPulseCH1 = pointTestInjectorEntity.delayPulseCH1
                        pointTestInjector.firstCH1 = pointTestInjectorEntity.firstCH1
                        pointTestInjector.timeTest2 = pointTestInjectorEntity.timeTest2
                        pointTestInjector.timeInjection2 = pointTestInjectorEntity.timeInjection2
                        pointTestInjector.timeOn2 = pointTestInjectorEntity.timeOn2
                        pointTestInjector.timeOff2 = pointTestInjectorEntity.timeOff2
                        pointTestInjector.timeSignalHigh2 = pointTestInjectorEntity.timeSignalHigh2
                        pointTestInjector.timeSignalCstHigh2 = pointTestInjectorEntity.timeSignalCstHigh2
                        pointTestInjector.injectionActive2 = pointTestInjectorEntity.injectionActive2
                        pointTestInjector.measureInjection2 = pointTestInjectorEntity.measureInjection2
                        pointTestInjector.measureReturn2 = pointTestInjectorEntity.measureReturn2
                        pointTestInjector.preFillMedInj2 = pointTestInjectorEntity.preFillMedInj2
                        pointTestInjector.presetUser2 = pointTestInjectorEntity.presetUser2
                        pointTestInjector.originPoint2 = pointTestInjectorEntity.originPoint2
                        pointTestInjector.timeSignalOff2 = pointTestInjectorEntity.timeSignalOff2
                        pointTestInjector.tensionHigh2 = pointTestInjectorEntity.tensionHigh2
                        pointTestInjector.tensionSmaller2 = pointTestInjectorEntity.tensionSmaller2
                        pointTestInjector.emptyCH2 = pointTestInjectorEntity.emptyCH2
                        pointTestInjector.peakCurrentCH2 = pointTestInjectorEntity.peakCurrentCH2 ?: 0
                        pointTestInjector.currentMaximumCH2 = pointTestInjectorEntity.currentMaximumCH2 ?: 0
                        pointTestInjector.currentMinimumCH2 = pointTestInjectorEntity.currentMinimumCH2 ?: 0
                        pointTestInjector.delayTimeCH2 = pointTestInjectorEntity.delayTimeCH2 ?: 0
                        pointTestInjector.empty1CH2 = pointTestInjectorEntity.empty1CH2
                        pointTestInjector.empty2CH2 = pointTestInjectorEntity.empty2CH2
                        pointTestInjector.empty3CH2 = pointTestInjectorEntity.empty3CH2
                        pointTestInjector.empty4CH2 = pointTestInjectorEntity.empty4CH2
                        pointTestInjector.delayPulseCH2 = pointTestInjectorEntity.delayPulseCH2
                        pointTestInjector.firstCH2 = pointTestInjectorEntity.firstCH2

                        val injectorPointGeneric = PointInjectorGeneric()
                        injectorPointGeneric.pointTestInjector = pointTestInjector
                        injectorPointGeneric.statusCheckBox = pointTestInjector.presetUser ?: true
                        listInjectorPointTestGeneric.add(injectorPointGeneric)
                    }

                }

            } else {
                loadingSpinner.visibility = View.INVISIBLE
                Toast.makeText(this, "Nenhuma informação encontrada", Toast.LENGTH_LONG).show()
                GlobalUtil.method.shortTimeMessageAlert(
                    context = applicationContext,
                    msg = applicationContext.getString(R.string.error_load_list)
                )
            }
        })

        GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                delay(1000)
                if (listInjectorPointTestGeneric.size > 0) {
                    Log.d(tagLog, "Update adapter: ${listInjectorPointTestGeneric.size}")
                    adapter.update(listInjectorPointTestGeneric)
                    button.visibility = View.VISIBLE
                    loadingSpinner.visibility = View.INVISIBLE
                    return@launch
                }
            }
        }

    }

    /**
     * Update list selected point test
     */
    private fun checkListPointTestInjector(pointInjectorGeneric: PointInjectorGeneric) {
        if (pointInjectorGeneric.pointTestInjector?.typePointTestInjector?.description != null
            && pointInjectorGeneric.pointTestInjector?.typePointTestInjector?.description!!.trim().isNotEmpty()
            && pointInjectorGeneric.pointTestInjector?.typePointTestInjector?.description!!.trim().uppercase() != "0"
            && pointInjectorGeneric.pointTestInjector?.typePointTestInjector?.description!!.trim().uppercase() != "ESTANQUEIDADE"
            && pointInjectorGeneric.pointTestInjector?.typePointTestInjector?.description!!.trim().uppercase() != "PARTIDA"
        ) {
            var position = 0
            for (index in 0..listInjectorPointTestGeneric.size) {
                if (listInjectorPointTestGeneric[index].pointTestInjector?.typePointTestInjector?.description == pointInjectorGeneric.pointTestInjector?.typePointTestInjector?.description
                    && listInjectorPointTestGeneric[index].pointTestInjector?.sequence == pointInjectorGeneric.pointTestInjector?.sequence
                ) {
                    listInjectorPointTestGeneric[index].statusCheckBox = !listInjectorPointTestGeneric[index].statusCheckBox
                    position = index
                    break
                }
            }
            adapter.update(position) // necessário para conseguir clicar no nome dos itens
            updateVisibilityButton(listInjectorPointTestGeneric)
        }
    }

    /**
     * Atualiza a visibilidade do botão de acordo com a lista. Esconde o botão quando lista vazia
     */
    private fun updateVisibilityButton(list: MutableList<PointInjectorGeneric>) {
        list.forEach { point ->
            if (point.statusCheckBox) {
                button.visibility = View.VISIBLE
                return // pelo menos 1 checkado já pode mostrar o botão
            }
        }
        // nenhum checkado, esconde o botão
        button.visibility = View.INVISIBLE
    }

    /**
     * Redirect to View Executed Test Injector
     */
    private fun redirectExecuteTestInjector(plano: PlanTestInjector?, injector: Injector?) {
        button.setOnClickListener {
            val intent = Intent(this, TestInjectorActivity::class.java)

//            listInjectorPointTestGeneric.forEach {
//                val pointTestEntityInjectorList: List<PointInjectorTestEntity>? = viewModel.findByPointTestInjectorUsingId(it.pointTestInjector!!.id)
//                if (!pointTestEntityInjectorList.isNullOrEmpty()) {
//                    pointTestEntityInjectorList[0].presetUser = it.statusCheckBox
//                    viewModel.findByPointTestInjectorUpdate(pointInjectorTestEntity = pointTestEntityInjectorList[0])
//                }
//            }

            val pointsInjector: MutableList<PointTestInjector> = arrayListOf()
            checkSelectedPointTest().forEach {
                pointsInjector.add(it.pointTestInjector!!)
            }

            intent.putExtra(Extra.plan, plano)
            intent.putExtra(Extra.points, ArrayList<PointTestInjector>(pointsInjector))
            intent.putExtra(Extra.injector, injector)
            intent.putExtra(Extra.platform, platform)
            startActivity(intent)
        }
    }

    /**
     * Check Selected Point Test Injector
     */
    private fun checkSelectedPointTest(): MutableList<PointInjectorGeneric> {
        val list: MutableList<PointInjectorGeneric> = ArrayList()
        listInjectorPointTestGeneric.forEach { point ->
            if (point.statusCheckBox) {
                list.add(point)
            }
        }
        return list
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}