package com.simple.module2_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.module2_3.R
import com.simple.module2_3.data.SwipeList

class SwipeAdapter(val dataList : ArrayList<SwipeList>) : RecyclerView.Adapter<SwipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swipe, parent, false)

        return SwipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.adTitle.text = dataList[position].title
        holder.adListTitle.text = "전체보기 ${position + 1} / ${dataList.size}"
        Glide.with(holder.adImg.context).load(dataList[position].image).into(holder.adImg)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

class SwipeViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val adTitle = view.findViewById<TextView>(R.id.adTitle)
    val adListTitle = view.findViewById<TextView>(R.id.adListTitle)
    val adImg = view.findViewById<ImageView>(R.id.swipeImage)
}