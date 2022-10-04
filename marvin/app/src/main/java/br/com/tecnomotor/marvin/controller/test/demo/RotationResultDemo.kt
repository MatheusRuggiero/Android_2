package br.com.tecnomotor.marvin.controller.test.demo

import br.com.tecnomotor.commonrail.device.commands.result.TestResult

class RotationResultDemo(
    var value: Long,
    var temperature: Long = 0
): TestResult() {
    override fun toString(): String {
        return "RotationResultDemo(statusDeTeste=$status, value=$value, temperature=$temperature, error=$error)"
    }
}