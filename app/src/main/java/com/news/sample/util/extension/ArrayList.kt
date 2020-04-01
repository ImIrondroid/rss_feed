package com.news.sample.util.extension

import kotlin.collections.ArrayList

fun ArrayList<Pair<String, Int>>.valueSort() {

    var temp = Pair("", 0)
    for(i in this.size-1 downTo 1 ) {
        for(j in i downTo 0) {
            if(this[i].second > this[j].second) {
                temp = this[j]
                this[j] = this[i]
                this[i] = temp
            }
        }
    }
}

fun ArrayList<Pair<String, Int>>.keySort(start: Int, end: Int) {

    var temp = Pair("", 0)
    for(i in start until end ) {
        for(j in start until end-i+start) {
            val firstWord = this[j].first
            val secondWord = this[j+1].first
            var index = 0
            val limit =
                if(firstWord.length>secondWord.length) secondWord.length
                else firstWord.length
            while(index!=limit) {
                if(firstWord[index] > secondWord[index]) {
                    temp = this[j]
                    this[j] = this[j+1]
                    this[j+1] = temp
                    break
                } else if(firstWord[index] < secondWord[index]) {
                    break
                }
                index++
            }
            if(index==limit) {
                if(firstWord.length>secondWord.length) {
                    temp = this[j]
                    this[j] = this[j+1]
                    this[j+1] = temp
                }
            }
        }
    }
}