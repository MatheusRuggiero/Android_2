package br.com.tecnomotor.commonrail.device.commands

import br.com.tecnomotor.commonrail.device.utils.ExtentionFunctions.toHex

/**
 * Classe criada apenas para armazenar o send e o read e facilitar o tratamento dos valores via LiveData e ViewModel
 */
data class CommandSendRead(
    val send: ByteArray,
    var read: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommandSendRead

        if (!send.contentEquals(other.send)) return false
        if (read != null) {
            if (other.read == null) return false
            if (!read.contentEquals(other.read)) return false
        } else if (other.read != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = send.contentHashCode()
        result = 31 * result + (read?.contentHashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "CommandSendRead(send=${send.toHex()}, read=${read?.toHex()})"
    }
}