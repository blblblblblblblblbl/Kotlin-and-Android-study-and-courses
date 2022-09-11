package com.blblblbl.unittest

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DoerTest{
    private lateinit var logger:TestLogger
    private lateinit var doer:Doer

    @Before
    fun setUp(){
        logger = TestLogger()
        doer = Doer(logger)
    }

    @After
    fun clear(){
        logger.logCallCount=0
    }

    @Test
    fun testOneTimeCase(){
        doer.doMain()
        val actual = logger.logCallCount
        val expected = 1
        assertEquals(actual,expected)
    }
    private inner class TestLogger:Logging{
        var logCallCount = 0
        override fun log(message: String) {
            logCallCount++
        }
    }
}