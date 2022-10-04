package br.com.tecnomotor.marvin.adapter.sensor

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorPlatformPlanTestEntity

class SensorPlataformsPlansTestsAdapter(
    private var filterList: List<SensorPlatformPlanTestEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listSenPlataformsPlansTestEntities: List<SensorPlatformPlanTestEntity>) {
        filterList = ArrayList()
        if (!listSenPlataformsPlansTestEntities.isNullOrEmpty()) {
            listSenPlataformsPlansTestEntities.forEach {
                (filterList as ArrayList<SensorPlatformPlanTestEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}