package br.com.tecnomotor.marvin.adapter.pump

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.pump.TypeTestPointPumpEntity

class TypePointTestBombaAdapter(
    private var filterList: List<TypeTestPointPumpEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTypePointTestBombaEntity: List<TypeTestPointPumpEntity>) {
        filterList = ArrayList()
        if (!listTypePointTestBombaEntity.isNullOrEmpty()) {
            listTypePointTestBombaEntity.forEach {
                (filterList as ArrayList<TypeTestPointPumpEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}