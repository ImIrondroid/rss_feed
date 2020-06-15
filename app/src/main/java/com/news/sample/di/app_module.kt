package com.news.sample.di

import com.news.sample.service.NewsApi
import com.news.sample.service.NewsApiImpl
import com.news.sample.ui.adapter.PositiveNewsAdapter
import com.news.sample.ui.adapter.PositiveKeywordAdapter
import com.news.sample.ui.viewmodel.MainViewModel
import com.news.sample.util.rx.AppSchedulerProvider
import com.news.sample.util.rx.SchedulerProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule : Module = module {

    single { NewsApiImpl(get()) as NewsApi }
    single { AppSchedulerProvider() as SchedulerProvider }
}

val viewModule : Module = module {

    factory { PositiveNewsAdapter() }
    factory { PositiveKeywordAdapter() }

    viewModel { MainViewModel(get()) }
}

val module = listOf(appModule, viewModule)
