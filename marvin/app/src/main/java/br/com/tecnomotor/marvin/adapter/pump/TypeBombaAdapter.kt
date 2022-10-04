package br.com.tecnomotor.marvin.adapter.pump

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.pump.TypePumpEntity

class TypeBombaAdapter(
    private var filterList: List<TypePumpEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTypeBombaEntity: List<TypePumpEntity>) {
        filterList = ArrayList()
        if (!listTypeBombaEntity.isNullOrEmpty()) {
            listTypeBombaEntity.forEach {
                (filterList as ArrayList<TypePumpEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}