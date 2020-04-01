package com.news.sample.ui.viewmodel

import com.news.sample.base.BaseViewModel
import com.news.sample.ui.navigator.SplashNavigator
import com.news.sample.ui.view.activity.MainActivity
import com.news.sample.util.rx.SchedulerProvider
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SplashViewModel(
    private val scheduler: SchedulerProvider
): BaseViewModel<SplashNavigator>() {

    fun toMain() {
        Observable
            .timer(SPLASH_TIME, TimeUnit.MILLISECONDS)
            .subscribe { getNavigator().nextActivity(MainActivity::class, clearTask = true)}
            .let(compositeDisposable::add)
    }

    companion object {
        const val SPLASH_TIME: Long = 1300L
    }
}
