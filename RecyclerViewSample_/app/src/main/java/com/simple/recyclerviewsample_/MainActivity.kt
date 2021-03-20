package com.simple.recyclerviewsample_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val arraySample : ArrayList<Chat> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        arraySample.add(Chat("","오태식",
            "ㅎㅇ",SimpleDateFormat("HH:mm:ss").format(Date(System.currentTimeMillis()))))

        recyclerViewSample.layoutManager = LinearLayoutManager(this)
        recyclerViewSample.adapter = KakaoAdapter(arraySample)
    }
}