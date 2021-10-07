package com.example.corner_library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var logoImage = findViewById<ImageView>(R.id.splash_logo)
        var logo_ani : Animation

        var inputLayout = findViewById<LinearLayout>(R.id.input_info)
        var input_ani : Animation

        logo_ani = AnimationUtils.loadAnimation(this, R.anim.slide_logo)
        input_ani = AnimationUtils.loadAnimation(this, R.anim.input_info)

        logoImage.startAnimation(logo_ani)
        inputLayout.startAnimation(input_ani)
    }
}