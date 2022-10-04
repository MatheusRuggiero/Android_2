package br.com.tecnomotor.marvin.adapter.injector

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorPlatformEntity

class InjectorsPlatformsAdapter(
    private var list: List<InjectorPlatformEntity>,
    private var context: Context,
    private var listener : OnItemClickListener
) : RecyclerView.Adapter<InjectorsPlatformsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View? = null
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_bomb_points, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun setData(list1: List<InjectorPlatformEntity>) {
        list = ArrayList()
        if(!list1.isNullOrEmpty()){
            list1.forEach {
                (list as ArrayList<InjectorPlatformEntity>).add(it)
            }
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position : Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}