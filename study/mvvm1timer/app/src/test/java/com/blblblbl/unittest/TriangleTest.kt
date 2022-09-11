package com.blblblbl.unittest

import org.junit.Assert.*
import org.junit.Test

class TriangleTest{
    @Test
    fun testValidData(){
        val triangle = Triangle(3,4,5)
        val actual = triangle.isRightTriangle()
        val expected = true
        assertEquals(expected,actual)
    }
}