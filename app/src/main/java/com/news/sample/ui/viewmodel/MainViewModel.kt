package com.news.sample.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.news.sample.base.BaseViewModel
import com.news.sample.model.News
import com.news.sample.service.NewsApi
import com.news.sample.util.lifecycle.NotNullMutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers

class MainViewModel(
    private val newsApi: NewsApi
) : BaseViewModel() {

    val refreshStatus = NotNullMutableLiveData(false)
    val newsList = MutableLiveData<List<News>>(mutableListOf())

    fun work() {
        newsApi.getNews()
            .doOnSubscribe { newsList.value = mutableListOf() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { news -> newsList.value = newsList.value!!.toMutableList().apply { this.add(news) }; if(refreshStatus.value) stopRefresh() },
                { throwable: Throwable? -> throwable?.printStackTrace() })
            .let{ addDisposable(it) }
    }

    fun startRefresh() {
        refreshStatus.value = true
        work()
    }

    fun stopRefresh() {
        refreshStatus.value = false
    }
}