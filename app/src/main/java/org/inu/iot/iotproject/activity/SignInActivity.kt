package org.inu.iot.iotproject.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.editText_Email
import kotlinx.android.synthetic.main.activity_sign_up.editText_passwd
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.model.SignInResponse
import org.inu.iot.iotproject.util.Retrofits
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var email = ""
        var passwd = ""

        var userToken : String = ""

        var signInSuccess = false

        btn_sign_in.setOnClickListener {
            email = editText_Email.text.toString()
            passwd = editText_passwd.text.toString()
//            email = "juwom0831@naver.com"
//            passwd = "rjatjd0815*"

            val paramObject = JsonObject()
            paramObject.addProperty("email", email)
            paramObject.addProperty("password", passwd)

            Retrofits.getService().signIn(paramObject)
                .enqueue(object : Callback<SignInResponse> {
                    override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, "서버연결을 확인해주세요!", Toast.LENGTH_LONG).show()
                        Log.d("fail",t.toString())
                    }

                    override fun onResponse(
                        call: Call<SignInResponse>, response: Response<SignInResponse>) {
                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                Toast.makeText(applicationContext, "로그인성공!", Toast.LENGTH_LONG)
                                    .show()
                                userToken = response.body()!!.data

                                signInSuccess = true
                                Log.d("success", response.message() + signInSuccess.toString())

                                if(signInSuccess){
                                    val intentMain = Intent(applicationContext, MainActivity::class.java)
                                    intentMain.putExtra("userToken", userToken)
                                    intentMain.putExtra("userID", email)
                                    startActivity(intentMain)
                                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
                                    finish()
                                }
                            }
                            else{
                                Log.d("response code", response.code().toString())
                                Log.d("body", response.message())
                            }
                        }
                        Log.d("response code 500", response.code().toString())
                    }
                })
        }
    }
}