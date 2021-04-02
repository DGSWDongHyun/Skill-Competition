package com.simple.module2_3.net.network

import com.simple.module2_3.data.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Network {
    @GET("api/v1/amountAvailable.json")
    fun getAmount() : Call<AvailableAmount>

    @GET("api/v1/noticeList.json")
    fun getNoticeList() : Call<ResNoticeList>

    @GET("api/v1/noticeView.json")
    fun getNoticeView(@Query("noticeUid") uid : String) : Call<ResNoticeView>

    @POST("api/v1/payment.json")
    fun getPayments(@Query("storeUid") uid : String) : Call<ResPayment>

    @GET("api/v1/storeList.json")
    fun getStoreList() : Call<ResStoreList>

    @GET("api/v1/swipeList.json")
    fun getSwipeList() : Call<ResSwipeList>
}