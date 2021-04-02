package com.simple.module2_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.module2_3.R
import com.simple.module2_3.data.Horizontal

class HorizontalAdapter(val dataList : ArrayList<Horizontal>) : RecyclerView.Adapter<HorizontalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal, parent, false)

        return HorizontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        Glide.with(holder.imageIcon.context).load(dataList[position].image).into(holder.imageIcon)
        holder.titleText.text = dataList[position].title
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class HorizontalViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val titleText = view.findViewById<TextView>(R.id.titleMenu)
    val imageIcon = view.findViewById<ImageView>(R.id.imageIcon)
}