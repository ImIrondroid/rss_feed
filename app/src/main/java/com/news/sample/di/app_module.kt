package com.news.sample.di

import com.news.sample.ui.viewmodel.DetailViewModel
import com.news.sample.ui.viewmodel.MainViewModel
import com.news.sample.ui.viewmodel.SplashViewModel
import com.news.sample.util.rx.AppSchedulerProvider
import com.news.sample.util.rx.SchedulerProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule : Module = module {

    single { AppSchedulerProvider() as SchedulerProvider }
}

val viewModule : Module = module {

    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val module = listOf(appModule, viewModule)
