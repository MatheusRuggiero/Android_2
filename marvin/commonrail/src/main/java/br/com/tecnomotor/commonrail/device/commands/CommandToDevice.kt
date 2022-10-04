package br.com.tecnomotor.commonrail.device.commands


import br.com.tecnomotor.commonrail.device.CommonRailHexDefines.commonRailControleTarget
import br.com.tecnomotor.commonrail.device.CommonRailHexDefines.commonRailMedicaoTarget
import br.com.tecnomotor.commonrail.device.EnumDeviceDefault
import br.com.tecnomotor.commonrail.device.utils.ExtentionFunctions.toHex
import br.com.tecnomotor.device.EnumCommandResult

class CommandToDevice(
    val send: ByteArray,
    var read: ByteArray? = null,
    var executed: Boolean = false,
    var response: EnumCommandResult = EnumCommandResult.NoneResponse,
    private val device: EnumDeviceDefault
) {


    init {

    }

    fun getDevice(): EnumDeviceDefault {
        if(read == null)
            return EnumDeviceDefault.Undetermined
        if(response == EnumCommandResult.NoneResponse)
            return EnumDeviceDefault.Undetermined

        return if (device != EnumDeviceDefault.Undetermined)
            device
        else when(read!![1]){
            commonRailControleTarget[0]->{EnumDeviceDefault.Control}
            commonRailMedicaoTarget[0]->{EnumDeviceDefault.Measurement}
            else->{EnumDeviceDefault.Undetermined}
        }

    }
    override fun equals(other: Any?): Boolean {
        other as CommandToDevice
        if (!send.contentEquals(other.send)) return false
        if (read != null) {
            if (other.read == null) return false
            if (!read.contentEquals(other.read)) return false
        } else if (other.read != null) return false
        if (executed != other.executed) return false
        if (response != other.response) return false

        return true
    }
    fun notEquals(other: Any?): Boolean {
        return !equals(other)
    }

    override fun hashCode(): Int {
        var result = send.contentHashCode()
        result = 31 * result + (read?.contentHashCode() ?: 0)
        result = 31 * result + executed.hashCode()
        result = 31 * result + response.hashCode()
        return result
    }

    override fun toString(): String {
        return "CommandToDevice(send=${send.toHex()}, read=${read?.toHex()}, executed=$executed, response=$response)"
    }
}