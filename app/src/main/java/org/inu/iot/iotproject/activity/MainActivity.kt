package org.inu.iot.iotproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        var adapterRecyclerSanitizer : AdapterRecyclerSanitizer = AdapterRecyclerSanitizer()

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

        Retrofits.getService().getSterilizers("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZXMiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNjA2NzkyODc2LCJleHAiOjE2MDY3OTY0NzZ9.e94pz--Y5TiJuRXiPWbQGbj_HaDC3Zyq9zQ6uuxwrFg")
            .enqueue( object : Callback<sterilizersList> {
                override fun onFailure(call: Call<sterilizersList>, t: Throwable) {
                    Toast.makeText(applicationContext, "서버연결을 확인해주세요!", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<sterilizersList>,
                    response: Response<sterilizersList>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext, "리스트를 가져옵니다.", Toast.LENGTH_LONG).show()
                        sList = response.body()!!.data
                        enableListCount = sList.size
                    }
                }
            })
        adapterRecyclerSanitizer.dataList = this.sList
        recyclerView.adapter = adapterRecyclerSanitizer

        if(enableListCount == 0 ){
            recyclerView.visibility = View.INVISIBLE
            tvEmptyValue.visibility = View.VISIBLE
        }
        else{
            recyclerView.visibility = View.VISIBLE
            tvEmptyValue.visibility = View.INVISIBLE
        }
    }
}