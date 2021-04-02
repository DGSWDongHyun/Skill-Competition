package com.simple.module2_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)

        updateWidget()
    }

    fun updateWidget() {

        val titleSplash = findViewById<TextView>(R.id.titleSplash)
        val nameSplash = findViewById<TextView>(R.id.nameSplash)
        val imgSplash = findViewById<ImageView>(R.id.logo)

        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_splash)

        animation.fillAfter = true

        titleSplash.animation = animation
        nameSplash.animation = animation
        imgSplash.animation = animation

        Handler().postDelayed(Runnable {
            finish()
        }, 2000)
    }
}