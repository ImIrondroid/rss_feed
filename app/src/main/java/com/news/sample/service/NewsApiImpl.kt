package com.news.sample.service

import com.news.sample.model.News
import com.news.sample.util.extension.extract
import com.news.sample.util.rx.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class NewsApiImpl(
    private val scheduler: SchedulerProvider
) : NewsApi {
    override fun getAllNews(): Single<List<News>> {
        return Jsoup.connect("https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko").toSingle
            .map { doc ->
                doc.select("item").map { elm ->
                    Pair<String, String>(elm.select("link").text(), elm.select("link").text())
                }
            }
            .flatMap { itemList ->
                Flowable.fromIterable(itemList)
                    .concatMapEager { item ->
                        Jsoup.connect(item.first).toSingle
                            .map { doc ->
                                val desc = doc.select("meta[property=og:description]").attr("content")
                                val image = doc.select("meta[property=og:image]").attr("content")
                                val keyWords = desc.extract()
                                val keyWordsStringOnly = arrayListOf<String>()
                                keyWords.forEach { pair: Pair<String, Int> -> keyWordsStringOnly.add(pair.first) }
                                News(
                                    link = item.first,
                                    title = item.second,
                                    description = desc,
                                    image = image,
                                    keyWords = keyWordsStringOnly
                                )
                            }
                            .onErrorReturnItem(News(link = item.first, title = item.second))
                            .toFlowable()
                    }
                    .toList()
            }
    }

    override fun getNews(): Flowable<News> {
        return Jsoup.connect("https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko").toFlowable
            .map { doc ->
                doc.select("item").map { elm ->
                    Pair<String, String>(elm.select("link").text(), elm.select("title").text())
                }
            }
            .flatMap { itemList ->
                Flowable.fromIterable(itemList)
                    .concatMapEager { item ->
                        Jsoup.connect(item.first).toFlowable
                            .map { doc ->
                                val desc = doc.select("meta[property=og:description]").attr("content")
                                val image = doc.select("meta[property=og:image]").attr("content")
                                val keyWords = desc.extract()
                                val keyWordsStringOnly = arrayListOf<String>()
                                keyWords.forEach { pair: Pair<String, Int> -> keyWordsStringOnly.add(pair.first) }
                                News(
                                    link = item.first,
                                    title = item.second,
                                    description = desc,
                                    image = image,
                                    keyWords = keyWordsStringOnly
                                )
                            }
                            .onErrorReturnItem(News(link = item.first, title = item.second))
                    }
            }
    }

    private val Connection.toFlowable: Flowable<Document>
        get() {
            return Single.create { emitter: SingleEmitter<Document> ->
                try {
                    val doc = this.get()
                    if (!emitter.isDisposed) {
                        emitter.onSuccess(doc)
                    }
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
                .subscribeOn(scheduler.io())
                .toFlowable()
        }

    private val Connection.toSingle: Single<Document>
        get() {
            return Single.create { emitter: SingleEmitter<Document> ->
                try {
                    val doc = this.get()
                    if (!emitter.isDisposed) {
                        emitter.onSuccess(doc)
                    }
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
                .subscribeOn(scheduler.io())
        }
}