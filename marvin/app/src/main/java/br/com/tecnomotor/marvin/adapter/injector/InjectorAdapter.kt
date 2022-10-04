package br.com.tecnomotor.marvin.adapter.injector

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.model.injector.Injector

class InjectorAdapter(
    private var context: Context,
    private val list: MutableList<Injector> = mutableListOf(),
    var whenItemClicked: (obj: Injector) -> Unit = {}
) : RecyclerView.Adapter<InjectorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.injector_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val injector = list[position]
        holder.bind(injector)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var injector: Injector

        init {
            itemView.setOnClickListener {
                if (::injector.isInitialized) {
                    whenItemClicked(injector)
                }
            }
        }

        fun bind(injector: Injector) {
            this.injector = injector
            val adapterInjector = itemView.findViewById<TextView>(R.id.adapter_injector)
            val applicationInjector = itemView.findViewById<TextView>(R.id.application_injector)
            val brandInjector = itemView.findViewById<TextView>(R.id.brand_injector)
            val codeInjector = itemView.findViewById<TextView>(R.id.code_injector)
            val revisionInjector = itemView.findViewById<TextView>(R.id.revision_injector)
            val typeInjector = itemView.findViewById<TextView>(R.id.type_injector)

            var labelPressure: String = context.getString(R.string.label_pressure)
            var labelConector: String = context.getString(R.string.label_connector)
            var labelReturn: String = context.getString(R.string.label_return)
            labelPressure += if (injector.adaptPressure != null && injector.adaptPressure!!.isNotEmpty())
                " ${injector.adaptPressure}" else " - "
            labelReturn += if (injector.adaptReturn != null && injector.adaptReturn!!.isNotEmpty())
                " ${injector.adaptReturn}" else " - "
            labelConector += if (injector.adaptConnector != null && injector.adaptConnector!!.isNotEmpty())
                " ${injector.adaptConnector}" else " - "
            adapterInjector.text = "%s | %s | %s".format(labelPressure,labelReturn,labelConector)

            codeInjector.text = injector.code
            applicationInjector.text = injector.application
            typeInjector.text = injector.type

            brandInjector.text = if (injector.brand != null && !injector.brand?.name.isNullOrEmpty())
                injector.brand?.name else " - "

            revisionInjector.text = injector.revisionNumber.toString()
        }
    }

    fun update(list: List<Injector>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }

}