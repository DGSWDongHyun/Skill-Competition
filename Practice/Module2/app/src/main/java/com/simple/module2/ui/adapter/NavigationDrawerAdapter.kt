package com.simple.module2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simple.module2.DrawerItems
import com.simple.module2.R

class NavigationDrawerAdapter(val insertData : ArrayList<DrawerItems>) : RecyclerView.Adapter<DrawerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drawer, parent, false)
        return DrawerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {
        holder.titleTextView.text = insertData[position].title
        holder.contentTextView.text = insertData[position].content
    }

    override fun getItemCount(): Int {
        return insertData.size
    }
}


class DrawerViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
    val contentTextView = view.findViewById<TextView>(R.id.contentTextView)
}
