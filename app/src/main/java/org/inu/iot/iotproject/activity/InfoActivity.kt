package org.inu.iot.iotproject.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info.*
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.util.Retrofits

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        var name = ""
        var email = ""
        var passwd = ""


        btn_ok.setOnClickListener {
            name = editText_Name.text.toString()
            email = editText_Email.toString()
            passwd = editText_passwd.toString()

//            if(!name.equals("") && (!Email.equals("")||Email.length==11)){
//                val intentCamera = Intent(this, CameraActivity::class.java)
//                this.startActivity(intentCamera)
//                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
//            }

            val intentCamera = Intent(this, SignUpActivity::class.java)
            intentCamera.putExtra("SIGN_STATUS", 2);
            this.startActivity(intentCamera)
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            this.finish()
        }
    }
}