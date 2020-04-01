package com.news.sample.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.news.sample.R

class KeywordAdapter() : RecyclerView.Adapter<KeywordAdapter.MyViewHolder>() {

    var limitedItemCount: Int? = null
    lateinit var context: Context
    private var keyWordList: List<String> = ArrayList()

    constructor(keyWordList: List<String>) : this() {
        this.keyWordList = keyWordList
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = keyWordList[position]
    }

    override fun getItemCount(): Int =
        limitedItemCount
            ?.coerceAtMost(keyWordList.size)
            ?: keyWordList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_keyword,
                parent,
                false
            )
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id._keyword)
    }
}