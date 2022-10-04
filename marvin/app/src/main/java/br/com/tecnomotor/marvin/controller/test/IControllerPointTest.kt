package br.com.tecnomotor.marvin.controller.test

interface IControllerPointTest<T>: IControllerTest {
    fun setNextPoint(pointTest: T)
}