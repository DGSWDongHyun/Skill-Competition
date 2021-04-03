package com.simple.module2_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.simple.module2_3.data.ResPayment
import com.simple.module2_3.data.module.ExceptionModule.shutOffApp
import com.simple.module2_3.data.module.ZxingModule.createBarcode
import com.simple.module2_3.net.network.Network
import com.simple.module2_3.net.retrofit.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val service = RetrofitModule.getInstance()
        val api = service.create(Network::class.java)

        val toolbar : Toolbar = findViewById(R.id.toolbar2)
        val imageBarcode : ImageView = findViewById(R.id.barcodeImage)
        val title : TextView = findViewById(R.id.barcodeTitle)

        setSupportActionBar(toolbar)

        api.getPayments(intent.getStringExtra("storeUid")!!).enqueue(object : Callback<ResPayment> {
            override fun onResponse(call: Call<ResPayment>, response: Response<ResPayment>) {
                if(response.code() == 200) {
                    toolbar.title = response.body()!!.storeName
                    imageBarcode.setImageBitmap(createBarcode(response.body()!!.paymentCode!!, applicationContext))
                    title.text = response.body()!!.paymentCode
                }else{
                    shutOffApp(this@PaymentActivity)
                }
            }

            override fun onFailure(call: Call<ResPayment>, t: Throwable) {
                shutOffApp(this@PaymentActivity)
            }
        })
    }
}