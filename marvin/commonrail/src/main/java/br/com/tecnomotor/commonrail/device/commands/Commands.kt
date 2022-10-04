package br.com.tecnomotor.commonrail.device.commands

import android.util.Log
import br.com.tecnomotor.commonrail.device.DeviceDataInfo
import br.com.tecnomotor.commonrail.device.EnumDeviceDefault.*
import br.com.tecnomotor.commonrail.device.exceptions.CommonRailControlException
import br.com.tecnomotor.commonrail.device.exceptions.CommonRailDeviceException
import br.com.tecnomotor.commonrail.device.exceptions.CommonRailMeasurementException
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.ExtentionFunctions.toHex
import br.com.tecnomotor.device.Commands
import br.com.tecnomotor.device.EnumCommandResult
import br.com.tecnomotor.kwptm.KWPTM
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.experimental.or

/**
 * Classe responsável por manipular os comandos
 * do CommonRail comuns às placas Medicao e Controle
 */
@InternalCoroutinesApi
open class Commands(kwp: KWPTM) : Commands(kwp) {
    //companion object{
    private val mutex = Mutex()  // our hero ;-)
    //}

    private val TAG = "cmdCR - ${kwp.name}"

    var started: Boolean = false
    lateinit var response: ByteArray

    suspend fun sendCommand(cmd: ByteArray): ByteArray {
        //Fluxograma do sendCommand
        //https://app.diagrams.net/?libs=general;flowchart#G1tYrGseMtWuflJPB1dWLTCUOPFpLJWeUa
        return mutex.withLock {
//            Log.i(TAG, "[TX]: ${cmd.toHex()}")
            val timeout = 120
            val tentative = 10
            var count = 0

            while (true) {
                count++
                if (count >= tentative) { //tornar 1000 um membro da classe
                    if (result != EnumCommandResult.NegativeResponse)
                        result = EnumCommandResult.ErrorResponse
                    response = byteArrayOf()
                    break
                }
//            Log.i(TAG, "sendCommand tentative: $count")
                try {
                    kwp.write(cmd)
                    delay(10) //Importante para conseguir obter a resposta correta
                    response = kwp.read(timeout)
//                    Log.v(TAG, "[RX]: ${response.toHex()}")
                } catch (e: Exception) {
//                Log.e(TAG, "readCommand error: $e")
                    continue
                }

                if (response.isEmpty()) {//Rx?
                    if (count < tentative)
                        delay((count * 5).toLong())//tornar 300 um membro da classe
                    continue //Rx no
                }

                if (response[0] == (cmd[0].or(0x40.toByte()))) {
                    result = EnumCommandResult.CorrectResponse
                    break
                } else if (response[0] == 0x7F.toByte()) {
                    result = EnumCommandResult.NegativeResponse
                    if (count <= tentative)
                        delay((count * 5).toLong())//tornar 300 um membro da classe
                    continue //Rx no
                } else break
            }
            delay(100)
            //Log.i(TAG, "sendCommand: $result")
            response
        }
    }

    suspend fun sendCommand(cmd: CommandToDevice): CommandToDevice {
        this.result = EnumCommandResult.NoneResponse
        cmd.read = this.sendCommand(cmd.send)
        cmd.executed = true //this.result == EnumCommandResult.CorrectResponse (O comando foi executado, apesar da resposta errada.)
        cmd.response = this.result
        when(this.result) {
            EnumCommandResult.ErrorResponse->{
                println("error response")
                throw buildException(cmd)
            }
            EnumCommandResult.NegativeResponse->{
                println("negative response")
                throw buildException(cmd)
            }
            EnumCommandResult.CorrectResponse->{
                //println("correct response")
            }
            EnumCommandResult.NoneResponse->{
                println("none response")
                throw buildException(cmd)
            }
        }
        return cmd
    }

    private fun buildException(cmd: CommandToDevice): Throwable {
        return when (cmd.getDevice()){
            Control -> CommonRailControlException(cmd.toString())
            Measurement -> CommonRailMeasurementException(cmd.toString())
            Undetermined -> CommonRailDeviceException(cmd.toString())
        }
    }

    suspend fun processCommand(cmd: ByteArray): Boolean {
        this.sendCommand(cmd)
        return (result == EnumCommandResult.CorrectResponse)
    }

    suspend fun startCommunication(): Boolean {
        this.sendCommand(CMD_START_COMMUNICATION)
        started = (result == EnumCommandResult.CorrectResponse)
        return started
    }

    suspend fun stopComunication(): Boolean {
        this.sendCommand(CMD_STOP_COMMUNICATION)
        val res = (result == EnumCommandResult.CorrectResponse)
        started = !res
        return res
    }

    open suspend fun getVersion(): DeviceDataInfo {
        this.sendCommand(CMD_FIRMWARE_VERSION)
        val res = DeviceDataInfo("00", "00")
        if (result == EnumCommandResult.CorrectResponse) {
            val responseText = String(response.copyOfRange(1, 5))
            res.version = responseText.substring(0, 2)
            res.revision = responseText.substring(2, 4)

        }
        return res
    }

    suspend fun reset(): Boolean {
        this.sendCommand(CMD_RESET)
        var res = (result == EnumCommandResult.CorrectResponse)
        res = res && this.startCommunication()
        return res
    }

    suspend fun testerPresent(): Boolean {
        return this.processCommand(CMD_TESTER_PRESENT)
    }


    /**
     * Retorna os valores do equipamento, comando 19
     * Classe UTControleCommand linha 141
     * function TControleCommand.GetTesteData: String;
     */
    open suspend fun getTestValues(): ByteArray {
        var ret: ByteArray
        do {
            ret = this.sendCommand(byteArrayOf(0x19.toByte(), 0x3D.toByte()))
            println(ret.toHex())
        }while (ret[2]==EnumTestStatus.TEST_RUNNING.toByte())//Enquanto estiver em execussão
        return ret
    }

}

