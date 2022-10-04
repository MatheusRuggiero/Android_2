package br.com.tecnomotor.marvin.view.injector

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
import br.com.tecnomotor.marvin.adapter.injector.InjectorTestPlanAdapter
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.global.Revision
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.injector.PlanTestInjector
import br.com.tecnomotor.marvin.repository.global.TypePlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorPlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorPlatformPlanTestRepository
import br.com.tecnomotor.marvin.repository.injector.RevisionInjectorPlanRepository
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.viewmodel.factory.injector.module.InjectorPlanTestViewModelFactory
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.InjectorPlanTestViewModel
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class PlanInjectorActivity : AppCompatActivity() {

    private val tagLog: String = this::class.java.simpleName
    val PLANOS_INJ = "Planos de Teste"
    private lateinit var loadingSpinner: ProgressBar

    private lateinit var inj: Injector
    private lateinit var platform: Platform

    private val adapter by lazy {
        InjectorTestPlanAdapter(context = this)
    }

    private val viewModel by lazy {
        val injectorPlanTestRepository = InjectorPlanTestRepository(application)
        val typePlanTestRepository = TypePlanTestRepository(application)
        val injectorPlatformPlanTestRepository = InjectorPlatformPlanTestRepository(application)
        val revisionInjectorPlanRepository = RevisionInjectorPlanRepository(application)

        val factory =
            InjectorPlanTestViewModelFactory(injectorPlanTestRepository, typePlanTestRepository, injectorPlatformPlanTestRepository, revisionInjectorPlanRepository)
        ViewModelProvider(this, factory).get(InjectorPlanTestViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.injector_activity_plans)
        loadingSpinner = findViewById(R.id.loading_spinner)
        loadingSpinner.visibility = View.VISIBLE

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_voltar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        platform = intent.getSerializableExtra(Extra.platform) as Platform
        inj = intent.getSerializableExtra(Extra.injector) as Injector

        titleActivity()
        configureRecyclerView()
        loadInjectorPlanTest()
    }

    /**
     * Configurações da listagem de planos de teste injetores (RecyclerView e CardView)
     */
    private fun configureRecyclerView() {
        val listInjectorPlanTestRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_list_plans_injector)
        val recyclerLayoutManager = LinearLayoutManager(applicationContext)
        listInjectorPlanTestRecyclerView.layoutManager = recyclerLayoutManager
        val dividerItemDecoration = DividerItemDecoration(listInjectorPlanTestRecyclerView.context, recyclerLayoutManager.orientation)
        listInjectorPlanTestRecyclerView.addItemDecoration(dividerItemDecoration)
        listInjectorPlanTestRecyclerView.adapter = adapter
        configureAdapter()
    }

    /**
     * Inicializa o adapter
     */
    private fun configureAdapter() {
        adapter.whenItemClicked = this::redirectPointsInjector
    }


    /**
     * Lista os planos de teste do Injetor
     */
    @DelicateCoroutinesApi
    private fun loadInjectorPlanTest() {

        val listPlanTest: MutableList<PlanTestInjector> = mutableListOf()
        Log.d(tagLog, "Plataform: ${platform.id} / Injector: ${inj.id}")

        viewModel.findPlatformPlanTestByInjId(platform.id, inj.id).observe(this, { resourcePlatform ->
            Log.d(tagLog, "loadListPlan: ${!resourcePlatform.data.isNullOrEmpty()}")
            if (!resourcePlatform.data.isNullOrEmpty()) {
                Log.d(tagLog, "loadListPlan size: ${resourcePlatform.data.size}")
                resourcePlatform.data.forEach { platformPlanTestEntity ->
                    Log.d(tagLog, "platformPlanTest: ${platformPlanTestEntity.planTestInjectorId}")
                    viewModel.findPlanTestById(platformPlanTestEntity.planTestInjectorId).observe(this, { planTestEntity ->
                        Log.d(tagLog, "planTestEntity: Null(${planTestEntity != null})")
                        if (planTestEntity != null) {
                            val planTestInjector = PlanTestInjector()
                            planTestInjector.id = planTestEntity.id
                            planTestInjector.typePlanId = planTestEntity.typePlanTestOtherId
                            viewModel.findTypePlanTestById(planTestEntity.typePlanTestOtherId).observe(this, {
                                planTestInjector.typePlanTest?.id = it.id
                                planTestInjector.typePlanTest?.description = it.description
                            })
                            planTestInjector.revision = Revision()
                            planTestInjector.revision!!.id = planTestEntity.revisionId
                            viewModel.findRevisionById(planTestInjector.revision!!.id).observe(this, {
                                planTestInjector.revision!!.motivation = it.motivation
                            })

                            listPlanTest.add(planTestInjector)
                        }
                    })
                }
                GlobalScope.launch(Dispatchers.Main) {
                    delay(1000)
                    while (true) {
                        if (listPlanTest.size > 0) {
                            Log.d(tagLog, "Update adapter: ${listPlanTest.size}")
                            adapter.update(listPlanTest)
                            loadingSpinner.visibility = View.GONE
                            return@launch
                        }
                    }
                }
            } else
                Toast.makeText(applicationContext, applicationContext.getString(R.string.not_found_values_list_inj_plan), Toast.LENGTH_SHORT).show()
        })

    }

    /**
     * Exibe o Código da Bomba no Título da Activity
     */
    private fun titleActivity() {
        val cod = codigoInjSelecionado()
        val info = infoInjSelecionado()
        if (info != null) {
            title = "INJETOR - $cod  |  ${info.brand?.name}  |  ${info.type}  |  Rev.Inj. ${info.revisionNumber}  |  $PLANOS_INJ"
        }
    }

    /**
     * Verifica informações do Injetor Selecionado
     */
    private fun infoInjSelecionado(): Injector? {
        val intent = intent
        return intent.getSerializableExtra(Extra.injector) as? Injector
    }

    /**
     * Verifica o Código do Injetor Selecionado
     */
    private fun codigoInjSelecionado(): String? {
        val info = infoInjSelecionado()
        return info?.code
    }

    /**
     * Verifica o id do Injetor Selecionado
     */
    private fun idInjSelecionado(): String {
        val info = infoInjSelecionado()
        return info?.id.toString()
    }

    /**
     * Redirecionar para a activity de Pontos de Teste Injetor
     */
    private fun redirectPointsInjector(it: PlanTestInjector) {
        val inj = infoInjSelecionado()
        val intent = Intent(this, PointInjectorActivity::class.java)
        intent.putExtra(Extra.plan, it)
        intent.putExtra(Extra.platform, platform)
        intent.putExtra(Extra.injector, inj)
        startActivity(intent)
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