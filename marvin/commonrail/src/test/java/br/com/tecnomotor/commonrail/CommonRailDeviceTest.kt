package br.com.tecnomotor.commonrail

import android.app.Application
import br.com.tecnomotor.commonrail.device.commands.ECommonRailCommandException
import br.com.tecnomotor.commonrail.device.utils.control.ElectricalTestCondition
import br.com.tecnomotor.commonrail.device.utils.measurement.TestMeasurement
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumTestProcessForMeasurement
import br.com.tecnomotor.textutil.TextUtil
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class CommonRailDeviceTest {

    private var context = Mockito.mock(Application::class.java)

//    private val commands: CommandsCommonRail = CommandsCommonRail(KWPTM())
//    abstract fun CommandsCommonRail(): CommandsCommonRail
//
//    private val device: CommonrailDevice = CommonrailDevice()
//    abstract fun CommonrailDevice(): CommonrailDevice

    val CMD_CONF_REMOTA_ESCRITA = byteArrayOf(0x08.toByte())
    val CMD_TEMPERATURA_MEDIA = byteArrayOf(0x48.toByte())

    @Test
    fun testConfRemotaEscrita() {
        val teste = CMD_CONF_REMOTA_ESCRITA.contentEquals(CMD_CONF_REMOTA_ESCRITA)
        //Assert.assertArrayEquals(CMD_CONF_REMOTA_ESCRITA, CMD_CONF_REMOTA_ESCRITA)
        //val teste: Boolean = arrayOf(CMD_CONF_REMOTA_ESCRITA).contentEquals(arrayOf(CMD_CONF_REMOTA_ESCRITA))
        println("CommonRailDeviceTest - Conf. Remota Escrita: $teste")
    }

    @Test
    fun testConfRemotaEscritaErro() {
        val teste = CMD_CONF_REMOTA_ESCRITA.contentEquals(CMD_TEMPERATURA_MEDIA)
        //Assert.assertArrayEquals(CMD_CONF_REMOTA_ESCRITA, CMD_CONF_REMOTA_ESCRITA)
        //val teste: Boolean = arrayOf(CMD_CONF_REMOTA_ESCRITA).contentEquals(arrayOf(CMD_CONF_REMOTA_ESCRITA))
        println("CommonRailDeviceTest - Conf. Remota Escrita: $teste")
    }

    @Test
    fun testVersion() {
        //var response = byteArrayOf(0x30.toByte(),0x31.toByte(),0x31.toByte(),0x32.toByte()) //Controle
        var response = byteArrayOf(0x30.toByte(),0x31.toByte(),0x30.toByte(),0x38.toByte()) //SOM
        val responseHex = arrayOf(
                                TextUtil.toHexString(byteArrayOf(response[0])),
                                TextUtil.toHexString(byteArrayOf(response[1])),
                                TextUtil.toHexString(byteArrayOf(response[2])),
                                TextUtil.toHexString(byteArrayOf(response[3]))
                            )
        println("responseHex: ${responseHex.toString()}")
        println("responseHex: ${response[0].toInt().toChar()}${response[1].toInt().toChar()}")
        println("responseHex: ${response[2].toInt().toChar()}${response[3].toInt().toChar()}")
    }

    @Test
    fun testErrors(){
        try {
            if (context != null) {
                println("Contexto OK")
                println(context.getString(R.string.MSG_ERROR_01_PROBLEMA_CONTROLE))
            }
            //throw ECommonRailCommandException(1)
        } catch (e: ECommonRailCommandException) {
            //println("Error: ${e.message} - ${e.getLocalizedMessage(context)}")
        }
    }

    @Test
    fun testeCondicaoDoTesteEletrico() {
        val i = 2
        val condicao = ElectricalTestCondition.valueOf(i)
        println("Condição: ${condicao.toString()}")
    }

    @Test
    fun testeClasseMedicao() {
        val medicao = TestMeasurement()
        medicao.loadParametrizacao(EnumTestProcessForMeasurement.Injector, 40, true, true, 1.0, 2.0)

        medicao.parser(
            byteArrayOf(
                0x59.toByte(),
                0x01.toByte(),
                0x01.toByte(),
                0x00.toByte(),
                0x01.toByte(),
                0x01.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x01.toByte(),
                0x01.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x00.toByte(),
                0x7F.toByte(),
                0x00.toByte())
        )
        println(medicao.toString())
        println(Arrays.toString(medicao.getParametros()))
        medicao.mudarDeCanal()
        println(medicao.toString())
        medicao.getParametros().forEach(System.out::print)
    }

    @Test
    fun testeRotacao() {
        var valores = byteArrayOf(0x0C.toByte(),0xB8.toByte())
        val rotacao = ((valores[0].toUByte().toInt() shl (8)) + valores[1].toUByte().toInt())
        println("Rotacao: $rotacao")
    }

    @Test
    fun testeTemperatura() {
        var valores = byteArrayOf(0x00.toByte(),0x81.toByte())
        val temperatura = ((valores[0].toInt() shl (8)) + valores[1].toInt())
        println("Temperatura: $temperatura")
    }
}