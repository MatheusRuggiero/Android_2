package br.com.tecnomotor.marvin.adapter.generic.pump

import android.view.View
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import java.io.Serializable

class PointPumpGeneric : Serializable {
    var pointTestPump: PointTestPump? = PointTestPump()
    var statusCheckBox: Boolean = true
}