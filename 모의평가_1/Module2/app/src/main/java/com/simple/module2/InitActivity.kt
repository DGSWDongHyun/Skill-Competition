package com.simple.module2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.simple.module2.databinding.ActivityInitBinding
import kotlin.system.exitProcess

class InitActivity : AppCompatActivity() {

    private lateinit var initBinding : ActivityInitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(initBinding.root)

        updateWidget()
    }
    private fun updateWidget() {
        initBinding.checkAccept.setOnClickListener {
            if(initBinding.checkList1.isChecked && initBinding.checkList2.isChecked)
                startActivity(Intent(this, PinActivity::class.java))
            else
                Snackbar.make(it, "필수 동의 항목 중 하나가 누락되어있습니다.", Snackbar.LENGTH_LONG).show()
        }

        initBinding.checkRefuse.setOnClickListener {
            Snackbar.make(it, "필수 동의를 하지 않으시면 해당 어플리케이션을 이용할 수 없습니다.", Snackbar.LENGTH_LONG).show()
        }
    }
}