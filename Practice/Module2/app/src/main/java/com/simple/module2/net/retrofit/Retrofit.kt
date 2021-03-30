package com.simple.module2.net.retrofit

import com.simple.module2.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Retrofit {
    @GET("/api/v1/storeList.json")
    fun getStoreList() : Call<ResAroundRestaurant>

    @GET("/api/v1/amountAvailable.json")
    fun getAmount() : Call<ResAmountModel>

    @GET("/api/v1/noticeList.json")
    fun getNotice() : Call<ResNoticeModel>

    @GET("/api/v1/swipeList.json")
    fun getSwipeBanner() : Call<ResSwipeModel>

    @POST("/api/v1/payment.json")
    fun getPayment(@Query("storeUid") storeUid : String?) : Call<ResStoreModel>


}