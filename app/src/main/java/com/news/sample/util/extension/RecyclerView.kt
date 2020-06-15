package com.news.sample.util.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.news.sample.base.BaseRecyclerViewAdapter

@BindingAdapter("adapter")
fun RecyclerView.binding(adapter: RecyclerView.Adapter<*>? = null) {
    this.adapter = adapter
}


@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun<T> RecyclerView.binding(list: List<T>? = null) {
    (adapter as? BaseRecyclerViewAdapter<T>)?.run {
        submitList(list)
    }
}