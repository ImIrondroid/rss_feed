package com.news.sample.ui.viewmodel

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.news.sample.base.BaseViewModel
import com.news.sample.model.News
import com.news.sample.ui.navigator.MainNavigator
import com.news.sample.util.lifecycle.NotNullMutableLiveData
import com.news.sample.util.rx.SchedulerProvider
import io.reactivex.*
import org.jsoup.Jsoup
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val scheduler: SchedulerProvider
): BaseViewModel<MainNavigator>() {

    var linkList = arrayListOf<News>()
    val refreshStatus = NotNullMutableLiveData(false)
    val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        linkList = arrayListOf()
        startRefresh()
        crawlingGoogle()
    }

    init {
        overtimeWait()
            .doOnComplete { getNavigator().hideLoading() }
            .subscribe({},{t: Throwable? -> t?.printStackTrace() })
            .let(compositeDisposable::add)
    }

    fun crawlingGoogle() {
        getLinks()
            .doOnSubscribe{ if(!refreshStatus.value) getNavigator().showLoading() }
            .retry(RETRY_TIMES)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.mainThread())
            .subscribe({ getNavigator().crawlingLink(); stopRefresh() } , { t: Throwable? -> t?.printStackTrace() })
            .let(compositeDisposable::add)
    }

    fun getLinks(): Completable {
        return Completable.create {
            try {
                val doc = Jsoup.connect(URL).get()
                val elemnets = doc.select("item")
                elemnets.forEach { element ->
                    val title = element.select("title").text()
                    val link = element.select("link").text()
                    linkList.add(News(link = link, title = title))
                }
            } catch(e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }

    fun overtimeWait(): Observable<Long> {
        return Observable
            .timer(OVER_TIME, TimeUnit.MILLISECONDS)
    }

    private fun startRefresh() {
        refreshStatus.value = true
    }

    private fun stopRefresh() {
        refreshStatus.value = false
    }

    companion object {
        const val URL = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"
        const val OVER_TIME: Long  = 3000L
        const val RETRY_TIMES: Long = 3L
    }
}