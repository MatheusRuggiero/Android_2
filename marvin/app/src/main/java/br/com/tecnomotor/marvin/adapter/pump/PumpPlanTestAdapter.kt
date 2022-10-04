package br.com.tecnomotor.marvin.adapter.pump

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.model.pump.PumpPlanTest

class PumpPlanTestAdapter(
    private var context: Context,
    private val list: MutableList<PumpPlanTest> = mutableListOf(),
    var whenItemClicked: (obj: PumpPlanTest) -> Unit = {}
) : RecyclerView.Adapter<PumpPlanTestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pump_recyclerview_plan_test, parent, false)
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
        val pump = list[position]
        holder.bind(pump)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var pumpPlanTest: PumpPlanTest

        init {
            itemView.setOnClickListener {
                if (::pumpPlanTest.isInitialized) {
                    whenItemClicked(pumpPlanTest)
                }
            }
        }

        fun bind(pumpPlanTest: PumpPlanTest) {
            this.pumpPlanTest = pumpPlanTest
            itemView.findViewById<TextView>(R.id.description_plan_test_pump).text = pumpPlanTest.typePlanTest?.description
        }
    }

    fun update(list: List<PumpPlanTest>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }

}