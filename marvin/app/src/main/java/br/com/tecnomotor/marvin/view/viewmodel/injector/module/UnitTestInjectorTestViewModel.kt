package br.com.tecnomotor.marvin.view.viewmodel.injector.module

import androidx.lifecycle.ViewModel
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.marvin.view.unit_test.injector.InjUnitTestController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class, DelicateCoroutinesApi::class)
class UnitTestInjectorTestViewModel: ViewModel() {
    //livedata dos valores a serem lidos
    //livedata dos valores a serem escritos (quando o fragment iniciar, preencher os valores)
    val controller  = InjUnitTestController(CommonRailRepository.getInstance())

    //as ações vão se corresponder com comandos a serem enviados.
    //Seria bom se o controler a ser usado fosse o mesmo que será usado no fluxo de testes, para reaproveitar o código e manter a consistência
    fun test(){

        println("estou test")
    }

    fun testParamInj(param: Array<Int>) =   controller.parametrizaInj(param)

    fun testStartInj()  =   controller.inicia()

    fun testStopInj()   =   controller.terminar()

    fun testSkipInj()   =   controller.pular()

    fun testCancelInj() =   controller.cancela()


}