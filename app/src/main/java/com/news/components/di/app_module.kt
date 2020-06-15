package com.news.components.di

import com.news.components.model.remote.NewsApi
import com.news.components.model.remote.NewsApiImpl
import com.news.components.ui.adapter.PositiveNewsAdapter
import com.news.components.ui.adapter.PositiveKeywordAdapter
import com.news.components.ui.viewmodel.MainViewModel
import com.news.components.util.rx.AppSchedulerProvider
import com.news.components.util.rx.SchedulerProvider
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
