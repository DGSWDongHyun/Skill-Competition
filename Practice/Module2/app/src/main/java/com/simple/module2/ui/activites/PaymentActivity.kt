package com.simple.module2.ui.activites

import android.graphics.Bitmap
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.util.TypedValue
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.simple.module2.R
import com.simple.module2.ResStoreModel
import com.simple.module2.net.RetrofitModule
import com.simple.module2.net.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity : AppCompatActivity() {

    private lateinit var code : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val service = RetrofitModule.getInstance()
        val api = service.create(Retrofit::class.java)
        val imageView = findViewById<ImageView>(R.id.barcodeImage)
        val barcodeNum = findViewById<TextView>(R.id.barcodeNum)
        val price = findViewById<TextView>(R.id.amountText2)
        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        val paymentSuccess = findViewById<Button>(R.id.paymentSuccess)
        val paymentCancel = findViewById<Button>(R.id.paymentCancel)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = intent.getStringExtra("storeName").toString()

        paymentSuccess.setOnClickListener {
            finish()
        }

        paymentCancel.setOnClickListener {
            finish()
        }

        api.getPayment(intent.getStringExtra("storeUid").toString()).enqueue(object :
            Callback<ResStoreModel> {
            override fun onResponse(call: Call<ResStoreModel>, response: Response<ResStoreModel>) {
                if (response.code() == 200) {
                    code = response.body()!!.paymentCode.toString()

                    val barcode = createBarcode(code)
                    imageView.setImageBitmap(barcode)
                    barcodeNum.text = response.body()!!.paymentCode
                    price.text = intent.getStringExtra("availablePrice")+"원"

                    setUpCountDownTimer()

                }
            }

            override fun onFailure(call: Call<ResStoreModel>, t: Throwable) {
                Log.d("TAG", "${t.message}")
            }

        })
    }
    private val WHITE: Int = 0xFFFFFFFF.toInt()
    private val BLACK: Int = 0xFF000000.toInt()


    fun setUpCountDownTimer() {
        val textTimer = findViewById<TextView>(R.id.countDownText)

        val countDownTimer = object : CountDownTimer(900000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                textTimer.text = "유효시간 : ${(millisUntilFinished/1000)%3600/60}분 ${(millisUntilFinished/1000)%3600%60}초"
            }
            override fun onFinish() {
                Toast.makeText(applicationContext, "유효시간이 만료되어 자동으로 종료합니다.", Toast.LENGTH_LONG).show()
                finish()
            }
        }

        countDownTimer.start()
    }

    fun createBarcode(code: String) : Bitmap {
        val widthPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 390f,
            resources.displayMetrics
        )
        val heightPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 111f,
            resources.displayMetrics
        )
        val format: BarcodeFormat = BarcodeFormat.CODE_128
        val matrix: BitMatrix = MultiFormatWriter().encode(
            code,
            format,
            widthPx.toInt(),
            heightPx.toInt()
        )
        val bitmap = createBitmap(matrix)
        return bitmap
    }

    fun createBitmap(matrix: BitMatrix): Bitmap {
        val width = matrix.width
        val height = matrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (matrix.get(x, y)) BLACK else WHITE)
            }
        }
        return bitmap
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