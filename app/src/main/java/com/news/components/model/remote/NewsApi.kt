package com.news.components.model.remote

import com.news.components.model.News
import io.reactivex.Flowable
import io.reactivex.Single

interface NewsApi {
    fun getAllNews() : Single<List<News>>
    fun getNews() : Flowable<News>
}