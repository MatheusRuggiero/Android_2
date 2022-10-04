package br.com.tecnomotor.marvin.adapter.pump

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.dao.entities.pump.PumpReportEntity

class PumpsReportingAdapter(
    private var list: List<PumpReportEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<PumpsReportingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //val view : View? = null
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_relatorios, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reports = list[position]
       // holder.code.text = reports.code
       // holder.application.text = reports.application
       // holder.brand.text = reports.brand
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

    fun setData(list1: List<PumpReportEntity>) {
        list = ArrayList()
        if (!list1.isNullOrEmpty()) {
            list1.forEach {
                (list as ArrayList<PumpReportEntity>).add(it)
            }
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var code: TextView = view.findViewById<View>(R.id.rel_list_codigo) as TextView
        var application: TextView = view.findViewById<View>(R.id.rel_list_aplicacao) as TextView
        var brand: TextView = view.findViewById<View>(R.id.rel_list_marca) as TextView

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}