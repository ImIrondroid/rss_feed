package com.news.sample.util

import android.app.Activity
import android.os.Bundle
import kotlin.reflect.KClass

interface ActionStore {

    fun <T: Activity> nextActivity(kClass: KClass<T>, bundle: Bundle? = null, clearTask: Boolean = false)

    fun showLoading()
    fun hideLoading()
}