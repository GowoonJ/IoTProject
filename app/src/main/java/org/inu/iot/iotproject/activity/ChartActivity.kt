package org.inu.iot.iotproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_chart.*
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.model.Datas
import org.inu.iot.iotproject.model.SanitizerDetailModel
import org.inu.iot.iotproject.model.UseHistoryModel
import org.inu.iot.iotproject.util.Retrofits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChartActivity : AppCompatActivity() {
    var userToken :String = ""
    private var sanitizerId : Long = 0

    var historyModel : UseHistoryModel = UseHistoryModel()
    
//    var date : ArrayList<String> = ArrayList()
//    var barEntry = BarEntry(0f,0f)
//    var capaticy : ArrayList<BarEntry> = ArrayList()

    val values : ArrayList<Entry> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userToken = intent.extras?.getString("userToken", "").toString()
        sanitizerId = intent.extras?.getLong("sterilizerId", 0)!!

        Retrofits.getService().getUsedHistory(userToken, sanitizerId)
            .enqueue(object : Callback<UseHistoryModel>{
                override fun onFailure(call: Call<UseHistoryModel>, t: Throwable) {
                    Log.d("getHistoryData", "failure")
                }

                override fun onResponse( call: Call<UseHistoryModel>, response: Response<UseHistoryModel>) {
                    if (response.isSuccessful){
                        if (response.code() == 200){
                            historyModel = response.body()!!
                            for (i in 0 until historyModel.data.size){
                                val datas = historyModel.data.get(i)
                                val entry = Entry((i+1).toFloat(), datas.sumOfUseAmount.toFloat())
                                values.add(entry)
//                                date.add(i, datas.date)
//                                barEntry = BarEntry(datas.sumOfUseAmount.toFloat(),i.toFloat())
//                                capaticy.add(i,barEntry)
                                Log.d("getHistoryData", "success")
                            }
                            setChart()
                        }
                    }
                }
            })

        setContentView(R.layout.activity_chart)
    }

    private fun setChart(){
        val xAxis : XAxis =LineChart.xAxis

        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 11f
            setDrawGridLines(false)
            granularity = 1f
            axisMinimum = 0f
            isGranularityEnabled = true
        }

        LineChart.apply {
            axisRight.isEnabled = false
            axisLeft.axisMaximum = 1000f
            setBackgroundColor(resources.getColor(R.color.white))
            legend.apply {
                textSize = 15f
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)
            }
        }

        val lineDataSet = LineDataSet(values, "총 사용량 (mL)")
        lineDataSet.apply {
            color = resources.getColor(R.color.colorMain)
            setCircleColor(resources.getColor(R.color.colorMain))
            valueTextSize = 10f
            lineWidth = 2f
            circleRadius = 3f
            fillAlpha = 0
            fillColor = resources.getColor(R.color.colorMain)

            setDrawValues(true)
        }
        val dataSets = ArrayList<LineDataSet>()
        dataSets.add(lineDataSet)

        val lineData = LineData(dataSets as List<ILineDataSet>?)
        LineChart.data = lineData
    }
}