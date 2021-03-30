package com.simple.module2.ui.adapter

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.simple.module2.R
import com.simple.module2.SearchedModel
import com.simple.module2.ui.adapter.listener.onItemDeleteListener

class SearchedAdapter(var dataList : ArrayList<SearchedModel>, val listener: onItemDeleteListener) : RecyclerView.Adapter<SearchedViewHolder>() {

    fun setData(dataList1: ArrayList<SearchedModel>) {
        this.dataList = dataList1
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)

        return SearchedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchedViewHolder, position: Int) {
        holder.title.text = dataList[position].title

        holder.deleteButton.setOnClickListener {
            listener.onDelete(dataList[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class SearchedViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.searchedText)
    val deleteButton = view.findViewById<AppCompatImageButton>(R.id.deleteButton)
}