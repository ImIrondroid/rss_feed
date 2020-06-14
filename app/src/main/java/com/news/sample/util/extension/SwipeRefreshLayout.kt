package com.news.sample.util.extension

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("register")
fun SwipeRefreshLayout.registerListener(listener: SwipeRefreshLayout.OnRefreshListener?) {
    if(listener!=null) this.setOnRefreshListener(listener)
}

@BindingAdapter("android:onRefresh")
fun SwipeRefreshLayout.refresh(status: Boolean) {
    this.isRefreshing = status
}