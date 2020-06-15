package com.news.components.ui.application

import android.app.Application
import com.news.components.di.module
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