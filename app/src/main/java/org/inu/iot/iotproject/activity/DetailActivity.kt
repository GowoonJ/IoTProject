package org.inu.iot.iotproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.model.Data
import org.inu.iot.iotproject.model.SanitizerDetailModel
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
        setContentView(R.layout.activity_detail)

        sanitizerId = intent.extras?.getLong("sterilizerId", 0)!!
        userToken = intent.extras?.getString("userToken", "").toString()

        getSanitizerData()
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
    }

    private fun dataSetView(){

    }
}