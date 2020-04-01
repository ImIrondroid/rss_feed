package com.news.sample.ui.navigator

import com.news.sample.base.BaseNavigator
import com.news.sample.model.News

interface DetailNavigator : BaseNavigator {

    val data: News
}