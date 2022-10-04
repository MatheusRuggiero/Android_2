package br.com.tecnomotor.marvin.adapter.injector

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.generic.injector.PointInjectorGeneric
import java.math.BigDecimal

class InjectorTestPointAdapter(
    private var context: Context,
    private val list: MutableList<PointInjectorGeneric> = mutableListOf(),
    var whenItemClicked: (obj: PointInjectorGeneric) -> Unit = {}
) : RecyclerView.Adapter<InjectorTestPointAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.injector_recyclerview_points, parent, false)
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
        // holder.descricaoPoint.text = list[position].typeTest.description
        val obj = list[position]
        holder.bind(injectorPointTest = obj, obj.statusCheckBox)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var checkBox: CheckBox = view.findViewById<View>(R.id.checkBox_inj) as CheckBox
//        var descricaoPoint: TextView = view.findViewById<View>(R.id.descricao_ponto_inj) as TextView

        private lateinit var injectorPointTest: PointInjectorGeneric

        init {
            itemView.setOnClickListener {
                if (::injectorPointTest.isInitialized) {
                    whenItemClicked(injectorPointTest)
                }
            }

            itemView.findViewById<CheckBox>(R.id.checkBox_inj).setOnClickListener {
                if (::injectorPointTest.isInitialized) {
                    whenItemClicked(injectorPointTest)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(injectorPointTest: PointInjectorGeneric, checkbox: Boolean) {
            this.injectorPointTest = injectorPointTest
            var descriptionInjector = ""

            if (injectorPointTest.pointTestInjector?.typePointTestInjector?.description != null
                && injectorPointTest.pointTestInjector?.typePointTestInjector?.description!!.trim()
                    .isNotEmpty()
                && injectorPointTest.pointTestInjector?.typePointTestInjector?.description!!.trim() != "0"
            ) {
                descriptionInjector =
                    "$descriptionInjector ${injectorPointTest.pointTestInjector?.typePointTestInjector?.description} "
            }

            if (injectorPointTest.pointTestInjector?.originPoint != null
                && injectorPointTest.pointTestInjector?.originPoint!!.trim().isNotEmpty()
                && injectorPointTest.pointTestInjector?.originPoint!!.trim() != "0"
            ) {
                descriptionInjector =
                    " ${injectorPointTest.pointTestInjector?.originPoint} - $descriptionInjector"
            }

            if (injectorPointTest.pointTestInjector?.injectionMinimum != null
                && injectorPointTest.pointTestInjector?.injectionMaximum != null
                && injectorPointTest.pointTestInjector?.injectionMaximum!! > BigDecimal(0.0)
            ) {
                descriptionInjector =
                    "$descriptionInjector / ${context.resources.getString(R.string.abbreviation_injection_rate_1)} (${injectorPointTest.pointTestInjector?.injectionMinimum}/${injectorPointTest.pointTestInjector?.injectionMaximum})"
            }

            if (injectorPointTest.pointTestInjector?.returnMinimum != null
                && injectorPointTest.pointTestInjector?.returnMaximum != null
                && injectorPointTest.pointTestInjector?.returnMaximum!! > BigDecimal(0.0)
            ) {
                descriptionInjector =
                    "$descriptionInjector / ${context.resources.getString(R.string.abbreviation_return_1)} (${injectorPointTest.pointTestInjector?.returnMinimum}/${injectorPointTest.pointTestInjector?.returnMaximum})"
            }

            if (injectorPointTest.pointTestInjector?.originPoint2 != null
                && injectorPointTest.pointTestInjector?.originPoint2!!.trim().isNotEmpty()
                && injectorPointTest.pointTestInjector?.originPoint2!!.trim() != "0"
            ) {
                descriptionInjector =
                    " $descriptionInjector -  ${context.resources.getString(R.string.abbreviation_source_2)} ${injectorPointTest.pointTestInjector?.originPoint2} "
            }


            if (injectorPointTest.pointTestInjector?.injectionMinimum2 != null
                && injectorPointTest.pointTestInjector?.injectionMaximum2 != null
                && injectorPointTest.pointTestInjector?.injectionMaximum2!! > BigDecimal(0.0)
            ) {
                descriptionInjector =
                    "$descriptionInjector / ${context.resources.getString(R.string.abbreviation_injection_rate_1)} (${injectorPointTest.pointTestInjector?.injectionMinimum2}/${injectorPointTest.pointTestInjector?.injectionMaximum2})"
            }

            if (injectorPointTest.pointTestInjector?.returnMinimum2 != null
                && injectorPointTest.pointTestInjector?.returnMaximum2 != null
                && injectorPointTest.pointTestInjector?.returnMaximum2!! > BigDecimal(0.0)
            ) {
                descriptionInjector =
                    "$descriptionInjector / ${context.resources.getString(R.string.abbreviation_return_2)} (${injectorPointTest.pointTestInjector?.returnMinimum2}/${injectorPointTest.pointTestInjector?.returnMaximum2})"
            }

            itemView.findViewById<TextView>(R.id.descricao_ponto_inj).text = descriptionInjector
            itemView.findViewById<CheckBox>(R.id.checkBox_inj).isChecked = checkbox
        }

    }

    fun update(list: List<PointInjectorGeneric>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }

    /**
     * Atualiza apenas um item
     */
    fun update(position: Int) {
        notifyItemChanged(position)
    }

}