package br.com.tecnomotor.commonrail.device.commands.injector.result

import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus

class ConditioningResult(
    enumTestState: EnumTestStatus,
    var frequency: Int = 160,
    var desiredPressure: Long = 1000,
    var pressure: Long = 0,
    var timeTest: Long = 20000, //20s
    var currentTestTime: Long = 0,
    var injectionTime: Int = 2000,
    var temperature: Int = 0,
    var highVoltage: Byte = 0,
    var thresholdCH1: Byte = 0,
    var failCH1: Byte = 0,
    var thresholdCH2: Byte = 0,
    var failCH2: Byte = 0
) : TestResult(enumTestState) {

}