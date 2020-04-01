package com.news.sample

import com.news.sample.util.extension.extract
import org.junit.Assert
import org.junit.Test

class ExtractTest {

    lateinit var description: String
    lateinit var actualValue: List<Pair<String, Int>>
    lateinit var expectedValue: List<Pair<String, Int>>

    @Test
    fun extractTestForEmptyValue() {

        description = ""
        actualValue = description.extract()
        expectedValue = listOf<Pair<String,Int>>()

        Assert.assertEquals(expectedValue, actualValue)
    }

    @Test
    fun extractTestForWrongValue() {

        description = "중앙방역대책본부- 중앙방역대책본부 30일 0시 기준"
        actualValue = description.extract()
        expectedValue = listOf(Pair("0시",0),Pair("30일",0),Pair("기준",0),Pair("중앙방역대책본부",1))

        Assert.assertEquals(expectedValue, actualValue)
    }

    @Test
    fun extractTestForRightValue() {

        description = "중앙방역대책본부- 중앙방역대책본부 30일 0시 기준"
        actualValue = description.extract()
        expectedValue = listOf(Pair("중앙방역대책본부",1),Pair("0시",0),Pair("30일",0),Pair("기준",0))

        Assert.assertEquals(expectedValue, actualValue)
    }
}