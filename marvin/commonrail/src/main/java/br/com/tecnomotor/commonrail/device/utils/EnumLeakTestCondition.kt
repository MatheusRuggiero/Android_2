package br.com.tecnomotor.commonrail.device.utils

import android.content.Context
import br.com.tecnomotor.commonrail.R

enum class EnumLeakTestCondition(val value: Int) : ILocalizedMessage {
    NONE(-1) {
        override fun getLocalizedMessage(context: Context): String {
            return ""
        }
    },
    OK(1) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_TESTE_ESTANQUEIDADE_OK)
        }
    },
    SKIPPED(2) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_TESTE_ESTANQUEIDADE_SKIPPED)
        }
    },
    LEAK(5) {
        override fun getLocalizedMessage(context: Context): String {
            return context.getString(R.string.MSG_TESTE_ESTANQUEIDADE_LEAK)
        }
    };

    companion object {
        fun valueOf(value: Int): EnumLeakTestCondition {
            var res = NONE
            EnumLeakTestCondition.values().forEach {
                if (it.value == value) {
                    res = it
                }
            }
            return res
        }
    }
}