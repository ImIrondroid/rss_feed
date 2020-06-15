package com.news.components.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.news.components.base.BaseViewModel
import com.news.components.model.News
import com.news.components.model.remote.NewsApi
import com.news.components.util.lifecycle.NotNullMutableLiveData
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