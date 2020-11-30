package org.inu.iot.iotproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.inu.iot.iotproject.R

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btn_sign_in.setOnClickListener {
            val intentCamera = Intent(this, MainActivity::class.java)
            intentCamera.putExtra("SIGN_STATUS", 1);
            this.startActivity(intentCamera)
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            this.finish()
        }

        btn_sign_up.setOnClickListener {
            val intentInfo = Intent(this, SignUpActivity::class.java)
//            intentInfo.putExtra("SIGN_UP", 2);
            this.startActivity(intentInfo)
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
        }
    }
}