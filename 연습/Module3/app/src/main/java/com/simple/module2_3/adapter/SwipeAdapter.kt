package com.simple.module2_3.adapter

import android.util.Log
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

    val uri = "http://10.80.163.56:8080"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swipe, parent, false)

        return SwipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.adTitle.text = dataList[position].title
        holder.adListTitle.text = "전체보기 ${position + 1} / ${dataList.size}"
        Log.d("ImageTAG", uri+dataList[position].image)
        Glide.with(holder.adImg.context).load(uri+dataList[position].image).into(holder.adImg)
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