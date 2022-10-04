package br.com.tecnomotor.marvin.adapter.injector

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.injector.TypePointInjectorTestEntity

class TypePointTestInjetorAdapter(
    private var filterList: List<TypePointInjectorTestEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTypePointInjetorTestEntity: List<TypePointInjectorTestEntity>) {
        filterList = ArrayList()
        if (!listTypePointInjetorTestEntity.isNullOrEmpty()) {
            listTypePointInjetorTestEntity.forEach {
                (filterList as ArrayList<TypePointInjectorTestEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}