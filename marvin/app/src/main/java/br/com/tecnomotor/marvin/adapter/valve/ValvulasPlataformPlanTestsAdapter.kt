package br.com.tecnomotor.marvin.adapter.valve

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.valve.ValvePlatformPlanEntity

class ValvulasPlataformPlanTestsAdapter(
    private var filterList: List<ValvePlatformPlanEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listValvulasPlataformPlanTestPlatformPlanEntities: List<ValvePlatformPlanEntity>) {
        filterList = ArrayList()
        if (!listValvulasPlataformPlanTestPlatformPlanEntities.isNullOrEmpty()) {
            listValvulasPlataformPlanTestPlatformPlanEntities.forEach {
                (filterList as ArrayList<ValvePlatformPlanEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}