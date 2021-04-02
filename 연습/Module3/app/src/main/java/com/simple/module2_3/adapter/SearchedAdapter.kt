package com.simple.module2_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simple.module2_3.R
import com.simple.module2_3.adapter.listener.OnClickDeleteListener

class SearchedAdapter(var dataList : ArrayList<String>, val listener : OnClickDeleteListener) : RecyclerView.Adapter<SearchedViewHolder>() {

    fun setData(dataList: ArrayList<String>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)

        return SearchedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchedViewHolder, position: Int) {
        holder.searchedText.text = dataList[position]
        holder.deleteButton.setOnClickListener {
            listener.onClickItem(dataList[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

class SearchedViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val searchedText = view.findViewById<TextView>(R.id.searchedTextView)
    val deleteButton = view.findViewById<ImageButton>(R.id.deleteButton)
}