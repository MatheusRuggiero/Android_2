package br.com.tecnomotor.marvin.controller.test

interface IControllerTestBase {
    fun startTest()
    fun cancelTest()

    fun isFinished(): Boolean
}