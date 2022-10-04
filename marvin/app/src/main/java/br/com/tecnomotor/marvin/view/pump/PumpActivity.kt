package br.com.tecnomotor.marvin.view.pump

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.pump.PumpsAdapter
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.repository.global.BrandRepository
import br.com.tecnomotor.marvin.repository.global.RevisionRepository
import br.com.tecnomotor.marvin.repository.pump.PumpRepository
import br.com.tecnomotor.marvin.repository.pump.RevisionPumpRepository
import br.com.tecnomotor.marvin.repository.pump.TypePumpRepository
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.viewmodel.factory.pump.module.PumpViewModelFactory
import br.com.tecnomotor.marvin.view.viewmodel.pump.module.PumpViewModel
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class PumpActivity : AppCompatActivity() {

    private val tagLog: String = this::class.java.simpleName
    private val LISTA_BOMBAS = "Lista Bombas"
    var listPump = ArrayList<Pump>()
    private var listPumpNotFilter: MutableList<Pump> = ArrayList()
    private lateinit var platform: Platform
    private lateinit var loadingSpinner: ProgressBar

    private val adapter by lazy {
        PumpsAdapter(context = this)
    }

    private val viewModel by lazy {
        val pumpRepository = PumpRepository(application)
        val brandRepository = BrandRepository(application)
        val revisionRepository = RevisionRepository(application)
        val revisionPumpRepository = RevisionPumpRepository(application)
        val typePumpRepository = TypePumpRepository(application)
        val factory = PumpViewModelFactory(pumpRepository, brandRepository, revisionRepository, revisionPumpRepository, typePumpRepository)

        ViewModelProvider(this, factory)[PumpViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pump_activity_list)
        loadingSpinner = findViewById(R.id.loading_spinner)
        loadingSpinner.visibility = View.VISIBLE

        platform = intent.getSerializableExtra(Extra.platform) as Platform

        supportActionBar?.title = LISTA_BOMBAS
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_voltar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configureRecyclerView()
        loadListPump()
    }

    override fun onStop() {
        loadingSpinner.visibility = View.INVISIBLE
        super.onStop()
    }

    private fun loadListPump() {
        viewModel.findAllPump().observe(this) { resource ->
            resource.data?.let { listPumpEntity ->
                listPump = ArrayList()
                if (!listPumpEntity.isNullOrEmpty()) {
                    listPumpEntity.forEach { it01 ->
                        val pump = Pump()
                        pump.id = it01.id
                        pump.description = it01.descriptionType
                        pump.application = it01.application
                        pump.code = it01.code
                        pump.revision?.id = it01.revisionId
                        pump.nameActuator = it01.nameActuator
                        pump.directionRotating = it01.directionRotating
                        pump.adapterElectric = it01.adapterElectric
                        pump.adapterHhydraulicPressure = it01.hydraulicPressureAdapter
                        pump.adapterHydraulicPower = it01.hydraulicPowerAdapter
                        pump.adapterHydraulicReturn = it01.hydraulicReturnAdapter
                        pump.adapterMechanical = it01.mechanicalAdapter
                        pump.referenceActuator = it01.referenceActuator

//                        viewModel.findByUsingRevisionPumpId(it01.revisionId).observe(this, { revisionList ->
//                            if (!revisionList.data.isNullOrEmpty()) {
//                                revisionList.data.forEach { revisionEntity ->
//                                    if (revisionEntity.id_rev < pump.revision?.id!!) {
//                                        pump.revision?.id = revisionEntity.id_rev
//                                    }
//                                }
//                            }
//                        })

                        viewModel.findByUsingTypeId(it01.type)?.observe(this) { resourcesType ->
                            pump.type?.id = resourcesType.id
                            pump.type?.description = resourcesType.name
                        }

                        viewModel.findByBrandId(it01.brandId)?.observe(this) { resourcesBrand ->
                            pump.brand.id = resourcesBrand.id
                            pump.brand.name = resourcesBrand.name
                        }
                        listPump.add(pump)
                    }
                } else {
                    Toast.makeText(this@PumpActivity,
                        R.string.not_found_values_list_pump,
                        Toast.LENGTH_SHORT).show()
                }
            }
            listPumpNotFilter = listPump
            GlobalScope.launch(Dispatchers.Main) {
                delay(1000)
                while (true) {
                    if (listPump.size > 0) {
                        Log.d(tagLog, "Update adapter: ${listPump.size}")
                        adapter.update(listPump)
                        loadingSpinner.visibility = View.GONE
                        return@launch
                    }
                }
            }
        }
    }

    /**
     * Configurações da activity
     */
    private fun configureRecyclerView() {
        val listInjRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_list_pump)
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
        loadingSpinner.visibility = View.VISIBLE
        adapter.whenItemClicked = this::redirectPlanPump
    }

    /**
     * Redireciona para a activity de Planos de Bomba
     */
    private fun redirectPlanPump(it: Pump) {
        val intent = Intent(this, PlanPumpActivity::class.java)
        intent.putExtra(Extra.platform, platform)
        intent.putExtra(Extra.pump, it)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_bombas, menu)
        val menuItem = menu.findItem(R.id.search_bombas)
        val searchView: SearchView = menuItem?.actionView as SearchView
        searchView.queryHint = applicationContext.getString(R.string.type_to_search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isNullOrBlank()
                    || !newText.isNullOrEmpty()
                ) {
                    searchFilter(newText)
                } else {
                    adapter.update(listPumpNotFilter)
                }
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
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

    fun searchFilter(filter: String) {
        var exist: Boolean
        val pumpListFilter: MutableList<Pump> = ArrayList()

        listPumpNotFilter.forEach { pump ->
            exist = false
            if (pump.code != null && pump.code!!.contains(filter)
                || (pump.code != null && pump.code!!.uppercase().contains(filter.uppercase())
                        || pump.code != null && pump.code!!.lowercase().contains(filter.lowercase()))
            ) {
                if (pumpListFilter.isEmpty()) {
                    pumpListFilter.add(pump)
                } else {
                    pumpListFilter.forEach {
                        if (pump.id == it.id) {
                            exist = true
                        }
                    }
                    if (!exist) {
                        pumpListFilter.add(pump)
                    }
                }
            }
        }

        listPumpNotFilter.forEach { pump ->
            exist = false
            if (pump.brand.name != null && pump.brand.name!!.contains(filter)
                || (pump.brand.name != null && pump.brand.name!!.lowercase().contains(filter.lowercase())
                        || pump.brand.name != null && pump.brand.name!!.uppercase().contains(filter.uppercase()))
            ) {
                if (pumpListFilter.isEmpty()) {
                    pumpListFilter.add(pump)
                } else {
                    pumpListFilter.forEach {
                        if (pump.id == it.id) {
                            exist = true
                        }
                    }
                    if (!exist) {
                        pumpListFilter.add(pump)
                    }
                }
            }
        }

        listPumpNotFilter.forEach { pump ->
            exist = false
            if (pump.application != null
                && pump.application!!.contains(filter)
                || (pump.application!!.uppercase().contains(filter.uppercase())
                        || pump.application!!.lowercase().contains(filter.lowercase()))
            ) {
                if (pumpListFilter.isEmpty()) {
                    pumpListFilter.add(pump)
                } else {
                    pumpListFilter.forEach {
                        if (pump.id == it.id) {
                            exist = true
                        }
                    }
                    if (!exist) {
                        pumpListFilter.add(pump)
                    }
                }
            }
        }

        print(pumpListFilter.toString())
        adapter.update(pumpListFilter)
    }

}