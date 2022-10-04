package br.com.tecnomotor.commonrail.device.commands

import br.com.tecnomotor.commonrail.device.DeviceControleDataInfo
import br.com.tecnomotor.commonrail.device.commands.result.TemperatureResult
import br.com.tecnomotor.commonrail.device.utils.control.EnumControlTypeTest
import br.com.tecnomotor.commonrail.device.utils.control.EnumTestKey
import br.com.tecnomotor.device.EnumCommandResult
import br.com.tecnomotor.kwptm.KWPTM
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

/**
 * Classe responsável pelos comandos da placa Controle
 */
@InternalCoroutinesApi
class ControlCommands(kwp: KWPTM) : Commands(kwp) {

    init {
        kwp.name = "Control"
    }

    suspend fun startCommunicationSkipErrors(): Boolean {
        this.sendCommand(CMD_START_COMMUNICATION + byteArrayOf(0x01.toByte()))
        return (result == EnumCommandResult.CorrectResponse)
    }

    /**
     * Retorna a plataforma, versão e revisão do hardware, método correspondente ao do projeto CRFast
     * Classe UTControleCommand linha 111
     * function TControleCommand.GetInformacoesDaPlataforma: TPlataforma;
     */
    override suspend fun getVersion(): DeviceControleDataInfo {
        this.sendCommand(CMD_FIRMWARE_VERSION)
        var res = DeviceControleDataInfo(0, "00", "00")
        if (result == EnumCommandResult.CorrectResponse) {
            res.platformId = (response[9].toInt() * 256) + response[10].toInt()
            val responseText = String(response.copyOfRange(1, 5))
            res.version = responseText.substring(0, 2)
            res.revision = responseText.substring(2, 4)
        }
        return res
    }

    /**
     * Reseta os parametros e para de bipar
     */
    suspend fun parametersReset(){
        this.processCommand(CommandHelper.CMD_PARAMETER_RESET)
    }

    /**
     * Verifica se o equipamento já pode ser parametrizado
     * Classe UTControleCommand linha 243
     * function TControleCommand.podeParametrizar(var resposta: String): boolean;
     */
    suspend fun canParameterize(): Boolean {
        this.sendCommand(CommandHelper.CMD_CONTROL_CAN_PARAMETERIZE)
        if ((this.response.size > 3) && (this.response[3] == 0xFF.toByte())) {
            //val error: Int = response[4].toInt()
            //throw ECommonRailCommandException(error)
        }
        return (this.result == EnumCommandResult.CorrectResponse)
    }

    /**
     * Retorna os valores do equipamento, comando 16
     * Classe UTControleCommand linha 151
     * function TControleCommand.GetTesteStatico: String;
     */
    suspend fun getStaticTest(): ByteArray? {
        var res: ByteArray? = null
        this.sendCommand(byteArrayOf(0x16.toByte()))
        if (result == EnumCommandResult.CorrectResponse)
            res = response
        return res
    }

    /**
     * Retorna os valores do equipamento, comando 18
     * Classe UTControleCommand linha 146
     * function TControleCommand.GetTesteDinamico: String;
     */
    suspend fun getDynamicTest(): ByteArray? {
        var res: ByteArray? = null
        this.sendCommand(byteArrayOf(0x18.toByte()))
        if (result == EnumCommandResult.CorrectResponse)
            res = response
        return res
    }

    suspend fun turnOnPumps(): Boolean {
        var res = turnOnTankPumps()
        delay(3000)
        res = (res && turnOnHighPump())
        return res
    }

    suspend fun turnOnTankPumps(): Boolean {
        return processCommand(byteArrayOf(0x26.toByte(), 0x03.toByte()))
    }

    suspend fun turnOnHighPump(): Boolean {
        return processCommand(byteArrayOf(0x26.toByte(), 0x04.toByte()))
    }


    /**
     * Envia o comando de uma tecla do equipamento
     * Classe UTControleCommand linha 238
     * procedure TControleCommand.EnviarTecla(tecla: TTeclaEnum);
     */
    suspend fun sendKey(enumTestKey: EnumTestKey): Boolean {
        this.sendCommand(CommandHelper.CMD_SEND_KEY + enumTestKey.value)
        return (this.result == EnumCommandResult.CorrectResponse)
    }

    /**
     * Retorna as configurações de temperatura setadas no equipamento
     * Classe UTControleCommand linha 126
     * procedure TControleCommand.GetConfiguracoesTemperaturas;
     */
    suspend fun getTemperatureSetting(): TemperatureResult {
        this.sendCommand(CommandHelper.CMD_CONTROL_READ_REMOTE_CONFIG)
        return if (this.result == EnumCommandResult.CorrectResponse)
            TemperatureResult().ofByteArray(response)
        else TemperatureResult()
    }

    /**
     * Realiza a parametrização para o teste de aquecimento
     * Classe UTControleCommand linha 171
     * procedure TControleCommand.ParametrizaAquecimento;
     */
    suspend fun parameterizeHeating(temperatureResult: TemperatureResult): Boolean {
        this.sendCommand(
            byteArrayOf(
                0x25.toByte(),
                0x02.toByte(),
                0x03.toByte(),
                0x20.toByte(),
                temperatureResult.minimum.toByte(),
                temperatureResult.average.toByte(),
                temperatureResult.maximum.toByte()
            )
        )
        return (result == EnumCommandResult.CorrectResponse)
    }

    /**
     * Abaixa a pressão do equipamento
     * Classe UTControleCommand linha 91
     * procedure TControleCommand.AbaixarPressao;
     */
    suspend fun lowerPressure(): Boolean {
        this.sendCommand(byteArrayOf(0x26.toByte(), 0x07.toByte()))
        return (result == EnumCommandResult.CorrectResponse)
    }

    /**
     * Desliga o sistema de pressão do equipamento
     * Classe UTControleCommand linha 106
     * procedure TControleCommand.DesligarSistemaDePressao;
     */
    suspend fun turnOffPressureSystem(): Boolean {
        this.sendCommand(byteArrayOf(0x26.toByte(), 0x08.toByte()))
        return (result == EnumCommandResult.CorrectResponse)
    }

    /**
     * Verifica os periféricos do hardware, método correspondente ao do projeto CRFast
     * Classe UTControleCommand linha 179
     * procedure TControleCommand.VerifcaPerifericos;
     */
    suspend fun checkPeripherals(): Boolean {
        this.sendCommand(byteArrayOf(0x26.toByte(), 0x01.toByte()))
        val values = getStaticTest()
        var res = (result == EnumCommandResult.CorrectResponse)
        if ((values == null) || (result == EnumCommandResult.NegativeResponse))
            throw ECommonRailCommandException(ECommonRailCommandException.ERROR_COMUNICACAO_USB)
        //Suprimido a verificação de teclas
        if (response[2] == 0xFF.toByte())
            throw ECommonRailCommandException(response[15].toInt())
        return res
    }

    suspend fun startTest(): Boolean {
        return this.processCommand(byteArrayOf(0x26.toByte(), 0x0D.toByte()))
    }

    /**
     * Cancela o teste em execução no equipamento
     * Classe UTControleCommand linha 203
     * procedure TControleCommand.CancelarTeste;
     */
    suspend fun cancelTeste(): Boolean {
        this.sendCommand(byteArrayOf(0x09.toByte()))
        return if (result == EnumCommandResult.CorrectResponse) {
            this.sendCommand(byteArrayOf(0x06.toByte(), 0x00.toByte()))
            (result == EnumCommandResult.CorrectResponse)
        } else false
    }

    suspend fun configurePID(): Boolean {
        this.sendCommand(byteArrayOf(0x28.toByte()))
        return (result == EnumCommandResult.CorrectResponse)
    }

    suspend fun setParameters(value: ByteArray): Boolean {
        this.sendCommand(CommandHelper.CMD_SET_PARAMETER + value)
        return (this.result == EnumCommandResult.CorrectResponse)
    }

    suspend fun setRotationTest() {
        this.parametersReset()
        this.sendCommand(CommandHelper.CMD_SET_TEST + byteArrayOf(EnumControlTypeTest.RPM_TEST.value))
    }

}