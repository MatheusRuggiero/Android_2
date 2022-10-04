package br.com.tecnomotor.marvin.adapter.sensor

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.dao.entities.sensor.TestSensorEntity

class TestsSensoresAdapter(
    private var filterList: List<TestSensorEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<TestsSensoresAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //Mudar Layout
        val view: View? = null
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_bomb_points, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

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

    fun setData(listTestsSensores: List<TestSensorEntity>) {
        filterList = ArrayList()
        if (!listTestsSensores.isNullOrEmpty()) {
            listTestsSensores.forEach {
                (filterList as ArrayList<TestSensorEntity>).add(it)
            }
            notifyDataSetChanged()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}