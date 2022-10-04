package br.com.tecnomotor.marvin.adapter.pump

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.pump.TranslateTypePlanTestPumpEntity

class TranslationTypePlanTestBombaAdapter(
    private var filterList: List<TranslateTypePlanTestPumpEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTranslateTypePlanTestBombaEntity: List<TranslateTypePlanTestPumpEntity>) {
        filterList = ArrayList()
        if (!listTranslateTypePlanTestBombaEntity.isNullOrEmpty()) {
            listTranslateTypePlanTestBombaEntity.forEach {
                (filterList as ArrayList<TranslateTypePlanTestPumpEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}