package br.com.tecnomotor.marvin.view.injector

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.commonrail.device.commands.injector.EnumInjectorType
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.injector.InjectorAdapter
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.repository.global.BrandRepository
import br.com.tecnomotor.marvin.repository.global.RevisionRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorRepository
import br.com.tecnomotor.marvin.repository.injector.RevisionInjectorRepository
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.viewmodel.factory.injector.module.InjectorViewModelFactory
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.InjectorViewModel
import kotlinx.coroutines.*

@InternalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
class InjectorActivity : AppCompatActivity() {

    private val tagLog: String = this::class.java.simpleName
    private val LISTA_INJETORES = "Lista Injetores"
    private var listInjectorNotFilter: MutableList<Injector> = ArrayList()
    private lateinit var platform: Platform
    private lateinit var loadingSpinner: ProgressBar

    private val adapter by lazy {
        InjectorAdapter(context = this)
    }

    private val viewModel by lazy {
        val injectorRepository = InjectorRepository(application)
        val brandRepository = BrandRepository(application)
        val revisionRepository = RevisionRepository(application)
        val revisionInjectorRepository = RevisionInjectorRepository(application)
        val factory = InjectorViewModelFactory(injectorRepository, brandRepository, revisionRepository, revisionInjectorRepository)
        ViewModelProvider(this, factory)[InjectorViewModel::class.java]
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.injector_activity_list)

        loadingSpinner = findViewById(R.id.loading_spinner)
        loadingSpinner.visibility = View.VISIBLE

        platform = intent.getSerializableExtra(Extra.platform) as Platform

        supportActionBar?.title = LISTA_INJETORES
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_voltar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configureRecyclerView()
        findAll()

    }

    /**
     * Inicializa a activity
     */
    private fun configureRecyclerView() {
        val listInjRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_list_injector)
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
        adapter.whenItemClicked = this::redirectPlanInjector
    }

    /**
     * Listar as informações dos Injetores
     */
    private fun findAll() {
        viewModel.findAllInjector().observe(this) { resources ->
            val list: MutableList<Injector> = ArrayList()
            resources.data?.let { it ->
                if (!it.isNullOrEmpty()) {
                    it.forEach { it1 ->
                        val injector = Injector()
                        injector.id = it1.id
                        injector.code = it1.code
                        injector.application = it1.application
                        injector.adaptConnector = it1.adaptConnector
                        injector.adaptReturn = it1.adaptReturn
                        injector.adaptPressure = it1.adaptPressure
                        injector.type = EnumInjectorType.valueOfDB(it1.tipo).toString()
                        injector.typeId = it1.tipo
                        injector.revisionNumber = it1.revision

                        viewModel.findByBrandId(it1.brandId)?.observe(this) { resourcesBrand ->
                            injector.brand?.id = resourcesBrand.id
                            injector.brand?.name = resourcesBrand.name
                        }

                        viewModel.findByUsingRevisionId(it1.revision)
                            .observe(this) { revisionList ->
                                if (!revisionList.data.isNullOrEmpty()) {
                                    revisionList.data.forEach { revisionEntity ->
                                        if (revisionEntity.id < injector.revision?.id!!) {
                                            injector.revision?.id = revisionEntity.id
                                        }
                                    }
                                }

                                resources.error?.let {
                                    GlobalUtil.method.shortTimeMessageAlert(context = this,
                                        msg = this.getString(R.string.error_load_revision))
                                }
                            }

                        viewModel.findByUsingInjectorId(it1.id)
                            .observe(this) { revisionInjectorList ->
                                if (!revisionInjectorList.data.isNullOrEmpty()) {
                                    revisionInjectorList.data.forEach { revisionInjectorEntity ->
                                        if (revisionInjectorEntity.id_rev < injector.revisionInjector.id_rev!!) {
                                            injector.revisionInjector.id_rev =
                                                revisionInjectorEntity.id_rev
                                        }
                                    }
                                }

                                resources.error?.let {
                                    GlobalUtil.method.shortTimeMessageAlert(context = this,
                                        msg = this.getString(R.string.error_load_revision))
                                }
                            }

                        list.add(injector)
                    }
                    listInjectorNotFilter = list
                    GlobalScope.launch(Dispatchers.Main) {
                        delay(1000)
                        while (true) {
                            if (list.size > 0) {
                                Log.d(tagLog, "Update adapter: ${list.size}")
                                adapter.update(list)
                                loadingSpinner.visibility = View.GONE
                                return@launch
                            }
                        }
                    }
                } else {
//                    val globalScopeMsgNotFoundListInjector = GlobalScope.launch(Dispatchers.IO) {
                    Toast.makeText(this@InjectorActivity,
                        R.string.not_found_values_list_injector,
                        Toast.LENGTH_SHORT).show()
//                    }
//                    globalScopeMsgNotFoundListInjector.cancel()
                }
            }
            resources.error?.let {
                GlobalUtil.method.shortTimeMessageAlert(context = this,
                    msg = this.getString(R.string.error_load_list))
            }
        }

    }

    /**
     * Redireciona para a activity de Planos de Injetor
     */
    private fun redirectPlanInjector(it: Injector) {
        val intent = Intent(this, PlanInjectorActivity::class.java)
        intent.putExtra(Extra.injector, it)
        intent.putExtra(Extra.platform, platform)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_injector, menu)
        val menuItem = menu.findItem(R.id.search_injector)
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
                    adapter.update(listInjectorNotFilter)
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
        val injListFilter: MutableList<Injector> = ArrayList()

        listInjectorNotFilter.forEach { injector ->
            exist = false
            if (injector.code != null && injector.code!!.contains(filter)
                || (injector.code != null && injector.code!!.uppercase().contains(filter.uppercase())
                        || injector.code != null && injector.code!!.lowercase().contains(filter.lowercase()))
            ) {
                if (injListFilter.isEmpty()) {
                    injListFilter.add(injector)
                } else {
                    injListFilter.forEach {
                        if (injector.id == it.id) {
                            exist = true
                        }
                    }
                    if (!exist) {
                        injListFilter.add(injector)
                    }
                }
            }
        }

        listInjectorNotFilter.forEach { injector ->
            exist = false
            if (injector.brand?.name != null && injector.brand?.name!!.contains(filter)
                || (injector.brand?.name != null && injector.brand?.name!!.lowercase().contains(filter.lowercase())
                        || injector.brand?.name != null && injector.brand?.name!!.uppercase().contains(filter.uppercase()))
            ) {
                if (injListFilter.isEmpty()) {
                    injListFilter.add(injector)
                } else {
                    injListFilter.forEach {
                        if (injector.id == it.id) {
                            exist = true
                        }
                    }
                    if (!exist) {
                        injListFilter.add(injector)
                    }
                }
            }
        }

        listInjectorNotFilter.forEach { injector ->
            exist = false
            if ((injector.adaptConnector != null && injector.adaptConnector!!.contains(filter))
                || (injector.adaptReturn != null && injector.adaptReturn!!.contains(filter))
                || (((injector.adaptPressure != null && injector.adaptPressure!!.uppercase().contains(filter.uppercase()))
                        || (injector.adaptReturn != null && injector.adaptReturn!!.uppercase().contains(filter.uppercase())))
                        || ((injector.adaptConnector != null && injector.adaptConnector!!.lowercase().contains(filter.lowercase()))
                        || (injector.adaptPressure != null && injector.adaptPressure!!.lowercase().contains(filter.lowercase()))))
            ) {
                if (injListFilter.isEmpty()) {
                    injListFilter.add(injector)
                } else {
                    injListFilter.forEach {
                        if (injector.id == it.id) {
                            exist = true
                        }
                    }
                    if (!exist) {
                        injListFilter.add(injector)
                    }
                }
            }
        }

        listInjectorNotFilter.forEach { injector ->
            exist = false
            if (injector.application != null
                && injector.application!!.contains(filter)
                || (injector.application!!.uppercase().contains(filter.uppercase())
                        || injector.application!!.lowercase().contains(filter.lowercase()))
            ) {
                if (injListFilter.isEmpty()) {
                    injListFilter.add(injector)
                } else {
                    injListFilter.forEach {
                        if (injector.id == it.id) {
                            exist = true
                        }
                    }
                    if (!exist) {
                        injListFilter.add(injector)
                    }
                }
            }
        }

        listInjectorNotFilter.forEach { injector ->
            exist = false
            if (injector.type.contains(filter) || (injector.type.lowercase().contains(filter.lowercase()) || injector.type.uppercase().contains(filter.uppercase()))
            ) {
                if (injListFilter.isEmpty()) {
                    injListFilter.add(injector)
                } else {
                    injListFilter.forEach {
                        if (injector.id == it.id) {
                            exist = true
                        }
                    }
                    if (!exist) {
                        injListFilter.add(injector)
                    }
                }
            }
        }

        print(injListFilter.toString())
        adapter.update(injListFilter)
    }
}