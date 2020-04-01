package com.news.sample.util.extension

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun ConstraintLayout.setVisibility(str: String?) {

    if(str.isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}