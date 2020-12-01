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

class AdapterRecyclerSanitizer : RecyclerView.Adapter<AdapterRecyclerSanitizer.ViewHolder>() {
    private var dataList : ArrayList<SanitizerDataModel> = ArrayList()

    fun setDataList(dataSet : ArrayList<SanitizerDataModel>){
        this.dataList = dataSet
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    //클릭리스너 선언
    private lateinit var itemClickListener: ItemClickListener

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

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

        holder.progressBar.progress = data.capaticy

        holder.tvName.text = data.id.toString()
        holder.tvCapaticy.text = "잔량 : " + data.capaticy.toString() + "%"
        if (data.runStatus == "RUN")
            holder.tvRunable.text = "사용 중"
        else
            holder.tvRunable.text = "미사용"

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvLocation: TextView
        var tvRunable: TextView
        var tvCapaticy: TextView
        var progressBar : ProgressBar

        init {
            tvName = itemView.findViewById(R.id.tv_name)
            tvLocation = itemView.findViewById(R.id.tv_location)
            tvRunable = itemView.findViewById(R.id.tv_runable)
            tvCapaticy = itemView.findViewById(R.id.tv_capacity)
            progressBar = itemView.findViewById(R.id.progressBar)
        }
    }
}