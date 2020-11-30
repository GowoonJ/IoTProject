package org.inu.iot.iotproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.editText_Email
import kotlinx.android.synthetic.main.activity_sign_up.editText_passwd
import org.inu.iot.iotproject.R
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

        var signInSuccess = false

        btn_sign_in.setOnClickListener {
            email = editText_Email.toString()
            passwd = editText_passwd.toString()

            Retrofits.getService().signIn(email, passwd)
                .enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Toast.makeText(applicationContext, "서버연결을 확인해주세요!", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if (response.isSuccessful && response.code() == 200){
                            Toast.makeText(applicationContext, "가입이 완료되었습니다\n 로그인 해주세요", Toast.LENGTH_LONG).show()
                            signInSuccess = true
                        }
                    }
                })

            if(signInSuccess){
                val intentMain = Intent(this, MainActivity::class.java)
                this.startActivity(intentMain)
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
                this.finish()
            }
        }
    }
}