package br.com.tecnomotor.marvin.view.pump

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.pump.PumpPlanTestAdapter
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.global.Revision
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.model.pump.PumpPlanTest
import br.com.tecnomotor.marvin.repository.global.TypePlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.PumpPlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.PumpPlatformPlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.RevisionPumpPlanRepository
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.viewmodel.factory.pump.module.PumpPlanTestViewModelFactory
import br.com.tecnomotor.marvin.view.viewmodel.pump.module.PumpPlanTestViewModel
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class PlanPumpActivity : AppCompatActivity() {

    private val tagLog: String = this::class.java.simpleName
    private val PLANOS_BOMBA = "Planos da Bomba"

    private lateinit var loadingSpinner: ProgressBar
    private val adapter by lazy {
        PumpPlanTestAdapter(context = this)
    }
    private val viewModel by lazy {
        val pumpPlanTestRepository = PumpPlanTestRepository(application)
        val typePlanTestRepository = TypePlanTestRepository(application)
        val pumpPlatformPlanTestRepository = PumpPlatformPlanTestRepository(application)
        val revisionPumpPlanRepository = RevisionPumpPlanRepository(application)

        val factory = PumpPlanTestViewModelFactory(
            pumpPlanTestRepository,
            typePlanTestRepository,
            pumpPlatformPlanTestRepository,
            revisionPumpPlanRepository)
        ViewModelProvider(this, factory)[PumpPlanTestViewModel::class.java]
    }
    private lateinit var pump: Pump
    private lateinit var platform: Platform


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pump_activity_plans)
        loadingSpinner = findViewById(R.id.loading_spinner)
        loadingSpinner.visibility = View.VISIBLE

        val intent = intent
        pump = intent.getSerializableExtra(Extra.pump) as Pump
        platform = intent.getSerializableExtra(Extra.platform) as Platform

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_voltar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        titleActivity()
        confRecyclerView()
        loadListPlanPump()
    }

    /**
     * Configurações da listagem de planos de teste bomba (RecyclerView e CardView)
     */
    private fun confRecyclerView() {
        val listPumpPlanTestRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_list_plan_pump)
        val recyclerLayoutManager = LinearLayoutManager(applicationContext)
        listPumpPlanTestRecyclerView.layoutManager = recyclerLayoutManager
        val dividerItemDecoration = DividerItemDecoration(listPumpPlanTestRecyclerView.context, recyclerLayoutManager.orientation)
        listPumpPlanTestRecyclerView.addItemDecoration(dividerItemDecoration)
        listPumpPlanTestRecyclerView.adapter = adapter
        configureAdapter()
    }

    /**
     * Exibe o Código da Bomba no Título da Activity
     */
    private fun titleActivity() {
        title = "BOMBA  -  ${pump.code}  |  ${pump.brand.name}  |  Rev.Bomba ${pump.revision?.id}  |  $PLANOS_BOMBA "
    }

    /**
     * Lista os planos de teste da bomba
     */
    private fun loadListPlanPump() {

        val listPlanTest: MutableList<PumpPlanTest> = mutableListOf()

        Log.d(tagLog, "Plataform: ${platform.id} / Bomba: ${pump.id}")

        viewModel.findPlatformPlanTestByPumpId(platform.id, pump.id)
            .observe(this) { resourcePlatform ->
                Log.d(tagLog, "loadListPlan: ${!resourcePlatform.data.isNullOrEmpty()}")
                if (!resourcePlatform.data.isNullOrEmpty()) {
                    Log.d(tagLog, "loadListPlan size: ${resourcePlatform.data.size}")
                    resourcePlatform.data.forEach { platformPlanTestEntity ->
                        Log.d(tagLog, "platformPlanTest: ${platformPlanTestEntity.pumpPlanTestId}")
                        viewModel.findPlanTestById(platformPlanTestEntity.pumpPlanTestId)
                            .observe(this) { planTestEntity ->
                                Log.d(tagLog, "planTestEntity: Null(${planTestEntity != null})")
                                if (planTestEntity != null) {
                                    val planTest = PumpPlanTest()
                                    planTest.id = planTestEntity.id
                                    planTest.typePlanId = planTestEntity.typePlanId
                                    viewModel.findTypePlanTestById(planTestEntity.typePlanId)
                                        .observe(this) {
                                            planTest.typePlanTest.id = it.id
                                            planTest.typePlanTest.description = it.description
                                        }
                                    planTest.revision = Revision()
                                    planTest.revision.id = planTestEntity.revisionId!!
                                    viewModel.findRevisionById(planTest.revision.id).observe(this) {
                                        planTest.revision.motivation = it.motivation
                                        planTest.revision.dateHour = it.dateHour
                                    }
                                    planTest.pressure = planTestEntity.pressure
                                    planTest.rotation = planTestEntity.rotation
                                    planTest.minimumFlow = planTestEntity.minimumFlow
                                    planTest.timeCourse = planTestEntity.timeCourse
                                    planTest.prescaler = planTestEntity.prescaler
                                    planTest.token = planTestEntity.token
                                    planTest.deleted = planTestEntity.deleted
                                    planTest.deletePermanent = planTestEntity.deletePermanent
                                    listPlanTest.add(planTest)
                                }
                            }
                    }
                    GlobalScope.launch(Dispatchers.Main) {
                        while (listPlanTest.size < resourcePlatform.data.size) delay(100)
                        Log.d(tagLog, "Update adapter: ${listPlanTest.size}")
                        delay(10)
                        if (listPlanTest.size == 1) { //Auto seleção
                            autoRedirectPointPump(listPlanTest[0])
                        } else {
                            adapter.update(listPlanTest)
                            loadingSpinner.visibility = View.GONE
                        }
                    }
                } else
                    Toast.makeText(applicationContext,
                        applicationContext.getString(R.string.not_found_values_list_pump_plan),
                        Toast.LENGTH_SHORT).show()
            }
    }

    /**
     * Ação do Ícone Voltar (View)
     */
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

    /**
     * Inicializa o adapter
     */
    private fun configureAdapter() {
        adapter.whenItemClicked = this::redirectPointPump
    }

    private fun autoRedirectPointPump(planPlanTest: PumpPlanTest) {
        redirectPointPump(planPlanTest)
        finish()
    }


    /**
     * Redirecionar para a activity de Pontos de Teste Bomba
     */
    private fun redirectPointPump(pumpPlanTest: PumpPlanTest) {
        val intent = Intent(this, PointPumpActivity::class.java)
        intent.putExtra(Extra.pump, pump)
        intent.putExtra(Extra.plan, pumpPlanTest)
        intent.putExtra(Extra.platform, platform)
        startActivity(intent)
    }


}