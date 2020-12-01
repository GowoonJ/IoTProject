package org.inu.iot.iotproject.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.adapter.AdapterRecyclerHistory
import org.inu.iot.iotproject.model.Data
import org.inu.iot.iotproject.model.SanitizerDetailModel
import org.inu.iot.iotproject.model.UseHistoryModel
import org.inu.iot.iotproject.model.changeHistory
import org.inu.iot.iotproject.util.Retrofits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private var sanitizerId : Long = 0
    private var userToken = ""

    private var dataDetailModel = SanitizerDetailModel()
    var sanitizerData : Data = Data()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sanitizerId = intent.extras?.getInt("sterilizerId", 0)!!.toLong()
        userToken = intent.extras?.getString("userToken", "").toString()

        getSanitizerData()

        setContentView(R.layout.activity_detail)

        btn_select_usedHistory.setOnClickListener {

        }
    }

    private fun getSanitizerData(){
        Retrofits.getService().getSterilizer(userToken, sanitizerId)
            .enqueue(object : Callback<SanitizerDetailModel>{
                override fun onFailure(call: Call<SanitizerDetailModel>, t: Throwable) {
                    Log.d("getDetailData", "failure")
                }

                override fun onResponse(
                    call: Call<SanitizerDetailModel>,
                    response: Response<SanitizerDetailModel>
                ) {
                    if (response.isSuccessful){
                        if (response.code() == 200){
                            dataDetailModel = response.body()!!
                            sanitizerData = dataDetailModel.data
                            dataSetView()
                            Log.d("getDetailData", sanitizerData.serialNumber + sanitizerData.address.position)
                        }
                    }
                }
            })

//        Retrofits.getService().getUsedHistory(userToken, sanitizerId)
//            .enqueue(object : Callback<UseHistoryModel>{
//                override fun onFailure(call: Call<UseHistoryModel>, t: Throwable) {
//                    Log.d("getDetailData", "failure")
//                }
//
//                override fun onResponse(
//                    call: Call<UseHistoryModel>,
//                    response: Response<UseHistoryModel>
//                ) {
//                    if (response.isSuccessful){
//                        if (response.code() == 200){
//                            setHistoryView(response.body()!!)
//                            Log.d("getDetailData", sanitizerData.serialNumber + sanitizerData.address.position)
//                        }
//                    }
//                }
//            })

        Retrofits.getService().getChangedHistory(userToken, sanitizerId)
            .enqueue(object : Callback<changeHistory>{
                override fun onFailure(call: Call<changeHistory>, t: Throwable) {
                    Log.d("getHistoryData", "failure")
                }

                override fun onResponse(
                    call: Call<changeHistory>,
                    response: Response<changeHistory>
                ) {
                    if (response.isSuccessful){
                        if (response.code() == 200){
                            setHistoryView(response.body()!!)
                            Log.d("getDetailData", sanitizerData.serialNumber + sanitizerData.address.position)
                        }
                    }
                }
            })
    }

    @SuppressLint("SetTextI18n")
    private fun dataSetView(){
        tv_serializeNum.text = "일련번호 : "+ sanitizerData.serialNumber
        tv_locationCity.text = sanitizerData.address.city
        tv_locationDetail.text = sanitizerData.address.position

        val percent = sanitizerData.disinfectant.currentCapacity/sanitizerData.disinfectant.totalCapacity*100

        progressBar_Capacity.progress = percent
        tv_Capacity_left.text = "현재 잔여량 : " + percent + "%"
//        tv_Capacity_left.text = "현재 잔여량 : " + sanitizerData.disinfectant.percent.toString() + "%"
        tv_totalCapacity.text = "총량 : "+ sanitizerData.disinfectant.totalCapacity + " 잔여량 : " + sanitizerData.disinfectant.currentCapacity
    }

    @SuppressLint("SetTextI18n")
    private fun setHistoryView(historyData : changeHistory){

        var adapterRecyclerHistory = AdapterRecyclerHistory()
        adapterRecyclerHistory.setDataList(historyData)

        if(historyData.data.size != 0){
            recyclerview_history.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerview_history.adapter = adapterRecyclerHistory
        }
    }
}