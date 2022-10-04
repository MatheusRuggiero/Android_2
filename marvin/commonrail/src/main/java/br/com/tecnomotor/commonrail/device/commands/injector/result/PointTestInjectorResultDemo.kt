package br.com.tecnomotor.commonrail.device.commands.injector.result

import android.graphics.Color
import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus

/**
 * Armazena o Resultado de um Ponto de Teste
 */
class PointTestInjectorResultDemo(
    status: EnumTestStatus,
    var testTime: Long = 0,
    var frequency: Int = 160,
    var desiredPressure: Long = 0,
    var pressure: Long = 0,
    var injectionTime: Int = 2000,
    var injectionValue: Double = 0.0,
    var minimumInjection: Double = 0.0,
    var maximumInjection: Double = 0.0,
    var returnValue: Double = 0.0,
    var minimumReturn: Double = 0.0,
    var maximumReturn: Double = 0.0
): TestResult(status) {

    fun toAssign(value: PointTestInjectorResultDemo) {
        this.status = value.status
        this.testTime = value.testTime
        this.frequency = value.frequency
        this.desiredPressure = value.desiredPressure
        this.pressure = value.pressure
        this.injectionTime = value.injectionTime
        this.injectionValue = value.injectionValue
        this.minimumInjection = value.minimumInjection
        this.maximumInjection = value.maximumInjection
        this.returnValue = value.returnValue
        this.minimumReturn = value.minimumReturn
        this.maximumReturn = value.maximumReturn
    }

    override fun ofByteArray(values: ByteArray?):PointTestInjectorResultDemo {
        if ((values != null) && (values.size >= 21) && (EnumControlTypeTest.valueOf(values[1]) == EnumControlTypeTest.PUMP)) {
            super.ofByteArray(values)

        }
        return this
    }

    private fun getRotation(mostSignificantValue: Int, leastSignificantValue: Int): Long {
        return (((mostSignificantValue shl (8)) + leastSignificantValue)).toLong()
    }

    fun getInjectionColor(): Int {
        if (injectionValue < minimumInjection)
            return Color.parseColor("#00678D")
        else if (injectionValue > maximumInjection)
            return Color.parseColor("#00678D")
        return Color.parseColor("#00FF19")
    }

    fun getReturnColor(): Int {
        if (returnValue < minimumReturn)
            return Color.parseColor("#00678D")
        else if (returnValue > maximumReturn)
            return Color.parseColor("#00678D")
        return Color.parseColor("#00FF19")
    }

    override fun toString(): String {
        return "PointTestResultDemo(testTime=$testTime, frequency=$frequency, desiredPressure=$desiredPressure, pressure=$pressure, injectionTime=$injectionTime, injectionValue=$injectionValue, minimumInjection=$minimumInjection, maximumInjection=$maximumInjection, returnValue=$returnValue, minimumReturn=$minimumReturn, maximumReturn=$maximumReturn)"
    }

}