package org.inu.iot.iotproject.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.util.Retrofits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var name = ""
        var email = ""
        var passwd = ""

        var signUpSuccess = false;


        btn_sign_up.setOnClickListener {
            name = editText_Name.text.toString()
            email = editText_Email.text.toString()
            passwd = editText_passwd.text.toString()

            Retrofits.getService().signUp(email, name, passwd)
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

                            signUpSuccess = true
                        }
                    }
                })

            if(signUpSuccess){
                val intentSignIn = Intent(this, SignInActivity::class.java)
                this.startActivity(intentSignIn)
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
                this.finish()
            }
        }
    }
}