package org.inu.iot.iotproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.model.SanitizerDataModel

class AdapterRecyclerSanitizer : RecyclerView.Adapter<AdapterRecyclerSanitizer.ViewHolder>() {
    private var dataList : ArrayList<SanitizerDataModel> = ArrayList()

    fun setDataList(dataSet : ArrayList<SanitizerDataModel>){
        this.dataList = dataSet
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }
    interface OnItemClickListener {
        fun onItemClick(v: View?, position: Int)
    }
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterRecyclerSanitizer.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_grid, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.width = (parent.width * 0.451).toInt()
        view.layoutParams = layoutParams

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdapterRecyclerSanitizer.ViewHolder, position: Int) {
        var data : SanitizerDataModel = dataList[position]

        holder.tvName.text = data.id.toString()
        holder.tvCapaticy.text = "잔량 : " + data.capaticy.toString() + "%"
        if (data.runStatus == "RUN")
            holder.tvRunable.text = "사용 중"
        else
            holder.tvRunable.text = "미사용"
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvLocation: TextView
        var tvRunable: TextView
        var tvCapaticy: TextView

        init {
            tvName = itemView.findViewById(R.id.tv_name)
            tvLocation = itemView.findViewById(R.id.tv_location)
            tvRunable = itemView.findViewById(R.id.tv_runable)
            tvCapaticy = itemView.findViewById(R.id.tv_capacity)
        }
    }


}