package com.blblblbl.unittest

import org.junit.Assert.*
import org.junit.Test
import kotlin.reflect.KClass


class TriangleTest{
    @Test
    fun testValidData(){
        val triangle = Triangle(3,4,5)
        val actual = triangle.isRightTriangle()
        val expected = true
        assertEquals(expected,actual)
    }
    @Test(expected = IllegalArgumentException::class)
    fun testValidDataNegative(){
        val triangle = Triangle(-3,-4,-5)
    }
    @Test(expected = IllegalArgumentException::class)
    fun testUnrealTriangle(){
        val triangle = Triangle(1,1,3)
    }
    @Test
    fun notRightTriangle(){
        val triangles = listOf<Triangle>(
            Triangle(1,1,1),
            Triangle(1,2,2),
            Triangle(4,5,6)
        )
        val expected = false
        triangles.forEach { triangle-> assertEquals(triangle.isRightTriangle(),expected) }
    }
}