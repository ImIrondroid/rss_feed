package com.news.sample.service

import com.news.sample.model.News
import io.reactivex.Flowable
import io.reactivex.Single

interface NewsApi {
    fun getAllNews() : Single<List<News>>
    fun getNews() : Flowable<News>
}