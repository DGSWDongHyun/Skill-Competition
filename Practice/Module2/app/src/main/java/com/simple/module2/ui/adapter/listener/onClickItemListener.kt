package com.simple.module2.ui.adapter.listener

import com.simple.module2.AroundData
import com.simple.module2.NoticeModel
import com.simple.module2.SearchedModel

interface onClickItemListener {
    fun onClickItem(data : AroundData)
}

interface onClickItemListenerAdapter {
    fun onClickItem(data : NoticeModel)
}

interface onItemDeleteListener {
    fun onDelete(data : SearchedModel)
}