package br.com.tecnomotor.commonrail.device.commands

import br.com.tecnomotor.commonrail.device.commands.CommandHelper.CMD_MEASURE_SET_ESTANQ_PROCESS
import br.com.tecnomotor.device.EnumCommandResult
import br.com.tecnomotor.kwptm.KWPTM
import kotlinx.coroutines.*

/**
 * Classe responsável pelos comandos da placa Medicao
 */
@InternalCoroutinesApi
class MeasurementCommands(kwp: KWPTM) : Commands(kwp) {

    init {
        kwp.name = "Measurement"
    }

    suspend fun flush(): Boolean {
        var res = false
        //this.sendCommand(CommandHelper.CMD_MEASURE_SET_PARAMETER_FLUSH_PROCESS)
        // TODO: utilizar parametrização
        if (this.result == EnumCommandResult.CorrectResponse) {
            this.sendCommand(CommandHelper.CMD_MEASURE_SET_FLUSH_PROCESS)
            res = (this.result == EnumCommandResult.CorrectResponse)
        }
        return res
    }

    suspend fun conditioning(): Boolean {
        var res = false
        //this.sendCommand(CommandHelper.CMD_MEASURE_SET_PARAMETER_CONDITIONING_PROCESS)
        // TODO: utilizar parametrização
        if (this.result == EnumCommandResult.CorrectResponse) {
            this.sendCommand(CommandHelper.CMD_MEASURE_SET_CONDITIONING_PROCESS)
            res = (this.result == EnumCommandResult.CorrectResponse)
        }
        return res
    }

    suspend fun heating(): Boolean {
        var res = false
        //this.sendCommand(CommandHelper.CMD_MEASURE_SET_PARAMETERS_HEATING_PROCESS)
        // TODO: aqui deve parametrizar para fazer o HEATING
        if (this.result == EnumCommandResult.CorrectResponse) {
            this.sendCommand(CommandHelper.CMD_MEASURE_SET_HEATING_PROCESS)
            res = (this.result == EnumCommandResult.CorrectResponse)
        }
        return res
    }

    suspend fun tightness(): Boolean {
        return this.processCommand(CMD_MEASURE_SET_ESTANQ_PROCESS)
    }

    suspend fun calibratePWM(): Boolean {
        this.sendCommand(CommandHelper.CMD_MEASURE_CALIBRA_PWM)
        return (this.result == EnumCommandResult.CorrectResponse)
    }

    suspend fun setParameters(value: ByteArray): Boolean {
        this.sendCommand(CommandHelper.CMD_SET_PARAMETER + value)
        return (this.result == EnumCommandResult.CorrectResponse)
    }

    suspend fun parameterizesTightnessTest(): Boolean {
        // TODO: revisar parametrização
//        return setParameters(
//            CommandHelper.CMD_MEASURE_SET_PARAMETER_LEAK_TEST_PROCESS +
//                    CommandHelper.CMD_MEASURE_CM_TOP_10_LEAK +
//                    CommandHelper.CMD_MEASURE_CM_TOP_20_LEAK +
//                    CommandHelper.CMD_MEASURE_FREQ_LEAK +
//                    CommandHelper.CMD_MEASURE_LEAK_TIMEOUT +
//                    CommandHelper.CMD_MEASURE_MSR_LEAK_FLOW_DIR +
//                    CommandHelper.CMD_MEASURE_MSR_INJ_LEAK +
//                    CommandHelper.CMD_MEASURE_MSR_RET_LEAK
//        )
        return false
    }

    /**
     * Leitura das variáveis de processo
     *
     * Cmd = hdr + 0x19 + qtd de bytes de dados + sum
     *
     * result:
     *      http://gitlab/diesel/SpeedyGonzales/software/firmwares/fw_tm4c/som/blob/master/Diversos/
     *          Protocolo/DocumentacaoProtocoloComunicacao.pdf
     */
    override suspend fun getTestValues(): ByteArray {
        return this.sendCommand(CommandHelper.CMD_MEASURE_GET_DATA_TEST)
    }

    @DelicateCoroutinesApi
    suspend fun setPauseState() {
        GlobalScope.launch(Dispatchers.IO) {
            conditioning()
            delay(4000)
            flush()
        }
    }


}