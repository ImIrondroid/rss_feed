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
        overtimeWait()
            .subscribe({getNavigator().nextActivity(MainActivity::class, clearTask = true)},{t: Throwable? -> t?.printStackTrace() })
            .let(compositeDisposable::add)
    }

    fun overtimeWait(): Observable<Long> {
        return Observable
            .timer(SPLASH_TIME, TimeUnit.MILLISECONDS)
    }

    companion object {
        const val SPLASH_TIME: Long = 1300L
    }
}
