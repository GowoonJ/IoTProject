package org.inu.iot.iotproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.model.SanitizerDataModel

class AdapterRecyclerSanitizer : RecyclerView.Adapter<AdapterRecyclerSanitizer.ViewHolder>() {
    lateinit var dataList : ArrayList<SanitizerDataModel>

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

    override fun onBindViewHolder(holder: AdapterRecyclerSanitizer.ViewHolder, position: Int) {
        var data : SanitizerDataModel = dataList[position]

        holder.tvName.text = data.id.toString()
        holder.tvCapaticy.text = data.capaticy.toString()
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvLocation: TextView
        var tvLocationDetail: TextView
        var tvCapaticy: TextView

        init {
            tvName = itemView.findViewById(R.id.tv_name)
            tvLocation = itemView.findViewById(R.id.tv_location)
            tvLocationDetail = itemView.findViewById(R.id.tv_location_detail)
            tvCapaticy = itemView.findViewById(R.id.tv_capacity)
        }
    }
}