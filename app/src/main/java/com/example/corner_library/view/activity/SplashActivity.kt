package com.example.corner_library.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.corner_library.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
//            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1000)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }
}