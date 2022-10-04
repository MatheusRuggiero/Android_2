package br.com.tecnomotor.marvin.adapter.generic.pump

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.PieChart
import java.io.Serializable

class PointTestPumpExecuteGeneric: Serializable {
    var pointView: View? = null
    var currentPointExt1: TextView? = null
    var currentPointValueExt1: TextView? = null
    var currentPointExt2: TextView? = null
    var currentPointValueExt2: TextView? = null
    var namePoint: TextView? = null
    var statusPointValue: TextView? = null
    var errorControlPointValue: TextView? = null
    var errorMeasurePointValue: TextView? = null
    var pieChartViewPointTestPumpExecuteGeneric: PieChart? = null
    var chartFlowPointTestPumpExecuteGeneric: CombinedChart? = null
    var chartReturnPointTestPumpExecuteGeneric: CombinedChart? = null
    var groupTimePoint: Group? = null
    var timePoint: TextView? = null
    var timePointValue: TextView? = null
    var pressurePointValue: TextView? = null
    var legendChartFlowPointTestPumpExecuteGeneric: TextView? = null
    var legendChartReturnPointTestPumpExecuteGeneric: TextView? = null

    var groupFlowPointTest: Group? = null
    var groupReturnPointTest: Group? = null
    var groupCalRotationFlow: Group? = null

    var frameRotation: ConstraintLayout? = null
    var frameRotationChart: PieChart? = null
    var frameRotationValue: TextView? = null
    var frameRotationTextInfo: TextView? = null
    var frameRotationBtnCancel: ImageButton? = null

    var rotationLabel: TextView? = null
    var rotationValue: TextView? = null
    var pressureFeedValue: TextView? = null
    var temperatureValue: TextView? = null
    var flowValue: TextView? = null
    var flowMaxMinValue: TextView? = null
    var returnValue: TextView? = null
    var returnMaxMinValue: TextView? = null
    var ml100RotValue: TextView? = null

    var pointTestPump: PointTestPump? = PointTestPump()
}
