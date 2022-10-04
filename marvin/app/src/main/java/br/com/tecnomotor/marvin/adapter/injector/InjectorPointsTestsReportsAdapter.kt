package br.com.tecnomotor.marvin.adapter.injector

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.model.injector.PointTestInjector

class InjectorPointsTestsReportsAdapter(
    private var context: Context,
    private val list: MutableList<PointTestInjector> = mutableListOf(),
    var whenItemClicked: (obj: PointTestInjector) -> Unit = {}
) : RecyclerView.Adapter<InjectorPointsTestsReportsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.injector_recyclerview_points, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pointTestInjector = list[position]
        holder.bind(pointTestInjector)
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var pointTestInjector: PointTestInjector

        init {
            itemView.setOnClickListener {
                if (::pointTestInjector.isInitialized) {
                    whenItemClicked(pointTestInjector)
                }
            }
        }

        fun bind(pointTestInjector: PointTestInjector) {
            this.pointTestInjector = pointTestInjector
            itemView.findViewById<TextView>(R.id.descricao_ponto_inj).text = pointTestInjector.typePointTestInjector?.description
        }
    }

    fun update(list: List<PointTestInjector>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }
}