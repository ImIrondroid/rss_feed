package com.news.sample.util.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.news.sample.util.ErrorImage

@BindingAdapter("imageUrl")
fun ImageView.load(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .thumbnail(0.5f)
        .override(150, 150)
        .error(ErrorImage.getDefaultImage())
        .into(this)
}