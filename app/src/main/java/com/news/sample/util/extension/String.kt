package com.news.sample.util.extension

fun String.extract(): List<Pair<String, Int>> {

    var sortList = arrayListOf<Pair<String, Int>>()
    val tempList: ArrayList<String> = arrayListOf()
    val hashMap = LinkedHashMap<String, Int>()
    val limitLength = 2

    val words: List<String> = this
        .replace("[^\uAC00-\uD7A3xfe0-9a-zA-Z]".toRegex(), " ")
        .split(" ")

    for(item in words) {
        if(item.length>=limitLength) {
            tempList.add(item)
            if(hashMap[item]==null) hashMap[item] = 0
            else hashMap[item] = hashMap[item]!!.plus(1)
        }
    }

    for(item in hashMap) {
        sortList.add(Pair(item.key, item.value))
    }

    //Value값 기준으로 내림차순을 진행합니다
    sortList.valueSort()

    //같은 Value값들의 Index범위를 구하는 과정입니다
    val indexList = arrayListOf<Pair<Int, Int>>()
    var start = 0
    var end = 0
    for(i in 0 until sortList.size) {
        if(i==(sortList.size-2)) {
            if(sortList[i].second==sortList[i+1].second) {
                indexList.add(Pair(start, sortList.size-1))
            } else {
                indexList.add(Pair(start, sortList.size-2))
            }
            break
        }
        if(sortList[i].second!=sortList[i+1].second) {
            indexList.add(Pair(start, end))
            start = i+1
            end = i+1
        }
        else end++
    }

    //같은 Value값의 범위마다 유니코드 기준 오름차순을 진행합니다.
    indexList.forEach{ pair: Pair<Int, Int> -> sortList.keySort(pair.first, pair.second) }

    return sortList.toList()
}