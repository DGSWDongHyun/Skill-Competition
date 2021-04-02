package com.simple.module2_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simple.module2_3.R
import com.simple.module2_3.data.Vertical

class VerticalAdapter(val dataList : ArrayList<Vertical>) : RecyclerView.Adapter<VerticalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical, parent, false)

        return VerticalViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        holder.title.text = dataList[position].title
        holder.content.text = dataList[position].content
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class VerticalViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.titleVertical)
    val content = view.findViewById<TextView>(R.id.contentVertical)
}