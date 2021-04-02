package com.simple.module2_3.data

data class Horizontal(val title : String?, val image : Int?)

data class Vertical(val title : String?, val content : String?)

data class AvailableAmount(val amount : String?)

data class NoticeList(val noticeUid : String?, val registDt : String?, val cn : String?, val title : String?)

data class ResNoticeList(val totalCnt : Int, val list : ArrayList<NoticeList>)

data class ResNoticeView(val registDt: String?, val cn: String?, val noticeUid: String?, val title: String?)

data class ResPayment(val storeImage : String?, val storeBranchName : String?, val vaildMills : Int?, val paymentCode : String?, val storeUid : String?, val storeName : String?)

data class StoreList(val storeImage: String?, val storeBranchName: String?, val storeUid: String?,val storeName: String?)

data class ResStoreList(val totalCnt: Int, val list: ArrayList<StoreList>)

data class SwipeList(val image : String?, val image2x : String?, val title : String?, val url : String?)

data class ResSwipeList(val totalCnt: Int, val list : ArrayList<SwipeList>)