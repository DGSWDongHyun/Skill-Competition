package com.simple.module2_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.module2_3.R
import com.simple.module2_3.adapter.listener.OnClickListenerAround
import com.simple.module2_3.data.StoreList

class AroundStoreAdapter(val dataList : ArrayList<StoreList>,val listener : OnClickListenerAround) : RecyclerView.Adapter<AroundViewHolder>() {

    val uri = "http://10.80.163.56:8080"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AroundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_around, parent, false)

        return AroundViewHolder(view)
    }

    override fun onBindViewHolder(holder: AroundViewHolder, position: Int) {
        holder.titleStore.text = dataList[position].storeName + "\n" + dataList[position].storeBranchName
        Glide.with(holder.imgStore.context).load(uri+dataList[position].storeImage).into(holder.imgStore)
        holder.itemView.setOnClickListener {
            listener.onClickItem(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

class AroundViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val titleStore = view.findViewById<TextView>(R.id.titleStoreName)
    val imgStore = view.findViewById<ImageView>(R.id.imageStore)
}