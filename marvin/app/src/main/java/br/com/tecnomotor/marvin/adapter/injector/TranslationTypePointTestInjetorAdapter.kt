package br.com.tecnomotor.marvin.adapter.injector

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.injector.TranslateTypePointTestInjectorEntity

class TranslationTypePointTestInjetorAdapter(
    private var filterList: List<TranslateTypePointTestInjectorEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTransPointInjectorTestEntity: List<TranslateTypePointTestInjectorEntity>) {
        filterList = ArrayList()
        if (!listTransPointInjectorTestEntity.isNullOrEmpty()) {
            listTransPointInjectorTestEntity.forEach {
                (filterList as ArrayList<TranslateTypePointTestInjectorEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}