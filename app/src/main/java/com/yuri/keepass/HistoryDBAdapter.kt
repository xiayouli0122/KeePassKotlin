package com.yuri.keepass

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yuri.keepass.model.HistoryFile

class HistoryDBAdapter : RecyclerView.Adapter<HistoryDBAdapter.MyViewHolder> {

    private var context: Context? = null
    private var dataList : List<HistoryFile>? = null

    constructor(context: Context, list: List<HistoryFile>?) {
        this.context = context
        this.dataList = list
    }

    fun setDataList(list: ArrayList<HistoryFile>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_history_db, parent, false);
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val item = dataList!!.get(position)
        holder!!.nameView.text = item.name
        holder.pathView.text = item.path
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var nameView:TextView
        var pathView:TextView
        init {
            nameView = itemView!!.findViewById(R.id.tv_item_history_name)
            pathView = itemView.findViewById(R.id.tv_item_history_path)
        }
    }

}
