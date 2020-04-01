package com.news.sample.ui.viewmodel

import com.news.sample.base.BaseViewModel
import com.news.sample.ui.navigator.DetailNavigator
import com.news.sample.util.rx.SchedulerProvider

class DetailViewModel(
    private val scheduler: SchedulerProvider
): BaseViewModel<DetailNavigator>()