package br.com.tecnomotor.commonrail.device.utils.control

import android.content.Context
import br.com.tecnomotor.commonrail.R
import br.com.tecnomotor.commonrail.device.utils.ILocalizedMessage

enum class ElectricalTestCondition(val value: Int) : ILocalizedMessage {
    NONE(-1) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_TESTE_ELETRICO_NONE)
        }
    },
    SHORT(0) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_TESTE_ELETRICO_EM_CURTO)
        }
    },
    OK(1) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_TESTE_ELETRICO_OK)
        }
    },
    OPEN(2) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_TESTE_ELETRICO_ABERTO)
        }
    },
    ERROR(3) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_TESTE_ELETRICO_FORA_DE_ESPECIFICACAO)
        }
    },
    NOT_INSTALLED(4) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_TESTE_ELETRICO_NAO_INSTALADO)
        }
    };

    companion object {
        fun valueOf(value: Int): ElectricalTestCondition {
            var res = ERROR
            values().forEach {
                if (it.value == value) {
                    res = it
                }
            }
            return res
        }
    }
}