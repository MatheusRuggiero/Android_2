package br.com.tecnomotor.marvin.adapter.pump

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.generic.pump.PointTestPumpExecuteGeneric
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.utils.objects.FormatTestResults

@SuppressLint("SetTextI18n")
class PointTestPumpAdapter(
    private var context: Context,
    private val list: MutableList<PointTestPumpExecuteGeneric> = mutableListOf(),
) : RecyclerView.Adapter<PointTestPumpAdapter.ViewHolder>() {

    private var tag = this::class.java.simpleName
    var bindData: Boolean = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PointTestPumpAdapter.ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(
                R.layout.point_test_pump_recyclerview2,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PointTestPumpAdapter.ViewHolder, position: Int) {
        val pointTestPump = list[position]
        if (bindData)
            holder.bind(pointTestPump, position)
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
        private lateinit var view: PointTestPumpExecuteGeneric
        private var values: PointTestPump? = null

        fun bind(pointTestPumpExecuteGeneric: PointTestPumpExecuteGeneric, position: Int) {
            view = PointTestPumpExecuteGeneric()
            view.pointTestPump = pointTestPumpExecuteGeneric.pointTestPump
            values = view.pointTestPump
            view.pointView = itemView
            view.currentPointExt1 = itemView.findViewById(R.id.pump_test_current_ext_1)
            view.currentPointValueExt1 = itemView.findViewById(R.id.value_pump_test_current_ext_1)
            view.currentPointExt2 = itemView.findViewById(R.id.pump_test_current_ext_2)
            view.currentPointValueExt2 = itemView.findViewById(R.id.value_pump_test_current_ext_2)
            view.namePoint = itemView.findViewById(R.id.name_point_test_pump)
            view.statusPointValue = itemView.findViewById(R.id.value_status_point_test_pump)
            view.errorControlPointValue = itemView.findViewById(R.id.value_error_control_point_test_pump)
            view.errorMeasurePointValue = itemView.findViewById(R.id.value_error_measure_point_test_pump)
            view.pieChartViewPointTestPumpExecuteGeneric = itemView.findViewById(R.id.point_test_pump_pie_chart)
            view.chartFlowPointTestPumpExecuteGeneric = itemView.findViewById(R.id.chart_flow_point_test_pump)
            view.chartReturnPointTestPumpExecuteGeneric = itemView.findViewById(R.id.chart_return_point_test_pump)
            view.groupTimePoint = itemView.findViewById(R.id.group_time_point_test_pump)
            view.timePoint = itemView.findViewById(R.id.time_point_test_pump)
            view.timePointValue = itemView.findViewById(R.id.value_time_point_test_pump)
            view.legendChartFlowPointTestPumpExecuteGeneric = itemView.findViewById(R.id.legend_chart_flow_point_test_pump)
            view.legendChartReturnPointTestPumpExecuteGeneric = itemView.findViewById(R.id.legend_chart_return_point_test_pump)
            view.pressurePointValue = itemView.findViewById(R.id.value_pressure_point_test_pump)

            view.groupFlowPointTest = itemView.findViewById(R.id.group_flow_point_test_pump)
            view.groupReturnPointTest = itemView.findViewById(R.id.group_return_point_test_pump)
            view.groupCalRotationFlow = itemView.findViewById(R.id.group_calc_rot_flow_point_test_pump)

            view.frameRotation = itemView.findViewById(R.id.frame_rotation)
            view.frameRotationChart = itemView.findViewById(R.id.pieChartRotation)
            view.frameRotationValue = itemView.findViewById(R.id.value_rotation)
            view.frameRotationTextInfo = itemView.findViewById(R.id.txt_info_rotation)
            view.frameRotationBtnCancel = itemView.findViewById(R.id.btn_cancel_rotation)

            view.rotationLabel = itemView.findViewById(R.id.pump_test_rotation)
            view.rotationValue = itemView.findViewById(R.id.value_pump_test_rotation)
            view.pressureFeedValue = itemView.findViewById(R.id.value_pump_test_pressure_feed)
            view.temperatureValue = itemView.findViewById(R.id.value_temperature_test_pump)
            view.flowValue = itemView.findViewById(R.id.value_flow_point_test_pump)
            view.flowMaxMinValue = itemView.findViewById(R.id.value_flow_min_or_max_point_test_pump)
            view.returnValue = itemView.findViewById(R.id.value_return_point_test_pump)
            view.returnMaxMinValue = itemView.findViewById(R.id.value_return_min_or_max_point_test_pump)
            view.ml100RotValue = itemView.findViewById(R.id.value_pump_test_calc_rot_vaz)

            if (values?.measureMain!!) {
                view.groupFlowPointTest?.visibility = View.VISIBLE
                view.groupCalRotationFlow?.visibility = View.VISIBLE
            } else {
                view.groupFlowPointTest?.visibility = View.GONE
                view.groupCalRotationFlow?.visibility = View.GONE
            }

            if (values?.measureReturn!!)
                view.groupReturnPointTest?.visibility = View.VISIBLE
            else
                view.groupReturnPointTest?.visibility = View.GONE

            // exibe tempo se ambos main e retorno não são exibidos
            if ((!values?.measureMain!!) && (!values?.measureReturn!!)) {
                view.groupTimePoint?.visibility = View.VISIBLE
                view.groupCalRotationFlow?.visibility = View.GONE
            } else {
                view.groupTimePoint?.visibility = View.GONE
            }

            //pointTestPumpExecGeneric.statusPointValue!!.text = ""
            view.legendChartFlowPointTestPumpExecuteGeneric!!.text = context.getString(R.string.txt_flow)
            view.legendChartReturnPointTestPumpExecuteGeneric!!.text = context.getString(R.string.txt_return)
            view.pressurePointValue?.text = FormatTestResults.FORMAT_PRESSURE.format(view.pointTestPump?.pressureTest)

            view.timePoint?.text = view.timePoint?.text.toString() + " (${FormatTestResults.FORMAT_TIME_TEST.format(view.pointTestPump?.timeWaitingMeasurement)})"
            view.currentPointValueExt1?.text = FormatTestResults.FORMAT_AMP.format(view.pointTestPump?.currentExt1)
            view.currentPointValueExt2?.text = FormatTestResults.FORMAT_AMP.format(view.pointTestPump?.currentExt2)
            view.rotationLabel?.text = view.rotationLabel?.text.toString() + " (${FormatTestResults.FORMAT_RPM.format(view.pointTestPump?.rotation)})"
            view.rotationValue?.text = FormatTestResults.FORMAT_RPM.format(0)
            view.pressureFeedValue?.text = FormatTestResults.FORMAT_PRESSURE_FEED.format(view.pointTestPump?.pressureFeed)
            view.temperatureValue?.text = FormatTestResults.FORMAT_TEMP_CELCIUS.format(0)
            view.flowValue?.text = FormatTestResults.FORMAT_LITERS_HOURS.format(0.0)
            view.flowMaxMinValue?.text = "${FormatTestResults.FORMAT_LITERS_HOURS.format(values?.getMinFlowMain())} / ${FormatTestResults.FORMAT_LITERS_HOURS.format(values?.getMaxFlowMain())}"
            view.returnValue?.text = FormatTestResults.FORMAT_LITERS_HOURS.format(0.0)
            view.returnMaxMinValue?.text =  "${FormatTestResults.FORMAT_LITERS_HOURS.format(values?.getMinFlowReturn())} / ${FormatTestResults.FORMAT_LITERS_HOURS.format(values?.getMaxFlowReturn())}"
            view.ml100RotValue?.text = FormatTestResults.FORMAT_ML_100_ROT.format(0.0)

            if (!values?.typePointTest?.description.isNullOrEmpty()) {
                view.namePoint?.visibility = View.VISIBLE
                var origenPointName: String = values?.nameGeneric.toString()
                if (origenPointName.isNotEmpty())
                    origenPointName += " - "
                view.namePoint?.text = origenPointName + values?.typePointTest?.description
            } else
                view.namePoint?.visibility = View.INVISIBLE

            GlobalUtil.method.settingValueBarChart(
                view.chartFlowPointTestPumpExecuteGeneric!!,
                0f,
                values?.getMinFlowMain() ?: 0f,
                values?.getMaxFlowMain() ?: 0f,
                values?.getFlowMainMinimumGraph() ?: 0f,
                values?.getFlowMainMaximumGraph() ?: 0f
            )

            GlobalUtil.method.settingValueBarChart(
                view.chartReturnPointTestPumpExecuteGeneric!!,
                0f,
                values?.getMinFlowReturn() ?: 0f,
                values?.getMaxFlowReturn() ?: 0f,
                values?.getFlowReturnMinimumGraph() ?: 0f,
                values?.getFlowReturnMaximumGraph() ?: 0f
            )

            GlobalUtil.method.settingValuePieChart(
                view.pieChartViewPointTestPumpExecuteGeneric!!,
                context,
                2000f,
                0f,
                "0"
            )

            list[position] = view

            Log.i(tag, "Index: $position :: ${list[position].namePoint?.text}")


            // COMENTADOS
//            if (isLastPosition) {
//                btnNextOrFinishPointTestPumpExecuteGeneric.setOnClickListener {
////                    TODO implementation action to function cancel test in point selected
//                }
//                imgViewNextOrFinishPointTestPumpExecuteGeneric.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_ok))
//                textPausePoint.text = context.getString(R.string.txt_finish)
//
////                btnNextOrFinishPointTestPumpExecuteGeneric.visibility = View.INVISIBLE
////                imgViewNextOrFinishPointTestPumpExecuteGeneric.visibility = View.INVISIBLE
////                textNextOrFinishPointTestPumpExecuteGeneric.visibility = View.INVISIBLE
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

    fun getPointTestPumpView(position: Int): PointTestPumpExecuteGeneric? {
        return if (position < list.size)
            list[position]
        else null
    }

    fun update(list: List<PointTestPumpExecuteGeneric>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }

}