package com.simple.module2.ui.activites

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.simple.module2.R

class NoticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        val toolbar = findViewById<Toolbar>(R.id.toolbar3)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.title = "공지사항"

        val title = findViewById<TextView>(R.id.titleNotice)
        val content = findViewById<TextView>(R.id.contentNotice)
        val date = findViewById<TextView>(R.id.dateContent)
        val amount = findViewById<TextView>(R.id.amountText3)

        amount.text = intent.getStringExtra("availablePrice") + "원"
        title.text = intent.getStringExtra("title")
        content.text = intent.getStringExtra("cn")
        date.text = intent.getStringExtra("date")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}