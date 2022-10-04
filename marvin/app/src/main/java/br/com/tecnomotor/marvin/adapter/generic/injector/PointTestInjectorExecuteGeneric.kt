package br.com.tecnomotor.marvin.adapter.generic.injector

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.PieChart
import java.io.Serializable

class PointTestInjectorExecuteGeneric: Serializable{
    var pointView: View? = null
    var flowPoint: TextView? = null
    var flowPointValue: TextView? = null
    var flowPointMinMax: TextView? = null
    var frequencyPoint: TextView? = null
    var frequencyPointValue: TextView? = null
    var namePoint: TextView? = null
    var returnPoint: TextView? = null
    var returnPointValue: TextView? = null
    var returnPointMinMax: TextView? = null
    var statusPoint: TextView? = null
    var statusPointValue: TextView? = null
    var timeInjectionPoint: TextView? = null
    var timeInjectionPointValue: TextView? = null
    var pieChartViewPointTestInjectorExecuteGeneric: PieChart? = null
    var chartFlowPointTestInjectorExecuteGeneric: CombinedChart? = null
    var chartReturnPointTestInjectorExecuteGeneric: CombinedChart? = null

    //var backgroundValuesPointTestInjector: RelativeLayout? = null
    var groupFlowPointTestInjector: Group? = null
    var groupReturnPointTestInjector: Group? = null

    var groupTimePoint: Group? = null
    var timePoint: TextView? = null
    var timePointValue: TextView? = null

    var pressurePointValue: TextView? = null
    var legendChartFlowPointTestInjectorExecuteGeneric: TextView? = null
    var legendChartReturnPointTestInjectorExecuteGeneric: TextView? = null

    var pointTestInjector: PointTestInjector? = PointTestInjector()

}