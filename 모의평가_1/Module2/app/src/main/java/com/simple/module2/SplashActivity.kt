package com.simple.module2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.simple.module2.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        initAnim()

        Handler().postDelayed(Runnable {
                finish()
        }, 2500)
    }
    private fun initAnim() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        anim.fillAfter = true

        splashBinding.splashLayout.animation = anim
    }

    override fun onDestroy() {
        startActivity(Intent(this, InitActivity::class.java))
        super.onDestroy()
    }

    override fun onBackPressed() {
        return
    }
}