package br.com.tecnomotor.marvin.controller.test.pump

import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestPumpResult
import br.com.tecnomotor.marvin.model.pump.PointTestPump

class PointTestItem(
    val pointTest: PointTestPump,
    var pointTestPumpResult: PointTestPumpResult
) {
}