package com.simple.module2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simple.module2.NoticeModel
import com.simple.module2.R
import com.simple.module2.ui.adapter.listener.onClickItemListenerAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoticeAdapter(val dataList : ArrayList<NoticeModel>, val listener : onClickItemListenerAdapter) : RecyclerView.Adapter<NoticeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notice, parent, false)

        return NoticeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.title.text = dataList[position].title

        val longTime = dataList[position].registDt!!.toLong()
        holder.dateTime.text = SimpleDateFormat("yyyy.MM.dd").format(Date(longTime))

        holder.itemView.setOnClickListener {
            listener.onClickItem(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return if(dataList.size != 0) dataList.size else 0
    }
}

class NoticeViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.noticeTitle)
    val dateTime = view.findViewById<TextView>(R.id.noticeDateTime)
}