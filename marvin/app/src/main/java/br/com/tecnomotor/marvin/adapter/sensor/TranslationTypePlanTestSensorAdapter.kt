package br.com.tecnomotor.marvin.adapter.sensor

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.sensor.TranslateTypePlanTestSensorEntity

class TranslationTypePlanTestSensorAdapter(
    private var filterList: List<TranslateTypePlanTestSensorEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listTranslateTypePlanTestSensorEntity: List<TranslateTypePlanTestSensorEntity>) {
        filterList = ArrayList()
        if (!listTranslateTypePlanTestSensorEntity.isNullOrEmpty()) {
            listTranslateTypePlanTestSensorEntity.forEach {
                (filterList as ArrayList<TranslateTypePlanTestSensorEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}