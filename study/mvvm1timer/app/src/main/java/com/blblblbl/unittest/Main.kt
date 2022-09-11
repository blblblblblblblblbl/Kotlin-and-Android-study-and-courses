package com.blblblbl.unittest

object Main{
    fun main(args:Array<String>){
        val triangle = Triangle(3,4,5)
        println(triangle.isRightTriangle())
    }
}

 class Triangle(private val sideA:Int,private val sideB:Int,private val sideC:Int){

     fun isRightTriangle():Boolean{
         return (sideA.square()+sideB.square()==sideC.square())
                 ||(sideB.square()+sideC.square()==sideA.square())
                 ||(sideC.square()+sideA.square()==sideB.square())
     }

     private fun Int.square():Int{
         return this*this
     }
 }