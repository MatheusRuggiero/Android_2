package br.com.tecnomotor.commonrail.device.utils

import android.content.Context

interface ILocalizedMessage {
    fun getLocalizedMessage(context: Context): String
}