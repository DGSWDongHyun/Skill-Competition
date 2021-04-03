package com.simple.module2_3.adapter.listener

import com.simple.module2_3.data.NoticeList
import com.simple.module2_3.data.StoreList

interface OnClickDeleteListener {
    fun onClickItem(data : String)
}

interface OnClickListenerNotice {
    fun onClickItem(data : NoticeList)
}

interface OnClickListenerAround {
    fun onClickItem(data : StoreList)
}