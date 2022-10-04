package br.com.tecnomotor.marvin.adapter.injector

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.generic.injector.PointTestInjectorExecuteGeneric
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.utils.objects.FormatTestResults

@SuppressLint("SetTextI18n")
class PointTestInjectorAdapter(
    private val context: Context,
    private val list: MutableList<PointTestInjectorExecuteGeneric> = mutableListOf()
) : RecyclerView.Adapter<PointTestInjectorAdapter.ViewHolder>() {

    private var tag = "PointTestInjectorAdapter"
    var bindData: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointTestInjectorAdapter.ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(
                R.layout.point_test_injector_recyclerview2,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PointTestInjectorAdapter.ViewHolder, position: Int) {
        val pointTestInjector = list[position]
        if(bindData)
            holder.bind(pointTestInjector, position)
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
        private lateinit var pointTestInjExecGeneric: PointTestInjectorExecuteGeneric

        init {
        }

        fun bind(pointTestInjectorExecuteGeneric: PointTestInjectorExecuteGeneric, position: Int) {
            pointTestInjExecGeneric = PointTestInjectorExecuteGeneric()
            pointTestInjExecGeneric.pointView = itemView
            pointTestInjExecGeneric.pointTestInjector = pointTestInjectorExecuteGeneric.pointTestInjector
            pointTestInjExecGeneric.pieChartViewPointTestInjectorExecuteGeneric = itemView.findViewById(R.id.point_test_injector_pie_chart)
            pointTestInjExecGeneric.chartFlowPointTestInjectorExecuteGeneric = itemView.findViewById(R.id.chart_flow_point_test_injector)
            pointTestInjExecGeneric.chartReturnPointTestInjectorExecuteGeneric = itemView.findViewById(R.id.chart_return_point_test_injector)
            pointTestInjExecGeneric.flowPoint = itemView.findViewById(R.id.flow_point_test_injector)
            pointTestInjExecGeneric.flowPointValue = itemView.findViewById(R.id.value_flow_point_test_injector)
            pointTestInjExecGeneric.flowPointMinMax = itemView.findViewById(R.id.value_flow_min_or_max_point_test_injector)
            pointTestInjExecGeneric.frequencyPoint = itemView.findViewById(R.id.frequency_point_test_injector)
            pointTestInjExecGeneric.frequencyPointValue = itemView.findViewById(R.id.value_frequency_point_test_injector)
            pointTestInjExecGeneric.namePoint = itemView.findViewById(R.id.name_point_test_injector)
            pointTestInjExecGeneric.returnPoint = itemView.findViewById(R.id.return_point_test_injector)
            pointTestInjExecGeneric.returnPointValue = itemView.findViewById(R.id.value_return_point_test_injector)
            pointTestInjExecGeneric.returnPointMinMax = itemView.findViewById(R.id.value_return_min_or_max_point_test_injector)
            // pointTestInjExecGeneric.statusPoint = itemView.findViewById(R.id.status_point_test_injector) isso estava no point_test_injector_recyclerview e não estava no point_test_injector_recyclerview2
            pointTestInjExecGeneric.statusPointValue = itemView.findViewById(R.id.value_status_point_test_injector)
            pointTestInjExecGeneric.timeInjectionPoint = itemView.findViewById(R.id.time_injection_point_test_injector)
            pointTestInjExecGeneric.timeInjectionPointValue = itemView.findViewById(R.id.value_time_injection_point_test_injector)

            pointTestInjExecGeneric.groupTimePoint = itemView.findViewById(R.id.group_time_point_test_injector)
            pointTestInjExecGeneric.timePoint = itemView.findViewById(R.id.time_point_test_injector)
            pointTestInjExecGeneric.timePointValue = itemView.findViewById(R.id.value_time_point_test_injector)

            pointTestInjExecGeneric.legendChartFlowPointTestInjectorExecuteGeneric = itemView.findViewById(R.id.legend_chart_flow_point_test_injector)
            pointTestInjExecGeneric.legendChartReturnPointTestInjectorExecuteGeneric = itemView.findViewById(R.id.legend_chart_return_point_test_injector)
            pointTestInjExecGeneric.pressurePointValue = itemView.findViewById(R.id.value_pressure_point_test_injector)

            //pointTestInjExecGeneric.backgroundValuesPointTestInjector = itemView.findViewById(R.id.background_values_point_test_injector)
            pointTestInjExecGeneric.groupFlowPointTestInjector = itemView.findViewById(R.id.group_flow_point_test_pump)
            pointTestInjExecGeneric.groupReturnPointTestInjector = itemView.findViewById(R.id.group_return_point_test_injector)

            pointTestInjExecGeneric.statusPointValue!!.text = ""
            pointTestInjExecGeneric.legendChartFlowPointTestInjectorExecuteGeneric!!.text = context.getString(R.string.txt_flow)
            pointTestInjExecGeneric.legendChartReturnPointTestInjectorExecuteGeneric!!.text = context.getString(R.string.txt_return)
            pointTestInjExecGeneric.pressurePointValue!!.text = context.getString(R.string.txt_pressure_rail)

            if (pointTestInjExecGeneric.pointTestInjector?.frequency!! > 0) {
                pointTestInjExecGeneric.frequencyPoint?.visibility = View.VISIBLE
                pointTestInjExecGeneric.frequencyPointValue?.text =
                    FormatTestResults.FORMAT_FREQUENCY.format((pointTestInjExecGeneric.pointTestInjector?.frequency!!.toDouble() / 10.0))
            } else {
                pointTestInjExecGeneric.frequencyPoint?.visibility = View.INVISIBLE
                pointTestInjExecGeneric.frequencyPointValue?.visibility = View.INVISIBLE
            }

            if (!pointTestInjExecGeneric.pointTestInjector?.typePointTestInjector?.description!!.isNullOrEmpty()) {
                pointTestInjExecGeneric.namePoint?.visibility = View.VISIBLE
                var origenPointName: String = pointTestInjExecGeneric.pointTestInjector?.originPoint.toString()
                if (origenPointName.isNotEmpty())
                    origenPointName += " - "
                if (pointTestInjExecGeneric.pointTestInjector?.typePointTestInjector?.isHeating() == true) {
                    pointTestInjExecGeneric.groupTimePoint?.visibility = View.VISIBLE
                    pointTestInjExecGeneric.timePoint?.text = context.getString(R.string.txt_time) + " (${"%02d".format(pointTestInjExecGeneric.pointTestInjector?.timeTest)})"
                } else pointTestInjExecGeneric.groupTimePoint?.visibility = View.GONE
                pointTestInjExecGeneric.namePoint?.text = origenPointName + pointTestInjExecGeneric.pointTestInjector?.typePointTestInjector?.description ?: ""
            } else {
                pointTestInjExecGeneric.namePoint?.visibility = View.INVISIBLE
            }

            pointTestInjExecGeneric.pressurePointValue?.text = FormatTestResults.FORMAT_PRESSURE.format(pointTestInjExecGeneric.pointTestInjector?.pressure)

            if (pointTestInjExecGeneric.pointTestInjector?.timeInjection!! > 0) {
                pointTestInjExecGeneric.timeInjectionPoint?.visibility = View.VISIBLE
                pointTestInjExecGeneric.timeInjectionPointValue?.text = FormatTestResults.FORMAT_TIME_INJ.format(pointTestInjExecGeneric.pointTestInjector?.timeInjection)
            } else {
                pointTestInjExecGeneric.timeInjectionPoint?.visibility = View.INVISIBLE
                pointTestInjExecGeneric.timeInjectionPointValue?.visibility = View.INVISIBLE
            }

            if (pointTestInjExecGeneric.pointTestInjector?.typePointTestInjector?.isHeating() == true) {
                //pointTestInjExecGeneric.backgroundValuesPointTestInjector?.visibility = View.GONE
                pointTestInjExecGeneric.groupFlowPointTestInjector?.visibility = View.GONE
                pointTestInjExecGeneric.groupReturnPointTestInjector?.visibility = View.GONE
            } else {
                if (!pointTestInjExecGeneric.pointTestInjector?.measureReturn!!) {
                    pointTestInjExecGeneric.groupReturnPointTestInjector?.visibility = View.GONE
                } else {
                    pointTestInjExecGeneric.groupReturnPointTestInjector?.visibility = View.VISIBLE
                    pointTestInjExecGeneric.returnPointValue?.text = FormatTestResults.FORMAT_MILLILITER.format(0.0)
                    pointTestInjExecGeneric.returnPointMinMax?.text = FormatTestResults.FORMAT_MILLILITER.format(pointTestInjExecGeneric.pointTestInjector?.returnMinimum) + " / " +
                            FormatTestResults.FORMAT_MILLILITER.format(pointTestInjExecGeneric.pointTestInjector?.returnMaximum)
                }
                if (!pointTestInjExecGeneric.pointTestInjector?.measureInjection!!) {
                    pointTestInjExecGeneric.groupFlowPointTestInjector?.visibility = View.GONE
                } else {
                    pointTestInjExecGeneric.groupFlowPointTestInjector?.visibility = View.VISIBLE
                    pointTestInjExecGeneric.flowPointValue?.text = FormatTestResults.FORMAT_MILLILITER.format(0.0)
                    pointTestInjExecGeneric.flowPointMinMax?.text =
                        FormatTestResults.FORMAT_MILLILITER.format(pointTestInjExecGeneric.pointTestInjector?.injectionMinimum) + " / " +
                                FormatTestResults.FORMAT_MILLILITER.format(pointTestInjExecGeneric.pointTestInjector?.injectionMaximum)
                }
            }

            GlobalUtil.method.settingValueBarChart(
                pointTestInjExecGeneric.chartFlowPointTestInjectorExecuteGeneric!!,
                0f,
                pointTestInjExecGeneric.pointTestInjector?.injectionMinimum?.toFloat() ?: 0f,
                pointTestInjExecGeneric.pointTestInjector?.injectionMaximum?.toFloat() ?: 0f,
                pointTestInjExecGeneric.pointTestInjector?.getInjectionMinimumGraph() ?: 0f,
                pointTestInjExecGeneric.pointTestInjector?.getInjectionMaximumGraph() ?: 0f
            )

            GlobalUtil.method.settingValueBarChart(
                pointTestInjExecGeneric.chartReturnPointTestInjectorExecuteGeneric!!,
                0f,
                pointTestInjExecGeneric.pointTestInjector?.returnMinimum?.toFloat() ?: 0f,
                pointTestInjExecGeneric.pointTestInjector?.returnMaximum?.toFloat() ?: 0f,
                pointTestInjExecGeneric.pointTestInjector?.getReturnMinimumGraph() ?: 0f,
                pointTestInjExecGeneric.pointTestInjector?.getReturnMaximumGraph() ?: 0f
            )

            GlobalUtil.method.settingValuePieChart(
                pointTestInjExecGeneric.pieChartViewPointTestInjectorExecuteGeneric!!,
                context,
                2000f,
                0f,
                "0"
            )

            list[position] = pointTestInjExecGeneric

            Log.i(tag, "Index: $position :: ${list[position].namePoint?.text}")

//            if (isLastPosition) {
//                btnNextOrFinishPointTestInjectorExecuteGeneric.setOnClickListener {
////                    TODO implementation action to function cancel test in point selected
//                }
//                imgViewNextOrFinishPointTestInjectorExecuteGeneric.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_ok))
//                textPausePoint.text = context.getString(R.string.txt_finish)
//
////                btnNextOrFinishPointTestInjectorExecuteGeneric.visibility = View.INVISIBLE
////                imgViewNextOrFinishPointTestInjectorExecuteGeneric.visibility = View.INVISIBLE
////                textNextOrFinishPointTestInjectorExecuteGeneric.visibility = View.INVISIBLE
//
//            } else {
////                TODO implementation action to function pause test in point selected
//            }

        }
    }

    fun onAttachRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    fun getObjectView(position: Int): View? {
        return list[position].pointView
    }

    fun getPointTestInjectorView(position: Int): PointTestInjectorExecuteGeneric? {
        return if (position < list.size)
            list[position]
        else null
    }


    fun update(list: List<PointTestInjectorExecuteGeneric>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }
}