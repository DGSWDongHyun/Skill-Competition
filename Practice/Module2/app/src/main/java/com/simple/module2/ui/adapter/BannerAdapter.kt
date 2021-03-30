package com.simple.module2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.module2.R
import com.simple.module2.ResSwipeModel
import com.simple.module2.SwipeBannerData

class BannerAdapter(val context : Context, val bannerDataList : ArrayList<SwipeBannerData>) : RecyclerView.Adapter<BannerViewHolder>(){

    val uri = "http://10.80.163.40:8080/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)

        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        Glide.with(context).load(uri+bannerDataList[position].image).into(holder.bannerImage)
        holder.bannerSize.text = "${position + 1} / ${bannerDataList.size} 전체"
        holder.bannerNum.text = "${bannerDataList[position].title}"
    }

    override fun getItemCount(): Int {
        return bannerDataList.size
    }
}

class BannerViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val bannerSize = view.findViewById<TextView>(R.id.allSize)
    val bannerNum = view.findViewById<TextView>(R.id.bannerNum)
    val bannerImage = view.findViewById<ImageView>(R.id.bannerImage)
}