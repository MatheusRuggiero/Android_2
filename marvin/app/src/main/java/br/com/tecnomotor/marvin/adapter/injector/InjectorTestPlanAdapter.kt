package br.com.tecnomotor.marvin.adapter.injector

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.model.injector.PlanTestInjector

class InjectorTestPlanAdapter(
    private var context: Context,
    private val list: MutableList<PlanTestInjector> = mutableListOf(),
    var whenItemClicked: (obj: PlanTestInjector) -> Unit = {}
) : RecyclerView.Adapter<InjectorTestPlanAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.injector_recyclerview_plans, parent, false)
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
        val obj = list[position]
        holder.bind(planTestInjector = obj)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var planTestInjector: PlanTestInjector

        init {
            itemView.setOnClickListener {
                if (::planTestInjector.isInitialized) {
                    whenItemClicked(planTestInjector)
                }
            }
        }

        fun bind(planTestInjector: PlanTestInjector) {
            this.planTestInjector = planTestInjector
            itemView.findViewById<TextView>(R.id.description_plans_test_injector).text = planTestInjector.typePlanTest?.description
        }
    }

    fun update(list: List<PlanTestInjector>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }
}