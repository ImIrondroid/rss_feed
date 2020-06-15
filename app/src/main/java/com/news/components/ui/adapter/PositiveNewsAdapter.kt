package com.news.components.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.news.components.base.BaseRecyclerViewAdapter
import com.news.components.base.BaseViewHolder
import com.news.components.databinding.PositiveItemNewsBinding
import com.news.components.model.News
import com.news.components.util.OnItemSelectedListener
import kotlinx.android.synthetic.main.positive_item_news.view.*

class PositiveNewsAdapter : BaseRecyclerViewAdapter<News>(
    object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.link==newItem.link
        }
        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.link==newItem.link &&
                    oldItem.title==newItem.title &&
                    oldItem.description==newItem.description
        }
    }
) {
    private var onItemSelectedListener : OnItemSelectedListener<News>? = null

    fun setOnItemSelectedListener(onItemSelectedListener: OnItemSelectedListener<News>) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    override fun onBindViewHolder(holder: BaseViewHolder<News>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView._rcv_keyword.adapter = KeywordAdapter(currentList[position].keyWords).apply {
            limitedItemCount = 3
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<News> {
        return PositiveNewsViewHolder(
            PositiveItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class PositiveNewsViewHolder(
        binding : PositiveItemNewsBinding
    ) : BaseViewHolder<News>(binding) {
        override fun onBind(item: News?) {
            super.onBind(item)
            if(item==null) return
            itemView.setOnClickListener {
                onItemSelectedListener?.invoke(item)
            }
        }
    }
}