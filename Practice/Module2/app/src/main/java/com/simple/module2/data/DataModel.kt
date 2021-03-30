package com.simple.module2

data class DrawerItems(val title : String?, val content : String?)

data class ResAmountModel(val amount : Int?)

data class ResNoticeModel(val totalCnt : Int?, val list : ArrayList<NoticeModel>)

data class NoticeModel(val noticeUid : String?, val title : String?, val cn : String?, val registDt : String?)

data class ResAroundRestaurant(val totalCnt: Int?, val list : ArrayList<AroundData>)

data class AroundData(val storeImage : String?, val storeBranchName : String?, val storeUid : String?, val storeName : String?)

data class ResSwipeModel(val totalCnt: Int?, val list : ArrayList<SwipeBannerData>)

data class SwipeBannerData(val image : String?, val image2x : String?, val title : String?, val url : String?)

data class ResStoreModel(val storeImage: String?, val storeBranchName: String?, val vaildMills : Int?, val paymentCode : String?, val storeUid: String?, val storeName: String?)

data class SearchedModel(val title : String?)