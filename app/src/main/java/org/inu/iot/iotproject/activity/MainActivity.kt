package org.inu.iot.iotproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.adapter.AdapterRecyclerSanitizer
import org.inu.iot.iotproject.model.SanitizerDataModel
import org.inu.iot.iotproject.model.sterilizersList
import org.inu.iot.iotproject.util.Retrofits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var sList : ArrayList<SanitizerDataModel> = ArrayList<SanitizerDataModel>()
    var enableListCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val userToken = intent.extras!!.getString("userToken", "")

        val adapterRecyclerSanitizer : AdapterRecyclerSanitizer = AdapterRecyclerSanitizer()

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

        Retrofits.getService().getSterilizers("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNjA2Nzk4Njc1LCJleHAiOjE2MDY4MDIyNzV9.8jBscgoSfTSOqm0wmUN4U0koRvKuiRePkfhzDpLdSLQ")
            .enqueue( object : Callback<sterilizersList> {
                override fun onFailure(call: Call<sterilizersList>, t: Throwable) {
                    Toast.makeText(applicationContext, "서버연결을 확인해주세요!", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<sterilizersList>,
                    response: Response<sterilizersList>
                ) {
                    if (response.isSuccessful){
                        Log.d("success state", response.code().toString() + response.body()?.data)
                        Toast.makeText(applicationContext, "리스트를 가져옵니다.", Toast.LENGTH_LONG).show()
                        sList = response.body()!!.data
                        enableListCount = sList.size

                        if(enableListCount == 0 ){
                            recyclerView.visibility = View.INVISIBLE
                            tvEmptyValue.visibility = View.VISIBLE
                        }
                        else{
                            recyclerView.visibility = View.VISIBLE
                            tvEmptyValue.visibility = View.INVISIBLE

                            adapterRecyclerSanitizer.setDataList(sList)
                            recyclerView.adapter = adapterRecyclerSanitizer
                        }
                    }
                }
            })


    }
}