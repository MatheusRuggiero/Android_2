package br.com.tecnomotor.marvin.controller.test

import br.com.tecnomotor.marvin.controller.Controller
import br.com.tecnomotor.marvin.controller.test.IControllerTest

open class ControllerTest: Controller(), IControllerTest {
    override fun startTest() {}
    override fun cancelTest() {}
    override fun skipTest() {}
    override fun pauseTest() {}
}