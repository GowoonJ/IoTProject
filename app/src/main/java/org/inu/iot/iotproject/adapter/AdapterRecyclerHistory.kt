package org.inu.iot.iotproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.model.SanitizerDataModel
import org.inu.iot.iotproject.model.changeHistory

class AdapterRecyclerHistory : RecyclerView.Adapter<AdapterRecyclerHistory.ViewHolder>() {
    private var dataList : changeHistory = changeHistory()

    fun setDataList(dataSet : changeHistory){
        this.dataList = dataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterRecyclerHistory.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdapterRecyclerHistory.ViewHolder, position: Int) {
        val data = dataList.data[position]

        holder.tvDateTime.text = data.changeTime
        holder.tvCount.text = data.changeHistoryId.toString()
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvDateTime: TextView = itemView.findViewById(R.id.tv_history_time)
        var tvCount: TextView = itemView.findViewById(R.id.tv_count)
    }
}