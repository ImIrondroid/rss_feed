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
    newsApi: NewsApi
) : BaseViewModel() {

    val refreshStatus = NotNullMutableLiveData(false)
    val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        Log.e("test : " , "test")
        //work()
    }

    val newsList = MutableLiveData<List<News>>(mutableListOf())

    init {
        newsApi.getNews()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { news -> newsList.value = newsList.value!!.toMutableList().apply { this.add(news) } },
                { throwable: Throwable? -> throwable?.printStackTrace() })
            .let{ addDisposable(it) }
    }
}