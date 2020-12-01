package org.inu.iot.iotproject.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.inu.iot.iotproject.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intentSplash = Intent(this, StartActivity::class.java)
            this.startActivity(intentSplash)
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            this.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}