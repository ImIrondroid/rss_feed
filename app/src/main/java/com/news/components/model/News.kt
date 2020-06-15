package com.news.components.model

import androidx.databinding.BaseObservable
import java.io.Serializable

data class News(
    var link: String = "",
    var title: String = "",
    var description: String = "",
    var image: String = "",
    var keyWords: ArrayList<String> = arrayListOf(),
    var status: Boolean = false
): BaseObservable(), Serializable