package br.com.tecnomotor.marvin.adapter.pump

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.pump.TranslateTypePointTestPumpEntity

class TranslationTypePointTestBombaAdapter(
    private var filterList: List<TranslateTypePointTestPumpEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTranslateTypePointTestBombaEntity: List<TranslateTypePointTestPumpEntity>) {
        filterList = ArrayList()
        if (!listTranslateTypePointTestBombaEntity.isNullOrEmpty()) {
            listTranslateTypePointTestBombaEntity.forEach {
                (filterList as ArrayList<TranslateTypePointTestPumpEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}