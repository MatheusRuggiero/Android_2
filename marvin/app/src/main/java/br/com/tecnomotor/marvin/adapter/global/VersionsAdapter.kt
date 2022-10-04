package br.com.tecnomotor.marvin.adapter.global

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.global.VersionEntity

class VersionsAdapter(
    private var filterList: List<VersionEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listVersionEntities: List<VersionEntity>) {
        filterList = ArrayList()
        if (!listVersionEntities.isNullOrEmpty()) {
            listVersionEntities.forEach {
                (filterList as ArrayList<VersionEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}