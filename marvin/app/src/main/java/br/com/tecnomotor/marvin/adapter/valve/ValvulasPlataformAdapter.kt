package br.com.tecnomotor.marvin.adapter.valve

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.valve.ValvePlatformEntity

class ValvulasPlataformAdapter(
    private var filterList: List<ValvePlatformEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listValvulasPlataform: List<ValvePlatformEntity>) {
        filterList = ArrayList()
        if (!listValvulasPlataform.isNullOrEmpty()) {
            listValvulasPlataform.forEach {
                (filterList as ArrayList<ValvePlatformEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}