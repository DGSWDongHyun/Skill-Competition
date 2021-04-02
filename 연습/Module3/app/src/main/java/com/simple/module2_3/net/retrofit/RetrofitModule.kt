package com.simple.module2_3.net.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private var instance : Retrofit ?= null

    fun getInstance() : Retrofit {
        if(instance == null){
            instance = Retrofit.Builder()
                .baseUrl("http://10.80.163.56:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return instance!!
    }
}