package com.news.sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.news.sample.base.BaseRecyclerViewAdapter
import com.news.sample.base.BaseViewHolder
import com.news.sample.databinding.TestItemNewsBinding
import com.news.sample.model.News
import com.news.sample.util.OnItemSelectedListener
import kotlinx.android.synthetic.main.test_item_news.view.*

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
            TestItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class PositiveNewsViewHolder(
        binding : TestItemNewsBinding
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