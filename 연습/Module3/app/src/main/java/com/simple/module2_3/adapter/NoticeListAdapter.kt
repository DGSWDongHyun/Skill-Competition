package com.simple.module2_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simple.module2_3.R
import com.simple.module2_3.data.NoticeList
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoticeListAdapter(val dataList : ArrayList<NoticeList>) : RecyclerView.Adapter<NoticeListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notice, parent, false)

        return NoticeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeListViewHolder, position: Int) {
        holder.titleNotice.text = dataList[position].title
        holder.titleDateNotice.text = SimpleDateFormat("yyyy. MM. dd").format(Date(dataList[position].registDt!!.toLong()))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

class NoticeListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val titleNotice = view.findViewById<TextView>(R.id.titleNotice)
    val titleDateNotice = view.findViewById<TextView>(R.id.titleDateNotice)
}