package br.com.tecnomotor.commonrail.device.utils

object ExtentionFunctions {
    private fun genericIntToByteArray(valor: Int, bytes: Int): ByteArray {
        var ret = ByteArray(0)
        for (i in 0 until bytes){
            ret = byteArrayOf((valor shr 8 * i).toByte()) + ret
        }
        return ret
    }

    /**
     * Constroi um Byte Array com quatro bytes (32bits) preenchendo com zero à esquerda a partir de um inteiro
     */
    fun Int.toByteArray32(): ByteArray {
        return genericIntToByteArray(this,4)
    }

    /**
     * Constroi um Byte Array com dois bytes preenchendo com zero à esquerda a partir de um inteiro
     */
    fun Int.toByteArray16(): ByteArray {//16bits
        return genericIntToByteArray(this,2)
    }

    /**
     * Constroi um Byte Array com um byte a partir de um inteiro
     */
    fun Int.toByteArray8(): ByteArray {//8bits
        return genericIntToByteArray(this,1)
    }
    /**
     * Imprime um ByteArray de bytes sinalizados em UByte
     */
    fun ByteArray.toHex(): String = "[" + joinToString(separator = ",") { eachByte -> "%02X".format(eachByte) } + "]"
}