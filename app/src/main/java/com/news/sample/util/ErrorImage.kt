package com.news.sample.util

import com.news.sample.R

object ErrorImage {
    fun getDefaultImage(position: Int): Int {
        val imageList = listOf(
            R.drawable.default_first_background,
            R.drawable.default_second_background,
            R.drawable.default_third_background,
            R.drawable.default_fourth_background,
            R.drawable.default_fifth_background
        )
        return imageList[position%5]
    }
}