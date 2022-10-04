package br.com.tecnomotor.commonrail.device.utils

import android.content.Context
import br.com.tecnomotor.commonrail.R

enum class EnumTestStatus(val id: Int) : ILocalizedMessage {
    NONE(-1) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_NONE)
        }
    },
    TEST_STOPPED(0) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_PARADO)
        }
    },
    TEST_RUNNING(1) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_EXECUTANDO)
        }
    },
    TEST_STARTING(2) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_INICIANDO)
        }
    },
    TEST_FINISHED(3) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_FINALIZADO)
        }
    },
    TEST_HEATING(4) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_AQUECIMENTO)
        }
    },
    TEST_WAITING(5) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_AGUARDANDO)
        }
    },
    TEST_FINISHING(6) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_FINALIZANDO)
        }
    },
    TEST_REPORTING(7) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_DADOS)
        }
    },
    TEST_ACTIVATING(9) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_ATIVANDO)
        }
    },
    TEST_PAUSED(16) { //0x10
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_PAUSADO)
        }
    },
    TEST_END_PAUSED(17) { //0x11
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TODOS_TESTES_FINALIZADO)
        }
    },
    TEST_WAIT_KEY(18) { //0x12
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_AGUARDANDO_TECLA)
        }
    },
    TEST_WARNING(19) { //0x13
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_ATENCAO)
        }
    },
    TEST_ALL_TESTS_FINISHED(200) { //Utilizado no APP
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TODOS_TESTES_FINALIZADO)
        }
    },
    TEST_CONDITIONING(250) { //Utilizado no APP
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_CONDICIONAMENTO)
        }
    },
    TEST_SKIPPED(251) { // 0xFB
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_PULADO)
        }
    },
    TEST_SKIPPED_ROTATION(351) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_PULADO)
        }
    },
    TEST_PARAMETERIZING(252) { // 0xFC
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_PARAMETRIZANDO)
        }
    },
//    TEST_MEASURING(253) { // 0xFD
//        override fun getLocalizedMessage(context: Context): String {
//            return context.getString(R.string.MSG_STATUSTESTE_TESTE_MEDINDO)
//        }
//    },
    TEST_CANCELLED(254) { // 0xFE
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_CANCELADO)
        }
    },
    TEST_FAIL(255) { // 0xFF
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_STATUSTESTE_TESTE_ERROR)
        }
    };

    fun toByte(): Byte {
        return id.toByte()
    }

    companion object {
        fun valueOf(id: Int): EnumTestStatus {
            var res = NONE
            EnumTestStatus.values().forEach {
                if (it.id == id)
                    res = it
            }
            return res
        }
    }
}