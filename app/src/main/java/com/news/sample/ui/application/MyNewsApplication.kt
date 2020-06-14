package com.news.sample.ui.application

import android.app.Application
import android.util.Log
import com.news.sample.di.module
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyNewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@MyNewsApplication)
            modules(module)
        }
    }
}