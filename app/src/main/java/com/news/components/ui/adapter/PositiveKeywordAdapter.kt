package com.news.components.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.news.components.base.BaseRecyclerViewAdapter
import com.news.components.base.BaseViewHolder
import com.news.components.databinding.ItemKeywordBinding

class PositiveKeywordAdapter : BaseRecyclerViewAdapter<String>(
    object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        return PositiveKeywordViewHolder(
            ItemKeywordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class PositiveKeywordViewHolder(
        binding : ItemKeywordBinding
    ) : BaseViewHolder<String>(binding) {
        override fun onBind(item: String?) {
            super.onBind(item)
            if(item==null) return
        }
    }
}