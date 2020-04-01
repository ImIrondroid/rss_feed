package com.news.sample.util.lifecycle

import androidx.lifecycle.MutableLiveData

class NotNullMutableLiveData<T>(value: T): MutableLiveData<T>(value){
    override fun getValue(): T{
        return super.getValue()!!
    }
}