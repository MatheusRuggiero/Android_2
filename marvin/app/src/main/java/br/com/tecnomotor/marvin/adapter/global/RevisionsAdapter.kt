package br.com.tecnomotor.marvin.adapter.global

import android.content.Context
import br.com.tecnomotor.marvin.dao.entities.global.RevisionEntity

class RevisionsAdapter(
    private var filterList: List<RevisionEntity>,
    private var context: Context,
    private var listener: OnItemClickListener
) {

    fun setData(listRevisionEntities: List<RevisionEntity>) {
        filterList = ArrayList()
        if (!listRevisionEntities.isNullOrEmpty()) {
            listRevisionEntities.forEach {
                (filterList as ArrayList<RevisionEntity>).add(it)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}