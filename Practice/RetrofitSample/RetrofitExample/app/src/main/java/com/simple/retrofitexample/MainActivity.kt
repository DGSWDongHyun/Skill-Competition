package com.simple.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myLoginData = ReqLoginData("ydh12256", "dong")
        val service = retrofit.create(RetrofitNetwork::class.java)

        service.login(myLoginData).enqueue(object : Callback<Responses<ResLoginData>> {
            override fun onResponse(
                call: Call<Responses<ResLoginData>>,
                response: Response<Responses<ResLoginData>>
            ) {
                Log.d("Result MainActivity :: " , response.body()!!.data.token)
            }

            override fun onFailure(call: Call<Responses<ResLoginData>>, t: Throwable) {
            }

        })

    }
}

data class ReqLoginData(var id : String?, var pw : String?)

data class Responses<T>(val status : Int?, val message : String?, val data : T)
data class ResLoginData(val name : String?, val token : String)

interface RetrofitNetwork {
    @POST("auth/login")
    fun login(@Body reqData : ReqLoginData) : Call<Responses<ResLoginData>>
}

