package br.com.tecnomotor.marvin.adapter.pump

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.model.pump.Pump

class PumpsAdapter(
    private var context: Context,
    private val list: MutableList<Pump> = mutableListOf(),
    var whenItemClicked: (obj: Pump) -> Unit = {}
) : RecyclerView.Adapter<PumpsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pump_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bombs = list[position]
        holder.bind(bombs)
        // holder.codePumps.text = bombs.code
        // holder.applicationBombs.text = bombs.application
        // holder.description.text = bombs.description
        // holder.brand.text = bombs.brand.name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var codePump: TextView = view.findViewById<View>(R.id.codigo_pump) as TextView
        var applicationPump: TextView = view.findViewById<View>(R.id.application_pump) as TextView
        var brand: TextView = view.findViewById<View>(R.id.brand_pump) as TextView
        var revision: TextView = view.findViewById<View>(R.id.revision_pump) as TextView
        var adapter: TextView = view.findViewById<View>(R.id.adapter_pump) as TextView
        var typePump: TextView = view.findViewById<View>(R.id.type_pump) as TextView

        private lateinit var pump: Pump

        init {
            itemView.setOnClickListener {
                if (::pump.isInitialized) {
                    whenItemClicked(pump)
                }
            }
        }

        fun bind(pump: Pump) {
            this.pump = pump
            var str = ""

            var labelAdaptPressure: String = context.getString(R.string.label_adapt_pressao)
            var labelAdaptEletrico: String = context.getString(R.string.label_adapt_eletrico)
            var labelAdaptReturn: String = context.getString(R.string.label_adapt_returno)
            var labelAdaptMecanico: String = context.getString(R.string.label_adapt_mecanico)
            var labelAdaptAlimentacao: String = context.getString(R.string.label_adapt_alimentacao)

            labelAdaptPressure += if (pump.adapterHhydraulicPressure != null && pump.adapterHhydraulicPressure!!.isNotEmpty())
                " ${pump.adapterHhydraulicPressure}" else " - "
            labelAdaptEletrico += if (pump.adapterElectric != null && pump.adapterElectric!!.isNotEmpty())
                " ${pump.adapterElectric}" else " - "
            labelAdaptReturn += if (pump.adapterHydraulicReturn != null && pump.adapterHydraulicReturn!!.isNotEmpty())
                " ${pump.adapterHydraulicReturn}" else " - "
            labelAdaptMecanico += if (pump.adapterMechanical != null && pump.adapterMechanical!!.isNotEmpty())
                " ${pump.adapterMechanical}" else " - "
            labelAdaptAlimentacao += if (pump.adapterHydraulicPower != null && pump.adapterHydraulicPower!!.isNotEmpty())
                " ${pump.adapterHydraulicPower}" else " - "
            adapter.text = " (%s | %s | %s | %s | %s)".format(labelAdaptEletrico, labelAdaptPressure, labelAdaptAlimentacao, labelAdaptReturn, labelAdaptMecanico)

            if (pump.code != null && pump.code!!.isNotEmpty()) {
                str = "${pump.code}"
                codePump.text = str
            }

            if (pump.application != null && pump.application!!.isNotEmpty()) {
                str = "${pump.application}"
                applicationPump.text = str
            }

            if (pump.brand.name!!.isNotEmpty()) {
                str = "${pump.brand.name}"
                brand.text = str
            }

            if (pump.revision?.id != null) {
                str = "${pump.revision?.id}"
                revision.text = str
            }

            if (pump.type?.id != null) {
                str = "${pump.type?.description}"
                typePump.text = str
            }
        }
    }

    fun update(list: List<Pump>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }
}