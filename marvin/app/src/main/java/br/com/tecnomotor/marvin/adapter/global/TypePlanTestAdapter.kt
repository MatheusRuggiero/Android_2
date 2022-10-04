package br.com.tecnomotor.marvin.adapter.global

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.global.TypePlanTestEntity

class TypePlanTestAdapter(
    private var filterList: List<TypePlanTestEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTypePlanTestEntity: List<TypePlanTestEntity>) {
        filterList = ArrayList()
        if (!listTypePlanTestEntity.isNullOrEmpty()) {
            listTypePlanTestEntity.forEach {
                (filterList as ArrayList<TypePlanTestEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}