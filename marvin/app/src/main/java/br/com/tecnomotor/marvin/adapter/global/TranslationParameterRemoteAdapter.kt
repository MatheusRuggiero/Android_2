package br.com.tecnomotor.marvin.adapter.global

import android.content.Context
import br.com.tecnomotor.marvin.model.configuration.TranslateParameterRemoteEntity

class TranslationParameterRemoteAdapter(
    private var filterList: List<TranslateParameterRemoteEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTranslateParameterRemoteEntity: List<TranslateParameterRemoteEntity>) {
        filterList = ArrayList()
        if (!listTranslateParameterRemoteEntity.isNullOrEmpty()) {
            listTranslateParameterRemoteEntity.forEach {
                (filterList as ArrayList<TranslateParameterRemoteEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}