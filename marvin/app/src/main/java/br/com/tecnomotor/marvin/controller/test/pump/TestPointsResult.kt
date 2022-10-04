package br.com.tecnomotor.marvin.controller.test.pump

import br.com.tecnomotor.commonrail.device.commands.pump.result.PointTestPumpResult
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import kotlinx.coroutines.DelicateCoroutinesApi

/**
 * Gerenciamento dos Resultados de Testes dos Pontos de Teste
 */
@DelicateCoroutinesApi
class TestPointsResult(
    val pointTestPumpList: MutableList<PointTestPump>
) {

    var testTime: Long = 0 //Tempo total do teste
    val pointTestItems: ArrayList<PointTestItem> = arrayListOf()
    var allTestFinished: Boolean = false // indica se todos os testes foram finalizados

    var indexSelected: Int = 0
        private set

    init {
        pointTestItems.clear()
        pointTestPumpList.forEach {
            pointTestItems.add(PointTestItem(it, PointTestPumpResult()))
        }
    }

    fun isNewTestPoint():Boolean {
        return ((indexSelected+1) < pointTestItems.size)
    }

    fun nextPoint() {
        if ((indexSelected+1) < pointTestItems.size)
            indexSelected++
    }

    fun priorPoint() {
        if ((indexSelected-1) >= 0)
            indexSelected--
    }

    fun updateResult(pointTestPumpResult: PointTestPumpResult) {
        getItem().pointTestPumpResult.deepCopy(pointTestPumpResult)
    }

    fun getItem(): PointTestItem = pointTestItems[indexSelected]

    override fun toString(): String {
        return "TestPointsResult(pointTest=${getItem().pointTest.typePointTest?.description}, testTime=${testTime}, result=${getItem().pointTestPumpResult})"
    }
}