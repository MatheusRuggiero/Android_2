package br.com.tecnomotor.marvin.adapter.sensor

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.sensor.SensorPlatformEntity

class SensorPlataformAdapter (
    private var filterList: List<SensorPlatformEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listSensoresPlataforms: List<SensorPlatformEntity>) {
        filterList = ArrayList()
        if (!listSensoresPlataforms.isNullOrEmpty()) {
            listSensoresPlataforms.forEach {
                (filterList as ArrayList<SensorPlatformEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}