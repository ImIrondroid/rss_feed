package com.news.sample.util.extension

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter

@SuppressLint("SetJavaScriptEnabled")
@BindingAdapter("android:loadUrl")
fun WebView.loadUrl(linkUrl : String?) {

    this.apply {
        webViewClient = WebViewClient()
        settings.apply {
            javaScriptEnabled = true
            setSupportMultipleWindows(false)
            javaScriptCanOpenWindowsAutomatically = false
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportZoom(false)
            builtInZoomControls = false
            cacheMode = WebSettings.LOAD_DEFAULT
            domStorageEnabled = true
        }
        loadUrl(linkUrl)
    }
}