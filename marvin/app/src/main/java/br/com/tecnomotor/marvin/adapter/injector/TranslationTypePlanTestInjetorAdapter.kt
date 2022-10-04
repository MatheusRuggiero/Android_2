package br.com.tecnomotor.marvin.adapter.injector

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.injector.TranslateTypePlanTestInjectorEntity

class TranslationTypePlanTestInjetorAdapter(
    private var filterList: List<TranslateTypePlanTestInjectorEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTranslateTypePlanInjetorTestEntity: List<TranslateTypePlanTestInjectorEntity>) {
        filterList = ArrayList()
        if (!listTranslateTypePlanInjetorTestEntity.isNullOrEmpty()) {
            listTranslateTypePlanInjetorTestEntity.forEach {
                (filterList as ArrayList<TranslateTypePlanTestInjectorEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}