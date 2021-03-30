package com.simple.module2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.module2.AroundData
import com.simple.module2.R
import com.simple.module2.ui.adapter.listener.onClickItemListener

class AroundRestaurantAdapter(val context : Context, val dataList : ArrayList<AroundData>,val listener : onClickItemListener) : RecyclerView.Adapter<AroundRestViewHolder>() {

    val uri = "http://10.80.163.40:8080/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AroundRestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_payment_around, parent, false)

        return AroundRestViewHolder(view)
    }

    override fun onBindViewHolder(holder: AroundRestViewHolder, position: Int) {
        Glide.with(context).load(uri+dataList[position].storeImage).centerCrop().into(holder.image)
        holder.title.text = dataList[position].storeName

        holder.itemView.setOnClickListener {
            listener.onClickItem(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return if(dataList.size != 0) dataList.size else 0
    }
}

class AroundRestViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val image = view.findViewById<ImageView>(R.id.aroundImage)
    val title = view.findViewById<TextView>(R.id.aroundText)
}


