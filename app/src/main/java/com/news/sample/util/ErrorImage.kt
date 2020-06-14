package com.news.sample.util

import com.news.sample.R
import kotlin.random.Random

object ErrorImage {
    fun getDefaultImage(): Int {
        val imageList = listOf(
            R.drawable.default_first_background,
            R.drawable.default_second_background,
            R.drawable.default_third_background,
            R.drawable.default_fourth_background,
            R.drawable.default_fifth_background
        )
        return imageList[Random.nextInt(5)]
    }
}