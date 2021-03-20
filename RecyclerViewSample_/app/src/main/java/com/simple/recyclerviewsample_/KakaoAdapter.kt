package com.simple.recyclerviewsample_

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.kakao_item.view.*

class KakaoAdapter(val datas : ArrayList<Chat>) : RecyclerView.Adapter<KakaoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakaoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.kakao_item, parent, false)
        return KakaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: KakaoViewHolder, position: Int) {
        holder.userNameText.text = datas[position].userName
        holder.contextText.text = datas[position].chatContent
        holder.timeText.text = datas[position].time

    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class KakaoViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val profileImageView = view.profileImageView
    val userNameText = view.userTextView
    val contextText = view.contentTextView
    val timeText = view.timeTextView
}