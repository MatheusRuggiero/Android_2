package br.com.tecnomotor.marvin.adapter.valve

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.dao.entities.valve.ValveEntity

class ValvulasAdapter(
    private var filterList: List<ValveEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<ValvulasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Mudar Layout
        val view: View? = null
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_bomb_points, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val valvulas = filterList[position]
        //holder.codeBombs.text = bombs.codigo
        //holder.applicationBombs.text = bombs.aplicacao
        //holder.description.text = bombs.descricao
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        //var codeBombs: TextView = view.findViewById<View>(R.id.codigo) as TextView
        //var applicationBombs: TextView = view.findViewById<View>(R.id.aplicacao) as TextView
        //var description: TextView = view.findViewById<View>(R.id.descricao) as TextView

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

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setData(listValves: List<ValveEntity>) {
        filterList = ArrayList()
        if (!listValves.isNullOrEmpty()) {
            listValves.forEach {
                (filterList as ArrayList<ValveEntity>).add(it)
            }
            notifyDataSetChanged()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}