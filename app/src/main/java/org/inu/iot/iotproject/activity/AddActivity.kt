package org.inu.iot.iotproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add.*
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.model.ResponseInsertItem
import org.inu.iot.iotproject.model.SanitizerDetailModel
import org.inu.iot.iotproject.util.Retrofits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    var userToken :String = ""
    var userID :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        userToken = intent.extras!!.getString("userToken", "")
        userID = intent.extras!!.getString("userID", "")

        btn_save.setOnClickListener {
//            val paramObject = JsonObject()
//            paramObject.addProperty("city", editText_city.text.toString())
//            paramObject.addProperty("code", editText_code.text.toString())
//            paramObject.addProperty("position", editText_position.text.toString())
//            paramObject.addProperty("serialNumber", editText_serialNum.text.toString())
//            paramObject.addProperty("street", "")

            val intentMain = Intent(applicationContext, MainActivity::class.java)
            intentMain.putExtra("userToken", userToken)
            intentMain.putExtra("userID", userID)
            startActivity(intentMain)
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            MainActivity().finish()
            finish()
//
//            Retrofits.getService().insertSterilizer(userToken, paramObject)
//                .enqueue(object : Callback<ResponseInsertItem> {
//                    override fun onFailure(call: Call<ResponseInsertItem>, t: Throwable) {
//                        Log.d("insert failure", "서버연결을 확인해주세요")
//                        Toast.makeText(applicationContext, "서버연결을 확인해주세요!", Toast.LENGTH_LONG).show()
//                    }
//
//                    override fun onResponse(
//                        call: Call<ResponseInsertItem>, response: Response<ResponseInsertItem>) {
//                        if (response.isSuccessful){
//                            if (response.code() == 200){
//                                Log.d("insert success", "추가 성공!")
//                                Toast.makeText(applicationContext, "추가 성공!", Toast.LENGTH_SHORT).show()
//
//                                val intentMain = Intent(applicationContext, MainActivity::class.java)
//                                intentMain.putExtra("userToken", userToken)
//                                intentMain.putExtra("userID", userID)
//                                startActivity(intentMain)
//                                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
//                                MainActivity().finish()
//                                finish()
//                            }else if (response.code() == 500){
//                                Toast.makeText(applicationContext, "소독기의 시리얼 넘버가 이미 존재합니다", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    }
//                })
        }
    }
}