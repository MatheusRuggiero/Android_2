package br.com.tecnomotor.marvin.adapter.valve

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.valve.TranslateTypePlanTestValveEntity

class TranslationTypePlanTestValvulaAdapter(
    private var filterList: List<TranslateTypePlanTestValveEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTranslateTypePlanTestValvula: List<TranslateTypePlanTestValveEntity>) {
        filterList = ArrayList()
        if (!listTranslateTypePlanTestValvula.isNullOrEmpty()) {
            listTranslateTypePlanTestValvula.forEach {
                (filterList as ArrayList<TranslateTypePlanTestValveEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}