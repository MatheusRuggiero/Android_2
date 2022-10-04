package br.com.tecnomotor.marvin.view.relatorios

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.pump.PumpsReportingAdapter
import br.com.tecnomotor.marvin.dao.entities.pump.PumpReportEntity
import br.com.tecnomotor.marvin.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ListRelatoriosActivity : AppCompatActivity(), PumpsReportingAdapter.OnItemClickListener {

    val LISTA_RELATORIOS = "Relatórios"
    private var recyclerViewReports: PumpsReportingAdapter? = null
    var listReport = ArrayList<PumpReportEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_relatorios)

        title = LISTA_RELATORIOS

//        listReport = listRelatorios() as ArrayList<PumpReport>
        val listReportsRecyclerView = settingsReportActivity()
        startActivity(listReportsRecyclerView)
    }

    /**
     * Inicializa a activity
     */
    private fun startActivity(listReportsRecyclerView: RecyclerView?) {
        recyclerViewReports = PumpsReportingAdapter(listReport, applicationContext, this)
        if (listReportsRecyclerView != null) {
            listReportsRecyclerView.adapter = recyclerViewReports
        }
    }

    /**
     * Configurações da activity
     */
    private fun settingsReportActivity(): RecyclerView? {
        val listReportRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_list_relatorios)
        val recyclerLayoutManager = LinearLayoutManager(applicationContext)
        listReportRecyclerView.layoutManager = recyclerLayoutManager
        val dividerItemDecoration =
            DividerItemDecoration(listReportRecyclerView.context, recyclerLayoutManager.orientation)
        listReportRecyclerView.addItemDecoration(dividerItemDecoration)
        return listReportRecyclerView
    }

    /**
     * Adiciona relatórios na lista
     */
//    private fun listRelatorios(): MutableList<PumpReport> {
//        val listRel = mutableListOf<PumpReport>()
//
//        listRel.add(PumpReport("5WS40001", "BOSCH", "Aplicação - 20/03/2021"))
//        listRel.add(PumpReport("0986437014", "DELPHI", "Aplicação - 22/03/2021"))
//        listRel.add(PumpReport("9422A060A", "CARTEPILLAR", "Aplicação - 22/03/2021"))
//        listRel.add(PumpReport("22100-0L020", "DENSO", "Aplicação - 25/03/2021"))
//        listRel.add(PumpReport("5WS40018", "SIEMENS", "Aplicação - 25/03/2021"))
//        listRel.add(PumpReport("0445010010", "BOSCH", "Aplicação - 26/03/2021"))
//        listRel.add(PumpReport("22100-0L020", "DENSO", "Aplicação - 25/03/2021"))
//        listRel.add(PumpReport("5WS40018", "SIEMENS", "Aplicação - 25/03/2021"))
//        listRel.add(PumpReport("0445010010", "BOSCH", "Aplicação - 26/03/2021"))
//
//        return listRel
//    }

    /**
     * Verifica qual relatório foi selecionada
     */
    override fun onItemClick(position: Int) {
        //Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        if (position == 0) {
            redirectInfoRelatorio(
                "5WS40001",
                "Teste Padrão",
                "BOSCH",
                "20/03/2021",
                "1:38 PM"
            )
        }
        if (position == 1) {
            redirectInfoRelatorio(
                "0986437014",
                "Teste Padrão",
                "DELPHI",
                "22/03/2021",
                "5:12 PM"
            )
        }
        if (position == 2) {
            redirectInfoRelatorio(
                "9422A060A",
                "Teste Referencial",
                "CARTEPILLAR",
                "22/03/2021",
                "11:08 AM"
            )
        }
        if (position == 3) {
            redirectInfoRelatorio(
                "22100-0L020",
                "Teste Referencial",
                "DENSO",
                "25/03/2021",
                "7:59 AM"
            )
        }
        if (position == 4) {
            redirectInfoRelatorio(
                "5WS40018",
                "Teste Padrão",
                "SIEMENS",
                "25/03/2021",
                "3:48 PM"
            )
        }
        if (position == 5) {
            redirectInfoRelatorio(
                "0445010010",
                "Teste Referencial",
                "BOSCH",
                "26/03/2021",
                "9:31 AM"
            )
        }
        if (position == 6) {
            redirectInfoRelatorio(
                "22100-0L020",
                "Teste Referencial",
                "DENSO",
                "25/03/2021",
                "7:59 AM"
            )
        }
        if (position == 7) {
            redirectInfoRelatorio(
                "5WS40018",
                "Teste Padrão",
                "SIEMENS",
                "25/03/2021",
                "3:48 PM"
            )
        }
        if (position == 8) {
            redirectInfoRelatorio(
                "0445010010",
                "Teste Referencial",
                "BOSCH",
                "26/03/2021",
                "9:31 AM"
            )
        }
    }

    /**
     * Redireciona para a activity de Relatório de Teste de Dispositivos (View)
     */
    fun redirectInfoRelatorio(
        cod: String?,
        plano: String?,
        marca: String?,
        data: String?,
        hora: String?) {
        val intent = Intent(this, RelatorioTestDispositivoActivity::class.java)
        intent.putExtra(Extra.code, cod)
        intent.putExtra(Extra.plan, plano)
        intent.putExtra(Extra.brandName, marca)
        intent.putExtra(Extra.date, data)
        intent.putExtra(Extra.hour, hora)
        startActivity(intent)
    }
}