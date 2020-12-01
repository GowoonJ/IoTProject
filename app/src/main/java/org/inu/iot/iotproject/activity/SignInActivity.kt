package org.inu.iot.iotproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.editText_Email
import kotlinx.android.synthetic.main.activity_sign_up.editText_passwd
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.model.SignInResponse
import org.inu.iot.iotproject.model.token
import org.inu.iot.iotproject.util.Retrofits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var email = ""
        var passwd = ""

        var token : token = token()
        var userToken : String = ""

        var signInSuccess = false

        btn_sign_in.setOnClickListener {
            email = editText_Email.text.toString()
            passwd = editText_passwd.text.toString()

            Log.d("signintest", email+passwd)
            Retrofits.getService().signIn(email, passwd)
                .enqueue(object : Callback<SignInResponse> {
                    override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, "서버연결을 확인해주세요!", Toast.LENGTH_LONG).show()
                        Log.d("fail",t.toString())
                    }

                    override fun onResponse(
                        call: Call<SignInResponse>,
                        response: Response<SignInResponse>
                    ) {
                        if (response.isSuccessful && response.code() == 200){
                            Toast.makeText(applicationContext, "로그인성공!", Toast.LENGTH_LONG).show()
                            token.userToken = response.body()!!.data
                            userToken = response.body()!!.data

                            signInSuccess = true
                            Log.d("success", response.message() + signInSuccess.toString())
                        }
                    }
                })

            if(signInSuccess){
                val intentMain = Intent(this, MainActivity::class.java)
                intentMain.putExtra("userToken", userToken)
                intentMain.putExtra("userID", email)
                this.startActivity(intentMain)
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
                this.finish()
            }
        }
    }
}