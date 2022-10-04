package br.com.tecnomotor.marvin

import br.com.tecnomotor.marvin.controller.test.flags.TestFlags
import br.com.tecnomotor.marvin.controller.test.flags.TestFlagsControl
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testflag() {
        val flagDeControle = TestFlagsControl()
        //Teste de rotação
        flagDeControle.clearValidFlags()
        flagDeControle
            .addValidFlags(TestFlags.flgSkipRotation)
            .addValidFlags(TestFlags.flgCancel)

        println("Flag: ${flagDeControle.flag} - ${flagDeControle.isValid()}")
        flagDeControle.flag = TestFlags.flgSkipRotation

        if (flagDeControle.flag == TestFlags.flgSkipRotation) {
            //resetaPlacaControle
            flagDeControle.setExecuted()
        }


        println("Flag: ${flagDeControle.flag} - ${flagDeControle.isValid()}")
        flagDeControle.flag = TestFlags.flgCancel
        println("Flag: ${flagDeControle.flag} - ${flagDeControle.isValid()}")
        flagDeControle.flag = TestFlags.flgFail
        println("Flag: ${flagDeControle.flag} - ${flagDeControle.isValid()}")
    }
}