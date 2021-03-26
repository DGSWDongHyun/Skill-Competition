package com.simple.module2.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.simple.module2.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       updateWidget()

        Handler().postDelayed(Runnable {
            finish()
        }, 2000)
    }

    fun updateWidget() {
        val initSplash = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        initSplash.fillAfter = true
        
        titleSplash.animation = initSplash
        nameSplash.animation = initSplash
        logo.animation = initSplash
    }
}